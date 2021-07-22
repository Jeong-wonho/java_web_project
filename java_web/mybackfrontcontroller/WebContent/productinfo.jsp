<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@page import ="com.day.dto.Product" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script> -->
<script>
	$(function(){
		$('div.productinfo>div>ol>li>button').click(function(){
			
			var prod_no = $(this).parents('ol').find('li>span.prod_no').html();
			console.log(prod_no);
			var quantity = $(this).parents('ol').find('li>input[type=number]').val();
			console.log(quantity);
			$.ajax({
				url:'./putcart',
				method:'get',
				data:{prod_no: prod_no, quantity: quantity},
				success:function(responseData){
					alert(responseData);
					//div영역보여주기
					$('div.productinfo>div.model').show();
					//모달창듸우기
				}
			});
		});
		
		//계속하기 버튼 클릭이벤트 처리
		$('div.productinfo>div.model>button.productlist').click(function(){
			//상품목록보여주기메뉴에 click 이벤트 강제로 발생
			$('body > nav > ol > li > a[href="./productlist"]').trigger('click');
		});
		
		$('div.productinfo>div.model>button.viewcart').click(function(){
			$('body > nav > ol > li > a[href="./viewcart"]').trigger('click');
		});
	});
</script>
<c:set var="p" value="${requestScope.p}"/>
<div class="productinfo">
<div style = "float:left; width:40%;">
 <img src ="./images/${p.prod_no}.jpg" alt="${p.prod_name}" style = "max-width: 127px">
</div>
<div style = "float:right; width:60%;">
 <ol style = "list-style-type: none; padding:0px">
 <li>상품번호:<span class = "prod_no">${p.prod_no}</span></li>
 <li>상품명:<span>${p.prod_name}</span></li>
 <li>거래:<span>${p.prod_price}</span></li>
 <li>수량:<input type = "number" value = "1" max = "99"/></li>
 <li><button>장바구니 넣기</button></li>
 </ol>
 </div>
 <div class="model" style ="clear:both; display:none;">
 	<button class = "viewcart">장바구니 보기</button>
 	<button class = "productlist">계속하기</button>
 </div>
</div>


<!-- 나중에 삭제해야할 부분 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>
<!-- 나중에 삭제 해아할 부분 끝 -->