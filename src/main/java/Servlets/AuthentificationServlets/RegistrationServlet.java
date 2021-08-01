package Servlets.AuthentificationServlets;


import StarterManager.Listeners.Attributes;
import StarterManager.Manage.UserManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet(name = "Register_Servlet", value = "/register")
public class RegistrationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");

        UserManager um = (UserManager) getServletContext().getAttribute(Attributes.USER_MANAGER_ATTRIBUTE);
        assert um != null;
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (um.isValidInput(username, password)) {
            request.setAttribute(Attributes.USER_NAME_ATTRIBUTE, username);
            try {
                um.addUser(username, password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            request.getRequestDispatcher("/Welcome.jsp").forward(request, response);
            return;
        }

        request.getRequestDispatcher("/InvalidRegistration.jsp").forward(request, response);
    }

}
