package Servlets.IdentificationServlets;


import Manage.ManageUser;
import StarterManager.Attributes;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet(name = "Register_Servlet", value = "/register")
public class RegistrationServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");

        ManageUser um = (ManageUser) getServletContext().getAttribute(Attributes.USER_MANAGER_ATTRIBUTE);
        assert um != null;
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (um.isValidInput(username, password)) {
            HttpSession session = request.getSession();
            session.setAttribute(username, username);
            try {
                um.addUser(username, password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            request.getRequestDispatcher("/Welcome.jsp").forward(request, response);
        }else
            request.getRequestDispatcher("/InvalidRegistration.jsp").forward(request, response);
    }

}
