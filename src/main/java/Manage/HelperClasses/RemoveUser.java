package Manage.HelperClasses;

import DataBaseConnection.BaseConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class RemoveUser {
    BaseConnector bc;

    public RemoveUser(BaseConnector bc) {
        this.bc = bc;
    }

    public void removeById(int user_id) throws SQLException, ClassNotFoundException {
        removeFromLocation(user_id);
        removeFromChangeLocationRequests(user_id);
        removeFromBlockedUsers(user_id);
        removeFromUsersInfo(user_id);
        removeFromComments(user_id);
        removeFromPosts(user_id);
        removeFromMessages(user_id);
        removeFromGroupMembers(user_id);
        removeFromGroupsInfo(user_id);
        removeFromFriendRequests(user_id);
        removeFromFriends(user_id);
        removeFromUsers(user_id);
    }

    private void removeFromLocation(int user_id) throws SQLException, ClassNotFoundException {
        Connection connection = bc.accessConnection();
        Statement statement = connection.createStatement();
        LocationAddition locationAddition = new LocationAddition(bc);
        if (locationAddition.alreadyRegistered(user_id)) {
            int location_id = locationAddition.locationId(user_id);
            statement.execute("Update locations set numStudents = numStudents-1 where id = " + location_id + ";");
        }
        statement.execute("Delete from locationMembers where user_id = " + user_id + ";");

    }

    private void removeFromChangeLocationRequests(int user_id) throws SQLException {
        Connection connection = bc.accessConnection();
        Statement statement = connection.createStatement();
        statement.execute("Delete from changeLocationRequest where receiver_id = " + user_id
                + " or  requester_id = " + user_id + ";");
    }

    private void removeFromBlockedUsers(int user_id) throws SQLException {
        Connection connection = bc.accessConnection();
        Statement statement = connection.createStatement();
        statement.execute("Delete from blockedUsers where blocker_id = " + user_id
                + " or blocked_id = " + user_id + ";");

    }

    private void removeFromUsersInfo(int user_id) throws SQLException {
        Connection connection = bc.accessConnection();
        Statement statement = connection.createStatement();
        statement.execute("Delete from usersInfo where user_id = " + user_id + ";");
    }

    private void removeFromComments(int user_id) throws SQLException {
        Connection connection = bc.accessConnection();
        Statement statement = connection.createStatement();
        statement.execute("Delete from comments where user_id = " + user_id + ";");
    }

    private void removeFromPosts(int user_id) throws SQLException {
        Connection connection = bc.accessConnection();
        Statement statement = connection.createStatement();
        statement.execute("Delete from posts where user_id = " + user_id + ";");
    }

    private void removeFromMessages(int user_id) throws SQLException {
        Connection connection = bc.accessConnection();
        Statement statement = connection.createStatement();
        statement.execute("Delete from messages where sender_id = " + user_id
                + " or receiver_id = " + user_id + ";");

    }

    private void removeFromGroupMembers(int user_id) throws SQLException {
        Connection connection = bc.accessConnection();
        Statement statement = connection.createStatement();
        statement.execute("Delete from groupMembers where member_id = " + user_id + ";");

    }

    private void removeFromGroupsInfo(int user_id) throws SQLException {
        Connection connection = bc.accessConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("Select id from groupsInfo " +
                "where admin_id = " + user_id + ";");
        ArrayList<Integer> groupIds = new ArrayList<>();
        while (resultSet.next()) {
            groupIds.add(resultSet.getInt(1));
        }
        for (Integer id : groupIds) {
            statement.execute("Delete from groupMembers where group_id = " + id + ";");
        }

    }

    private void removeFromFriendRequests(int user_id) throws SQLException {
        Connection connection = bc.accessConnection();
        Statement statement = connection.createStatement();
        statement.execute("Delete from friendRequests where requester_id = " + user_id
                + " or receiver_id = " + user_id + ";");

    }

    private void removeFromFriends(int user_id) throws SQLException {
        Connection connection = bc.accessConnection();
        Statement statement = connection.createStatement();
        statement.execute("Delete from friends where requester_id = " + user_id
                + " or receiver_id = " + user_id + ";");

    }

    private void removeFromUsers(int user_id) throws SQLException {
        Connection connection = bc.accessConnection();
        Statement statement = connection.createStatement();
        System.out.println("Delete from users where id = " + user_id + " ;");
        statement.execute("Delete from users where id = " + user_id + " ;");

    }

}
