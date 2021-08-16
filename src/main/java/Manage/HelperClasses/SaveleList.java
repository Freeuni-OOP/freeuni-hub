package Manage.HelperClasses;

import DataBaseConnection.BaseConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SaveleList {
    private BaseConnector bc;
    private Connection con;

    public SaveleList(BaseConnector bc) {
        this.bc = bc;
        con = bc.accessConnection();
    }

    // returns list of Users by Id
    public List<User> getSaveleRequestersList(int id) throws SQLException {
        List<User> list = new ArrayList();
        Statement statement = con.createStatement();

        UserById ubi = new UserById(bc);
        PreparedStatement preparedStatement = con.prepareStatement("select * from changeLocationRequest;");

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            if (resultSet.getInt("receiver_id") == id) {
                int friendId = resultSet.getInt("requester_id");
                User friend = ubi.getUser(friendId);
                list.add(friend);
            }
        }
        preparedStatement.close();
        return list;
    }

    // returns list of Users by Username
    public List<User> getSaveleRequestersList(String userName) throws SQLException {
        UserById ubi = new UserById(bc);
        int id = ubi.getIdByUsername(userName);
        return getSaveleRequestersList(id);
    }

    public void removeRequest(int requester_id, int receiver_id) throws SQLException {
        PreparedStatement preparedStatement = con.prepareStatement("Delete from changeLocationRequest where requester_id = ? "
                + " and receiver_id = ?;");
        preparedStatement.setInt(1, requester_id);
        preparedStatement.setInt(2, receiver_id);
        preparedStatement.execute();
        preparedStatement.close();
    }

    public void addRequest(int requester_id, int receiver_id) throws SQLException, ClassNotFoundException {
        Statement statement = con.createStatement();
        LocationID locationID = new LocationID(new BaseConnector());
        int requester_location_id = 0;
        int receiver_location_id = 0;
        PreparedStatement preparedStatement = con.prepareStatement("Select location_id from locationMembers where" +
                " user_id = ?;");
        preparedStatement.setInt(1, requester_id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            requester_location_id = resultSet.getInt(1);
        }
        preparedStatement.setInt(1, receiver_id);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            receiver_location_id = resultSet.getInt(1);
        }
        preparedStatement = con.prepareStatement("Insert into changeLocationRequest values(?,?,?,?,false)");
        preparedStatement.setInt(1, requester_id);
        preparedStatement.setInt(2, requester_location_id);
        preparedStatement.setInt(3, receiver_id);
        preparedStatement.setInt(4, receiver_location_id);
        preparedStatement.execute();
        preparedStatement.close();
    }
}
