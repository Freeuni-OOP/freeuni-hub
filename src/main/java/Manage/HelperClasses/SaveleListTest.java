package Manage.HelperClasses;

import DataBaseConnection.BaseConnector;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.assertEquals;

public class SaveleListTest {
    BaseConnector bc;
    Connection connection;
    Statement statement;

    @Before
    public void setup() throws SQLException, ClassNotFoundException {
        bc = new BaseConnector();
        connection = bc.accessConnection();
        statement = connection.createStatement();
    }

    @Test
    public void testRequestList() throws SQLException, ClassNotFoundException {
        LocationAddition locationAddition = new LocationAddition(bc);
        SaveleList saveleList = new SaveleList(bc);

        saveleList.removeRequest(100, 101);
        locationAddition.removeFromLocation(100);
        locationAddition.removeFromLocation(101);
        locationAddition.removeLocation(100);
        locationAddition.removeLocation(101);

        locationAddition.removeFromLocation(100);
        locationAddition.removeFromLocation(101);
        locationAddition.removeLocation(100);
        locationAddition.removeLocation(101);
        statement.execute("delete from users where id = 100;");
        statement.execute("delete from users where id = 101;");
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

        saveleList.addRequest(100, 101);
        ResultSet resultSet = statement.executeQuery("select * from changeLocationRequest");
        while (resultSet.next()) {
            assertEquals(100, resultSet.getInt(1));
            assertEquals(100, resultSet.getInt(2));
            assertEquals(101, resultSet.getInt(3));
            assertEquals(101, resultSet.getInt(4));
        }
        saveleList.removeRequest(100, 101);
        locationAddition.removeFromLocation(100);
        locationAddition.removeFromLocation(101);
        locationAddition.removeLocation(100);
        locationAddition.removeLocation(101);
        statement.execute("delete from users where id = 100;");
        statement.execute("delete from users where id = 101;");
    }

    @Test
    public void testList() throws SQLException, ClassNotFoundException {
        LocationAddition locationAddition = new LocationAddition(bc);
        SaveleList saveleList = new SaveleList(bc);

        saveleList.removeRequest(100, 101);
        locationAddition.removeFromLocation(100);
        locationAddition.removeFromLocation(101);
        locationAddition.removeLocation(100);
        locationAddition.removeLocation(101);

        locationAddition.removeFromLocation(100);
        locationAddition.removeFromLocation(101);
        locationAddition.removeLocation(100);
        locationAddition.removeLocation(101);
        statement.execute("delete from users where id = 100;");
        statement.execute("delete from users where id = 101;");
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

        saveleList.addRequest(100, 101);
        assertEquals(0, saveleList.getSaveleRequestersList(100).size());
        assertEquals(1, saveleList.getSaveleRequestersList(101).size());
        assertEquals(100, saveleList.getSaveleRequestersList(101).get(0).getId());
        assertEquals(0, saveleList.getSaveleRequestersList("naf_fly").size());
        assertEquals(1, saveleList.getSaveleRequestersList("friberg").size());
        assertEquals(100, saveleList.getSaveleRequestersList("friberg").get(0).getId());
        saveleList.removeRequest(100, 101);
        locationAddition.removeFromLocation(100);
        locationAddition.removeFromLocation(101);
        locationAddition.removeLocation(100);
        locationAddition.removeLocation(101);
        statement.execute("delete from users where id = 100;");
        statement.execute("delete from users where id = 101;");
    }
}
