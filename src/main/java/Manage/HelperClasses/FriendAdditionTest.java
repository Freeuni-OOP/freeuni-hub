package Manage.HelperClasses;

import DataBaseConnection.BaseConnector;
import org.junit.Before;
import org.junit.Test;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.*;

public class FriendAdditionTest {

    BaseConnector bc;
    Connection connection;
    FriendAddition friendAddition;

    @Before
    public void setup() throws SQLException, ClassNotFoundException {
        bc = new BaseConnector();
        connection = bc.accessConnection();
        friendAddition = new FriendAddition(bc);
    }

    @Test
    public void testFriendAddition() throws SQLException {
        Statement statement = connection.createStatement();

        statement.execute("Insert into users (id,first_name,last_name,user_name,password,email)" +
                " values " + "(1000,'luka','macho','bigenti','123','fsjsffdsdfadsse')");
        statement.execute("Insert into users (id,first_name,last_name,user_name,password,email)" +
                " values " + "(2000,'blukab','macho','bigentia','123','fsjdfadsse')");
        statement.execute("Insert into usersInfo (user_id,user_name,user_last_name)" +
                " values " + "(1000,'luka','macho')");
        statement.execute("Insert into usersInfo (user_id,user_name,user_last_name)" +
                " values " + "(2000,'blukab','macho')");
        friendAddition.addFriend(1000, 2000);
        ResultSet resultSet = statement.executeQuery("Select * from friends");
        int num = 0;
        while (resultSet.next()) {
            num++;
        }
        assertEquals(1, num);
        friendAddition.removeFriend(2000, 1000);
        ResultSet resultSet2 = statement.executeQuery("Select * from friends");
        num = 0;
        while (resultSet2.next()) {
            num++;
        }
        assertEquals(0, num);
        statement.execute("delete from usersInfo where user_id = 1000;");
        statement.execute("delete from users where user_name = 'bigenti';");
        statement.execute("delete from usersInfo where user_id = 2000;");
        statement.execute("delete from users where user_name = 'bigentia'");
    }

    @Test
    public void testIsFriend() throws SQLException {
        Statement statement = connection.createStatement();

        statement.execute("Insert into users (id, first_name, last_name, user_name, password, email)" +
                " values " + "(108, 'qpqpqpqp', 'qpqpqpqp', 'awsawwwww', 'val1D', 'ppqpq41@freeuni.edu.ge')");
        statement.execute("Insert into users (id, first_name, last_name, user_name, password, email)" +
                " values " + "(109, 'apqpqpqp', 'qpqpqpqp', 'swawwwww', 'val1D', 'apqpq42@freeuni.edu.ge')");
        statement.execute("Insert into users (id, first_name, last_name, user_name, password, email)" +
                " values " + "(110, 'bpqpqpqp', 'qpqpqpqp', 'zwawwwww', 'val1D', 'bpqpq43@freeuni.edu.ge')");

        statement.execute("Insert into usersInfo (user_id, user_name, user_last_name)" +
                " values " + "(108, 'qpqpqpqp', 'qpqpqpqp')");
        statement.execute("Insert into usersInfo (user_id, user_name, user_last_name)" +
                " values " + "(109, 'apqpqpqp', 'qpqpqpqp')");
        statement.execute("Insert into usersInfo (user_id, user_name, user_last_name)" +
                " values " + "(110, 'bpqpqpqp', 'qpqpqpqp')");

        friendAddition.addFriend(108, 109);
        friendAddition.addFriend(110, 108);

        assertTrue(friendAddition.isFriend(108, 109));
        assertTrue(friendAddition.isFriend(108, 110));
        assertTrue(friendAddition.isFriend(109, 108));
        assertTrue(friendAddition.isFriend(110, 108));

        assertFalse(friendAddition.isFriend(109, 110));
        assertFalse(friendAddition.isFriend(110, 109));
        assertFalse(friendAddition.isFriend(108, 108));
        assertFalse(friendAddition.isFriend(109, 109));
        assertFalse(friendAddition.isFriend(1010, 110));

        friendAddition.removeFriend(108, 109);
        friendAddition.removeFriend(110, 108);

        statement.execute("delete from usersInfo where user_id = 108;");
        statement.execute("delete from users where id = 108;");
        statement.execute("delete from usersInfo where user_id = 109;");
        statement.execute("delete from users where id = 109");
        statement.execute("delete from usersInfo where user_id = 110;");
        statement.execute("delete from users where id = 110");
    }

    @Test
    public void testRejectFriend() throws SQLException {
        Statement statement = connection.createStatement();

        statement.execute("delete from friendRequests where requester_id = 2000;");
        statement.execute("delete from usersInfo where user_id = 1000;");
        statement.execute("delete from users where id = 1000;");
        statement.execute("delete from usersInfo where user_id = 2000;");
        statement.execute("delete from users where first_name = 'blukab'");
        statement.execute("Insert into users (id,first_name,last_name,user_name,password,email)" +
                " values " + "(1000,'luka','macho','mlfakfflsalme','123','MAE')");
        statement.execute("Insert into users (id,first_name,last_name,user_name,password,email)" +
                " values " + "(2000,'blukab','macho','mlfakflme','123','MARTVA')");
        statement.execute("Insert into usersInfo (user_id,user_name,user_last_name)" +
                " values " + "(2000,'blukab','macho')");
        statement.execute("Insert into usersInfo (user_id,user_name,user_last_name)" +
                " values " + "(1000,'luka','macho')");

        FriendRequesters fr = new FriendRequesters(bc);
        assertFalse(fr.hasSentFriendRequest(2000, 1000));
        assertTrue(fr.sendFriendRequest(2000, 1000));
        assertFalse(fr.hasSentFriendRequest(1000, 2000));
        assertTrue(fr.hasSentFriendRequest(2000, 1000));

        FriendAddition fa = new FriendAddition(bc);
        fa.rejectFriend(2000, 1000);
        assertFalse(fr.hasSentFriendRequest(2000, 1000));

        statement.execute("delete from friendRequests where requester_id = 2000;");
        statement.execute("delete from usersInfo where user_id = 1000;");
        statement.execute("delete from users where id = 1000;");
        statement.execute("delete from usersInfo where user_id = 2000;");
        statement.execute("delete from users where first_name = 'blukab'");
    }
}
