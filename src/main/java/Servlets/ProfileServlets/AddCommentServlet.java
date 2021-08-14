package Servlets.ProfileServlets;

import DataBaseConnection.BaseConnector;
import Manage.HelperClasses.CommentAddition;
import Manage.HelperClasses.UserById;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "Add_Comment_Servlet", value = "/addComment")
public class AddCommentServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String userName = request.getParameter("username");
        int post_id = Integer.parseInt(request.getParameter("postId"));
        String commentText = request.getParameter("commentText");
        System.out.println(commentText);
        System.out.println(post_id);
        System.out.println(userName);
        try {
            UserById userById = new UserById(new BaseConnector());
            int user_id = userById.getIdByUsername(userName);
            CommentAddition commentAddition = new CommentAddition(new BaseConnector());
            int next = commentAddition.nextId();
            commentAddition.addComment(user_id, post_id, next, commentText);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        session.setAttribute("username", userName);
        request.getRequestDispatcher("JSPs/PersonalHomePages/PersonalPage.jsp").forward(request, response);
    }
}
