<script>
      var startpage;
      var currentpage;
      var endpage;
      var pageNum = 1;
      var $tableBody = $("table.notice-table>tbody");
      var $notice_round_list = $("div.notice_round_list");
      var $notice_list = $("a.notice_list");
      var $table_notice = $("table.notice-table");
      var $notice_content_write = $("div.notice_content_write");
      var $notice_content_list = $("div.notice_content_list");
      var $table_content_body = $("table#content_table>tbody");
      var $content_info = $("div.notice_content_confirm");
      function notice_reload(i) {
        var trHtml = "";
        var pagingHtml = "";

        $.ajax({
          method: "GET",
          transformRequest: [null],
          transformResponse: [null],
          jsonpCallbackParam: "callback",
          url: "http://localhost:9999/slur/notice/" + i,
          headers: {
            Accept: "application/json, text/plain, */*",
          },
          success: function (response) {
            console.log(response.list);
            $(response.list).each(function (i, e) {
              var notice_num = e.notice_num;
              var notice_title = e.notice_title;
              var notice_user = e.user_id.user_id;
              var notice_date = e.notice_date;

              trHtml += "<tr>";
              trHtml += '<th scope="row">';
              trHtml += notice_num;
              trHtml += "</th>";
              trHtml += "<td>";
              trHtml += notice_title;
              trHtml += "</td>";
              trHtml += "<td>";
              trHtml += notice_user;
              trHtml += "</td>";
              trHtml += "<td>";
              trHtml += notice_date;
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
        notice_reload(pagenum);
      });

      $(document).ready(function () {
        notice_reload(1);
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
          url: "http://localhost:9999/slur/notice/content/" + tdArr[0],
          headers: {
            Accept: "application/json, text/plain, */*",
          },
          success: function (response) {
            console.log(response.notice);
            notice = response.notice;
            trHtml +=
              "<tr><td>" +
              notice.notice_num +
              "</td><td>" +
              notice.notice_date +
              "</td><td>" +
              notice.user_id.user_id +
              "</td><td>" +
              notice.notice_title +
              "</td></tr>";

            $notice_content_list.hide();
            $notice_content_write.hide();

            $content_info.show();
            $table_content_body.html(trHtml);
            $("div.notice_content").text(notice.notice_content);
          },
        });
      });

      $(".notice_list").click(function () {
        $notice_content_write.hide();
        $content_info.hide();
        $notice_round_list.show(); //if문처리
        $tableBody.empty();
        $notice_content_list.show();
        notice_reload(1);
      });
    </script>