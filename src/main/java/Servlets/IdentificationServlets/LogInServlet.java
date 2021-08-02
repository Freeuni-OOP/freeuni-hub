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
import java.io.PrintWriter;
import java.sql.SQLException;



@WebServlet(name = "LogIn_Servlet", value = "/login")
public class LogInServlet extends HttpServlet implements Attributes, Configuration {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        ManageUser um = (ManageUser) getServletContext().getAttribute(USER_MANAGER_ATTRIBUTE);

        try {
            String result = um.isValidUser(username, password);
            if (!result.equals(FOUND)) {
                HttpSession session = request.getSession();
                session.setAttribute(username, username);
                request.getRequestDispatcher("/JSPs/Identification/Welcome.jsp").forward(request, response);
            }else {
                PrintWriter pw = response.getWriter();
                pw.println(result);
            }
        } catch (SQLException ignored) {}
    }
}