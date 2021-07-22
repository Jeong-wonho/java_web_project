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
  <link rel="stylesheet" type="text/css" href="./css/Immedisell.css"> 
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
	$(function(){
	      var $section_right = $('.Immedisell');
		var $bidsellbtn = $('.bidbuybutton1');
		$bidsellbtn.click(function() {
			$section_right.load('./bidsell', function(responseTxt, statucTxt, xhr) {
				if (statucTxt == "error")
					alert("Error: " + xhr.status + ":" + xhr.statucTxt);
			});
			return false;
		});
		var $continuebtn = $('.continuebtn1');
		$continuebtn.click(function(){
			 $section_right.load('./immedisell2', function (responseTxt, statucTxt, xhr) {
	              if (statucTxt == "error")
	                alert("Error: " + xhr.status + ":" + xhr.statucTxt);
	            });
			 return false;
		});
	 
		
	});
</script>
</head>
<c:set var="minO" value="${requestScope.minO}"></c:set>
<c:set var="maxO" value="${requestScope.maxO}"></c:set>
<fmt:formatNumber type="number" maxFractionDigits="3" value="${maxO.order_price}" var="maxprice" />
<c:choose>
	<c:when test="${minprice==0}">
		<c:set var="maxprice" value="-"></c:set>
	</c:when>
	<c:otherwise>
	<fmt:formatNumber type="number" maxFractionDigits="3" value="${minO.order_price}" var="minprice" />
	</c:otherwise>
</c:choose>
<c:set var="c" value="${requestScope.c}"></c:set>



<body>
 <section class="Immedisell">
    <br>
    <div class="price_back">
      <div class="price_content">
        <ul class="price_list">
          <div class="price_left">
          <li class="list_item">
            <p>즉시 구매가</p>
            <span class="min_price">${minprice}</span>
            <span>원</span>
          </li>
        </div>
        <div class="updown"></div>
        <div class="price_right">
          <li class="list_item">
            
            <p>즉시 판매가</p>
            <span class="max_price">${maxprice}</span>
            <span>원</span>
          </li>
        </div>
        </ul>
        <br>
  
        <ul class="bidorimmedibuy">
          <li>
            <a > <button class="bidbuybutton1">판매 입찰</button></a>
          </li>
          <li>
            <a></a>
            <button class="instant">즉시 판매</button>
          </li>
        </ul>
        <div class="totalamount"><span>즉시 판매가</span>
          <span class="totalprice" >${maxprice}원</span></div>
        <hr>
        <div class="free"><span class="title">검수비</span><span>무료</span></div>
        <div class="free"><span class="title">판매 수수료</span><span>무료 이벤트</span></div>
        <div class="free"><span class="title">배송비</span><span>선불·판매자 부담</span></div>
        <hr>
        <div class="totalamount"><span>총 정산금액</span><span class="totalprice1" >${maxprice}원</span>
        </div>
        <br>
      </div>
    </div>
    <div class="account">
      <div class="accountinfo">
        <div class="accountinfo_line1">판매 정산 계좌</div><br>
        <div class="accountinfo_line2">
          <span>정산 받으실 계좌를 등록하세요.</span><button disabled>등록</button>
        </div>
      </div><br>
    </div>
    <div class="addrback">
      <p>반송 주소</p><br>
      <div class="user_name">${c.user_name}</div><br>
      <div class="user_phone">${c.user_phone}</div><br>
      <div class="user_addr">${c.user_addr}</div><br>
      <br>
    </div>
    <div class="continue1">
      <a class="continuebtn1">
      <button class="continuebtn1">즉시 판매 계속</button>
      </a>
    </div>
    <br>
    </section>
  </body>
</html>