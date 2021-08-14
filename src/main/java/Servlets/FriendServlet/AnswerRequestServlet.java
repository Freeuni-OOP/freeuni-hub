package Servlets.FriendServlet;

import DataBaseConnection.BaseConnector;
import Manage.HelperClasses.FriendAddition;
import Manage.HelperClasses.UserById;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

@WebServlet(name = "Answer_Servlet", value = "/answerRequest")
public class AnswerRequestServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<String, String[]> es = request.getParameterMap();
        System.out.println(es.size());
        String userName = "";
        String secondUserName = "";
        for (String s : es.keySet()) {
            if (!s.equals("username") && secondUserName.equals("")) {
                secondUserName = es.get(s)[0];
            } else if (s.equals("username")) {
                userName = es.get(s)[0];
            }
        }
        System.out.println(userName + " " + secondUserName);
        String action = request.getParameter("action");
        System.out.println(action);
        int requester_id = 0, receiver_id = 0;
        try {
            UserById ubi = new UserById(new BaseConnector());
            receiver_id = ubi.getIdByUsername(userName);
            requester_id = ubi.getIdByUsername(secondUserName);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //dasamatebeli friend requestebidan amoshla
        if (action.equals("accept")) {
            try {
                System.out.println("shemovida");
                FriendAddition friendAddition = new FriendAddition(new BaseConnector());
                friendAddition.addFriend(requester_id, receiver_id);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            try {
                FriendAddition friendAddition = new FriendAddition(new BaseConnector());
                friendAddition.rejectFriend(requester_id, receiver_id);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        request.getRequestDispatcher("/JSPs/PersonalHomePages/HomePage.jsp").forward(request, response);
    }
}

