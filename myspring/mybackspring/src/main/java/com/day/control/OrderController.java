package com.day.control;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.day.dto.Customer;
import com.day.dto.OrderInfo;
import com.day.dto.OrderLine;
import com.day.dto.Product;
import com.day.exception.AddException;
import com.day.exception.FindException;
import com.day.service.OrderService;

@Controller
public class OrderController {

	private Logger log = Logger.getLogger(OrderController.class);
	@Autowired
	private OrderService service;

	@GetMapping("/addorder")
	public void addorder(HttpSession session, Model model) {
		Customer c = (Customer) session.getAttribute("loginInfo");
		if (c == null) {
			model.addAttribute("status", "0");
		} else {
			Map<String, Integer> cart = (Map) session.getAttribute("cart");
			System.out.println(cart);
			if (cart != null && cart.size() > 0) {
				// 2.장바구니내용을 OrderInfo객체로 변환
				OrderInfo info = new OrderInfo();
				List<OrderLine> lines = new ArrayList<>();
				for (String prod_no : cart.keySet()) {
					int quantity = cart.get(prod_no);

					OrderLine line = new OrderLine(); // 주문상세
					Product order_p = new Product();
					order_p.setProd_no(prod_no);
					line.setOrder_p(order_p); // 주문상품
					line.setOrder_quantity(quantity);// 주문수량
					lines.add(line);
				}
				info.setLines(lines); // 주문상세들
				info.setOrder_c(c); // 주문자
				// 3.비지니스로직 호출
				try {
					service.add(info);// 주문추가
					session.removeAttribute("cart");;// 장바구니 비우기
					model.addAttribute("status", 1);
				} catch (AddException e) { // 추가실패인 겨우
					e.printStackTrace();
					model.addAttribute("msg", e.getMessage());
					model.addAttribute("status", -2);
				}
			} else { // 장바구니가 비어있는 경우
				model.addAttribute("status", -1);
			}
		}
	}
	
	@GetMapping("/orderlist")
	public void orderlist(HttpSession session, Model model) {
		//1.세션에서 데이터 얻기
		Customer c = (Customer)session.getAttribute("loginInfo");
		if(c==null) {
			model.addAttribute("status", "0");
		}else {
			try {
				System.out.println(c.getId());
				List<OrderInfo> infos = service.findById(c.getId());
				
				model.addAttribute("infos", infos);
			}catch(FindException e) {
				e.printStackTrace();
				model.addAttribute("status", -1); // 주문목록이 없는 경우
				model.addAttribute("msg", e.getMessage());
			}
		}
	}
	
}
