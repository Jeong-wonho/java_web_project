package com.day.control;

import java.io.IOException;
import java.util.List;

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

/**
 * Servlet implementation class IndexServlet
 */
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public IndexServlet() {
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.요청전달데이터얻기
			ShopService service_s;
			BookmarkService service_b;
			
			//2.비지니스로직호출
			ServletContext sc = getServletContext();
			ShopService.envProp = sc.getRealPath(sc.getInitParameter("env"));
			BookmarkService.envProp = sc.getRealPath(sc.getInitParameter("env"));
			
			service_s = ShopService.getInstance();
			service_b = BookmarkService.getInstance();
			
			String id = "";
			String brand = request.getParameter("brand");
			
			String path;
	 
			try {
				
				List<Shop> list_s = service_s.findAll(id);	
				List<Shop> list_ddt = service_s.findByReleaseddt(id);
				List<Bookmark> list_b = service_b.findAll(id);
				
	   
				request.setAttribute("shoplist", list_s);
				request.setAttribute("ddtlist", list_ddt);
				request.setAttribute("booklist", list_b);
				path = "/index.jsp";
				
			}catch(FindException e) {
				e.printStackTrace();
				path = "/fail.jsp";
			}

			
			RequestDispatcher rd = request.getRequestDispatcher(path);
			rd.forward(request, response);
		}

}
