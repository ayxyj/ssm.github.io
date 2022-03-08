<%--
  Created by IntelliJ IDEA.
  User: AYXYJ
  Date: 2021/7/17
  Time: 21:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>index</title>
</head>
<script src="statics/js/jquery-3.6.0.js"></script>
<body>
<h1>
    <a href="${pageContext.request.contextPath}/user/toLogin">登录页面</a>
    <a href="${pageContext.request.contextPath}/user/goMain">系统首面</a>
</h1>
用户名：<input type="text" id="username" onblur="a()">

<br>

<hr/>
<form action="${pageContext.request.contextPath}/upload" method="post" enctype="multipart/form-data">
文件上传：<input type="file" name="file"/>
    <input type="submit" value="上传">
</form>
<br>
<a href="${pageContext.request.contextPath}/download">文件下载</a>
</body>

<script>
    function a() {
        $.post({
                url: "${pageContext.request.contextPath}/a1",
                data: {"name": $("#username").val()},
                success: function (data, status) {
                    alert(data)
                },
                error: function () {

                }
            }
        )
    }
</script>
</html>
