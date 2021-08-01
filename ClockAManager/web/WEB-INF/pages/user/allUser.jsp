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

<link href="https://cdn.bootcss.com/bootstrap-switch/3.3.4/css/bootstrap3/bootstrap-switch.min.css" rel="stylesheet">
<script src="https://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap-switch/3.3.4/js/bootstrap-switch.min.js"></script>
<body>

<div class="container" style="height: 90%">
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
        <div class="col-md-4 column">
            <a class="btn btn-primary" href="/admin/goMain">首页</a>
            <a class="btn btn-info" href="/user/goAdd">添加用户</a>
        </div>
        <div class="col-md-4 column"></div>
        <div class="col-md-4 column">
            <span style="color: red ;font-size: 18px;font-weight: bold;position:absolute;transform: translate(0,20%)">${error}</span>
            <form class="form-inline" action="${pageContext.request.contextPath}/user/findUserByName" method="post"
                  style="float: right">
                <input type="text" name="uid" placeholder="请输入用户学号">
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
                    <th class="center">用户学号</th>
                    <th class="center">用户密码</th>
                    <th class="center">用户邮箱</th>
                    <th class="center">打卡状态</th>
                    <th class="center">用户操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="user" items="${list}">
                    <tr>
                        <td>${user.id}</td>
                        <td>${user.uid}</td>
                        <td>${user.upw}</td>
                        <td>${user.email}</td>
                        <td>
                            <input type="checkbox" name="my-checkbox" value="${user.record}" checked/>
                        </td>
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
<div class="row" style="position: absolute;transform: translate(50%,50%)">
    <div class="col-md-12 column">
        <span class="clearfix" id="demo"></span>
    </div>
</div>
</body>
<script>
    $('input[name="my-checkbox"]').bootstrapSwitch({
        "onColor": "success",
        "offColor": "danger",
        "onText": "是",
        "offText": "否"
    })
    ;
</script>
<script language=javascript>
    var int=self.setInterval("clock()",50)
    function clock()
    {
        var date = new Date();
        Y = date.getFullYear();
        MH = (date.getMonth() + 1) < 10 ? '0' +  (date.getMonth() + 1) : ( date.getMonth() + 1);
        D = date.getDate()      < 10 ? '0' + date.getDate()      : date.getDate() ;
        H = date.getHours()     < 10 ? '0' + date.getHours()     : date.getHours();
        M = date.getMinutes()   < 10 ? '0' + date.getMinutes()   : date.getMinutes();
        S = date.getSeconds()   < 10 ? '0' + date.getSeconds()   : date.getSeconds();
        document.getElementById("demo").innerHTML = "当前时间："+Y+"-"+MH+"-"+D+"  "+H+":"+M+":"+S;
    }
</script>

</html>
