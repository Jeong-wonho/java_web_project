package com.day.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.day.dto.Product;
import com.day.exception.FindException;


//@Repository("productDAO1")
public class ProductDAOOracle implements ProductDAO {

	private SqlSessionFactory sqlSessionFactory;

	public ProductDAOOracle() {
		String resource = "mybatis-config.xml";
		InputStream inputStream;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}

	}

	@Override
	public List<Product> selectAll() throws FindException {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			List<Product> list = session.selectList("com.day.dto.ProductMapper.selectAll");
			return list;
		} catch (Exception e) {
			throw new FindException(e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	@Override
	public List<Product> selectAll(int currentPage) throws FindException {
		int cnt_per_page = 4; // 페이지별 보여줄 목록수
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			HashMap<String, Integer>map = new HashMap<>();
			map.put("cnt_per_page", cnt_per_page);
			map.put("currentPage", currentPage);
			List<Product> list =session.selectList("com.day.dto.ProductMapper.selectAllPage", map);
			if(list.size()==0) {
				throw new FindException("상품이없습니다.");
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace(); // 콘솔에 오류내용 출력.
			throw new FindException(e.getMessage());
		} finally {
			if(session != null) {
				session.close();
			}
			
		}
	}

	@Override
	public Product selectByNo(String prod_no) throws FindException {
		SqlSession session=null;
		try {
			session=sqlSessionFactory.openSession();
			Product p = session.selectOne("com.day.dto.ProductMapper.selectByNo", prod_no);
			if(p==null) {
				throw new FindException("상품이없습니다.");
			}
			return p;

		} catch (Exception e) {
			e.printStackTrace(); // 콘솔에 오류내용 출력.
			throw new FindException(e.getMessage());
		} finally {
			if(session != null) {
				session.close();
			}
		}
	}

	@Override
	public List<Product> selectByName(String word) throws FindException {
		// DB연결
		SqlSession session=null;
		try {
			session= sqlSessionFactory.openSession();
			HashMap<String, String> map = new HashMap<>();
			map.put("word", word);
			map.put("ordermethod", "prod_no ASC");
			List<Product> list = session.selectList("com.day.dto.ProductMapper.selectByName", map);
			if(list.size() == 0) {
				throw new FindException("상품이없습니다.");
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace(); // 콘솔에 오류내용 출력.
			throw new FindException(e.getMessage());
		} finally {
			// DB연결해제
			if(session != null) {
				session.close();
			}
		}
	}
//	public static void main(String[] args) {
//		String word = "리";
//		System.out.println("\""+word+"\"단어를 포함한 상품목록");
//		try {
//			ProductDAOOracle dao = new ProductDAOOracle();
////			List<Product> all = dao.selectAll(3);
////			Product p1 = dao.selectByNo("C0001");
//			List<Product> all_1 = dao.selectByName(word);
//			for (Product p : all_1) {
//				System.out.println(p); // p.toString()자동호출됨.
//			}
//		} catch(FindException e) {
//			System.out.println(e.getMessage());
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
//	}
}
