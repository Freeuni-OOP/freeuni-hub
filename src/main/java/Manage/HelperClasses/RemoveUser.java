package Manage.HelperClasses;

import DataBaseConnection.BaseConnector;

import java.sql.*;
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
        //PreparedStatement preparedStatement = connection.prepareStatement();
        if (locationAddition.alreadyRegistered(user_id)) {
            int location_id = locationAddition.locationId(user_id);
            PreparedStatement preparedStatement = connection.prepareStatement("Update locations set numStudents = numStudents-1 where id = ?;");
            preparedStatement.setInt(1, location_id);
            preparedStatement.execute();
            preparedStatement.close();
        }
        PreparedStatement preparedStatement = connection.prepareStatement("Delete from locationMembers where user_id = ?;");
        preparedStatement.setInt(1, user_id);
        preparedStatement.execute();
        preparedStatement.close();

    }

    private void removeFromChangeLocationRequests(int user_id) throws SQLException {
        Connection connection = bc.accessConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("Delete from changeLocationRequest where receiver_id = ?"
                + " or  requester_id = ? ;");
        preparedStatement.setInt(1, user_id);
        preparedStatement.setInt(2, user_id);
        preparedStatement.execute();
        preparedStatement.close();
    }

    private void removeFromBlockedUsers(int user_id) throws SQLException {
        Connection connection = bc.accessConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("Delete from blockedUsers where blocker_id = ?"
                + " or blocked_id = ?;");
        preparedStatement.setInt(1, user_id);
        preparedStatement.setInt(2, user_id);
        preparedStatement.execute();
        preparedStatement.close();

    }

    private void removeFromUsersInfo(int user_id) throws SQLException {
        Connection connection = bc.accessConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("Delete from usersInfo where user_id = ?;");
        preparedStatement.setInt(1, user_id);
        preparedStatement.execute();
        preparedStatement.close();
    }

    private void removeFromComments(int user_id) throws SQLException {
        Connection connection = bc.accessConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("Delete from comments where user_id = ?;");
        preparedStatement.setInt(1, user_id);
        preparedStatement.execute();
        preparedStatement.close();
    }

    private void removeFromPosts(int user_id) throws SQLException {
        Connection connection = bc.accessConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("Delete from posts where user_id = ?;");
        preparedStatement.setInt(1, user_id);
        preparedStatement.execute();
        preparedStatement.close();
    }

    private void removeFromMessages(int user_id) throws SQLException {
        Connection connection = bc.accessConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("Delete from messages where sender_id = ? "
                + " or receiver_id = ?;");
        preparedStatement.setInt(1, user_id);
        preparedStatement.setInt(2, user_id);
        preparedStatement.execute();
        preparedStatement.close();

    }

    private void removeFromGroupMembers(int user_id) throws SQLException {
        Connection connection = bc.accessConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("Delete from groupMembers where member_id = ?;");
        preparedStatement.setInt(1, user_id);
        preparedStatement.execute();
        preparedStatement.close();

    }

    private void removeFromGroupsInfo(int user_id) throws SQLException {
        Connection connection = bc.accessConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("Select id from groupsInfo" +
                " where admin_id = ? ;");
        preparedStatement.setInt(1, user_id);
        ResultSet resultSet = preparedStatement.executeQuery();
        ArrayList<Integer> groupIds = new ArrayList<>();
        while (resultSet.next()) {
            groupIds.add(resultSet.getInt(1));
        }
        preparedStatement = connection.prepareStatement("Delete from groupMembers where group_id = ?;");
        for (Integer id : groupIds) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        }
        preparedStatement.close();

    }

    private void removeFromFriendRequests(int user_id) throws SQLException {
        Connection connection = bc.accessConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("Delete from friendRequests where requester_id = ? or receiver_id = ?;");
        preparedStatement.setInt(1, user_id);
        preparedStatement.setInt(2, user_id);
        preparedStatement.execute();
        preparedStatement.close();

    }

    private void removeFromFriends(int user_id) throws SQLException {
        Connection connection = bc.accessConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("Delete from friends where requester_id = ? or receiver_id = ?;");
        preparedStatement.setInt(1, user_id);
        preparedStatement.setInt(2, user_id);
        preparedStatement.execute();
        preparedStatement.close();

    }

    private void removeFromUsers(int user_id) throws SQLException {
        Connection connection = bc.accessConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("Delete from users where id = ?;");
        preparedStatement.setInt(1, user_id);
        preparedStatement.execute();
        preparedStatement.close();
    }

}
