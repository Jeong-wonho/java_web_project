package com.slur.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.slur.dto.Page;
import com.slur.dto.Qa;
import com.slur.dto.Review;
import com.slur.dto.User;
import com.slur.exception.AddException;
import com.slur.exception.FindException;
import com.slur.exception.RemoveException;
import com.slur.service.QaService;

@RestController
@RequestMapping("/qa/*")
public class QaController {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private QaService service;
	
	@PostMapping("/write")
	public Map<String,Object> write(@RequestBody Qa qa, HttpSession session){
		Map<String, Object> result = new HashMap<>();
		try {
			log.error(qa.toString());
			User u = (User)session.getAttribute("loginInfo");
			log.error(u.getUser_id());
			qa.setUser_id(u);
			
			
			service.qaWrite(qa);
			
			result.put("status", 1);
		}catch (AddException e) {
			e.printStackTrace();
			result.put("status", 0);
			result.put("msg", e.getMessage());
		}
		return result;
	}
	
	@GetMapping(value= {"/", "/{pageno}"})
	public Map<String, Object> reviewList(@PathVariable(name="pageno") Optional<Integer> optInt){// Criteria cri){
		Map<String, Object> result = new HashMap<>();
		List<Qa> list = new ArrayList<Qa>();
		int totalListCount = 0;
		int pageNo = 1; //
		if(!optInt.isEmpty()) {
			//cri.setPageNum(optInt.get());
			pageNo = optInt.get();
		}
		int amount = 10;
		try {
			list = service.qaSelectAll(pageNo, amount);//cri); //페이지에 해당하는 목록
			totalListCount = service.qaAll().size();//총목록수
			result.put("status", 1); 
			result.put("qalist", list);
			result.put("pageMaker", new Page(pageNo, amount, totalListCount));
		}catch(FindException e) {
			e.printStackTrace();
			result.put("status", 0);
			result.put("msg", e.getMessage());
		}
		return result;
	}
	
	@GetMapping("/content/{qa_num}")
	public Map<String, Object> qaInfo(@PathVariable int qa_num){
		Map<String, Object> result = new HashMap<>();
		try {
			Qa value = service.qaSelectInfo(qa_num);
			result.put("status", 1);
			result.put("list", value);
		}catch(FindException e) {
			e.printStackTrace();
			result.put("status", 0);
			result.put("msg", e.getMessage());
		}
		return result;
	}
	
	@PutMapping("/{qa_num}")
	public Map<String, Object> qaModify(@PathVariable(name="qa_num") int qa_num, @RequestBody Qa qa, HttpSession session){
		Map<String, Object> result = new HashMap<>();
		log.error(qa.toString());
		User u = (User)session.getAttribute("loginInfo");
		log.error(u.getUser_id());
	
		qa.setUser_id(u);
		qa.setQa_num(qa_num);
		try {
			service.qaMoidfy(qa);
			result.put("status", 1);
		}catch (Exception e) {
			e.printStackTrace();
			result.put("status", 0);
			result.put("msg", e.getMessage());
		}
		return result;
	}
	
	@DeleteMapping("/{qa_num}")
	public Map<String, Object> qaRemove(@PathVariable int qa_num, HttpSession session){
		log.error(qa_num+"");
		Map<String, Object> result = new HashMap<>();
		// -->session의 loginInfo속성으로 차후 변경
		User u = (User)session.getAttribute("loginInfo");
		log.error(u.getUser_id());
		Qa qa = new Qa();
		qa.setUser_id(u);
		qa.setQa_num(qa_num);
		try {
			service.qaDelete(qa_num);
			result.put("status", 1);
		}catch(RemoveException e) {
			result.put("status", 0);
			result.put("msg", e.getMessage());
		}
		return result;
	}
	
	
	
}
