<%--
  Created by IntelliJ IDEA.
  User: AYXYJ
  Date: 2021/7/22
  Time: 21:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加用户</title>
</head>
<!-- 引入 Bootstrap -->
<!-- 引入 Bootstrap -->
<link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1>
                    <small>项目列表————新增用户</small>
                </h1>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-4 column">
            <a class="btn btn-primary" href="/index.jsp">首页</a>
            <a class="btn btn-info" href="/user/findAll">所有用户</a>
        </div>
    </div>
    <div class="row">
        <br/>
    </div>
    <div class="row clearfix">
        <div class="col-md-12 column">
            <form action="${pageContext.request.contextPath}/user/addUser" method="post">
                <div class="form-group">
                    <label>用户名称:</label>
                    <input type="text" class="form-control" name="username" placeholder="用户名称" required>
                </div>
                <div class="form-group">
                    <label>用户密码:</label>
                    <input type="text" class="form-control" name="password" placeholder="用户密码">
                </div>
                <div class="form-group">
                    <label>出生日期:</label>
                    <input type="datetime-local" class="form-control" name="birthday" placeholder="出生日期">
                </div>
                <div class="form-group">
                    <label>信誉值:</label>
                    <input type="text" class="form-control" name="val" placeholder="信誉值">
                </div>
                <button type="submit" class="btn btn-info">添加</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
