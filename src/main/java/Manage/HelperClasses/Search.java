package Manage.HelperClasses;

import DataBaseConnection.BaseConnector;

import java.lang.ref.PhantomReference;
import java.sql.*;
import java.util.ArrayList;

import static Manage.Configurations.UserConfiguration.USERS_INFO_TABLE;
import static Manage.Configurations.UserConfiguration.USERS_TABLE;

public class Search {
    private static Connection connection;

    public Search(BaseConnector baseConnector) {
        connection = baseConnector.accessConnection();
    }

    public ArrayList<User> searchUsers(String userName) throws SQLException {
        ArrayList<User> answer = new ArrayList<>();
        Statement statement = connection.createStatement();
        PreparedStatement preparedStatement = connection.prepareStatement("Select * from " + USERS_TABLE + " where user_name = ?;");
        preparedStatement.setString(1,userName);
        ResultSet rs = preparedStatement.executeQuery();
        int id = 0;
        while (rs.next()) {
            id = rs.getInt(1);
        }
        preparedStatement = connection.prepareStatement("Select * from " + USERS_INFO_TABLE + " where user_id = ? ;");
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            User curUser = new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), userName,
                    resultSet.getString(4), resultSet.getString(5));
            answer.add(curUser);
        }
        preparedStatement.close();
        return answer;
    }

    public ArrayList<User> searchSimilarUsers(String userName, int user_id) throws SQLException, ClassNotFoundException {
        ArrayList<User> answer = new ArrayList<>();
        Statement statement = connection.createStatement();
        ArrayList<Integer> user_ids = new ArrayList<>();
        ArrayList<String> users = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement("Select * from " + USERS_TABLE + " where user_name like  ? " +
                "and id != ?;");
        preparedStatement.setString(1,"%"+userName+"%");
        preparedStatement.setInt(2,user_id);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            int id = rs.getInt(1);
            user_ids.add(id);
            users.add(rs.getString("user_name"));
        }
        for (int i = 0; i < user_ids.size(); i++) {
            int id = user_ids.get(i);
            String curUserName = users.get(i);
            preparedStatement = connection.prepareStatement("Select * from " + USERS_INFO_TABLE + " where user_id = ?;");
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User curUser = new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), curUserName,
                        resultSet.getString(4), resultSet.getString(5));
                answer.add(curUser);
            }
        }
        ArrayList<User> result = new ArrayList<>();
        for (User user : answer) {
            int blocker_id = user.getId();
            BlockUser blockUser = new BlockUser(new BaseConnector());
            if (!blockUser.isBlocked(blocker_id, user_id)) {
                result.add(user);
            }
        }
        preparedStatement.close();
        return result;
    }
}