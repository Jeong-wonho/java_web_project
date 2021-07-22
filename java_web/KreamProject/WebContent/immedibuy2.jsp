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
    <link rel="stylesheet" type="text/css" href="./css/Bidbuy2.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script>
      $(function(){
        var $finalbtn = $('.finalbtn');
        $finalbtn.attr("disabled", true);
        $('.checkboxbtn').click(function(){
          var tmp = $(this).prop('checked');
          var tt = $(".checkboxbtn").length;
          var ss= $(".checkboxbtn:checked").length;
          if(tt==ss){
            $('.finalbtn').removeAttr("disabled")
            $('.finalbtn').css('background-color','black');
          }else{
            $finalbtn.attr("disabled", true);
          }
        });
        var $section = $('.main_section');
		$finalbtn.click(function(){
			 $section.load('./immedibuyend', function (responseTxt, statucTxt, xhr) {
	              if (statucTxt == "error")
	                alert("Error: " + xhr.status + ":" + xhr.statucTxt);
	            });
			 return false;
		});

     });
      
     
 


    </script>
</head>
<c:set var="c" value="${requestScope.c}"></c:set>
<c:set var="minO" value="${requestScope.minO}"></c:set>
<fmt:formatNumber type="number" maxFractionDigits="3" value="${minO.order_price}" var="minprice" />

<body>
 <section class="rightcontent2">
    <br>
    <div class="price_back">
      <div class="price_content">
        <div class="price_title"><p>최종 주문정보</p></div><br>
        <div class="totalamount"><span>총 결제금액</span><span class="totalprice" >${minprice}원</span>
        </div>
        <br>
        <div class="hrline"></div>
        <br>
        <div class="price_info">
          <div class="free"><span class="title1">즉시 구매가</span><span class="totalpriceB">${minprice}원</span></div>
          <div class="free"><span class="title">검수비</span><span>무료</span></div>
          <div class="free"><span class="title">배송비</span><span>무료 이벤트</span></div>
        </div>
        <br>
        <br>
      </div> 
    </div>
    <div class="addrback">
      <p>배송 주소</p><br>
      <div class="user_name">${c.user_name}</div><br>
      <div class="user_phone">${c.user_phone}</div><br>
      <div class="user_addr">${c.user_addr}</div><br>
      <div class="hrline"></div><br>
      <br>
      <div class="delivery">
        <p>배송 방법</p><br><br><br><br>
        <ul>
          <li>
            <input type="button" value="택배" disabled="disabled">
          </li>
          <li>
            <div class="delivery_comment">
              <p class="deli_line1">일반 택배</p>
              <p>검수 합격 시 배송됩니다.</p>
            </div>
            
          </li>
        </ul>
        <div class="hrline"></div>
        <br>
        <br>
        <div class="delivery_info">검수센터에 도착 후, 입고 > 검수대기 > 검수 과정을 거쳐 합격한 경우에 배송을 시작합니다.
          따라서 일반 쇼핑몰의 구매과정보다 더 많은 시일이 소요됩니다.</div>
        <br><br><br>
      </div>
    </div>
    
    <div class="payment_back">
      <div class="payment_info">
        <div class="payment_title">결제 방법</div><br>
        <div class="payment_line1">
          <div class="p_line1_1">간편 결제</div><div class="p_line1_2">일시불</div>
        </div>
        <div class="payment_line2">
          <input type="button" value="카드를 등록해주세요 >" disabled="disabled">
        </div>
        <div class="p_line2">구매 입찰은 일시불만 지원하며, 카드사 홈페이지나 앱에서 분할 납부로 변경 가능합니다. 단, 카드사별 정책에 따라 분할 납부 변경 시 수수료가 발생할 수 있습니다.</div>
      </div>
      <br><br>
    </div>

    <div class="checkbox_back">
      <div class="checkbox_item">
        <p class="c_line1">판매자의 판매거부, 배송지연, 미입고 등의 사유가 발생할 경우, 거래가 취소될 수 있습니다.</p>
        <input class="checkboxbtn" type="checkbox" name="agree" id="a">
      </div>
      <div class="checkbox_item">
        <div><p class="c_line1">'바로 결제하기'를 선택하시면 즉시 결제가 진행되며, 단순 변심이나 실수에 의한 취소가 불가능합니다.</p>
          
          <span class="c_line2">본 거래는 개인간 거래로 전자상거래법(제17조)에 따른 청약철회(환불, 교환) 규정이 적용되지 않습니다.</span>
        </div>
        <input class="checkboxbtn" type="checkbox" name="agree" id="b">
      </div>
      <div class="checkbox_item">
        <p class="c_line3">구매 조건을 모두 확인하였으며, 입찰 진행에 동의합니다.</p>
        <input class="checkboxbtn" type="checkbox" name="agree" id="c">
      </div>
      <div class="totalamount"><span>총 결제금액</span><span class="totalprice" >${minprice}원</span>
      </div>
      <br><br>
    </div>
    <div class="finalize">
      <input class="finalbtn" type="button" name="checkButton" value=" 결제하기 ">
    </div>
    <div class="blank"></div>
  </section>
</body>
</html>