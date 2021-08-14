package Manage.HelperClasses;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LocationTests {
    Location loc1, loc2;

    @Before
    public void setUp() {
        loc1 = new Location(5, "baxmaro", 33);
        loc2 = new Location(7, "fari", 10);
    }


    @Test
    public void testGetLocId() {
        assertEquals(5, loc1.getLocId());
        assertEquals(7, loc2.getLocId());
    }


    @Test
    public void testGetLocName() {
        assertEquals("baxmaro", loc1.getLocName());
        assertEquals("fari", loc2.getLocName());
    }


    @Test
    public void testGetLocNumStudents() {
        assertEquals(33, loc1.getLocNumStudents());
        assertEquals(10, loc2.getLocNumStudents());
    }
}
