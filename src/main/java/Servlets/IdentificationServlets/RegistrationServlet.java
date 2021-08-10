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

        String result = null;




        try {
            result = um.isValidInput(firstName, lastName, username, password, mail);
        } catch (SQLException ignored) { }
        assert result != null;
        if (result.equals(ALL_GOOD) && repeatPassword.equals(password)) {
            try {
                um.addUser(firstName, lastName, username, password, mail);
                // add user info too

            } catch (SQLException e) {
                e.printStackTrace();
            }


            // set into session
            HttpSession session = request.getSession();
            session.setAttribute("firstname", firstName);
            session.setAttribute("lastname", lastName);
            session.setAttribute("username", username);
            session.setAttribute("mail", mail);
            session.setAttribute("faculty", "არაა მითითებული");
            session.setAttribute("course", "არაა მითითებული");
            session.setAttribute("sex", "თავს შევიკავებ");
            request.getRequestDispatcher("/JSPs/PersonalHomePages/HomePage.jsp").forward(request, response);
        }else if (!result.equals(ALL_GOOD)){
            request.setAttribute("problem", result); // what was the problem
            request.getRequestDispatcher("/JSPs/IdentificationPages/InvalidRegistration.jsp").forward(request, response);
        }else{
            request.setAttribute("problem", NOT_EQUAL_PASSWORD);
            request.getRequestDispatcher("/JSPs/IdentificationPages/InvalidRegistration.jsp").forward(request, response);
        }
    }

}
