<%--
  Created by IntelliJ IDEA.
  User: mi
  Date: 2021/6/14
  Time: 21:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户注册</title>
</head>
<body>
<h1>用户注册</h1>
<form action="${pageContext.request.contextPath}/user/register" method="post">
    用户名：<input type="text" name="username"><br>
    密码 ：<input type="test" name="password"><br>
    <input type="submit" value="注册">
</form>
</body>
</html>
