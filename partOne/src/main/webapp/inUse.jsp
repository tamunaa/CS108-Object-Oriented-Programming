<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Account Name in Use</title>
    <%-- Include Bootstrap CSS --%>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>

<div class="container">
    <div class="row justify-content-center">
        <div class="col-sm-6">
            <p>The account name you requested is already in use. Please choose a different username.</p>
            <br>
            <a href="createAccount.jsp" class="btn btn-link d-block text-center">Go back to Create Account</a>
        </div>
    </div>
</div>

</body>
</html>
