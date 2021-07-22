<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>KREAM | 한정판 거래의 FLEX</title>
  <c:set var = "shoplist" value="${requestScope.shoplist}"/>
  <c:set var = "booklist" value="${requestScope.booklist}"/>
  <c:set var = "ddtlist" value="${requestScope.ddtlist}"/>
  <link rel="stylesheet" type="text/css" href="./css/Index.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script>
  
   //전역변수
   var $prod_num;
   var $prod_name;
   var $id;
   var $prod_size;
   var $brand_list = new Array();
   var $brand;
    /*
    로그인 여부에 따라서  display변경
    */
    function logined() {
      $('.login_item').hide();
      $('.logout_item').show();
    }

    function logouted() {
      $('.login_item').show();
      $('.logout_item').hide();
    }
    /*
    sessionStorage KEY(checkLogined)의 value값이 login으로 설정되어있는 값을 loginstatus로 변수 설정.
    */
    var loginstatus = sessionStorage.getItem("checkLogined", "login");
    /*
    sessionStorage KEY(checkLogined)의 value값이 login으로 설정되었을때, 각 조건에 따라 display변경.
    */
    function checkLogined() {
      if (loginstatus == "login") {
        logined();
      } else {
        logouted();
      }
    }
 
    $(function () {
      checkLogined();
      /*
      1초마다  checkLogined실행.
      */
      setInterval(function () {
        checkLogined();
      }, 1000);

      var $menuObj = $('.header_link');
      var $kreamObj = $('.nav_link');
      var $section = $('.main_section');
      $menuObj.click(function () {
        var href = $(this).attr('href');
        switch (href) {
          case './Login.html':
            $section.load(href, function (responseTxt, statucTxt, xhr) {
              if (statucTxt == "error")
                alert("Error: " + xhr.status + ":" + xhr.statucTxt);
            });
            break;
          case './mypage':
            /*
            sessionStorage의 KEY(loginedId)에 설정되어 있는 VALUE값 얻기.  			 
            ex) key:loginedId, value:ahh1234
                       value:로그인된 회원의 아이디
            */
            var loginedId = sessionStorage.getItem("loginedId");
            if (loginedId != null && loginedId != '') {
              $section.load(href, { user_id: loginedId }, function (responseTxt, statucTxt, xhr) {
                if (statucTxt == "error")
                  alert("Error: " + xhr.status + ":" + xhr.statucTxt);
              });
            } else {
              alert("로그인을 해주세요.");
              $section.load("./Login.html", function (responseTxt, statucTxt, xhr) {
                if (statucTxt == "error")
                  alert("Error: " + xhr.status + ":" + xhr.statucTxt);
              });
              break;
            }
            break;
          case './Index.html':
            /*
            로그아웃 버튼 클릭시, sessionStorage KEY(checkLogined)의 value 삭제.
            loginstaus의 값이 삭제된다.
            */
            sessionStorage.removeItem("checkLogined");
            sessionStorage.removeItem("loginedId"); //?
            logouted();
            location.href = "Index.html";
        }
        return false;
      });
      $kreamObj.click(function () {
        //클릭된 현재 객체의 href속성값 얻기 : .attr
        var href = $(this).attr('href');
        switch (href) {
          case './shop':
          case './buy':
            $section.load(href, function (responseTxt, statucTxt, xhr) {
              if (statucTxt == "error")
                alert("Error: " + xhr.status + ":" + xhr.statucTxt);
            });
            break;

        }
        return false;
      });
      
      
      // wonho modify++
      // 프로덕트 인포로 넘어가게.!
      //ajax로 상품 정보 보내기
      $(".item_inner").click(function(){
     	 //이름 값 가지고 오기
     	 $prod_num = $(this).attr("number");

     	 console.log(this);
     	$.ajax({
     		 type:"GET",
      		url:"/KreamProject/productinfo",
      		datatype:"html",
      		data:{"prod_num":$prod_num},
      		success:function(response){
      			$(".main").empty();
     			$(".main").html(response);
      		}
     	 })
      });
      
    // 북마크 관련 메서드
    //북마크 클릭할때 북마크 정보 데이터 보내기
      var id = "kms1234";
      
      $(".bookmark").click(function(e) {
     	 var $parent = $(this).parent(".brand_logo").parent(".info_box");
     	 $prod_num = $parent.find('a').attr('number');
     	 $prod_name = $parent.find('.name').text();
     	 $prod_size = $parent.find('.name').attr("size");
     	 
     	 
     	 
     	 var img_url = "./images/prod_num/"+$prod_num +".png";
     	 $('.bm_img').attr("src", img_url);
     	 $('.bm_product_name').text($prod_name);
     	 $('.bm_product_num').text($prod_num);
     	 $('.bm_modal').fadeIn();
     	 
     	  $.ajax({
     		 type:"get",
     		 url:"./bookmark",
     		 datatype:"html",
     		 //data: {id:"customer_id"} 추가예정 임의로 kms1234 적용
				 data:{"id":"kms1234", "prod_num":$prod_num},
				 
				 success: function(response){
					 
					 alert("북마크불러오기 성공");
					 
				 }
     	 }); 
     	 
      });
     // modal창 밖을 클릭시 원래 창으로 돌아오기! 
      $(window).click(function(e) {
          if(e.target.className == "bm_modal"){
              $(".bm_modal").fadeOut();
            }
        });
      
     //확인창누르면 모달창 끄기
      $(".bm_confirm").click(function(e) {
          $(".bm_modal").fadeOut();
    	});
     
     //북마크 클릭시 데이터 전송하기
     // id, 사이즈, 상품정보
     $(".size_p_list").click(function(e){
     	var fill_bm = './images/fill_bookmark.svg';    	
     	var $prod_size = $(this).find('p').text();
     	
     	
     	console.log($prod_num);
     	var $img = $(this).find('img');
     	$img.attr("Src", fill_bm);
			
     	//클릭시 데이터 전송! //아이디 꼭 전송
       	$.ajax({
     		type:"get",
     		url:"./bookmark",
     		datatype:"html",
     		data:{"id":'kms1234', "prod_num":$prod_num, "prod_size":$prod_size}, // id 추가헤야함.!
     		
     		success: function(response){
     			alert("전송성공");	
     		}

     	});
     });  
    });
  </script>
