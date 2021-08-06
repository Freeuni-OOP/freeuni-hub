package Manage;


import DataBaseConnection.BaseConnector;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class ManageTradeTests {
    BaseConnector bc;
    ManageTrade manageTrade;

    @Before
    public void setUp() throws SQLException, ClassNotFoundException {
        bc = new BaseConnector();
        manageTrade = new ManageTrade(bc);
    }

    @Test
    public void testAddIsLocation() throws SQLException {
        assertFalse(manageTrade.isLocation("ბახმარო"));
        manageTrade.addLocation("ბახმარო");
        assertTrue(manageTrade.isLocation("ბახმარო"));
        manageTrade.removeLocation("ბახმარო");
        assertFalse(manageTrade.isLocation("ფარი"));
        manageTrade.addLocation("ფარი");
        assertTrue(manageTrade.isLocation("ფარი"));
        manageTrade.removeLocation("ფარი");
    }

    @Test
    public void testRemoveLocation() throws SQLException {
        manageTrade.addLocation("ბახმარო");
        assertTrue(manageTrade.isLocation("ბახმარო"));
        manageTrade.removeLocation("ბახმარო");
        assertFalse(manageTrade.isLocation("ბახმარო"));
    }

    @Test
    public void testAddStudentToLocation() throws SQLException, ClassNotFoundException {
        ManageUser mu = new ManageUser(bc);
        mu.addUserWithId(150, "badri", "shubladze", "badri123", "Badri123",
                "bshub16@freeuni.edu.ge");
        mu.addUserWithId(151, "badri", "shubladze", "bbadri123", "Badri123",
                "bashub16@freeuni.edu.ge");

        manageTrade.addLocation("ბუნიკეთი");
        manageTrade.addStudentToLocation("bshub16@freeuni.edu.ge", "ბუნიკეთი");
        manageTrade.addStudentToLocation("bashub16@freeuni.edu.ge", "ბუნიკეთი");
        assertTrue(manageTrade.isLocation("ბუნიკეთი"));
        assertEquals(2, manageTrade.getNumStudents("ბუნიკეთი"));

        manageTrade.removeStudentFromLocation("bshub16@freeuni.edu.ge", "ბუნიკეთი");
        manageTrade.removeStudentFromLocation("bashub16@freeuni.edu.ge", "ბუნიკეთი");
        assertEquals(0, manageTrade.getNumStudents("ბუნიკეთი"));

        mu.removeUser("bshub16@freeuni.edu.ge");
        mu.removeUser("bashub16@freeuni.edu.ge");

        manageTrade.removeLocation("ბუნიკეთი");
    }

    @Test
    public void testRemoveStudentFromLocation() throws SQLException {
        manageTrade.addLocation("ბუნიკეთი");
        manageTrade.addStudentToLocation("gadik19@freeuni.edu.ge", "ბუნიკეთი");
        assertTrue(manageTrade.isLocation("ბუნიკეთი"));
        assertEquals(1, manageTrade.getNumStudents("ბუნიკეთი"));
        manageTrade.removeStudentFromLocation("gadik19@freeuni.edu.ge", "ბუნიკეთი");
        manageTrade.removeLocation("ბუნიკეთი");
        assertFalse(manageTrade.isLocation("ბუნიკეთი"));
    }

    @Test
    public void testNumStudents() throws SQLException {
        manageTrade.addLocation("ბუნიკეთი");
        manageTrade.addStudentToLocation("gadik19@freeuni.edu.ge", "ბუნიკეთი");
        manageTrade.addStudentToLocation("nsali19@freeuni.edu.ge", "ბუნიკეთი");
        assertTrue(manageTrade.isLocation("ბუნიკეთი"));
        assertEquals(2, manageTrade.getNumStudents("ბუნიკეთი"));
        manageTrade.removeStudentFromLocation("gadik19@freeuni.edu.ge", "ბუნიკეთი");
        assertEquals(1, manageTrade.getNumStudents("ბუნიკეთი"));
        manageTrade.removeStudentFromLocation("nsali19@freeuni.edu.ge", "ბუნიკეთი");
        assertEquals(0, manageTrade.getNumStudents("ბუნიკეთი"));
        assertTrue(manageTrade.isLocation("ბუნიკეთი"));
        manageTrade.removeLocation("ბუნიკეთი");
        assertFalse(manageTrade.isLocation("ბუნიკეთი"));
    }
}
