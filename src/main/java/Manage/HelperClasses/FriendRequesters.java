package Manage.HelperClasses;

import DataBaseConnection.BaseConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class FriendRequesters {
    private static Connection connection;
    BaseConnector bc;
    public FriendRequesters(BaseConnector bc){
        connection = bc.accessConnection();
        this.bc =bc;
    }

    public ArrayList<User> getFriendRequesters(int user_id) throws SQLException {
        ArrayList<User> result = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("Select requester_id from friendRequests " +
                "where receiver_id = " + user_id + " ;");
        while(resultSet.next()){
            UserById userById = new UserById(bc);
            result.add(userById.getUser(resultSet.getInt(1)));
        }
        return result;
    }

    // returns false if request is already sent, true otherwise
    public boolean sendFriendRequest(int requester_id, int receiver_id) throws SQLException {
        if (hasSentFriendRequest(requester_id, receiver_id))
            return false;
        Statement statement = connection.createStatement();
        statement.execute("Insert into friendRequests (requester_id, receiver_id, accepted) " +
                "values (" + requester_id + "," + receiver_id + ", false);");
        return true;
    }

    public boolean hasSentFriendRequest(int requester_id, int receiver_id) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("Select * from friendRequests where requester_id = " +
                requester_id + " and receiver_id = " + receiver_id + ";");
        return rs.next();
    }

    public void removeRequest(int requester_id, int receiver_id) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("Delete from friendRequests where requester_id = " + requester_id +
                " and receiver_id = " + receiver_id);
    }

}