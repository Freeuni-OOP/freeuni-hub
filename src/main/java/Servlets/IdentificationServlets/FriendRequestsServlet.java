package Servlets.IdentificationServlets;

import DataBaseConnection.BaseConnector;
import Manage.HelperClasses.FriendRequesters;
import Manage.HelperClasses.User;
import Manage.HelperClasses.UserById;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "Friend_Request_Servlet", value = "/FriendRequests")
public class FriendRequestsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String userame = (String)session.getAttribute("username");
        int id=-1;
        ArrayList<User> requesters = new ArrayList<>();
        try {
            UserById userById = new UserById(new BaseConnector());
            id=userById.getIdByUsername(userame);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            FriendRequesters friendRequesters = new FriendRequesters(new BaseConnector());
            requesters = friendRequesters.getFriendRequesters(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        session.setAttribute("requesters",requesters);
        session.setAttribute("username",userame);

        request.getRequestDispatcher("/JSPs/IdentificationPages/FriendRequests.jsp").forward(request,response);
    }
}
