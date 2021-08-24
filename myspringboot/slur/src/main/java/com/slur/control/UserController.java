package com.slur.control;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.slur.dto.User;
import com.slur.exception.FindException;
import com.slur.service.UserService;

@RestController
public class UserController {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private UserService service;
	
	@RequestMapping(value="/login")
	@ResponseBody
	public Object login(@RequestBody User u, HttpSession session) throws IOException{
		
		ObjectMapper mapper;
		mapper = new ObjectMapper();
		String jsonStr = "";
		
		log.error(u.toString());
		
		session.removeAttribute("loginInfo");
		Map<String, Object> map = new HashMap<>();
		
		try {
			User loginInfo = service.login(u.getUser_id(), u.getUser_pwd());
			session.setAttribute("loginInfo", loginInfo);
			map.put("status", 1);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			map.put("satus", -1);
			map.put("msg",  e.getMessage());
		}
		jsonStr = mapper.writeValueAsString(map);
		log.warn(jsonStr);
		return map;
	}
	
	@GetMapping("/logout")
	@ResponseBody
	public void logout(HttpSession session) {
		session.invalidate();
	}
	
	@GetMapping("/iddupchk")
	@ResponseBody
	public String idDupcchk(String user_id) {
		try {
			User u = service.findById(user_id);
			if (u!=null) {
				return "0";
			}else {
				return "1";
			}
		} catch (FindException e) {
			// TODO: handle exception
			return "1";
		}
	}
	
	@PostMapping("/signup")
	@ResponseBody
	public String signup(User u) {
		log.info(u.toString());
		try {
			service.signup(u);
			return "1";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "0";
		}
	}
}
