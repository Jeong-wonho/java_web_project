function handleImgFileSelect(e) {
  var files = e.target.files;
  var filesArr = Array.prototype.slice.call(files);

  filesArr.forEach(function (f) {
    if (!f.type.match("image.*")) {
      alert("확장자는 이미지 확장자만 가능합니다.");
      return;
    }
    sel_file = f;

    var reader = new FileReader();
    reader.onload = function (e) {
      $("#profile_img").attr("src", e.target.result);
    };
    reader.readAsDataURL(f);
  });
}
//데이터 불러오기 get
$(document).ready(function () {
  var user_id = sessionStorage.getItem("user_id");
  var teacher_program = sessionStorage.getItem("program");
  var teacher_selection = sessionStorage.getItem("program_selection");
  var $teacher_major = $("input[name=teacher_inst]");
  var $intro_area = $("#introduceTextarea");
  var $curr_area = $("#curr_Textarea");
  var $degree = $("#teacher_degree");
  var $program = $("#teacher_program");

  $("img.profile_img").attr(
    "src",
    "/slur/upload/s_profile-" + user_id + ".jpg"
  );
  //selection 상태에 따라 버튼 상태 활성화
  if (teacher_selection == "심사대기중") {
    $("button.teacher_modify_button").attr("disabled", false);
  }

  //데이터 받아오기
  $.ajax({
    url: "/slur/applicants/teacher_select",
    method: "get",
    data: {
      teacher_user_id: user_id,
      teacher_program: teacher_program,
    },
    success: function (responseData) {
      var res_teacher = responseData.teacher;
      console.log(res_teacher);
      $degree.val(res_teacher.teacher_edu).prop("selected", true);
      $program
        .val(res_teacher.teacher_program.program_time)
        .prop("selected", true);
      $teacher_major.val(res_teacher.teacher_inst);
      $intro_area.val(res_teacher.teacher_introduce);
      $curr_area.val(res_teacher.teacher_curr);
    },
  });

  $("#fileup").on("change", handleImgFileSelect);
  var fileTarget = $(".doc_filebox .upload-hidden");

  fileTarget.on("change", function () {
    // 값이 변경되면
    if (window.FileReader) {
      // modern browser
      var filename = $(this)[0].files[0].name;
    } else {
      // old IE
      var filename = $(this).val().split("/").pop().split("\\").pop(); // �뙆�씪紐낅쭔 異붿텧
    }

    // 추출한 파일명 삽입
    $(this).siblings(".upload-name").val(filename);
  });
});

//데이터 전송 구현(수정)
$("button.teacher_modify_button").click(function () {
  var teacher_inst = $("input[name=teacher_major]");
  var teacher_intro = $("#introduceTextarea");
  var teacher_curr = $("#curr_Textarea");
  var teacher_edu = $("select.teacher_degree option:selected");
  var program_time = $("select.teacher_program option:selected");

  var data = {};
  data.teacher_inst = teacher_inst.val();
  data.teacher_introduce = teacher_intro.val();
  data.teacher_curr = teacher_curr.val();
  data.teacher_edu = teacher_edu.val();
  data.teacher_program = program_time.val();

  var dataJsonStr = JSON.stringify(data);
  console.log(dataJsonStr);

  $.ajax({
    method: "PUT",
    transformRequest: [null],
    transformResponse: [null],
    jsonpCallbackParam: "callback",
    url: "/slur/applicants/teacher_app_modify",
    headers: {
      Accept: "application/json, text/plain, */*",
      "Content-Type": "application/json;charset=utf-8",
    },
    data: dataJsonStr,
    success: function (responseData) {
      if (responseData.status == 1) {
        //로그인 성공됨!
        alert("수정 되었습니다.");
        location.href = "./slur_main.html";
      } else {
        alert("다시 시도해주세요");
      }
    },
  });
});
