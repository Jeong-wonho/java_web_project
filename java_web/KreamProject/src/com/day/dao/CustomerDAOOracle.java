package com.day.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.day.dto.Customer;
import com.day.exception.AddException;
import com.day.exception.DuplicatedException;
import com.day.exception.FindException;
import com.day.exception.ModifyException;
import com.day.sql.Myconnection;

public class CustomerDAOOracle implements CustomerDAO{

	@Override
	public Customer insert(Customer c) throws AddException {
		//DB연결
		Connection con = null;
		try {
			con = Myconnection.getConnection();
			System.out.println("DB연결 성공");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AddException(null);
		}

		//회원가입
		String insertSQL = "INSERT INTO kr_customer values (?, ?, ?, ?, ?, ?)";
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(insertSQL);
			pstmt.setString(1, c.getUser_id());
			pstmt.setString(2, c.getUser_pwd());
			pstmt.setString(3, c.getUser_name());
			pstmt.setString(4, c.getUser_ssn());
			pstmt.setString(5, c.getUser_phone());
			pstmt.setString(6, c.getUser_addr());
			pstmt.executeUpdate();
			System.out.println("정보가 추가되었습니다.");
		} catch (java.sql.SQLIntegrityConstraintViolationException e) {
			e.printStackTrace();
			throw new DuplicatedException("이미 존재하는 아이디입니다.");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AddException("추가에 실패했습니다.");
		} finally {
			Myconnection.close(con, pstmt, null);
		}
		return c;

	}

	@Override
	public Customer selectById(String user_id) throws FindException {
		Connection con = null;
		try {
			con = Myconnection.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		}

		//아이디로 회원정보 얻기
		String selectSQL = "SELECT * FROM kr_customer WHERE user_id=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Customer c = null;
		try {
			pstmt = con.prepareStatement(selectSQL);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				String user_pwd = rs.getString("user_pwd");
				String user_name = rs.getString("user_name");
				String user_ssn =rs.getString("user_ssn");
				String user_phone =rs.getString("user_phone");
				String user_addr =rs.getString("user_addr");				

				c = new Customer(user_id, user_pwd, user_name, user_ssn, user_phone, user_addr);
				return c;
			} else {
				throw new FindException("해당 고객을 찾을 수 없습니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		} finally {
			Myconnection.close(con, pstmt, rs);
		}
	}

	@Override
	public String findId(String phone) throws FindException {
		Connection con = null;
		try {
			con = Myconnection.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		}

		//휴대폰 번호로 아이디 찾기
		String findIdSQL = "SELECT user_id FROM kr_customer WHERE user_phone=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement(findIdSQL);
			pstmt.setString(1, phone);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				String user_id = rs.getString("user_id");
				return user_id;
			} else {
				throw new FindException("해당 고객을 찾을 수 없습니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		} finally {
			Myconnection.close(con, pstmt, rs);
		}
	}

	@Override
	public String findPwd(String phone, String id) throws FindException {
		Connection con = null;
		try {
			con = Myconnection.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		}

		String findPwdSQL = "SELECT user_pwd FROM kr_customer WHERE user_phone =? AND user_id =?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		try {
			pstmt = con.prepareStatement(findPwdSQL);
			pstmt.setString(1, phone);
			pstmt.setString(2, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {	
				String user_pwd = rs.getString("user_pwd");				
				return user_pwd;				
			} else {
				throw new FindException("해당 고객을 찾을 수 없습니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		} finally {
			Myconnection.close(con, pstmt, rs);
		}
	}

	@Override
	public void update(Customer c) throws ModifyException {
		Connection con = null;
		try {
			con = Myconnection.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ModifyException(e.getMessage());
		}		

		//고정값 변수선언
		String updateSQL = "UPDATE kr_customer SET ";
		String updateSQL1 = " WHERE user_id = ?";

		//변경할 값이 있는경우 true, 변경할 값이 없으면 false.
		boolean flag = false;

		//비밀번호를 변경할 경우
		String user_pwd = c.getUser_pwd();
		if (user_pwd!=null && !user_pwd.equals("")) {
			updateSQL += "user_pwd = '" + user_pwd + "'"; 
			flag = true;			
		}	

		//휴대폰 번호를 변경할 경우
		String user_phone = c.getUser_phone();
		if (user_phone!=null && !user_phone.equals("")) {
			updateSQL += "user_phone = '" + user_phone + "'"; 
			flag = true;			
		}

		//주소를 변경할 경우.
		String user_addr = c.getUser_addr();
		if (user_addr != null && !user_addr.equals("")) {
			if(flag) {
				updateSQL += ",";
			}
			updateSQL += "user_addr = '" + user_addr + "'";
			flag = true;
		}		
		if(!flag) {
			throw new ModifyException("수정할 내용이 없습니다");
		}

		String finalUpateSQL = updateSQL + updateSQL1;
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(finalUpateSQL);
			pstmt.setString(1, c.getUser_id());
			pstmt.executeUpdate();
			System.out.println("정보가 수정되었습니다.");
		} catch (SQLException e) {			
			e.printStackTrace();
			throw new ModifyException("정보수정에 실패했습니다.");
		} finally {
			Myconnection.close(con, pstmt, null);
		}		
	}
}
