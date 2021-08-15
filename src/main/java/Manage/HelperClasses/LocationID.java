package Manage.HelperClasses;

import DataBaseConnection.BaseConnector;

import java.sql.*;

import static Manage.Configurations.SaveleConfiguration.LOCATIONS_TABLE;

public class LocationID {
    private static Connection con;


    public LocationID(BaseConnector bc) {
        con = bc.accessConnection();
    }

    public int getIdByLocation(String location) throws SQLException {
        PreparedStatement preparedStatement = con.prepareStatement("select * from " + LOCATIONS_TABLE +
                " where name = ?;");
        preparedStatement.setString(1,location);
        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            return rs.getInt("id");
        }
        preparedStatement.close();
        return -1;
    }

    public Location getLocationById(int id) throws SQLException {
        PreparedStatement preparedStatement = con.prepareStatement("select * from " + LOCATIONS_TABLE +
                " where id = ?;");
        preparedStatement.setInt(1,id);

        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            return new Location(rs.getInt("id"), rs.getString("name"),
                    rs.getInt("numStudents"));
        }
        preparedStatement.close();
        return null;
    }
}
