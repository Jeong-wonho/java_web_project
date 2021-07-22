package com.day.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.day.dto.Product;
import com.day.dto.Shop;
import com.day.exception.FindException;
import com.day.sql.Myconnection;

public class ShopDAOOracle implements ShopDAO {

	public ShopDAOOracle() throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		System.out.println("JDBC 드라이버 로드 성공");
	}

	@Override
	public List<Shop> selectAll(String id) throws FindException {
		// TODO Auto-generated method stub
		Connection con = null;
		try {
			con = Myconnection.getConnection();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new FindException(e.getMessage());
		}

		String selectALLSQL = "SELECT kr_product.*," + "(SELECT MIN(kr_order.order_price) \r\n"
				+ "FROM kr_order " + "WHERE kr_order.prod_num = kr_product.prod_num) order_p\r\n";

		if (id != null && !id.equals("")) {
			selectALLSQL += ", (SELECT COUNT(prod_num) \r\n" + "FROM kr_bookmark \r\n"
					+ "WHERE kr_bookmark.prod_num = kr_product.prod_num AND kr_bookmark.user_id ='"+id+"') as bm\r\n";
		}

		selectALLSQL += "FROM kr_product ORDER BY prod_count desc";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Shop> list = new ArrayList<>();
		try {
			pstmt = con.prepareStatement(selectALLSQL);
			rs = pstmt.executeQuery();
			// rs.next() 이동한 위치에 행이 존재하면 true를 반환하고, 행이 존재하지 않으면 false를 반환한다.
			while (rs.next()) {
				// 행의 컬럼값 얻기
				int prod_num = rs.getInt("prod_num");
				String prod_name = rs.getString("prod_name");
				String prod_brand = rs.getString("prod_brand");
				String prod_collection = rs.getString("prod_collection");
				String prod_gender = rs.getString("prod_gender");
				int shop_price = rs.getInt("order_p");
				
				int shop_bm = 0;

				if (id != null && !id.equals("")) {
					shop_bm = rs.getInt("bm");
					
				}
				Product p = new Product(prod_num,prod_name,prod_brand,prod_collection, prod_gender);
				Shop s = new Shop(p, shop_price, shop_bm);
				list.add(s);
			}


			if (list.size() == 0) {
				throw new FindException("상품이 없습니다.111");
			}

			return list;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new FindException(e.getMessage());
		} finally {
			Myconnection.close(con, pstmt, rs);
		}

	}

	@Override
	public List<Shop> selectByName(String id, String name) throws FindException {
		// TODO Auto-generated method stub
		String low_name = name.toLowerCase(); // 문자 소문자로 변환
		Connection con = null;
		try {
			con = Myconnection.getConnection();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new FindException(e.getMessage());
		}
		String selectNamesql = "select * from (SELECT kr_product.*," + "(SELECT MIN(kr_order.order_price) \r\n"
				+ "FROM kr_order " + "WHERE kr_order.prod_num = kr_product.prod_num) order_p";
		if (id != null && !id.equals(" ")) {
			selectNamesql += ", (SELECT COUNT(prod_num)" + "FROM kr_bookmark \r\n"
					+ "WHERE kr_bookmark.prod_num = kr_product.prod_num AND  kr_bookmark.user_id = '"+id+"') bm\r\n";
		}
		selectNamesql += " FROM kr_product) where lower(prod_name) Like ?";
		
		System.out.println(selectNamesql);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Shop> list = new ArrayList<>();
		try {
			pstmt = con.prepareStatement(selectNamesql);
			pstmt.setString(1, "%" + low_name + "%");
			rs = pstmt.executeQuery();
			// rs.next() 이동한 위치에 행이 존재하면 true를 반환하고, 행이 존재하지 않으면 false를 반환한다.
			while (rs.next()) {
				// 행의 컬럼값 얻기
				int prod_num = rs.getInt("prod_num");
				String prod_name = rs.getString("prod_name");
				String prod_brand = rs.getString("prod_brand");
				String prod_collection = rs.getString("prod_collection");
				String prod_gender = rs.getString("prod_gender");
				int shop_price = rs.getInt("order_p");
				int shop_bm = 0;
				if (id != null && !id.equals("")) {
					shop_bm = rs.getInt("bm");
				}

				Product p = new Product(prod_num, prod_name,prod_brand, prod_collection, prod_gender);
				Shop s = new Shop(p, shop_price, shop_bm);
				list.add(s);
			}

			if (list.size() == 0) {
				throw new FindException("상품이 없습니다.111");
			}

			return list;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new FindException(e.getMessage());
		} finally {
			Myconnection.close(con, pstmt, rs);
		}
	}

