<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="c" value="${requestScope.c}" />
<c:choose>
	<c:when test="${empty c}">
		<%--고객상세 실패인 경우 --%>
		<script>
alert("고객 상세 정보 보기 실패 : ${requestScope.msg}"); 
</script>
	</c:when>
	<c:otherwise>
 
		<link rel="stylesheet" type="text/css" href="./css/Address.css">
		<script
			src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
		<script>
			//비밀번호 변경화면
			$(function() {
				var $btn_modify = $('.addr_modify');
				var $btn_cancel = $('.addr_cancel');
				var $btn_Before = $('.addr_before');
				var $btn_after = $('.addr_after')
				$btn_modify.click(function() {
					$btn_Before.css('display', 'none')
					$btn_after.css('display', 'block')
				});
				$btn_cancel.click(function() {
					$btn_Before.css('display', 'block')
					$btn_after.css('display', 'none')
				});

				var $modifyaddr_complete = $('.addr_save');
				$modifyaddr_complete.click(function() {
					var user_id = sessionStorage.getItem("loginedId");
					var $modify_addr = $('.modifyaddr_value').val();
					var href = $(this).attr('href');
					switch (href) {
					case './modifyaddr':
						if ($modify_addr == "") {
							alert("수정할 내용이 없습니다.");
						} else {
							$.ajax({
								url : href,
								method : 'post',
								data : {
									id : user_id,
									addr : $modify_addr
								},
								success : function(responseData) {
									var result = responseData.trim();
									if (result == "실패") {
										alert("입력값를 확인해주세요.");
									} else {
										alert("주소가 수정되었습니다.");
										location.reload();
									}
								}
							});
						}
						break;
					}
					return false;
				});
			});
		</script>

		<div class="addr_main">
			<span class="addr_title">주소록</span>
		</div>
		<div class="addr_before">
			<span class="addr_beforeitem">현재 주소</span>
			<div class="addr_wrap">
				<input class="addr_beforeinput" type="text" value="${c.user_addr}" disabled>
				<button class="addr_modify">변경</button>
			</div>
		</div>
		<div class="addr_after">
			<span class="addr_modifyname">주소 변경</span>
			<span class="addr_infoitem">이전 주소</span> 
			<input class="addr_newinput" type="text" value="${c.user_addr}" disabled>
			<span class="addr_infoitem">새 주소</span>
			<input class="addr_newinput modifyaddr_value" type="text" name="addr" placeholder="새 주소를 입력해주세요.">
			<div class="addr_modifybutton">
				<a class="addr_cancel">취소</a>
				<a class="addr_save" href="./modifyaddr">저장</a>
			</div>
		</div>
	</c:otherwise>
</c:choose>