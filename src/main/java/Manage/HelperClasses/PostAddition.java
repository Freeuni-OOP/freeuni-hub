package Manage.HelperClasses;

import DataBaseConnection.BaseConnector;
import com.mysql.cj.protocol.Resultset;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static java.lang.Math.max;

public class PostAddition {
    private BaseConnector bc;

    public PostAddition(BaseConnector bc) {
        this.bc = bc;
    }

    public void addPost(int user_id, String postText, int post_id) throws SQLException {
        Connection connection = bc.accessConnection();
        Statement statement = connection.createStatement();
        statement.execute("Insert into posts (post_id,user_id,post_text) values (" + post_id
                + "," + user_id + ",'" + postText + "');");
    }

    public void removePost(int post_id) throws SQLException {
        Connection connection = bc.accessConnection();
        Statement statement = connection.createStatement();
        statement.execute("Delete from posts where post_id = " + post_id);
    }

    public int next() throws SQLException {
        Connection connection = bc.accessConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("Select * from posts");
        int cur = 0;
        while (resultSet.next()) {
            cur = max(cur, resultSet.getInt(1));
        }
        return cur + 1;
    }
}
