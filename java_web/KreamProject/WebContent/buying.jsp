<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <link rel="stylesheet" type="text/css" href="./css/Buying.css">
    <script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script>
      //구매 입찰 
      $(function () { 
        var $btn_start = $('.pbc_a1');
        var $btn_ing = $('.pbc_a2');
        var $btn_end = $('.pbc_a3');
        var $purchase = $('.purchase_info_warp')
        var $purchasing = $('.purchasing_info_warp')
        var $end = $('.end_info_warp')
  
        $btn_start.click(function () {
          $btn_start.css({color:"#FF0000"});
          $btn_ing.css({color:"rgb(0, 0, 0)"});
          $btn_end.css({color:"rgb(0, 0, 0)"});
          
          $purchase.css('display', 'block')
          $purchasing.css('display', 'none')
          $end.css('display', 'none')
  
          $('.purchase_menu_item1').css('border-bottom', '2px solid black')
          $('.purchase_menu_item2').css('border-bottom', 'none')
          $('.purchase_menu_item3').css('border-bottom', 'none')
   
        });
        //진행중
        $btn_ing.click(function () {
          $btn_start.css({color:"rgb(0, 0, 0)"});
          $btn_ing.css({color:"#FF0000"});
          $btn_end.css({color:"rgb(0, 0, 0)"});
  
          $purchase.css('display', 'none')
          $purchasing.css('display', 'block')
          $end.css('display', 'none')
  
          $('.purchase_menu_item1').css('border-bottom', 'none')
          $('.purchase_menu_item2').css('border-bottom', '2px solid black')
          $('.purchase_menu_item3').css('border-bottom', 'none')
        });
        //종료
        $btn_end.click(function () {
          $btn_start.css({color:"rgb(0, 0, 0)"});
          $btn_ing.css({color:"rgb(0, 0, 0)"});
          $btn_end.css({color:"#FF0000"});
  
          $purchase.css('display', 'none')
          $purchasing.css('display', 'none')
          $end.css('display', 'block')
  
          $('.purchase_menu_item1').css('border-bottom', 'none')
          $('.purchase_menu_item2').css('border-bottom', 'none')
          $('.purchase_menu_item3').css('border-bottom', '2px solid black')
         });
      });
  
      
    </script>
    <!-- 구매 내역 & 버튼-->
    <div class="purchase_warp">
    <div class="purchase_title">    
      <h1 class="purchase_title_item">구매 내역</h1>
    </div>
    <div class="purchase_menu">
      <div class="purchase_menu_item1">
        <button class="pbc_a1">
        <span class="pbc purchase_bid_count">0</span>
        <span class="pbc purchase_bid_title">구매 입찰</span>
        </button>
      </div>
      <div class="purchase_menu_item2">
        <button class="pbc_a2">
        <span class="pbc purchase_ing_count">0</span>
        <span class="pbc purchase_ing_title">진행 중</span>
      </button>
      </div>
      <div class="purchase_menu_item3">
        <button class="pbc_a3">
        <span class="pbc purchase_done_count">0</span>
        <span class="pbc purchase_done_title">종료</span>
      </button>
      </div>
    </div>
  </div>
   <!-- 구매 입찰 -->
    <div class="purchase_info_warp">
      <div class="purchase_info_title">
        <div class="purchase_whole">
          <span class="purchase_whole_item">전체</span>
        </div>
        <div class="purchase_details">
          <span class="purchase_details_item">구매 희망가</span>
          <span class="purchase_details_item">만료일</span>
        </div>
      </div>
      <div>
        <div class="purchase_info">
          <div class="purchase_info_img">
            <img class="purchase_info_img_item" src="./images/dunk.png" alt="">
          </div>
          <div class="purchase_info_name">
            <span class="purchase_info_name_item">
              Nike Dunk High Retro PRM Light Chocolate
            </span>
          </div>
          <div class="purchase_info_details">
            <div class="pdvw">
            <span class="purchase_details_vlaue">300,000</span>
            <span class="purchase_details_won">원</span>
          </div>
            <span class="purchase_details_date">20/07/12</span>
          </div>
        </div>
      </div>
    </div>
   <!-- 진행 중 -->
   <div class="purchasing_info_warp">
    <div class="purchasing_info_title">
      <div class="purchasing_whole">
        <span class="purchasing_whole_item">전체</span>
      </div>
      <div class="purchasing_details">
        <span class="purchasing_details_item">상태</span>
      </div>
    </div>
    <div>
      <div class="purchasing_info">
        <div class="purchasing_info_img">
          <img class="purchasing_info_img_item" src="./images/dunk.png" alt="">
        </div>
        <div class="purchasing_info_name">
          <span class="purchasing_info_name_item">
            Nike Dunk High Retro PRM Light Chocolate
          </span>
        </div>
        <div class="purchasing_info_details">
          <span class="purchasing_details_date">진행 중</span>
        </div>
      </div>
    </div>
  </div>
  <!-- 종료 -->
  <div class="end_info_warp">
    <div class="end_info_title">
      <div class="end_whole">
        <span class="end_whole_item">전체</span>
      </div>
      <div class="end_details">
        <span class="end_details_item">구매일</span>
        <span class="end_details_item">상태</span>
      </div>
    </div>
    <div>
      <div class="end_info">
        <div class="end_info_img">
          <img class="end_info_img_item" src="./images/dunk.png" alt="">
        </div>
        <div class="end_info_name">
          <span class="end_info_name_item">
            Nike Dunk High Retro PRM Light Chocolate
          </span>
        </div>
        <div class="end_info_details">
          <div class="pdvw">
          <span class="end_details_vlaue">20/07/12</span>
        </div>
          <span class="end_details_date">거래종료</span>
        </div>
      </div>
    </div>
  </div>