package Manage.HelperClasses;

import DataBaseConnection.BaseConnector;

import java.lang.ref.PhantomReference;
import java.sql.*;

import static java.lang.Math.max;

public class CommentAddition {
    BaseConnector bc;

    public CommentAddition(BaseConnector bc) {
        this.bc = bc;
    }

    public void addComment(int user_id, int post_id, int comment_id, String comment_text) throws SQLException {
        Connection connection = bc.accessConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("Insert into comments (comment_id,user_id,post_id,comment_text) values " +
                "(?,?,?,?);");
        preparedStatement.setInt(1,comment_id);
        preparedStatement.setInt(2,user_id);
        preparedStatement.setInt(3,post_id);
        preparedStatement.setString(4,comment_text);
        preparedStatement.execute();
        preparedStatement.close();
    }

    public void removeComment(int user_id, int comment_id) throws SQLException {
        Connection connection = bc.accessConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("Delete from comments where comment_id = ? ");
        preparedStatement.setInt(1,comment_id);
        preparedStatement.execute();
        preparedStatement.close();
    }

    public int nextId() throws SQLException {
        Connection connection = bc.accessConnection();
        Statement statement = connection.createStatement();
        ResultSet resultset = statement.executeQuery("select comment_id from comments");
        int cur = 0;
        while (resultset.next()) {
            cur = max(cur, resultset.getInt(1));
        }
        return cur + 1;
    }
}