</head>

<body>
  <header class="header">
    <ul class="header_list">
      <li class="header_item"><a class="header_link" href="./mypage">마이페이지</a></li>
      <li class="header_item login_item"><a class="header_link" href="./Login.html">로그인</a></li>
      <li class="header_item logout_item"><a class="header_link" href="./Index.html">로그아웃</a></li>
    </ul>
    <div class="header_main">
      <h1 class="logo_h1"><a class="logo_link" href="">KREAM</a></h1>
      <ul class="nav_list">
        <li class="nav_item"><a class="nav_link" href="">STYLE</a></li>
        <li class="nav_item"><a class="nav_link" href="./shop">SHOP</a></li>
        <li class="nav_item"><a class="nav_link" href="./buy">ABOUT</a></li>
      </ul>
    </div>
  </header>
  <section class="main_section">
  <section class="main">
    <!-- 광고 -->
    <div class="main_ad">
      <img class="main_ad_item" src="./images/kreammain.jpg" alt="광고">
    </div>
    <!-- 발매 상품 -->
    <div class="product_recent_warp">
      <div class="product_recent_title">
      <span class="product_recent_sub">Just Dropped</span>
      <span class="product_recent_sub">발매 상품</span>
    </div>
    <div class="product_recent_list">
      <!-- 1번 상품-->
      <c:forEach items="${ddtlist}" var="d" varStatus="statusObj">
     
       <div class="product_item" name ="product_item">
       <a class="item_inner" href = "#" number = "${d.shop_p.prod_num}" price = "${d.shop_price}">
       <div class="product"><img src = "./images/prod_num/${d.shop_p.prod_num}.png" class = "product_img" name = "${s.shop_p.prod_num}"></div>
       <div class="info_box" name = "info_box">
       <div class = "brand_logo" name ="brand_logo">
       <c:choose>
                   <c:when test="${fn:toLowerCase(d.shop_p.prod_brand) eq 'nike'}">
                   <img src='./images/${fn:toLowerCase(d.shop_p.prod_brand)}.png' alt='brand' class="logo">
                   </c:when>
                   <c:when test="${fn:toLowerCase(d.shop_p.prod_brand) eq 'adidas'}">
                   <img src='./images/${fn:toLowerCase(d.shop_p.prod_brand)}.png' alt='brand' class="logo">
                   </c:when>
                   <c:when test="${fn:toLowerCase(d.shop_p.prod_brand) eq 'jordan'}">
                   <img src='./images/${fn:toLowerCase(d.shop_p.prod_brand)}.png' alt='brand' class="logo">
                   </c:when>
                   <c:when test="${fn:toLowerCase(d.shop_p.prod_brand) eq 'new balance'}">
                   <img src='./images/${fn:toLowerCase(d.shop_p.prod_brand)}.png' alt='brand' class="logo">
                   </c:when>
                   <c:when test="${fn:toLowerCase(d.shop_p.prod_brand) eq 'vans'}">
                   <img src='./images/${fn:toLowerCase(d.shop_p.prod_brand)}.png' alt='brand' class="logo">
                   </c:when>
               </c:choose>
              
               <c:set var = "bm" value ="bookmark"/>
               <c:if test="${d.shop_bm ne 0}">
               <c:set var = "bm" value ="fill_bookmark"/>	
               </c:if>
          		
               <a href="#" class="bookmark">
               <img src="./images/${bm}.svg" class = "bookmark_icon"/>
               </a>
               
               </div>
                <c:set var = "size_num" value = "225"/>
         		<c:if test = "${d.shop_p.prod_gender eq 'w'}">
         		<c:set var = "size_num" value = "215"/>
         </c:if>
        
               <p class = "name" size="${size_num}">${d.shop_p.prod_name}</p>
               
                   
                   <div class = "price_info">
                           <div class = "amount">
                           <c:choose>
                               <c:when test="${d.shop_price > 0}">
                               <em class = "num">${d.shop_price}</em>
                               <span class = "money">원</span>
                               </c:when>
                               
                               <c:when test="${d.shop_price == 0}">
                               <em class = "num">-</em>
                               <span class = "money">원</span>
                               </c:when>
                           </c:choose>
                           </div>
                           <div class = "desc"><p>즉시구매가</p></div>
                   </div>
           </div>
         </a>
          
       </div>
      </c:forEach>
      </div>
      </div>
      
    <!-- 광고 -->
     <div class="main_ad">
      <img class="main_ad_item" src="./images/kreammain.jpg" alt="광고">
    </div>

    <!-- 스타일 게시판 -->
    <div class="stylepick_warp">
      <div class="stylepick_title">
        <span class="title_tiem">Style Picks!</span>
      </div>
      <div class="stylepick_list">
        <div class="stylepick_list_item">
          <img class="stylepick_img" src="./images/style1.jpg" alt="스타일">
        </div>
        <div class="stylepick_list_item">
          <img class="stylepick_img" src="./images/style1.jpg" alt="스타일">
        </div>
        <div class="stylepick_list_item">
          <img class="stylepick_img" src="./images/style1.jpg" alt="스타일">
        </div>
        <div class="stylepick_list_item">
          <img class="stylepick_img" src="./images/style1.jpg" alt="스타일">
        </div>
        <div class="stylepick_list_item">
          <img class="stylepick_img" src="./images/style1.jpg" alt="스타일">
        </div>
        <div class="stylepick_list_item">
          <img class="stylepick_img" src="./images/style1.jpg" alt="스타일">
        </div>
      </div>
    </div>

    <!-- 광고 -->
    <div class="main_ad">
      <img class="main_ad_item" src="./images/kreammain.jpg" alt="광고">
    </div>
	
    <!-- 인기 상품 -->

    <div class="product_popular_warp">
      <div class="product_popular_title">
      <span class="product_popular_sub">Most Popular</span>
      <span class="product_popular_sub">인기 상품</span>
    </div>
    <!--  4개 출력을 위한변수 선언 -->
   <c:set var = "size_count" value = "0"/>
    <div class="product_popular_list">
      <!-- 1번 상품-->
      <c:forEach items="${shoplist}" var="s" varStatus="statusObj">
      	<c:set var = "size_count" value = "${size_count+1}"/>
      	<c:if test = "${size_count<5}">
       <div class="product_item" name ="product_item">
       <a class="item_inner" href = "#" number = "${s.shop_p.prod_num}" price = "${s.shop_price}">
       <div class="product"><img src = "./images/prod_num/${s.shop_p.prod_num}.png" class = "product_img" name = "${s.shop_p.prod_num}"></div>
       <div class="info_box" name = "info_box">
       <div class = "brand_logo" name ="brand_logo">
       <c:choose>
                   <c:when test="${fn:toLowerCase(s.shop_p.prod_brand) eq 'nike'}">
                   <img src='./images/${fn:toLowerCase(s.shop_p.prod_brand)}.png' alt='brand' class="logo">
                   </c:when>
                   <c:when test="${fn:toLowerCase(s.shop_p.prod_brand) eq 'adidas'}">
                   <img src='./images/${fn:toLowerCase(s.shop_p.prod_brand)}.png' alt='brand' class="logo">
                   </c:when>
                   <c:when test="${fn:toLowerCase(s.shop_p.prod_brand) eq 'jordan'}">
                   <img src='./images/${fn:toLowerCase(s.shop_p.prod_brand)}.png' alt='brand' class="logo">
                   </c:when>
                   <c:when test="${fn:toLowerCase(s.shop_p.prod_brand) eq 'new balance'}">
                   <img src='./images/${fn:toLowerCase(s.shop_p.prod_brand)}.png' alt='brand' class="logo">
                   </c:when>
                   <c:when test="${fn:toLowerCase(s.shop_p.prod_brand) eq 'vans'}">
                   <img src='./images/${fn:toLowerCase(s.shop_p.prod_brand)}.png' alt='brand' class="logo">
                   </c:when>
               </c:choose>
              
               <c:set var = "bm" value ="bookmark"/>
               <c:if test="${s.shop_bm ne 0}">
               <c:set var = "bm" value ="fill_bookmark"/>	
               </c:if>
          		
               <a href="#" class="bookmark">
               <img src="./images/${bm}.svg" class = "bookmark_icon"/>
               </a>
               
               </div>
               <c:set var = "size_num" value = "225"/>
         <c:if test = "${s.shop_p.prod_gender eq 'w'}">
         <c:set var = "size_num" value = "215"/>
         </c:if>
        
               <p class = "name" size="${size_num}">${s.shop_p.prod_name}</p>
               
                   
                   <div class = "price_info">
                           <div class = "amount">
                           <c:choose>
                               <c:when test="${s.shop_price > 0}">
                               <em class = "num">${s.shop_price}</em>
                               <span class = "money">원</span>
                               </c:when>
                               
                               <c:when test="${s.shop_price == 0}">
                               <em class = "num">-</em>
                               <span class = "money">원</span>
                               </c:when>
                           </c:choose>
                           </div>
                           <div class = "desc"><p>즉시구매가</p></div>
                   </div>
           </div>
         </a>
          
       </div>
     </c:if>
      </c:forEach>
      </div>
      <!--  modal창 디자인 -->
          <div class = "bm_modal">
                <div class = "bm_modal_content">
                
                    <h2 class="bm_modal_title">관심 상품 추가</P>
                    <div class = "suggest_list">
                    <div class = "bm_modal_img_div">
                    	<img alt="" src="" style = "width:80px; height:80px;" class ="bm_img"/>
                    </div>
                    <div class = "bm_modal_product_name">
                    	<span class = "bm_product_num" style="display:none"></span>
                    	
                    	<span class = "bm_product_name"> </span>
                    </div>
                    </div>
                    <!--  li반복문 삽입 -->
                    <div class = "bm_model_list">
          <!-- 데이터 불러오기.! 처음에 부르는 샵과, searchzip에도 데이터를 넘겨줘야한다. -->
         
                    <ul style = "list-style:none; padding-left:10px; width:100%; margin-top:10px;" class = buy_size_price>
		              <%-- <c:forEach items="${booklist}" var="b" varStatus="statusObj">  --%>
		             
		              <c:forEach var="size_max" begin = "0" end = "16" step ="1" varStatus = "status">
		              	<c:set var = "size_num" value = "${size_num+5}"/>
			              	<li  class = "size_p_list" name = "size_p_list">
			              	<%-- <c:if test="${b.prod_size == size_num}">
			              		<p class = "size_p_fill" name = "size_p_fill">${size_num}</p>
			              		<img class = "price_buy_modal" src = "./img/fill_bookmark.svg"/>
			              	</c:if> --%>
			                  <p class = "size_p" name = "size_p">${size_num}</p>
			            	  <img class = "price_buy_modal" src = "./images/bookmark.svg"/>
			                </li>
			                
		              </c:forEach>
		              <%-- </c:forEach> --%>
		              <li class = "bm_confirm"> 확인 </li>
                	</ul>
                    </div>	
                    	
                    
                </div>
                
             </div> 
    
      </div> 
   </section>
  </section>
  <section class="footer">
    <div class="footer_wrap">
      <div class="footer_info_warp">
        <div class="footer_info">
          <ul class="footer_ul">
            <li class="footer_item">이용안내</li>
            <li class="footer_item">검수기준</li>
            <li class="footer_item">이용정책</li>
            <li class="footer_item">페널티 정책</li>
            <li class="footer_item">커뮤니티 가이드라인</li>
          </ul>
        </div>
        <div class="footer_info">
          <ul class="footer_ul">
            <li class="footer_item">고객지원</li>
            <li class="footer_item">공지사항</li>
            <li class="footer_item">서비스 소개</li>
            <li class="footer_item">쇼룸 안내</li>
            <li class="footer_item">판매자 방문접수</li>
          </ul>
        </div>
      </div>
      <aside class="footer_aside">
        <span class="aside_item">고객센터 1588-7813</span>
        <span class="aside_item">운영시간평일 11:00 - 18:00(토 * 일, 공휴일 휴무)</span>
        <span class="aside_item">점심시간평일 13:00 - 14:00</span>
        <span class="aside_item">1:1 문의하기는 앱에서만 가능합니다.</span>
        <button class="aside_button">자주 묻는 질문</button>
      </aside>
    </div>
    <footer class="footer_main">
      <div class="footer_main_div1">
        <span class="footer_span">크림 주식회사 · 대표김창욱 사업자등록번호:570-88-01618사업자정보확인통신 판매업:제 2021-성남분당C-0093호</span>
        <span class="footer_span">사업장소재지 : 경기도 성남시 분당구 분당내곡로 117, 8층 개인정보관리책임자:김미진 호스팅 서비스:네이버 클라우드 (주)</span>
      </div>
      <div class="footer_main_div2">
        <span class="footer_span">크림(주)는 통신판매 중개자로서 통신판매의 당사자가 아니므로 개별 판매자가 등록한 상품정보에 대해서 책임을 지지 않습니다.</span>
        <span class="footer_span">단, 거래과정에서 검수하고 보증하는 내용에 대한 책임은 당사에 있습니다.</span>
      </div>
    </footer>
  </section>
</body>

</html>