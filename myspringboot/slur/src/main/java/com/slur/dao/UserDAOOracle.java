package com.slur.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.slur.dto.User;
import com.slur.exception.AddException;
import com.slur.exception.FindException;
import com.slur.exception.ModifyException;

@Repository("userDAO")
public class UserDAOOracle implements UserDAO {
	@Autowired
	private SqlSessionFactory sessionFactory;
	
	@Override
	public void insert(User u) throws AddException, FindException {
		// TODO Auto-generated method stub
		//db 연결
		SqlSession session = null;
		try {
			session = sessionFactory.openSession();
			session.insert("com.slur.dto.UserMapper.insert", u);
			session.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new AddException(e.getMessage());
		}finally {
			if(session != null) {
				session.close();
			}
		}

	}

	@Override
	public User selectById(String user_id) throws FindException {
		// TODO Auto-generated method stub
		SqlSession session = null;
		try {
			session = sessionFactory.openSession();
			User u  = session.selectOne("com.slur.dto.UserMapper.selectById", user_id);
			
			return u;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new FindException(e.getMessage());
		}finally {
			if(session != null) {
				session.close();
			}
		}
	}

	@Override
	public void update(User u) throws ModifyException {
		// TODO Auto-generated method stub
		SqlSession session = null;
		try {
			session=sessionFactory.openSession();
			session.update("com.slur.dto.UserMapper.update", u);
		}catch(Exception e) {
			throw new ModifyException(e.getMessage());
		}finally {
			if(session!=null) {
				session.close();
			}
		}
	}

}
