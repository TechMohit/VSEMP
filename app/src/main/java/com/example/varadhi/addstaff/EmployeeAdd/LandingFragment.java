package com.example.varadhi.addstaff.EmployeeAdd;


import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.varadhi.Attendance.AttendanceActivity;
import com.example.varadhi.addstaff.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class LandingFragment extends Fragment implements View.OnClickListener {
    LinearLayout addEmployeeLayout,layoutAddAttendance;
    Dialog chooseStaffDialog= null;
    Button btnTeachingClick,btnNonTeachingClick,btnTransportClick,btnStaffOk;
    static boolean selectedStaff = false;


    public LandingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_landing, container, false);
        addEmployeeLayout = v.findViewById(R.id.layoutAddEmployee);
        layoutAddAttendance = v.findViewById(R.id.layoutAddAttendance);
        addEmployeeLayout.setOnClickListener(this);
        layoutAddAttendance.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.layoutAddEmployee:
                 openDialogForStaffChoose();
               /* Intent openAddEmployeeActivity = new Intent(getActivity() , AddEmployeeLanding.class);
                startActivity(openAddEmployeeActivity);*/
                break;


            case R.id.btnStaffTeaching:
                btnTeachingClick.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                btnNonTeachingClick.setBackground(getResources().getDrawable(R.drawable.shape_border));
                btnTransportClick.setBackground(getResources().getDrawable(R.drawable.shape_border));
                Constant.STAFF_TYPE= "Teaching";
                selectedStaff = true;

                break;

            case R.id.btnStaffNonTeaching:
                btnNonTeachingClick.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                btnTeachingClick.setBackground(getResources().getDrawable(R.drawable.shape_border));
                btnTransportClick.setBackground(getResources().getDrawable(R.drawable.shape_border));
                Constant.STAFF_TYPE= "Non_Teaching";
                selectedStaff = true;
                break;

            case R.id.btnStaffTransport:
                btnTransportClick.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                btnNonTeachingClick.setBackground(getResources().getDrawable(R.drawable.shape_border));
                btnTeachingClick.setBackground(getResources().getDrawable(R.drawable.shape_border));
                Constant.STAFF_TYPE = "Transportation";
                selectedStaff = true;
                break;

            case R.id.btnStaffOk:
                if(selectedStaff) {
                    final ProgressDialog progressDialog = new ProgressDialog(getActivity());
                    progressDialog.setMessage("Loading...please wait");
                    progressDialog.show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            chooseStaffDialog.dismiss();
                            progressDialog.dismiss();
                            selectedStaff = false;
                            Intent openAddEmployeeActivity = new Intent(getActivity(), AddEmployeeLanding.class);
                            startActivity(openAddEmployeeActivity);
                        }
                    }, 3000);
                }
                else {
                    Toast.makeText(getActivity(), "Select Staff", Toast.LENGTH_SHORT).show();
                }

                break;


            case R.id.layoutAddAttendance:
                Intent openAddEmployeeActivity = new Intent(getActivity() , AttendanceActivity.class);
                startActivity(openAddEmployeeActivity);
                break;



        }

    }

    private void openDialogForStaffChoose() {
        chooseStaffDialog = new Dialog(getContext());
        chooseStaffDialog.setContentView(R.layout.staff_choose_dialog);
        chooseStaffDialog.setTitle("Choose your option..");
        chooseStaffDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        chooseStaffDialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

        chooseStaffDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                selectedStaff = false;
            }
        });
        btnTeachingClick = chooseStaffDialog.findViewById(R.id.btnStaffTeaching);
        btnNonTeachingClick = chooseStaffDialog.findViewById(R.id.btnStaffNonTeaching);
        btnTransportClick = chooseStaffDialog.findViewById(R.id.btnStaffTransport);
        btnStaffOk = chooseStaffDialog.findViewById(R.id.btnStaffOk);


        btnTeachingClick.setOnClickListener(this);
        btnNonTeachingClick.setOnClickListener(this);
        btnTransportClick.setOnClickListener(this);
        btnStaffOk.setOnClickListener(this);



        chooseStaffDialog.show();
    }
}
