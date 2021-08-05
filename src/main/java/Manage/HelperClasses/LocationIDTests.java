package Manage.HelperClasses;


import DataBaseConnection.BaseConnector;
import Manage.Configurations.SaveleConfiguration;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.assertEquals;

public class LocationIDTests implements SaveleConfiguration{
    private LocationID locationID;
    private Statement stmt;

    @Before
    public void setUp() throws SQLException, ClassNotFoundException {
        BaseConnector bc = new BaseConnector();
        Connection con = bc.accessConnection();
        stmt = con.createStatement();
        locationID = new LocationID(bc);
    }

    @Test
    public void testIdByLocation() throws SQLException {
        stmt.execute("insert into " + LOCATIONS_TABLE + " values ('20', 'gori', '11');");
        assertEquals(20, locationID.getIdByLocation("gori"));
        stmt.execute("delete from " + LOCATIONS_TABLE + " where name = 'gori';");
    }

    @Test
    public void testLocationById() throws SQLException {
        stmt.execute("insert into " + LOCATIONS_TABLE + " values ('20', 'gori', '11');");
        assertEquals("gori", locationID.getLocationById(20).getLocName());
        stmt.execute("delete from " + LOCATIONS_TABLE + " where name = 'gori';");
    }
}
