package Servlets;

import Data.AccountManager;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;

@WebServlet(name= "login", urlPatterns = {"/", "/login"})
public class login extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Perform login validation
        boolean isAuthenticated = authenticate(username, password);

        RequestDispatcher dispatcher;

        if (isAuthenticated){
            dispatcher = request.getRequestDispatcher("welcome.jsp");
        }else{
            request.setAttribute("incorrect", true);
            dispatcher = request.getRequestDispatcher("error.jsp");
        }

        dispatcher.forward(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }

    private boolean authenticate(String username, String password) {
        AccountManager manager = (AccountManager) getServletContext().getAttribute("AccountManager");
        return manager.contains(username, password);
    }
}

