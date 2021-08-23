package com.day.security.configuration;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.day.security.handler.MyLoginFailerHandler;
import com.day.security.handler.MyLoginSuccessHandler;
import com.day.security.service.CustomUserDetailsService;

//스프링 시큐리티의 웹 보안 기능 초기화 및 설정
//@EnableWebSecurity
public class SecurityConfig2 extends WebSecurityConfigurerAdapter {
	private Logger log = LoggerFactory.getLogger(this.getClass());

	 
	@Bean
	public PasswordEncoder passwordEncoder() {
//		return new PasswordEncoder() {
//
//			@Override
//			public boolean matches(CharSequence rawPassword, String encodedPassword) {
//				return rawPassword.equals(encodedPassword);
//			}
//
//			@Override
//			public String encode(CharSequence rawPassword) {
//				return rawPassword.toString();
//			}
//		};
		 return new BCryptPasswordEncoder();//Spring Security에서 제공하는 비밀번호 암호화 객체

	}
	@Bean
	public AuthenticationSuccessHandler successHandler() {
		return new MyLoginSuccessHandler();
	}
	@Bean
	public AuthenticationFailureHandler failHandler() {
		return new MyLoginFailerHandler();
	}
	 
	@Autowired
	DataSource dataSource;
	
//	모든 인증은 AuthenticationManager를 통해 이루어지며 AuthenticationManager를 생성하기 위해서는 AuthenticationManagerBuilder를 사용
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
		log.error("<1>configureGlobal----");
		auth.inMemoryAuthentication().withUser("a").password("1").roles("MEMBER");
		auth.inMemoryAuthentication().withUser("b").password("2").roles("ADMIN");
		
//		String query1 = "SELECT id, pwd, enabled FROM customer WHERE id=?";
//		String query2 = "SELECT role_id, role_name FROM customer_role WHERE role_id=?";
//		auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery(query1)
//		//.rolePrefix("ROLE_")
//		.authoritiesByUsernameQuery(query2);
	}
	
	
	@Autowired
	CustomUserDetailsService customUserDetailsService;
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		log.error("security configure AuthenticationManagerBuilder----");
//		auth.userDetailsService(customUserDetailsService);
//	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		log.error("<2>configure(HttpSecurity http)---------------");
		http
			.authorizeRequests() // 요청에 대한 권한확인한다.
//				.antMatchers("/myLogin", "/").permitAll() // 누구나 접근 허용
//				.anyRequest().authenticated() // 나머지 요청들은 권한의 종류에 상관 없이 권한이 있어야 접근 

				.antMatchers("/admin/**").hasRole("ADMIN") // ADMIN만 접근 가능

		.and()	
//		     인증방법은 폼로그인방식처리. spring security내장된 /login과 /logout사용함
//			.formLogin(); 
//		                 
			.formLogin()
				.loginPage("/myLogin")
				.permitAll()
//				.loginPage("/html/login.html") // 사용자정의 /myLogin를 사용하면  getpost /logout사용불가
				.successHandler(successHandler())
				.failureHandler(failHandler())
		
		.and()
//		       권한이 없는 경우 403응답코드가 응답된다.
//	       403응답인 경우  accessDeniedPage값에 지정된 url로 forward된다 : controller필요!
			.exceptionHandling()
				.accessDeniedPage("/accessError")
				
		.and()
			.userDetailsService(customUserDetailsService)
			
//		.and()
//			.csrf()
//				.disable()
//			.csrf()
//				.ignoringAntMatchers("/login")
//				.ignoringAntMatchers("/sugnup")
	;
	}	

	@Override
	public void configure(WebSecurity web) { // 4
		log.error("<3> configure(WebSecurity)----");
		web.ignoring().antMatchers("/css/**", "/js/**", "/images/**", "/html/**");
	}
}
