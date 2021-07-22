<%@page import="com.day.dto.OrderInfo"%>
<%@page import="com.day.dto.Customer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	// 서블릿에서 수행할 작업
request.setAttribute("resultInt", 1);
request.setAttribute("resultCustomer", new Customer("id1", "p1", "n1"));
%>

<%
	// 이동된 JSP에서 수행할 작업
// 요청 객체의 속성(이름 : resultInt)값 얻기
int resultIntValue = 0;
Integer resultIntegerValue = (Integer) request.getAttribute("resultInt");
if (resultIntegerValue != null) {
	resultIntValue = resultIntegerValue.intValue();
}
%>

<%
	// 요청 객체의 속성(이름 : resultCustomer)값 얻기
Customer resultC = (Customer) request.getAttribute("resultCustomer");

/*
	- 요청 객체가 null일 경우 매개변수 없는 생성자를 이용해 객체 생성
	- 요청 객체의 속성(이름 : resultCustomer)값 얻기
*/
if (resultC == null) {
	resultC = new Customer();
	request.setAttribute("resultCustomer", resultC);
}
%>

<jsp:useBean id="resultCustomer" class="com.day.dto.Customer"
	scope="request" />

<%--
	resultC.setEnabled(0);
	<jsp:setProperty name = "resultCustomer" property = "enabled" value = 0 />
--%>



<%-- 
	out.print(resultC.getId()); 
	<jsp:getProperty name = "resultCustomer" property = "enabled" />
--%>



<%-- 
	<*
		- 요청 속성(이름 : "orderInfo") 얻기
		- null인 경우 객체 생성 후 요청 속성으로 추가
	*>
	OrderInfo oi = (OrderInfo)request.getAttribute("orderInfo");
	if(oi == null) {
		oi = new OrderInfo();
		request.setAttribute("orderInfo", oi);
	}
	
	<jsp:useBean id = "orderInfo" class = "com.day.dto.OrderInfo" scope = "request" />
--%>


<%-- 
	Customer c = new Customer();
	oi.setOrder_c(c);
	
	<jsp:setProperty name = "orderInfo" property="order_c" value = "new Customer()" />
--%>


<%-- 
	// 요청 속성(이름 : orderInfo)의 프로퍼티중 order_c의 값을 얻기 
	Customer c1 = oi.getOrder_c();
	out.print(c1.getId());
	
	<jsp:getProperty name = "orderInfo" property="order_c.id" />
--%>

<%-- 
	<h3>EL 표기법에 의한 id값 : ${requestScope.orderInfo.order_c.id }</h3>
	<h3>JSP Expression에 의한 id값 : <%=((OrderInfo)request.getAttribute("orderInfo")).getOrder_c().getId() %></h3>
--%>
<h3>EL 표기법에 의한 id값 : ${requestScope.orderInfo.order_c.id }</h3>

<h3>EL 표기법에 의한 요청 전달 데이터 word값 : ${param.word}</h3>
<h3>
	JSP Expression에 의한 요청 전달 데이터 word값 :
	<%=request.getParameter("word")%></h3>
