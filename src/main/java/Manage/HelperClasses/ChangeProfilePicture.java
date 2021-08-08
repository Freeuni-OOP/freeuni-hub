package Manage.HelperClasses;

import DataBaseConnection.BaseConnector;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ChangeProfilePicture {
    BaseConnector bc;

    public ChangeProfilePicture(BaseConnector bc){
        this.bc = bc;
    }

    public void changePicture(int user_id, String image) throws SQLException {
        Connection connection = bc.accessConnection();
        Statement statement = connection.createStatement();
        statement.execute("Update usersInfo Set image = '" + image + "' where user_id = " + user_id +";");
    }
}
