<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List, Objects.*" %>
<!DOCTYPE html>
<%
    // Change this to your path
    String path = "store-images/";

    List<Product> data = (List<Product>) request.getServletContext().getAttribute("ProductList");
    Product product = null;
    String id = request.getParameter("id");

    for (Product p : data) {
        if (p.getProductId().equals(id)) {
            product = p;
            break;
        }
    }

    String imgPath = path + product.getImageFile();
%>
<html>
<head>
    <meta charset="UTF-8">
    <title><%= product.getName() %></title>
    <style>
        h1 {
            font-size: 24px;
            color: #333;
        }
        img {
            max-width: 300px;
            height: auto;
        }
        p {
            font-size: 18px;
        }
        .price {
            font-weight: bold;
            color: #f00;
        }
        .add-to-cart {
            background-color: #007bff;
            color: #fff;
            padding: 10px 20px;
            border: none;
            cursor: pointer;
            font-size: 16px;
            border-radius: 4px;
            transition: background-color 0.3s ease;
        }
        .add-to-cart:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
<form action="CartManager" method="post">
    <input name="id" type="hidden" value="<%= product.getProductId() %>">
    <h1><%= product.getName() %></h1>
    <img src="<%= imgPath %>" alt="Product Image">
    <p class="price">$<%= product.getPrice() %></p>
    <input type="submit" class="add-to-cart" value="Add to Cart">
</form>
</body>
</html>
