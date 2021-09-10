package com.slur.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.slur.dto.Notice;
import com.slur.dto.Page;
import com.slur.dto.Review;
import com.slur.exception.FindException;
import com.slur.service.NoticeService;

@RestController
@RequestMapping("/notice/*")
public class NoticeController {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private NoticeService service;
	
	@GetMapping(value = {"/{pageno}"})
	public Map<String, Object> noticeList(@PathVariable(name="pageno") Optional<Integer> optInt){
		Map<String, Object> result = new HashMap<>();
		List<Notice> list = new ArrayList<Notice>();
		int totalListCount = 0;
		int pageNo = 1; //
		if(!optInt.isEmpty()) {
			//cri.setPageNum(optInt.get());
			pageNo = optInt.get();
		}
		int amount = 10;
		try {
			list = service.reviewAll(pageNo, amount);//cri); //페이지에 해당하는 목록
			totalListCount = service.reviewAll().size();//총목록수
			result.put("status", 1); 
			result.put("list", list);
			result.put("pageMaker", new Page(pageNo, amount, totalListCount));
		}catch(FindException e) {
			e.printStackTrace();
			result.put("status", 0);
			result.put("msg", e.getMessage());
		}
		return result;
	}
	
	@GetMapping(value={"/content","/content/{notice_num}"})
	public Map<String, Object> reviewInfo(@PathVariable int notice_num){
		Map<String, Object> result = new HashMap<>();
		try {
			Notice value = service.reviewInfo(notice_num);
			result.put("status", 1);
			result.put("notice", value);
		}catch(FindException e) {
			e.printStackTrace();
			result.put("status", 0);
			result.put("msg", e.getMessage());
		}
		return result;
	}
	
	
}
