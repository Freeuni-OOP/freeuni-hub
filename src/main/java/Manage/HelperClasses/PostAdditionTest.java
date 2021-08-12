package Manage.HelperClasses;

import DataBaseConnection.BaseConnector;
import org.junit.Before;
import org.junit.Test;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.assertEquals;

public class PostAdditionTest {
    BaseConnector bc;

    @Before
    public void setup() throws SQLException, ClassNotFoundException {
        bc = new BaseConnector();
    }

    @Test
    public void testPostAddition() throws SQLException {
        PostAddition postAddition = new PostAddition(bc);
        Connection connection = bc.accessConnection();
        Statement statement =connection.createStatement();
        postAddition.removePost(2000);
        statement.execute("delete from comments");
        statement.execute("Delete from posts");
        statement.execute("delete from users where id = 1000;");
        statement.execute("Insert into users (id,first_name,last_name,user_name,password,email)" +
                " values "+ "(1000,'luka','macho','mlfakfflsalme','123','MACSE')");
        postAddition.addPost(1000,"This is my first post",2000);
        ResultSet resultSet = statement.executeQuery("Select * from posts");
        while(resultSet.next()){
            assertEquals(1000,resultSet.getInt(2));
            assertEquals(2000,resultSet.getInt(1));
        }
        postAddition.removePost(2000);
        statement.execute("delete from users where id = 1000;");
    }

    @Test
    public void testNextId() throws SQLException {
        PostAddition postAddition = new PostAddition(bc);
        Connection connection = bc.accessConnection();
        Statement statement =connection.createStatement();
        statement.execute("delete from users where id = 1000;");
        statement.execute("Insert into users (id,first_name,last_name,user_name,password,email)" +
                " values "+ "(1000,'luka','macho','mlfakfflsalme','123','MACSE')");
        postAddition.addPost(1000,"This is my first post",2000);
        int nextId = postAddition.next();
        assertEquals(2001,nextId);
        postAddition.addPost(1000,"This is my second post",2003);
        nextId = postAddition.next();
        assertEquals(2004,nextId);
        postAddition.removePost(2000);
        postAddition.removePost(2003);
        statement.execute("delete from users where id = 1000;");
    }
}
