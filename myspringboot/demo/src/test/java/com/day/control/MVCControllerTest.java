package com.day.control;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.ContentResultMatchers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(MVCController.class)
class MVCControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	@Test
	void test() throws Exception {
		MockHttpServletRequestBuilder requestBuilder;
		requestBuilder = MockMvcRequestBuilders.get("/a");
		requestBuilder.param("id", "id1"); //요청시 전달할 데이터
		ResultActions resultActions = mockMvc.perform(requestBuilder); //요청테스트
		
		ResultMatcher matcher = MockMvcResultMatchers.status().isOk(); // 예상되는 응답상태코드 값은 200
		resultActions.andExpect(matcher); //테스트
		
//		String expectedURL = "/WEB-INF/jsp/a.jsp";
//		ResultMatcher matcher1 = MockMvcResultMatchers.forwardedUrl(expectedURL);
//		resultActions.andExpect(matcher1);//테스트
		
		String expectedViewNmae="a";
		ResultMatcher matcher1 = MockMvcResultMatchers.view().name(expectedViewNmae);
		resultActions.andExpect(matcher1);//테스트
		
		
	}

}
