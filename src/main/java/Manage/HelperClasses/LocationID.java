package Manage.HelperClasses;

import DataBaseConnection.BaseConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static Manage.Configurations.SaveleConfiguration.LOCATIONS_TABLE;

public class LocationID {
    private static Connection con;


    public LocationID(BaseConnector bc) {
        con = bc.accessConnection();
    }

    public int getIdByLocation(String location) throws SQLException {
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from " + LOCATIONS_TABLE +
                " where name = '" + location + "';");

        while (rs.next()) {
            return rs.getInt("id");
        }
        return -1;
    }

    public Location getLocationById(int id) throws SQLException {
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from " + LOCATIONS_TABLE +
                " where id = " + id + ";");

        while (rs.next()) {
            return new Location(rs.getInt("id"), rs.getString("name"),
                    rs.getInt("numStudents"));
        }
        return null;
    }
}
