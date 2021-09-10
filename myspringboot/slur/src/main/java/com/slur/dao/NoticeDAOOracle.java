package com.slur.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.slur.dto.Notice;
import com.slur.dto.Review;
import com.slur.exception.FindException;

@Repository("NoticeDAO")
public class NoticeDAOOracle implements NoticeDAO {
	
	@Autowired
	private SqlSessionFactory sessionFactory;
	
	@Override
	public List<Notice> selectAll(int pageNo, int amount) throws FindException {
		// TODO Auto-generated method stub
		SqlSession session = null;
		try {
			session = sessionFactory.openSession();
			HashMap<String, Integer> map = new HashMap<>();
			map.put("pageNum", pageNo);
			map.put("amount", amount);
			List<Notice> list = session.selectList("com.slur.dto.NoticeMapper.selectAll", map);
			return list;
		} catch (Exception e) {
			throw new FindException(e.getMessage());
		}finally {
			session.close();
		}
	}

	@Override
	public Notice selectByInfo(int notice_num) throws FindException {
		// TODO Auto-generated method stub
		SqlSession session = null;
		try {
			session=sessionFactory.openSession();
			Notice notice = session.selectOne("com.slur.dto.NoticeMapper.selectByInfo", notice_num);
			return notice;
		} catch (Exception e) {
			throw new FindException(e.getMessage());
		}finally {
			session.close();
		}
	}

	@Override
	public List<Notice> selectAll() throws FindException {
		// TODO Auto-generated method stub
		SqlSession session = null;
		try {
			session = sessionFactory.openSession();
			List<Notice> list = session.selectList("com.slur.dto.NoticeMapper.selectCount");
			return list;
		} catch (Exception e) {
			throw new FindException(e.getMessage());
		}finally {
			session.close();
		}
	}
}
