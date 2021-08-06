package Manage.HelperClasses;

public class Location {
    // private instance variables
    private final int id;
    private final String name;
    private final int numStudents;

    public Location(int id, String name, int numStudents) {
        this.id = id;
        this.name = name;
        this.numStudents = numStudents;
    }

    public int getLocId() {return id;}
    public String getLocName() {return name;}
    public int getLocNumStudents() {return numStudents;}
}
