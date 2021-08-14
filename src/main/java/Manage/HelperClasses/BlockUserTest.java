package Manage.HelperClasses;

import DataBaseConnection.BaseConnector;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.assertEquals;

public class BlockUserTest {
    BaseConnector bc;

    @Before
    public void setup() throws SQLException, ClassNotFoundException {
        bc = new BaseConnector();
    }

    @Test
    public void testBlockUser() throws SQLException, ClassNotFoundException {
        BlockUser blockUser = new BlockUser(bc);
        Connection connection = bc.accessConnection();
        Statement statement = connection.createStatement();
        statement.execute("Insert into users (id,first_name,last_name,user_name,password,email)" +
                " values " + "(1000,'luka','macho','bigenti','123','fsjsffdsdfadsse')");
        statement.execute("Insert into users (id,first_name,last_name,user_name,password,email)" +
                " values " + "(2000,'blukab','macho','bigentia','123','fsjdfadsse')");
        statement.execute("Insert into usersInfo (user_id,user_name,user_last_name)" +
                " values " + "(1000,'luka','macho')");
        statement.execute("Insert into usersInfo (user_id,user_name,user_last_name)" +
                " values " + "(2000,'blukab','macho')");

        blockUser.blockById(1000, 2000);
        ResultSet resultSet = statement.executeQuery("Select * from blockedUsers");
        int num = 0;
        while (resultSet.next()) {
            num++;
        }
        assertEquals(1, num);
        blockUser.unblockById(1000, 2000);
        ResultSet second = statement.executeQuery("Select * from blockedUsers");
        num = 0;
        while (second.next()) {
            num++;
        }
        assertEquals(0, num);
        statement.execute("delete from usersInfo where user_id = 1000;");
        statement.execute("delete from users where user_name = 'bigenti';");
        statement.execute("delete from usersInfo where user_id = 2000;");
        statement.execute("delete from users where user_name = 'bigentia'");
    }

    @Test
    public void testGetBlockedList() throws SQLException {

        BlockUser blockUser = new BlockUser(bc);
        Connection connection = bc.accessConnection();
        Statement statement = connection.createStatement();

        statement.execute("Insert into users (id, first_name, last_name, user_name, password, email)" +
                " values " + "(100, 'keith', 'markovic', 'naf_fly', 'val1D', 'kmark15@freeuni.edu.ge')");
        statement.execute("Insert into users (id, first_name, last_name, user_name, password, email)" +
                " values " + "(101, 'adam', 'friberg', 'friberg', 'val1D', 'afrib15@freeuni.edu.ge')");
        statement.execute("Insert into users (id, first_name, last_name, user_name, password, email)" +
                " values " + "(102, 'patrick', 'lindberg', 'forest', 'val1D', 'plind15@freeuni.edu.ge')");
        statement.execute("Insert into users (id, first_name, last_name, user_name, password, email)" +
                " values " + "(103, 'niko', 'kovac', 'nikokovac', 'val1D', 'nkova15@freeuni.edu.ge')");

        assertEquals(0, blockUser.getBlockedList(100).size());
        blockUser.blockById(100, 101);
        blockUser.blockById(100, 102);
        blockUser.blockById(100, 103);

        assertEquals(3, blockUser.getBlockedList(100).size());
        assertEquals(0, blockUser.getBlockedList(101).size());
        assertEquals(0, blockUser.getBlockedList(102).size());
        assertEquals(0, blockUser.getBlockedList(103).size());

        blockUser.blockById(101, 102);
        blockUser.blockById(102, 103);
        assertEquals(3, blockUser.getBlockedList(100).size());
        assertEquals(1, blockUser.getBlockedList(101).size());
        assertEquals(1, blockUser.getBlockedList(102).size());
        assertEquals(0, blockUser.getBlockedList(103).size());

        blockUser.unblockById(100, 101);
        blockUser.unblockById(100, 102);
        blockUser.unblockById(100, 103);
        blockUser.unblockById(101, 102);
        blockUser.unblockById(102, 103);

        statement.execute("delete from users where id = 100;");
        statement.execute("delete from users where id = 101;");
        statement.execute("delete from users where id = 102;");
        statement.execute("delete from users where id = 103;");
    }

    @Test
    public void testIsBlocked() throws SQLException {
        BlockUser blockUser = new BlockUser(bc);
        Connection connection = bc.accessConnection();
        Statement statement = connection.createStatement();
        statement.execute("Insert into users (id,first_name,last_name,user_name,password,email)" +
                " values " + "(1000,'luka','macho','bigenti','123','fsjsffdsdfadsse')");
        statement.execute("Insert into users (id,first_name,last_name,user_name,password,email)" +
                " values " + "(2000,'blukab','macho','bigentia','123','fsjdfadsse')");
        statement.execute("Insert into usersInfo (user_id,user_name,user_last_name)" +
                " values " + "(1000,'luka','macho')");
        statement.execute("Insert into usersInfo (user_id,user_name,user_last_name)" +
                " values " + "(2000,'blukab','macho')");
        blockUser.blockById(1000, 2000);
        assertEquals(true, blockUser.isBlocked(1000, 2000));
        blockUser.unblockById(1000, 2000);
        assertEquals(false, blockUser.isBlocked(1000, 2000));
        statement.execute("delete from usersInfo where user_id = 1000;");
        statement.execute("delete from users where user_name = 'bigenti';");
        statement.execute("delete from usersInfo where user_id = 2000;");
        statement.execute("delete from users where user_name = 'bigentia'");
    }
}