package Manage;

import DataBaseConnection.BaseConnector;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ManageTrade {
    private static Connection con;

    public ManageTrade(BaseConnector bc) throws SQLException, ClassNotFoundException {
        con = bc.accessConnection();
    }

    public void addLocation(String location) throws SQLException {
        Statement stmt = con.createStatement();

    }
}
