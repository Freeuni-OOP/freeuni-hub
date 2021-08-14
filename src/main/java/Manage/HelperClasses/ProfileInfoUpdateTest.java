package Manage.HelperClasses;

import DataBaseConnection.BaseConnector;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.assertEquals;

public class ProfileInfoUpdateTest {

    BaseConnector bc;
    Connection connection;

    @Before
    public void setUp() throws SQLException, ClassNotFoundException {
        bc = new BaseConnector();
        connection = bc.accessConnection();
    }

    @Test
    public void testInfoUpdate() throws SQLException {
        ProfileInfoUpdate update = new ProfileInfoUpdate(bc);

        Statement statement = connection.createStatement();

        statement.execute("Insert into users (id, first_name, last_name, user_name, password, email)" +
                " values " + "(100, 'boris', 'ivanishvili', 'bidz1na', 'Bidzina41', 'bivan69@freeuni.edu.ge')");
        statement.execute("Insert into usersInfo (user_id, user_name, user_last_name, sqesi, course)" +
                " values " + "(100, 'boris', 'ivanishvili', 'mamrobiti', 'biznesi')");

        update.updateInfo(100, "bidzina", "ivanishvili", "mamrobiti", "politika");

        ResultSet rs = statement.executeQuery("Select * from usersInfo where user_id = 100");

        while (rs.next()) {
            assertEquals("bidzina", rs.getString("user_name"));
            assertEquals("ivanishvili", rs.getString("user_last_name"));
            assertEquals("mamrobiti", rs.getString("sqesi"));
            assertEquals("politika", rs.getString("course"));
        }


        statement.execute("Insert into users (id, first_name, last_name, user_name, password, email)" +
                " values " + "(101, 'beso', 'uknown', 'kesssso', 'Kes0', 'kunkn69@freeuni.edu.ge')");
        statement.execute("Insert into usersInfo (user_id, user_name, user_last_name, sqesi, course)" +
                " values " + "(101, 'beso', 'unknown', 'mamrobiti', 'unknown')");

        update.updateInfo(101, "keso", "unknown", "mdedrobiti", "modeli");

        rs = statement.executeQuery("Select * from usersInfo where user_id = 101");

        while (rs.next()) {
            assertEquals("keso", rs.getString("user_name"));
            assertEquals("unknown", rs.getString("user_last_name"));
            assertEquals("mdedrobiti", rs.getString("sqesi"));
            assertEquals("modeli", rs.getString("course"));
        }


        statement.execute("delete from usersInfo where user_id = 100;");
        statement.execute("delete from users where id = 100;");
        statement.execute("delete from usersInfo where user_id = 101;");
        statement.execute("delete from users where id = 101;");
    }
}
