package Servlets;

import java.io.IOException;
import java.io.Serial;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DataBase.*;
import Objects.*;

/**
 * Servlet implementation class CartManager
 */
@WebServlet("/CartManager")
public class CartManager extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> data = (List<Product>) request.getServletContext().getAttribute("ProductList");

        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        String id = request.getParameter("id");
        Product product = null;

        if(cart == null)cart = new Cart();

        if(id != null){
            String finalId = id;
            product = data.stream()
                    .filter(obj -> obj.getProductId().equals(finalId))
                    .findFirst()
                    .orElse(null);
            cart.updateCart(product, cart.getCount(product) + 1);
        }else{
            Enumeration<String> parameters = request.getParameterNames();
            while(parameters.hasMoreElements()){
                id = parameters.nextElement();
                for (Product p : data){
                    if (p.getProductId().equals(id))product = p;
                }
                cart.updateCart(product, Integer.parseInt(request.getParameter(id)));
            }
        }

        session.setAttribute("cart", cart);
        response.sendRedirect("cart.jsp");
    }
}