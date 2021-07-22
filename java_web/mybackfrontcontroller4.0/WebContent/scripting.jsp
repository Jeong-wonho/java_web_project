<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%int i=9;%>
<%i++; %>
<%out.print("i변수값은 : " + i);%>
<hr>
지역변수 i값 : <%=i %>
<hr>
<%!int i; %>
전역변수 i값 : <%=this.i %>
<%--<%!out.print("_jspService()의 외부입니다."); --%>
<hr>
<%//자바주석 %>
<!-- html주석 -->
<%-- JSP주석 --%>

</body>
</html>