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

import com.slur.dto.Criteria;
import com.slur.dto.Page;
import com.slur.dto.Program;
import com.slur.dto.Review;
import com.slur.dto.User;
import com.slur.exception.AddException;
import com.slur.exception.FindException;
import com.slur.exception.RemoveException;
import com.slur.service.ProgramService;

@RestController
@RequestMapping("/program/*")
public class ProgramController {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ProgramService service;
	
	@PostMapping("/write")
	public Map<String,Object> write(@RequestBody Review review, HttpSession session){
		Map<String, Object> result = new HashMap<>();
		try {
			log.error(review.toString());
			User u = (User)session.getAttribute("loginInfo");
			log.error(u.getUser_id());
			review.setReview_user_id(u);
			
			service.reviewWrite(review);
			
			result.put("status", 1);
		}catch (AddException e) {
			e.printStackTrace();
			result.put("status", 0);
			result.put("msg", e.getMessage());
		}
		return result;
		
	}
	
	@GetMapping(value= {"/find","/find/{word}"})
	public Map<String, Object> reviewFindList(@PathVariable(name="word") Optional<String> optWord){
		Map<String, Object> result = new HashMap<>();
		List<Review> list = new ArrayList<Review>();
		
		try {
			list = service.reviewWord(optWord.get());
			result.put("status", 1);
			result.put("list", list);
		}catch(FindException e) {
			e.printStackTrace();
			result.put("status", 0);
			result.put("msg", e.getMessage());
		}
		return result;
	}
	
	@GetMapping(value= {"/review","/review/{pageno}"})
	public Map<String, Object> reviewList(@PathVariable(name="pageno") Optional<Integer> optInt){// Criteria cri){
		Map<String, Object> result = new HashMap<>();
		List<Review> list = new ArrayList<Review>();
		int totalListCount = 0;
		int pageNo = 1; //
		if(!optInt.isEmpty()) {
			//cri.setPageNum(optInt.get());
			pageNo = optInt.get();
		}
		int amount = 10;
		try {
			list = service.getReviewPaging(pageNo, amount);//cri); //페이지에 해당하는 목록
			totalListCount = service.reviewAll().size();//총목록수
			result.put("status", 1); 
			result.put("crilist", list);
			result.put("pageMaker", new Page(pageNo, amount, totalListCount));
		}catch(FindException e) {
			e.printStackTrace();
			result.put("status", 0);
			result.put("msg", e.getMessage());
		}
		return result;
	}
	
	@GetMapping("/{review_num}")
	public Map<String, Object> reviewInfo(@PathVariable int review_num){
		Map<String, Object> result = new HashMap<>();
		try {
			Review value = service.reviewInfo(review_num);
			result.put("status", 1);
			result.put("review", value);
		}catch(FindException e) {
			e.printStackTrace();
			result.put("status", 0);
			result.put("msg", e.getMessage());
		}
		return result;
	}
	
	@GetMapping(value={"/times","/times/{program_times}"})
	public Map<String, Object> programList(@PathVariable(name="program_times") Optional<String> optWord){
		Map<String, Object> result = new HashMap<>();
		List<Program> list = new ArrayList<Program>();
		try {
			if(optWord.isPresent()) {
				list = service.programReview(optWord.get());
			}
			result.put("status", 1);
			result.put("list", list);
		}catch(FindException e) {
			e.printStackTrace();
			result.put("status", 0);
			result.put("msg", e.getMessage());
		}
		return result;
	}

	@PutMapping("/{review_num}")
	public Map<String, Object> reviewModify(@PathVariable(name="review_num") int review_num, @RequestBody Review review){
		Map<String, Object> result = new HashMap<>();
		log.error(review.toString());
		User u = new User(); // user_id 값 session에서 받아오기!
		u.setUser_id("id1");
		//
		review.setReview_user_id(u);
		review.setReview_num(review_num);
		try {
			service.reviewModify(review);
			result.put("status", 1);
		}catch (Exception e) {
			e.printStackTrace();
			result.put("status", 0);
			result.put("msg", e.getMessage());
		}
		return result;
	}
	@DeleteMapping("/{review_num}")
	public Map<String, Object> reviewRemove(@PathVariable int review_num, HttpSession session){
		log.error(review_num+"");
		Map<String, Object> result = new HashMap<>();
		// -->session의 loginInfo속성으로 차후 변경
		User u = (User)session.getAttribute("loginInfo");
		log.error(u.getUser_id());
		Review review = new Review();
		review.setReview_user_id(u);
		review.setReview_num(review_num);
		try {
			service.reviewDelete(review);
			result.put("status", 1);
		}catch(RemoveException e) {
			result.put("status", 0);
			result.put("msg", e.getMessage());
		}
		return result;
	}
	
	
	
}
