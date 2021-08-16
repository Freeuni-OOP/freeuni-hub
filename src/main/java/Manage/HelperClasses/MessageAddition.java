package Manage.HelperClasses;

import DataBaseConnection.BaseConnector;

import java.sql.*;
import java.util.ArrayList;

public class MessageAddition {

    BaseConnector bc;
    Connection connection;

    public MessageAddition(BaseConnector bc) {
        this.bc = bc;
        connection = bc.accessConnection();
    }

    public void addMessage(int sender_id, int receiver_id, String message_text, int id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("Insert into messages values(?,?,?,?);");
        preparedStatement.setInt(1, id);
        preparedStatement.setInt(2, sender_id);
        preparedStatement.setInt(3, receiver_id);
        preparedStatement.setString(4, message_text);
        preparedStatement.execute();
        preparedStatement.close();
        ;
    }

    public void removeMessage(int id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("Delete from messages where id = ?;");
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        preparedStatement.close();
    }

    public ArrayList<Message> getMessages(int sender_id, int receiver_id) throws SQLException {
        ArrayList<Message> result = new ArrayList<>();
        Statement statement = connection.createStatement();
        PreparedStatement preparedStatement = connection.prepareStatement("Select * from messages where sender_id = ?  and receiver_id =?;");
        preparedStatement.setInt(1, sender_id);
        preparedStatement.setInt(2, receiver_id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Message message = new Message(resultSet.getInt(1), resultSet.getInt(2),
                    resultSet.getInt(3), resultSet.getString(4));
            result.add(message);
        }
        preparedStatement.setInt(1, receiver_id);
        preparedStatement.setInt(2, sender_id);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            Message message = new Message(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4));
            result.add(message);
        }
        return result;
    }
}
