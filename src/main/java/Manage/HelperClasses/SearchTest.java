package Manage.HelperClasses;


import DataBaseConnection.BaseConnector;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.assertEquals;

public class SearchTest {
    BaseConnector bc;
    Connection connection;

    @Before
    public void setUp() throws SQLException, ClassNotFoundException {
        bc = new BaseConnector();
        connection = bc.accessConnection();
    }

    @Test
    public void searchUsersTest() throws SQLException {

        Statement statement = connection.createStatement();
        statement.execute("Insert into users (id,first_name,last_name,user_name,password,email)" +
                " values " + "(1000,'luka','macho','bigenti','123','fsjsffdsdfadsse')");
        statement.execute("Insert into users (id,first_name,last_name,user_name,password,email)" +
                " values " + "(2000,'blukab','macho','bigentia','123','fsjdfadsse')");
        statement.execute("Insert into usersInfo (user_id,user_name,user_last_name)" +
                " values " + "(1000,'luka','macho')");
        statement.execute("Insert into usersInfo (user_id,user_name,user_last_name)" +
                " values " + "(2000,'blukab','macho')");
        Search search = new Search(bc);
        assertEquals(1, search.searchUsers("bigenti").size());
        assertEquals(1, search.searchUsers("bigentia").size());
        statement.execute("delete from usersInfo where user_id = 1000;");
        statement.execute("delete from users where user_name = 'bigenti';");
        statement.execute("delete from usersInfo where user_id = 2000;");
        statement.execute("delete from users where user_name = 'bigentia'");
    }

    @Test
    public void similarUsersSearchTest() throws SQLException, ClassNotFoundException {
        Statement statement = connection.createStatement();
        statement.execute("Insert into users (id,first_name,last_name,user_name,password,email)" +
                " values " + "(1000,'luka','macho','bigenti','123','fsjsffdsdfadsse')");
        statement.execute("Insert into users (id,first_name,last_name,user_name,password,email)" +
                " values " + "(2000,'blukab','macho','bigentia','123','fsjdfadsse')");
        statement.execute("Insert into usersInfo (user_id,user_name,user_last_name)" +
                " values " + "(1000,'luka','macho')");
        statement.execute("Insert into usersInfo (user_id,user_name,user_last_name)" +
                " values " + "(2000,'blukab','macho')");
        Search search = new Search(bc);
        assertEquals(1, search.searchSimilarUsers("bigenti", 1000).size());
        assertEquals(0, search.searchSimilarUsers("bigentia", 2000).size());
        statement.execute("delete from usersInfo where user_id = 1000;");
        statement.execute("delete from users where user_name = 'bigenti';");
        statement.execute("delete from usersInfo where user_id = 2000;");
        statement.execute("delete from users where user_name = 'bigentia'");
    }
}

