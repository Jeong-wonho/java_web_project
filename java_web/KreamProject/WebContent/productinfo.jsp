<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var = "p" value="${requestScope.p}"/>
<c:set var = "minmap" value = "${requestScope.minmap}"/>
<c:set var = "recent" value = "${requestScope.recent}"/>
<c:set var = "colmin" value = "${requestScope.colmin}"/>
<c:set var = "colmax" value = "${requestScope.colmax }"/>
<c:set var = "bm" value = "${requestScope.bm}"/>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
$(function(){
    //search아이콘 클릭시 모달창 표시!
    var $prod_num;
    var $prod_name;
    var $id;
    var $prod_size;
    var $brand_list = new Array();
    var $brand;    
	var $section = $('.main_section');
	
	
    $(".btn_size").click(function(){
            $(".all_modal").fadeIn();
        });

    
        //모달창 표시 이외의 화면 클릭시 모달창 닫힘!
    $(window).click(function(e) {
          if(e.target.className == "all_modal"){
              $(".all_modal").fadeOut();
            }
        });

    $(".seller").click(function(){
            $(".sell_modal").fadeIn();
        });

        //모달창 표시 이외의 화면 클릭시 모달창 닫힘!
    $(window).click(function(e) {
          if(e.target.className == "sell_modal"){
              $(".sell_modal").fadeOut();
              $section.load('./sellcheck', function (responseTxt, statucTxt, xhr) {
	              if (statucTxt == "error")
	                alert("Error: " + xhr.status + ":" + xhr.statucTxt);
	            });
			 return false;
            }
        });

    $(".buyer").click(function(){
        $(".buy_modal").fadeIn();
    });

     //모달창 표시 이외의 화면 클릭시 모달창 닫힘!
    $(window).click(function(e) {
          if(e.target.className == "buy_modal"){
              $(".buy_modal").fadeOut();
              $section.load('./buycheck', function (responseTxt, statucTxt, xhr) {
	              if (statucTxt == "error")
	                alert("Error: " + xhr.status + ":" + xhr.statucTxt);
	            });
			 return false;
            }
        });
	//모든사이즈 클릭하고 size클릭시 모든사이즈-> 선택사이즈로 변경
    $(".all_size_price>.size_p_list").click(function(e){
  	  var p = $(this).find('p');
  	  var span = $(this).find('span');
  	  var prod_size = p.text();
  	  var prod_price = span.text();
  	  
  	  var list = $(this).parent('ul').find('li');
  	  if(prod_size == '모든사이즈'){
  		  alert("확인");
  	  }
  	  
  	  if(prod_price == "-"){
  		  prod_price = 0;
  	  }
  	  
  	 $('span.right_size').text(prod_size);
  	$(".all_modal").fadeOut();
     });
   
   $(".sell_size_price>.size_p_list").click(function(e){
	  var p = $(this).find('p');
	  var span = $(this).find('span');
	  var prod_size = p.text();
	  var prod_price = span.text();
	  
	  var list = $(this).parent('ul').find('li');
	  console.log(list);
	  if(prod_size == '모든사이즈'){
		  alert("확인");
	  }
	  
	  if(prod_price == "-"){
		  prod_price = 0;
	  }
	  
	 
	  
   });
   
   $(".buy_size_price>.size_p_list").click(function(e){
		  var p = $(this).find('p');
		  var span = $(this).find('span');
		  var prod_size = p.text();
		  var prod_price = span.text();
		  
		  var list = $(this).parent('ul').find('li');
		  console.log(list);
		  if(prod_size == '모든사이즈'){
			  alert("확인");
		  }
		  
		  if(prod_price == "-"){
			  prod_price = 0;
		  }
		  
		  console.log(prod_size);
		  console.log(prod_price);
	   });
   
   $(".bookmark").click(function(e) {
  
  	 $prod_num = '<c:out value="${p.prod_num}"/>';
  	 $prod_name =  '<c:out value="${p.prod_name}"/>';
  	 $prod_size =  '<c:out value="${p.prod_num}"/>';
  	 
  	 
  	 
  	 var img_url = "./images/prod_num/"+$prod_num +".png";
  	 $('.bm_img').attr("src", img_url);
  	 $('.bm_product_name').text($prod_name);
  	 $('.bm_product_num').text($prod_num);
  	 $('.bm_modal').fadeIn();
  	 });
   
  });
