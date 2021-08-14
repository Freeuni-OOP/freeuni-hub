package Servlets.ProfileServlets;

import DataBaseConnection.BaseConnector;
import Manage.HelperClasses.FriendRequesters;
import Manage.HelperClasses.UserById;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "Add_Friend_Profile_Servlet", value = "/addFriend")
public class FriendServlet extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userName = request.getParameter("username");
        String profileName = request.getParameter("profileName");
        System.out.println(userName + " DF " + profileName);
        try {
            FriendRequesters friendAddition = new FriendRequesters(new BaseConnector());
            UserById userById = new UserById(new BaseConnector());
            int requester_id = userById.getIdByUsername(userName);
            int receiver_id = userById.getIdByUsername(profileName);
            friendAddition.sendFriendRequest(requester_id, receiver_id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("/JSPs/PersonalHomePages/HomePage.jsp").forward(request, response);
    }
}


