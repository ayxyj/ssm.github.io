<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: AYXYJ
  Date: 2021/7/22
  Time: 20:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>AllUser</title>
</head>
<!-- 引入 Bootstrap -->
<link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<body>

<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1>
                    <small>项目列表————显示所有用户</small>
                </h1>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-md-1 column" style="float: right">
            <a class="btn btn-primary" href="/user/loginOut">退出</a>
        </div>
    </div>
    <hr/>
    <div class="row">
        <div class="col-md-4 column">
            <a class="btn btn-primary" href="/index.jsp">首页</a>
            <a class="btn btn-info" href="/user/goAdd">添加用户</a>
        </div>
        <div class="col-md-4 column"></div>
        <div class="col-md-4 column">
            <span style="color: #ff0000;font-weight: bold">${error}</span>
            <form class="form-inline" action="${pageContext.request.contextPath}/user/findUserByName" method="post"
                  style="float: right">
                <input type="text" name="username" placeholder="请输入用户名称">
                <input class="btn btn-primary" type="submit" value="查询">
            </form>
        </div>
    </div>
    <div class="row clearfix">
        <div class="col-md-12 column">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th class="center">ID</th>
                    <th class="center">用户名称</th>
                    <th class="center">用户密码</th>
                    <th class="center">出生日期</th>
                    <th class="center">信誉值</th>
                    <th class="center">操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="user" items="${list}">
                    <tr>
                        <td>${user.id}</td>
                        <td>${user.username}</td>
                        <td>${user.password}</td>
                        <td><fmt:formatDate value="${user.birthday}" pattern="yyyy-MM-dd HH:mm"/></td>
                        <td>${user.val}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/user/goUpdate/${user.id}">更新</a>
                            <a href="${pageContext.request.contextPath}/user/deleteUserById?id=${user.id}">删除</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-md-12 column">
        <span class="clearfix"> 当前日期：<%=(new java.util.Date()).toLocaleString()%></span>
    </div>
</div>
</body>
</html>
