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
        int id=0;
        while(rs.next()){
            id=rs.getInt(1);
        }
        ResultSet resultSet = statement.executeQuery("Select * from "+USERS_INFO_TABLE+ " where user_id = "+ id+";" );
        while (resultSet.next()) {
            User curUser = new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),userName,
                    resultSet.getString(4), resultSet.getString(5));
            answer.add(curUser);
        }
        return answer;
    }

    public ArrayList<User> searchSimilarUsers(String userName) throws SQLException {
        ArrayList<User> answer = new ArrayList<>();
        Statement statement = connection.createStatement();
        ArrayList<Integer> user_ids = new ArrayList<>();
        ResultSet rs = statement.executeQuery("Select * from " + USERS_TABLE + " where user_name like  '%" + userName + "%';");
        while(rs.next()) {
            int id = rs.getInt(1);
           user_ids.add(id);
        }
        for(int id : user_ids){
            ResultSet resultSet = statement.executeQuery("Select * from " + USERS_INFO_TABLE + " where user_id = " + id + ";");
            while (resultSet.next()) {
                User curUser = new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), userName,
                        resultSet.getString(4), resultSet.getString(5));
                answer.add(curUser);
            }
        }
        return answer;
    }
}