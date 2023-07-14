package DataBase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import Objects.*;

/**
 * Application Lifecycle Listener implementation class Listener
 *
 */
@WebListener
public class Listener implements ServletContextListener {

    /**
     * Default constructor.
     */
    public Listener() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void contextInitialized(ServletContextEvent arg) {
        ConnectionManager manager = ConnectionManager.getInstance();
        List<Product> data = null;
        try {
            data = DB.getProductList(manager);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        ServletContext cont = arg.getServletContext();
        cont.setAttribute("ProductList", data);

    }

}