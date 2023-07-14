<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List, Objects.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Student Store</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.3.0/css/bootstrap.min.css">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .container {
            margin-top: 50px;
        }
        .item-link {
            color: #007bff;
            text-decoration: none;
        }
        .item-link:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body style="background-color: #f8f9fa;">
<div class="container">
    <h1>Student Store</h1>
    <h4>Items Available:</h4>
    <ul>
        <%
            List<Product> list = (List<Product>) request.getServletContext().getAttribute("ProductList");
            String url = "item.jsp?";

            for (Product product : list) {
                String productId = product.getProductId();
                String productName = product.getName();
                out.println("<li><a class='item-link' href='" + url + "id=" + productId + "'>" + productName + "</a></li>");
            }
        %>
    </ul>
</div>
</body>
</html>
