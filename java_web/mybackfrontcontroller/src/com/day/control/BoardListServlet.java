package com.day.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.day.dto.Board;
import com.day.dto.PageBean;


public class BoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "boardlist";
		String cp = request.getParameter("currentPage");
		int currentPage =1;
		if(cp != null && !cp.equals("")) {
			currentPage = Integer.parseInt(cp);
		}
		List<Board> list = new ArrayList<>();
		//list = service.findByPage(currentPage, PageBean.CNT_PER_PAGE);
		int totalCnt = 1; //service.findTotalCnt(); //총건수
		int totalPage = 1; //titalCnt 와 PageBean.CNT_PER_PAGE;
		PageBean<Board> pb = new PageBean<Board>(currentPage, totalPage, list, url);
	}

}
