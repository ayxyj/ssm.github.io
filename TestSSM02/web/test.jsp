<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: AYXYJ
  Date: 2021/7/18
  Time: 10:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>test</title>
</head>
<script src="statics/js/jquery-3.6.0.js"></script>
<script>
    $(function (){
        $("#btn").click(function (){
            $.ajax({
                url: "${pageContext.request.contextPath}/a2",
                success:function (data){
                    alert(data);
                    var html = "";

                    for (let i = 0; i < data.length; i++) {
                        html += "<tr> \
                            <td>" + data[i].username + "</td> \
                            <td>" + data[i].password + "</td> \
                        </tr>"
                    }

                    $("#container").html(html);
                }
            })
        })
    });
</script>

<body>
<input type="button" value="加载数据" id="btn"><br/>
<table>
    <tr>姓名</tr>
    <tr>密码</tr>
    <tbody id="container">
    </tbody>
</table>
</body>
</html>
