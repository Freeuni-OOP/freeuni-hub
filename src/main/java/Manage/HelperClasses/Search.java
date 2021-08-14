package Manage.HelperClasses;

import DataBaseConnection.BaseConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
        ResultSet rs = statement.executeQuery("Select * from " + USERS_TABLE + " where user_name = '" + userName + "';");
        int id = 0;
        while (rs.next()) {
            id = rs.getInt(1);
        }
        ResultSet resultSet = statement.executeQuery("Select * from " + USERS_INFO_TABLE + " where user_id = " + id + ";");
        while (resultSet.next()) {
            User curUser = new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), userName,
                    resultSet.getString(4), resultSet.getString(5));
            answer.add(curUser);
        }
        return answer;
    }

    public ArrayList<User> searchSimilarUsers(String userName, int user_id) throws SQLException, ClassNotFoundException {
        ArrayList<User> answer = new ArrayList<>();
        Statement statement = connection.createStatement();
        ArrayList<Integer> user_ids = new ArrayList<>();
        ArrayList<String> users = new ArrayList<>();
        ResultSet rs = statement.executeQuery("Select * from " + USERS_TABLE + " where user_name like  '%" + userName + "%' " +
                "and id != " + user_id + ";");
        while (rs.next()) {
            int id = rs.getInt(1);
            user_ids.add(id);
            users.add(rs.getString("user_name"));
        }
        for (int i = 0; i < user_ids.size(); i++) {
            int id = user_ids.get(i);
            String curUserName = users.get(i);
            ResultSet resultSet = statement.executeQuery("Select * from " + USERS_INFO_TABLE + " where user_id = " + id + ";");
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
        return result;
    }
}