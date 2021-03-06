package com.day.boardrest.configuration;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@PropertySource("classpath:/application.properties")
public class DatabaseConfiguration {
	
	@Bean
	@ConfigurationProperties(prefix="spring.datasource.hikari")
	public HikariConfig hikariConfig() {
		return new HikariConfig();
	}
	
	@Bean
	public DataSource dataSource() throws Exception{
		DataSource dataSource = new HikariDataSource(hikariConfig());
		System.out.println(dataSource.toString());
		return dataSource;
	}
	
	@Autowired
	private ApplicationContext applicationContext;
	
	@Bean
	@Qualifier("UnderscoreToCamelCase")
//	@Primary
	public SqlSessionFactory sqlSessionFactory1(DataSource dataSource) throws Exception{
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		
		sqlSessionFactoryBean.setConfigLocation(applicationContext.getResource("classpath:/mybatisConfig/mybatis-board-config.xml"));
		return sqlSessionFactoryBean.getObject();
	}
	
	@Bean
	@Qualifier("Underscore")
	public SqlSessionFactory sqlSessionFactory2(DataSource dataSource) throws Exception{
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		
		sqlSessionFactoryBean.setConfigLocation(applicationContext.getResource("classpath:/mybatisConfig/mybatis-config.xml"));
		return sqlSessionFactoryBean.getObject();
	}
	
	@Bean	
	@Qualifier("UnderscoreToCamelCaseTemplate")
	public SqlSessionTemplate sqlSessionTemplate1(DataSource dataSource) throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory1(dataSource));
	}
	@Bean
	@Qualifier("UnderscoreTemplate")
	public SqlSessionTemplate sqlSessionTemplate2(DataSource dataSource) throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory2(dataSource));
	}
}
