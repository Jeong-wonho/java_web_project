package com.day.control;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.day.dto.Bookmark;
import com.day.dto.Customer;
import com.day.dto.Product;
import com.day.exception.AddException;
import com.day.service.BookmarkService;

/**
 * Servlet implementation class BookmarkServlet
 */
public class BookmarkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//요청전달데이터 얻기
		String prod_num = request.getParameter("prod_num");
		String prod_size = request.getParameter("prod_size");
		String bm_id = request.getParameter("id");
		System.out.println(prod_num);
		System.out.println(prod_size);
		System.out.println(bm_id);
		
		Customer c = new Customer();
		Product p = new Product();
		
		c.setUser_id(bm_id);
		p.setProd_num(Integer.parseInt(prod_num));
		

		BookmarkService service;
		

		//2. 비지니스 로직 활용
		ServletContext sc = getServletContext();
		BookmarkService.envProp = sc.getRealPath(sc.getInitParameter("env"));
		
		service = BookmarkService.getInstance();
		
		
		String path ="";
		// 북마크 클릭시 북마크db에 추가 하는 메서드!!
		if(prod_size != null) {
			Bookmark bm = new Bookmark(0 ,c, p, Integer.parseInt(prod_size));
			System.out.println(bm.getBm_num());
		try {
			service.insert(bm);
			System.out.println("성공");
		} catch (AddException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 
			
	}

}
