<%--
  Created by IntelliJ IDEA.
  User: AYXYJ
  Date: 2021/7/23
  Time: 14:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>更新用户信息</title>
</head>
<!-- 引入 Bootstrap -->
<link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1>
                    <small>项目列表————更新用户</small>
                </h1>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-4 column">
            <a class="btn btn-primary" href="/admin/goMain">首页</a>
            <a class="btn btn-info" href="/user/findAll">所有用户</a>
        </div>
    </div>
    <div class="row">
        <br/>
    </div>
    <div class="row clearfix">
        <div class="col-md-12 column">
            <form action="${pageContext.request.contextPath}/user/updateUser" method="post">
                <input type="hidden" name="id" value="${user.id}">
                <div class="form-group">
                    <label>用户名称:</label>
                    <input type="text" class="form-control" name="uid" placeholder="用户学号" value="${user.uid}" required>
                </div>
                <div class="form-group">
                    <label>用户密码:</label>
                    <input type="text" class="form-control" name="upw" placeholder="用户密码" value="${user.upw}" required>
                </div>
                <div class="form-group">
                    <label>用户邮箱:</label>
                    <input type="text" class="form-control" name="email" placeholder="用户邮箱" value="${user.email}"  required>
                </div>
                <button type="submit" class="btn btn-info">更新用户</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
