<!-- 메인페이지 포함될 때 삭제할 영역 시작  -->  <!-- 메인페이지에 포함될 때 삭제할 영역 끝 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!-- 메인페이지 포함될 때 삭제할 영역 시작  -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>상품목록</title>
<!-- 메인페이지에 포함될 때 삭제할 영역 끝 -->
<link rel="stylesheet" href="./css/basic.css" />
<style>
      /* * {
        box-sizing: border-box;
      } */
      table.productlist {
        width: 500px;
        height: 300px;
        border: 1px solid;
        border-collapse: collapse;
      }
      table.productlist tr > td {
        width: 20%;
        margin: 5px;
        border: 1px solid;
      }
      table.productlist tr > td > ul {
        list-style-type: none;
        padding: 0px;
        text-align: center;
      }
      table.productlist tr > td > ul > li > img {
        max-width: 100%;
        max-height: 127px;
      }
    </style>
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script> -->
<script>
$(function(){ //$(document).ready(function(){});
	$('table.productlist tr>td').click(function(){
		console.log(this); //콘솔에 this에 대해서 출력해라!
		var prod_no = $(this).attr('class');
		console.log(prod_no);
		$.ajax({								//상품상세정보보기페이지 : ./productinfo
			url: '/myback/productinfo',				//요청방식 : get
			data : {prod_no:prod_no},			//요청전달데이터 : prod_no=상품번호값
			success:function(responseData){		//응답
				$('section').empty(); //section영역을 지운다.
				$('section').html(responseData); // responseData는 html태그 형태로 구성된 응답 내용이어야 한다.
				//json형태의 응답결과라면
				//var htmlStr = '<img src="'+ responseData.prod_no +".jpg>";
				//$('section').html(htmlStr);
			}
		});
	});
});
</script>


<!-- 메인페이지 포함될 때 삭제할 영역 시작  -->
</head>
<body>
<section>
<!-- 메인페이지 포함될 때 삭제할 영역 끝  -->
<c:set var="productList" value="${requestScope.productList}" />

<table class="productlist">
   <c:forEach items="${productList}" var="p" varStatus="statusObj">
	<c:if test="${statusObj.index % 4 == 0}">
	  <c:if test="${statusObj.index > 0}">
	    </tr>
	  </c:if>
	  <tr>
	</c:if>  
     <td class="${p.prod_no}">
       <ul>
         <li><img src="images/${p.prod_no}.jpg" alt="${p.prod_name}" /></li>
         <li>${p.prod_name}</li>
       </ul>
     </td>
   </c:forEach> 
   </tr>  
</table>
<!-- 메인페이지 포함될 때 삭제할 영역 시작  -->
</section>
</body>
</html>
<!-- 메인페이지 포함될 때 삭제할 영역 끝  -->