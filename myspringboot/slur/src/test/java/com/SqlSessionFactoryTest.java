package com;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SqlSessionFactoryTest {
	
	private Logger log =LoggerFactory.getLogger(this.getClass());
	@Autowired
	SqlSessionFactory sqlSessionFactory;
	
	@Test
	void test() {
		log.error(sqlSessionFactory.toString());
		assertNotNull(sqlSessionFactory);
	}
}
