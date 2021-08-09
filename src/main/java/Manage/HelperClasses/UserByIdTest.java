package Manage.HelperClasses;

import DataBaseConnection.BaseConnector;
import Manage.ManageUser;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.assertEquals;

public class UserByIdTest {

    BaseConnector bc;
    Connection connection;
    @Before
    public void setUp() throws SQLException, ClassNotFoundException {
        bc = new BaseConnector();
        connection = bc.accessConnection();
    }
    @Test
    public void getByIdTest() throws SQLException {
        Statement statement= connection.createStatement();
        statement.execute("Insert into users (id,first_name,last_name,user_name,password,email)" +
                " values "+ "(1000,'luka','macho','mlfakfflsalme','123','MAE')");
        statement.execute("Insert into users (id,first_name,last_name,user_name,password,email)" +
                " values "+ "(2000,'blukab','macho','mlfakflme','123','MARTVA')");
        statement.execute("Insert into usersInfo (user_id,user_name,user_last_name)" +
                " values "+ "(2000,'blukab','macho')");
        statement.execute("Insert into usersInfo (user_id,user_name,user_last_name)" +
                " values "+ "(1000,'luka','macho')");
        UserById user = new UserById(bc);
        User luka = user.getUser(1000);
        User blukab = user.getUser(2000);
        assertEquals(luka.getId(),1000);
        assertEquals(luka.getUserName(),"mlfakfflsalme");
        assertEquals(luka.getUserLastName(),"macho");
        assertEquals(blukab.getId(),2000);
        assertEquals(blukab.getUserName(),"mlfakflme");
        assertEquals(blukab.getUserLastName(),"macho");
        statement.execute("delete from usersInfo where user_id = 1000;");
        statement.execute("delete from users where first_name = 'luka';");
        statement.execute("delete from usersInfo where user_id = 2000;");
        statement.execute("delete from users where first_name = 'blukab'");
    }


    @Test
    public void testIdByMail() throws SQLException, ClassNotFoundException {
        UserById userById = new UserById(bc);
        ManageUser manageUser = new ManageUser(bc);
        manageUser.addUserWithId(159, "bartome", "diashi", "Barto555",
                "Barto123", "bdias12@freeuni.edu.ge");
        assertEquals(159, userById.getIdByMail("bdias12@freeuni.edu.ge"));
        manageUser.removeUser("bdias12@freeuni.edu.ge");
    }

    @Test
    public void testUserByUsername() throws SQLException {
        Statement statement= connection.createStatement();
        statement.execute("Insert into users (id,first_name,last_name,user_name,password,email)" +
                " values "+ "(1000,'luka','macho','luka','123','MAE')");
        statement.execute("Insert into users (id,first_name,last_name,user_name,password,email)" +
                " values "+ "(2000,'blukab','macho','luku','123','MARTVA')");
        statement.execute("Insert into usersInfo (user_id,user_name,user_last_name)" +
                " values "+ "(2000,'blukab','macho')");
        statement.execute("Insert into usersInfo (user_id,user_name,user_last_name)" +
                " values "+ "(1000,'luka','macho')");
        UserById user = new UserById(bc);
        assertEquals(1000,user.getIdByUsername("luka"));
        assertEquals(2000,user.getIdByUsername("luku"));
        statement.execute("delete from usersInfo where user_id = 1000;");
        statement.execute("delete from users where first_name = 'luka';");
        statement.execute("delete from usersInfo where user_id = 2000;");
        statement.execute("delete from users where first_name = 'blukab'");
    }
}

