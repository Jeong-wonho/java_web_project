package com.day.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.day.dto.Product;
import com.day.exception.FindException;
import com.day.sql.MyConnection;

@Repository("productDAO1")
public class ProductDAOOracle implements ProductDAO {
		
	public ProductDAOOracle() throws Exception {
		//JDBC드라이버 로드
		Class.forName("oracle.jdbc.driver.OracleDriver");
		System.out.println("JDBC드라이버 로드 성공");
		return;
	}
	@Override
	public List<Product> selectAll() throws FindException {
		//DB연결
		Connection con = null;
		try {
			con = MyConnection.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		}
		
		String selectALLSQL = "SELECT * FROM Product ORDER BY prod_no ASC";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Product> list = new ArrayList<>();
		try {
			pstmt = con.prepareStatement(selectALLSQL);
			rs = pstmt.executeQuery();
			//rs.next(); -- 커서를 이동한 위치에 행이 존재하면 true, 존재하지 않으면 false.
			while(rs.next()) {
				//행의 컬럼값 얻기
				String prod_no = rs.getString("prod_no");
				String prod_name = rs.getString("prod_name");
				int prod_price = rs.getInt("prod_price");
				java.sql.Date prod_mf_dt = rs.getDate("prod_mf_dt");

				Product p = new Product(prod_no, prod_name, prod_price, prod_mf_dt, null);
				list.add(p);
			}
			if(list.size() == 0) {
				throw new FindException("상품이 없습니다");
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace(); // 콘솔에 오류내용 출력.
			throw new FindException(e.getMessage());
		} finally {
			//DB연결해제
			MyConnection.close(con, pstmt, rs);
		}				
	}

	@Override
	public List<Product> selectAll(int currentPage) throws FindException {
		int cnt_per_page = 4; // 페이지별 보여줄 목록수
		Connection con = null;
		try {
			con = MyConnection.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		}
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Product> list = new ArrayList<>();
		String selectALLpageSQL = "SELECT *\r\n" + 
				"FROM (SELECT rownum rn, a.*\r\n" + 
				"           FROM   (SELECT *\r\n" + 
				"                        FROM product \r\n" + 
				"                        --WHERE order_dt BETWEEN '21/01/01' AND '21/03/31' \r\n" + 
				"                        ORDER BY prod_no ASC \r\n" + 
				"                       ) a\r\n" + 
				"          )\r\n" + 
				"WHERE rn BETWEEN START_ROW(?, ?) AND END_ROW(?, ?)";
		try {
			pstmt = con.prepareStatement(selectALLpageSQL);
			pstmt.setInt(1, currentPage);
			pstmt.setInt(2, cnt_per_page);
			pstmt.setInt(3, currentPage);
			pstmt.setInt(4, cnt_per_page);
			rs = pstmt.executeQuery();
			//행의 컬럼값 얻기
			while(rs.next()) {
				String prod_no = rs.getString("prod_no");
				String prod_name = rs.getString("prod_name");
				int prod_price = rs.getInt("prod_price");
				java.sql.Date prod_mf_dt = rs.getDate("prod_mf_dt");

				Product p = new Product(prod_no, prod_name, prod_price, prod_mf_dt, null);
				list.add(p);
			}
			if(list.size() == 0) {
				throw new FindException("상품이 없습니다");
			}
			return list;

		} catch (SQLException e) {
			e.printStackTrace(); // 콘솔에 오류내용 출력.
			throw new FindException(e.getMessage());
		} finally {
			//DB연결해제
			MyConnection.close(con, pstmt, rs);
		}
	}

	@Override
	public Product selectByNo(String prod_no) throws FindException {
		Connection con = null;
		try {
			con = MyConnection.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		}
		
		String selectByNoSQL = "SELECT * FROM product where prod_no = ? ";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement(selectByNoSQL);
			pstmt.setString(1, prod_no);
			rs = pstmt.executeQuery();
			if(rs.next()){
				prod_no = rs.getString("prod_no");
				String prod_name = rs.getString("prod_name");
				int prod_price = rs.getInt("prod_price");
				java.sql.Date prod_mf_dt = rs.getDate("prod_mf_dt");
				Product p = new Product(prod_no, prod_name, prod_price, prod_mf_dt, null);
				return p;
			} else {
				throw new FindException("상품이 없습니다.");
			}

		} catch (SQLException e) {
			e.printStackTrace(); // 콘솔에 오류내용 출력.
			throw new FindException(e.getMessage());
		} finally {
			//DB연결해제
			MyConnection.close(con, pstmt, rs);
		}
	}

	@Override
	public List<Product> selectByName(String word) throws FindException {
		//DB연결
		Connection con = null;
		try {
			con = MyConnection.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		}
		
		String selectByNameSQL = "SELECT * FROM product where prod_name LIKE ? order by prod_no";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Product> list = new ArrayList<>();
		try {
			//SQL구문 송신, 수신, List화, 반환
			//송신
			pstmt = con.prepareStatement(selectByNameSQL);
			pstmt.setString(1, "%"+word+"%");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				//수신
				String prod_no = rs.getString("prod_no");
				String prod_name = rs.getString("prod_name");
				int prod_price = rs.getInt("prod_price");
				java.sql.Date prod_mf_dt = rs.getDate("prod_mf_dt");
				//리스트화
				Product p = new Product(prod_no, prod_name, prod_price, prod_mf_dt, null);
				list.add(p);
			}
			if(list.size() == 0) {
				throw new FindException("상품이 없습니다");
			}
			return list;

		} catch (SQLException e) {
			e.printStackTrace(); // 콘솔에 오류내용 출력.
			throw new FindException(e.getMessage());
		} finally {
			//DB연결해제
			MyConnection.close(con, pstmt, rs);
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
