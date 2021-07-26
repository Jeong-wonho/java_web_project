package com.day.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@org.springframework.web.bind.annotation.RestController // RestApi 중요!
/*
 * @Controller
 * 
 * @ResponseBody 두개를 합친 효과 json형태로 결과를 응답한다.
 * 
 * Restful용 요청방식 종류 Get - 검색 Post - 추가 "/board" Put - 수정 Delete - 삭제
 */
public class RestController {
	private Logger log = Logger.getLogger(RestController.class);

	@PostMapping("/board")
	public Map<String, Object> write(@RequestBody // 요청 전달데이터가 application/json타입으로 전달됨
	Map<String, String> map) {
		log.error("/board 추가 시작");
		log.error("map:" + map);

		Map<String, Object> result = new HashMap<>();
		result.put("status", 1);
		result.put("msg", "게시글 추가 성공");
		return result;
	}
	
	
/*	@PostMapping(value = "/board/reply/{no}", produces = {MediaType.TEXT_PLAIN_VALUE})//produces 응답형식!
	public ResponseEntity reply(@PathVariable int no,
								@RequestBody Map<String,String>map) {
		//service ~> dao
		log.error(map);
		ResponseEntity<String> responseEntity = 
					//new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);//실패
					//new ResponseEntity<>("답글쓰기 실패", HttpStatus.INTERNAL_SERVER_ERROR);
		log.error(responseEntity.getBody());
		return responseEntity;
	}
*/
	@PostMapping(value = "/board/reply/{no}")
	public ResponseEntity<Map<String, Object>> reply(@PathVariable int no,
			@RequestBody Map<String,String>map) {
		Map<String, Object> result = new HashMap<>();
		result.put("msg", "답글 쓰기 실패");
		
		ResponseEntity<Map<String, Object>> responseEntity = 
				//new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);//실패
				new ResponseEntity<>(result, HttpStatus.OK);
		return responseEntity;
	}
	
//	@GetMapping("/board/list")
	// public List<Map<String, Object>> list(){
	// public List<Map<String, Object>> list(@PathVariable(required = false) String
	// word){
	@GetMapping(value = { "/board/list", "/board/list/{word}" }) // 한번에 두개의 맵핑 처리하기
	public List<Map<String, Object>> list(@PathVariable(name = "word") // OPtional변수를 사용하면 required 사용 안해도된다.
	Optional<String> optWord) {
		List<Map<String, Object>> resultList = new ArrayList<>();

		log.error("/board 검색 시작" + optWord);
		String word = null;
		if (optWord.isPresent()) { // ispresent 값이 있으면 true 없으면 false반환 if (word != null){}(x)
			Map<String, Object> result = new HashMap<>();
			word = optWord.get();
			log.error("/board 단어검색 시작: word=" + word);

			result.put("no", 1);
			result.put("title", "제목1");
			result.put("content", "내용");
			resultList.add(result);

			result = new HashMap<>();
			result.put("no", 2);
			result.put("title", "제목");
			result.put("content", "내용1");
			resultList.add(result);
		} else {
			Map<String, Object> result = new HashMap<>();
			log.error("/board 전체검색 시작");
			result.put("no", 1);
			result.put("title", "제목1");
			result.put("content", "내용1");
			resultList.add(result);

			result = new HashMap<>();
			result.put("no", 2);
			result.put("title", "제목2");
			result.put("content", "내용2");
			resultList.add(result);

			result = new HashMap<>();
			result.put("no", 3);
			result.put("title", "제목3");
			result.put("content", "내용3");
			resultList.add(result);
		}

		return resultList;
	}

	// http://localhost:8888/restspring/board?no=123
	// public void info(@RequestParam(name="no")int board_no){}
	@GetMapping("/board/{no}") // http://localhost:8888/restspring/board/123
								// http://localhost:8888/restspring/board/1
	public Map<String, Object> info(@PathVariable int no) {
		// pathvariable{no} parameter 의 값은 동일해야한다.
		// service~>dao
		Map<String, Object> result = new HashMap<>();
		result.put("no", no);
		result.put("title", "제목1");
		result.put("content", "내용1");
		return result;
	}

	@PutMapping("/board/{no}")
	/*
	 * public void modify(@PathVariable int no,
	 * 
	 * @RequestBody Map<String, String> map ) { //service~>dao log.error(map);
	 * 
	 * }
	 */
	public ResponseEntity<String> modify(@PathVariable int no, 
										 @RequestBody Map<String, String> map) {
		//service~>dao
		log.error(map);
		ResponseEntity<String> responseEntity = new ResponseEntity<>(HttpStatus.OK);
																	//HttpStatus.INTERNAL_SERVER_ERROR
		return responseEntity;
	}
	
	@DeleteMapping("/board/{no}")
	public ResponseEntity<String> remove(@PathVariable int no){
		//service~>dao
		ResponseEntity<String> responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		return responseEntity;
	}
}
