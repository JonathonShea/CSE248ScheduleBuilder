package activities;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.csecoursescheduler.MainActivity;
import com.example.csecoursescheduler.R;

import java.util.ArrayList;

import adapter.CourseAdapter;
import model.Semester;

public class SemesterSpinnerActivity extends Activity implements AdapterView.OnItemSelectedListener {
    private ListView list;
    public SemesterSpinnerActivity(){

    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(parent.getContext(), "Help", Toast.LENGTH_LONG).show();
        Semester semester = (Semester)parent.getAdapter().getItem(position);
       // ((CourseAdapter)list.getAdapter()).setCourses(semester.getCourses());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
