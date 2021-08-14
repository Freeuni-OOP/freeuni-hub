package Servlets.FriendServlet;

import DataBaseConnection.BaseConnector;
import Manage.HelperClasses.FriendList;
import Manage.HelperClasses.User;
import com.mysql.cj.Session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "Show_Friend_Servlet", value = "/showFriends")
public class ShowFriendsServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        HttpSession session = request.getSession();
        try {
            FriendList friendList = new FriendList(new BaseConnector());
            List<User> friends = friendList.getFriendList(username);
            session.setAttribute("friendsList", friends);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        session.setAttribute("username", username);
        request.getRequestDispatcher("JSPs/PersonalHomePages/MyFriends.jsp").forward(request, response);
    }
}
