package Manage.HelperClasses;

import DataBaseConnection.BaseConnector;

import java.sql.*;

public class LocationAddition {
    BaseConnector bc;
    Connection connection;


    public LocationAddition(BaseConnector bc) throws SQLException, ClassNotFoundException {
        this.bc = bc;
        connection = bc.accessConnection();
    }

    public int locationId(int user_id) throws SQLException {
        Statement statement = connection.createStatement();
        PreparedStatement preparedStatement = connection.prepareStatement("Select location_id from locationMembers where user_id = ?;");
        preparedStatement.setInt(1, user_id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            return resultSet.getInt(1);
        }
        return 0;
    }

    public void addLocation(int location_id, String username) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("Insert into locations values(?,?,0);");
        preparedStatement.setInt(1, location_id);
        preparedStatement.setString(2, username);
        preparedStatement.execute();
        preparedStatement.close();
    }

    public void removeLocation(int location_id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("Delete from locations where id = ?;");
        preparedStatement.setInt(1, location_id);
        preparedStatement.execute();
        preparedStatement.close();
    }

    public void addIdInLocation(int user_id, int location_id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("Insert into locationMembers values(?,?);");
        preparedStatement.setInt(1, location_id);
        preparedStatement.setInt(2, user_id);
        preparedStatement.execute();
        preparedStatement = connection.prepareStatement("Update locations set numStudents = numStudents + 1 where id = ?;");
        preparedStatement.setInt(1, location_id);
        preparedStatement.execute();
        preparedStatement.close();

    }

    public void removeFromLocation(int user_id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("Delete from locationMembers where user_id =?;");
        preparedStatement.setInt(1, user_id);
        preparedStatement.execute();
        preparedStatement.close();
    }

    public void updateLocationId(int user_id, int location_id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("Update locationMembers set location_id = ? where user_id = ?;");
        preparedStatement.setInt(1, location_id);
        preparedStatement.setInt(2, user_id);
        preparedStatement.execute();
        preparedStatement.close();
    }

    public boolean alreadyRegistered(int user_id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("Select * from locationMembers where user_id = ?;");
        preparedStatement.setInt(1, user_id);
        ResultSet resultSet = preparedStatement.executeQuery();
        int num = 0;
        while (resultSet.next()) {
            num++;
        }
        preparedStatement.close();
        return num != 0;
    }

    public void changeLocations(int requester_id, int receiver_id) throws SQLException {
        Statement statement = connection.createStatement();
        int requesterLocationID = 0;
        int receiverLocationId = 0;
        PreparedStatement preparedStatement = connection.prepareStatement("Select location_id from locationMembers where user_id = ?;");
        preparedStatement.setInt(1, requester_id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            requesterLocationID = resultSet.getInt(1);
        }
        preparedStatement.setInt(1, receiver_id);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            receiverLocationId = rs.getInt(1);
        }
        updateLocationId(receiver_id, requesterLocationID);
        updateLocationId(requester_id, receiverLocationId);
    }

    public void removeSimilars(int requester_id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("Delete from changeLocationRequest where requester_id = ?;");
        preparedStatement.setInt(1, requester_id);
        preparedStatement.execute();
        preparedStatement = connection.prepareStatement("Delete from changeLocationRequest where receiver_id = ?;");
        preparedStatement.setInt(1, requester_id);
        preparedStatement.execute();
    }
}
