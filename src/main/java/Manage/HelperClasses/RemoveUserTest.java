package Manage.HelperClasses;

import DataBaseConnection.BaseConnector;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.assertEquals;

public class RemoveUserTest {
    BaseConnector bc;
    @Before
    public void setup() throws SQLException, ClassNotFoundException {
        bc = new BaseConnector();
    }

    @Test
    public void testBlockUser() throws SQLException, ClassNotFoundException {
        RemoveUser removeUser = new RemoveUser(bc);
        Connection connection = bc.accessConnection();
        Statement statement = connection.createStatement();
        removeUser.removeById(1000);
        removeUser.removeById(2000);

        statement.execute("Insert into users (id,first_name,last_name,user_name,password,email)" +
                " values "+ "(1000,'luka','macho','bigenti','123','fsjsffdsdfadsse')");
        statement.execute("Insert into users (id,first_name,last_name,user_name,password,email)" +
                " values "+ "(2000,'blukab','macho','bigentia','123','fsjdfadsse')");
        statement.execute("Insert into usersInfo (user_id,user_name,user_last_name)" +
                " values "+ "(1000,'luka','macho')");
        statement.execute("Insert into usersInfo (user_id,user_name,user_last_name)" +
                " values "+ "(2000,'blukab','macho')");
        statement.execute("Insert into friendRequests (requester_id,receiver_id,accepted) " +
                "values(1000,2000,false)");
        statement.execute("Insert into friends (requester_id,receiver_id) " +
                "values(1000,2000)");
        ResultSet resultSet = statement.executeQuery("Select * from friendRequests");
        int num =0;
        while(resultSet.next()){
            num++;
        }
        assertEquals(1,num);
        removeUser.removeById(1000);
        ResultSet resultSet2 = statement.executeQuery("Select * from friends");
        num =0;
        while(resultSet2.next()){
            num++;
        }
        assertEquals(0,num);
        removeUser.removeById(2000);
        statement.execute("delete from usersInfo where user_id = 1000;");
        statement.execute("delete from users where user_name = 'bigenti';");
        statement.execute("delete from usersInfo where user_id = 2000;");
        statement.execute("delete from users where user_name = 'bigentia'");
    }

}
