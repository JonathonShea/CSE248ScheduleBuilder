package com.example.csecoursescheduler;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import activities.SemesterSpinnerActivity;
import adapter.CourseAdapter;
import model.Course;
import model.Semester;
import utils.CourseFileLoader;

public class MainActivity extends AppCompatActivity {
    ArrayList<Course> courses;
    ArrayList<Course> takenCourses;
    ListView courseList;
    ListView takenCourseList;
    int selectedCourse;
    static HashMap<String, Semester> semesters;
    Spinner dropdown;
    private static final String COURSE_FILE = "courses.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createCourses();
        createListViews();
        initializeButtons();
        initializeSemesters();
        initializeSpinner();
        setTitle("CSE SCHEDULE BUILDER");
    }






    private void createCourses(){
        AssetManager mgr = getAssets();
        try {
            InputStream courseFile = mgr.open(COURSE_FILE);
            courses = CourseFileLoader.getCoursesFromFile(courseFile);

        } catch (Exception e) {
            e.printStackTrace();
        }
        takenCourses = new ArrayList<>();
    }

    private void createListViews(){
        createUntakenListView();
        createTakenListView();
    }

    private void createUntakenListView(){
        courseList = (ListView) findViewById(R.id.untaken_courses);
        createListView(courseList, courses);
    }

    private void createTakenListView(){
        takenCourseList = (ListView) findViewById(R.id.taken_courses);
        createListView(takenCourseList, takenCourses);
    }

    private void createListView(ListView courseList, ArrayList<Course> courses){
        CourseAdapter courseAdapter = new CourseAdapter(this, courses);
        courseList.setAdapter(courseAdapter);
        courseList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected item text from ListView
                Course selectedItem = (Course) parent.getItemAtPosition(position);
                TextView textView = (TextView) findViewById(R.id.courseDetails);
                textView.setText(selectedItem.getCourseDescription());
                textView.setMovementMethod(new ScrollingMovementMethod());
                textView = (TextView) findViewById(R.id.courseDetailsTitle);
                textView.setText(selectedItem.getCourseNumber() + "-" + selectedItem.getCourseTitle());
                selectedCourse = position;

            }
        });
    }

    private void initializeSpinner(){
        dropdown = findViewById(R.id.semester_spinner);
        ArrayList<String> semesterNames = new ArrayList<>();
        semesterNames.addAll(semesters.keySet());
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_text, semesterNames);

        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                String key = dropdown.getItemAtPosition(pos).toString();
                takenCourses = semesters.get(key).getCourses();
                ((CourseAdapter)takenCourseList.getAdapter()).setCourses(takenCourses);

            }

            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void initializeButtons(){
        Button enrollButton = findViewById(R.id.enroll_btn);
        enrollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takenCourses.add(courses.get(selectedCourse));
                ((CourseAdapter)takenCourseList.getAdapter()).addCourse((courses.get(selectedCourse)));
                ((CourseAdapter)courseList.getAdapter()).removeCourse((selectedCourse));
            }
        });

        Button unenrollButton = findViewById(R.id.unenroll_btn);
        unenrollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Course removed = ((CourseAdapter)takenCourseList.getAdapter()).removeCourse(selectedCourse);
                if(removed != null)
                    ((CourseAdapter)courseList.getAdapter()).addCourse(removed);
            }
        });
    }

    private void initializeSemesters(){
        semesters = new HashMap<>();
        Semester semester = new Semester("Spring 2020");
        semesters.put(semester.getName(), semester );
        semester = new Semester("Fall 2020");
        semesters.put(semester.getName(), semester );
        semester = new Semester("Spring 2021");
        semesters.put(semester.getName(), semester );
        semester = new Semester("Fall 2021");
        semesters.put(semester.getName(), semester );
    }
}
