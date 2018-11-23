package com.example.varadhi.Attendance;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.varadhi.addstaff.R;

public class AttendanceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);
        getSupportFragmentManager().beginTransaction().
                replace(R.id.container, new AttendanceLandingFragment()).
                    addToBackStack(null).commit();
    }
}
