<%@page import="java.text.DecimalFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%!
	int total_score = 0;
	int click = 0;	
	%>	
	<%
	String score;	
	score = request.getParameter("score");
	total_score += Integer.parseInt(score);
	click++;	
	double avg_score = (double)(total_score)/click;
	%>
	선택한 별점은 <%=score%> 점입니다.
	<br>
	별점 총점은	<%=total_score%> 점입니다.
	<br>
	참여인원수 : <%=click%>
	<br>
	평점은 <%= avg_score %> 입니다. 소수점 모두 표시.
	<br>
	<%
	String patten = "#.#";
	DecimalFormat df = new DecimalFormat(patten);
	%>
	평점은 <%= df.format(avg_score)%> 입니다. 소수점 한 자리 표시.
	<br>
	<a href="score.html">별점주기</a>
	
</body>
</html>