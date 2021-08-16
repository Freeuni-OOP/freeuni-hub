package Manage.HelperClasses;

import DataBaseConnection.BaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        PreparedStatement preparedStatement = connection.prepareStatement("Select * from posts where post_id = ?;");
        preparedStatement.setInt(1, postId);
        ResultSet resultSet = preparedStatement.executeQuery();
        Post post = null;
        while (resultSet.next()) {
            post = new Post(postId, resultSet.getInt("user_id"), resultSet.getString("post_text"));
        }
        preparedStatement.close();
        return post;
    }

    // gets list of comment by post_id
    public List<Comment> getCommentList(int postId) throws SQLException {
        List<Comment> list = new ArrayList();

        Connection connection = bc.accessConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("Select * from comments where post_id = ?;");
        preparedStatement.setInt(1, postId);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            list.add(new Comment(postById(postId), resultSet.getString("comment_text"),
                    resultSet.getInt("user_id")));
        }
        preparedStatement.close();
        return list;
    }
}
