package model;

public class Course {
    private String courseNumber;
    private String courseTitle;
    private String courseDescription;
    private String credits;

    public Course(String courseNumber, String courseTitle, String courseDescription, String credits) {
        this.courseNumber = courseNumber;
        this.courseTitle = courseTitle;
        this.courseDescription = courseDescription;
        this.credits = credits;
    }

    public String getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public String getCredits() {
        return credits;
    }

    public void setCredits(String credits) {
        this.credits = credits;
    }
}
