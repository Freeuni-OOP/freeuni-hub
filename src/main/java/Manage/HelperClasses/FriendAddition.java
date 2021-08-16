package Manage.HelperClasses;

import DataBaseConnection.BaseConnector;

import java.sql.*;

public class FriendAddition {

    BaseConnector bc;
    Connection connection;

    public FriendAddition(BaseConnector bc) {
        this.bc = bc;
        connection = bc.accessConnection();
    }

    public void addFriend(int requester_id, int receiver_id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("Insert into friends (requester_id,receiver_id) " +
                "values (?,?);");
        preparedStatement.setInt(1, requester_id);
        preparedStatement.setInt(2, receiver_id);
        preparedStatement.execute();
        preparedStatement.close();
        FriendRequesters fr = new FriendRequesters(bc);
        fr.removeRequest(requester_id, receiver_id);
    }

    public void rejectFriend(int requester_id, int receiver_id) throws SQLException {
        FriendRequesters fr = new FriendRequesters(bc);
        fr.removeRequest(requester_id, receiver_id);
    }

    public void removeFriend(int requester_id, int receiver_id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("Delete from friends where requester_id = ? and receiver_id =? ;");
        preparedStatement.setInt(1, receiver_id);
        preparedStatement.setInt(2, requester_id);
        preparedStatement.execute();
        preparedStatement.setInt(2, receiver_id);
        preparedStatement.setInt(1, requester_id);
        preparedStatement.execute();
        preparedStatement.close();
    }

    public boolean isFriend(int id1, int id2) throws SQLException {
        // it will be saved in database either (id1, id2) or (id2, id1)
        return isFriendHelper(id1, id2) || isFriendHelper(id2, id1);
    }

    // determines if two ids are friends where first is requester and second is receiver
    private boolean isFriendHelper(int requester_id, int receiver_id) throws SQLException {
        Statement statement = connection.createStatement();
        PreparedStatement preparedStatement = connection.prepareStatement("Select * from friends where requester_id = ? and  receiver_id =?;");
        preparedStatement.setInt(1, requester_id);
        preparedStatement.setInt(2, receiver_id);
        ResultSet rs = preparedStatement.executeQuery();
        return rs.next();
    }
}
