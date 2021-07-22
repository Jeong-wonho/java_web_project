<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:choose>
<%--로그인 안된 경우 --%>
<c:when test="${empty sessionScope.loginInfo}">
{"status": 0}
</c:when>
<c:otherwise>
<%--로그인 이 성공된 경우 --%>
{"status": 1, "loginedId":"${sessionScope.loginInfo.user_id}"}
</c:otherwise>
</c:choose>  