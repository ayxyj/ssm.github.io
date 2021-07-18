<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: AYXYJ
  Date: 2021/7/16
  Time: 17:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>allItems</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 引入 Bootstrap -->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="jumbotron">
    <h1>Hello, world!</h1>
    <p>...</p>
    <p><a class="btn btn-primary btn-lg" href="#" role="button">Learn more</a></p>
</div>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1>
                    <small>项目列表————显示所有项目</small>
                </h1>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-4 column">
             <a class="btn btn-primary" href="${pageContext.request.contextPath}/item/toAddItem">新增项目</a>
             <a class="btn btn-primary" href="${pageContext.request.contextPath}/item/allItems">显示所有项目</a>
        </div>
        <div class="col-md-4 column"></div>
        <div class="col-md-4 column">
           <form class="form-inline" action="${pageContext.request.contextPath}/item/queryItemByName" method="post" style="float: right">
               <span style="color: #ff0000;font-weight: bold" >${error}</span>
               <input type="text" name="queryName" class="form-control" placeholder="请输入要查询的名称">
               <input type="submit" class="btn btn-info" value="查询">
           </form>
        </div>
    </div>
    <div class="row clearfix">
        <div class="col-md-12 column">
            <table class="table table-hover">
                <thead>
                <tr>
                    <td class="center">ID</td>
                    <td>名称</td>
                    <td>价格</td>
                    <td>图片</td>
                    <td>创建时间</td>
                    <td>其他</td>
                    <td>操作</td>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="item" items="${list}">
                    <tr>
                        <td>${item.id}</td>
                        <td>${item.name}</td>
                        <td>${item.price}</td>
                        <td>${item.pic}</td>
                        <td>${item.createtime}</td>
                        <td>${item.detail}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/item/deleteItem/${item.id}">删除</a> &nbsp; | &nbsp;
                            <a href="${pageContext.request.contextPath}/item/toUpdateItem?id=${item.id}">更新</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <div class="row clearfix">

    </div>
</div>


<hr size="7" color="7"/>
<%--${msg}--%>
</body>
</html>
