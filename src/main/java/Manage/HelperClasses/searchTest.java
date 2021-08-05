package Manage.HelperClasses;


import DataBaseConnection.BaseConnector;
import Manage.HelperClasses.Search;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.assertEquals;

public  class searchTest {
    BaseConnector bc;
    Connection connection;
    @Before
    public void setUp() throws SQLException, ClassNotFoundException {
        bc = new BaseConnector();
        connection = bc.accessConnection();
    }
    @Test
    public void searchUsersTest() throws SQLException {

        Statement statement= connection.createStatement();
        statement.execute("Insert into users (id,first_name,last_name,user_name,password,email)" +
                " values "+ "(1,'luka','macho','mlfakfflsalme','123','fsjsffdsdfadsse')");
        statement.execute("Insert into users (id,first_name,last_name,user_name,password,email)" +
                " values "+ "(2,'blukab','macho','mlfakflme','123','fsjdfadsse')");
        statement.execute("Insert into usersInfo (user_id,user_name,user_last_name)" +
                " values "+ "(2,'blukab','macho')");
        statement.execute("Insert into usersInfo (user_id,user_name,user_last_name)" +
                " values "+ "(1,'luka','macho')");
        Search search = new Search(bc);
        assertEquals(1,search.searchUsers("luka").size());
        assertEquals(1,search.searchUsers("blukab").size());
        statement.execute("delete from usersInfo where user_id = 1;");
        statement.execute("delete from users where first_name = 'luka';");
        statement.execute("delete from usersInfo where user_id = 2;");
        statement.execute("delete from users where first_name = 'blukab'");
    }
    @Test
    public void similarUsersSearchTest() throws SQLException {
        Statement statement= connection.createStatement();
        statement.execute("Insert into users (id,first_name,last_name,user_name,password,email)" +
                " values "+ "(1,'luka','macho','mlfakfflsalme','123','fsjsffdsdfadsse')");
        statement.execute("Insert into users (id,first_name,last_name,user_name,password,email)" +
                " values "+ "(2,'blukab','macho','mlfakflme','123','fsjdfadsse')");
        statement.execute("Insert into usersInfo (user_id,user_name,user_last_name)" +
                " values "+ "(2,'blukab','macho')");
        statement.execute("Insert into usersInfo (user_id,user_name,user_last_name)" +
                " values "+ "(1,'luka','macho')");
        Search search = new Search(bc);
        assertEquals(2,search.searchSimilarUsers("luka").size());
        statement.execute("delete from usersInfo where user_id = 1;");
        statement.execute("delete from users where first_name = 'luka';");
        statement.execute("delete from usersInfo where user_id = 2;");
        statement.execute("delete from users where first_name = 'blukab'");
    }
}

