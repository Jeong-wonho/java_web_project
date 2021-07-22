package com.day.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.day.dto.Bookmark;
import com.day.dto.Customer;
import com.day.dto.Product;
import com.day.exception.AddException;
import com.day.exception.FindException;
import com.day.exception.RemoveException;
import com.day.sql.Myconnection;

public class BookmarkDAOOracle implements BookmarkDAO{
	/**
	 * 	private int bm_num;
	private Customer bm_c;
	private Product bm_p;
	private int prod_size;
	 */
	public BookmarkDAOOracle() throws Exception{
		Class.forName("oracle.jdbc.driver.OracleDriver");
//		System.out.println("JDBC드라이버로드 성공");
	}
	/** 승환
	 * 북마크 테이블에 넣을때 
	 * 중복처리 아직안했음
	 */
	@Override
	public void insert(Bookmark bm) throws AddException {
		Connection con = null;
		try {
			con = Myconnection.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AddException(e.getMessage());
		}
		
		String insertSQL = "INSERT INTO kr_bookmark(bm_num, user_id, prod_num, prod_size) "
				+ "VALUES(BM_NUM_SEQ.nextval, ?, ?, ?)";
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(insertSQL);
			pstmt.setString(1, bm.getBm_c().getUser_id());
			pstmt.setInt(2, bm.getBm_p().getProd_num());
			pstmt.setInt(3, bm.getProd_size());
			int rowcnt = pstmt.executeUpdate();
			if(rowcnt==1) {
				System.out.println("추가성공");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(2);
		}finally {
			Myconnection.close(con, pstmt, null);
		}
	}

	@Override
	public void delete(int bm_num) throws RemoveException {
		Connection con = null;
		try {
			con = Myconnection.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RemoveException(e.getMessage());
		}
		String deleteSQL = "DELETE FROM kr_bookmark WHERE bm_num = ?";
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(deleteSQL);
			pstmt.setInt(1, bm_num);
			int rowcnt = pstmt.executeUpdate();
			if(rowcnt==1) {
				System.out.println("삭제성공");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			Myconnection.close(con, pstmt, null);
		}

		
				
	}

	@Override
	public List<Bookmark> selectAll(String bm_id) throws FindException {
		Connection con = null;
		try {
			con=Myconnection.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		}
		String selectAllSQL="SELECT bm.bm_num, bm.PROD_NUM, prod_size From kr_bookmark bm"
				+ " JOIN kr_product p ON(bm.PROD_NUM = p.PROD_NUM) WHERE bm.USER_ID = ?"
				+ " ORDER BY bm_num DESC";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Bookmark> list = new ArrayList<>();
		try {
			pstmt=con.prepareStatement(selectAllSQL);
			pstmt.setString(1, bm_id);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Bookmark bm = new Bookmark();
				bm.setBm_num(rs.getInt("bm_num"));
				Customer bm_c = new Customer();
				bm_c.setUser_id(bm_id);
				bm.setBm_c(bm_c);
				Product bm_p = new Product();
				bm_p.setProd_num(rs.getInt("prod_num"));
				bm.setBm_p(bm_p);
				bm.setProd_size(rs.getInt("prod_size"));
				list.add(bm);
			}
			if(list.size()==0) {
			
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		}finally {
			Myconnection.close(con, pstmt, rs);
		}
	}
}
