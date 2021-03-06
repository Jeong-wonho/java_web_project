package com.day.control;

import static org.junit.jupiter.api.Assertions.fail;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

//@WebMvcTest(RepBoardController.class) //Controller내에서 사용하는 @Service 빈, @Respository 빈객체생성주입불가 껍대기만 테스트 하는 용도
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK) // 모의객체에 컨트롤러 주입해서 테스트
@AutoConfigureMockMvc
class RepBoardControllerTest {
	@Autowired
	MockMvc mockMvc;
	@Test
	@DisplayName("상세게시물 검색")
	void test() {
		MockHttpServletRequestBuilder requestBuilder;
		String uri = "/board/1";
		requestBuilder = MockMvcRequestBuilders.get(uri);
		try {
			ResultActions resultActions = mockMvc.perform(requestBuilder);
			ResultMatcher ok = MockMvcResultMatchers.status().isOk();
			resultActions.andExpect(ok);
			
			ResultHandler resultHandler =  MockMvcResultHandlers.print();
			resultActions.andDo(resultHandler);
			

			
		}catch(Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

}
