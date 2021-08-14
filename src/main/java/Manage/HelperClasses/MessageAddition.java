package Manage.HelperClasses;

import DataBaseConnection.BaseConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MessageAddition {

    BaseConnector bc;
    Connection connection;

    public MessageAddition(BaseConnector bc) {
        this.bc = bc;
        connection = bc.accessConnection();
    }

    public void addMessage(int sender_id, int receiver_id, String message_text, int id) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("Insert into messages values(" + id + "," + sender_id + "," + receiver_id
                + ",'" + message_text + "');");
    }

    public void removeMessage(int id) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("Delete from messages where id = " + id + " ;");
    }

    public ArrayList<Message> getMessages(int sender_id, int receiver_id) throws SQLException {
        ArrayList<Message> result = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("Select * from messages where sender_id = " +
                sender_id + " and receiver_id =" + receiver_id + ";");
        while (resultSet.next()) {
            Message message = new Message(resultSet.getInt(1), resultSet.getInt(2),
                    resultSet.getInt(3), resultSet.getString(4));
            result.add(message);
        }
        ResultSet rs = statement.executeQuery("Select * from messages where receiver_id = " +
                sender_id + " and sender_id =" + receiver_id + ";");
        while (rs.next()) {
            Message message = new Message(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4));
            result.add(message);
        }
        return result;
    }
}
