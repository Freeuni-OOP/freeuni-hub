package Manage.HelperClasses;

import DataBaseConnection.BaseConnector;

import java.sql.Connection;

public class LocationID {
    private static Connection con;

    public LocationID(BaseConnector bc) {
        con = bc.accessConnection();
    }

    public int getIdByLocation() {
        return -1;
    }

}
