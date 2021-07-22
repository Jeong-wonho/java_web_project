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
  <link rel="stylesheet" type="text/css" href="./css/Bidsell.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script>
 
    $(function () {
    	  var img_url = "./images/prod_num/"+${p.prod_num}+".png";
      	 $('.productpic').attr("src", img_url);
      //DOM트리에서a 객체 찾기
      var $menuObj = $('.header_link');
      var $kreamObj = $('.nav_link');
      //DOM트리에서 section객체 찾기
      var $section = $('.main_section');
      $menuObj.click(function () {
        //클릭된 현재 객체의 href속성값 얻기 : .attr
        var href = $(this).attr('href');
        switch (href) {
          case './Login.html':
          case './Mypage.html':
          case './Tradecomplete.html':
            $section.load(href, function (responseTxt, statucTxt, xhr) {
              if (statucTxt == "error")
                alert("Error: " + xhr.status + ":" + xhr.statucTxt);
            });
            break;
 
        }
        return false;
      });
      $kreamObj.click(function () {
        //클릭된 현재 객체의 href속성값 얻기 : .attr
        var href = $(this).attr('href');
        switch (href) {
          case './Buysize.html':
          case './Shop.html':
            $section.load(href, function (responseTxt, statucTxt, xhr) {
              if (statucTxt == "error")
                alert("Error: " + xhr.status + ":" + xhr.statucTxt);
            });
            break;

        }
        return false;
      });
      // 승환시작
      var $section_right = $('.rightcontent');
      $(document).ready(function(){
    	  $section_right.load('./bidsell', function (responseTxt, statucTxt, xhr) {
              if (statucTxt == "error")
                alert("Error: " + xhr.status + ":" + xhr.statucTxt);
            });
      });
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