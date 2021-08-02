package Manage;


import DataBaseConnection.BaseConnector;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class ManageUserTests implements Configuration {
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
    public void testUserName() {
        assertEquals(EMPTY, manageUser.isValidUsername(""));
        assertEquals(ILLEGAL_SYMBOL, manageUser.isValidUsername("G%daF"));
        assertEquals(ILLEGAL_SYMBOL, manageUser.isValidUsername("adik!"));
        assertEquals(ILLEGAL_SYMBOL, manageUser.isValidUsername("adika?$"));
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
        assertEquals(ILLEGAL_SYMBOL, manageUser.isValidPassword("adika%!"));
        assertEquals(CORRECT_PASSWORD, manageUser.isValidPassword("Gio_ado1"));
        assertEquals(CORRECT_PASSWORD, manageUser.isValidPassword("GioAdika17"));
    }

    @Test
    public void testMail() {
        assertEquals(EMPTY, manageUser.isValidMail("", "tariel", "mklavadze"));
        assertEquals(INCORRECT_MAIL, manageUser.isValidMail("ack19@freeuni.edu.ge", "akaki",
                "chukhua"));
        assertEquals(INCORRECT_MAIL, manageUser.isValidMail("gadik19@freeuni.edu.ge", "luka",
                "macharashvili"));
        assertEquals(INCORRECT_MAIL, manageUser.isValidMail("gadik19f3reeuni.edu.ge", "luka",
                "macharashvili"));
        assertEquals(CORRECT_MAIL, manageUser.isValidMail("gadik19@freeuni.edu.ge", "giorgi",
                "adikashvili"));
        assertEquals(CORRECT_MAIL, manageUser.isValidMail("achuk19@freeuni.edu.ge", "akaki",
                "chukhua"));
        assertEquals(CORRECT_MAIL, manageUser.isValidMail("niglun20@freeuni.edu.ge", "nika",
                "glunchadze"));
        assertEquals(CORRECT_MAIL, manageUser.isValidMail("achuk19@freeuni.edu.ge", "akaki",
                "chukhua"));
    }



    @Test
    public void testInputValidity() {
        assertEquals(ALL_GOOD, manageUser.isValidInput("giorgi", "adikashvili",
                "gioado7", "Gioadika7", "gadik19@freeuni.edu.ge"));
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
    public void testAddingUser() throws SQLException{
        // you should try this for other user
        assertEquals(NOT_FOUND, manageUser.isValidUser("blasdasd12", "kaiKacoVax"));
        manageUser.addUser("miua", "mianshvili", "blasdasd12", "kaiKacoVax",
                "mmian19@freeuni.edu.ge");
        assertEquals(FOUND, manageUser.isValidUser("blasdasd12", "kaiKacoVax"));
    }



}
