package com.day.control;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.day.dto.Customer;
import com.day.exception.FindException;
import com.day.service.CustomerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class CustomerController {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CustomerService service;
		
	
	@RequestMapping(value="/login")
	@ResponseBody
	public Object login(@RequestBody Customer c, HttpSession session) throws IOException {
		
		ObjectMapper mapper;
		mapper = new ObjectMapper();
		String jsonStr ="";
//
		session.removeAttribute("loginInfo");
		Map<String, Object>map = new HashMap<>();
		try {
			Customer loginInfo = service.login(c.getId(), c.getPwd());
			//로그인정보를 세션에 추가			
			session.setAttribute("loginInfo", loginInfo);			
			map.put("status", 1);
		} catch (FindException e) {
			e.printStackTrace();
			map.put("status", -1);
			map.put("msg", e.getMessage());
		}
		jsonStr = mapper.writeValueAsString(map);
		System.out.println(jsonStr);
		return map;
	}
	
	@GetMapping("/logout")
	@ResponseBody
	public void logout(HttpSession session)
			{
				session.invalidate(); //세션제거			
	}
	
	@GetMapping("/iddupchk")
	@ResponseBody
	public String idDupChk(String id) {
		try {	
		Customer c = service.findById(id);
		if (c!=null) {
		return "0";
		}else {
			return "1";
		}
		}catch(FindException e) {
		System.out.println("넘어와");
		return "1";
			
		}
	}
	
	@PostMapping("/signup")
	@ResponseBody
	public String signup(Customer c) {
		log.info(c.toString());
		try {
			service.signup(c);
			return "1";
		}catch(Exception e) {
			e.printStackTrace();
			return "0";
		}
	}
}
