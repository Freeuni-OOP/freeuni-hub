package Manage.HelperClasses;

import DataBaseConnection.BaseConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FriendList {

    private BaseConnector bc;
    private Connection con;

    public FriendList(BaseConnector bc) {
        this.bc = bc;
        con = bc.accessConnection();
    }

    // returns list of Users by Id
    public List<User> getFriendList(int id) throws SQLException {
        List<User> list = new ArrayList();
        Statement statement = con.createStatement();

        UserById ubi = new UserById(bc);
        PreparedStatement preparedStatement = con.prepareStatement("Select * from friends");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            if (resultSet.getInt("requester_id") == id) {
                int friendId = resultSet.getInt("receiver_id");
                User friend = ubi.getUser(friendId);
                list.add(friend);
            } else if (resultSet.getInt("receiver_id") == id) {
                int friendId = resultSet.getInt("requester_id");
                User friend = ubi.getUser(friendId);
                list.add(friend);
            }
        }
        preparedStatement.close();
        return list;
    }

    // returns list of Users by Username
    public List<User> getFriendList(String userName) throws SQLException {
        UserById ubi = new UserById(bc);
        int id = ubi.getIdByUsername(userName);
        return getFriendList(id);
    }


}
