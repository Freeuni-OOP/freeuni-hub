package Manage;

import DataBaseConnection.BaseConnector;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import static Manage.Configurations.UserConfiguration.USERS_INFO_TABLE;

public class Search {
    private static Connection connection;

    public Search(BaseConnector baseConnector) {
        connection = baseConnector.accessConnection();
    }

    public ArrayList<User> searchUsers(String userName) throws SQLException {
        ArrayList<User> answer = new ArrayList<>();
        Statement statement = connection.createStatement();
        System.out.println("Select * from " + USERS_INFO_TABLE +" where user_name = '" + userName + "';");
        ResultSet resultSet = statement.executeQuery("Select * from " + USERS_INFO_TABLE + " where user_name = '" + userName + "';");
        while (resultSet.next()) {
            User curUser = new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
                    resultSet.getString(4), resultSet.getString(5));
            answer.add(curUser);
        }
        return answer;
    }

    public ArrayList<User> searchSimilarUsers(String userName) throws SQLException {
        ArrayList<User> answer = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("Select * from " + USERS_INFO_TABLE + " where user_name like '%" + userName + "%';");
        while (resultSet.next()) {
            User curUser = new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
                    resultSet.getString(4), resultSet.getString(5));
            answer.add(curUser);
        }
        return answer;
    }
}