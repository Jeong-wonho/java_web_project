<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>KREAM | 한정판 거래의 FLEX</title>
    <link rel="stylesheet" type="text/css" href="./css/Bidsell2.css">
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
			 $section.load('./bidsellend', function (responseTxt, statucTxt, xhr) {
	              if (statucTxt == "error")
	                alert("Error: " + xhr.status + ":" + xhr.statucTxt);
	            });
			 return false;
		});

      });
      
       


    </script>
 </head>

 <body>
    
  <section class="rightcontent2">
    <br>
    <div class="price_back">
      <div class="price_content">
        <div class="price_title"><p>최종 주문정보</p></div><br>
        <div class="totalamount"><span>총 정산금액</span><span class="totalprice" >${customprice}원</span>
        </div>
        <br>
        <div class="hrline"></div>
        <br>
        <div class="price_info">
          <div class="free"><span class="title1">판매 희망가</span><span class="totalpriceB">${customprice}원</span></div>
          <div class="free"><span class="title">검수비</span><span>무료</span></div>
          <div class="free"><span class="title">판매 수수료</span><span>무료 이벤트</span></div>
          <div class="free"><span class="title">배송비</span><span>선불·판매자 부담</span></div>
        </div>
        <br>
        <div class="hrline"></div>
        <br>
        <div class="deadinfo">
          <div class="free"><span class="title1">입찰 마감 기한</span>
          <span class="deadline">등록일로부터 30일</span>
          </div>
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
      <div class="hrline"></div><br>
      <br>
      <div class="delivery">
        <p>발송 방법</p><br><br><br><br>
        <ul>
          <li>
            <input type="button" value="택배" disabled="disabled">
          </li>
          <li>
            <div class="delivery_comment">
              <p class="deli_line1">선불 택배 발송</p>
              <p>착불 발송 시 정산 금액에서 차감됩니다.</p>
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
        <div class="payment_title">페널티 결제 방법</div><br>
        <div class="payment_line1">
          <div class="p_line1_1">간편 결제</div><div class="p_line1_2">일시불</div>
        </div>
        <div class="payment_line2">
          <input type="button" value="    카드를 등록해주세요 >" disabled="disabled">
        </div>
        <div class="p_line2"><p>-페널티는 일시불만 지원하며, 카드사 홈페이지나 앱에서 분할납부로 변경 가능합니다. 단, 카드사별 정책에 따라 분할납부 변경 시 수수료가 발생할 수 있습니다.</p>
          <p>-수수료(페널티, 착불배송비 등)가 정산되지 않을 경우, 별도 고시 없이 해당 금액을 결제 시도 할 수 있습니다.</p></div>
      </div>
      <br><br>
    </div>

    <div class="checkbox_back">
      <div class="checkbox_item">
        <p class="c_line1">거래가 체결되면 일요일·공휴일을 제외하고 48시간 내에 KREAM으로 발송을 완료한 후, 발송 정보를 정확히 입력해야 합니다.</p>
        <span class="c_line2">착불 배송 시 판매 급액에서 차감 정산하며, 미정산 시 별도 고지없이 해당 금액을 결제 시도할 수 있습니다.</span>
        <input class="checkboxbtn" type="checkbox" name="agree" id="a">
      </div>
      <div class="checkbox_item">
        <p class="c_line1">송장 번호 미기재·오입력 시 입고가 진행되지 않으며, 발송 후 5일(일요일·공휴일 제외) 내 미도착은 허위 정보 입력으로 간주하여 미입고 페널티를 부과합니다.</p>
        <input class="checkboxbtn" type="checkbox" name="agree" id="a">
      </div>
      <div class="checkbox_item">
        <div>
          <p class="c_line1">검수 기준과 페널티 및 이용 정책을 다시 한번 확인하였습니다.</p>
          <span class="c_line2">이용정책 위반 시, 판매 급액의 최대 15.0%의 페널티가 부과됩니다. 페널티 회피 시 이후 거래가 제한되며 별도 고지없이 해당 금액을 결제 시도할 수 있습니다.</span>
        </div>
        <input class="checkboxbtn" type="checkbox" name="agree" id="b">
      </div>
      <div class="checkbox_item">
        <p class="c_line3">판매 조건을 모두 확인하였으며, 입찰 진행에 동의합니다.</p>
        <input class="checkboxbtn" type="checkbox" name="agree" id="c">
      </div>
      <div class="totalamount"><span>총 정산금액</span><span class="totalprice" >${customprice}원</span>
      </div>
      <br><br>
    </div>
    <div class="finalize">
      <input class="finalbtn" type="button" name="checkButton" value=" 판매 입찰하기 ">
    </div>
    <div class="blank"></div>
  </section>

  </body>

</html>