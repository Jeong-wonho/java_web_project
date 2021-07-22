<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <c:set var="c" value="${requestScope.c}" />
    <c:choose>
      <c:when test="${empty c}">
        <%--고객상세 실패인 경우 --%>
          <script>
            alert("고객 상세 정보 보기 실패 : ${requestScope.msg}");
          </script>
      </c:when>
      <c:otherwise>
 
        <link rel="stylesheet" type="text/css" href="./css/Mypage.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script>
          $(function () {
            var $menuObj = $('.mypage_link');
            var $section = $('.main_menu');
            $menuObj.click(function () {
              var href = $(this).attr('href');
              switch (href) {
                case './profile':
                case './address':
                case './Buying.html':
                case './Selling.html':
                case './Wish.html':
                  var loginedId = sessionStorage.getItem("loginedId");
                  if (loginedId != null && loginedId != '') {
                    $section.load(href, { user_id: loginedId }, function (responseTxt, statucTxt, xhr) {
                      if (statucTxt == "error")
                        alert("Error: " + xhr.status + ":" + xhr.statucTxt);
                    });
                  } else {
                    alert("로그인을 해주세요.");
                  }
                  break;
              }
              return false;
            });
          });
        </script>
        <div class="wrap">
          <div class="sub_menu">
            <h2 class="mypage_title">MY PAGE</h2>
            <ul class="mypage_menu">
              <li class="mypage_item"><strong>쇼핑 정보</strong></li>
              <li class="mypage_item"><a class="mypage_link" href="./Buying.html">구매 내역</a></li>
              <li class="mypage_item"><a class="mypage_link" href="./Selling.html">판매 내역</a></li>
              <li class="mypage_item"><a class="mypage_link" href="./Wish.html">관심 상품</a></li>
            </ul>
            <ul class="mypage_menu">
              <li class="mypage_item"><strong>내 정보</strong></li>
              <li class="mypage_item"><a class="mypage_link" href="./profile">프로필 정보</a></li>
              <li class="mypage_item"><a class="mypage_link" href="./address">주소록</a></li>
            </ul>
          </div>
          <section class="main_menu">
            <!-- 프로필 -->
            <div class="profile_main">
              <div class="profile_img">
                <img class="profile_item" src="./images/profile_${c.user_id}.jpg" alt="프로필 사진">
              </div>
              <div class="profile_sub">
                <div class="profile_info">
                  <span class="profile_name">${c.user_name}</span>
                  <p class="profile_id">${c.user_id}</p>
                </div>
                <div class="profile_button">
                  <a class="img_button mypage_link" href="./profile">프로필 수정</a>
                </div>
              </div>              
              </div><!-- 구매 내역 -->
              <div class="buying_info">
                <div class="buying_title">
                  <span class="buying_title_span">구매 내역</span>
                  <a class="buying_title_plus" href="">더보기 ></a>
                </div>
                <div class="buying_progress_warp">
                  <div class="buying_progress_box">
                    <div class="buying_box_all">
                      <span class="buying_box_all_title">전체 : </span>
                      <span class="buying_box_all_value">0</span>
                    </div>
                    <div class="buying_box_ing">
                      <span class="buying_box_ing_title">진행중 : </span>
                      <span class="buying_box_ing_value">0</span>
                    </div>
                    <div class="buying_box_end">
                      <span class="buying_box_end_title">종료 : </span>
                      <span class="buying_box_end_value">0</span>
                    </div>
                  </div>
                  <div class="buying_complete_list">
                    <div class="buying_complete_item">
                      거래 내역
                    </div>
                  </div>
                </div>
              </div>
              
              <!-- 판매 내역 -->
              <div class="selling_info">
                <div class="selling_title">
                  <span class="selling_title_span">판매 내역</span>
                  <a class="selling_title_plus" href="">더보기 ></a>
                </div>
                <div class="selling_progress_warp">
                  <div class="selling_progress_box">
                    <div class="selling_box_all">
                      <span class="selling_box_all_title">전체 : </span>
                      <span class="selling_box_all_value">0</span>
                    </div>
                    <div class="selling_box_ing">
                      <span class="selling_box_ing_title">진행중 : </span>
                      <span class="selling_box_ing_value">0</span>
                    </div>
                    <div class="selling_box_end">
                      <span class="selling_box_end_title">종료 : </span>
                      <span class="selling_box_end_value">0</span>
                    </div>
                  </div>
                  <div class="selling_complete_list">
                    <div class="selling_complete_item">
                      거래 내역
                    </div>
                  </div>
                </div>
              </div>

              <!-- 관심 상품 -->
              <div class="mypage_bookmark_main">
                <div class="mypage_bookmark_title">
                  <span class="mypage_bookmark_item">관심 상품</span>
                </div>
                <div class="mypage_bookmark_list">
                  <!-- 1번 상품-->
                  <div class="mypage_bookmark_item_info">
                    <div class="mypage_bookmark_item_img">
                      <img class="mypage_bookmark_img" src="./images/dunk.png" alt="신발">
                    </div>
                    <div class="mypage_bookmark_info_warp">
                      <div class="mypage_bookmark_info_bb">
                        <img class="mypage_bookmark_bb_item" src="./images/a1.jpg" alt="">
                        <img class="mypage_bookmark_bb_item" src="./images/b1.jpg" alt="">
                      </div>
                      <div class="mypage_bookmark_info_item_warp">
                      <p class="mypage_bookmark_info_item1">NIKE Jordan 1 Low Pollen</p>
                      <p class="mypage_bookmark_info_item2">155,000원</p>
                      <p class="mypage_bookmark_info_item3">즉시 구매가</p>
                    </div>
                    </div>
                  </div>
                  <!-- 2번 상품 -->
                  <div class="mypage_bookmark_item_info">
                    <div class="mypage_bookmark_item_img">
                      <img class="mypage_bookmark_img" src="./images/dunk.png" alt="신발">
                    </div>
                    <div class="mypage_bookmark_info_warp">
                      <div class="mypage_bookmark_info_bb">
                        <img class="mypage_bookmark_bb_item" src="./images/a1.jpg" alt="">
                        <img class="mypage_bookmark_bb_item" src="./images/b1.jpg" alt="">
                      </div>
                      <div class="mypage_bookmark_info_item_warp">
                      <p class="mypage_bookmark_info_item1">NIKE Jordan 1 Low Pollen</p>
                      <p class="mypage_bookmark_info_item2">155,000원</p>
                      <p class="mypage_bookmark_info_item3">즉시 구매가</p>
                    </div>
                    </div>
                  </div>  
                   <!-- 3번 상품 -->
                  <div class="mypage_bookmark_item_info">
                    <div class="mypage_bookmark_item_img">
                      <img class="mypage_bookmark_img" src="./images/dunk.png" alt="신발">
                    </div>
                    <div class="mypage_bookmark_info_warp">
                      <div class="mypage_bookmark_info_bb">
                        <img class="mypage_bookmark_bb_item" src="./images/a1.jpg" alt="">
                        <img class="mypage_bookmark_bb_item" src="./images/b1.jpg" alt="">
                      </div>
                      <div class="mypage_bookmark_info_item_warp">
                      <p class="mypage_bookmark_info_item1">NIKE Jordan 1 Low Pollen</p>
                      <p class="mypage_bookmark_info_item2">155,000원</p>
                      <p class="mypage_bookmark_info_item3">즉시 구매가</p>
                    </div>
                    </div>
                  </div>  
                  <!-- 4번 상품 -->
                  <div class="mypage_bookmark_item_info">
                    <div class="mypage_bookmark_item_img">
                      <img class="mypage_bookmark_img" src="./images/dunk.png" alt="신발">
                    </div>
                    <div class="mypage_bookmark_info_warp">
                      <div class="mypage_bookmark_info_bb">
                        <img class="mypage_bookmark_bb_item" src="./images/a1.jpg" alt="">
                        <img class="mypage_bookmark_bb_item" src="./images/b1.jpg" alt="">
                      </div>
                      <div class="mypage_bookmark_info_item_warp">
                      <p class="mypage_bookmark_info_item1">NIKE Jordan 1 Low Pollen</p>
                      <p class="mypage_bookmark_info_item2">155,000원</p>
                      <p class="mypage_bookmark_info_item3">즉시 구매가</p>
                    </div>
                    </div>
                  </div>  
                  </div>
              </div>

          </section>
        </div>
      </c:otherwise>
    </c:choose>