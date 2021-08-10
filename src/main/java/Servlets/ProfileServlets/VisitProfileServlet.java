package Servlets.ProfileServlets;

import DataBaseConnection.BaseConnector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;


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
        boolean isBlocked = false, isFriend = false;
        if(isBlocked) {
            request.getRequestDispatcher("/JSPs/ProfilePages/blockedProfile.jsp").forward(request, response);
        }else if(isFriend){
            request.getRequestDispatcher("/JSPs/ProfilePages/friendProfile.jsp").forward(request, response);
        }else{
            request.getRequestDispatcher("/JSPs/ProfilePages/unFriendProfile.jsp").forward(request,response);
        }
    }
}
