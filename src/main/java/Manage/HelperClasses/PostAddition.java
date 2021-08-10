package Manage.HelperClasses;

import DataBaseConnection.BaseConnector;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PostAddition {
    private BaseConnector bc;
    public PostAddition(BaseConnector bc){
        this.bc = bc;
    }

    public void addPost(int user_id , String postText,int post_id) throws SQLException {
        Connection connection = bc.accessConnection();
        Statement statement = connection.createStatement();
        statement.execute("Insert into posts (post_id,user_id,post_text) values ("+post_id
                + ","+user_id + ",'"+ postText+"');");
    }
    public void removePost(int post_id) throws SQLException {
        Connection connection = bc.accessConnection();
        Statement statement = connection.createStatement();
        statement.execute("Delete from posts where post_id = " +post_id);
    }
}
