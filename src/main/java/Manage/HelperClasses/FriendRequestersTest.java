package Manage.HelperClasses;
import DataBaseConnection.BaseConnector;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.*;

public class FriendRequestersTest {

    BaseConnector bc;
    Connection connection;
    @Before
    public void setUp() throws SQLException, ClassNotFoundException {
        bc = new BaseConnector();
        connection = bc.accessConnection();
    }

    @Test
    public void getFriendRequestersTest() throws SQLException {
        Statement statement= connection.createStatement();
        statement.execute("delete from friendRequests where requester_id = 2000;");
        statement.execute("delete from usersInfo where user_id = 1000;");
        statement.execute("delete from users where id = 1000;");
        statement.execute("delete from usersInfo where user_id = 2000;");
        statement.execute("delete from users where id = 2000");
        statement.execute("Insert into users (id,first_name,last_name,user_name,password,email)" +
                " values "+ "(1000,'luka','macho','mlfakfflsalme','123','MAE')");
        statement.execute("Insert into users (id,first_name,last_name,user_name,password,email)" +
                " values "+ "(2000,'blukab','macho','mlfakflme','123','MARTVA')");
        statement.execute("Insert into usersInfo (user_id,user_name,user_last_name)" +
                " values "+ "(2000,'blukab','macho')");
        statement.execute("Insert into usersInfo (user_id,user_name,user_last_name)" +
                " values "+ "(1000,'luka','macho')");
        statement.execute("Insert into friendRequests (requester_id,receiver_id,accepted) " +
                "values  (2000,1000,false);");
        FriendRequesters friendRequesters = new FriendRequesters(bc);
        assertEquals(1,friendRequesters.getFriendRequesters(1000).size());
        assertEquals(2000,friendRequesters.getFriendRequesters(1000).get(0).getId());
        statement.execute("delete from friendRequests where requester_id = 2000;");
        statement.execute("delete from usersInfo where user_id = 1000;");
        statement.execute("delete from users where id = 1000;");
        statement.execute("delete from usersInfo where user_id = 2000;");
        statement.execute("delete from users where id = 2000");
    }

    @Test
    public void sendFriendRequestTest() throws SQLException {
        Statement statement = connection.createStatement();

        statement.execute("delete from friendRequests where requester_id = 2000;");
        statement.execute("delete from usersInfo where user_id = 1000;");
        statement.execute("delete from users where id = 1000;");
        statement.execute("delete from usersInfo where user_id = 2000;");
        statement.execute("delete from users where id = 2000");
        statement.execute("Insert into users (id,first_name,last_name,user_name,password,email)" +
                " values "+ "(1000,'luka','macho','mlfakfflsalme','123','MAE')");
        statement.execute("Insert into users (id,first_name,last_name,user_name,password,email)" +
                " values "+ "(2000,'blukab','macho','mlfakflme','123','MARTVA')");
        statement.execute("Insert into usersInfo (user_id,user_name,user_last_name)" +
                " values "+ "(2000,'blukab','macho')");
        statement.execute("Insert into usersInfo (user_id,user_name,user_last_name)" +
                " values "+ "(1000,'luka','macho')");

        FriendRequesters fr = new FriendRequesters(bc);
        assertFalse(fr.hasSentFriendRequest(2000, 1000));
        assertTrue(fr.sendFriendRequest(2000, 1000));
        assertFalse(fr.hasSentFriendRequest(1000, 2000));
        assertTrue(fr.hasSentFriendRequest(2000, 1000));

        statement.execute("delete from friendRequests where requester_id = 2000;");
        statement.execute("delete from usersInfo where user_id = 1000;");
        statement.execute("delete from users where id = 1000;");
        statement.execute("delete from usersInfo where user_id = 2000;");
        statement.execute("delete from users where id = 2000");
    }
}
