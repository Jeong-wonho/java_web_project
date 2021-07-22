<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" type="text/css" href="./css/Buycheck.css">
  <script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
	$(function() {
		var $buycontinuebtn = $('.buy_continue');
		$buycontinuebtn.attr("disabled", true);
		$('.checkboxbtn').click(function() {
			var tmp = $(this).prop('checked');
			var tt = $(".checkboxbtn").length;
			var ss = $(".checkboxbtn:checked").length;
			
			if (tt == ss) {
				$('.buy_continue').removeAttr("disabled")
				$('.buy_continue').css('background-color', 'black');
			} else {
				$buycontinuebtn.attr("disabled", true);
			}
		});
		 var $section = $('.main_section');
		$buycontinuebtn.click(function(){
			 $section.load('./buy', function (responseTxt, statucTxt, xhr) {
	              if (statucTxt == "error")
	                alert("Error: " + xhr.status + ":" + xhr.statucTxt);
	            });
			 return false;
		});
	});
</script>	
 	
</head>

<body>
  <div class="buysize_info">
    <div class="buysize_title">
      <span class="title_item">
        <span class="title_item2">구매</span>
        하시기전에 꼭 확인하세요.
      </span>
    </div>
    <div class="psize_info">
      <div class="psize_warp">
        <div class="psize_img">
          <img class="img_item" src="" alt="">
        </div>
        <div class="psize_msg">
          <p class="msg_p">새상품 · DH9696-100</p>
          <span class="msg_span">(W) Jordan 6 Retro Gold Hoops</span>
          <button class="msg_size">size</button>
        </div>
      </div>
      <div class="checkbox_back">
        <div class="checkbox_item">
          <p class="info_p">구매하려는 상품이 맞습니다.</p>
          <span class="info_span">상품 이미지, 모델번호, 출시일, 상품명, 사이즈를 한 번 더 확인했습니다.</span>
          <input class="checkboxbtn" type="checkbox" name="" id="">
        </div>
        <div class="checkbox_item">
          <p class="info_p">국내/해외에서 발매한 정품·새상품입니다.</p>
          <span class="info_span">모든 구성품이 그대로이며, 한 번도 착용하지 않은 정품·새상품입니다. 국내 발매 상품 여부는 확인드리지 않습니다.</span>
          <input class="checkboxbtn" type="checkbox" name="" id="">
        </div>
        <div class="checkbox_item">
          <p class="info_p">제조사에서 불량으로 인정하지 않는 기준은 하자로 판단하지 않습니다,.</p>
          <span class="info_span">박스/패키지와 상품 컨디션에 민감하시다면 검수 기준을 반드시 확인하시기 바랍니다.</span>
          <input class="checkboxbtn" type="checkbox" name="" id="">
        </div>
        <div class="checkbox_item">
          <p class="info_p">KREAM의 최신 이용정책을 모두 확인하였으며, 구매를 계속합니다.</p>
          <span class="info_span">건전하고 안전한 거래를 위해 반드시 숙지해야 할 미입고, 페널티, 부정거래 등의 이용정책을 확인했습니다.</span>
          <input class="checkboxbtn" type="checkbox" name="" id="">
        </div>
        <input class="buy_continue" type="button" value=" 구매 계속 ">
      </div>
    </div>
  </div>
</body>

</html>