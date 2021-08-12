package Servlets.FriendServlet;

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

@WebServlet(name = "Friend_Post_Servlet", value = "/visitPosts")
public class FriendPostsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String profileName = request.getParameter("profileName");
        HttpSession session = request.getSession();
        session.setAttribute("username",username);
        session.setAttribute("profileName",profileName);
        System.out.println(profileName);
        System.out.println(username);
        try {
            PostList postList = new PostList(new BaseConnector());
            List<Post> posts = postList.getPostList(profileName);
            Map<Post,List<Comment>> all = new HashMap<>();
            for(Post post : posts){
                int post_id =post.getPostId();
                CommentList commentList = new CommentList(new BaseConnector());
                List<Comment> comments = commentList.getCommentList(post_id);
                all.put(post,comments);
            }
            session.setAttribute("all",all);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("JSPs/PersonalHomePages/FriendsPosts.jsp").forward(request,response);
    }
}
