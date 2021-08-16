package Servlets.ProfileServlets;

import DataBaseConnection.BaseConnector;
import Manage.HelperClasses.Comment;
import Manage.HelperClasses.CommentList;
import Manage.HelperClasses.Post;
import Manage.HelperClasses.PostList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "Post_Servlet", value = "/Posts")
public class PostServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = (String)request.getSession().getAttribute("username");
        HttpSession session = request.getSession();
        session.setAttribute("username", username);
        //System.out.println(username);
        try {
            PostList postList = new PostList(new BaseConnector());
            List<Post> posts = postList.getPostList(username);
            Map<Post, List<Comment>> all = new HashMap<>();
            for (Post post : posts) {
                int post_id = post.getPostId();
                CommentList commentList = new CommentList(new BaseConnector());
                List<Comment> comments = commentList.getCommentList(post_id);
                all.put(post, comments);
            }
            session.setAttribute("all", all);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("JSPs/PersonalHomePages/ProfilePosts.jsp").forward(request, response);
    }
}
