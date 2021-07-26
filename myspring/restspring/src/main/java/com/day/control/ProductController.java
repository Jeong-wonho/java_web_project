package com.day.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.day.dto.Product;
import com.day.exception.FindException;
import com.day.service.ProductService;


//@Controller //@repository, @service, @controller은 모두 @component의 하위 어노테이션이다.
public class ProductController { 
		private Logger log  = Logger.getLogger(ProductController.class);
				
		@Autowired
		private ProductService service;
		
		@GetMapping("/productinfo")
		@ResponseBody //응답바디에 직접 쓰기하려면 @ResponseBody 어노테이션이 필요하다.
//		public String productInfo(String prod_no) {
//			String jsonStr;
//			try {
//				Product p = service.findByNo(prod_no);
//				jsonStr =  "{\"msg\":\"success\"}";
//			} catch (FindException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//				jsonStr = "{\"msg\":\"fail\"}";
//			}
//			return jsonStr;
//			
//		}

//		public Product productInfo(String prod_no) {
//			try {
//				Product p = service.findByNo(prod_no);
//				return p; //Product 타입의 객체의 toString() 호출되어 응답 바디에 쓰기
//			} catch (Exception e) {
//				// TODO: handle exception
//				e.printStackTrace();
//				return null;
//			}
//		}
		
//		public Map<String, Object> productInfo(String prod_no){
//			HashMap<String, Object> map = new HashMap<>();
//			try {
//				Product p = service.findByNo(prod_no);
//				map.put("p", p);
//				
//			} catch (FindException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//				map.put("status", -1);
//				map.put("msg", e.getMessage());
//			}
//			return map; //map객체의 내용을 json 객체 타입의 문자열로 변환 응답바디에 쓰기된다.
//		}
		public Object productInfo(String prod_no) {
			HashMap<String, Object>map = new HashMap<>();
			try {
				Product p = service.findByNo(prod_no);
				return p;
			} catch (FindException e) {
				e.printStackTrace();
				map.put("status", -1);
				map.put("msg", e.getMessage());
				return map;//Map객체의내용을 json객체타입의 문자열로 변환후 응답바디에 쓰기된다
			}		 
		}

		
//		@GetMapping("/productlist")
//		public String productlist(Model model) {
//			String viewName;
//			try {
//				List<Product> list = service.findAll();
//				viewName = "productlist";
//				model.addAttribute("productList", list);
//				return "productlist";
//			}catch(FindException e) {
//				e.printStackTrace();
//				model.addAttribute("msg", e.getMessage());
//				return "fail";
//			}
//			
//		}
		
		@GetMapping("/productlist")
		@ResponseBody //중요!
		public List<Product> productList(){
			List<Product> list;
			try {
				list = service.findAll();
				return list;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
			
		}
		
		
}
