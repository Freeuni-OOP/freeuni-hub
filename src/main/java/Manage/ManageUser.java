package Manage;


import DataBaseConnection.BaseConnector;
import Manage.Configurations.UserConfiguration;
import Manage.HelperClasses.UserById;

import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class ManageUser implements UserConfiguration {
    private static Connection con;
    private final BaseConnector bc;

    public ManageUser(BaseConnector bc) throws SQLException, ClassNotFoundException {
        this.bc = bc;
        con = bc.accessConnection();
    }


    public String changeProfilePic(String username, String img) throws SQLException {
        try {
            Statement stmt = con.createStatement();
            String query =  "update users set profile_pic = '" + img + "' where user_name = '" + username + "';";
            stmt.executeUpdate(query);
            return ALL_GOOD;
        }
        catch (Exception e) {
            e.printStackTrace();
            return NOT_GOOD;
        }
    }

    //---------------------------------------------------------------------------------------------------login part

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


    //------------------------------------------------------------------------------------------------registration part


    // check whether first name input is correct or not
    protected String isValidFirstName(String firstName) {
        if (firstName.isEmpty()) return EMPTY;
        int len = firstName.length();
        for (int i = 0; i < len; i++) // all letters should be lowercase latin

            if (!Character.isLetter(firstName.charAt(i)) || Character.isUpperCase(firstName.charAt(i)))
                return INCORRECT_FIRST_NAME;
        return CORRECT_FIRST_NAME;
    }

    // check whether last name input is correct or not
    protected String isValidLastName(String lastName) {
        if (lastName.isEmpty()) return EMPTY;
        if (lastName.length() < 4) return INCORRECT_LAST_NAME; // at least 4 length
        int len = lastName.length();
        for (int i = 0; i < len; i++) // all letters should be lowercase latin
            if (!Character.isLetter(lastName.charAt(i)) || Character.isUpperCase(lastName.charAt(i)))
                return INCORRECT_LAST_NAME;
        return CORRECT_LAST_NAME;
    }

    // only letters, digits and underscore
    protected String isValidUsername(String username) throws SQLException {
        if (username.isEmpty()) return EMPTY;
        int len = username.length();
        for (int i = 0; i < len; i++) {
            char curChar = username.charAt(i);
            if (Character.isLetter(curChar) || Character.isDigit(curChar)
                    || curChar == '_') continue; // those are ok
            return ILLEGAL_USERNAME;
        }
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from " + USERS_TABLE + ";");
        while (rs.next())
            if (rs.getString("user_name").equals(username))
                return USERNAME_EXISTS;
        return CORRECT_USERNAME;
    }

    // at least 1 big letter, 1 digit and length should be in interval: 4-16.
    protected String isValidPassword(String password) {
        if (password.isEmpty()) return EMPTY;
        int len = password.length();
        if (len < 4 || len > 16) return INCORRECT_PASSWORD_LENGTH;
        boolean hasDigit = false, hasBigLetter = false;
        for (int i = 0; i < len; i++) {
            char curChar = password.charAt(i);
            if (curChar == '_') continue; // underscore ok
            if (curChar <= 'Z' && curChar >= 'A') hasBigLetter = true;
            else if (curChar <= '9' && curChar >= '0') hasDigit = true;
            else if (curChar < 'a' || curChar > 'z') return ILLEGAL_PASSWORD;
        }
        if (!hasDigit || !hasBigLetter) return WRONG_FORMAT;
        return CORRECT_PASSWORD;
    }


    // helper methods for mail validation
    private String case22Helper(String mail, String firstName, String lastName) {
        for (int i = 0; i < 5; i++) { // first 5 letters must be letters
            if (!Character.isLetter(mail.charAt(i))) return INCORRECT_MAIL;
        }
        // next 2 symbols must be digits
        if (!Character.isDigit(mail.charAt(5))) return INCORRECT_MAIL;
        if (!Character.isDigit(mail.charAt(6))) return INCORRECT_MAIL;
        if (mail.charAt(7) != '@') return INCORRECT_MAIL; // next @
        if (!mail.substring(8).equals("freeuni.edu.ge")) return INCORRECT_MAIL; // next should be freeuni.edu.ge
        // now check coincidence (final check)
        if (!((firstName.charAt(0) + lastName.substring(0, 4)).equals(mail.substring(0, 5))))
            return INCORRECT_MAIL;

        return CORRECT_MAIL;
    }


    private String case23Helper(String mail, String firstName, String lastName) {
        for (int i = 0; i < 6; i++) { // first 5 letters must be letters
            if (!Character.isLetter(mail.charAt(i))) return INCORRECT_MAIL;
        }
        // next 2 symbols must be digits
        if (!Character.isDigit(mail.charAt(6))) return INCORRECT_MAIL;
        if (!Character.isDigit(mail.charAt(7))) return INCORRECT_MAIL;
        if (mail.charAt(8) != '@') return INCORRECT_MAIL; // next @
        if (!mail.substring(9).equals("freeuni.edu.ge")) return INCORRECT_MAIL; // next should be freeuni.edu.ge

        // now check coincidence (first name's first 2 symbols + last name's first 4)
        if (!((firstName.substring(0, 2) + lastName.substring(0, 4)).equals(mail.substring(0, 6))))
            return INCORRECT_MAIL;

        return CORRECT_MAIL;
    }

    // 7 or 8 letter + @ + freeuni.edu.ge (totally: 14 + 1 + 7/8 = 22/23)
    // e.g. gadik19@freeuni.edu.ge = 22
    protected String isValidMail(String mail, String firstName, String lastName) throws SQLException {
        if (mail.isEmpty()) return EMPTY;
        int len = mail.length();
        if (len != 22 && len != 23) return INCORRECT_MAIL;
        String check22 = case22Helper(mail, firstName, lastName);
        String check23 = case23Helper(mail, firstName, lastName);

        if (len == 22 && !check22.equals(CORRECT_MAIL)) return check22;
        if (len == 23 && !check23.equals(CORRECT_MAIL)) return check23;

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from " + USERS_TABLE + ";");

        while (rs.next()) {
            if (rs.getString("email").equals(mail)) return MAIL_EXISTS;
        }

        return CORRECT_MAIL; // everything is ok
    }

    // check whether input is valid or not
    public String isValidInput(String firstName, String lastName, String username, String password, String mail) throws SQLException {
        String firstNameMessage = isValidFirstName(firstName);
        if (!firstNameMessage.equals(CORRECT_FIRST_NAME)) return firstNameMessage; // first check first name
        String lastNameMessage = isValidLastName(lastName);
        if (!lastNameMessage.equals(CORRECT_LAST_NAME)) return lastNameMessage; // check last name
        String passwordMessage = isValidPassword(password);
        if (!passwordMessage.equals(CORRECT_PASSWORD)) return passwordMessage; // check password
        String usernameMessage = isValidUsername(username);
        if (!usernameMessage.equals(CORRECT_USERNAME)) return usernameMessage; // check username
        // finally mail
        String mailMessage = isValidMail(mail, firstName, lastName);
        if (!mailMessage.equals(CORRECT_MAIL)) return mailMessage; // check mail
        return ALL_GOOD; // wow, everything is ok
    }

    // method adds new user into the table
    public void addUser(String firstName, String lastName, String username, String password, String mail) throws SQLException {
        //adds into USERS_TABLE
        Statement stmt = con.createStatement();
        stmt.execute("insert into " + USERS_TABLE + "(first_name, last_name, user_name, password, email)" +
                " values ('" + firstName + "' , '" + lastName
                + "' , '" + username + "' , '" + password + "' , '" + mail + "');");

        // adds into USERS_INFO_TABLE
        UserById ubi = new UserById(bc);
        int id = ubi.getIdByUsername(username);
        stmt.execute("insert into " + USERS_INFO_TABLE + "(user_id, user_name, user_last_name)" +
                " values ('" + id + "' , '" + username + "' , '" + lastName + "');");
    }

    // method adds new user into the table
    public void addUserWithId(int id, String firstName, String lastName,
                              String username, String password, String mail) throws SQLException {
        Statement stmt = con.createStatement();
        stmt.execute("insert into " + USERS_TABLE + "(id, first_name, last_name, user_name, password, email)" +
                " values ('" + id + "' , '" + firstName + "' , '" + lastName
                + "' , '" + username + "' , '" + password + "' , '" + mail + "');");

        // adds into USERS_INFO_TABLE
        stmt.execute("insert into " + USERS_INFO_TABLE + "(user_id, user_name, user_last_name)" +
                " values ('" + id + "' , '" + username + "' , '" + lastName + "');");
    }


    // method removes user from users table (remember mail is unique)
    public void removeUser(String mail) throws SQLException {
        Statement stmt = con.createStatement();
        UserById ubi = new UserById(bc);
        int user_id = ubi.getIdByMail(mail);
        stmt.execute("delete from " + USERS_INFO_TABLE + " where user_id = '" + user_id + "';"); // at first from info table
        stmt.execute("delete from " + USERS_TABLE + " where email = '" + mail + "';");
    }

    public String getProfilePic(String username) {
        String defaultProfilePic = "../../Images/UserImages/default.png";
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("select profile_pic from " + USERS_TABLE + " where user_name = '" + username + "';");


            if (rs.next()) {
                String  profilePic = rs.getString(1);

                return profilePic != null ? profilePic : defaultProfilePic;
            } else {
                return defaultProfilePic;
            }
        }
        catch (Exception ex) {
            return defaultProfilePic;
        }
    }

    public ArrayList<String> getUserInfo(int user_id) throws SQLException {
        // returns user info: firstName, lastName, username, password, mail
        Statement stmt = con.createStatement();
        ResultSet rs1 = stmt.executeQuery("select * from " + USERS_TABLE + ";");

        ArrayList<String> res = new ArrayList<>(); // final result

        while (rs1.next()) {
            if (rs1.getInt("id") == user_id) {
                res.add(rs1.getString("first_name"));
                res.add(rs1.getString("last_name"));
                res.add(rs1.getString("user_name"));
                res.add(rs1.getString("password"));
                res.add(rs1.getString("email"));
                break;
            }
        }

        //-----------------------------now visit USERS_INFO_TABLE

        ResultSet rs2 = stmt.executeQuery("select * from " + USERS_INFO_TABLE + ";");


        while (rs2.next()) {
            if (rs2.getInt("user_id") == user_id) {
                res.add(rs2.getString("course"));
                int courseNum = rs2.getInt("courseNum");
                switch (courseNum) { // convert course int -> string
                    case 1:
                        res.add("I");
                        break;
                    case 2:
                        res.add("II");
                        break;
                    case 3:
                        res.add("III");
                        break;
                    case 4:
                        res.add("IV");
                        break;
                    case 5:
                        res.add("IV+");
                        break;
                    default:
                        res.add("არაა მითითებული");
                        break;
                }
                res.add(rs2.getString("sqesi"));
                break;
            }
        }

        return res;
    }


    // to update user by id
    public void updateUser(int id, String newUsername, String sex, String course, int courseNum, String newPassword) throws SQLException {
        Statement stmt = con.createStatement();
        stmt.executeUpdate("update " + USERS_INFO_TABLE + " " +
                "set user_name = '" + newUsername + "', sqesi = '" + sex +
                "', course = '" + course + "', courseNum = " + courseNum + " where user_id = '" + id + "';");

        stmt.executeUpdate("update " + USERS_TABLE + " " +
                "set user_name = '" + newUsername + "' , password = '" + newPassword + "' where id = " + id + ";");
    }


    //-----------------------------------------------------------------------------------------------other features

    public ArrayList<String> allUsers() throws SQLException {
        ArrayList<String> userList = new ArrayList<>();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from " + USERS_TABLE + ";");
        while (rs.next())
            userList.add(rs.getString("user_name"));
        return userList;
    }

    public void printUsernames() throws SQLException { // just printing users
        for (String user : this.allUsers())
            System.out.println(user);
    }


}