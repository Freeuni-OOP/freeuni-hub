package Servlets.IdentificationServlets;


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
import java.sql.SQLException;
import java.util.ArrayList;


@WebServlet(name = "LogIn_Servlet", value = "/login")
public class LogInServlet extends HttpServlet implements Attributes, UserConfiguration {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        BaseConnector bc = (BaseConnector) getServletContext().getAttribute(BASE_CONNECTOR_ATTRIBUTE);
        ManageUser um = (ManageUser) getServletContext().getAttribute(USER_MANAGER_ATTRIBUTE);



        try {
            String result = um.isValidUser(username, password);
            if (result.equals(FOUND)) {
                UserById ubi = new UserById(bc);
                int user_id = ubi.getIdByUsername(username);
                ArrayList<String> info = um.getUserInfo(user_id); // get information about this user

                HttpSession session = request.getSession();
                session.setAttribute("firstname", info.get(0));
                session.setAttribute("lastname", info.get(1));
                session.setAttribute("username", username);
                session.setAttribute("mail", info.get(4));

                if (info.get(5) == null)
                    session.setAttribute("faculty", "არაა მითითებული");
                else session.setAttribute("faculty", info.get(5));

                if (info.get(6) == null)
                    session.setAttribute("course", "არაა მითითებული");
                else session.setAttribute("course", info.get(6));

                if (info.get(7) == null)
                    session.setAttribute("sex", "თავს შევიკავებ");
                else {
                    String sex = info.get(7);
                    switch (sex) {
                        case "no": session.setAttribute("sex", "თავს შევიკავებ"); break;
                        case "male": session.setAttribute("sex", "მამრობითი"); break;
                        case "female": session.setAttribute("sex", "მდედრობითი"); break;
                    }
                }


                request.getRequestDispatcher("/JSPs/PersonalHomePages/HomePage.jsp").forward(request, response);
            }else {
                request.getRequestDispatcher("/JSPs/IdentificationPages/InvalidLogIn.jsp").forward(request, response);
            }
        } catch (SQLException ignored) {}
    }
}