</script>
<style> 

      section.main {
        /* padding: 20px; */
        /* text-align:center; */
        margin-top:50px;
        text-align:center;
        width:1300px;'
        margin : auto;
      }

      section.main>div.shop_info{
                border:1px none;
                width:100%;
                margin: 0 auto;
                height:100%;
       }

      section.main> div.shop_info  > figure > img {
        float: left;
        margin: 0px;
        width: 562px;
        height: 562px;
        border-radius: 	12px;
      }
      section.main > div.shop_info > section {
        float: right;
      }

      span.right {
        float: right;
        font-weight: bold;
        font-size: 20px;
      }
      .all_modal{
          position:fixed; width:100%; height:100%;
          background-color: rgb(0,0,0);
          background-color: rgba(0,0,0,0.4); top:0; left:0;
          display:none; 

          }

      .all_modal_content{
          width:400px; height:450px;
          background:#fff; border-radius:10px;
          position:relative; top:50%; left:50%;
          margin-top:-175px; margin-left:-200px;
          text-align:center;
          box-sizing:border-box; padding:74px 0;
          line-height:23px; cursor:pointer;
          padding-top:10px; overflow:auto;
          }
      .sell_modal{
          position:fixed; width:100%; height:100%;
          background-color: rgb(0,0,0);
          background-color: rgba(0,0,0,0.4); top:0; left:0;
          display:none; 

          }

      .sell_modal_content{
          width:400px; height:450px;
          background:#fff; border-radius:10px;
          position:relative; top:50%; left:50%;
          margin-top:-175px; margin-left:-200px;
          text-align:center;
          box-sizing:border-box; padding:74px 0;
          line-height:23px; cursor:pointer;
          padding-top:10px; overflow:auto;
          }

      .buy_modal{
      position:fixed; width:100%; height:100%;
      background-color: rgb(0,0,0);
      background-color: rgba(0,0,0,0.4); top:0; left:0;
      display:none; 

      }
		
      .buy_modal_content{
          width:400px; height:450px;
          background:#fff; border-radius:10px;
          position:relative; top:50%; left:50%;
          margin-top:-175px; margin-left:-200px;
          text-align:center;
          box-sizing:border-box; padding:74px 0;
          line-height:23px; cursor:pointer;
          padding-top:10px; overflow:auto;
          }
      .all_size_price ul .sell_size_price ul, .buy_size_price ul{
        float:left;
        list-style:none;
        margin-left:1em;
        padding-left:0px;
      }

      .all_size_price ul .sell_size_price li, .buy_size_price li{
        padding-right:0px;
        line-height : 1.5em;
        font-size:12px;
      }
	  .size_p_list{
	   border: solid 1px gray;
	   width : 100px; 
	   border-radius:8px;
	   margin:5px;
	   display:inline-block;
	  }
	  
	  .size_p{
	   font-size : 10px;
	    margin :0px;
	    padding-bottom:5px;
	  }
	  
	  .price_sell{
	  font-size : 10px; 
	  font-weight:bold;
	  color:red;
	  }
	  
	  .price_buy{
	  font-size : 10px;
	  font-weight:bold;
	  color:lightgreen;
	  }
      section.main  > div.shop_info > section > div > a {
        text-decoration: none;
        font-size: 5px;
        color: white;
        border-style: solid;
        text-align: left;
        display: inline-block;
        border-radius: 10px;
        border: hidden;
      }
      
   		
      .sub_list{
      margin-left:10px;
      }
      div.right_section{
      	width:650px;
    	text-align:right;
      	      }
      section.main > div.shop_info > section > div > a:hover {
        box-shadow: 0 12px 16px 0 rgba(0, 0, 0, 0.24),
          0 17px 50px 0 rgba(0, 0, 0, 0.19);
      }
      
      th,
      td {
        padding: 8px;
        padding-right: 130px;
        text-align: center;
        border-bottom: 1px solid #ddd;
        
      }
      section.main > div.shop_info > section > div > button {
        padding: 10px 220px;
        border: lightgray 1px solid;
        background-color: white;
        color: lightgray;
        cursor: pointer;
      }

      div.board > div>div>ul>li{
        list-style:none;
      }

      div.board{
        width:300px;
        margin-left: 1cm;
      }

      div.board>img{
        width: 300px;
        height: 313px;
      }

      div.board{
        display: inline-block;
        width:310px;
        height: 500px;
      }

      div.board>div>p{
        text-overflow: ellipsis;
        white-space: nowrap;
        width:300px;
      }
      div.board > div >div> img{
        float: left;
        width: 60px;
        height: 60px;
      }
      div p{
        font-size:11px;
      }
	  .prod_info_wrap > dl{
		display:flex;
		justify-content: space-between;
		font-size:14px;
		margin-bottom: 2px;
		}
		
	  .bookmark{
         width:20px;
         height:20px;
        }
	
	   .bm_modal_title{
	   padding: 0px;
	   min-height: 60px;
	   font-size:18px;
	   font-weight: 700;
	   text-align:center;
	   }
	   
	   .suggest_list{
	    display:flex;
	    border-bottom: 1px solid lightgray;
	    padding-bottom:10px;
	   }
	   
	   .bm_modal_img_div{
	    width:80px;
	    height:80px;
	    margin-left:20px;
	   }
	   
	   .bm_modal_product_name{
	   margin: auto;
	   }
	   
	   .bm_product_name{
	    font-size:15px;
	   }
	   
	   .buy_size_price ul{
        float:left;
        list-style:none;
        margin-left:1em;
        padding-left:0px;
      }

      .buy_size_price li{
        padding-right:0px;
        line-height : 1.5em;
        font-size:12px;
      }
	  .size_p_list{
	   border: solid 1px gray;
	   width : 100px;
	   height: 60px; 
	   border-radius:8px;
	   margin:5px;
	   display:inline-block;
	  }
	  .size_p_fill{
	    font-size : 15px;
	    margin :0px;
	    padding-top: 10px;
	    
	  }
	  .size_p{
	    font-size : 15px;
	    margin :0px;
	    padding-top: 10px;
	   
	  }
	  
	  .price_buy_modal{
	  	font-weight: border;
	  }
	   
	  .bm_confirm{
	   font-weight:700; 
	   font-size:20px; 
	   border: 2px solid black;
	   border-radius:8px;
	   width : 150px;
	   height:60px;
	   margin-top:10px;
	   vertical-align:center;
	   padding-top: 20px;
	   margin-left: 110px;
	   background-color:white;
	  }
	  }
	 
