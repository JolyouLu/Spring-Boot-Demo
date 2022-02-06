<%--
  Created by IntelliJ IDEA.
  User: mi
  Date: 2021/6/14
  Time: 21:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>主页</title>
</head>
<body>
<h1>我的门户</h1>
<a href="${pageContext.request.contextPath}/user/logout">退出登录</a>
<ul>
    <shiro:hasRole name="admin">
        <li><a href="">用户管理</a></li>
        <ul>
            <shiro:hasPermission name="user:add:*">
                <li><a href="${pageContext.request.contextPath}/user/add">新增用户</a></li>
            </shiro:hasPermission>
            <shiro:hasPermission name="user:update:*">
                <li><a href="${pageContext.request.contextPath}/user/update">修改用户</a></li>
            </shiro:hasPermission>
            <shiro:hasPermission name="user:delete:*">
                <li><a href="">删除用户</a></li>
            </shiro:hasPermission>
        </ul>
    </shiro:hasRole>
    <li><a href="">商品管理</a></li>
    <li><a href="">订单管理</a></li>
    <li><a href="">物流管理</a></li>
</ul>
</body>
</html>
