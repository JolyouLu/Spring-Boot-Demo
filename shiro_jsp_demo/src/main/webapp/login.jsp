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
    <title>用户登录</title>
</head>
<body>
<h1>用户登录</h1>
<form action="${pageContext.request.contextPath}/user/login" method="post">
    用户名：<input type="text" name="username"><br>
    密码 ：<input type="test" name="password"><br>
    请输入验证码：<input type="text" name="code"><img src="${pageContext.request.contextPath}/user/getImage"/><br>
    <input type="submit" value="登录">
</form>
</body>
</html>
