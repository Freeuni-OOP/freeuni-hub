package Manage;

import DataBaseConnection.BaseConnector;
import Manage.Configurations.SaveleConfiguration;
import Manage.Configurations.UserConfiguration;
import Manage.HelperClasses.LocationID;
import Manage.HelperClasses.UserById;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class ManageTrade implements SaveleConfiguration, UserConfiguration {
    private static Connection con;
    private final BaseConnector bc;

    public ManageTrade(BaseConnector bc) {
        this.bc = bc;
        con = bc.accessConnection();
    }

    // methods checks whether exists or not
    public boolean isLocation(String location) throws SQLException { // to fix
        Statement stmt = con.createStatement();
        PreparedStatement preparedStatement = con.prepareStatement("select * from " + LOCATIONS_TABLE + ";");
        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            if (rs.getString("name").equals(location)) return true;
        }
        return false;
    }

    public void addLocation(String location) throws SQLException { // to fix
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from " + LOCATIONS_TABLE + ";");

        int count = 0; // initially no students

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


    // 2 helper methods for adding and removing student----------------------------------------------------------------
    private void increaseNumStudents(String location) throws SQLException { // increases number of students in 'locations' table
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from " + LOCATIONS_TABLE + ";");


        while (rs.next()) {
            if (rs.getString("name").equals(location)) {
                int id = rs.getInt("id");
                int num = rs.getInt("numStudents") + 1;
                stmt.executeUpdate("update " + LOCATIONS_TABLE +
                        " set numStudents = '" + num + "' " +
                        " where id = " + id + ";");
                break;
            }
        }

    }


    private void decreaseNumStudents(String location) throws SQLException { // increases number of students in 'locations' table
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from " + LOCATIONS_TABLE + ";");


        while (rs.next()) {
            if (rs.getString("name").equals(location)) {
                int id = rs.getInt("id");
                int num = rs.getInt("numStudents") - 1;
                stmt.executeUpdate("update " + LOCATIONS_TABLE +
                        " set numStudents = '" + num + "' " +
                        " where id = " + id + ";");
                break;
            }
        }

    }


    //--------------------------------------------------------------------------------------------------------

    public void addStudentToLocation(String mail, String location) throws SQLException {
        Statement stmt = con.createStatement();
        // helper classes
        UserById ubi = new UserById(bc);
        LocationID locationID = new LocationID(bc);

        int user_id = ubi.getIdByMail(mail);
        int location_id = locationID.getIdByLocation(location);
        PreparedStatement preparedStatement = con.prepareStatement("insert into " + MEMBERS_TABLE + " (location_id, user_id) values (?,?);");
        preparedStatement.setInt(1, location_id);
        preparedStatement.setInt(2, user_id);
        preparedStatement.execute();
        preparedStatement.close();
        increaseNumStudents(location);
    }


    public void removeStudentFromLocation(String mail, String location) throws SQLException {
        Statement stmt = con.createStatement();
        UserById ubi = new UserById(bc);
        int user_id = ubi.getIdByMail(mail);
        PreparedStatement preparedStatement = con.prepareStatement("delete from " + MEMBERS_TABLE + " where user_id = ?;");
        preparedStatement.setInt(1, user_id);
        preparedStatement.execute();
        preparedStatement.close();
        decreaseNumStudents(location);
    }

    public int getNumStudents(String location) throws SQLException { // gets number of students for current location
        Statement stmt = con.createStatement();
        PreparedStatement preparedStatement = con.prepareStatement("select * from " + LOCATIONS_TABLE + " where name = ?;");
        preparedStatement.setString(1, location);
        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next())
            return rs.getInt("numStudents");
        preparedStatement.close();
        return -1;
    }


    // returns all users in given location
    public ArrayList<String> getLocationUserNames(String location) throws SQLException {
        ArrayList<String> res = new ArrayList<>(); // final result
        Statement stmt = con.createStatement();
        LocationID locationID = new LocationID(bc);
        int loc_id = locationID.getIdByLocation(location);

        Set<Integer> users = new HashSet<>();
        PreparedStatement preparedStatement = con.prepareStatement("select * from " + MEMBERS_TABLE +
                " where location_id = ?;");
        preparedStatement.setInt(1, loc_id);
        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) users.add(rs.getInt("user_id"));

        ResultSet resultSet = stmt.executeQuery("select * from " + USERS_TABLE + ";");
        while (resultSet.next()) {
            if (users.contains(resultSet.getInt("id")))
                res.add(resultSet.getString("user_name"));
        }

        return res;
    }
}
