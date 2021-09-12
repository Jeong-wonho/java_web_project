$("#summernote").summernote({
  placeholder: "글을 입력해주세요",
  tabsize: 2,
  height: 400,
  focus: true,
  toolbar: [
    // [groupName, [list of button]]
    ["fontname", ["fontname"]],
    ["fontsize", ["fontsize"]],
    ["style", ["bold", "italic", "underline", "strikethrough", "clear"]],
    ["color", ["forecolor", "color"]],
    ["table", ["table"]],
    ["para", ["ul", "ol", "paragraph"]],
    ["height", ["height"]],
    ["insert", ["picture", "link", "video"]],
    ["view", ["fullscreen", "help"]],
  ],
  fontNames: [
    "Arial",
    "Arial Black",
    "Comic Sans MS",
    "Courier New",
    "맑은 고딕",
    "궁서",
    "굴림체",
    "굴림",
    "돋움체",
    "바탕체",
  ],
  fontSizes: [
    "8",
    "9",
    "10",
    "11",
    "12",
    "14",
    "16",
    "18",
    "20",
    "22",
    "24",
    "28",
    "30",
    "36",
    "50",
    "72",
  ],
});
var review_title = $('input[name="story_title"]');
var program_times = $("select[name='story_program']");
console.log("리뷰입니다:" + review);
$(document).ready(function () {
  $.ajax({
    method: "GET",
    transformRequest: [null],
    transformResponse: [null],
    jsonpCallbackParam: "callback",
    url: "http://localhost:9999/slur/program/" + review.review_num,
    headers: {
      Accept: "application/json, text/plain, */*",
    },
    success: function (response) {
      if (response.status == 1) {
        console.log(response.review.review_title);
        review_title.val(response.review.review_title);
        console.log(response.review.review_program_times);
        program_times
          .val(response.review.review_program_times)
          .prop("selected", true);
        $("#summernote").summernote("code", response.review.review_content);
      }
    },
  });
});
//수정버튼 클릭시
$("#modify_btn").on("click", function () {
  console.log(review_title.val());
  console.log(sessionStorage.getItem("user_id"));
  console.log(program_times.val());
  var summer = $("#summernote");
  console.log(summer.summernote("code"));
  data = {};
  data.review_title = review_title.val();
  data.review_content = summer.summernote("code");
  data.review_program_times = program_times.val();
  // data.review_user_id = sessionStorage.getItem("user_id");
  //Json Parsing
  var dataJsonStr = JSON.stringify(data);
  alert(dataJsonStr);

  $.ajax({
    method: "put",
    transformRequest: [null],
    transformResponse: [null],
    jsonpCallbackParam: "callback",
    url: "/slur/program/" + review.review_num,
    headers: {
      Accept: "application/json, text/plain, */*",
      "Content-Type": "application/json;charset=utf-8",
    },
    data: dataJsonStr,
    success: function (response) {
      if (response.status == 1) {
        alert("추가되었습니다.");
        $story_content_write.hide();
        $story_list.trigger("click");
      } else {
        alert("내용을 다시 확인해주세요");
      }
    },
  });
});
