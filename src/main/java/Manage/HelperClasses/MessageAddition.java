package Manage.HelperClasses;

import DataBaseConnection.BaseConnector;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class MessageAddition {

    BaseConnector bc;
    public MessageAddition(BaseConnector bc){
        this.bc = bc;
    }
    public void addMessage(int sender_id, int receiver_id,String message_text, int id) throws SQLException {
        Connection connection = bc.accessConnection();
        Statement statement = connection.createStatement();
        statement.execute("Insert into messages values("+ id+","+sender_id+","+receiver_id
                +",'"+message_text+"');");
    }
    public void removeMessage(int id) throws SQLException {
        Connection connection = bc.accessConnection();
        Statement statement = connection.createStatement();
        statement.execute("Delete from messages where id = "+ id+" ;");
    }
}
