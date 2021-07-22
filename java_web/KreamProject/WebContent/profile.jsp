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
		<link rel="stylesheet" type="text/css" href="./css/Profile.css">
		<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
		<script>
			//비밀번호 변경화면
			$(function() {
				var $btn_modify = $('.pwd_modify');
				var $btn_cancel = $('.pwd_cancel');
				var $btn_Before = $('.pwd_before');
				var $btn_after = $('.pwd_info')
				$btn_modify.click(function() {
					$btn_Before.css('display', 'none')
					$btn_after.css('display', 'block')
				});
				$btn_cancel.click(function() {
					$btn_Before.css('display', 'block')
					$btn_after.css('display', 'none')
				});
			});
			//휴대폰번호 변경화면
			$(function() {
				var $btn_phonemodify = $('.phone_modify');
				var $btn_phonecancel = $('.phone_cancel');
				var $btn_phonebefore = $('.customer_phone');
				var $btn_phoneafter = $('.modify_phone');
				$btn_phonemodify.click(function() {
					$btn_phonebefore.css('display', 'none')
					$btn_phoneafter.css('display', 'block')
				})
				$btn_phonecancel.click(function() {
					$btn_phonebefore.css('display', 'block')
					$btn_phoneafter.css('display', 'none')
				})

				var $modifypwd_complete = $('.pwd_save');
				var $modifyphone_complete = $('.phone_save');

				$modifypwd_complete.click(function() {
					var pwdPattern = /^[A-Za-z0-9]{4,10}$/;
					var $user_id = $('.login_userid').val();
					var $modify_before = $('.modifypwd_before').val();
					var $modify_pwd = $('.modifypwd_value').val();
					var href = $(this).attr('href');
					switch (href) {
					case './modifypwd':
						if ($modify_pwd == "") {
							alert("수정할 내용이 없습니다.");
						} else if (!pwdPattern.test($modify_pwd)) {
							alert("비밀번호 형식에 맞게 입력해주세요.");
						} else if ($modify_before == $modify_pwd) {
							alert("변경할 값이 변경 전 값과 같습니다.");
						} else if ($modify_pwd.search($user_id) > -1) {
							alert("비밀번호에 아이디가 포함되어 있습니다.");
						} else {
							$.ajax({
								url : href,
								method : 'post',
								data : {
									id : $user_id,
									pwd : $modify_pwd
								},
								success : function(responseData) {
									var result = responseData.trim();
									if (result == "실패") {
										alert("입력값을 확인해주세요.");
									} else {
										alert("비밀번호가 수정되었습니다.");
										location.reload();

									}
								}
							});

						}
						break;
					}
					return false;
				});

				$modifyphone_complete.click(function() {
					var phonePattern = /^\d{3}-\d{3,4}-\d{4}$/;
					var $user_id = $('.login_userid').val();
					var $modify_before = $('.modifyphone_before').val();
					var $modify_phone = $('.modifyphone_value').val();
					var href = $(this).attr('href');
					switch (href) {
					case './modifyphone':
						if ($modify_phone == "") {
							alert("수정할 내용이 없습니다.");
						} else if (!phonePattern.test($modify_phone)) {
							alert("휴대폰 번호 형식에 맞게 입력해주세요.");
						} else if ($modify_before == $modify_phone) {
							alert("변경할 값이 변경 전 값과 같습니다.");
						} else {
							$.ajax({
								url : href,
								method : 'post',
								data : {
									id : $user_id,
									phone : $modify_phone
								},
								success : function(responseData) {
									var result = responseData.trim();
									if (result == "실패") {
										alert("입력값을 확인해주세요.");
									} else {
										alert("휴대폰 번호가 수정되었습니다.");
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
		<div class="profile_infomation">
			<h1 class="profile_mainname">프로필 정보</h1>
		</div>
		<div class="profile_maininfo">
			<div class="profile_mainimg">
				<img class="profile_mainitem"
					src="./images/profile_${c.user_id}.jpg" alt="프로필 사진">
			</div>
			<div class="profile_subinfo">
				<div class="profile_id">
					<span class="profile_id_item">${c.user_id}</span>
					<span class="prifile_id_sub">회원님의 정보입니다.</span>
				</div>
			</div>
		</div>
		<div class="login_info">
			<strong class="login_infoname">로그인 정보</strong>
			<div class="login_id">
				<span class="login_iditem">아이디</span>
				<input type="text" class="login_iditem login_userid" value="${c.user_id}" disabled>
			</div>
			<div class="pwd_before">
				<span class="pwd_beforeitem">비밀번호</span>
				<div class="pwd_wrap">
					<input class="pwd_beforeinput" type="password" value="${c.user_pwd}" disabled>
					<button class="pwd_modify">변경</button>
				</div>
			</div>
			<div class="pwd_info">
				<span class="pwd_modifyname">비밀번호 변경</span>
				<span class="pwd_infoitem">이전 비밀번호</span>
				<input class="pwd_newinput modifypwd_before" type="password" value="${c.user_pwd}" placeholder="" disabled>
					<span class="pwd_infoitem">새 비밀번호</span>
					<input class="pwd_newinput modifypwd_value" type="password" name="pwd" placeholder="영문,숫자를 포함한 4-10자리의 값을 입력해주세요.">
				<div class="pwd_modifybutton">
					<a class="pwd_cancel">취소</a> <a class="pwd_save" href="./modifypwd">저장</a>
				</div>
			</div>
		</div>
		<div class="customer_info">
			<div class="customer_name">
				<strong class="customer_infotitle">개인 정보</strong>
				<span	class="customer_title">이름</span> <span class="customer_value">${c.user_name}</span>
			</div>
			<div class="customer_phone">
				<span class="phone_title">휴대폰 번호</span>
				<div class="phone_wrap">
					<input class="phone_value" type="text" value="${c.user_phone}" disabled>
					<button class="phone_modify">변경</button>
				</div>
			</div>
			<div class="modify_phone">
				<span class="modify_title">변경 전 휴대폰 번호</span>
				<input class="phone_value modifyphone_before" type="text"	value="${c.user_phone}" disabled>
				<span class="modify_title">변경 후 휴대폰 번호</span>
				<input class="modify_value modifyphone_value" type="text" name="phone" placeholder="새 휴대폰 번호를 입력하세요.">
				<div class="phone_modifywrap">
					<a class="phone_cancel">취소</a>
					<a class="phone_save"	href="./modifyphone">저장</a>
				</div>

			</div>
		</div>
		<div class="marketing_info">
			<span class="marketing_title">광고성 문자 수신</span>
			<span	class="marketing_p">문자 메세지<span>
			<span	class="marketing_span">수신동의</span>
			<input class="marketing_radio" name="marketing_radio" type="radio">
			<span	class="marketing_span">수신거부</span>
			<input class="marketing_radio" name="marketing_radio" type="radio" checked>
		</div>
	</c:otherwise>
</c:choose>