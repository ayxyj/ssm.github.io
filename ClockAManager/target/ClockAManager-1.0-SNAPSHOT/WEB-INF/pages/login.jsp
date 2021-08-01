<%--
  Created by IntelliJ IDEA.
  User: AYXYJ
  Date: 2021/7/23
  Time: 15:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<!-- 引入 Bootstrap -->
<link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1>
                    <small>登录页面</small>
                </h1>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-4 column">
            <a class="btn btn-primary" href="/index.jsp">系统首页</a>
        </div>
    </div>
    <div class="row">
        <br/>
    </div>
    <div class="row clearfix">
        <div class="col-md-12 column">

            <form action="${pageContext.request.contextPath}/admin/login" method="post">
                <div class="form-group">
                    <label>用户名称:</label>
                    <input type="text" class="form-control" name="username" placeholder="用户名称" required>
                </div>
                <div class="form-group">
                    <label>用户密码:</label>
                    <input type="text" class="form-control" name="password" placeholder="用户密码">
                </div>
                <button type="submit" class="btn btn-info">登录</button>
            </form>
                <label>${status} ${pwdError}</label>
        </div>
    </div>
</div>
</body>
</html>
