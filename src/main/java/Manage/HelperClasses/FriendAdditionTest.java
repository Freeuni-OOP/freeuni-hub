package Manage.HelperClasses;

import DataBaseConnection.BaseConnector;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class FriendAdditionTest {
    BaseConnector bc;
    @Before
    public void setup() throws SQLException, ClassNotFoundException {
        bc = new BaseConnector();
    }

    @Test
    public void testFriendAddition() throws SQLException {
        FriendAddition friendAddition = new FriendAddition(bc);
        Connection connection = bc.accessConnection();
        Statement statement = connection.createStatement();
        statement.execute("Insert into users (id,first_name,last_name,user_name,password,email)" +
                " values "+ "(1000,'luka','macho','bigenti','123','fsjsffdsdfadsse')");
        statement.execute("Insert into users (id,first_name,last_name,user_name,password,email)" +
                " values "+ "(2000,'blukab','macho','bigentia','123','fsjdfadsse')");
        statement.execute("Insert into usersInfo (user_id,user_name,user_last_name)" +
                " values "+ "(1000,'luka','macho')");
        statement.execute("Insert into usersInfo (user_id,user_name,user_last_name)" +
                " values "+ "(2000,'blukab','macho')");
        friendAddition.addFriend(1000,2000);
        friendAddition.removeFriend(2000,1000);
        statement.execute("delete from usersInfo where user_id = 1000;");
        statement.execute("delete from users where user_name = 'bigenti';");
        statement.execute("delete from usersInfo where user_id = 2000;");
        statement.execute("delete from users where user_name = 'bigentia'");
    }
}
