package Manage.HelperClasses;

import DataBaseConnection.BaseConnector;

import javax.swing.plaf.nimbus.State;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CommentAddition {
    BaseConnector bc;
    public CommentAddition(BaseConnector bc){
        this.bc =bc;
    }
    public void addComment(int user_id, int post_id,int comment_id,String comment_text) throws SQLException {
        Connection connection = bc.accessConnection();
        Statement statement = connection.createStatement();
        statement.execute("Insert into comments (comment_id,user_id,post_id,comment_text) values " +
                "("+ comment_id+ ","+user_id+ ","+post_id+ ",'"+comment_text+"');");
    }
    public void removeComment(int user_id,int comment_id) throws SQLException {
        Connection connection = bc.accessConnection();
        Statement statement = connection.createStatement();
        statement.execute("Delete from comments where comment_id =" + comment_id+";");
    }
}
