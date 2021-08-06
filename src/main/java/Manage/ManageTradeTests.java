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
    public void testAddStudentToLocation() throws SQLException {
     //  manageTrade.addLocation("ბუნიკეთი");
       manageTrade.addStudentToLocation("gadik19@freeuni.edu.ge", "ბუნიკეთი");
       manageTrade.addStudentToLocation("gadik19@freeuni.edu.ge", "ბუნიკეთი");
       assertTrue(manageTrade.isLocation("ბუნიკეთი"));
       assertEquals(2, manageTrade.getNumStudents("ბუნიკეთი"));
       manageTrade.removeStudentFromLocation("gadik19@freeuni.edu.ge", "ბუნიკეთი");
       manageTrade.removeStudentFromLocation("gadik19@freeuni.edu.ge", "ბუნიკეთი");
       assertFalse(manageTrade.isLocation("ბუნიკეთი"));
    }

    @Test
    public void testRemoveStudentFromLocation() throws SQLException {
        manageTrade.addLocation("ბახმარო");
        assertTrue(manageTrade.isLocation("ბახმარო"));
        manageTrade.removeLocation("ბახმარო");
        assertFalse(manageTrade.isLocation("ბახმარო"));
    }

    @Test
    public void testNumStudents() throws SQLException {
        manageTrade.addLocation("ბახმარო");
        assertTrue(manageTrade.isLocation("ბახმარო"));
        manageTrade.removeLocation("ბახმარო");
        assertFalse(manageTrade.isLocation("ბახმარო"));
    }
}
