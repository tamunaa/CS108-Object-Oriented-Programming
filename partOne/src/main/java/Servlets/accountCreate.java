package Servlets;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import Data.AccountManager;


@WebServlet(name= "create", urlPatterns = {"/accountCreate"})
public class accountCreate extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the form data
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        AccountManager manager = (AccountManager) getServletContext().getAttribute("AccountManager");
        int success = manager.add(username, password);

        RequestDispatcher dispatcher;
        if (success == AccountManager.CODE_SUCCESS){
            dispatcher = request.getRequestDispatcher("welcome.jsp");
        }else if (success == AccountManager.CODE_IN_USE){
            dispatcher = request.getRequestDispatcher("inUse.jsp");
        } else if (success == AccountManager.CODE_ERROR) {
            request.setAttribute("empty data", true);
            dispatcher = request.getRequestDispatcher("error.jsp");
        }else {
            dispatcher = request.getRequestDispatcher("homepage.jsp");
        }
        dispatcher.forward(request, response);

        System.out.println(username+ " " + password);
    }


}
