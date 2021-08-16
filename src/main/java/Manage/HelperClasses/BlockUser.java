package Manage.HelperClasses;

import DataBaseConnection.BaseConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BlockUser {
    private BaseConnector bc;
    private Connection con;

    public BlockUser(BaseConnector bc) {
        this.bc = bc;
        con = bc.accessConnection();
    }

    public void blockById(int blocker_id, int blocked_id) throws SQLException {
        PreparedStatement preparedStatement = con.prepareStatement("Insert into blockedUsers(blocker_id,blocked_id) values (?,?)");
        preparedStatement.setInt(1, blocker_id);
        preparedStatement.setInt(2, blocked_id);
        preparedStatement.execute();
        preparedStatement.close();
    }

    public void unblockById(int blocker_id, int blocked_id) throws SQLException {
        PreparedStatement preparedStatement = con.prepareStatement("Delete from blockedUsers where blocker_id = ? and blocked_id= ? ;");
        preparedStatement.setInt(1, blocker_id);
        preparedStatement.setInt(2, blocked_id);
        preparedStatement.execute();
        preparedStatement.close();
    }

    // returns blocked list of ID-s
    public List<Integer> getBlockedList(int blocker_id) throws SQLException {
        List<Integer> idList = new ArrayList<>();
        Statement statement = con.createStatement();
        PreparedStatement preparedStatement = con.prepareStatement("Select blocked_id from blockedUsers " +
                "where blocker_id = ?;");
        preparedStatement.setInt(1, blocker_id);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            int blocked_id = resultSet.getInt(1);
            idList.add(blocked_id);
        }
        preparedStatement.close();
        return idList;
    }

    public boolean isBlocked(int blocker_id, int blocked_id) throws SQLException {
        PreparedStatement preparedStatement = con.prepareStatement("Select * from blockedUsers where " +
                "blocker_id = ? and blocked_id = ?; ");
        preparedStatement.setInt(1, blocker_id);
        preparedStatement.setInt(2, blocked_id);
        ResultSet resultSet = preparedStatement.executeQuery();
        int num = 0;
        while (resultSet.next()) {
            num++;
        }
        preparedStatement.close();
        return num != 0;
    }
}