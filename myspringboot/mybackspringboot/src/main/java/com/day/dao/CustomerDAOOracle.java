package com.day.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.day.dto.Customer;
import com.day.exception.AddException;
import com.day.exception.FindException;
import com.day.exception.ModifyException;


@Repository("customerDAO")
public class CustomerDAOOracle implements CustomerDAO{
	
//	@Autowired
//	private DataSource ds;
	@Autowired
	private SqlSessionFactory sessionFactory;
	@Override
	public void insert(Customer c) throws AddException {
		//DB연결
		SqlSession session = null;
		try {
			session = sessionFactory.openSession();
			session.insert("com.day.dto.CustomerMapper.insert", c);
			session.commit();
		}catch(Exception e) {
			e.printStackTrace();
			throw new AddException(e.getMessage());
		}finally {
			if(session != null) {
				session.close();
			}
		}
	

	}

	@Override
	public Customer selectById(String id) throws FindException {
		SqlSession session = null;
		try {
			session = sessionFactory.openSession();
			Customer c =session.selectOne("com.day.dto.CustomerMapper.selectById", id);
			
			return c;
		} catch (Exception e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		} finally {
			if(session != null) {
				session.close();
			}
		}
	}

	@Override
	public void update(Customer c) throws ModifyException {
		SqlSession session = null;
		try {
			session = sessionFactory.openSession();
			session.update("com.day.dto.CustomerMapper.update", c);
		} catch (Exception e) {
			throw new ModifyException(e.getMessage());
		} finally {
			if(session!=null) {
				session.close();
			}
		}
	}
	
	public static void main(String[] args) {
		CustomerDAOOracle dao = new CustomerDAOOracle();
		Customer c = new Customer();
		//c.setId("id1");
		//c.setPwd("pp1");
		//c.setName("nn1");
		//c.setBuildingno("b1");
		//c.setEnabled(-1);
		try {
			dao.selectById("id1");
			System.out.println(dao.selectById("id1"));
		} catch (FindException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
	
}
