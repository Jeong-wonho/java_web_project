//데이터 전송 구현
$("button.qa_button").click(function (e) {
  var qa_content = $("#question_input").val();
  var qa_title = $("input[name='question_title']").val();

  data = {};
  data.qa_content = qa_content;
  data.qa_title = qa_title;

  var dataJson = JSON.stringify(data);
  alert(dataJson);
  $.ajax({
    method: "POST",
    transformRequest: [null],
    transformResponse: [null],
    jsonpCallbackParam: "callback",
    url: "http://localhost:9999/slur/qa/write",
    headers: {
      Accept: "application/json, text/plain, */*",
      "Content-Type": "application/json;charset=utf-8",
    },
    data: dataJson,
    success: function (responseObj) {
      if (responseObj.status == 1) {
        alert("질문 등록 성공입니다.");
        location.href = "./slur_main.html";
      } else {
        alert("질문 실패 입니다.");
      }
    },
  });
});
