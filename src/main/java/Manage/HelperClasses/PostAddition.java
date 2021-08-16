package Manage.HelperClasses;

import DataBaseConnection.BaseConnector;

import java.sql.*;

import static java.lang.Math.max;

public class PostAddition {
    private BaseConnector bc;

    public PostAddition(BaseConnector bc) {
        this.bc = bc;
    }

    public void addPost(int user_id, String postText, int post_id) throws SQLException {
        Connection connection = bc.accessConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("Insert into posts (post_id,user_id,post_text) values (?,?,?);");
        preparedStatement.setInt(1, post_id);
        preparedStatement.setInt(2, user_id);
        preparedStatement.setString(3, postText);
        preparedStatement.execute();
        preparedStatement.close();
    }

    public void removePost(int post_id) throws SQLException {
        Connection connection = bc.accessConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("Delete from posts where post_id = ?;");
        preparedStatement.setInt(1, post_id);
        preparedStatement.execute();
        preparedStatement.close();
    }

    public int next() throws SQLException {
        Connection connection = bc.accessConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("Select * from posts");
        int cur = 0;
        while (resultSet.next()) {
            cur = max(cur, resultSet.getInt(1));
        }
        return cur + 1;
    }
}
