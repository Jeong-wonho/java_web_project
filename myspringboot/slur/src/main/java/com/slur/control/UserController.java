package com.slur.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.slur.dto.Program;
import com.slur.dto.Role;
import com.slur.dto.User;
import com.slur.exception.FindException;
import com.slur.service.UserService;

@RestController
public class UserController {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private UserService service;
	
	@PostMapping(value="/login")
	@ResponseBody
	public Map<String, Object> login(@RequestBody User u, 
			HttpSession session) throws IOException{
		
		//ObjectMapper mapper;
		//mapper = new ObjectMapper();
//		User u = new User();
//		u.setUser_id("id1"); u.setUser_pwd("pwd1");
		log.error(u.toString());
		
		session.removeAttribute("loginInfo");
		Map<String, Object> map = new HashMap<>();
		
		try {
			User loginInfo = service.login(u.getUser_id(), u.getUser_pwd());
			session.setAttribute("loginInfo", loginInfo);
			User user = (User)session.getAttribute("loginInfo");
			map.put("status", 1);
			map.put("User", user);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			map.put("satus", -1);
			map.put("msg",  e.getMessage());
		}
//		jsonStr = mapper.writeValueAsString(map);
//		log.warn(jsonStr);
		return map;
	}
	
	@GetMapping("/checklogined")
	@ResponseBody
	public Object loginchk(HttpSession session) {
		Map<String, Object> map = new HashMap<>();
		User loginInfo = (User)session.getAttribute("loginInfo");
		//로그인 정보를 세션에 추가
		if(loginInfo != null) {
		session.setAttribute("loginInfo", loginInfo);
		map.put("status",1);
		//3. 성공
		}else {
			map.put("status",0);
		}
		return map;
	}
	
	@GetMapping("/myinfo")
	@ResponseBody
	public Object myInfochk(HttpSession session) {
		User loginInfo = (User)session.getAttribute("loginInfo");
		//로그인 정보를 세션에 추가
		return loginInfo;
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
	
	@PostMapping("/program_role")
	@ResponseBody
	public Map<String, Object> programList(HttpSession session){
		User u = (User)session.getAttribute("loginInfo");
		Map<String, Object> result = new HashMap<>();
		List<Role> list = new ArrayList<Role>();
		try {
			list = service.findByRole(u.getUser_id());
			result.put("status", 1);
			result.put("list", list);
		}catch(FindException e) {
			e.printStackTrace();
			result.put("status", 0);
			result.put("msg", e.getMessage());
		}
		return result;
	}
	@PutMapping("/modify")
	@ResponseBody
	public String userModify(User u) {
		log.info(u.toString());
		try {
			service.modify(u);
			return "1";
		}catch(Exception e) {
			e.printStackTrace();
			return "0";
		}
	}
	
	
}