//	@Override
//	public List<Shop> selectByGender(String id, String[] gender) throws FindException {
//		ArrayList<String> gender_list = new ArrayList<String>();
//		
//		if(gender!=null) {
//		for (int i = 0; i < gender.length; i++) {
//			gender_list.add(gender[i]);
//		}
//		}
//		System.out.println(gender_list.size());
//		Connection con = null;
//		try {
//			con = Myconnection.getConnection();
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//			throw new FindException(e.getMessage());
//		}
//
//		String selectGendersql = "select * from (SELECT kr_product.*," + "(SELECT MIN(kr_order.order_price) \r\n"
//				+ "FROM kr_order " + "WHERE kr_order.prod_num = kr_product.prod_num) order_p";
//		if (id != null && !id.equals("")) {
//			selectGendersql += ", (SELECT COUNT(prod_num)" + "FROM kr_bookmark \r\n"
//					+ "WHERE kr_bookmark.prod_num = kr_product.prod_num AND  kr_bookmark.user_id = '"+id+"') bm\r\n";
//		}
//		selectGendersql += " FROM kr_product) where prod_gender = ?";
//
//		for (int i = 1; i < gender_list.size(); i++) {
//			if(!gender_list.get(i).isEmpty()) {
//				selectGendersql += " or prod_gender = ?";
//			}
//		}
//
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		List<Shop> list = new ArrayList<>();
//		try {
//			
//			pstmt = con.prepareStatement(selectGendersql);
//			if(gender_list.size()>0) {
//			for (int i = 0; i < gender_list.size(); i++) {
//				pstmt.setString(i+1, gender_list.get(i));
//			}
//			}
//			rs = pstmt.executeQuery();
//			// rs.next() 이동한 위치에 행이 존재하면 true를 반환하고, 행이 존재하지 않으면 false를 반환한다.
//			while (rs.next()) {
//				// 행의 컬럼값 얻기
//				int shop_num = rs.getInt("prod_num");
//				String shop_name = rs.getString("prod_name");
//				String prod_brand = rs.getString("prod_brand");
//				String prod_collection = rs.getString("prod_collection");
//				String prod_gender = rs.getString("prod_gender");
//				int shop_price = rs.getInt("order_p");
//				int shop_bm = 0;
//				if (id != null && !id.equals("")) {
//					shop_bm = rs.getInt("bm");
//				}
//
//				Product p = new Product(shop_num, shop_name, prod_brand, prod_collection, prod_gender);
//				Shop s = new Shop(p, shop_price, shop_bm);
//				list.add(s);
//			}
//			
//			if (list.size() == 0) {
//
//			}
//
//			return list;
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//			throw new FindException(e.getMessage());
//		} finally {
//			Myconnection.close(con, pstmt, rs);
//		}

