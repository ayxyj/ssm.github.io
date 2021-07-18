<%--
  Created by IntelliJ IDEA.
  User: AYXYJ
  Date: 2021/7/16
  Time: 14:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>allItems</title>
</head>
<style>
    a {
        text-decoration: none; /*下划线去掉*/
        color: black;
        font-size: 18px;
    }
    h1 {
        width: 150px;
        height: 38px;
        margin: 100px auto;
        text-align: center;
        line-height: 38px;
        background: aqua;
        border-radius: 15px;
    }
</style>
<body>
<div class="container">
    <h1><a href="${pageContext.request.contextPath}/item/allItems">书籍页面</a></h1>
</div>
</body>
</html>
