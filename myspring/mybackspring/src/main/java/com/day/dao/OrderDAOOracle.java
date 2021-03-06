package com.day.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.day.dto.Customer;
import com.day.dto.OrderInfo;
import com.day.dto.OrderLine;
import com.day.dto.Product;
import com.day.exception.AddException;
import com.day.exception.FindException;
import com.day.sql.MyConnection;

@Repository("orderDAO")
public class OrderDAOOracle implements OrderDAO {

	@Autowired
	private SqlSessionFactory sessionFactory;
	
	@Transactional
	public void test() {
		//session.insert(~~~~)
		//session.insert(~~~)
		String str = null;
		System.out.println(str.length()); //NullPointerException  발생하면 자동 RollbaCK
	}
	@Override 
	@Transactional(rollbackFor = AddException.class)
	public void insert(OrderInfo info) throws AddException {
		SqlSession session = null;
		try {
			session = sessionFactory.openSession();
			insertInfo(session, info); //ex: 주문수량이 아주 큰값인 경우, 주문 기본 테이블 데이터 추가 성공
			insertLines(session, info.getLines());//		주문상세테이블 데이터 추가 실패 -- 자동으로 롤백 처리 한다.!
			//session.commit(); // 커밋
		} catch (Exception e) {
				//session.rollback(); // 롤백
				e.printStackTrace();

		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	/**
	 * 주문기본정보 추가한다
	 * 
	 * @param con  DB연결객체
	 * @param info 주문기본정보
	 * @throws AddException
	 */
	private void insertInfo(SqlSession session,OrderInfo info) throws AddException {
		// DB연결
		try {
			session.insert("com.day.dto.OrderMapper.insertInfo", info);
		} catch (Exception e) {
			throw new AddException(e.getMessage());
		} 
	}

	/**
	 * 주문 상세 정보들 추가한다
	 * 
	 * @param con   DB연결객체
	 * @param lines 주문상세정보들
	 * @throws AddException
	 */
	private void insertLines(SqlSession session, List<OrderLine> lines) throws AddException {
		try {
			for (OrderLine line : lines) {
				session.insert("com.day.dto.OrderMapper.insertLine", line);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new AddException("주문상세 추가실패:" + e.getMessage());
		}
	}

	@Override
	public List<OrderInfo> selectById(String id) throws FindException {
		SqlSession session = null;
		try {
			session = sessionFactory.openSession();
			List<OrderInfo> list = session.selectList("com.day.dto.OrderMapper.selectById", id);
			session.commit();
		
			if (list.size() == 0) {
				throw new FindException("주문내역이 없습니다");
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		} 
}
}
