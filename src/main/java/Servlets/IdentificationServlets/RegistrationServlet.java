package Servlets.IdentificationServlets;


import Manage.Configurations.UserConfiguration;
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
public class RegistrationServlet extends HttpServlet implements UserConfiguration {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
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
        String repeatPassword = request.getParameter("repeatPassword");
        String mail = request.getParameter("mail");
        String notEqualPassword = "პაროლები არ ემთხვევა";
        String result = um.isValidInput(firstName, lastName, username, password, mail);
        if (result.equals(ALL_GOOD)&& repeatPassword.equals(password)) {
            HttpSession session = request.getSession();
            session.setAttribute(username, username);
            try {
                um.addUser(firstName, lastName, username, password, mail);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            request.getRequestDispatcher("/JSPs/IdentificationPages/HomePage.jsp").forward(request, response);
        }else if (!result.equals(ALL_GOOD)){
            request.setAttribute("problem", result); // what was the problem
            request.getRequestDispatcher("/JSPs/IdentificationPages/InvalidRegistration.jsp").forward(request, response);
        }else{
             request.setAttribute("problem",notEqualPassword);
            request.getRequestDispatcher("/JSPs/IdentificationPages/InvalidRegistration.jsp").forward(request, response);
        }
    }

}
