package com.day.control;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@WebMvcTest(ProductController.class)
class ProductControllerTest {
	@Autowired
	MockMvc mockMvc;
	
	@Test
	void testProductinfoSuccess()throws Exception {
		MockHttpServletRequestBuilder requestBuilder;
		requestBuilder = MockMvcRequestBuilders.get("/productinfo");
		requestBuilder.param("prod_no", "C0001"); //요청시 전달할 데이터
		ResultActions resultActions = mockMvc.perform(requestBuilder); //요청테스트
		
		ResultMatcher matcher = MockMvcResultMatchers.status().isOk(); // 예상되는 응답상태코드 값은 200
		resultActions.andExpect(matcher); //테스트
		
		String expectedViewName="productinfo";
		ResultMatcher matcher1 = MockMvcResultMatchers.view().name(expectedViewName);
		resultActions.andExpect(matcher1);//테스트
	}
	
	@Test
	void testProductinfoFail()throws Exception {
		MockHttpServletRequestBuilder requestBuilder;
		requestBuilder = MockMvcRequestBuilders.get("/productinfo");
		requestBuilder.param("prod_no", "xxxx"); //요청시 전달할 데이터
		ResultActions resultActions = mockMvc.perform(requestBuilder); //요청테스트
		
		ResultMatcher matcher = MockMvcResultMatchers.status().isOk(); // 예상되는 응답상태코드 값은 200
		resultActions.andExpect(matcher); //테스트
		
		String expectedViewName="fail";
		ResultMatcher matcher1 = MockMvcResultMatchers.view().name(expectedViewName);
		resultActions.andExpect(matcher1);//테스트
	}
	

}
