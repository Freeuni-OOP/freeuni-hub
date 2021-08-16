package Manage.HelperClasses;

import DataBaseConnection.BaseConnector;

import java.sql.*;
import java.util.ArrayList;

public class FriendRequesters {
    private static Connection connection;
    BaseConnector bc;

    public FriendRequesters(BaseConnector bc) {
        connection = bc.accessConnection();
        this.bc = bc;
    }

    public ArrayList<User> getFriendRequesters(int user_id) throws SQLException {
        ArrayList<User> result = new ArrayList<>();
        Statement statement = connection.createStatement();
        PreparedStatement preparedStatement = connection.prepareStatement("Select requester_id from friendRequests " +
                "where receiver_id = ?;");
        preparedStatement.setInt(1, user_id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            UserById userById = new UserById(bc);
            result.add(userById.getUser(resultSet.getInt(1)));
        }
        preparedStatement.close();
        return result;
    }

    // returns false if request is already sent, true otherwise
    public boolean sendFriendRequest(int requester_id, int receiver_id) throws SQLException {
        if (hasSentFriendRequest(requester_id, receiver_id))
            return false;
        PreparedStatement preparedStatement = connection.prepareStatement("Insert into friendRequests (requester_id, receiver_id, accepted) " +
                " values (?,?,?);");
        preparedStatement.setInt(1, requester_id);
        preparedStatement.setInt(2, receiver_id);
        preparedStatement.setBoolean(3, false);
        preparedStatement.execute();
        preparedStatement.close();
        return true;
    }

    public boolean hasSentFriendRequest(int requester_id, int receiver_id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("Select * from friendRequests where requester_id = ? and receiver_id = ?;");
        preparedStatement.setInt(1, requester_id);
        preparedStatement.setInt(2, receiver_id);
        ResultSet rs = preparedStatement.executeQuery();
        return rs.next();
    }

    public void removeRequest(int requester_id, int receiver_id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("Delete from friendRequests where requester_id = ? " +
                " and receiver_id = ?;");
        preparedStatement.setInt(1, requester_id);
        preparedStatement.setInt(2, receiver_id);
        preparedStatement.execute();
        preparedStatement.close();
    }

}