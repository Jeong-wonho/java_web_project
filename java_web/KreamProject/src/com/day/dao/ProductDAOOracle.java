package com.day.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.day.dto.Product;
import com.day.exception.FindException;
import com.day.sql.Myconnection;

public class ProductDAOOracle implements ProductDAO{

	//1. jdbc드라이버로드
	public ProductDAOOracle() throws Exception{
		Class.forName("oracle.jdbc.driver.OracleDriver");
//		System.out.println("JDBC드라이버로드 성공");
	}
	@Override
	public List<Product> selectAll() throws FindException {
		// DB연결
		Connection con = null;
		try {
			con = Myconnection.getConnection();
		}catch(SQLException e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		}
		
		String selectAllsql = "SELECT * FROM kr_product";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Product> list = new ArrayList<>();
		try {
			pstmt = con.prepareStatement(selectAllsql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				//행의 컬럼값 얻기
				int prod_num = rs.getInt("prod_num");
				String prod_name = rs.getString("prod_name");
				String prod_brand = rs.getString("prod_brand");
				String prod_collection = rs.getString("prod_collection");
				String prod_modelnum = rs.getString("prod_modelnum");
				int prod_price = rs.getInt("prod_releaseprice");
				java.sql.Date prod_releaseddt = rs.getDate("prod_releaseddt");
				int prod_count = rs.getInt("prod_count");
				String prod_gender = rs.getString("prod_gender");
				
				Product p = new Product(prod_num, prod_name, prod_brand, prod_collection,
						prod_modelnum, prod_price, prod_releaseddt, prod_count, prod_gender);
				list.add(p);
			}
			
			if(list.size() == 0) {
				throw new FindException("상품이없습니다.");
			}
			return list;
		} catch (Exception e) {
			// TODO: handle exception
			throw new FindException(e.getMessage());
		}finally {
			//DB연결 해제
			Myconnection.close(con, pstmt, rs);
		}
		
	}

	@Override
	public Product selectByNo(int prod_num) throws FindException {
		// TODO Auto-generated method stub
				Connection con = null;
				try {
					con = Myconnection.getConnection();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					throw new FindException(e.getMessage());
				}
				
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				
				String sqlbynoquery = "select * from kr_product where prod_num = ?";
				try {
					pstmt = con.prepareCall(sqlbynoquery);
					pstmt.setInt(1, prod_num);
					rs = pstmt.executeQuery();
					
					if(rs.next()) {
//						int prod_num = rs.getInt("prod_num");
						String prod_name = rs.getString("prod_name");
						String prod_brand = rs.getString("prod_brand");
						String prod_collection = rs.getString("prod_collection");
						String prod_modelnum = rs.getString("prod_modelnum");
						int prod_price = rs.getInt("prod_releaseprice");
						java.sql.Date prod_releaseddt = rs.getDate("prod_releaseddt");
						int prod_count = rs.getInt("prod_count");
						String prod_gender = rs.getString("prod_gender");
						
						Product p = new Product(prod_num, prod_name, prod_brand, prod_collection,
								prod_modelnum, prod_price, prod_releaseddt, prod_count, prod_gender);
						
						return p;
					}
					
					else {
						throw new FindException("상품이 없습니다.111");
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					throw new FindException(e.getMessage());
				}finally {
					Myconnection.close(con, pstmt, rs);
				}
			
	}
	@Override
	public List<Product> selectBy() throws FindException {
		// TODO Auto-generated method stub
		Connection con = null;
		try {
			con = Myconnection.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new FindException(e.getMessage());
		}
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sqlbynoquery = "select distinct(prod_brand) from kr_product";
		
		List<Product> list = new ArrayList<>();
		try {
			pstmt = con.prepareStatement(sqlbynoquery);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Product p = new Product();
				p.setProd_brand(rs.getString("prod_brand"));
				list.add(p);
				
			}
			
			if(list.size() == 0) {
				throw new FindException("상품이 없습니다.111");
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new FindException(e.getMessage());
		}finally {
			Myconnection.close(con, pstmt, rs);
		}
	}
	
	public static void main(String[] args) {
		ProductDAOOracle dao;
		try {
			dao = new ProductDAOOracle();
			List<Product> p = dao.selectBy();
			for (Product product : p) {
				System.out.println(product.getProd_brand());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
