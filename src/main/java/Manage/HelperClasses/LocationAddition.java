package Manage.HelperClasses;

import DataBaseConnection.BaseConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LocationAddition {
    BaseConnector bc;
    Connection connection;


    public LocationAddition(BaseConnector bc) throws SQLException, ClassNotFoundException {
       this.bc = bc;
        connection=bc.accessConnection();
    }
    public int locationId(int user_id) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("Select location_id from locationMembers where user_id=" + user_id+";");
        while(resultSet.next()){
            return  resultSet.getInt(1);
        }
        return 0;
    }
    public void addLocation(int location_id, String username) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("Insert into locations values("+location_id+",'"+username+"',0);");
    }
    public void removeLocation(int location_id) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("Delete from locations where id =" + location_id+";");
    }
    public void addIdInLocation(int user_id, int location_id) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("Insert into locationMembers values("+location_id+","+user_id+");");
        statement.execute("Update locations set numStudents = numStudents + 1 where id= "+location_id+";");
    }
    public void removeFromLocation(int user_id) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("Delete from locationMembers where user_id ="+user_id+";");
    }
    public void updateLocationId(int user_id, int location_id) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("Update locationMembers set location_id = "+ location_id+ " where user_id=" +
                user_id+" ;");
    }
    public boolean alreadyRegistered(int user_id) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("Select * from locationMembers where user_id=" +
                +user_id+";");
        int num=0;
        while(resultSet.next()){
            num++;
        }
        return num!=0;
    }
    public void changeLocations(int requester_id,int receiver_id) throws SQLException {
        Statement statement = connection.createStatement();
        int requesterLocationID=0;
        int receiverLocationId=0;
        ResultSet resultSet = statement.executeQuery("Select location_id from locationMembers where user_id" +
                "=" + requester_id+";");
        while(resultSet.next()){
            requesterLocationID =  resultSet.getInt(1);
        }
        ResultSet rs = statement.executeQuery("Select location_id from locationMembers where user_id" +
                "=" + receiver_id+";");
        while(rs.next()){
            receiverLocationId = rs.getInt(1);
        }
        System.out.println(receiverLocationId+"ese minida");
        updateLocationId(receiver_id,requesterLocationID);
        updateLocationId(requester_id,receiverLocationId);
    }
    public void removeSimilars(int requester_id) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("Delete from changeLocationRequest where requester_id = "+requester_id+";");
        statement.execute("Delete from changeLocationRequest where receiver_id = "+requester_id+";");
    }
}
