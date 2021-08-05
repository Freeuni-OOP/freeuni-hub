package Manage;

public class User {
    String userName;
    String userLastName;
    String sqesi;
    String course;
    public User(String userName,String userLastName, String sqesi, String course){
        this.userName = userName;
        this.userLastName = userLastName;
        this.sqesi = sqesi;
        this.course = course;
    }

    public String getUserName(){
        return userName;
    }

    public String getUserLastName(){
        return userLastName;
    }
    public String getSqesi(){
        return sqesi;
    }
    public String getCourse(){
        return course;
    }
}
