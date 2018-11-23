package com.example.varadhi.addstaff.EmployeeAdd;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.varadhi.addstaff.R;

public class LandingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        getSupportFragmentManager().beginTransaction().
                replace(R.id.container,new LandingFragment()).commit();
    }
}
