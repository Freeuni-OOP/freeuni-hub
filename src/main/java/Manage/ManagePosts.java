package Manage;

import DataBaseConnection.BaseConnector;
import Manage.HelperClasses.Comment;
import Manage.HelperClasses.CommentList;
import Manage.HelperClasses.Post;
import Manage.HelperClasses.PostList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ManagePosts {
    private ArrayList<String> posts = new ArrayList<>();

    public void setPosts(ArrayList<String> posts) {
        this.posts = posts;
    }


    public static void getPosts(HttpServletRequest request, HttpServletResponse response) {
        String username = (String) request.getSession().getAttribute("username");
        String profileName = (String) request.getSession().getAttribute("profileName");
        HttpSession session = request.getSession();
        session.setAttribute("username", username);
        session.setAttribute("profileName", profileName);
//        System.out.println(profileName);
        //System.out.println(username);

        try {
            PostList postList = new PostList(new BaseConnector());
            List<Post> posts = postList.getPostList(profileName);
            Map<Post, List<Comment>> all = new HashMap<>();
            for (Post post : posts) {
                int post_id = post.getPostId();
                CommentList commentList = new CommentList(new BaseConnector());
                List<Comment> comments = commentList.getCommentList(post_id);
                all.put(post, comments);
            }
            session.setAttribute("all", all);
            request.setAttribute("all", all);

        } catch (
                SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
