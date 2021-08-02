package StarterManager;


import DataBaseConnection.BaseConnector;
import Manage.ManageUser;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.SQLException;


@WebListener
public class StartingListener implements ServletContextListener, Attributes {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext sc = sce.getServletContext();

        BaseConnector bc = null;
        try {
            bc = new BaseConnector();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        ManageUser userManager = null;
        try {
            assert bc != null;
            userManager = new ManageUser(bc);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }


        sc.setAttribute(BASE_CONNECTOR_ATTRIBUTE, bc);
        sc.setAttribute(USER_MANAGER_ATTRIBUTE, userManager);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            BaseConnector.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}