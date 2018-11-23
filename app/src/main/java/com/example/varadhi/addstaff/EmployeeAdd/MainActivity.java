package com.example.varadhi.addstaff.EmployeeAdd;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.varadhi.addstaff.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button employer, employee,employerLoginButton;
    LinearLayout layoutEmployer,layoutEmployee;
    EditText emailEmployer,passwordEmployer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        employer = findViewById(R.id.employer);
        employee = findViewById(R.id.employee);
        employee.setClickable(false);
        layoutEmployer = findViewById(R.id.layoutEmployer);
        //layoutEmployee= findViewById(R.id.layoutAddEmployee);
        employerLoginButton = findViewById(R.id.email_sign_in_buttonEmployer);
        emailEmployer = findViewById(R.id.emailEmployer);
        passwordEmployer = findViewById(R.id.passwordEmployer);



        employer.setOnClickListener(this);
        employee.setOnClickListener(this);
        employerLoginButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.employer:
                layoutEmployer.setVisibility(View.VISIBLE);
                //layoutEmployee.setVisibility(View.GONE);
                layoutEmployer.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.anim_on));
                employer.setTextColor(getResources().getColor(R.color.colorWhite));
                employee.setTextColor(getResources().getColor(R.color.colorOrange));
                employer.setBackground(getResources().getDrawable(R.drawable.selector_red_left_switch_button));
                employee.setBackground(getResources().getDrawable(R.drawable.selector_gray_right_switch_button));

                break;

            case R.id.employee:
                //layoutEmployee.setVisibility(View.VISIBLE);
                layoutEmployer.setVisibility(View.GONE);
                //layoutEmployee.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.anim_on));
                employee.setTextColor(getResources().getColor(R.color.colorWhite));
                employer.setTextColor(getResources().getColor(R.color.colorOrange));
                employer.setBackground(getResources().getDrawable(R.drawable.selector_gray_left_switch_button));
                employee.setBackground(getResources().getDrawable(R.drawable.selector_red_right_switch_button));
                break;

            case R.id.email_sign_in_buttonEmployer:

                /*if(emailEmployer.getText().toString().equals("varadhismartek@gmail.com") &&
                        passwordEmployer.getText().toString().equals("VARADHI")){*/
                    Intent intent = new Intent(MainActivity.this, LandingActivity.class);
                    startActivity(intent);
/*
                }*/

                break;






        }
    }
}
