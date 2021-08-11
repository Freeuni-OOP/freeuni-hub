package Servlets.ProfileServlets;

import DataBaseConnection.BaseConnector;
import Manage.HelperClasses.BlockUser;
import Manage.HelperClasses.UserById;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "UnBlock_User_Servlet", value = "/unblockUser")
public class UnblockServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userName = request.getParameter("username");
        String profileName = request.getParameter("profileName");
        System.out.println(userName +" ganblokva "+ profileName);
        try {
            BlockUser blockUser = new BlockUser(new BaseConnector());
            UserById userById = new UserById(new BaseConnector());
            int blocker_id = userById.getIdByUsername(userName);
            int blocked_id = userById.getIdByUsername(profileName);
            blockUser.unblockById(blocker_id,blocked_id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("/JSPs/PersonalHomePages/HomePage.jsp").forward(request,response);
    }
}
