package Manage.HelperClasses;

import DataBaseConnection.BaseConnector;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class PostTest {

    BaseConnector bc;
    Connection con;

    @Before
    public void setup() throws SQLException, ClassNotFoundException {
        bc = new BaseConnector();
        con = bc.accessConnection();
    }

    @Test
    public void testPostId() {
        Post post1 = new Post(20, 40, "FeelingGood");
        assertEquals(20, post1.getPostId());
        Post post2 = new Post(27, 47, "Unknown");
        assertEquals(27, post2.getPostId());
        Post post3 = new Post(22, 42, "-_-");
        assertEquals(22, post3.getPostId());
    }

    @Test
    public void testUserId() {
        Post post1 = new Post(220, 402, "FeelingGood");
        assertEquals(402, post1.getUserId());
        Post post2 = new Post(237, 427, "Unknown");
        assertEquals(427, post2.getUserId());
        Post post3 = new Post(122, 4222, "-_-");
        assertEquals(4222, post3.getUserId());
    }

    @Test
    public void testText() {
        Post post1 = new Post(22320, 42302, "FeelingGood");
        assertEquals("FeelingGood", post1.getText());
        Post post2 = new Post(23237, 42327, "Unknown");
        assertEquals("Unknown", post2.getText());
        Post post3 = new Post(12322, 422322, "-_-");
        assertEquals("-_-", post3.getText());
    }
}
