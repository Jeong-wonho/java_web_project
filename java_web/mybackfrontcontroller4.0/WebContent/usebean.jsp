<%@page import="com.day.dto.Customer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//서블릿에서 할 일
request.setAttribute("resultInt", 1);
request.setAttribute("resultCustomer", new Customer("id1","p1","n1"));

%>

<%
//이동된 JSP에서 할 일
//요청객체에서 속성(이름:"resultInt")값 얻기

//int resultIntValue = (Integer)request.getAttribute("resultInt");
int resultIntValue = 0;
Integer resultIntegerValue = (Integer)request.getAttribute("resultInt");
if(resultIntegerValue != null) {
	resultIntValue = resultIntegerValue.intValue();
}
%>
<%-- 
//요청객체에서 속성(이름:"resultCustomer")값 얻기
Customer resultC = (Customer)request.getAttribute("resultCustomer");
//요청객체가 null이면 매개변수없는 생성자를 이용해 객체생성하기,
//요청객체속성(이름:"resultCustomer". 값: resultC)으로 추가하기.
if(resultC==null) {
	resultC = new Customer();
	request.setAttribute("resultCustomer", resultC);
}

--%>
<jsp:useBean id="resultCustomer" class="com.day.dto.Customer" scope="request" />
<%--resultC.setEnabled(0);--%>
<jsp:setProperty name="resultCustomer" property="enabled" value="0" />

<%--out.print(resultC.getId());--%>

<h3>EL표기법에 의한 id값 : ${requestScope.orderInfo.order_c.id}</h3>
<%-- <h3>JSP expression에 의한 id값 : <%((OrderInfo) request.getAttribute("orderInfo")).getIrder_c().getId() %></h3> --%>

<%-- 요청전달데이터 없는경우 결과: "", 요청전달데이터 이름인 있고 값은 없는경우 결과:ex)?word=결과 : "" --%>
<h3>EL표기법에 의한 요청전달데이터 word값 : ${param.word}</h3>
<%-- 요청전달데이터 없는경우 결과: null, 요청전달데이터 이름인 있고 값은 없는경우 결과:ex)?word=결과 : --%>
<h3>JSP expression에 의한 요청전달데이터 word값 :<%=request.getParameter("word") %></h3>