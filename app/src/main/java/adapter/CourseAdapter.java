package adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.csecoursescheduler.R;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.concurrent.Callable;

import model.Course;

public class CourseAdapter extends ArrayAdapter {
    private Activity context;
    private ArrayList<Course> courses;

    public CourseAdapter(Activity context, ArrayList<Course> courses){
        super(context, R.layout.course_row,  courses);
        this.context = context;
        this.courses = courses;

    }


    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View courseRow = inflater.inflate(R.layout.course_row, null, true);
        TextView courseNumberText = (TextView) courseRow.findViewById(R.id.courseNumber);
        TextView courseNameText = (TextView) courseRow.findViewById(R.id.courseTitle);
        TextView creditsText = (TextView) courseRow.findViewById(R.id.credits);
        Course course = courses.get(position);
        courseNumberText.setText(course.getCourseNumber());
        courseNameText.setText(course.getCourseTitle());
        creditsText.setText(course.getCredits() + " credits");
        return courseRow;
    }

    public void setCourses(ArrayList<Course> newCourses){
        this.courses.clear();
        for(Course c: newCourses){
            courses.add(c);
        }

        this.notifyDataSetChanged();
    }

    public Course getCourse(int index){
        return courses.get(index);
    }

    public void addCourse(Course newCourse){
        this.courses.add(newCourse);
        this.notifyDataSetChanged();
    }

    public Course removeCourse(int index){
        Course removed = null;
        if(this.courses.size() > index) {
            removed = this.courses.remove(index);
            this.notifyDataSetChanged();
        }
        return removed;
    }
}
