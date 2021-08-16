package Manage.HelperClasses;

import DataBaseConnection.BaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class ProfileInfoUpdate {

    private BaseConnector bc;
    private Connection con;

    public ProfileInfoUpdate(BaseConnector bc) {
        this.bc = bc;
        con = bc.accessConnection();
    }

    private void updateUserName(int user_id, String user_name) throws SQLException {
        PreparedStatement preparedStatement = con.prepareStatement("Update usersInfo Set user_name = ? where user_id = ?;");
        preparedStatement.setString(1,user_name);
        preparedStatement.setInt(2,user_id);
        preparedStatement.execute();
        preparedStatement.close();
    }

    private void updateUserLastName(int user_id, String user_last_name) throws SQLException {
        PreparedStatement preparedStatement = con.prepareStatement("Update usersInfo Set user_last_name = ? where user_id = ?");
        preparedStatement.setInt(2,user_id);
        preparedStatement.setString(1,user_last_name);
        preparedStatement.execute();
        preparedStatement.close();
    }

    private void updateSqesi(int user_id, String sqesi) throws SQLException {
        PreparedStatement preparedStatement = con.prepareStatement("Update usersInfo Set sqesi = ? where user_id = ?");
        preparedStatement.setInt(2,user_id);
        preparedStatement.setString(1,sqesi);
        preparedStatement.execute();
        preparedStatement.close();
    }

    private void updateCourse(int user_id, String course) throws SQLException {
        PreparedStatement preparedStatement = con.prepareStatement("Update usersInfo Set course = ? where user_id = ?");
        preparedStatement.setInt(2,user_id);
        preparedStatement.setString(1,course);
        preparedStatement.execute();
        preparedStatement.close();
    }

    public void updateInfo(int user_id, String user_name, String user_last_name, String sqesi, String course) throws SQLException {
        updateUserName(user_id, user_name);
        updateUserLastName(user_id, user_last_name);
        updateSqesi(user_id, sqesi);
        updateCourse(user_id, course);
    }

}
