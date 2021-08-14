package Manage.HelperClasses;

import DataBaseConnection.BaseConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
        Statement statement = con.createStatement();
        statement.execute("Insert into blockedUsers(blocker_id,blocked_id) values ("
                + blocker_id + "," + blocked_id + ");");
    }

    public void unblockById(int blocker_id, int blocked_id) throws SQLException {
        Statement statement = con.createStatement();
        statement.execute("Delete from blockedUsers where blocker_id = " + blocker_id
                + " and blocked_id=" + blocked_id + ";");
    }

    // returns blocked list of ID-s
    public List<Integer> getBlockedList(int blocker_id) throws SQLException {
        List<Integer> idList = new ArrayList<>();
        Statement statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery("Select blocked_id from blockedUsers " +
                "where blocker_id = " + blocker_id + " ;");
        while (resultSet.next()) {
            int blocked_id = resultSet.getInt(1);
            idList.add(blocked_id);
        }
        return idList;
    }

    public boolean isBlocked(int blocker_id, int blocked_id) throws SQLException {
        Statement statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery("Select * from blockedUsers where blocker_id= " +
                blocker_id + " and blocked_id =" + blocked_id + ";");
        int num = 0;
        while (resultSet.next()) {
            num++;
        }
        return num != 0;
    }
}