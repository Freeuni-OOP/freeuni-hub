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
}