//	}

	@Override
	public List<Shop> selectByReleaseddt(String id) throws FindException {
		Connection con = null;
		try {
			con = Myconnection.getConnection();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new FindException(e.getMessage());
		}

		String selectddtsql = "select * from (SELECT kr_product.*," + "(SELECT MIN(kr_order.order_price) \r\n"
				+ "FROM kr_order " + "WHERE kr_order.prod_num = kr_product.prod_num) order_p";
		if (id != null && !id.equals(" ")) {
			selectddtsql += ", (SELECT COUNT(prod_num)" + "FROM kr_bookmark \r\n"
					+ "WHERE kr_bookmark.prod_num = kr_product.prod_num AND  kr_bookmark.user_id = '"+id+"') bm\r\n";
		}
		selectddtsql += " FROM kr_product) where rownum < 5 order by prod_releaseddt desc";

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Shop> list = new ArrayList<>();
		try {
			pstmt = con.prepareStatement(selectddtsql);
			rs = pstmt.executeQuery();
			// rs.next() 이동한 위치에 행이 존재하면 true를 반환하고, 행이 존재하지 않으면 false를 반환한다.
			while (rs.next()) {
				// 행의 컬럼값 얻기
				
				int shop_num = rs.getInt("prod_num");
				String shop_name = rs.getString("prod_name");
				String prod_brand = rs.getString("prod_brand");
				String prod_collection = rs.getString("prod_collection");
				String prod_gender = rs.getString("prod_gender");
				int shop_price = rs.getInt("order_p");
				int shop_bm = 0;
				if (id != null && !id.equals("")) {
					shop_bm = rs.getInt("bm");
				}

				Product p = new Product(shop_num,  shop_name, prod_brand, prod_collection, prod_gender);
				Shop s = new Shop(p, shop_price, shop_bm);
				list.add(s);
			}
			// list_size 확인
			

			if (list.size() == 0) {
				throw new FindException("상품이 없습니다.111");
			}

			return list;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new FindException(e.getMessage());
		} finally {
			Myconnection.close(con, pstmt, rs);
		}

	}
	
	@Override
	public List<Shop> selectByList(String id, String[] brand) throws FindException {
		// TODO Auto-generated method stub
		ArrayList<String> brand_list = new ArrayList<String>();
		ArrayList<String> gender = new ArrayList<String>();
		int status=0;
		String tmp="";
		for (int i = 0; i < brand.length; i++) {
			if(brand[i].equals("m")) {
				if(status==1) {
					tmp="";
				}else {
				tmp=brand[i].toLowerCase();
				status=1;
				}
			}else if(brand[i].equals("w")) {
				if(status==1) {
					tmp="";	
				}else {
					tmp=brand[i].toLowerCase();
					status=1;
				}		
			}else {
				brand_list.add(brand[i].toLowerCase());
			}
		}
		if(tmp!="") {
			gender.add(tmp);
		}
		
		Connection con = null;
		try {
			con = Myconnection.getConnection();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new FindException(e.getMessage());
		}
		
		String selectNamesql="";
		System.out.println(gender.size());
		if(!gender.isEmpty()) {
			selectNamesql = "select * from ("; 
		}
			
		selectNamesql += "SELECT kr_product.*," + "(SELECT MIN(kr_order.order_price) \r\n"
				+ "FROM kr_order " + "WHERE kr_order.prod_num = kr_product.prod_num) order_p \r\n";
		
		if (id != null && !id.equals("")) {
			
			selectNamesql += ", (SELECT COUNT(prod_num)" + "FROM kr_bookmark \r\n"
					+ "WHERE kr_bookmark.prod_num = kr_product.prod_num AND  kr_bookmark.user_id = '"+id+"') bm\r\n";
		}
		
		//wonho's modify ++
		selectNamesql += "FROM kr_product ";
		System.out.println("여기는오나");
		if(!brand_list.isEmpty()&&!gender.isEmpty()) {
			selectNamesql += " where lower(prod_name) Like ?";
			for (int i = 1; i < brand_list.size(); i++) {
				if(!brand_list.get(i).isEmpty()) {
					selectNamesql += " or lower(prod_name) Like ?";
				}
			}
			
			if(gender.get(0).equals("m")) {
				selectNamesql += ") where prod_gender in 'm'";
			}else if(gender.get(0).equals("w")) {
				selectNamesql += ") where prod_gender in 'w'";
			}
		}else if(!brand_list.isEmpty()){
			selectNamesql += " where lower(prod_name) Like ?";
			for (int i = 1; i < brand_list.size(); i++) {
				if(!brand_list.get(i).isEmpty()) {
					selectNamesql += " or lower(prod_name) Like ?";
				}
			}
		}else if(!gender.isEmpty()) {
			if(gender.get(0).equals("m")) {
				selectNamesql += ") where prod_gender in 'm'";
			}else if(gender.get(0).equals("w")) {
				selectNamesql += ") where prod_gender in 'w'";
			}
		}else {
			
		}
		

		
		System.out.println(selectNamesql);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Shop> list = new ArrayList<>();
		try {
			pstmt = con.prepareStatement(selectNamesql);
			for (int i = 0; i < brand_list.size(); i++) {
				pstmt.setString(i+1, "%" + brand_list.get(i) + "%");
			}
			rs = pstmt.executeQuery();
			// rs.next() 이동한 위치에 행이 존재하면 true를 반환하고, 행이 존재하지 않으면 false를 반환한다.
			while (rs.next()) {
				// 행의 컬럼값 얻기
				int prod_num = rs.getInt("prod_num");
				String prod_name = rs.getString("prod_name");
				String prod_brand = rs.getString("prod_brand");
				String prod_collection = rs.getString("prod_collection");
				String prod_gender = rs.getString("prod_gender");
				int shop_price = rs.getInt("order_p");
				int shop_bm = 0;
				if (id != null && !id.equals("")) {
					shop_bm = rs.getInt("bm");
				}

				Product p = new Product(prod_num, prod_name, prod_brand, prod_collection, prod_gender);
				Shop s = new Shop(p, shop_price, shop_bm);
				list.add(s);
			}

			if (list.size() == 0) {
				throw new FindException("상품이 없습니다.111");
			}

			return list;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new FindException(e.getMessage());
		} finally {
			Myconnection.close(con, pstmt, rs);
		}
	}
	
	
	public static void main(String[] args) {
		
		
		String[] brand = {"m","Nike"};
		String id = "";
		try {
			ShopDAOOracle dao = new ShopDAOOracle();
			List<Shop> all = dao.selectByList(id, brand);
			for (Shop s : all) {
				System.out.println(s.getShop_p().getProd_brand());
			}

		} catch (FindException e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
