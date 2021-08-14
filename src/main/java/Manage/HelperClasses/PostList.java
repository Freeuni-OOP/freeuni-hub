package Manage.HelperClasses;

import DataBaseConnection.BaseConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
        ResultSet resultSet = statement.executeQuery("select * from posts where user_id = " + userId + ";");
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
