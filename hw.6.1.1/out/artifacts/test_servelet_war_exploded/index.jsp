<%--
  Created by IntelliJ IDEA.
  User: Roxi酱
  Date: 2018/12/8
  Time: 15:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=GBK"
         pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"         "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=GBK">
  <title>写的什么鬼玩意儿</title>
</head>
<body>
<form action = "Register_Servlet" method = "Post">
  用户名:<input type = "text" name = "usename">
  密码:<input type = "password" name = "pd">
  <input type = "submit" value = "注册">
  <input type = "reset" value = "重置">
  <a href="login.jsp">登录</a>
</form>
</body>
</html>