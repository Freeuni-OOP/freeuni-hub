package Manage.HelperClasses;

import DataBaseConnection.BaseConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostList {

    private BaseConnector bc;
    private Connection con;

    public PostList(BaseConnector bc) {
        this.bc = bc;
        con = bc.accessConnection();
    }

    // returns list of Posts by Id
    public List<Post> getPostList(int userId) throws SQLException {
        List<Post> list = new ArrayList();
        Statement statement = con.createStatement();

        UserById ubi = new UserById(bc);
        PreparedStatement preparedStatement = con.prepareStatement("select * from posts where user_id = ?;");
        preparedStatement.setInt(1,userId);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            list.add(new Post(resultSet.getInt("post_id"),
                    resultSet.getInt("user_id"), resultSet.getString("post_text")));
        }
        return list;
    }

    // returns list of Posts by Username
    public List<Post> getPostList(String userName) throws SQLException {
        UserById ubi = new UserById(bc);
        int id = ubi.getIdByUsername(userName);
        return getPostList(id);
    }


}
