package Manage;



import DataBaseConnection.BaseConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class ManageUser implements Configuration {
    private static Connection con;

    public ManageUser(BaseConnector bc) throws SQLException, ClassNotFoundException {
        con = bc.accessConnection();
    }

    // method adds new user into the table
    public void addUser(String username, String password) throws SQLException {
        Statement stmt = con.createStatement();
        stmt.execute("insert into " + USERS_TABLE + " values ('" + username + "' , '" + password + "');");
    }

    // checks whether user login is correct or not
    public String isValidUser(String username, String password) throws SQLException {
        if (username.isEmpty() || password.isEmpty()) return EMPTY;
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from " + USERS_TABLE + ";");
        while (rs.next())
            if (rs.getString("user_name").equals(username)
                && rs.getString("password").equals(password)) return FOUND;
        return NOT_FOUND;
    }

    // at least 1 big letter, 1 digit and length should be in interval: 4-16.
    private boolean isValidPassword(String password) {
        int len = password.length();
        if (len < 4 || len > 16) return false;
        boolean hasDigit = false, hasBigLetter = false;
        for (int i = 0; i < len; i++) {
            char curChar = password.charAt(i);
            if (curChar <= 'Z' && curChar >= 'A') hasBigLetter = true;
            if (curChar <= '9' && curChar >= '0') hasDigit = true;
        }
        return hasDigit && hasBigLetter;
    }

    // check whether input is valid or not
    public boolean isValidInput(String username, String password) {
        return (!username.equals("") && isValidPassword(password));
    }

    public ArrayList<String> allUsers() throws SQLException {
        ArrayList<String> userList = new ArrayList<>();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from " + USERS_TABLE + ";");
        while (rs.next())
            userList.add(rs.getString("user_name"));
        return userList;
    }

    public void printUsernames(ArrayList<String> users) {
        for (String user : users)
            System.out.println(user);
    }


}