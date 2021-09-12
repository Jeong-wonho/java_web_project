var startpage;
var currentpage;
var endpage;
var pageNum = 1;
var $tableBody = $("table.qa-table>tbody");
var $qa_round_list = $("div.qa_round_list");
var $qa_list = $("a.qa_list");
var $table_qa = $("table.qa-table");
var $qa_content_write = $("div.qa_content_write");
var $qa_content_list = $("div.qa_content_list");
var $table_content_body = $("table#content_table>tbody");
var $content_info = $("div.qa_content_confirm");
var qa_num;
var qa;
//함수시작
function qa_reload(i) {
  var trHtml = "";
  var pagingHtml = "";

  $.ajax({
    method: "GET",
    transformRequest: [null],
    transformResponse: [null],
    jsonpCallbackParam: "callback",
    url: "http://localhost:9999/slur/qa/" + i,
    headers: {
      Accept: "application/json, text/plain, */*",
    },
    success: function (response) {
      console.log(response.qalist);
      $(response.qalist).each(function (i, e) {
        var qa_num = e.qa_num;
        var qa_title = e.qa_title;
        var qa_user = e.user_id.user_id;
        var qa_date = e.qa_date;

        trHtml += "<tr>";
        trHtml += '<th scope="row">';
        trHtml += qa_num;
        trHtml += "</th>";
        trHtml += "<td>";
        trHtml += qa_title;
        trHtml += "</td>";
        trHtml += "<td>";
        trHtml += qa_user;
        trHtml += "</td>";
        trHtml += "<td>";
        trHtml += qa_date;
        trHtml += "</td>";
        trHtml += "</tr>";

        console.log(trHtml);
        $tableBody.html(trHtml);
      });

      startpage = response.pageMaker.startPage;
      currentpage = startpage;
      endpage = response.pageMaker.endPage;

      startpage = startpage;
      currentpage = currentpage;
      endpage = endpage;

      if (response.pageMaker.prev) {
        pagingHtml +=
          '<li class="page-item"><a class="page-link" href="#">Previous</a></li>';
      }
      for (let index = startpage; index <= endpage; index++) {
        pagingHtml +=
          '<li class="page-item"><a class="page-link pageNumber" href="#" name=' +
          index +
          ">" +
          index +
          "</a></li>";
      }
      if (response.pageMaker.next) {
        pagingHtml +=
          '<li class="page-item"><a class="page-link" href="#">Next</a></li>';
      }
      console.log(pagingHtml);
      $("ul.pagingNum").html(pagingHtml);
    },
  });
}

$(document).on("click", ".page-link", function (e) {
  pagenum = $(this).text();
  alert(pagenum);
  if (pagenum == "Next") {
    pagenum = $("ul.pagingNum").children().eq(-2).text() + 1;
    console.log(pagenum);
  } else if (pagenum == "Prevous") {
    pagenum = $("ul.pagingNum").children("li:eq(1)").text() - 1;
    console.log(pagenum);
  }
  qa_reload(pagenum);
});

$(document).ready(function () {
  qa_reload(1);
});

//테이븗 버튼 클릭시 화면전환입니다.
$tableBody.on("click", "tr", function () {
  var trHtml = "";
  var tr = $(this);
  var td = tr.children();
  var tdArr = new Array();

  td.each(function (i) {
    tdArr.push(td.eq(i).text());
  });

  $.ajax({
    method: "GET",
    transformRequest: [null],
    transformResponse: [null],
    jsonpCallbackParam: "callback",
    url: "http://localhost:9999/slur/qa/content/" + tdArr[0],
    headers: {
      Accept: "application/json, text/plain, */*",
    },
    success: function (response) {
      console.log(response);
      qa = response.list;
      trHtml +=
        "<tr><td>" +
        qa.qa_num +
        "</td><td>" +
        qa.qa_title +
        "</td><td>" +
        qa.user_id.user_id +
        "</td><td>" +
        qa.qa_date +
        "</td></tr>";

      $qa_content_list.hide();
      $qa_content_write.hide();

      $content_info.show();
      $table_content_body.html(trHtml);
      $("div.qa_content").text(qa.qa_content);
      qa_num = qa.qa_num;
      console.log("id:" + sessionStorage.getItem("user_id"));
      console.log("id2:" + qa.user_id.user_id);
      if (sessionStorage.getItem("user_id") == qa.user_id.user_id) {
        $("button.qa_modify_button").attr("disabled", false);
        $("button.qa_delete_button").attr("disabled", false);
      }

      $("p.qa_reply_content").text(qa.qa_reply.qa_reply_content);
    },
  });
});

//글쓰기 버튼 클릭
$("button.qa_write_button").click(function () {
  alert(sessionStorage.getItem("user_id"));
  if (sessionStorage.getItem("user_id") == null) {
    alert("로그인 후 이용 가능합니다.");
    $section.load("./slur_login.html", function (responseTxt, statusTxt, xhr) {
      if (statusTxt == "error")
        alert("Error:" + xhr.status + ":" + xhr.statusTxt);
    });
  } else {
    //   alert("호잇");
    $("div.qa_content_confirm").hide();
    $("div.qa_content_list").hide();
    $qa_content_write.show();
    $qa_content_write.load(
      "./slur_qa_write.html",
      function (responseTxt, statusTxt, xhr) {
        if (statusTxt == "error")
          alert("Error:" + xhr.status + ":" + xhr.statusTxt);
      }
    );
  }
});
//삭제버튼 구현
$("button.qa_delete_button").click(function () {
  alert("삭제버튼입니다.");
  console.log(qa_num);
  $.ajax({
    method: "DELETE",
    transformRequest: [null],
    transformResponse: [null],
    jsonpCallbackParam: "callback",
    url: "http://localhost:9999/slur/qa/" + qa_num,
    headers: {
      Accept: "application/json, text/plain, */*",
    },
    data: "",
    timeout: {},
    success: function (response) {
      if (response.status == 1) {
        alert("삭제성공입니다.");
        $("button.qa_modify_button").attr("disabled", true);
        $("button.qa_delete_button").attr("disabled", true);
        $qa_list.trigger("click");
      } else {
        console.log(response.msg);
        alert("삭제 실패 입니다.");
      }
    },
  });
});
//수정버튼입니다.
$("button.qa_modify_button").click(function () {
  alert("수정버튼입니다.");
  $("div.qa_content_list").hide();
  $content_info.hide();
  $qa_content_write.show();
  $qa_content_write.load(
    "./slur_qa_modify.html",
    function (responseTxt, statusTxt, xhr) {
      if (statusTxt == "error")
        alert("Error:" + xhr.status + ":" + xhr.statusTxt);
    }
  );
});

$qa_list.click(function () {
  $qa_content_write.hide();
  $content_info.hide();
  $qa_round_list.show(); //if문처리
  $tableBody.empty();
  $("div.qa_content_list").show();
  qa_reload(1);
  $("button.qa_modify_button").attr("disabled", true);
  $("button.qa_delete_button").attr("disabled", true);
});
