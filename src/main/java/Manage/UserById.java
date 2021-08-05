package Manage;

import DataBaseConnection.BaseConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserById {
    private static Connection connection;
    public UserById (BaseConnector bc){
        connection = bc.accessConnection();
    }
    public User getUser(int id) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("Select * from usersInfo where user_id = " + id +";");
        while(resultSet.next()){
            User user = new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
                    resultSet.getString(4), resultSet.getString(5));
            return user;
        }
        return null;
    }
}
