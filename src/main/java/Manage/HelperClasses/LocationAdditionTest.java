package Manage.HelperClasses;

import DataBaseConnection.BaseConnector;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.*;

public class LocationAdditionTest {
    LocationAddition locationAddition;
    Statement statement;
    BaseConnector bc;

    @Before
    public void setup() throws SQLException, ClassNotFoundException {
        bc = new BaseConnector();
        Connection connection = bc.accessConnection();
        statement = connection.createStatement();
        locationAddition = new LocationAddition(bc);
    }

    @Test
    public void testLocationId() throws SQLException {
        locationAddition.removeFromLocation(100);
        locationAddition.removeLocation(1);
        statement.execute("delete from users where id = " + 100);
        statement.execute("Insert into users (id, first_name, last_name, user_name, password, email)" +
                " values " + "(100, 'keith', 'markovic', 'naf_fly', 'val1D', 'kmark15@freeuni.edu.ge')");
        locationAddition.addLocation(100, "sacdeli");
        locationAddition.addIdInLocation(100, 100);
        assertEquals(100, locationAddition.locationId(100));
        locationAddition.removeFromLocation(100);
        locationAddition.removeLocation(100);
        statement.execute("delete from users where id = " + 100);
    }

    @Test
    public void testAddition() throws SQLException {
        locationAddition.removeFromLocation(100);
        locationAddition.removeLocation(1);
        statement.execute("delete from locationMembers");
        statement.execute("delete from users where id = " + 100);
        statement.execute("Insert into users (id, first_name, last_name, user_name, password, email)" +
                " values " + "(100, 'keith', 'markovic', 'naf_fly', 'val1D', 'kmark15@freeuni.edu.ge')");
        locationAddition.addLocation(100, "sacdeli");
        locationAddition.addIdInLocation(100, 100);
        ResultSet resultSet = statement.executeQuery("select * from locationMembers");
        int num = 0;
        while (resultSet.next()) {
            num++;
        }
        assertEquals(1, num);

        locationAddition.removeFromLocation(100);
        locationAddition.removeLocation(100);
        resultSet = statement.executeQuery("select * from locationMembers");
        num = 0;
        while (resultSet.next()) {
            num++;
        }
        assertEquals(0, num);
        statement.execute("delete from users where id = " + 100);
    }

    @Test
    public void testAlreadyRegistered() throws SQLException {
        statement.execute("delete from users where id = " + 100);
        statement.execute("Insert into users (id, first_name, last_name, user_name, password, email)" +
                " values " + "(100, 'keith', 'markovic', 'naf_fly', 'val1D', 'kmark15@freeuni.edu.ge')");
        boolean already = locationAddition.alreadyRegistered(100);
        assertFalse(already);
        locationAddition.addLocation(100, "sacdeli");
        locationAddition.addIdInLocation(100, 100);
        already = locationAddition.alreadyRegistered(100);
        assertTrue(already);
        locationAddition.removeFromLocation(100);
        locationAddition.removeLocation(100);
        already = locationAddition.alreadyRegistered(100);
        assertFalse(already);
        statement.execute("delete from users where id = " + 100);
    }

    @Test
    public void testChangeLocations() throws SQLException {
        locationAddition.removeFromLocation(100);
        locationAddition.removeFromLocation(101);
        locationAddition.removeLocation(100);
        locationAddition.removeLocation(101);
        statement.execute("delete from users where id = 100;");
        statement.execute("delete from users where id = 101;");
        statement.execute("Insert into users (id, first_name, last_name, user_name, password, email)" +
                " values " + "(100, 'keith', 'markovic', 'naf_fly', 'val1D', 'kmark15@freeuni.edu.ge')");
        statement.execute("Insert into users (id, first_name, last_name, user_name, password, email)" +
                " values " + "(101, 'adam', 'friberg', 'friberg', 'val1D', 'afrib15@freeuni.edu.ge')");
        locationAddition.addLocation(100, "sacdeli");
        locationAddition.addIdInLocation(100, 100);
        locationAddition.addLocation(101, "meore");
        locationAddition.addIdInLocation(101, 101);
        ResultSet resultSet = statement.executeQuery("Select * from locationMembers where location_id=100");
        while (resultSet.next()) {
            assertEquals(100, resultSet.getInt(2));
            assertEquals(100, resultSet.getInt(1));
        }
        resultSet = statement.executeQuery("Select * from locationMembers where location_id=101");
        while (resultSet.next()) {
            assertEquals(101, resultSet.getInt(2));
            assertEquals(101, resultSet.getInt(1));
        }
        locationAddition.changeLocations(101, 100);
        resultSet = statement.executeQuery("Select * from locationMembers where location_id=100");
        while (resultSet.next()) {
            assertEquals(101, resultSet.getInt(2));
            assertEquals(100, resultSet.getInt(1));
        }
        resultSet = statement.executeQuery("Select * from locationMembers where location_id=101");
        while (resultSet.next()) {
            assertEquals(100, resultSet.getInt(2));
            assertEquals(101, resultSet.getInt(1));
        }
        locationAddition.removeFromLocation(100);
        locationAddition.removeFromLocation(101);
        locationAddition.removeLocation(100);
        locationAddition.removeLocation(101);
        statement.execute("delete from users where id = 100;");
        statement.execute("delete from users where id = 101;");
    }

    @Test
    public void testRemoveSimilars() throws SQLException {
        locationAddition.removeFromLocation(100);
        locationAddition.removeFromLocation(101);
        locationAddition.removeLocation(100);
        locationAddition.removeLocation(101);
        statement.execute("delete from users where id = 100;");
        statement.execute("delete from users where id = 101;");
        statement.execute("Insert into users (id, first_name, last_name, user_name, password, email)" +
                " values " + "(100, 'keith', 'markovic', 'naf_fly', 'val1D', 'kmark15@freeuni.edu.ge')");
        statement.execute("Insert into users (id, first_name, last_name, user_name, password, email)" +
                " values " + "(101, 'adam', 'friberg', 'friberg', 'val1D', 'afrib15@freeuni.edu.ge')");
        locationAddition.addLocation(100, "sacdeli");
        locationAddition.addIdInLocation(100, 100);
        locationAddition.addLocation(101, "meore");
        locationAddition.addIdInLocation(101, 101);
        statement.execute("Insert into changeLocationRequest values(100,100,101,101,false)");
        ResultSet resultSet = statement.executeQuery("Select * from changeLocationRequest");
        int num = 0;
        while (resultSet.next()) {
            num++;
        }
        assertEquals(1, num);
        locationAddition.removeSimilars(100);
        num = 0;
        while (resultSet.next()) {
            num++;
        }
        assertEquals(0, num);
        locationAddition.removeFromLocation(100);
        locationAddition.removeFromLocation(101);
        locationAddition.removeLocation(100);
        locationAddition.removeLocation(101);
        statement.execute("delete from users where id = 100;");
        statement.execute("delete from users where id = 101;");
    }
}
