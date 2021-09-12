var startpage;
var currentpage;
var endpage;
var pageNum = 1;

function story_reload(i) {
  var trHtml = "";
  var pagingHtml = "";
  $.ajax({
    method: "GET",
    transformRequest: [null],
    transformResponse: [null],
    jsonpCallbackParam: "callback",
    url: "http://localhost:9999/slur/program/review/" + i,
    headers: {
      Accept: "application/json, text/plain, */*",
    },
    success: function (response) {
      console.log(response.crilist);
      $(response.crilist).each(function (i, e) {
        var review_num = e.review_num;
        var review_program_times = e.review_program_times;
        var review_title = e.review_title;
        var review_user = e.review_user_id.user_id;
        var review_date = e.review_date;

        trHtml += "<tr>";
        trHtml += '<th scope="row">';
        trHtml += review_num;
        trHtml += "</th>";
        trHtml += "<td>";
        trHtml += review_program_times;
        trHtml += "</td>";
        trHtml += "<td>";
        trHtml += review_title;
        trHtml += "</td>";
        trHtml += "<td>";
        trHtml += review_user;
        trHtml += "</td>";
        trHtml += "<td>";
        trHtml += review_date;
        trHtml += "</td>";
        trHtml += "</tr>";

        $tableBody.html(trHtml);
      });

      console.log(response.pageMaker);
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

function story_find(i) {
  var trHtml = "";
  var pagingHtml = "";
  $("nav.story_paging").hide();
  $.ajax({
    method: "GET",
    transformRequest: [null],
    transformResponse: [null],
    jsonpCallbackParam: "callback",
    url: "http://localhost:9999/slur/program/times/" + i,
    headers: {
      Accept: "application/json, text/plain, */*",
    },
    success: function (response) {
      console.log(response);
      $(response.list).each(function (i, e) {
        var review_num = e.review_num;
        var review_program_times = e.review_program_times;
        var review_title = e.review_title;
        var review_user = e.review_user_id.user_id;
        var review_date = e.review_date;

        trHtml += "<tr>";
        trHtml += '<th scope="row">';
        trHtml += review_num;
        trHtml += "</th>";
        trHtml += "<td>";
        trHtml += review_program_times;
        trHtml += "</td>";
        trHtml += "<td>";
        trHtml += review_title;
        trHtml += "</td>";
        trHtml += "<td>";
        trHtml += review_user;
        trHtml += "</td>";
        trHtml += "<td>";
        trHtml += review_date;
        trHtml += "</td>";
        trHtml += "</tr>";

        console.log(trHtml);
        $tableBody.html(trHtml);
      });
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
  story_reload(pagenum);
});

// 프로그램 회차 클릭시 나타나느 메서드 입니다.
$(".story_round").click(function (e) {
  e.preventDefault;
  $story_content_write.hide();
  $content_info.hide();
  $story_round_list.show(); //if문처리
  $tableBody.empty();
  $("div.story_content_list").show();

  var search = $(this).text();
  console.log(search);
  story_find(search);
});

var $story_round_list = $("div.story_round_list");
var $story_list = $("a.story_list");
var $table_story = $("table.story-table");
var $tableBody = $("table.story-table>tbody");
var $story_content_write = $("div.story_content_write");
var $story_content_list = $("div.story_content_list");
var $table_content_body = $("table#content_table>tbody");
var $content_info = $("div.story_content_confirm");
var review_num;
var review;
$story_round_list.hide();
$story_content_write.hide();
$content_info.hide();
//기본시작 페이지 입니다.
$(document).ready(function () {
  story_reload(1);
});

//스토리 목록 클릭할 때 행하는 이벤트 입니다.
$story_list.click(function () {
  $story_content_write.hide();
  $content_info.hide();
  $story_round_list.show(); //if문처리
  $tableBody.empty();
  $("div.story_content_list").show();
  $("nav.story_paging").show();
  story_reload(1);
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
    url: "http://localhost:9999/slur/program/" + tdArr[0],
    headers: {
      Accept: "application/json, text/plain, */*",
    },
    success: function (response) {
      review = response.review;
      trHtml +=
        "<tr><td>" +
        review.review_program_times +
        "</td><td>" +
        review.review_title +
        "</td><td>" +
        review.review_user_id.user_id +
        "</td><td>" +
        review.review_date +
        "</td></tr>";

      $story_content_list.hide();

      $content_info.show();
      $table_content_body.html(trHtml);
      $("div.review_content").html(review.review_content);
      console.log(sessionStorage.getItem("user_id"));
      console.log(review.review_user_id.user_id);
      review_num = review.review_num;
      if (sessionStorage.getItem("user_id") == review.review_user_id.user_id) {
        $("button.story_modify_button").attr("disabled", false);
        $("button.story_delete_button").attr("disabled", false);
      }
    },
  });
});

//글쓰기 버튼 클릭
$("button.story_write_button").click(function () {
  alert(sessionStorage.getItem("user_id"));
  if (sessionStorage.getItem("user_id") == null) {
    alert("로그인 후 이용 가능합니다.");
    $section.load("./slur_login.html", function (responseTxt, statusTxt, xhr) {
      if (statusTxt == "error")
        alert("Error:" + xhr.status + ":" + xhr.statusTxt);
    });
  } else {
    //   alert("호잇");
    $("div.story_content_list").hide();
    $story_content_write.show();
    $story_content_write.load(
      "./slur_textarea.html",
      function (responseTxt, statusTxt, xhr) {
        if (statusTxt == "error")
          alert("Error:" + xhr.status + ":" + xhr.statusTxt);
      }
    );
  }
});

$("button.story_delete_button").click(function () {
  alert("삭제버튼입니다.");
  console.log(review_num);
  $.ajax({
    method: "DELETE",
    transformRequest: [null],
    transformResponse: [null],
    jsonpCallbackParam: "callback",
    url: "http://localhost:9999/slur/program/" + review_num,
    headers: {
      Accept: "application/json, text/plain, */*",
    },
    data: "",
    timeout: {},
    success: function (response) {
      if (response.status == 1) {
        alert("삭제성공입니다.");
        $story_list.trigger("click");
      } else {
        alert("삭제 실패 입니다.");
      }
    },
  });
});
//수정버튼입니다.
$("button.story_modify_button").click(function () {
  alert("수정버튼입니다.");
  $("div.story_content_list").hide();
  $content_info.hide();
  $story_content_write.show();
  $story_content_write.load(
    "./slur_textarea_modify.html",
    function (responseTxt, statusTxt, xhr) {
      if (statusTxt == "error")
        alert("Error:" + xhr.status + ":" + xhr.statusTxt);
    }
  );
});
var actionForm = $("#actionForm");

$("documnet").on("click", ".page-link", function (e) {
  e.preventDefault;
  var pagenum = $(this).text();
  console.log(pagenum);
});
$("button.search_button").click(function () {
  event.preventDefault;
  var word = $("input[name='search_input'").val();
  var trHtml = "";
  var pagingHtml = "";
  $("nav.story_paging").hide();
  $.ajax({
    method: "GET",
    transformRequest: [null],
    transformResponse: [null],
    jsonpCallbackParam: "callback",
    url: "http://localhost:9999/slur/program/find/" + word,
    headers: {
      Accept: "application/json, text/plain, */*",
    },
    success: function (response) {
      console.log(response);
      $(response.list).each(function (i, e) {
        var review_num = e.review_num;
        var review_program_times = e.review_program_times;
        var review_title = e.review_title;
        var review_user = e.review_user_id.user_id;
        var review_date = e.review_date;

        trHtml += "<tr>";
        trHtml += '<th scope="row">';
        trHtml += review_num;
        trHtml += "</th>";
        trHtml += "<td>";
        trHtml += review_program_times;
        trHtml += "</td>";
        trHtml += "<td>";
        trHtml += review_title;
        trHtml += "</td>";
        trHtml += "<td>";
        trHtml += review_user;
        trHtml += "</td>";
        trHtml += "<td>";
        trHtml += review_date;
        trHtml += "</td>";
        trHtml += "</tr>";

        console.log(trHtml);
        $tableBody.html(trHtml);
      });
    },
  });
});
