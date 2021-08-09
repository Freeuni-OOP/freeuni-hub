package Servlets.ProfileServlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet(name = "Visit_Profile_Servlet", value = "/visitProfile")
public class VisitProfileServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        HttpSession session = request.getSession();
        String userName = request.getParameter("username");
        String profileName = request.getParameter("profileName");
        session.setAttribute("username",userName);
        session.setAttribute("profileName",profileName);
        System.out.println(userName +" "+  profileName);
    }
}
