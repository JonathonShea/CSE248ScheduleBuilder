package model;

import java.util.ArrayList;

public class Semester {
    private String name;
    private ArrayList<Course> courses;

    public Semester(String name){
        this.name = name;
        courses = new ArrayList<>();
    }

    public Semester(String name, ArrayList courses){
        this.name = name;
        this.courses = courses;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }
    @Override
    public String toString(){
        return this.name;
    }
}
