package Manage.HelperClasses;


import Manage.HelperClasses.User;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserTest {

    @Test
    public void testFirstName(){
        User user = new User(1,"Luka",null,null,null,null);
        assertEquals("Luka",user.getUserFirstName());
        user = new User(1,"Giorgi",null,null,null,null);
        assertEquals("Giorgi",user.getUserFirstName());
        user = new User(1,"Davit","Khvedo",null,null,null);
        assertEquals("Davit",user.getUserFirstName());
        user = new User(1,"Luke","Samkharadze",null,"Mamrobiti",null);
        assertEquals("Luke",user.getUserFirstName());
        user= new User(1,"Luka","Macho",null,"Mamrobiti","MACSE");
        assertEquals("Luka",user.getUserFirstName());
        user = new User(1,null,null,null,null,null);
        assertEquals(null,user.getUserFirstName());
    }
    @Test
    public void testLastName(){
        User user = new User(1,null,"Macho",null,null,null);
        assertEquals("Macho",user.getUserLastName());
        user = new User(1,"Giorgi","Adika",null,null,null);
        assertEquals("Adika",user.getUserLastName());
        user = new User(1,"Davit","Khvedo",null,null,null);
        assertEquals("Khvedo",user.getUserLastName());
        user = new User(1,"Luke","Samkharadze","es","Mamrobiti",null);
        assertEquals("Samkharadze",user.getUserLastName());
        user= new User(1,"Luka","Macharashvili",null,"Mamrobiti","MACSE");
        assertEquals("Macharashvili",user.getUserLastName());
        user = new User(1,null,null,null,null,null);
        assertEquals(null,user.getUserLastName());
    }
    @Test
    public void testGetSex(){
        User user = new User(1,null,"Macho",null,"Mamrobiti","Martva");
        assertEquals("Mamrobiti",user.getSex());
        user = new User(1,"Giorgi","Adika",null,"Mamrobiti","Engineering");
        assertEquals("Mamrobiti",user.getSex());
        user = new User(1,"Davit","Khvedo",null,null,null);
        assertEquals(null,user.getSex());
        user = new User(1,"Elene","Samkharadze","name","Mdedrobiti",null);
        assertEquals("Mdedrobiti",user.getSex());
        user= new User(1,null,"Macharashvili",null,"Other","MACSE");
        assertEquals("Other",user.getSex());
        user = new User(1,null,null,null,null,null);
        assertEquals(null,user.getSex());
    }

    @Test
    public void getCourseTest(){
        User user = new User(1,null,"Macho",null,null,"Martva");
        assertEquals("Martva",user.getCourse());
        user = new User(1,"Giorgi","Adika",null,null,"Engineering");
        assertEquals("Engineering",user.getCourse());
        user = new User(1,"Davit","Khvedo",null,null,"ESM");
        assertEquals("ESM",user.getCourse());
        user = new User(1,"Luke","Samkharadze",null,"Mamrobiti","VAADS");
        assertEquals("VAADS",user.getCourse());
        user= new User(1,"Luka","Macharashvili",null,"Mamrobiti","MACSE");
        assertEquals("MACSE",user.getCourse());
        user = new User(1,null,null,null,null,null);
        assertEquals(null,user.getCourse());
    }

    @Test
    public void getId(){
        User user = new User(1,null,"Macho",null,null,"Martva");
        assertEquals(1,user.getId());
        user = new User(100,"Giorgi","Adika",null,null,"Engineering");
        assertEquals(100,user.getId());
        user = new User(-1,"Davit","Khvedo",null,null,"ESM");
        assertEquals(-1,user.getId());
    }
}
