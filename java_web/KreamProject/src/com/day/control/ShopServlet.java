package com.day.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.day.dto.Bookmark;
import com.day.dto.Customer;
import com.day.dto.Product;
import com.day.dto.Shop;
import com.day.exception.FindException;
import com.day.service.BookmarkService;
import com.day.service.ProductService;
import com.day.service.ShopService;

/**
 * Servlet implementation class ShopServlet
 */
public class ShopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShopServlet() {
        super();
        // TODO Auto-generated constructor stub
        System.out.println("shopservlet 객체 생성 완룐");
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//    	1.입력한 데이터 수신(로그인 정보 수신)
//		HttpSession session = request.getSession();
//		Customer c = (customer)session.getAttribute("loginInfo");
		
//		2.로그인정보수신 두번째
//		String id = request.getParameter("id");
		
		// TODO Auto-generated method stub
		//1.요청전달데이터얻기
		ShopService service_s;
		BookmarkService service_b;
		ProductService service_p;
		//2.비지니스로직호출
		ServletContext sc = getServletContext();
		ShopService.envProp = sc.getRealPath(sc.getInitParameter("env"));
		BookmarkService.envProp = sc.getRealPath(sc.getInitParameter("env"));
		ProductService.envProp = sc.getRealPath(sc.getInitParameter("env"));
		
		service_s = ShopService.getInstance();
		service_b = BookmarkService.getInstance();
		service_p = ProductService.getInstance();
		
		String id = "kms1234";
		String brand = request.getParameter("brand");
		
		String path;
 
		try {
		
			List<Shop> list_s = service_s.findAll(id);
		
			List<Product> list_p =  service_p.findBy();
			List<Bookmark> list_b = service_b.findAll(id);
			
   
			request.setAttribute("shoplist", list_s);
			request.setAttribute("brandlist", list_p);
			request.setAttribute("booklist", list_b);
			path = "/shop.jsp";
			
		}catch(FindException e) {
			e.printStackTrace();
			path = "/fail.jsp";
		}

		
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
