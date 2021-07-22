package com.day.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.day.dto.Customer;
import com.day.dto.Order;
import com.day.dto.Product;
import com.day.exception.AddException;
import com.day.exception.FindException;
import com.day.exception.ModifyException;
import com.day.exception.RemoveException;
import com.day.sql.Myconnection;

public class OrderDAOOracle implements OrderDAO {
	public OrderDAOOracle() throws Exception{
		Class.forName("oracle.jdbc.driver.OracleDriver");
//		System.out.println("JDBC드라이버로드 성공");
	}
	public Order selectMinOrder(Order o) throws FindException {
		Connection con = null;
		try {
			con=Myconnection.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		}
		
		String selectMinpriceSQL="select ord.order_num, ord.order_size, ord.order_price min_price \n"
				+ "from kr_order ord join kr_product prod\n"
				+ "on prod.prod_num = ord.prod_num\n"
				+ "where prod.PROD_NUM = ? and ord.order_size = ? and ord.order_type = 0 and ord.order_status = 1\n"
				+ "and ord.order_price=(select min(ord.order_price) from kr_order) order by ord.order_price";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Order minO = new Order();
		try {
			pstmt=con.prepareStatement(selectMinpriceSQL);
			pstmt.setInt(1, o.getProd_num().getProd_num());
			pstmt.setInt(2, o.getOrder_size());
			rs=pstmt.executeQuery();
			while(rs.next()) {
				minO.setOrder_num(rs.getInt("order_num"));
				minO.setOrder_price(rs.getInt("min_price"));
				return minO;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			Myconnection.close(con, pstmt, rs);
		}

		
		
		
		return minO;
	}
	public Order selectMaxOrder(Order o) throws FindException {
		Connection con = null;
		try {
			con=Myconnection.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		}
		
		String selectMaxpriceSQL="select ord.order_num, ord.order_size, ord.order_price max_price \n"
				+ "from kr_order ord join kr_product prod\n"
				+ "on prod.prod_num = ord.prod_num\n"
				+ "where prod.PROD_NUM = ? and ord.order_size = ? and ord.order_type = 1 and ord.order_status = 1\n"
				+ "and ord.order_price=(select max(ord.order_price) from kr_order) order by ord.order_price desc";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Order maxO = new Order();

		try {
			pstmt=con.prepareStatement(selectMaxpriceSQL);
			pstmt.setInt(1, o.getProd_num().getProd_num());
			pstmt.setInt(2, o.getOrder_size());
			rs=pstmt.executeQuery();
			while(rs.next()) {
				maxO.setOrder_num(rs.getInt("order_num"));
				maxO.setOrder_price(rs.getInt("max_price"));
				return maxO;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			Myconnection.close(con, pstmt, rs);
		}

		
		
		
		return maxO;
	}

	@Override
	public Map<Order, Integer> selectMinprice(int prod_num) throws FindException {
		Connection con = null;
		try {
			con=Myconnection.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		}
		String selectMinpriceSQL="select ord.order_num, ord.order_size, ord.order_price min_price \n"
				+ "from kr_order ord join kr_product prod\n"
				+ "on prod.prod_num = ord.prod_num\n"
				+ "where prod.PROD_NUM = ? and ord.order_type = 0 and ord.order_status = 1\n"
				+ "and ord.order_price=(select min(ord.order_price) from kr_order)";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Map<Order, Integer> map = new HashMap<>();
		try {
			pstmt=con.prepareStatement(selectMinpriceSQL);
			pstmt.setInt(1, prod_num);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Order o = new Order();
				o.setOrder_num(rs.getInt("order_num"));
				Product p = new Product(prod_num);
				o.setProd_num(p);
				o.setOrder_size(rs.getInt("order_size"));
				map.put(o, rs.getInt("min_price"));
			}
			if(map.size()==0) {
//				throw new FindException("주문내역이 없습니다");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		}finally {
			Myconnection.close(con, pstmt, rs);
		}
		return map;
	}

	@Override
	public Map<Order, Integer> selectMaxprice(int prod_num) throws FindException {
		Connection con = null;
		try {
			con=Myconnection.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		}
		//오더넘버가필요함
		String selectMaxpriceSQL="select ord.order_size, max(ord.order_price) max_price \n"
				+ "from kr_order ord join kr_product prod\n"
				+ "on prod.prod_num = ord.prod_num\n"
				+ "where prod.PROD_NUM = ? and ord.order_type = 0 and ord.order_status = 1\n"
				+ "group by ord.order_size";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Map<Order, Integer> map = new HashMap<>();
		try {
			pstmt=con.prepareStatement(selectMaxpriceSQL);
			pstmt.setInt(1, prod_num);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Order o = new Order();
				Product p = new Product(prod_num);
				o.setProd_num(p);
				o.setOrder_size(rs.getInt("order_size"));
				map.put(o, rs.getInt("max_price"));
			}
			if(map.size()==0) {
//				throw new FindException("주문내역이 없습니다");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		}finally {
			Myconnection.close(con, pstmt, rs);
		}
		return map;
	}

	@Override
	public List<Order> selectRecent(int prod_num) throws FindException {
		Connection con = null;
		try {
			con=Myconnection.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		}
		String selectRecentSQL="select *\n"
				+ "from (select ord.order_size, ord.order_price, ord.order_date\n"
				+ "from kr_order ord join kr_product prod\n"
				+ "on prod.prod_num = ord.prod_num\n"
				+ "where ord.PROD_NUM=? and ord.order_status = 2\n"
				+ "order by order_date desc)\n"
				+ "where rownum <= 10";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Order> list = new ArrayList<>();
		try {
			pstmt=con.prepareStatement(selectRecentSQL);
			pstmt.setInt(1, prod_num);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Order o = new Order();
				Product p = new Product(prod_num);
				o.setProd_num(p);
				o.setOrder_size(rs.getInt("order_size"));
				o.setOrder_price(rs.getInt("order_price"));
				o.setOrder_date(rs.getDate("order_date"));
				list.add(o);
			}
			if(list.size()==0) {
//				throw new FindException("주문내역이 없습니다");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		}finally {
			Myconnection.close(con, pstmt, rs);
		}
		return list;
	}

	@Override
	public void insert(Order o) throws AddException {
		Connection con = null;
		try {
			con=Myconnection.getConnection();
			con.setAutoCommit(false);//자동커밋해제
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AddException(e.getMessage());
		}		
		PreparedStatement pstmt = null;
		String insertSQL = "INSERT INTO kr_order(order_num, buyer_user_id, "
				+ "seller_user_id, prod_num, order_type, order_size, order_price,"
				+ " order_date, order_status) VALUES(ORDER_NUM_SEQ.nextval, "
				+ "?,?,?,?,?,?,sysdate,1)";
		try {
			pstmt = con.prepareStatement(insertSQL);
			pstmt.setString(1, o.getBuyer_id().getUser_id());
			pstmt.setString(2, o.getSeller_id().getUser_id());
			pstmt.setInt(3, o.getProd_num().getProd_num());
			pstmt.setInt(4, o.getOrder_type());
			pstmt.setInt(5, o.getOrder_size());
			pstmt.setInt(6, o.getOrder_price());
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
	public void update(Order o) throws ModifyException {
		Connection con = null;
		try {
			con = Myconnection.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ModifyException(e.getMessage());
		}
		
		String updateSQL = "UPDATE kr_order SET ";
		if(o.getOrder_type()==0) {
			updateSQL+="buyer_user_id = ?,order_status = 2 WHERE order_num = ?";
		}else {
			updateSQL+="seller_user_id = ?, order_status = 2 WHERE order_num = ?";
		}
		PreparedStatement pstmt = null;
		System.out.println(updateSQL);

		try {
			pstmt = con.prepareStatement(updateSQL);
			if(o.getOrder_type()==0) {
				pstmt.setString(1, o.getBuyer_id().getUser_id());
			}else {
				pstmt.setString(1, o.getSeller_id().getUser_id());
			}
			pstmt.setInt(2, o.getOrder_num());
			int rowcnt = pstmt.executeUpdate();
			if(rowcnt==1) {
				System.out.println("변경성공");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			Myconnection.close(con, pstmt, null);
		}
	}

	@Override
	public void delete(int order_num) throws RemoveException {
		Connection con = null;
		try {
			con = Myconnection.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RemoveException(e.getMessage());
		}
		
		String deleteSQL = "DELETE FROM kr_order WHERE order_num = ?";
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(deleteSQL);
			pstmt.setInt(1, order_num);
			int rowcnt = pstmt.executeUpdate();
			if(rowcnt==1) {
				System.out.println("삭제성공");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			Myconnection.close(con, pstmt, null);
		}
	}
	

	@Override
	public Order selectByNum(int order_num) throws FindException {
		Connection con = null;
		try {
			con = Myconnection.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		}
		String selectByNumSQL = "SELECT * FROM kr_order WHERE order_num = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		System.out.println(1);
		try {
			pstmt = con.prepareStatement(selectByNumSQL);
			pstmt.setInt(1, order_num);
			rs = pstmt.executeQuery();
			if(rs.next()) {	
				Order o = new Order();
				o.setOrder_num(order_num);
				Customer c_b = new Customer();
				c_b.setUser_id(rs.getString("buyer_user_id"));
				o.setBuyer_id(c_b);
				Customer c_s = new Customer();
				c_s.setUser_id(rs.getString("seller_user_id"));
				o.setSeller_id(c_s);
				Product p = new Product();
				p.setProd_num(rs.getInt("prod_num"));
				o.setProd_num(p);
				o.setOrder_type(rs.getInt("order_type"));
				o.setOrder_size(rs.getInt("order_size"));
				o.setOrder_price(rs.getInt("order_price"));
				o.setOrder_date(rs.getDate("order_date"));
				o.setOrder_status(rs.getInt("order_status"));
				return o;
			}
			throw new FindException("아이디가 존재하지 않습니다");
		}catch(SQLException e) {
			throw new FindException(e.getMessage());
		}finally {
			Myconnection.close(con, pstmt, rs);
		}
		
	}

	@Override
	public List<Integer> collectMinprice(Product p) throws FindException {
		Connection con = null;
		try {
			con = Myconnection.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		}
		String collecMinpriceSQL="select ord.order_size, min(ord.order_price) min_price \n"
				+ "from kr_order ord join kr_product prod\n"
				+ "on prod.prod_num = ord.prod_num\n"
				+ "where prod.PROD_NUM = ? and ord.order_type = 0 and ord.order_status = 1\n"
				+ "group by ord.order_size";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Integer> list = new ArrayList<>();
		int startsize=0;
		int endsize=0;
		if(p.getProd_gender().equals("w")) {
			startsize=220;
			endsize=300;
		}else {
			startsize=230;
			endsize=310;
		}
		try {
			pstmt=con.prepareStatement(collecMinpriceSQL);
			pstmt.setInt(1, p.getProd_num());
			rs=pstmt.executeQuery();
			while(rs.next()) {
				int size = rs.getInt("order_size");
				while(startsize<size) {
					list.add(0);
					startsize+=5;
				}
				list.add(rs.getInt("min_price"));
				startsize+=5;
			}
			while(startsize<=endsize) {
				list.add(0);
				startsize+=5;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		}finally {
			Myconnection.close(con, pstmt, rs);
		}	
		return list;
	}

	@Override
	public List<Integer> collectMaxprice(Product p) throws FindException {
		Connection con = null;
		try {
			con = Myconnection.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		}
		String collectMaxpriceSQL="select ord.order_size, max(ord.order_price) max_price \n"
				+ "from kr_order ord join kr_product prod\n"
				+ "on prod.prod_num = ord.prod_num\n"
				+ "where prod.PROD_NUM = ? and ord.order_type = 1 and ord.order_status = 1\n"
				+ "group by ord.order_size";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Integer> list = new ArrayList<>();
		int startsize=0;
		int endsize=0;
		if(p.getProd_gender().equals("w")) {
			startsize=220;
			endsize=300;
		}else {
			startsize=230;
			endsize=310;
		}
		try {
			pstmt=con.prepareStatement(collectMaxpriceSQL);
			pstmt.setInt(1, p.getProd_num());
			rs=pstmt.executeQuery();
			while(rs.next()) {
				int size = rs.getInt("order_size");
				while(startsize<size) {
					list.add(0);
					startsize+=5;
				}
				list.add(rs.getInt("max_price"));
				startsize+=5;
			}
			while(startsize<=endsize) {
				list.add(0);
				startsize+=5;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());
		}finally {
			Myconnection.close(con, pstmt, rs);
		}	
		return list;
	}
}


