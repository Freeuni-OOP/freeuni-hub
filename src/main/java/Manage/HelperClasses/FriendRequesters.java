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
}