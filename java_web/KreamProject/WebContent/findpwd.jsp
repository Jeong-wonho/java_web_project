<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <c:set var="findpwd" value="${requestScope.findpwd}" />
    <!DOCTYPE html>
    <html lang="en"> 

    <head>
      <meta charset="UTF-8">
      <meta http-equiv="X-UA-Compatible" content="IE=edge">
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <style>
        .findid_div {
          margin-top: 100px;
          margin-left: 50px;
          width: 400px;
          text-align: center;
        }

        .findid_value1 {
          display: block;
          font-size: 30px;
          font-weight: 600;
        }

        .findid_value2 {
          display: block;
          font-size: 30px;
          font-weight: 600;
          color: blue;
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
          }, 5000)
        });
      </script>
    </head>

    <body>
      <div class="findid_div">
        <span class="findid_value1">고객님의 비밀번호는</span>
        <span class="findid_value2">${findpwd}</span>
        <span class="findid_move">잠시 후 메인화면으로 이동됩니다.</span>
      </div>
    </body>

    </html>