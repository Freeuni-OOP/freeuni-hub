package Manage;


import DataBaseConnection.BaseConnector;
import Manage.Configurations.UserConfiguration;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class ManageUserTests implements UserConfiguration {
    BaseConnector bc;
    ManageUser manageUser;

    @Before
    public void setUp() throws SQLException, ClassNotFoundException {
        bc = new BaseConnector();
        manageUser = new ManageUser(bc);
    }

    @Test
    public void testFirstName() {
        assertEquals(EMPTY, manageUser.isValidFirstName(""));
        assertEquals(CORRECT_FIRST_NAME, manageUser.isValidFirstName("giorgi"));
        assertEquals(CORRECT_FIRST_NAME, manageUser.isValidFirstName("luka"));
        assertEquals(INCORRECT_FIRST_NAME, manageUser.isValidFirstName("Luka"));
        assertEquals(INCORRECT_FIRST_NAME, manageUser.isValidFirstName("lu2ka"));
        assertEquals(INCORRECT_FIRST_NAME, manageUser.isValidFirstName("ad%"));
        assertEquals(INCORRECT_FIRST_NAME, manageUser.isValidFirstName("asd$"));
        assertEquals(INCORRECT_FIRST_NAME, manageUser.isValidFirstName("#"));
        assertEquals(INCORRECT_FIRST_NAME, manageUser.isValidFirstName("!faf*&"));
        assertEquals(INCORRECT_FIRST_NAME, manageUser.isValidFirstName("?vau"));
    }

    @Test
    public void testLastName() {
        assertEquals(EMPTY, manageUser.isValidLastName(""));
        assertEquals(CORRECT_LAST_NAME, manageUser.isValidLastName("adikashvili"));
        assertEquals(CORRECT_LAST_NAME, manageUser.isValidLastName("jashi"));
        assertEquals(INCORRECT_LAST_NAME, manageUser.isValidLastName("Ambroladze"));
        assertEquals(INCORRECT_LAST_NAME, manageUser.isValidLastName("is13"));
        assertEquals(INCORRECT_LAST_NAME, manageUser.isValidLastName("f"));
        assertEquals(INCORRECT_LAST_NAME, manageUser.isValidLastName("adi"));
        assertEquals(INCORRECT_LAST_NAME, manageUser.isValidLastName("a%d$"));
        assertEquals(INCORRECT_LAST_NAME, manageUser.isValidLastName("!fA#Sf*&"));
        assertEquals(INCORRECT_LAST_NAME, manageUser.isValidLastName("?vsdau"));
    }

    @Test
    public void testUserName() throws SQLException {
        assertEquals(EMPTY, manageUser.isValidUsername(""));
        assertEquals(ILLEGAL_USERNAME, manageUser.isValidUsername("G%daF"));
        assertEquals(ILLEGAL_USERNAME, manageUser.isValidUsername("adik!"));
        assertEquals(ILLEGAL_USERNAME, manageUser.isValidUsername("adika?$"));
        assertEquals(CORRECT_USERNAME, manageUser.isValidUsername("GsdaF"));
        assertEquals(CORRECT_USERNAME, manageUser.isValidUsername("gio_adika2001"));
        assertEquals(CORRECT_USERNAME, manageUser.isValidUsername("Adika3"));
    }

    @Test
    public void testPassword() {
        assertEquals(EMPTY, manageUser.isValidPassword(""));
        assertEquals(INCORRECT_PASSWORD_LENGTH, manageUser.isValidPassword("ad"));
        assertEquals(INCORRECT_PASSWORD_LENGTH, manageUser.isValidPassword("addasdafasdafafadssad13"));
        assertEquals(WRONG_FORMAT, manageUser.isValidPassword("adika"));
        assertEquals(ILLEGAL_PASSWORD, manageUser.isValidPassword("adika%!"));
        assertEquals(CORRECT_PASSWORD, manageUser.isValidPassword("Gio_ado1"));
        assertEquals(CORRECT_PASSWORD, manageUser.isValidPassword("GioAdika17"));
    }

    @Test
    public void testMail() throws SQLException {
        assertEquals(EMPTY, manageUser.isValidMail("", "tariel", "mklavadze"));
        assertEquals(INCORRECT_MAIL, manageUser.isValidMail("ack19@freeuni.edu.ge", "akaki",
                "chukhua"));
        assertEquals(INCORRECT_MAIL, manageUser.isValidMail("gadik19@freeuni.edu.ge", "luka",
                "macharashvili"));
        assertEquals(INCORRECT_MAIL, manageUser.isValidMail("gadik19f3reeuni.edu.ge", "luka",
                "macharashvili"));
        assertEquals(INCORRECT_MAIL, manageUser.isValidMail("giadik19@freeuni.edu.ge", "luka",
                "macharashvili"));
        assertEquals(CORRECT_MAIL, manageUser.isValidMail("giadik19@freeuni.edu.ge", "giorgi",
                "adikashvili"));
        assertEquals(CORRECT_MAIL, manageUser.isValidMail("achuk19@freeuni.edu.ge", "akaki",
                "chukhua"));
        assertEquals(CORRECT_MAIL, manageUser.isValidMail("niglun20@freeuni.edu.ge", "nika",
                "glunchadze"));
        assertEquals(CORRECT_MAIL, manageUser.isValidMail("achuk19@freeuni.edu.ge", "akaki",
                "chukhua"));
    }


    @Test
    public void testInputValidity() throws SQLException {
        assertEquals(ALL_GOOD, manageUser.isValidInput("giorgi", "adikashvili",
                "gioado7", "Gioadika10", "giadik19@freeuni.edu.ge"));
        assertEquals(ALL_GOOD, manageUser.isValidInput("nika", "glunchadze",
                "niglun7", "nika_Glun7", "niglun20@freeuni.edu.ge"));
        assertEquals(INCORRECT_MAIL, manageUser.isValidInput("g", "adikashvili",
                "gioado7", "Gioadika7", "gad19@freeuni.edu.ge"));
        assertEquals(EMPTY, manageUser.isValidInput("", "",
                "gioado7", "Gioadika7", "gadik19@freeuni.edu.ge"));
        assertEquals(INCORRECT_PASSWORD_LENGTH, manageUser.isValidInput("giorgi", "adikashvili",
                "gioado7", "Gioadika7sdasdadsd", "gadik19@freeuni.edu.ge"));
        assertEquals(WRONG_FORMAT, manageUser.isValidInput("giorgi", "adikashvili",
                "gioado7", "adika", "gadik19@freeuni.edu.ge"));
        assertEquals(INCORRECT_FIRST_NAME, manageUser.isValidInput("giorgi%!!", "adikashvili",
                "gioado7", "Gioadika7", "gadik19@freeuni.edu.ge"));
        assertEquals(INCORRECT_FIRST_NAME, manageUser.isValidInput("giorg_13i", "adikashvili",
                "gioado7", "Gioadika7", "gadik19@freeuni.edu.ge"));
        assertEquals(INCORRECT_LAST_NAME, manageUser.isValidInput("giorgi", "adikashvili1",
                "gioado7", "Gioadika7", "gadik19@freeuni.edu.ge"));
        assertEquals(INCORRECT_MAIL, manageUser.isValidInput("giorgi", "adikashvili",
                "gioado7", "Gioadika7", "gadik@freeuni.edu.ge"));

    }


    @Test
    public void testAddRemoveUser() throws SQLException {
        // you should try this for other user
        assertEquals(NOT_FOUND, manageUser.isValidUser("kaci", "Kacuri123"));
        manageUser.addUser("vigac", "vigacashvili", "kaci", "Kacuri123",
                "vviga17@freeuni.edu.ge");
        assertEquals(FOUND, manageUser.isValidUser("kaci", "Kacuri123"));
        manageUser.removeUser("vviga17@freeuni.edu.ge");
        // now add with id
        assertEquals(NOT_FOUND, manageUser.isValidUser("kaci", "Kacuri123"));
        manageUser.addUserWithId(50, "vigac", "vigacashvili", "kaci", "Kacuri123",
                "vviga17@freeuni.edu.ge");
        assertEquals(FOUND, manageUser.isValidUser("kaci", "Kacuri123"));
        manageUser.removeUser("vviga17@freeuni.edu.ge");
    }


    @Test
    public void testGetInfo() throws SQLException {
        assertEquals(NOT_FOUND, manageUser.isValidUser("kaci", "Kacuri123"));
        manageUser.addUserWithId(50, "vigac", "vigacashvili", "kaci", "Kacuri123",
                "vviga17@freeuni.edu.ge");
        assertEquals(FOUND, manageUser.isValidUser("kaci", "Kacuri123"));

        ArrayList<String> info = manageUser.getUserInfo(50);

        assertEquals(Arrays.asList("vigac", "vigacashvili", "kaci", "Kacuri123", "vviga17@freeuni.edu.ge",
                null, "უცნობია", null), info);

        manageUser.removeUser("vviga17@freeuni.edu.ge");
    }


    @Test
    public void testUpdateUser() throws SQLException {
        assertEquals(NOT_FOUND, manageUser.isValidUser("kaci", "Kacuri123"));
        manageUser.addUserWithId(50, "vigac", "vigacashvili", "kaci", "Kacuri123",
                "vviga17@freeuni.edu.ge");
        assertEquals(FOUND, manageUser.isValidUser("kaci", "Kacuri123"));

        // now update user information
        manageUser.updateUser(50, "kaci1", "male", "macs", 1, "Kacuri1234");

        Connection con = bc.accessConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from " + USERS_INFO_TABLE + ";");

        while (rs.next()) {
            if (rs.getInt("user_id") == 50) {
                assertEquals("kaci1", rs.getString("user_name"));
                assertEquals("male", rs.getString("sqesi"));
                assertEquals("macs", rs.getString("course"));
                assertEquals(1, rs.getInt("courseNum"));
                break;
            }
        }


        ResultSet rs2 = stmt.executeQuery("select * from " + USERS_TABLE + ";");

        while (rs2.next()) {
            if (rs2.getInt("id") == 50) {
                assertEquals("Kacuri1234", rs2.getString("password"));
                break;
            }
        }


        manageUser.removeUser("vviga17@freeuni.edu.ge");
    }


    @Test
    public void testUniqueUsername() throws SQLException {
        assertEquals(ALL_GOOD, manageUser.isValidInput("keith", "markovic", "naf_fly",
                "vaL1d", "kmark15@freeuni.edu.ge"));
        manageUser.addUser("keith", "markovic", "naf_fly", "vaL1d", "kmark15@freeuni.edu.ge");
        assertEquals(USERNAME_EXISTS, manageUser.isValidInput("keith", "markovic", "naf_fly",
                "vaL1d", "kmark15@freeuni.edu.ge"));
        manageUser.removeUser("kmark15@freeuni.edu.ge");
        assertEquals(ALL_GOOD, manageUser.isValidInput("keith", "markovic", "naf_fly",
                "vaL1d", "kmark15@freeuni.edu.ge"));
    }


}
