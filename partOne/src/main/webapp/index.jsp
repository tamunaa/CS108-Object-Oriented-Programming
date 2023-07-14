<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Login Page</title>
    <%-- Include Bootstrap CSS --%>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>

<div class="container Light">
    <div class="row justify-content-center">
        <div class="col-sm-6">
            <h1 class="text-center">Please Log In</h1>
            <form method="POST" action="login" class="needs-validation" novalidate>
                <div class="mb-3">
                    <label for="username" class="form-label">Username:</label>
                    <input type="text" class="form-control" id="username" name="username" required>
                    <div class="invalid-feedback">Please enter your username.</div>
                </div>
                <div class="mb-3">
                    <label for="password" class="form-label">Password:</label>
                    <input type="password" class="form-control" id="password" name="password" required>
                    <div class="invalid-feedback">Please enter your password.</div>
                </div>
                <button type="submit" class="btn btn-primary">Log In</button>
                <a href="createAccount.jsp" class="btn btn-link">Create New Account</a>
            </form>
        </div>
    </div>
</div>

</body>
</html>
