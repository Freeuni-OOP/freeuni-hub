package Manage.HelperClasses;

import DataBaseConnection.BaseConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SaveleList {
    private BaseConnector bc;
    private Connection con;

    public SaveleList(BaseConnector bc){
        this.bc = bc;
        con = bc.accessConnection();
    }

    // returns list of Users by Id
    public List<User> getSaveleRequestersList(int id) throws SQLException {
        List<User> list = new ArrayList();
        Statement statement = con.createStatement();

        UserById ubi = new UserById(bc);
        ResultSet resultSet = statement.executeQuery("select * from changeLocationRequest;");
        while (resultSet.next()) {
          if (resultSet.getInt("receiver_id") == id){
                int friendId = resultSet.getInt("requester_id");
                User friend = ubi.getUser(friendId);
                list.add(friend);
            }
        }

        return list;
    }

    // returns list of Users by Username
    public List<User> getSaveleRequestersList(String userName) throws SQLException {
        UserById ubi = new UserById(bc);
        int id = ubi.getIdByUsername(userName);
        return getSaveleRequestersList(id);
    }
    public void removeRequest(int requester_id,int receiver_id) throws SQLException {
        Statement statement = con.createStatement();
        statement.execute("Delete from changeLocationRequest where requester_id = "+requester_id
                +" and receiver_id =" +receiver_id+";");
    }
    public void addRequest(int requester_id, int receiver_id) throws SQLException, ClassNotFoundException {
        Statement statement = con.createStatement();
        LocationID locationID = new LocationID(new BaseConnector());
        int requester_location_id=0;
        int receiver_location_id=0;
        System.out.println("shemowmeba");
        ResultSet resultSet = statement.executeQuery("Select location_id from locationMembers where" +
                " user_id = "+requester_id+";");
        while(resultSet.next()){
            requester_location_id = resultSet.getInt(1);
        }
        resultSet = statement.executeQuery("Select location_id from locationMembers where" +
                " user_id = "+receiver_id+";");
        while(resultSet.next()){
            receiver_location_id = resultSet.getInt(1);
        }
        statement.execute("Insert into changeLocationRequest values("+requester_id+","+
                requester_location_id+","+receiver_id+","+receiver_location_id+",false);");
    }
}
