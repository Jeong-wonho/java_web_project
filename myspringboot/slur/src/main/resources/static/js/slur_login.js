// alert("login load");
//1.localStorage의 item(이름: loginInfo)찾기
var loginInfoValue = localStorage.getItem("loginInfo");

//2. 1의 결과가 null이거나 ''이 아니면 1의결과를 아이디입력란에 설정한다
var idObj = $("form.form-login input[name=id]");
//alert("login.html load idObj.value = " + idObj.value);
if (loginInfoValue != null && loginInfoValue != "") {
  idObj.value = loginInfoValue;
}
$("form.form-login").submit(function () {
  localStorage.removeItem("loginInfo");

  var chkboxObj = $("form.form-login input[type=checkbox]");
  //2)체크여부확인
  if (chkboxObj.checked) {
    //체크된 경우
    localStorage.setItem("loginInfo", idObj.value); //localStorage에 추가
  }

  /* var url = 'http://localhost:8888/myback/login'; */
  var url = "/slur/login";
  //서버로 AJAX 요청,응답
  var data = {};
  data.user_id = $("form.form-login input[name=id]").val();
  data.user_pwd = $("form.form-login input[name=pwd]").val();
  var dataJsonStr = JSON.stringify(data);
  alert(dataJsonStr);

  $.ajax({
    url: url,
    data: dataJsonStr,
    method: "POST",
    transformRequest: [null],
    transformResponse: [null],
    jsonpCallbackParam: "callback",

    headers: {
      Accept: "application/json, text/plain, */*",
      "Content-Type": "application/json;charset=utf-8",
    },
    success: function (responseData) {
      if (responseData.status == 1) {
        //로그인 성공됨!

        sessionStorage.setItem("user_id", responseData.User.user_id);
        console.log(sessionStorage.getItem("user_id"));
        location.href = "./slur_main.html";
      } else {
        alert(responseData.msg);
      }
    },
    error: function (xhr) {
      alert(xhr.status);
    },
  });
  Event.preventDefault();
}); //submit function

$("button.membership-btn").click(function () {
  $("a.main_slur_membership").trigger("click");
});
