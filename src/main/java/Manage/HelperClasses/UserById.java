package Manage.HelperClasses;

import DataBaseConnection.BaseConnector;

import java.sql.*;
import java.util.function.Predicate;

import static Manage.Configurations.UserConfiguration.USERS_TABLE;

public class UserById {
    private static Connection connection;

    public UserById(BaseConnector bc) {
        connection = bc.accessConnection();
    }


    public User getUser(int id) throws SQLException {
        Statement statement = connection.createStatement();
        String userFirstName = "", userLastName = "", userName = "", sex = "", course = "";
        PreparedStatement preparedStatement = connection.prepareStatement("Select * from usersInfo where user_id = ?;");
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            userFirstName = resultSet.getString(2);
            userLastName = resultSet.getString(3);
            sex = resultSet.getString(4);
            course = resultSet.getString(5);
        }
        preparedStatement = connection.prepareStatement("Select user_name from users where id = ?;");
        preparedStatement.setInt(1,id);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            userName = rs.getString(1);
        }
        User user = new User(id, userFirstName, userLastName, userName,
                sex, course);
        preparedStatement.close();
        return user;

    }


    // method finds user_id with mail as parameter
    public int getIdByMail(String mail) throws SQLException {
        Statement stmt = connection.createStatement();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from " + USERS_TABLE +
                " where email = ?;");
        preparedStatement.setString(1,mail);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            return rs.getInt("id");
        }
        preparedStatement.close();
        return -1;
    }

    public int getIdByUsername(String userName) throws SQLException {
        PreparedStatement preparedStatement= connection.prepareStatement("select * from " + USERS_TABLE +
                " where user_name = ? ;");
        preparedStatement.setString(1,userName);
        ResultSet resultSet =preparedStatement.executeQuery();
        while (resultSet.next()) {
            return resultSet.getInt("id");
        }
        preparedStatement.close();
        return -1;
    }
}