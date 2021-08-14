package Manage.HelperClasses;

import DataBaseConnection.BaseConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CommentList {

    private BaseConnector bc;

    public CommentList(BaseConnector bc) {
        this.bc = bc;
    }

    // gets Post object by post_id
    public Post postById(int postId) throws SQLException {
        Connection connection = bc.accessConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("Select * from posts where post_id = " + postId + ";");
        Post post = null;
        while (resultSet.next()) {
            post = new Post(postId, resultSet.getInt("user_id"), resultSet.getString("post_text"));
        }
        return post;
    }

    // gets list of comment by post_id
    public List<Comment> getCommentList(int postId) throws SQLException {
        List<Comment> list = new ArrayList();

        Connection connection = bc.accessConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("Select * from comments where post_id = " + postId + ";");

        while (resultSet.next()) {
            list.add(new Comment(postById(postId), resultSet.getString("comment_text"),
                    resultSet.getInt("user_id")));
        }

        return list;
    }
}
