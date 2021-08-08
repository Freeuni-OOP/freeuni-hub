package Servlets.PersonalHomeServlets;


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


@WebServlet(name = "Profile_Update", value = "/update")
public class ProfileUpdateServlet extends HttpServlet implements Attributes, UserConfiguration {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }



    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");

        HttpSession session = request.getSession(false); // get session
        String username = (String)session.getAttribute("username"); // get username
        String curPassword = "";

        // get info
        String faculty = request.getParameter("faculty");
        String course = request.getParameter("course");

        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("oldPassword");
        String repeatedPassword = request.getParameter("oldPassword");

        ManageUser um = (ManageUser) getServletContext().getAttribute(USER_MANAGER_ATTRIBUTE); // get manager

        // if (um.isValidInput(firstName, lastName, ))

        request.getRequestDispatcher("/JSPs/PersonalHomePages/PersonalPage.jsp").forward(request, response);
    }
}