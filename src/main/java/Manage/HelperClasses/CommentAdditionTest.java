package Manage.HelperClasses;

import DataBaseConnection.BaseConnector;
import org.junit.Before;
import org.junit.Test;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static org.junit.Assert.assertEquals;

public class CommentAdditionTest {

    BaseConnector bc;
    @Before
    public void setup() throws SQLException, ClassNotFoundException {
        bc = new BaseConnector();
    }
    @Test
    public void testCommentAddition() throws SQLException {
        CommentAddition commentAddition = new CommentAddition(bc);
        PostAddition postAddition = new PostAddition(bc);
        Connection connection = bc.accessConnection();
        Statement statement =connection.createStatement();
        statement.execute("delete from comments");
        commentAddition.removeComment(1000,100);
        postAddition.removePost(2000);
        statement.execute("delete from users where id = 1000;");
        statement.execute("Insert into users (id,first_name,last_name,user_name,password,email)" +
                " values "+ "(1000,'luka','macho','mlfakfflsalme','123','MACSE')");
        postAddition.addPost(1000,"This is my first post",2000);
        commentAddition.addComment(1000,2000,100,"My first comment.");
        ResultSet rs = statement.executeQuery("Select * from comments");
        while(rs.next()){
            assertEquals(100,rs.getInt(1));
            assertEquals(2000,rs.getInt(3));
            assertEquals(1000,rs.getInt(2));
        }
        ResultSet resultSet = statement.executeQuery("Select * from posts");
        while(resultSet.next()){
            assertEquals(1000,resultSet.getInt(2));
            assertEquals(2000,resultSet.getInt(1));
        }
        commentAddition.removeComment(1000,100);
        postAddition.removePost(2000);
        statement.execute("delete from users where id = 1000;");
    }

    @Test
    public void testNextId() throws SQLException {
        CommentAddition commentAddition = new CommentAddition(bc);
        PostAddition postAddition = new PostAddition(bc);
        Connection connection = bc.accessConnection();
        Statement statement =connection.createStatement();
        commentAddition.removeComment(1000,100);
        postAddition.removePost(2000);
        statement.execute("delete from comments");
        statement.execute("delete from users where id = 1000;");
        statement.execute("Insert into users (id,first_name,last_name,user_name,password,email)" +
                " values "+ "(1000,'luka','macho','mlfakfflsalme','123','MACSE')");
        postAddition.addPost(1000,"This is my first post",2000);
        commentAddition.addComment(1000,2000,100,"My first comment.");
        int nextId =commentAddition.nextId();
        assertEquals(101,nextId);
        commentAddition.addComment(1000,2000,200,"meore comment");
        assertEquals(201,commentAddition.nextId());
        commentAddition.removeComment(1000,100);
        commentAddition.removeComment(1000,200);
        postAddition.removePost(2000);
        statement.execute("delete from users where id = 1000;");
    }
}
