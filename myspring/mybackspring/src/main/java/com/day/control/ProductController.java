package com.day.control;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.day.dto.Product;
import com.day.exception.FindException;
import com.day.service.ProductService;


@Controller //@repository, @service, @controller은 모두 @component의 하위 어노테이션이다.
public class ProductController { 
		private Logger log  = Logger.getLogger(ProductController.class);
		
//		@GetMapping("/productinfo")
//		public void productInfo(HttpServletRequest request) {
//			String prod_no = request.getParameter("prod_no");
//			log.info("ProductController의 productInfo()입니다. prod_no="+prod_no);
//	}
//		public void productInfo(String no) {
//			log.info("ProductController의 productInfo()입니다. prod_no="+no);
//		}
//		public void productInfo(@RequestParam(name = "prod_no", required = false, defaultValue = "") String no) {
//			log.info("ProductController의 productInfo()입니다. prod_no="+no);
//		}
		
//		@GetMapping("/register")
//		public void add(String prod_no, String prod_name,
//				@RequestParam(required=false, defaultValue = "0") int prod_price) { //defaultValue는 문자열로 넘겨줘야한다.
//			log.info("add() 입니다 prod_no=" + prod_no + ", prod_name ="+prod_name+", prod_price="+prod_price);
//		}
//		public void add(Product p) { //defaultValue는 문자열로 넘겨줘야한다.
//			log.info("add() 입니다 prod_no=" + p.getProd_no() + ", prod_name ="+p.getProd_name()+", prod_price="+p.getProd_price());
//		}
		
		//스트링 배열형태로 값 받기!
//		public void add(String[] prod_no) {
//			log.info("add()입니다.");
//			for(String no:prod_no) {
//				log.info(no);
//			}
//		}
//		Rest API사용시 요청전달데이터를 JSON으로 처리할 수 있습니다. 그때 사용하겠습니다.
//		public void add(ArrayList<String> prod_no) {
//			log.info("add()입니다.");
//			for(String no:prod_no) {
//				log.info(no);
//			}
//		}
		
		@Autowired
		private ProductService service;
		
		@GetMapping("/productinfo")
//		public void productInfo(String prod_no, Model model) {
//			try {
//				Product p = service.findByNo(prod_no);
//				model.addAttribute("p", p);
//			} catch (FindException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		public ModelAndView productInfo(String prod_no) {
//			ModelAndView mnv = new ModelAndView();
//			try {
//				Product p = service.findByNo(prod_no);
//				mnv.setViewName("productinfo");
//				mnv.addObject("p", p);
//			} catch (FindException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//				mnv.setViewName("fail");
//			}
//			return mnv;
//		}
		
		public String productInfo(String prod_no, Model model) {
			String viewName;
			try {
				Product p = service.findByNo(prod_no);
				model.addAttribute("p", p);
				viewName = "productinfo";
			} catch (FindException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				viewName="fail";
			}
			return viewName;
			
		}
		
		@GetMapping("/productlist")
		public String productlist(Model model) {
			String viewName;
			try {
				List<Product> list = service.findAll();
				viewName = "productlist";
				model.addAttribute("productList", list);
				return "productlist";
			}catch(FindException e) {
				e.printStackTrace();
				model.addAttribute("msg", e.getMessage());
				return "fail";
			}
			
		}
		
		
}
