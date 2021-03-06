package com.day.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.day.dto.Customer;
import com.day.exception.AddException;
import com.day.exception.DuplicatedException;
import com.day.exception.FindException;
import com.day.exception.ModifyException;
import com.day.sql.MyConnection;

@Repository("customerDAO")
public class CustomerDAOOracle implements CustomerDAO{


	@Override
	public void insert(Customer c) throws AddException {
		//DB연결
		Connection con = null;
		try {
			con = MyConnection.getConnection();
			System.out.println("DB연결 성공");
		} catch (Exception e) {
			e.printStackTrace();
			throw new AddException(null);
		}
		
		//회원가입 SQL문
		String insertSQL = "INSERT INTO customer(id,pwd,name) VALUES (?,?,?)";
		//?에 값을 입력해주기 위한 메소드 PreparedStatement
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(insertSQL);
			pstmt.setString(1, c.getId());
			pstmt.setString(2, c.getPwd());
			pstmt.setString(3, c.getName());
			//SQL문 업데이트
			pstmt.executeUpdate();
			System.out.println("정보가 추가되었습니다.");
		} catch (java.sql.SQLIntegrityConstraintViolationException e) {
			e.printStackTrace();
			throw new DuplicatedException("이미 존재하는 아이디입니다.");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AddException("추가에 실패했습니다.");
		} finally {
			MyConnection.close(con, pstmt, null);
		}

	}

	@Override
	public Customer selectById(String id) throws FindException {
		Connection con = null;
		try {
			con = MyConnection.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		}

		String selectSQL = "SELECT * FROM customer WHERE id=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Customer c = null;
		try {
			pstmt = con.prepareStatement(selectSQL);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				String pwd = rs.getString("pwd");
				String name = rs.getString("name");
				String buildingno=rs.getString("buildingno");
				int enabled = rs.getInt("enabled");

				c = new Customer(id, pwd, name, buildingno, enabled);
				return c;
			} else {
				throw new FindException("해당 고객을 찾을 수 없습니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		} finally {
			MyConnection.close(con, pstmt, rs);
		}
	}

	@Override
	public void update(Customer c) throws ModifyException {
		//고정값 변수선언
		//SET pwd = 'p1', name = 'n1', buildingno = '1';
		String updateSQL = "UPDATE customer SET ";
		String updateSQL1 = " WHERE id = ?";
		 //변경할 값이 있는경우 true, 변경할 값이 없으면 false.
		boolean flag = false;
		//비밀번호를 변경할 경우
		String pwd = c.getPwd();
		if (pwd!=null && !pwd.equals("")) {
			updateSQL += "pwd = '" + pwd + "'"; 
			flag = true;			
		}		
		//이름을 변경할 경우
		String name = c.getName();
		if (name != null && !name.equals("")) {
			if(flag) {
				// SET절에서 수정할 내용을 추가할 경우에는 ,가 필요하기 때문
				updateSQL += ","; 
			}
			updateSQL += "name = '" + name + "'";
			flag = true;
		}
		//주소를 변경할 경우.
		String buildingno = c.getBuildingno();
		if (buildingno != null && !buildingno.equals("")) {
			if(flag) {
				updateSQL += ",";
			}
			updateSQL += "buildingno = '" + buildingno + "'";
			flag = true;
		}
		//
		int enabled = c.getEnabled();
		if (enabled > -1) { //0=탈퇴, 1=활동
			if(flag) {
				updateSQL += ",";
			}
			updateSQL += "enabled = '" + enabled + "'";
			flag = true;
		}
		System.out.println(updateSQL + updateSQL1);
		if(!flag) {
			throw new ModifyException("수정할 내용이 없습니다");
		}
//		Connection con = null;
//		try {
//			con = MyConnection.getConnection();
//		} catch (SQLException e) {			
//			e.printStackTrace();
//		}
//		updateSQL + updateSQL1
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
