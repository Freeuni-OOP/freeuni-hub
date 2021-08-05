package Manage;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserTest {

    @Test
    public void testFirstName(){
        User user = new User("Luka",null,null,null);
        assertEquals("Luka",user.getUserName());
        user = new User("Giorgi",null,null,null);
        assertEquals("Giorgi",user.getUserName());
        user = new User("Davit","Khvedo",null,null);
        assertEquals("Davit",user.getUserName());
        user = new User("Luke","Samkharadze","Mamrobiti",null);
        assertEquals("Luke",user.getUserName());
        user= new User("Luka","Macho","Mamrobiti","MACSE");
        assertEquals("Luka",user.getUserName());
        user = new User(null,null,null,null);
        assertEquals(null,user.getUserName());
    }
    @Test
    public void testLastName(){
        User user = new User(null,"Macho",null,null);
        assertEquals("Macho",user.getUserLastName());
        user = new User("Giorgi","Adika",null,null);
        assertEquals("Adika",user.getUserLastName());
        user = new User("Davit","Khvedo",null,null);
        assertEquals("Khvedo",user.getUserLastName());
        user = new User("Luke","Samkharadze","Mamrobiti",null);
        assertEquals("Samkharadze",user.getUserLastName());
        user= new User("Luka","Macharashvili","Mamrobiti","MACSE");
        assertEquals("Macharashvili",user.getUserLastName());
        user = new User(null,null,null,null);
        assertEquals(null,user.getUserLastName());
    }
    @Test
    public void testGetSex(){
        User user = new User(null,"Macho","Mamrobiti","Martva");
        assertEquals("Mamrobiti",user.getSex());
        user = new User("Giorgi","Adika","Mamrobiti","Engineering");
        assertEquals("Mamrobiti",user.getSex());
        user = new User("Davit","Khvedo",null,null);
        assertEquals(null,user.getSex());
        user = new User("Elene","Samkharadze","Mdedrobiti",null);
        assertEquals("Mdedrobiti",user.getSex());
        user= new User(null,"Macharashvili","Other","MACSE");
        assertEquals("Other",user.getSex());
        user = new User(null,null,null,null);
        assertEquals(null,user.getSex());
    }

    @Test
    public void getCourseTest(){
        User user = new User(null,"Macho",null,"Martva");
        assertEquals("Martva",user.getCourse());
        user = new User("Giorgi","Adika",null,"Engineering");
        assertEquals("Engineering",user.getCourse());
        user = new User("Davit","Khvedo",null,"ESM");
        assertEquals("ESM",user.getCourse());
        user = new User("Luke","Samkharadze","Mamrobiti","VAADS");
        assertEquals("VAADS",user.getCourse());
        user= new User("Luka","Macharashvili","Mamrobiti","MACSE");
        assertEquals("MACSE",user.getCourse());
        user = new User(null,null,null,null);
        assertEquals(null,user.getCourse());
    }
}
