<%--
  Created by IntelliJ IDEA.
  User: AYXYJ
  Date: 2021/7/18
  Time: 10:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>
</head>
<script src="statics/js/jquery-3.6.0.js"></script>
<script>
    function a(){
        $.ajax({
            url:"${pageContext.request.contextPath}/a3",
            data:{"name":$("#name").val()},
            success:function (data){
                if(data.toString()=="ok"){
                    $("#userInfo").css("color" , "green");
                }else{
                    $("#userInfo").css("color" , "red");
                }
                $("#userInfo").html(data);
            }
        });
    }
    function b(){
        $.ajax({
            url:"${pageContext.request.contextPath}/a3",
            data:{"pwd":$("#pwd").val()},
            success:function (data){
                if(data.toString()=="ok"){
                    $("#pwdInfo").css("color" , "green");
                }else{
                    $("#pwdInfo").css("color" , "red");
                }
                $("#pwdInfo").html(data);
            }
        });
    }
</script>
<body>
用户名：<input id="name" type="text" onblur="a()"><span id="userInfo"></span><br/>
密码：<input id="pwd" type="text" onblur="b()"><span id="pwdInfo"></span>
</body>
</html>
