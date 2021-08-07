package Manage.HelperClasses;

import DataBaseConnection.BaseConnector;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.assertEquals;

public class FriendListTest {

    BaseConnector bc;
    @Before
    public void setup() throws SQLException, ClassNotFoundException {
        bc = new BaseConnector();
    }

    @Test
    public void testFriendList() throws SQLException {
        Connection connection = bc.accessConnection();
        Statement statement = connection.createStatement();

        statement.execute("Insert into users (id, first_name, last_name, user_name, password, email)" +
                " values "+ "(100, 'keith', 'markovic', 'naf_fly', 'val1D', 'kmark15@freeuni.edu.ge')");
        statement.execute("Insert into users (id, first_name, last_name, user_name, password, email)" +
                " values "+ "(101, 'adam', 'friberg', 'friberg', 'val1D', 'afrib15@freeuni.edu.ge')");
        statement.execute("Insert into users (id, first_name, last_name, user_name, password, email)" +
                " values "+ "(102, 'patrick', 'lindberg', 'forest', 'val1D', 'plind15@freeuni.edu.ge')");
        statement.execute("Insert into users (id, first_name, last_name, user_name, password, email)" +
                " values "+ "(103, 'niko', 'kovac', 'nikokovac', 'val1D', 'nkova15@freeuni.edu.ge')");

        statement.execute("Insert into friends (requester_id, receiver_id)" +
                " values " + "(102, 101)");
        statement.execute("Insert into friends (requester_id, receiver_id)" +
                " values " + "(103, 102)");

        FriendList friendList = new FriendList(bc);
        assertEquals(0, friendList.getFriendList(100).size());
        assertEquals(0, friendList.getFriendList("naf_fly").size());
        assertEquals(1, friendList.getFriendList(101).size());
        assertEquals(1, friendList.getFriendList("friberg").size());
        assertEquals(2, friendList.getFriendList(102).size());
        assertEquals(2, friendList.getFriendList("forest").size());
        assertEquals(1, friendList.getFriendList(103).size());
        assertEquals(1, friendList.getFriendList("nikokovac").size());

        statement.execute("delete from friends where requester_id = 102 and receiver_id = 101");
        statement.execute("delete from friends where requester_id = 103 and receiver_id = 102");

        statement.execute("delete from users where id = 100;");
        statement.execute("delete from users where id = 101;");
        statement.execute("delete from users where id = 102;");
        statement.execute("delete from users where id = 103;");
    }
}
