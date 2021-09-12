var user_id = sessionStorage.getItem("user_id");
var program = "2104";
var $apply_info = $("div.apply_info");
var $student_a = $("a.student_apply");
var $teacher_a = $("a.teacher_apply");
var $student_div = $("div.student_apply_content");
var $teacher_div = $("div.teacher_apply_content");
$student_div.hide();
$teacher_div.hide();

$("a.slur_apply").click(function () {
  $apply_info.show();
  $student_div.hide();
  $teacher_div.hide();
});

$teacher_a.click(function () {
  $apply_info.hide();
  $student_div.hide();
  $teacher_div.show();
  $.ajax({
    url: "/slur/program_role",
    method: "post",
    success: function (responseData) {
      console.log(responseData);
      $(responseData.list).each(function (i, e) {
        if (i == 0 && e.program_time == "2104") {
          alert("이미 이번 캠프에 신청하셨습니다.");
          $(location).attr("href", "./slur_main.html");
          return false;
        }
      });
      $teacher_div.load("slur_apply_teacher.html", function (res, status, xhr) {
        if (status == "error")
          alert("Error:" + xhr.status + ":" + xhr.statusTxt);
      });
    },
  });
});

$student_a.click(function () {
  $apply_info.hide();
  $teacher_div.hide();
  $student_div.show();

  $.ajax({
    url: "/slur/program_role",
    method: "post",
    success: function (responseData) {
      console.log(responseData);
      $(responseData.list).each(function (i, e) {
        if (i == 0 && e.program_time == "2104") {
          alert("이미 이번 캠프에 신청하셨습니다.");
          $(location).attr("href", "./slur_main.html");
          return false;
        }
      });
      $student_div.load("slur_apply_student.html", function (res, status, xhr) {
        if (status == "error")
          alert("Error:" + xhr.status + ":" + xhr.statusTxt);
      });
    },
  });
});
