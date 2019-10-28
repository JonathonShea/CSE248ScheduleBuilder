package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import model.Course;

public class CourseFileLoader {
    private static ArrayList<Course> courses= new ArrayList<>();

    public static ArrayList<Course> getCoursesFromFile(InputStream courseFile) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(courseFile));
        String name = "";
        String description = "";
        String number = "";
        String credits = "";
        String nextLine = "";
        int index = 0;
        while ((nextLine = br.readLine()) != null){
            int indexMod = index % 5;
            switch (indexMod){
                case 0:
                    number = nextLine;
                    break;
                case 1:
                    name = nextLine;
                    break;
                case 2:
                    description = nextLine;
                    break;
                case 3:
                    credits = nextLine;
                    break;
                case 4:
                    courses.add(new Course(number, name, description, credits));
                    break;

            }
            index++;
        }
        return courses;
    }
}
