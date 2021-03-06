<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <link rel="stylesheet" type="text/css" href="./css/Selling.css">
    <script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script> 
      //판 입찰
      $(function () {
        var $btn_start = $('.sbc_a1');
        var $btn_ing = $('.sbc_a2');
        var $btn_end = $('.sbc_a3');
        var $purchase = $('.sale_info_warp')
        var $purchasing = $('.onsale_info_warp')
        var $end = $('.eos_info_warp')
  
        $btn_start.click(function () {
          $btn_start.css({color:"#FF0000"});
          $btn_ing.css({color:"rgb(0, 0, 0)"});
          $btn_end.css({color:"rgb(0, 0, 0)"});
          
          $purchase.css('display', 'block')
          $purchasing.css('display', 'none')
          $end.css('display', 'none')
  
          $('.sale_menu_item1').css('border-bottom', '2px solid black')
          $('.sale_menu_item2').css('border-bottom', 'none')
          $('.sale_menu_item3').css('border-bottom', 'none')
  
        });
        //진행중
        $btn_ing.click(function () {
          $btn_start.css({color:"rgb(0, 0, 0)"});
          $btn_ing.css({color:"#FF0000"});
          $btn_end.css({color:"rgb(0, 0, 0)"});
  
          $purchase.css('display', 'none')
          $purchasing.css('display', 'block')
          $end.css('display', 'none')
  
          $('.sale_menu_item1').css('border-bottom', 'none')
          $('.sale_menu_item2').css('border-bottom', '2px solid black')
          $('.sale_menu_item3').css('border-bottom', 'none')
        });
        //종료
        $btn_end.click(function () {
          $btn_start.css({color:"rgb(0, 0, 0)"});
          $btn_ing.css({color:"rgb(0, 0, 0)"});
          $btn_end.css({color:"#FF0000"});
  
          $purchase.css('display', 'none')
          $purchasing.css('display', 'none')
          $end.css('display', 'block')
  
          $('.sale_menu_item1').css('border-bottom', 'none')
          $('.sale_menu_item2').css('border-bottom', 'none')
          $('.sale_menu_item3').css('border-bottom', '2px solid black')
         });
      });
  
      
    </script>

     <!-- 판매 내역 & 버튼-->
  <div class="sale_warp">
    <div class="sale_title">    
      <h1 class="sale_title_item">구매 내역</h1>
    </div>
    <div class="sale_menu">
      <div class="sale_menu_item1">
        <button class="sbc_a1">
        <span class="sbc sale_bid_count">0</span>
        <span class="sbc sale_bid_title">판매 입찰</span>
        </button>
      </div>
      <div class="sale_menu_item2">
        <button class="sbc_a2">
        <span class="sbc sale_ing_count">0</span>
        <span class="sbc sale_ing_title">진행 중</span>
      </button>
      </div>
      <div class="sale_menu_item3">
        <button class="sbc_a3">
        <span class="sbc sale_done_count">0</span>
        <span class="sbc sale_done_title">종료</span>
      </button>
      </div>
    </div>
  </div>
   <!-- 판매 입찰 -->
    <div class="sale_info_warp">
      <div class="sale_info_title">
        <div class="sale_whole">
          <span class="sale_whole_item">전체</span>
        </div>
        <div class="sale_details">
          <span class="sale_details_item">판매 희망가</span>
          <span class="sale_details_item">만료일</span>
        </div>
      </div>
      <div>
        <div class="sale_info">
          <div class="sale_info_img">
            <img class="sale_info_img_item" src="./images/dunk.png" alt="">
          </div>
          <div class="sale_info_name">
            <span class="sale_info_name_item">
              Nike Dunk High Retro PRM Light Chocolate
            </span>
          </div>
          <div class="sale_info_details">
            <div class="sdvw">
            <span class="sale_details_vlaue">300,000</span>
            <span class="sale_details_won">원</span>
          </div>
            <span class="sale_details_date">20/07/12</span>
          </div>
        </div>
      </div>
    </div>
   <!-- 진행 중 -->
   <div class="onsale_info_warp">
    <div class="onsale_info_title">
      <div class="onsale_whole">
        <span class="onsale_whole_item">전체</span>
      </div>
      <div class="onsale_details">
        <span class="onsale_details_item">상태</span>
      </div>
    </div>
    <div>
      <div class="onsale_info">
        <div class="onsale_info_img">
          <img class="onsale_info_img_item" src="./images/dunk.png" alt="">
        </div>
        <div class="onsale_info_name">
          <span class="onsale_info_name_item">
            Nike Dunk High Retro PRM Light Chocolate
          </span>
        </div>
        <div class="onsale_info_details">
          <span class="onsale_details_date">진행 중</span>
        </div>
      </div>
    </div>
  </div>
  <!-- 종료 -->
  <div class="eos_info_warp">
    <div class="eos_info_title">
      <div class="eos_whole">
        <span class="eos_whole_item">전체</span>
      </div>
      <div class="eos_details">
        <span class="eos_details_item">정산일</span>
        <span class="eos_details_item">상태</span>
      </div>
    </div>
    <div>
      <div class="eos_info">
        <div class="eos_info_img">
          <img class="eos_info_img_item" src="./images/dunk.png" alt="">
        </div>
        <div class="eos_info_name">
          <span class="eos_info_name_item">
            Nike Dunk High Retro PRM Light Chocolate
          </span>
        </div>
        <div class="eos_info_details">
          <div class="edvw">
          <span class="eos_details_vlaue">20/07/12</span>
        </div>
          <span class="eos_details_date">거래종료</span>
        </div>
      </div>
    </div>
  </div>
