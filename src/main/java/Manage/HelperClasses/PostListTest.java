package Manage.HelperClasses;

import DataBaseConnection.BaseConnector;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.*;

public class PostListTest {

    BaseConnector bc;
    Connection connection;
    @Before
    public void setUp() throws SQLException, ClassNotFoundException {
        bc = new BaseConnector();
        connection = bc.accessConnection();
    }

    @Test
    public void testPostList() throws SQLException {

        Statement statement = connection.createStatement();

        statement.execute("Insert into users (id,first_name,last_name,user_name,password,email)" +
                " values "+ "(1000,'luka','macho','mlfakfflsalme','123','MAE')");
        PostAddition pa = new PostAddition(bc);
        PostList pl = new PostList(bc);
        assertEquals(0, pl.getPostList(1000).size());
        pa.addPost(1000, "hello", 10);
        assertEquals(1, pl.getPostList(1000).size());
        pa.addPost(1000, "goodbye", 11);
        assertEquals(2, pl.getPostList(1000).size());
        Post post1 = pl.getPostList(1000).get(0);
        Post post2 = pl.getPostList(1000).get(1);

        assertTrue((post1.getPostId() == 10 && post1.getText().equals("hello") && post2.getPostId() == 11 && post2.getText().equals("goodbye")) ||
                (post2.getPostId() == 10 && post1.getText().equals("goodbye") && post1.getPostId() == 11 && post2.getText().equals("hello")));
        pa.removePost(10);
        pa.removePost(11);
        assertFalse(pl.getPostList(1000).size() > 0);

        statement.execute("delete from users where first_name = 'luka';");
    }
}