</style>
  <section class="main">
      <div class="shop_info">
      
      <figure class="pic">
        <img src="./images/prod_num/${p.prod_num}.png" />
      </figure>
      <section>
        <div class = "right_section">
        <c:set var="minprice" value="5000000000"/>
		
        <!--  0을 제외한 최소값구하기 -->
        <c:forEach var = "min" items="${colmin}" varStatus = "status">
        	<c:if test = "${min > 0}">
        	<c:if test = "${minprice>min}">
        		<c:set var="minprice" value = "${min}"/>
        	</c:if>
        	</c:if>
        </c:forEach>
        <c:if test = "${minprice eq 5000000000}">
        <c:set var = "minprice" value = "-"/>
        </c:if>
        
        <!--0을 제외한 최대값 구하기 -->
        <c:set var="maxprice" value="0"/>
        <c:forEach var = "max" items="${colmax}" varStatus = "status">
        	<c:if test = "${max>maxprice}">
        		<c:set var="maxprice" value = "${max}"/>
        	</c:if>
        </c:forEach>
        <c:if test = "${maxprice eq 0}">
        <c:set var = "maxprice" value = "-"/>
        </c:if>
        
        <!--  해당 아이디에 관심상품으로 등록되어 있으면 북마크 변경!  -->
        <c:set var = "book" value ="bookmark"/>
        <c:set var = "loop_flag" value = "false"/>
          <h2 style="margin-bottom: 0px; margin-top:0px;">
            ${p.prod_name}
            
            <c:forEach var="b" items = "${bm}" varStatus="status">
            <c:if test="${not loop_flag}">
           
           		<c:set var = "book" value ="bookmark"/>
				<c:if test="${p.prod_num == b.bm_p.prod_num}">	
				<c:set var = "book" value ="fill_bookmark"/>	
            	
            	<c:set var= "loop_flag" value = "true" />
            </c:if>
           
            </c:if>
		</c:forEach>
			<a href ="#" class = "bookmark"><img
	         src="./images/${book}.svg"/></a>
          </h2>
        </div>
        <br />
        
        
        <!--  사이즈, 모든 사이즈 출력 -->
        <div style="padding-bottom: 10px; font-size:20px; display:flex; justify-content:space-between;" class = "size_info" >
          <span style="font-size: 20px" class = "span_size">사이즈</span>
          <button style = "border:none; padding:0px; color:black; float:right;" class = "btn_size">
          <span class="right_size" style="font-weight:bold;">모든사이즈</span>
          <img
              src="./images/arrow-down-circle.svg"
              style="vertical-align: sub"
          /></button>
        </div>
        <hr />
        <div style="padding-bottom: 40px; display:revert;">
          <span style="font-size: 20px; vertical-align: middle; text-align:left; float:left;">
            최근 거래가</span>
            <span class="right" style="font-weight:bold;">
            <c:if test = "${empty recent[0].order_price}">
					-
            </c:if>
            ${recent[0].order_price}원
            </span>
          <br />
        </div>
        
        <!--  모든 사이즈 모달 시작 -->
        <div class = "all_modal">
            <div class = "all_modal_content">
                <P style="margin:0px; font-size:12px; font-weight:bold">사이즈 선택</P>
                <P style = "font-size: 5px; color:gray; margin:0px;"> 즉시 판매가(원)</P>
            <div>
              <ul style = "list-style:none; padding-left:10px; width:100%;" 
               class = all_size_price>
                <li  class = "size_p_list" style = "pointer-events: none;">
                  <p class = "size_p">모든사이즈</p>
                  <span class = "price_buy">${maxprice}</span>
                </li>
                
                <c:set var = "size_num" value = "225"/>
                <c:if test = "${p.prod_gender eq 'w'}">
                <c:set var = "size_num" value = "215"/>
                </c:if>
                
              <c:forEach var="size_max" items="${colmax}" begin = "0" end = "16" step ="1" varStatus = "status">
              	<c:set var = "size_num" value = "${size_num+5}"/>
	              	<li  class = "size_p_list">
	                  <p class = "size_p">${size_num}</p>
	          		<c:if test = "${size_max == 0}">
	                  <c:set var = "size_max" value = "-"/>
	                </c:if>  
	            	<span class = "price_buy">${size_max}</span>
	                </li>
              </c:forEach>
                </ul>
            </div>
          </div>
        </div>
        <!--  buy_modal 시작 -->
        <div class = "buy_modal">
          <div class = "buy_modal_content">
              <P style="margin:0px; font-size:12px; font-weight:bold">사이즈 선택</P>
              <P style = "font-size: 5px; color:gray; margin:0px;"> 즉시 구매가(원)</P>
          <div>
          
            <ul style = "list-style:none; padding-left:10px; width:100%;" class = sell_size_price>
            	<li  class = "size_p_list" style = "pointer-events: none;">
                  <p class = "size_p">모든사이즈</p>
                  
                 <span class = "price_sell">${minprice}</span>
                </li>
              	<c:set var = "size_num" value = "225"/>
                <c:if test = "${p.prod_gender eq 'w'}">
                <c:set var = "size_num" value = "215"/>
                </c:if>
 			<c:forEach var="size_min" items="${colmin}" begin = "0" end = "16" step ="1" varStatus = "status">
              	<c:set var = "size_num" value = "${size_num+5}"/>
	              	<li  class = "size_p_list">
	                  <p class = "size_p">${size_num}</p>
	          		<c:if test = "${size_min == 0}">
	                  <c:set var = "size_min" value = "-"/>
	                </c:if>  
	            	<span class = "price_sell">${size_min}</span>
	                </li>
              </c:forEach>
              </ul>
          </div>
          </div>
          </div>
          <!--  sell_modal 시작 -->

          <div class = "sell_modal">
            <div class = "sell_modal_content">
                <P style="margin:0px; font-size:12px; font-weight:bold">사이즈 선택</P>
                <P style = "font-size: 5px; color:gray; margin:0px;"> 즉시 판매가(원)</P>
            <div>
              <ul style = "list-style:none; padding-left:10px; width:100%;" 
               class = buy_size_price>
                <li  class = "size_p_list" style = "pointer-events: none;">
                  <p class = "size_p">모든사이즈</p>
                  <span class = "price_buy">${maxprice}</span>
                </li>
                
                <c:set var = "size_num" value = "225"/>
                <c:if test = "${p.prod_gender eq 'w'}">
                <c:set var = "size_num" value = "215"/>
                </c:if>
                
              <c:forEach var="size_max" items="${colmax}" begin = "0" end = "16" step ="1" varStatus = "status">
              	<c:set var = "size_num" value = "${size_num+5}"/>
	              	<li  class = "size_p_list">
	                  <p class = "size_p">${size_num}</p>
	          		<c:if test = "${size_max == 0}">
	                  <c:set var = "size_max" value = "-"/>
	                </c:if>  
	            	<span class = "price_buy">${size_max}</span>
	                </li>
              </c:forEach>
                </ul>
            </div>
          </div>
        </div>
        <div style="display: flex; justify-content: space-around; margin-bottom: 50px">
           <a href="#" class="buyer">
          <div style="height:60px; width:260px; background-color: #ff5050; float:left; 
          border-radius:10px;">         
            <span style="font-size: 1rem; font-weight: bold; float : left;
            color:white; margin-left: 10px; margin-right:10px; padding-top: 20px;">구매</span>
            <div style="display: inline-block; margin-left:40px; margin-top: 5px;">
              <span style="margin-left:40px;
               margin-top:10px;
               margin-bottom: 0px; 
               color: white; 
               font-weight: 900; 
               font-size:15px; display:block;">${minprice}</span>
               <span style="color:white; font-weight: lighter; font-size: 10px; margin-left:40px;">즉시구매가</span></div>
         
          </div>
           </a>
         
          <a href="#" class="seller">
           <div style="height:60px; width:260px; background-color: #33cc33; display: inline-block;
          border-radius:10px;">
            <span style="font-size: 1rem; font-weight: bold; float : left;
            color:white; margin-left: 10px; margin-right:10px; padding-top: 20px;">판매</span>
            <div style="display: inline-block; margin-left:40px; margin-top: 5px;">
            <span style="margin-left:40px;
              margin-top:10px;
              margin-bottom: 0px; 
              color: white; 
              font-weight: 900; 
              font-size:15px; display:block;">${maxprice}</span>
              <span style="color:white; font-weight: lighter; font-size: 10px; margin-left:40px;">즉시판매가</span>
            </div>
          </div>
          </a          >
        </div>
        <div style="width: 100%; margin-bottom: 30px; ">
        <!--   <span style="font-weight: bold">구매 전 꼭 확인해주세요!</span> -->
        </div>
        <!-- 체결 거래 값 불러오기 -->
        
        <div style="width: 100%">
          <h3 style= "display:flex; border-bottom: solid 1px lightgray; padding-bottom: 10px;">체결 거래</h3>
          <table style = "padding-left:50px; width: 650px;">
            <tr>
              <th>사이즈</th>
              <th>거래가</th>
              <th>거래일</th>
            </tr>
            <c:forEach items="${recent}" var = "r" varStatus = "statusObj" >
            	<tr>
              		<td>${r.order_size}</td>
              		<td>${r.order_price}원</td>
             	 	<td>${r.order_date}</td>
            </tr>
            </c:forEach>
          </table>
        </div>
        <div class = "prod_info" style=" margin-bottom:10px; padding-bottom:10px; border-bottom: 1px solid lightgray;">
         <h3 style= "display:flex; padding-top:30px;">상품정보</h3>
         </div>
         <div class = "prod_info_wrap">
         <dl><dt>브랜드</dt><dt>${p.prod_brand}</dt></dl>
         <dl><dt>모델번호</dt><dt>${p.prod_modelnum}</dt></dl>
         <dl><dt>출시일</dt><dt>${p.prod_releaseddt}</dt></dl>
         <dl><dt>발매가</dt><dt>${p.prod_releaseprice}</dt></dl> 
         </div>
         
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
		             <c:set var = "size_num" value = "225"/>
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
         
      </section>
    <br>
    <div style = "clear:both;">
    <br>
    <p style="text-align:center; font-size:1.5em; font-weight:700; margin-bottom: 20px;">KREAM의 거래방식을 확인해 주세요</p>
    <img src = "./images/이미지 140.jpg"style = "width:100%"/></div>
	
    </div>
  </section>
