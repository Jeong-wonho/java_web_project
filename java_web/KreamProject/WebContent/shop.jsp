<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
 <%@taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>한정팜</title>
    <c:set var = "shoplist" value="${requestScope.shoplist}"/>
    <c:set var = "booklist" value="${requestScope.booklist}"/>
    <c:set var = "brandlist" value ="${requestScope.brandlist}"/>
      <link rel="stylesheet" type="text/css" href="./css/Shop.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script>
      $(function () {
    	 //  
    	//전역변수 선언
    	
    	var $prod_num;
    	var $prod_name;
    	var $id;
    	var $prod_size;
    	var $brand_list = new Array();
    	var $brand;
    	var $gender_list = new Array();
    	//필터리스트 값들
        $(".brand").click(function () {
          $(".brand").hide();
          $(".brand_in").show();
        });

        $(".brand_in>button").click(function () {
          $(".brand_in").hide();
          $(".brand").show();
        });

        $(".collection").click(function () {
          $(this).hide();
          $(".collection_in").show();
        });

        $(".collection_in>button").click(function () {
          $(".collection_in").hide();
          $(".collection").show();
        });

        $(".gender").click(function () {
          $(this).hide();
          $(".gender_in").show();
        });

        $(".gender_in>button").click(function () {
          $(".gender_in").hide();
          $(".gender").show();
        });

        $(".price").click(function () {
          $(this).hide();
          $(".price_in").show();
        });

        $(".price_in>button").click(function () {
          $(".price_in").hide();
          $(".price").show();
        });

        $(".brand").click(function () {
          $(this).hide();
          $(".brand_in").show();
        });

        $(".brand_in>button").click(function () {
          $(".brand_in").hide();
          $(".brand").show();
        });
		
        //search아이콘 클릭시 모달창 표시!

        $("#search_modal").click(function () {
          $(".modal").fadeIn();
        });

        //모달창 표시 이외의 화면 클릭시 모달창 닫힘!
        $(window).click(function (e) {
          if (e.target.className == "modal") {
            $(".modal").fadeOut();
          }
        });

    
         //ajax로 필터 데이터 보내기
        $(".checkbox").click(function(){
        	
        	if($(this).attr("name")=="brand"){
        		$brand_list.push($(this).val()); 
        		
        	}
        	else if($(this).attr("name")=="collection"){
        		$brand_list.push($(this).val()); 
        	}else if($(this).attr("name")=="gender"){
        		$brand_list.push($(this).val());
        	}
        	
        	console.log($brand_list);
        	 // 브랜드 중복 제거 (수정필요!)
        	var del_brand;
        	for(var i = 0; i < $brand_list.length; i++) {
        		  for(var j =i+1; j<$brand_list.length; j++){
        			  if($brand_list[i]==$brand_list[j]){
        				  del_brand=$brand_list[i];
        			  }
        		  }
        	};
        		
			$brand_list = $brand_list.filter(function(x){
				return x !== del_brand;
			});
			
			console.log($brand_list)
			
			//데이터 전송
			$.ajax({
       		type:"get",
       		url:"/KreamProject/search",
       		data:{"brand":$brand_list} ,//, "gender":$gender_list
       		traditional:true,
       		success:function(response){
       			
       			$(".search_result_list").empty();
       			$(".search_result_list").html(response); 
       			
       		}
        	});
			
			
			//널값일 때, 데이터 초기값으로 돌아옴
         	if($brand_list.length == 0){
        		var basic_page = "1";
        		$.ajax({
            		type:"GET",
            		url:"/KreamProject/search",
            		datatype:"html",
            		data:{"basic_page":basic_page},//, "gender":$gender_list
            		success:function(response){
            			$(".search_result_list").empty();
            			$(".search_result_list").html(response);
            		}
        	});
        	}
        });
         
         
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
         
         $(".bookmark").click(function(e) {
        	 var $parent = $(this).parent(".brand_logo").parent(".info_box");
        	 $prod_num = $parent.find('a').attr('number');
        	 $prod_name = $parent.find('.name').text();
        	 $prod_size = $parent.find('.name').attr("size");
        	 
        	 console.log($prod_num);
        	 
        	 
        	 var img_url = "./images/prod_num/"+$prod_num +".png";
        	 $('.bm_img').attr("src", img_url);
        	 $('.bm_product_name').text($prod_name);
        	 $('.bm_product_num').text($prod_num);
        	 $('.bm_modal').fadeIn();
        	 
        	 /*  $.ajax({
        		 type:"get",
        		 url:"./bookmark",
        		 datatype:"html",
        		 //data: {id:"customer_id"} 추가예정 임의로 kms1234 적용
				 data:{"id":"kms1234", "prod_num":$prod_num},
				 
				 success: function(response){
					 
					 alert("북마크불러오기 성공");
					 
				 }
        	 }); */ 
        	  //push 방법 + for문 돌려서 데이터만 넣어보자!
        	var $shoplist = new Array();
          	$shoplist = '<c:out value="${shoplist}"/>';
          	var $length = $('div.modal').attr("name")
          	
         });
        // modal창 밖을 클릭시 원래 창으로 돌아오기! 
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
        	var fill_bm = './images/fill_bookmark.svg';    	
        	var $prod_size = $(this).find('p').text();
        	
        	var $img = $(this).find('img');
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
      });
   
    </script>
  </head>
  <body>
    <section class="shop_main">
        <div class = "modal" name="${fn:length(shoplist)}">
        
            <div class = "modal_content" title="클릭하면 창이 닫힙니다.">
                <input type ="text" placeholder ="검색어를 입력하세요"/>
            </div>
        </div>
        <br>
    
        
        <section class="main">
            <div class="shop_title_div">
            <h2 class="shop_title_h2">SHOP</h2>
            </div>
                <section class = "outer">
                <div style="width:20%; border:none; float:left; padding-top: 5px;">
                <span style = "float:left; margin-left:20px;">필터</span><br>
                    <ul class ="classification">
                    <li class="brand"><button>브랜드<br><span>브랜드</span><img src = "./images/plus.svg"></button></li>
                    <li class="brand_in" style="display:none">
                        <button class = "class_button">브랜드<img src = "./images/minus.svg">
                        </button>  
                        
                        <c:forEach items="${brandlist}" var="b" varStatus="statusObj">
                        <div><ul class = "brand_in_list" name = "brand_in_list" style="padding-left:20px; padding-bottom: 10px; display:grid" >
                            <li class = "checked"><input class = "checkbox" type="checkbox" style = "float:left" name = "brand" value = "${b.prod_brand }"/>
                                <label>${b.prod_brand}</label></li>
                        </ul></div>
                        </c:forEach>
                        
                    </li>
                    <li class="collection"><button>컬렉션<br><span>컬렉션</span><img src = "./images/plus.svg"></button></li>
                    <li class="collection_in" style="display:none">
                        <button>컬렉션<img src = "./images/minus.svg"></button>
                        <div><ul class ="brand_in_list" name = brand_in_list style="padding-left:20px; padding-bottom: 10px; display:grid" >
                            <li class = "checked"><input class = "checkbox" type="checkbox" style = "float:left" name = "collection" value = "Dunk"/>
                            <label>Dunk</label></li>
                            <li class = "checked"><input class = "checkbox" type="checkbox" style = "float:left" name = "collection" value = "992"/>
                            <label>992</label></li>
                            <li class = "checked"><input class = "checkbox" type="checkbox" style = "float:left" name = "collection" value = "University"/>
                            <label>Retro High OG University</label></li>
                            <li class = "checked"><input class = "checkbox" type="checkbox" style = "float:left" name = "collection" value = "Sacai"/>
                            <label>x Sacai</label></li>
                            <li class = "checked"><input class = "checkbox" type="checkbox" style = "float:left" name = "collection" value = "SB"/>
                            <label>SB</label></li>
                            <li class = "checked"><input class = "checkbox" type="checkbox" style = "float:left" name = "collection" value = "Yeezy"/>
                            <label>Yeezy</label></li>
                            <li class = "checked"><input class = "checkbox" type="checkbox" style = "float:left" name = "collection" value = "Peaceminusone"/>
                            <label>x Peaceminusone</label></li>
                            <li class = "checked"><input class = "checkbox" type="checkbox" style = "float:left" name = "collection" value = "SB"/>
                            <label>SB Dunk</label></li>
                            <li class = "checked"><input class = "checkbox" type="checkbox" style = "float:left" name = "collection" value = "Vault"/>
                            <label>Vault</label></li>
                            <li class = "checked"><input class = "checkbox" type="checkbox" style = "float:left" name = "collection" value = "Kasina"/>
                            <label>x Kasina</label></li>
                            <li class = "checked"><input class = "checkbox" type="checkbox" style = "float:left" name = "collection" value = "Undercover"/>
                            <label>x Undercover</label></li>
                            <li class = "checked"><input class = "checkbox" type="checkbox" style = "float:left" name = "collection" value = "Stussy"/>
                            <label> x Stussy</label></li>
                            <li class = "checked"><input class = "checkbox" type="checkbox" style = "float:left" name = "collection" value = "Air"/>
                            <label>Air Max</label></li>
                        </ul></div>
                    </li>
                    <li class="gender"><button>성별<br><span>성별</span><img src = "./images/plus.svg"></button></li>
                    <li class="gender_in" style="display:none">
                        <button>성별<img src="./images/minus.svg"></button>
                        <div>
                        <ul class = "gender_in_class" style="padding-left:20px; padding-bottom: 10px; display:grid" >
                            <li class="checked"><input class ="checkbox" type="checkbox" style = "float:left" name = "gender" value ="m"/>
                            <label>남자</label></li>
                            <li class = "checked"><input class="checkbox" type="checkbox" style = "float:left" name = "gender" value ="w"/>
                            <label>여자</label></li>
                        </ul></div>
                    </li>
                </ul></div>
                
       <div class = "search_result_list">
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
      </c:forEach> 
      
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
          
          <c:forEach items="${booklist}" var="bm"  varStatus="statusObj">
          	<c:forEach items="${shoplist}" var="s" varStatus="statusObj">
          		 <c:if test =  "${bm.bm_p.prod_num eq s.shop_p.prod_num}">
          		 	<c:set var="prod_num" value="${bm.bm_p.prod_num}"/>
          		 	<c:set var="bm_size" value = "${bm.prod_size}"/> 
          		 </c:if>
          	</c:forEach>
          </c:forEach>
         			
         			
                    <ul style = "list-style:none; padding-left:10px; width:100%; margin-top:10px;" class = buy_size_price>
		           
		             
		              <c:forEach var="size_max" begin = "0" end = "16" step ="1" varStatus = "status">
		              	<c:set var = "size_num" value = "${size_num+5}"/>
			              	<li  class = "size_p_list" name = "size_p_list">
			              	
			                  <p class = "size_p" name = "size_p">${size_num}</p>
			            	  <img class = "price_buy_modal" src = "./images/bookmark.svg"/>
			                </li>
			                
		              </c:forEach>
		          
		              <li class = "bm_confirm"> 확인 </li>
                	</ul>
                    </div>	
                </div>
                
             </div>  
       </div>
       </section>
        </section>
    </section>
  </body>
</html>
