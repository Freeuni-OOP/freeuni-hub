package Manage.HelperClasses;

import DataBaseConnection.BaseConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static Manage.Configurations.UserConfiguration.USERS_TABLE;

public class UserById {
    private static Connection connection;

    public UserById(BaseConnector bc) {
        connection = bc.accessConnection();
    }


    public User getUser(int id) throws SQLException {
        Statement statement = connection.createStatement();
        String userFirstName = "", userLastName = "", userName = "", sex = "", course = "";

        ResultSet resultSet = statement.executeQuery("Select * from usersInfo where user_id = '" + id + "';");
        while (resultSet.next()) {
            userFirstName = resultSet.getString(2);
            userLastName = resultSet.getString(3);
            sex = resultSet.getString(4);
            course = resultSet.getString(5);
        }
        ResultSet rs = statement.executeQuery("Select user_name from users where id = " + id + ";");
        while (rs.next()) {
            userName = rs.getString(1);
        }
        User user = new User(id, userFirstName, userLastName, userName,
                sex, course);
        return user;

    }


    // method finds user_id with mail as parameter
    public int getIdByMail(String mail) throws SQLException {
        Statement stmt = connection.createStatement();

        ResultSet rs = stmt.executeQuery("select * from " + USERS_TABLE +
                " where email = '" + mail + "';");

        while (rs.next()) {
            return rs.getInt("id");
        }
        return -1;
    }

    public int getIdByUsername(String userName) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from " + USERS_TABLE +
                " where user_name = '" + userName + "';");
        while (resultSet.next()) {
            return resultSet.getInt("id");
        }
        return -1;
    }
}