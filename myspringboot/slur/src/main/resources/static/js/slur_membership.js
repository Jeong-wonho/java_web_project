$("button.idConfirm-btn").click(function () {
  var user_id = $("input.membership-id").val();
  var url = "/slur/iddupchk";
  alert(user_id);
  $.ajax({
    url: url,
    method: "get",
    data: {
      user_id: user_id,
    },
    success: function (responseData) {
      if (responseData.trim() == "1") {
        alert("가입진행해주세요");
      } else {
        alert("이미 사용중인 아이디 입니다.");
      }
    },
  });
});

function membership_daumapi() {
  new daum.Postcode({
    oncomplete: function (data) {
      //각 주소의 노출 규칙에 따라 주소를 조합한다.
      //내려오는 변수가 값이 없는 경우엔 공백값을 가지므로, 이를 참고하여 본기한다.
      var addr = "";
      var extraAddr = "";

      //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져옴
      if (data.userSelectedType === "R") {
        addr = data.roadAddress;
      } else {
        addr = data.jibunAddress;
      }
      console.log(data.zonecode);
      $("input.membership-addr-num").attr("value", data.zonecode);
      $("input.membership-addr-info").attr("value", addr);

      //커서를 상세주소 필드로 옮긴다.
      $("input.membership-addr-content").focus();
    },
  }).open();
}

var memberform = $("form.form-membership");
memberform.submit(function () {
  var user_id = $("input.membership-id").val();
  var user_pwd = $("input.membership-pwd").val();
  var user_pwd_confirm = $("input.membership-pwd-confirm").val();
  if (user_pwd == user_pwd_confirm) {
    alert("비밀번호가 같습니다.");
    return false;
  } else {
    alert("비밀번호가 다릅니다.");
    $("input.membership-pwd-confirm").focus();
  }
  var user_name = $("input.membership-name").val();
  var user_rrn = $("input.membership-rrn").val();
  var user_email = $("input.membership-email").val();
  var user_addr =
    $("input.membership-addr-info").val() +
    " " +
    $("input.membership-addr-content").val();
  var user_cp = $("input.membership-cp").val();
  var user_gender = $("input[name='gender']:checked").val();
  var user_agree = $("input:checkbox[name='agreement']").is(":checked");
  //개인정보 동의가 체크되어있지 않으면 alert를 뛰움.!!<아니면 빨간줄로 바꾸던가.!?>
  // alert("비밀번호:"+user_pwd);
  // alert("비밀번호확인:"+user_pwd_confirm);

  if (user_agree == true) {
    //ajax전송을 해준다.
    // json data로 파싱

    var data =
      "user_id=" +
      user_id +
      "&" +
      "user_pwd=" +
      user_pwd +
      "&" +
      "user_rrn=" +
      user_rrn +
      "&" +
      "user_email=" +
      user_email +
      "&" +
      "user_gender=" +
      user_gender +
      "&" +
      "user_addr=" +
      user_addr +
      "&" +
      "user_cp=" +
      user_cp +
      "&" +
      "user_name=" +
      user_name;

    alert("subset-3");
    alert(data);
    $.ajax({
      url: "/slur/signup",
      method: "post",
      data: data,
      success: function (responseData) {
        if (responseData.trim() == "1") {
          alert("가입성공");
        } else {
          alert("가입실패");
        }
      },
      error: function (xhr) {
        alert("Error:" + xhr.status);
      },
    });
    return false;

    // json data parsing.
    // var memberJsonstr = JSON.stringify(memberdata);
    // alert("json데이터입니다.:" + memberJsonstr);
  }
});
