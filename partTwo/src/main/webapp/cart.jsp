<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List, Objects.*" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Shopping Cart</title>
  <!-- Bootstrap CSS -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.3.0/css/bootstrap.min.css">
</head>
<body>
<div class="container">
  <h1 class="mt-4">Shopping Cart</h1>
  <form action="./CartManager" method="post">
    <ul class="list-group">
      <%
        List<Product> data = (List<Product>) request.getServletContext().getAttribute("ProductList");
        Cart cart = (Cart) session.getAttribute("cart");
        double total = cart.getTotalPrice();

        for (Product product : data) {
          int count = cart.getCount(product);
          if (count != 0) {
            out.print("<li class='list-group-item d-flex align-items-center'>"
                    + "<input class='form-control me-2' style='width: 70px' type='number' value='" + count + "' name='" + product.getProductId() + "' min='1'>"
                    + "<span>" + product.getName() + ", " + product.getPrice() + "</span></li>");
          }
        }
      %>
    </ul>
    <p class="mt-3 fs-5">Total: <span class="fw-bold text-danger"><%= total %></span></p>
    <input type="submit" class="btn btn-primary btn-lg mt-3" value="Update Cart">
  </form>
  <a href="index.jsp" class="btn btn-link mt-3">Continue shopping</a>
</div>
</body>
</html>
