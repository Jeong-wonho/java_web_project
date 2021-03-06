package com.day.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.day.dto.Product;
import com.day.exception.FindException;
import com.day.service.ProductService;

//@Controller
public class CartController {
	@Autowired
	private ProductService service;
	
	@GetMapping("/putcart")
	@ResponseBody
	public void putCart(String prod_no, int quantity, HttpSession session) {
		Map<String, Integer> cart = (Map)session.getAttribute("cart");
		if(cart == null) {
			cart = new HashMap<>();
			session.setAttribute("cart", cart);
		}
		Integer oldQuantity = (Integer)cart.get(prod_no);
		if(oldQuantity != null) {
			quantity += oldQuantity;
		}
		cart.put(prod_no, quantity);
	}
	
//	public ResponseEntity<String> putCart(String prod_no, int quantity, HttpSession session) {
//		Map<String, Integer> cart = (Map)session.getAttribute("cart");
//		if(cart == null) {
//			cart = new HashMap<>();
//			session.setAttribute("cart", cart);
//		}
//		Integer oldQuantity = (Integer)cart.get(prod_no);
//		if(oldQuantity != null) {
//			quantity += oldQuantity;
//		}
//		cart.put(prod_no, quantity);
//		
//		ResponseEntity<String> entity = 
//				 new ResponseEntity<String>(HttpStatus.OK); //응답코드 200
////				new ResponseEntity<String>(HttpStatus.BAD_GATEWAY);
//		return entity;
//	}
	
	
	@GetMapping("/viewcart")
	@ResponseBody
	public List<Map<String, Object>> viewCart(HttpSession session) {
		Map<String, Integer>cart = (Map)session.getAttribute("cart");
	      
	      List<Map<String, Object>>result = new ArrayList<>();
	      //장바구니가 있다면
	      if(cart != null && cart.size() > 0) {
	         //상품번호에 해당하는 상품정보찾기
	         Set<String>prod_nos = cart.keySet();
	         for(String prod_no: prod_nos) {
	            Product p;
	            try {
	               p = service.findByNo(prod_no);
	               Map map = new HashMap<>();
	               map.put("product", p);
	               map.put("quantity", cart.get(prod_no));
	               result.add(map);
	            } catch (FindException e) {
	               e.printStackTrace();
	            }
	         }
	      }
		return result;
	     
	}
		
}

