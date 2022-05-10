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
$("button.teacher_apply_button").click(function (e) {
  console.log($("div.t_apply_info_apply"));
  var formData = new FormData($("div.t_apply_info_apply>form")[0]);

  formData.forEach(function (value, key) {
    console.log(key + ":" + value);
  });

  $.ajax({
    url: "/slur/uploadteacher",
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
      alert("등록 성공 되었습니다.");
      Location.href("./slur_main.html");
    },
    error: function (jqXHR) {
      alert("에러:" + jqXHR.status);
    },
  });
  return false;
});

// 파일 명 변경
$(document).ready(function () {
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
