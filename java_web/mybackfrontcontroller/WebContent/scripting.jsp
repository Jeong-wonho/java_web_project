<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%int i=9; %>
<%i++; %>
<%out.print("i변수값은 :"+i); %>
<hr>
i변수값은: <%=i %>
<hr>
지역변수 i변수 값은 : <%= i %> <%-- expression 메서드 안에  --%>
<hr>
<%!int i; %> 		
멤버 변수 i 변수값 : <%=this.i %> <%--expression 메서드 밖에  --%>

<%--<%!out.print("_jspService()의 외부입니다.")%>--%>
<!-- html 주석문 -->
<%--jsp주석 --%>
 
</body>

</html>