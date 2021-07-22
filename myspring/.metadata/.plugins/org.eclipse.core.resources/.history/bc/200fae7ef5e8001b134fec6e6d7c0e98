package com.day.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


import com.day.dto.Customer;
import com.day.exception.AddException;
import com.day.exception.DuplicatedException;
import com.day.exception.FindException;
import com.day.exception.ModifyException;


//@Repository("customerDAO")
public class CustomerDAOOracle implements CustomerDAO{

	private SqlSessionFactory sqlSessionFactory;
	
	public CustomerDAOOracle() {
		String resource="mybatis-config.xml";
		InputStream inputStream;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		}catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void insert(Customer c) throws FindException {
		//DB연결
		SqlSession session = sqlSessionFactory.openSession();
		try {
			session.insert("com.day.dto.CustomerMapper.insert", c);
		} catch (Exception e) {
			throw new FindException(e.getMessage());
		}  finally {
			if (session != null) {
				session.close();
			}
		}

	}

	@Override
	public Customer selectById(String id) throws FindException {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			Customer c = session.selectOne("com.day.dto.CustomerMapper.selectById", id);
			return c;
		} catch (Exception e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		} finally {
			if(session!=null) {
				session.close();
			}
		}
	}

	@Override
	public void update(Customer c) throws ModifyException {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			session.update("com.day.dto.CustomerMapper.update", c);
		} catch (Exception e) {
			throw new ModifyException(e.getMessage());
		} finally {
			if(session!=null) {
				session.close();
			}
		}
	}
	
	
}
