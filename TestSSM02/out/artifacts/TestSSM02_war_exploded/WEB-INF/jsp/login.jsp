<%--
  Created by IntelliJ IDEA.
  User: AYXYJ
  Date: 2021/7/18
  Time: 11:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h1>登录页面</h1>
<form action="${pageContext.request.contextPath}/user/login" method="post">
 用户名：<input type="text" name="username"/>
 密  码：<input type="text" name="password"/>
    <input type="submit" value="提交">
</form>
</body>
</html>
