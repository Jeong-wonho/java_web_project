<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" type="text/css" href="./css/Tradecomplete.css">
</head>
<c:set var="customprice" value="${requestScope.customprice}"></c:set>

 
<body>
  <section class="trade_section">
    <div class="trade_img">
      <img class="img_item" src="" alt="">
    </div>
    <div class="trade_warp">
      <div class="trade_info">
        <span class="info_title">즉시 구매가 완료 되었습니다.</span>
        <span class="info_span">구매한 상품은 전문가 검수 완료 후,</span>
        <span class="info_span">안전하게 배송될 예정입니다.</span>
        <a class="btn_item" href="">구매 내역 상세보기</a>
      </div>
    </div>
    <div class="price_warp">
      <div class="price_main">
        <span class="main_title">총 결제금액</span>
        <div class="main_price_warp">
          <span class="main_price">${customprice}</span>
          <span class="main_price1">원</span>
        </div>
      </div>
      <div class="price_sub">
        <div class="sub_div1">
          <span class="sub_title1">즉시 구매가</span>
          <div class="suv_price1_warp">
            <span class="sub_price1">${customprice}</span>
            <span class="sub_pricewon">원</span>
          </div>
        </div>
        <div class="sub_div2">
          <span class="sub_title2">검수비</span>
          <span class="sub_price2">무료</span>
        </div>
        <div class="sub_div2">
          <span class="sub_title3">배송비</span>
          <span class="sub_price3">무료 이벤트</span>
        </div>
      </div>
    </div>
  </section>
</body>
</html>