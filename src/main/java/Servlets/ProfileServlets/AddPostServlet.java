package Servlets.ProfileServlets;


import DataBaseConnection.BaseConnector;
import Manage.HelperClasses.PostAddition;
import Manage.HelperClasses.UserById;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "Add_Post_Servlet", value = "/addPost")
public class AddPostServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String userName = request.getParameter("username");
        String postText = request.getParameter("postText");
        System.out.println(postText);
        System.out.println(userName);
        try {
            UserById userById = new UserById(new BaseConnector());
            int user_id = userById.getIdByUsername(userName);
            PostAddition postAddition = new PostAddition(new BaseConnector());
            int next = postAddition.next();
            postAddition.addPost(user_id, postText, next);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        session.setAttribute("username", userName);
        request.getRequestDispatcher("JSPs/PersonalHomePages/PersonalPage.jsp").forward(request, response);
    }
}
