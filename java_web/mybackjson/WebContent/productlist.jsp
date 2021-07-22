<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>productlist.jsp</title>
<meta charset="UTF-8">
    <meta name="viewport" content="width = device-width, initial-scale = 1" />
    <title>상품 목록</title>
    <style>
        * {
            box-sizing : border-box;
        }
        table.productlist {
            width : 500px;
            height : 300px;
            border : 1px solid;
            border-collapse : collapse;
        }
        table.productlist tr > td {
            width : 20%;
            margin : 5px;
            border : 1px solid;
        }
        table.productlist tr > td > ul {
            list-style-type : none;
            padding : 0px;
            text-align : center;
        }
        table.productlist tr > td > ul > li > img {
            max-width : 100%;
        }

    </style>
</head>
<body>
<c:set var="productList" value="${requestScope.productList}" />

<table class="productlist">
   <c:forEach items="${productList}" var="p" varStatus="statusObj">
	<c:if test="${statusObj.index % 4 == 0}">
	  <c:if test="${statusObj.index > 0}">
	    </tr>
	  </c:if>
	  <tr>
	</c:if>  
     <td>
       <ul>
         <li><img src="images/${p.prod_no}.jpg" alt="${p.prod_name}" /></li>
         <li>${p.prod_name}</li>
       </ul>
     </td>
   </c:forEach> 
   </tr>  
</table>
</body>
</html>