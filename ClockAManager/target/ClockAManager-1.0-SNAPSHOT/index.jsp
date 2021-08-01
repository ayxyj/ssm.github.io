<%--
  Created by IntelliJ IDEA.
  User: AYXYJ
  Date: 2021/7/24
  Time: 10:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>疫情打卡系统</title>
  </head>
  <style>
    #container {
      width: 50%;
      height: 50%;
      margin: 100px auto;
      background: paleturquoise;
      border-radius: 15px;
    }

    #center-content-area {
      position: absolute;
      top: 40%;
      left: 50%;
      transform: translate(-50%, -50%);
    }

    a {
      font-size: 24px;
      text-decoration: none;
      color: hotpink;
      line-height: 18px;
    }
  </style>
  <body>
  <div id="container">
    <div id="center-content-area">
      <h1><a id="index" href="${pageContext.request.contextPath}/admin/goMain">首页</a></h1>
      <h1><a id="login" href="${pageContext.request.contextPath}/admin/goLogin">登录</a></h1>
    </div>
  </div>
  </body>
</html>
