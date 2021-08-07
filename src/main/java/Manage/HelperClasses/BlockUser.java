package Manage.HelperClasses;

import DataBaseConnection.BaseConnector;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.ConcurrentHashMap;

public class BlockUser {
    BaseConnector bc;
    public BlockUser(BaseConnector bc){
        this.bc=bc;
    }
    public void blockById(int blocker_id, int blocked_id) throws SQLException {
        Connection connection = bc.accessConnection();
        Statement statement = connection.createStatement();
        statement.execute("Insert into blockedUsers(blocker_id,blocked_id) values ("
                +blocker_id+","+blocked_id+");");
    }
    public void unblockById(int blocker_id,int blocked_id) throws SQLException {
        Connection connection = bc.accessConnection();
        Statement statement = connection.createStatement();
        statement.execute("Delete from blockedUsers where blocker_id = "+blocker_id
                +" and blocked_id=" + blocked_id +";");
    }
}
