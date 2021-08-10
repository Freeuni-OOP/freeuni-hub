package Servlets.PersonalHomeServlets;


import DataBaseConnection.BaseConnector;
import Manage.Configurations.UserConfiguration;
import Manage.HelperClasses.UserById;
import Manage.ManageUser;
import StarterManager.Attributes;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;


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

        BaseConnector bc = (BaseConnector) request.getServletContext().getAttribute(BASE_CONNECTOR_ATTRIBUTE);
        UserById ubi = new UserById(bc);

        try {
            int user_id = ubi.getIdByUsername(username); // get user id
        } catch (SQLException ignored) {}

        

        // get info
        String newUsername = request.getParameter("user_name");
        String sex = request.getParameter("sex");

        String faculty = request.getParameter("faculty");
        String course = request.getParameter("course");

        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        String repeatedPassword = request.getParameter("repeatedPassword");

        ManageUser um = (ManageUser) getServletContext().getAttribute(USER_MANAGER_ATTRIBUTE); // get manager

//        if (um.isValidInput()) {
//            request.getRequestDispatcher("/JSPs/PersonalHomePages/PersonalPage.jsp").forward(request, response);
//        }else {
//            PrintWriter pw = response.getWriter();
//            pw.println("incorrect fields");
//        }
    }
}