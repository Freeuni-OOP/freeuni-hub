package Manage.HelperClasses;

import DataBaseConnection.BaseConnector;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class FriendAddition {

    BaseConnector bc;
    public FriendAddition(BaseConnector bc){
        this.bc = bc;
    }

    public void addFriend(int requester_id, int receiver_id) throws SQLException {
        Connection connection = bc.accessConnection();
        Statement statement = connection.createStatement();
        statement.execute("Insert into friends (requester_id,receiver_id) values ("
                + requester_id +"," + receiver_id+");");
    }

    public void removeFriend(int requester_id, int receiver_id) throws SQLException {
        Connection connection = bc.accessConnection();
        Statement statement = connection.createStatement();
        statement.execute("Delete from friends where requester_id = " + requester_id
                +" and " + " receiver_id =" + receiver_id);
        statement.execute("Delete from friends where requester_id = " + receiver_id
                +" and " + " receiver_id =" + requester_id);
    }
}
