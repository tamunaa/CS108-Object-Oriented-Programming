<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>User Welcome</title>
</head>
<body>
    <h1>Welcome, <%= request.getParameter("username") %>!</h1>
    <p>You have successfully logged in.</p>
</body>
</html>
