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
    <title>首页</title>
</head>
<body>
${userLoginInfo}

<a href="${pageContext.request.contextPath}/user/goOut">注销</a>
</body>
</html>
