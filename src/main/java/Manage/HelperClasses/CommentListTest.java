package Manage.HelperClasses;

import DataBaseConnection.BaseConnector;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.assertEquals;

public class CommentListTest {
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
        CommentAddition commentAddition = new CommentAddition(bc);
        PostAddition pa = new PostAddition(bc);
        commentAddition.removeComment(1000, 2);
        commentAddition.removeComment(1000, 1);
        pa.removePost(10);
        statement.execute("delete from users where id = 1000;");
        statement.execute("Insert into users (id,first_name,last_name,user_name,password,email)" +
                " values " + "(1000,'luka','macho','mlfakfflsalme','123','MAE')");
        pa.addPost(1000, "hello", 10);
        commentAddition.removeComment(1000, 2);
        commentAddition.removeComment(1000, 1);
        commentAddition.addComment(1000, 10, 1, "First comment");
        CommentList commentList = new CommentList(bc);
        assertEquals(1, commentList.getCommentList(10).size());
        commentAddition.addComment(1000, 10, 2, "Second commit");
        assertEquals(2, commentList.getCommentList(10).size());
        commentAddition.removeComment(1000, 2);
        commentAddition.removeComment(1000, 1);
        pa.removePost(10);
        statement.execute("delete from users where id = 1000;");
    }
}
