<%--
  Created by IntelliJ IDEA.
  User: AYXYJ
  Date: 2021/5/21
  Time: 21:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h3>入门程序</h3>

<a href="hello">入门程序</a><br/>
<a href="Account/parameter?username=hha&money=10">参数绑定</a><br/>
<form action="Account/saveAccount" method="post">
    账 户 名 :<input id="uaccound" type="text" name="username"/><br/>
    余 额 :<input id="money" type="text" name="money"/><br/>
    用 户 名 :<input id="uname" type="text" name="user.name"/><br/>
    性 别 :<input id="sex" type="text" name="user.sex"/><br/>

    用 户 名 :<input id="uname_list" type="text" name="userList[0].name"/><br/>
    性 别 :<input id="sex_list" type="text" name="userList[0].sex"/><br/>

    用 户 名 :<input id="uname_map" type="text" name="userMap['1'].name"/><br/>
    性 别 :<input id="sex_map" type="text" name="userMap['1'].sex"/><br/>
    <input type="submit" value="提交"/>
</form>
<hr color="red" size="5"/>
<form action="user/saveUser" method="post">
    save用 户 名 :<input type="text" name="name"/><br/>
    性 别 :<input type="text" name="sex"/><br/>
    出生日期 :<input type="date" name="date"><br/>
    <input type="submit" value="提交"/><br/>
</form>
<hr color="red" size="5"/>
<form action="user/printUser" method="post"><br/>
    print用 户 名 :<input type="text" name="uname"/><br/>
    性 别 :<input type="text" name="sex"/><br/>
    出生日期 :<input type="date" name="date"><br/>
    <input type="submit" value="提交"/><br/>
</form>
<hr color="red" size="5"/>
<form action="user/testRequestBody" method="post">
    用 户 名 :<input type="text" name="uname"/><br/>
    性 别 :<input type="text" name="sex"/><br/>
    出生日期 :<input type="date" name="date"><br/>
    <input type="submit" value="提交"/><br/>
</form>
<hr color="red" size="5"/>
<form action="user/testModelAttribute" method="post">
    testModelAttribute 户 名 :<input type="text" name="name"/><br/>
    性 别 :<input type="text" name="sex"/><br/>
    <input type="submit" value="提交"/><br/>
</form>

<a href="/user/testPathVaribale/10">PathVaribale</a>
<a href="/user/testRequestHeader">RequestHeader</a>
<a href="/user/testCookieVale">CookieVale</a>
<a href="/scope/testSessionAttribute">setSessionAttribute</a>
<a href="/scope/getSessionAttribute">getSessionAttribute</a>
<a href="/scope/delSessionAttribute">delSessionAttribute</a>

</body>
</html>
