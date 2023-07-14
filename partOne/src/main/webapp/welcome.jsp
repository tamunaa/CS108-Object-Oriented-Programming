<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>User Welcome</title>
    <%-- Include Bootstrap CSS --%>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>

<div class="container Light">
    <div class="row justify-content-center">
        <div class="col-sm-6">
            <h1 class="text-center">Welcome, <%= request.getParameter("username") %>!</h1>
            <p class="text-center">You have successfully logged in.</p>
        </div>
    </div>
</div>

</body>
</html>
