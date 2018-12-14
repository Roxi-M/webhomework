<%--
  Created by IntelliJ IDEA.
  User: Roxi酱
  Date: 2018/12/13
  Time: 23:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=GBK"
         pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=GBK">
    <title>登陆页面</title>
</head>
<body>
<form action="Login_Servlet" method="Post">
    用户名：<input type = "text" name = "usename">
    密码：<input type = "password" name = "password">
    <input type = "submit" value = "登陆">
    <input type = "reset" value = "重置">
</form>
</body>
</html>