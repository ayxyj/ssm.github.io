<%--
  Created by IntelliJ IDEA.
  User: AYXYJ
  Date: 2021/7/16
  Time: 20:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>addItem</title>
    <!-- 引入 Bootstrap -->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1>
                    <small>项目列表————修改项目</small>
                </h1>
            </div>
        </div>
    </div>
    <form action="${pageContext.request.contextPath}/item/updateItem" method="post">
        <input type="hidden" name="id" value="${Qitem[0].id}}"/>
        <div class="form-group">
            <label>项目名称:</label>
            <input type="text" class="form-control" name="name" placeholder="项目名称" value="${Qitem[0].name}" required>
        </div>
        <div class="form-group">
            <label>项目价格:</label>
            <input type="text" class="form-control" name="price" placeholder="价格" value="${Qitem[0].price}" required>
        </div>
        <div class="form-group">
            <label>项目图片:</label>
            <input type="text" class="form-control" name="pic" placeholder="图片" value="${Qitem[0].pic}" required>
        </div>
        <div class="form-group">
            <label>创建时间:</label>
            <input type="date" class="form-control" name="createtime" placeholder="创建时间" value="${Qitem[0].createtime}" required>
        </div>
        <div class="form-group">
            <label>项目其他:</label>
            <input type="text" class="form-control" name="detail" placeholder="详情" value="${Qitem[0].detail}" required>
        </div>
        <button type="submit" class="btn btn-info">修改</button>
    </form>
</div>

</body>
</html>
