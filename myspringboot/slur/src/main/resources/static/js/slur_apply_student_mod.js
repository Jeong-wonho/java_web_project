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
  var student_program = sessionStorage.getItem("program");
  var student_selection = sessionStorage.getItem("program_selection");
  var $student_major = $("input[name=student_inst]");
  var $intro_area = $("#introduceTextarea");
  var $degree = $("#student_degree");
  var $program = $("#student_program");

  console.log("id" + user_id);
  $("img.profile_img").attr(
    "src",
    "/slur/upload/s_profile-" + user_id + ".jpg"
  );

  console.log("심사결과:" + student_selection);
  //선정결과가  0(심사중일 경우에만 수정할 수 있도록 한다.)
  if (student_selection == "심사대기중") {
    $("button.student_modify_button").attr("disabled", false);
  }

  $.ajax({
    url: "/slur/applicants/student_select",
    method: "get",
    data: {
      student_program: student_program,
    },
    success: function (responseData) {
      console.log("responsedata:" + responseData);
      var res_student = responseData.student;
      $degree.val(res_student.student_edu).prop("selected", true);
      $program
        .val(res_student.student_program.program_time)
        .prop("selected", true);
      $student_major.val(res_student.student_inst);
      $intro_area.val(res_student.student_introduce);
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

$("button.student_modify_button").click(function () {
  var formData = new FormData($("div.s_apply_info_apply>form")[0]);

  formData.forEach(function (value, key) {
    console.log(key + ":" + value);
  });

  $.ajax({
    url: "/slur/studentmod",
    method: "POST",
    processData: false,
    contentType: false,
    data: formData,
    success: function (responseObj) {
      console.log(responseObj);
      responseObj.photoFileName;
      $("img.profile_img").attr(
        "src",
        "/slur/upload/" + responseObj.photoFileName
      );
      alert("수정되었습니다.");
      location.href = "./slur_main.html";
    },
    error: function (jqXHR) {
      alert("에러:" + jqXHR.status);
    },
  });
  return false;
});
