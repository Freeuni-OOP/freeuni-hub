package Manage;

import DataBaseConnection.BaseConnector;
import Manage.Configurations.SaveleConfiguration;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ManageTrade implements SaveleConfiguration {
    private static Connection con;

    public ManageTrade(BaseConnector bc) throws SQLException, ClassNotFoundException {
        con = bc.accessConnection();
    }

    public void addLocation(String location) throws SQLException { // to fix
        Statement stmt = con.createStatement();
        stmt.execute("insert into " + LOCATIONS_TABLE + "(name, numStudents) values ('"
                + location + "', " + 3 + ");");

    }
}
