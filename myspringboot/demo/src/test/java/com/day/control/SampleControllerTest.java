package com.day.control;

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

@WebMvcTest(SampleController.class)
class SampleControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	void test() throws Exception{
		MockHttpServletRequestBuilder requestBuilder;
		requestBuilder = MockMvcRequestBuilders.get("/hello");
		requestBuilder.accept(MediaType.APPLICATION_JSON); //요청형식
		ResultActions resultActions = mockMvc.perform(requestBuilder); //요청테스트
		
		ResultMatcher matcher = MockMvcResultMatchers.status().isOk(); // 예상되는 응답상태코드 값은 200
		resultActions.andExpect(matcher); //테스트
		
		ContentResultMatchers contentMatchers = MockMvcResultMatchers.content();
		String expectedContent = "hi"; //예상되는 응답내용은 "hi"
		ResultMatcher matcher1 = contentMatchers.string(expectedContent);
		resultActions.andExpect(matcher1); //테스트
		
	}
	
	@Test
	void testAdd() throws Exception {
		MockHttpServletRequestBuilder requestBuilder;
		requestBuilder = MockMvcRequestBuilders.post("/add");
		requestBuilder.accept(MediaType.APPLICATION_JSON); //요청형식 "application/json"
		requestBuilder.contentType(MediaType.APPLICATION_JSON); //요청형식:contentType
		requestBuilder.content("{\"id\":\"id1\"}"); //요청할 데이터
		ResultActions resultActions = mockMvc.perform(requestBuilder); //요청테스트
		
		ResultMatcher matcher = MockMvcResultMatchers.status().isOk(); // 예상되는 응답상태코드 값은 200
		resultActions.andExpect(matcher); //테스트
		
		ContentResultMatchers contentMatchers = MockMvcResultMatchers.content();
		String expectedContent = "id1"; //예상되는 응답내용은 "hi"
		ResultMatcher matcher1 = contentMatchers.string(expectedContent);
		resultActions.andExpect(matcher1); //테스트
		
	}
	

}
