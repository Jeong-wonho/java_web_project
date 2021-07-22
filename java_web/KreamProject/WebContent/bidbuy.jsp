<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>KREAM | 한정판 거래의 FLEX</title>
  <link rel="stylesheet" type="text/css" href="./css/Bidbuy.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script>
    $(function () {
      /* //DOM트리에서a 객체 찾기
      var $menuObj = $('.header_link');
      var $kreamObj = $('.nav_link');
      //DOM트리에서 section객체 찾기
      var $section = $('.main_section');
      $menuObj.click(function () {
        //클릭된 현재 객체의 href속성값 얻기 : .attr
        var href = $(this).attr('href');
        switch (href) {
          case './Login.html':
          case './Mypage.html':
          case './Tradecomplete.html':
            $section.load(href, function (responseTxt, statucTxt, xhr) {
              if (statucTxt == "error")
                alert("Error: " + xhr.status + ":" + xhr.statucTxt);
            });
            break;
 
        }
        return false;
      });
      $kreamObj.click(function () {
        //클릭된 현재 객체의 href속성값 얻기 : .attr
        var href = $(this).attr('href');
        switch (href) {
          case './Buysize.html':
          case './Shop.html':
            $section.load(href, function (responseTxt, statucTxt, xhr) {
              if (statucTxt == "error")
                alert("Error: " + xhr.status + ":" + xhr.statucTxt);
            });
            break;

        }
        return false;
      }); */
      // 승환시작
      var $section_right = $('.rightcontent');
      var $bidcontinuebtnObj = $('.continuebtn');
      $bidcontinuebtnObj.click(function(){
        var href = './bidbuy2'; 
        $section_right.load(href, {customprice : receiveprice}, function (responseTxt, statucTxt, xhr) {
            if (statucTxt == "error")
              alert("Error: " + xhr.status + ":" + xhr.statucTxt);
          }); 
          return false;
      });
      
      var min = '<c:out value="${minO.order_price}"/>'
      var $immedibuybtnObj = $('.instantbtn');
		$immedibuybtnObj.click(function() {
			if(min==0){
				alert("해당 물품 구매 입찰 등록건이 없습니다.");
				return false;
			}
			var href = './immedibuy';
			$section_right.load(href, function(responseTxt, statucTxt, xhr) {
				if (statucTxt == "error")
					alert("Error: " + xhr.status + ":" + xhr.statucTxt);
			});
			return false;
		});
		
		
      var currentVal;
      var receiveprice = $('.totalprice1');
	  $('.totalprice').change(function(){
		  currentVal = $(this).val();
		 /*  if(currentVal>min){
			 currentVal = min;
		  }; */
		  receiveprice = currentVal;
		$('.totalprice1').html(receiveprice+'원');
		$('.continuebtn').removeAttr("disabled")
	  });
	  
	





    });
  </script>
</head>
<c:set var="c" value="${requestScope.c}"></c:set>
<c:set var="minO" value="${requestScope.minO}"></c:set>
<c:set var="maxO" value="${requestScope.maxO}"></c:set>
<fmt:formatNumber type="number" maxFractionDigits="3" value="${minO.order_price}" var="minprice" />
<fmt:formatNumber type="number" maxFractionDigits="3" value="${maxO.order_price}" var="maxprice" />


<body>
		<section class="rightcontent2">
			<br>
			<div class="price_back">
				<div class="price_content">
					<ul class="price_list">
						<div class="price_left">
							<li class="list_item">
								<p>즉시 구매가</p> <span class="min_price">${minprice}</span> <span>원</span>
							</li>
						</div>
						<div class="updown"></div>
						<div class="price_right">
							<li class="list_item">

								<p>즉시 판매가</p> <span class="max_price">${maxprice}</span> <span>원</span>
							</li>
						</div>
					</ul>
					<br>

					<ul class="bidorimmedibuy">
						<li><a><button class="bidbuybutton">구매 입찰</button></a></li>
						<li><a class="instantbtn">
								<button class="instantbtn">즉시 구매</button>
						</a></li>
					</ul>
					<div class="hopeprice">구매 희망가</div>
					<label class="insertprice"><input class="totalprice"
						type="text" placeholder="희망가 입력">원 </label>
					<hr>
					<div class="free">
						<span class="title">검수비</span><span>무료</span>
					</div>
					<div class="free">
						<span class="title">배송비</span><span>무료</span>
					</div>
					<hr>
					<div class="totalamount">
						<span>총 결제금액</span><span class="totalprice1">가격</span>
					</div>
					<br>
				</div>
			</div>
			<div class="deadline_back">
				<div class="deadinfo">
					<div class="deadline_line1">입찰 마감기한</div>
					<br>
					<div class="deadline">
						등록일로부터 30일<span onload="time()"></span>
					</div>
				</div>
				<br>
			</div>
			<div class="addrback">
				<p>배송 주소</p>
				<br>
				<div class="user_name">${c.user_name}</div>
				<br>
				<div class="user_phone">${c.user_phone}</div>
				<br>
				<div class="user_addr">${c.user_addr}</div>
				<br> <br>
			</div>
			<div class="continue">
				<a class="continuebtn">
					<button class="continuebtn" disabled>구매 입찰 계속</button>
				</a>
			</div>
			<div class="blank"></div>
		</section>
</body>
</html>