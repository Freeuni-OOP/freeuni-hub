package Manage.HelperClasses;

public class User {
    String userFirstName;
    String userName;
    String userLastName;
    String sex;
    String course;
    int id;
    public User(int id, String userFirstName, String userLastName,String userName, String sex, String course){
        this.userFirstName=userFirstName;
        this.userName = userName;
        this.userLastName = userLastName;
        this.sex = sex;
        this.course = course;
        this.id = id;
    }

    public String getUserFirstName(){
        return userFirstName;
    }
    public String getUserName(){
        return userName;
    }
    public int getId(){
        return  id;
    }
    public String getUserLastName(){
        return userLastName;
    }
    public String getSex(){
        return sex;
    }
    public String getCourse(){
        return course;
    }
}
