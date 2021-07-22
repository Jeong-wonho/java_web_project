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
  <link rel="stylesheet" type="text/css" href="./css/Bidbuy.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
	$(function(){
		 var $section_right = $('.rightcontent');
	      $(document).ready(function(){
	    	  $section_right.load('./bidbuy', function (responseTxt, statucTxt, xhr) {
	              if (statucTxt == "error")
	                alert("Error: " + xhr.status + ":" + xhr.statucTxt);
	            });
	      });
	      
	      
	      var img_url = "./images/prod_num/"+${p.prod_num}+".png";
     	 $('.productpic').attr("src", img_url);
	});
 
</script>
</head>
<c:set var="p" value="${requestScope.p}"></c:set>
<body>
<section class="main_section">
  <aside class="product_back">
    <br>
    <div class="white">
      <img class="productpic" src="" alt="연습사진">
      <br>
      <br>
      <div class="product_info">
        새상품 · <span class="prod_modelnum">${p.prod_modelnum}</span></p>
        <span class="prod_name">${p.prod_name}</span></p>
        <span class="order_size">${user_size}</span>
        </div>
      <br><br><br>
    </div>
  </aside>
    <section class="rightcontent">
  </section>
  </section>
</body>
</html>
