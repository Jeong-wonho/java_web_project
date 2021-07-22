<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>

<html>
  <head>
    <meta charset="UTF-8" />
    <title>Insert title here</title>
    <c:set var = "shoplist" value="${requestScope.shoplist}"/>
    <c:set var = "booklist" value="${requestScope.booklist}"/>
      	<script>
    	// 전역 변수 만들기
    	var $prod_num;
    	var $prod_name;
    	var $id;
    	var $prod_size;
    	var $brand_list = new Array();
    	var $brand;
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
        
         //북마크 클릭할때 북마크 정보 데이터 보내기
         var id = "kms1234";
         
         $(".bookmark").click(function(){
        	 var $parent = $(this).parents(".info_box");
        	 $prod_num = $parent.find('a').attr('number');
        	 var $prod_name = $parent.find('.name').text();
        	 var $prod_size = $parent.find('.name').attr("size");
        	 
        	 
        	 var img_url = "./images/prod_num/"+$prod_num +".png";
        	 $('.bm_img').attr("src", img_url);
        	 $('.bm_product_name').text($prod_name);
        	 $('.bm_product_num').text($prod_num);
        	 $('.bm_modal').fadeIn();
        	 
        	 
           });
         
         // 모달창 바깥 클릭시 자동으로 모달창 닫힘
         $(window).click(function(e) {
             if(e.target.className == "bm_modal"){
                 $(".bm_modal").fadeOut();
               }
           });
         
         $(".bm_confirm").click(function(e) {
             $(".bm_modal").fadeOut();
       		});
         
        //북마크 클릭시 데이터 전송하기
        // id, 사이즈, 상품정보
            $(".size_p_list").click(function(e){
            //클릭시 데이터 넘기기!
        	var fill_bm = './images/fill_bookmark.svg';    	
        	var $prod_size = $(this).find('p').text();
        	
        	var $img = $(this).find('.price_buy');
        	console.log($img);
        	$img.attr("Src", fill_bm);
			
        	//클릭시 데이터 전송!
          	$.ajax({
        		type:"get",
        		url:"./bookmark",
        		datatype:"html",
        		data:{"id":id, "prod_num":$prod_num, "prod_size":$prod_size}, // id 추가헤야함.!
        		
        		success: function(response){
        			alert("전송성공");	
        		}

        	});
        	
        	
        }); 
 
         
	</script>
    <style>

    </style>
  </head>
  <body>
     <!-- 상품 수량 출력--> 
     	
          <div class="filter_result">
          <span class="title">상품</span>
          <span class="amount">${fn:length(shoplist)}</span>
        </div>
        
      <!-- 이곳부터 상품 정보 등장-->
      <c:forEach items="${shoplist}" var="s" varStatus="statusObj">
            <div class="product_item" name ="product_item">
              <a class="item_inner" href = "#" number = "${s.shop_p.prod_num}" price = "${s.shop_price}">
                <div class="product"><img src = "./images/prod_num/${s.shop_p.prod_num}.png" class = "product_img" name = "${s.shop_p.prod_num}"></div>
                <div class="info_box" name = "info_box">
                    <div class = "brand_logo" name ="brand_logo">
                    <c:choose>
                        <c:when test="${fn:toLowerCase(s.shop_p.prod_brand) eq 'nike'}">
                        <img src='images/${fn:toLowerCase(s.shop_p.prod_brand)}.png' alt='brand' class="logo">
                        </c:when>
                        <c:when test="${fn:toLowerCase(s.shop_p.prod_brand) eq 'adidas'}">
                        <img src='images/${fn:toLowerCase(s.shop_p.prod_brand)}.png' alt='brand' class="logo">
                        </c:when>
                        <c:when test="${fn:toLowerCase(s.shop_p.prod_brand) eq 'jordan'}">
                        <img src='images/${fn:toLowerCase(s.shop_p.prod_brand)}.png' alt='brand' class="logo">
                        </c:when>
                        <c:when test="${fn:toLowerCase(s.shop_p.prod_brand) eq 'new balance'}">
                        <img src='images/${fn:toLowerCase(s.shop_p.prod_brand)}.png' alt='brand' class="logo">
                        </c:when>
                        <c:when test="${fn:toLowerCase(s.shop_p.prod_brand) eq 'vans'}">
                        <img src='images/${fn:toLowerCase(s.shop_p.prod_brand)}.png' alt='brand' class="logo">
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
      </c:forEach> 
      <div class = "bm_modal">
                <div class = "bm_modal_content">
                
                    <h2 class="bm_modal_title">관심 상품 추가</P>
                    <div class = "suggest_list">
                    <div class = "bm_modal_img_div">
                    	<img alt="" src="" style = "width:80px; height:80px;" class ="bm_img"/>
                    </div>
                    <div class = "bm_modal_product_name">
                    	<span class = "bm_product_num" style="display:none" number = ""></span>
                    	
                    	<span class = "bm_product_name"> </span>
                    </div>
                    </div>
                    <!--  li반복문 삽입 -->
                    <div >
                    
                    <ul style = "list-style:none; padding-left:10px; width:100%; margin-top:10px;" class = buy_size_price>
		              <c:forEach var="size_max" begin = "0" end = "16" step ="1" varStatus = "status">
		              	<c:set var = "size_num" value = "${size_num+5}"/>
			              	<li  class = "size_p_list">
			                  <p class = "size_p">${size_num}</p>
			          		<c:if test = "${size_max == 0}">
			                  <c:set var = "size_min" value = "-"/>
			                </c:if>  
			            	<img class = "price_buy" src = "./images/bookmark.svg"/>
			                </li>
		              </c:forEach>
		              <li class = "bm_confirm"> 확인 </li>
                	</ul>
                    </div>	
                    	
                    
                </div>
                
             </div>  
       
      
  </body>

  
</html>
