<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Invalid Login</title>
    <%-- Include Bootstrap CSS --%>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .container {
            margin-top: 50px;
        }
    </style>
</head>
<body>

<div class="container">
    <div class="row justify-content-center">
        <div class="col-sm-6">
            <h1 class="text-center">Invalid Login</h1>
            <h1 class="text-center"> <jsp:include page="handleErrors.jsp" /> </h1>>
            <br>
            <a href="index.html" class="btn btn-link d-block text-center">Go back to Homepage</a>
        </div>
    </div>
</div>

</body>
</html>
