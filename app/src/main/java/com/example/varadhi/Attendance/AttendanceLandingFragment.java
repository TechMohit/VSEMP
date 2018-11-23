package com.example.varadhi.Attendance;


import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.varadhi.addstaff.EmployeeAdd.CustomSpinnerAdapter;
import com.example.varadhi.addstaff.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class AttendanceLandingFragment extends Fragment implements View.OnClickListener {

    Spinner spinnerFor_Staff;
    TextView tv_ManuallyUpload;
    Dialog chooseStaffAttendanceDialog = null;

    public AttendanceLandingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_attendance_landing, container, false);
        spinnerFor_Staff = v.findViewById(R.id.spinnerForStaff);
        tv_ManuallyUpload = v.findViewById(R.id.tvManuallyUpload);
        initSpinnerForStaffType();

        tv_ManuallyUpload.setOnClickListener(this);



        return v;


    }

    private void initSpinnerForStaffType() {

        ArrayList<String> staffArrayList = new ArrayList<>();
        staffArrayList.add("Select Staff");
        staffArrayList.add("Teaching");
        staffArrayList.add("Non Teaching");
        staffArrayList.add("Transportation");


        CustomSpinnerAdapter customSpinnerAdapter=new CustomSpinnerAdapter(getActivity(),staffArrayList);
        spinnerFor_Staff.setAdapter(customSpinnerAdapter);
        spinnerFor_Staff.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {



            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

        case R.id.tvManuallyUpload:
        chooseStaffAttendanceDialog = new Dialog(getContext());
        chooseStaffAttendanceDialog.setContentView(R.layout.attendance_dialog);
        chooseStaffAttendanceDialog.setTitle("Choose your option..");
        chooseStaffAttendanceDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        chooseStaffAttendanceDialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        chooseStaffAttendanceDialog.show();
                break;



        }
    }
}
