package Manage.HelperClasses;

import DataBaseConnection.BaseConnector;
import org.junit.Before;
import org.junit.Test;

import javax.swing.plaf.nimbus.State;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class MessageAdditionTest {
    BaseConnector bc;
    @Before
    public void setup() throws SQLException, ClassNotFoundException {
        bc = new BaseConnector();
    }

    @Test
    public void testAddMessage() throws SQLException {
        Connection connection = bc.accessConnection();
        Statement statement = connection.createStatement();
        MessageAddition messageAddition = new MessageAddition(bc);
        statement.execute("Insert into users (id,first_name,last_name,user_name,password,email)" +
                " values "+ "(1000,'luka','macho','bigenti','123','fsjsffdsdfadsse')");
        statement.execute("Insert into users (id,first_name,last_name,user_name,password,email)" +
                " values "+ "(2000,'blukab','macho','bigentia','123','fsjdfadsse')");
        statement.execute("Insert into usersInfo (user_id,user_name,user_last_name)" +
                " values "+ "(1000,'luka','macho')");
        statement.execute("Insert into usersInfo (user_id,user_name,user_last_name)" +
                " values "+ "(2000,'blukab','macho')");

        messageAddition.addMessage(1000,2000,"first message",10);
        messageAddition.removeMessage(10);
        ResultSet resultSet = statement.executeQuery("Select * from messages");
        while(resultSet.next()){
            assertEquals(10,resultSet.getInt(1));
            assertEquals(1000,resultSet.getInt(2));
            assertEquals(2000,resultSet.getInt(3));
            assertEquals("first message",resultSet.getString(4));
        }
        statement.execute("delete from usersInfo where user_id = 1000;");
        statement.execute("delete from users where user_name = 'bigenti';");
        statement.execute("delete from usersInfo where user_id = 2000;");
        statement.execute("delete from users where user_name = 'bigentia'");
    }

    @Test
    public void testGetMessages() throws SQLException {
        Connection connection = bc.accessConnection();
        Statement statement = connection.createStatement();
        MessageAddition messageAddition = new MessageAddition(bc);
        statement.execute("Insert into users (id,first_name,last_name,user_name,password,email)" +
                " values "+ "(1000,'luka','macho','bigenti','123','fsjsffdsdfadsse')");
        statement.execute("Insert into users (id,first_name,last_name,user_name,password,email)" +
                " values "+ "(2000,'blukab','macho','bigentia','123','fsjdfadsse')");
        statement.execute("Insert into usersInfo (user_id,user_name,user_last_name)" +
                " values "+ "(1000,'luka','macho')");
        statement.execute("Insert into usersInfo (user_id,user_name,user_last_name)" +
                " values "+ "(2000,'blukab','macho')");
        messageAddition.addMessage(1000,2000,"first message",10);
        ArrayList<Message> messages = messageAddition.getMessages(1000,2000);
        assertEquals(1,messages.size());
        assertEquals(10,messages.get(0).getId());
        assertEquals(1000,messages.get(0).getSender_id());
        assertEquals(2000,messages.get(0).getReceiver_id());
        assertEquals("first message",messages.get(0).getMessageText());
        messageAddition.addMessage(1000,2000,"second message",11);
        messages = messageAddition.getMessages(2000,1000);
        assertEquals(2,messages.size());
        messageAddition.removeMessage(10);
        messageAddition.removeMessage(11);
        statement.execute("delete from usersInfo where user_id = 1000;");
        statement.execute("delete from users where user_name = 'bigenti';");
        statement.execute("delete from usersInfo where user_id = 2000;");
        statement.execute("delete from users where user_name = 'bigentia'");
    }
}
