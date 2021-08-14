package Manage.HelperClasses;

import DataBaseConnection.BaseConnector;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class CommentTest {

    BaseConnector bc;
    Connection con;
    Post post;

    @Before
    public void setup() throws SQLException, ClassNotFoundException {
        bc = new BaseConnector();
        con = bc.accessConnection();
        post = new Post(7, 8, "post");
    }

    @Test
    public void testComment() {
        Comment comment1 = new Comment(post, "yochag", 1);
        assertEquals("yochag", comment1.getComment());
        Comment comment2 = new Comment(post, "gilocav", 2);
        assertEquals("gilocav", comment2.getComment());
        Comment comment3 = new Comment(post, "naxvamdis", 3);
        assertEquals("naxvamdis", comment3.getComment());
        Comment comment4 = new Comment(post, "amin", 4);
        assertEquals("amin", comment4.getComment());
        Comment comment5 = new Comment(post, "<3", 5);
        assertEquals("<3", comment5.getComment());
    }

    @Test
    public void testUserId() {
        Comment comment1 = new Comment(post, "yochag", 12);
        assertEquals(12, comment1.getUserId());
        Comment comment2 = new Comment(post, "gilocav", 22);
        assertEquals(22, comment2.getUserId());
        Comment comment3 = new Comment(post, "naxvamdis", 32);
        assertEquals(32, comment3.getUserId());
        Comment comment4 = new Comment(post, "amin", 122);
        assertEquals(122, comment4.getUserId());
        Comment comment5 = new Comment(post, "<3", 122);
        assertEquals(122, comment5.getUserId());
    }

    @Test
    public void testGetPost() {
        Comment comment1 = new Comment(post, "yochag", 12);
        assertEquals(post, comment1.getPost());
    }

}
