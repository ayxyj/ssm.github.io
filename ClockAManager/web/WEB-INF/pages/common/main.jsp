<%--
  Created by IntelliJ IDEA.
  User: AYXYJ
  Date: 2021/7/24
  Time: 12:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main</title>
</head>
<!-- 引入 Bootstrap -->
<link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1>
                    <small>疫情打卡————管理系统</small>
                </h1>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-4 column">
            <a class="btn btn-primary" href="/index.jsp">系统首页</a>
            <a class="btn btn-info" href="/user/findAll">用户管理</a>
            <a class="btn btn-primary" href="/admin/loginOut" style="float: right">退出</a>
        </div>
    </div>
</div>
</body>
</html>
