var $id;
var $table = $("table.myinfo-table");
var $student_div = $("div.student_apply_content");
var $teacher_div = $("div.teacher_apply_content");
var $tableBody = $("table.myinfo-table > tbody");
$(document).ready(function () {
  $table.hide();
  $student_div.hide();
  $teacher_div.hide();
  $.ajax({
    url: "/slur/myinfo",
    method: "get",
    success: function (responseObj) {
      console.log(responseObj);
      $("input.myInfo-id").attr("placeholder", responseObj.user_id);
      $("input.myInfo-name").attr("placeholder", responseObj.user_name);
      $("input.myInfo-cp").attr("placeholder", responseObj.user_cp);
      $("input.myInfo-address").attr("placeholder", responseObj.user_addr);
      $("input.myInfo-email").attr("placeholder", responseObj.user_email);
    },
  });
});
// 프로필 수정할 때 변경 내용 저장하기
$("button.myInfo-button").click(function () {
  $.ajax({
    url: "/slur/modify",
    method: "put",
    data: {
      user_id: $("input.myInfo-id").val(),
      user_name: $("input.myInfo-name").val(),
      user_cp: $("input.myInfo-cp").val(),
      user_addr: $("input.myInfo-address").val(),
      user_email: $("input.myInfo-email").val(),
    },
    success: function (responseObj) {
      alert("수정되었습니다.");
    },
  });
});
// 내가 참여한 프로그램 정보 클릭시 마아인포 사라지게 만들기
//참여한 프로그램 정보 표출
$("a.myProgramParticipate").click(function () {
  var trHtml = "";
  $("div.myInfo_content").hide();
  $teacher_div.hide();
  $student_div.hide();
  $("div.myProgramParticipate").show();
  $table.show();
  $.ajax({
    url: "/slur/program_role",
    method: "post",
    success: function (responseData) {
      console.log(responseData);
      $(responseData.list).each(function (i, e) {
        console.log(i + "," + e);
        trHtml += "<tr>";
        var program_time = e.program_time;
        var program_title = e.program_title;
        var role_name = e.role_name;
        var role_selection = e.role_selection;

        role_name = role_name == "s" ? "Student" : "Teacher";
        if (role_selection == 3) {
          role_selection = "선정";
        } else if (role_selection == 2) {
          role_selection = "다음기회에";
        } else if (role_selection == 1) {
          role_selection = "심사진행중";
        } else {
          role_selection = "심사대기중";
        }
        console.log(role_name);
        console.log(role_selection);
        trHtml += '<th scope="row">';
        trHtml += program_time;
        trHtml += "</th>";
        trHtml += "<td>";
        trHtml += program_title;
        trHtml += "</td>";
        trHtml += "<td>";
        trHtml += role_name;
        trHtml += "</td>";
        trHtml += "<td>";
        trHtml += role_selection;
        trHtml += "</td>";
        trHtml += "</tr>";
        console.log(trHtml);
        $tableBody.html(trHtml);
      });
    },
  });
});

// 본인 정보 수정란.
$("a.myInfoRevise").click(function () {
  $table.hide();
  $teacher_div.hide();
  $student_div.hide();
  $("div.myInfo_content").show();
});

//테이블 클릭
$tableBody.on("click", "tr", function () {
  var tr = $(this);
  var td = tr.children();
  var tdArr = new Array();
  console.log(tr);

  td.each(function (i) {
    tdArr.push(td.eq(i).text());
  });
  var program_time = tdArr[0];
  //session에 프로그램 정보 저장
  sessionStorage.setItem("program", tdArr[0]);
  sessionStorage.setItem("program_selection", tdArr[3]);

  var role = tdArr[2];
  console.log(program_time + " " + sessionStorage.getItem("user_id"));

  if (role == "Teacher") {
    $("div.myProgramParticipate").hide();
    $teacher_div.show();
    $teacher_div.load(
      "./slur_apply_teacher_mod.html",
      function (res, status, xhr) {
        if (status == "error")
          alert("Error:" + xhr.status + ":" + xhr.statusTxt);
      }
    );
  } else {
    $("div.myProgramParticipate").hide();
    $student_div.show();
    $student_div.load(
      "./slur_apply_student_mod.html",
      function (res, status, xhr) {
        if (status == "error")
          alert("Error:" + xhr.status + ":" + xhr.statusTxt);
      }
    );
  }
});
