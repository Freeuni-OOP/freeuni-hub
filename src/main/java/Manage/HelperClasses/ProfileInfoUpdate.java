package Manage.HelperClasses;

import DataBaseConnection.BaseConnector;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ProfileInfoUpdate {

    private BaseConnector bc;
    private Connection con;

    public ProfileInfoUpdate(BaseConnector bc){
        this.bc = bc;
        con = bc.accessConnection();
    }

    public void updateUserName(int user_id, String user_name) throws SQLException {
        Statement statement = con.createStatement();
        statement.execute("Update usersInfo Set user_name = '" + user_name + "' where user_id = " + user_id +";");
    }

    public void updateUserLastName(int user_id, String user_last_name) throws SQLException {
        Statement statement = con.createStatement();
        statement.execute("Update usersInfo Set user_last_name = '" + user_last_name + "' where user_id = " + user_id +";");
    }

    public void updateSqesi(int user_id, String sqesi) throws SQLException {
        Statement statement = con.createStatement();
        statement.execute("Update usersInfo Set sqesi = '" + sqesi + "' where user_id = " + user_id +";");
    }

    public void updateCourse(int user_id, String course) throws SQLException {
        Statement statement = con.createStatement();
        statement.execute("Update usersInfo Set course = '" + course + "' where user_id = " + user_id +";");
    }

    public void updateInfo(int user_id, String user_name, String user_last_name, String sqesi, String course) throws SQLException {
        updateUserName(user_id, user_name);
        updateUserLastName(user_id, user_last_name);
        updateSqesi(user_id, sqesi);
        updateCourse(user_id, course);
    }

}
