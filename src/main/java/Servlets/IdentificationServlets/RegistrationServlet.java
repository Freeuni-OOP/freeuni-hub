package Servlets.IdentificationServlets;


import Manage.Configuration;
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
public class RegistrationServlet extends HttpServlet implements Configuration {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
        System.out.println("fds");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");

        ManageUser um = (ManageUser) getServletContext().getAttribute(Attributes.USER_MANAGER_ATTRIBUTE);
        assert um != null;
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String mail = request.getParameter("mail");

        if (um.isValidInput(firstName, lastName, username, password, mail).equals(ALL_GOOD)) {
            HttpSession session = request.getSession();
            session.setAttribute(username, username);
            try {
                um.addUser(username, password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            request.getRequestDispatcher("/JSPs/Identification/Welcome.jsp").forward(request, response);
        }else
            request.getRequestDispatcher("/JSPs/Identification/InvalidRegistration.jsp").forward(request, response);
    }

}
