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
import java.util.ArrayList;


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

        int user_id = -1;
        try {
            user_id = ubi.getIdByUsername(username); // get user id
        } catch (SQLException ignored) {}

        

        // get info
        String newUsername = request.getParameter("user_name");
        String sex = request.getParameter("sex");

        String faculty = request.getParameter("faculty");
        int course = Integer.parseInt(request.getParameter("course"));

        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        String repeatedPassword = request.getParameter("repeatedPassword");

        PrintWriter pw = response.getWriter(); // need printWriter for message


        //-----------------------------------------------------------------------different cases
        if (oldPassword.equals(newPassword)) { // old and new mustn't match
            pw.println("ძველი და ახალი პაროლები არ უნდა ემთხვეოდეს, კიდევ ერთხელ წაიკითხეთ პაროლის მოთხოვნები.");
            request.getRequestDispatcher("/JSPs/PersonalHomePages/PersonalPage.jsp").forward(request, response);
            return;
        }

        if (!newPassword.equals(repeatedPassword)) { // new and repeated passwords must match
            pw.println("განმეორებული პაროლი არ ემთხვევა ახალს, გთხოვთ მეტი ყურადღებით შეავსოთ.");
            request.getRequestDispatcher("/JSPs/PersonalHomePages/PersonalPage.jsp").forward(request, response);
            return;
        }


        //-----------------------------------------------------------------------------------------

        ManageUser um = (ManageUser) getServletContext().getAttribute(USER_MANAGER_ATTRIBUTE); // get manager
        ArrayList<String> info = new ArrayList<>(); // this is final user info to check
        try {
            info = um.getUserInfo(user_id);
        } catch (SQLException ignored) {}

        try {
            if (um.isValidInput(info.get(0), info.get(1), newUsername, newPassword, info.get(4)).equals(ALL_GOOD)) {
                um.updateUser(user_id, newUsername, sex, faculty, course, newPassword);
            }else {
                pw.println("არალეგალური ინფუთი. სცადეთ ხელახლა");
            }
            request.getRequestDispatcher("/JSPs/PersonalHomePages/PersonalPage.jsp").forward(request, response);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}