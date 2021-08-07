		package com.day.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

import com.day.dto.Customer;
import com.day.dto.RepBoard;
import com.day.exception.AddException;
import com.day.exception.FindException;
import com.day.exception.RemoveException;
import com.day.service.RepBoardService;

@RestController
@RequestMapping("/board/*")
public class RepBoardController {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private RepBoardService service;

	@PostMapping("/write")
	public Map<String, Object> write(@RequestBody RepBoard repBoard) {
		Map<String, Object> result = new HashMap<>();
		try {
			log.error(repBoard.toString());
			Customer boardC = new Customer();
			boardC.setId("id1");
			System.out.println(repBoard.getBoardContent());
			
			repBoard.setBoardC(boardC);
			
			service.write(repBoard);
			
			result.put("status", 1);
		} catch (AddException e) {
			e.printStackTrace();
			result.put("status", 0);// 실패
			result.put("msg", e.getMessage());
		}
		return result;
	}

	@PostMapping("/reply/{no}")
	public Map<String, Object> reply(@PathVariable int no, @RequestBody RepBoard repBoard) {
		Map<String, Object> result = new HashMap<>();
		try {
			Customer boardC = new Customer();
			boardC.setId("id1");
			repBoard.setBoardC(boardC);
			repBoard.setParentNo(no);
			repBoard.setLevel(1);
			service.reply(repBoard);
			result.put("status", 1);
		} catch (AddException e) {
			e.printStackTrace();
			result.put("status", 0);// 실패
			result.put("msg", e.getMessage());
		}
		return result;
	}

	@GetMapping(value = { "/list", "/list/{word}" })
	public Map<String, Object> list(@PathVariable(name = "word") Optional<String> optWord) {
		Map<String, Object> result = new HashMap<>();
		List<RepBoard> list = new ArrayList<RepBoard>();
		try {
			if (optWord.isPresent()) {
				list = service.list(optWord.get());
			} else {
				list = service.list();
			}
			result.put("status", 1);
			result.put("list", list);
		} catch (FindException e) {
			result.put("status", 0);
			result.put("msg", e.getMessage());
		}
		return result;
	}

	@GetMapping("/{no}")
	public Map<String, Object> info(@PathVariable int no) {
		Map<String, Object> result = new HashMap<>();
		try {
			RepBoard value = service.findByNo(no);
			result.put("status", 1);
			result.put("repboard", value);
		} catch (FindException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.put("status", 0);
			result.put("msg", e.getMessage());
		}
		return result;
	}

	@PutMapping("/{no}")
	public Map<String, Object> modify(@PathVariable(name = "no") int boardNo, @RequestBody RepBoard repBoard) {
		Map<String, Object> result = new HashMap<>();
		// -->session의 loginInfo속성으로 차후 변경
		Customer boardC = new Customer();
		boardC.setId("id1");
		//
		repBoard.setBoardC(boardC);
		repBoard.setBoardNo(boardNo);
		System.out.println(repBoard.getBoardC());
		try {
			service.modify(repBoard);
			result.put("status", 1);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("status", 0);
			result.put("msg", e.getMessage());
		}
		return result;
	}

	@DeleteMapping("/{no}")
	public Map<String, Object> remove(@PathVariable int no) {
		
		log.error(no+"");
		Map<String, Object> result = new HashMap<>();
		// -->session의 loginInfo속성으로 차후 변경
		Customer boardC = new Customer();
		boardC.setId("id1");
		RepBoard repBoard = new RepBoard();
		repBoard.setBoardNo(no);
		repBoard.setBoardC(boardC);
		try {
			service.remove(repBoard);
			result.put("status", 1);
		} catch (RemoveException e) {
			result.put("status", 0);
			result.put("msg", e.getMessage());
		}
		return result;
		
	}
}
