package Manage.HelperClasses;

import DataBaseConnection.BaseConnector;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FriendAddition {

    BaseConnector bc;
    Connection connection;

    public FriendAddition(BaseConnector bc){
        this.bc = bc;
        connection = bc.accessConnection();
    }

    public void addFriend(int requester_id, int receiver_id) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("Insert into friends (requester_id,receiver_id) values ("
                + requester_id +"," + receiver_id+");");
        FriendRequesters fr = new FriendRequesters(bc);
        fr.removeRequest(requester_id, receiver_id);
    }

    public void rejectFriend(int requester_id, int receiver_id) throws SQLException {
        FriendRequesters fr = new FriendRequesters(bc);
        fr.removeRequest(requester_id, receiver_id);
    }

    public void removeFriend(int requester_id, int receiver_id) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("Delete from friends where requester_id = " + requester_id
                +" and " + " receiver_id =" + receiver_id);
        statement.execute("Delete from friends where requester_id = " + receiver_id
                +" and " + " receiver_id =" + requester_id);
    }

    public boolean isFriend(int id1, int id2) throws SQLException {
        // it will be saved in database either (id1, id2) or (id2, id1)
        return isFriendHelper(id1, id2) || isFriendHelper(id2, id1);
    }

    // determines if two ids are friends where first is requester and second is receiver
    private boolean isFriendHelper(int requester_id, int receiver_id) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("Select * from friends where requester_id = " + requester_id
                +" and " + " receiver_id =" + receiver_id);
        return rs.next();
    }
}
