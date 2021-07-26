package com.day.control;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.day.dto.Customer;
import com.day.dto.OrderInfo;
import com.day.dto.OrderLine;
import com.day.dto.Product;
import com.day.exception.AddException;
import com.day.exception.FindException;
import com.day.service.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

//@Controller
public class OrderController {
		
	@Autowired
	private OrderService service;
	
	@GetMapping("/addorder")
	@ResponseBody
	public String addOrder(HttpSession session, Model model) throws JsonProcessingException {
		ObjectMapper mapper;
		mapper = new ObjectMapper();
		String jsonStr ="";
		
		Map<String, Object> map = new HashMap<>();
		//로그인된 사용자만 주문가능
		Customer c = (Customer)session.getAttribute("loginInfo");
		if(c == null) {
			//로그인안된 사용자 status:0, 장바구니없음 status: -1, 추가실패:status: -2/ msg:실패이유, 
			//정상처리 : status:1
			map.put("status", 0);
		}else {
			//----로그인된 경우 테스트----
			//Customer c = new Customer();
			//c.setId("id1");
			//-----------------------
			//1.장바구니내용 
			Map<String, Integer> cart = (Map)session.getAttribute("cart");
			if(cart != null && cart.size() > 0) {
				//2.장바구니내용을 OrderInfo객체로 변환
				OrderInfo info = new OrderInfo();
				List<OrderLine> lines = new ArrayList<>();
				for(String prod_no: cart.keySet()) {
					int quantity = cart.get(prod_no);
					
					OrderLine line = new OrderLine(); //주문상세
					Product order_p = new Product(); 
					order_p.setProd_no(prod_no);
					line.setOrder_p(order_p);   //주문상품				
					line.setOrder_quantity(quantity);//주문수량
					lines.add(line);
				}		
				info.setLines(lines); //주문상세들			
				info.setOrder_c(c); //주문자
				//3.비지니스로직 호출
				try {
					service.add(info);//주문추가
					session.removeAttribute("cart");//장바구니 비우기
//					request.setAttribute("status", 1);
					map.put("status", 1);
				} catch (AddException e) { //추가실패인 겨우
					e.printStackTrace();
//					request.setAttribute("msg", e.getMessage());
//					request.setAttribute("status", -2);
					map.put("msg", e.getMessage());
					map.put("status", -2);
				}			
			}else { //장바구니가 비어있는 경우
//				request.setAttribute("status", -1);
				map.put("status", -1);
			}
		}
		jsonStr = mapper.writeValueAsString(map);
		System.out.println(jsonStr);
		return jsonStr;	
	}
	
	@GetMapping("/orderlist")
	@ResponseBody
	public Object orderlist(HttpSession session) {
		//1.세션에서 데이터 얻기
		Customer c = (Customer)session.getAttribute("loginInfo");
		if(c==null) {
			return 0;
		}else {
			try {
				System.out.println(c.getId());
				List<OrderInfo> infos = service.findById(c.getId());
				
				return infos;
			}catch(FindException e) {
				e.printStackTrace();
				return -1;
				
			}
		}
	}
}



