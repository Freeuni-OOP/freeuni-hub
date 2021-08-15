package Manage.HelperClasses;

import DataBaseConnection.BaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class ChangeProfilePicture {
    BaseConnector bc;

    public ChangeProfilePicture(BaseConnector bc) {
        this.bc = bc;
    }

    public void changePicture(int user_id, String image) throws SQLException {
        Connection connection = bc.accessConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("Update usersInfo Set image = ? " +
                "where user_id = ?;");
        preparedStatement.setString(1,image);
        preparedStatement.setInt(2,user_id);
        preparedStatement.execute();
        preparedStatement.close();

    }
}
