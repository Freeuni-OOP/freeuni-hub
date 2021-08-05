package Manage.HelperClasses;

public class User {
    String userName;
    String userLastName;
    String sex;
    String course;
    int id;
    public User(int id, String userName, String userLastName, String sex, String course){
        this.userName = userName;
        this.userLastName = userLastName;
        this.sex = sex;
        this.course = course;
        this.id = id;
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
