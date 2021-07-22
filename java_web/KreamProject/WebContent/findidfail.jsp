<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <!DOCTYPE html>
  <html lang="en">
 
  <head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
      .findid_div {
        margin-top: 100px;
        margin-left: 55px;
        width: 400px;
        text-align: center;
      }

      .findid_fail {
        display: block;
        font-size: 24px;
        font-weight: 600;
      }

      .findid_move {
        display: bloack;
        font-size: 20px;
 
      }
    </style>
    <script>
      $(function () {
        setTimeout(function () {
          location.href = "Index.html";
        }, 1000)
      });
    </script>
  </head>

  <body>
    <div class="findid_div">
      <span class="findid_fail">휴대폰 번호를 다시 입력해주세요.</span>
      <span class="findid_move">잠시 후 메인화면으로 이동됩니다.</span>
    </div>
  </body>

  </html>