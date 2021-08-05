package Manage;

import DataBaseConnection.BaseConnector;
import Manage.Configurations.SaveleConfiguration;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ManageTrade implements SaveleConfiguration {
    private static Connection con;

    public ManageTrade(BaseConnector bc) throws SQLException, ClassNotFoundException {
        con = bc.accessConnection();
    }

    // methods checks whether location contains or not
    public boolean isLocation(String location) throws SQLException { // to fix
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from " + LOCATIONS_TABLE + ";");

        while (rs.next()) {
            if (rs.getString("name").equals(location)) return true;
        }
        return false;
    }

    public void addLocation(String location) throws SQLException { // to fix
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from " + LOCATIONS_TABLE + ";");

        int count = 0;
        while (rs.next()) {
            if (rs.getString("name").equals(location)) {
                count = Integer.parseInt(rs.getString("numStudents"));
            }
        }
        count++;
        stmt.execute("insert into " + LOCATIONS_TABLE + "(name, numStudents) values ('"
                + location + "', '" + count + "');");
    }


    public void removeLocation(String location) throws SQLException { // to fix
        Statement stmt = con.createStatement();

        boolean exists = isLocation(location); // check whether location exists or not
        if (exists)
            stmt.execute("delete from " + LOCATIONS_TABLE + " where name = '" + location + "';");
        else throw new Error("location with given name isn't found");
    }

    // to fix
    public void addStudentToLocation(String student, String location) throws SQLException {
        Statement stmt = con.createStatement();
        stmt.execute("insert into " + MEMBERS_TABLE + " (location_id, user_id) values ('" +
               location + "' , ''" + student + "');'");
    }

    // to fix
    public void removeStudentFromLocation(String student, String location) throws SQLException {
        Statement stmt = con.createStatement();
        stmt.execute("insert into " + MEMBERS_TABLE + " (location_id, user_id) values ('" +
                location + "' , ''" + student + "');'");
    }
}
