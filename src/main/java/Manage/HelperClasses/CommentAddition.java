package Manage.HelperClasses;

import DataBaseConnection.BaseConnector;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static java.lang.Math.max;

public class CommentAddition {
    BaseConnector bc;

    public CommentAddition(BaseConnector bc) {
        this.bc = bc;
    }

    public void addComment(int user_id, int post_id, int comment_id, String comment_text) throws SQLException {
        Connection connection = bc.accessConnection();
        Statement statement = connection.createStatement();
        statement.execute("Insert into comments (comment_id,user_id,post_id,comment_text) values " +
                "(" + comment_id + "," + user_id + "," + post_id + ",'" + comment_text + "');");
    }

    public void removeComment(int user_id, int comment_id) throws SQLException {
        Connection connection = bc.accessConnection();
        Statement statement = connection.createStatement();
        statement.execute("Delete from comments where comment_id =" + comment_id + ";");
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

