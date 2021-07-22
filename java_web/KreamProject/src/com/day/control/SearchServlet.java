package com.day.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.management.openmbean.SimpleType;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.day.dto.Bookmark;
import com.day.dto.Shop;
import com.day.exception.FindException;
import com.day.service.BookmarkService;
import com.day.service.ShopService;


public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 입력데이터수신  
		//wh++ modify 급하게 추가하게 되었습니다.!
		String[] brand_list = request.getParameterValues("brand");
		
		 
		//젠더 받아오기	
		
		//기본페이지로 돌아가기 (모두 해제시)
		String basic_page = request.getParameter("basic_page");
		System.out.println("basic:"+basic_page);
		 
		
		ServletContext sc = getServletContext();
		ShopService.envProp = sc.getRealPath(sc.getInitParameter("env"));
		BookmarkService.envProp = sc.getRealPath(sc.getInitParameter("env"));
		
		ShopService service_s;
		BookmarkService service_b;
		service_s = ShopService.getInstance();
		service_b = BookmarkService.getInstance();
	 	
		String id = "kms1234";
		String path = null;
		
		//wonho's modify++
		
		if(basic_page != null) {
			try {
				System.out.println(id);
				List<Shop> list_s = service_s.findAll(id);
				System.out.println("1번리스트"+list_s.size());
				List<Bookmark> list_b = service_b.findAll(id);
				System.out.println("1번리스트b"+list_b.size());
				request.setAttribute("shoplist", list_s);
				request.setAttribute("booklist", list_b);
				path = "/search.jsp";
				System.out.println("basic_null 리스트사이즈:"+list_s.size());
			}catch(FindException e) {
				e.printStackTrace();
				path = "/fail.jsp";
			}
		}
		else if(brand_list != null) {
			try {
				System.out.println(id);
				// wonho++ modify 변경 되었습니다. 꼭 확인 부탁합니다 . 76번라인
				List<Shop> list = service_s.findByList(id, brand_list);
				System.out.println("2번리스트"+list.size());
				List<Bookmark> list_b = service_b.findAll(id);
				request.setAttribute("shoplist", list);
				System.out.println("2번리스트b"+list_b.size());
				request.setAttribute("booklist", list_b);
				path= "/search.jsp";
			}catch(FindException e) {
				e.printStackTrace();
				path = "/fail.jsp";
			}
		}

		if(path!=null) {
		System.out.println("마지막path확인:"+path);
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
		}
	}
		
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
	}

}
