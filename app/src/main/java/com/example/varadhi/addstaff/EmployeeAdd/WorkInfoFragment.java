package com.example.varadhi.addstaff.EmployeeAdd;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.varadhi.addstaff.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;


/**
 * A simple {@link Fragment} subclass.
 */
public class WorkInfoFragment extends Fragment implements View.OnClickListener,DynaicUiChanges {

    Button                             btnIncreaseCount , btnDecreaseCount;

    TextView                           tvNumberCount;


    EditText                           et_OrganisationName, et_OrganisationCity,
                                       et_EmployeeRoles,et_EmployeeResponsibility,
                                       et_JoiningDate,et_LeavingDate,
                                       et_HRNumber,et_TotalExperience;


    String                         str_OrganisationName, str_OrganisationCity,
                                   str_EmployeeRoles,str_EmployeeResponsibility,
                                   str_JoiningDate,str_LeavingDate,
                                   str_HRNumber,str_TotalExperience,str_employeeDesignation,
                                   str_employeeDepartment,str_employeeID,
                                   str_Employee_FirstName,str_Employee_LastName;

    ArrayList<EditText> arrayListOrganisationName            = new ArrayList<>();
    ArrayList<EditText> arrayListOrganisationCity            = new ArrayList<>();
    ArrayList<EditText> arrayListEmployeeRoles               = new ArrayList<>();
    ArrayList<EditText> arrayListEmployeeResponsibility      = new ArrayList<>();
    ArrayList<EditText> arrayListEmployeeExperience          = new ArrayList<>();
    ArrayList<EditText> arrayListJoiningDate                 = new ArrayList<>();
    ArrayList<EditText> arrayListLeavingDate                 = new ArrayList<>();
    ArrayList<EditText> arrayListHRNumber                    = new ArrayList<>();
    ArrayList<EditText> arrayListTotalExperience             = new ArrayList<>();
    ArrayList<RecyclerView> arrayListDocRecycelrview         = new ArrayList<>();
    ArrayList<ImageView> arrayListDocuments             = new ArrayList<>();

    ArrayList<ImageModel> arrayListExpDoc1 = new ArrayList<>();
    ArrayList<ImageModel> arrayListExpDoc2 = new ArrayList<>();
    ArrayList<ImageModel> arrayListExpDoc3 = new ArrayList<>();
    ArrayList<ImageModel> arrayListExpDoc4 = new ArrayList<>();
    ArrayList<ImageModel> arrayListExpDoc5 = new ArrayList<>();
    ArrayList<Uri> arrayListEmpExpDoc                        = new ArrayList<>();
    ImageView      iv_AttachExperienceDocs;
    RecyclerView   rv_AttachExperienceDocs;
    Dialog         imageChooserDialog = null;
    DatabaseReference refLastEmployee;
    private DatePickerDialog fromDatePickerDialog;
    private Calendar newCalendar;
    private SimpleDateFormat dateFormatter;
    private String getFromDate;
    private DatePickerDialog toDatePickerDialog;
    ImageAdpater imageAdapter;
    ProgressDialog progressDialog = null;
    TextView emp_name,emp_registration_id,emp_dept;
    FirebaseDatabase mDataBase;
    DatabaseReference mRef;
    DatabaseReference barriersRef;
    int valueStatus=0;
    int valueStatusFirebase=0;
    StorageReference mStorageRef;
    CircleImageView staffImage;
    int max = 5;
    int count = 0;
    LinearLayout experienceHeadLayout;
    private int passingCase=1;

    String selectedYear;
    String selectedMonth;
    String selectedDate;
    private String imageCase;
    Button sendWorkExp;
    private Uri filePath;
    private Bitmap bitmap;
    private int imageCounter=0;

    LinearLayout linear_LayoutHead;

    EventBus bus = EventBus.getDefault();
    private String str_date;
    private long date_min;
    private int year;
    private int month;
    private int date;

    public WorkInfoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_work_info, container, false);
        setHasOptionsMenu(true);
        btnIncreaseCount      = v.findViewById(R.id.button_increase_count);
        btnDecreaseCount      = v.findViewById(R.id.button_decrease_count);
        tvNumberCount         = v.findViewById(R.id.numberOfExperience);
        experienceHeadLayout  = v.findViewById(R.id.experience_linear_layout);
        mRef      = FirebaseDatabase.getInstance().getReference("School/SchoolId/Staff_Registration");
        refLastEmployee = FirebaseDatabase.getInstance().getReference("VARADHISMARTEK000/Employee_List");
        barriersRef      = FirebaseDatabase.getInstance().getReference("School/SchoolId/Barriers/Staff_Registration_Ids");
        mStorageRef = FirebaseStorage.getInstance().getReference("VARADHISMARTEK000/Employee Details");
        progressDialog = new ProgressDialog(getActivity());
        newCalendar = Calendar.getInstance();
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        staffImage         = v.findViewById(R.id.staffProfileImage);
        emp_name           = v.findViewById(R.id.staffName);
        emp_registration_id = v.findViewById(R.id.staffRegistartionNumber);
        emp_dept            = v.findViewById(R.id.staffDept);
        btnIncreaseCount.setOnClickListener(this);
        btnDecreaseCount.setOnClickListener(this);
        linear_LayoutHead = v.findViewById(R.id.linearLayoutHead);
        year = Calendar.getInstance().get(Calendar.YEAR);
        month = Calendar.getInstance().get(Calendar.MONTH);
        date = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);

        return v;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case 101:
                passingCase = 1;
                //openCalendarDialog(passingCase);

                if(!arrayListJoiningDate.get(0).getText().toString().equals("Joining Date")){
                    fromDate(passingCase);
                    arrayListLeavingDate.get(0).setOnClickListener(this);

                }
                else {
                    Toast.makeText(getActivity(), "Please select from date first", Toast.LENGTH_SHORT).show();
                }

                break;

            case 102:
                passingCase = 2;
                //openCalendarDialog(passingCase);
                fromDate(passingCase);
                arrayListLeavingDate.get(1).setOnClickListener(this);


                break;

            case 103:
                passingCase = 3;
                //openCalendarDialog(passingCase);
                fromDate(passingCase);
                arrayListLeavingDate.get(2).setOnClickListener(this);


                break;

            case 104:
                passingCase = 4;
                //openCalendarDialog(passingCase);
                fromDate(passingCase);
                arrayListLeavingDate.get(3).setOnClickListener(this);


                break;

            case 105:
                passingCase = 5;
                //openCalendarDialog(passingCase);
                fromDate(passingCase);
                arrayListLeavingDate.get(4).setOnClickListener(this);


                break;


            case 1001:
                passingCase = 6;
                toDate(passingCase);
                break;

            case 1002:
                passingCase = 7;
                toDate(passingCase);

                break;

            case 1003:
                passingCase = 8;
                toDate(passingCase);

                break;

            case 1004:
                passingCase = 9;
                toDate(passingCase);

                break;

            case 1005:
                passingCase = 10;
                toDate(passingCase);

                break;

            case R.id.attach_id+1:
                imageCase = "A";
                openDialogForImageChoose();

                break;

            case R.id.attach_id+2:
                imageCase = "B";
                openDialogForImageChoose();

                break;

            case R.id.attach_id+3:
                imageCase = "C";
                openDialogForImageChoose();

                break;

            case R.id.attach_id+4:
                imageCase = "D";
                openDialogForImageChoose();

                break;

            case R.id.attach_id+5:
                imageCase = "E";
                openDialogForImageChoose();
                break;

            case R.id.camera:
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, Constant.FROM_CAMERA);
                imageChooserDialog.dismiss();
                break;

            case R.id.gallery:
                Intent i = new Intent(
                Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, Constant.FROM_GALLERY);
                imageChooserDialog.dismiss();
                break;


            case R.id.button_increase_count:
                if(count!=max) {
                    count++;
                    //sendWorkExp.setVisibility(View.VISIBLE);
                    btnDecreaseCount.setEnabled(true);
                    displayNumberOfGuardian(count);
                    View view = getLayoutInflater().inflate(R.layout.experience_layout, null);
                    et_OrganisationName             = view.findViewById(R.id.etOrganisationName);
                    et_OrganisationCity             = view.findViewById(R.id.etOrganisationCity);
                    et_EmployeeRoles                = view.findViewById(R.id.etEmployeeRoles);
                    et_EmployeeResponsibility       = view.findViewById(R.id.etEmployeeResponsibility);
                    et_JoiningDate                  = view.findViewById(R.id.etJoiningDate);
                    et_JoiningDate.setId(100+count);
                    et_JoiningDate.setOnClickListener(this);
                    et_LeavingDate                  = view.findViewById(R.id.etLeavingDate);
                    et_LeavingDate.setId(1000+count);
                    et_HRNumber                     = view.findViewById(R.id.etHRNumber);
                    et_TotalExperience              = view.findViewById(R.id.etTotalExperience);
                    et_TotalExperience.setFocusable(false);
                    iv_AttachExperienceDocs         = view.findViewById(R.id.ivAttachExperienceDocs);
                    iv_AttachExperienceDocs.setId(R.id.attach_id+count);
                    iv_AttachExperienceDocs.setOnClickListener(this);
                    rv_AttachExperienceDocs         = view.findViewById(R.id.rvAttachExperienceDocs);




                    arrayListOrganisationName.add(et_OrganisationName);
                    arrayListOrganisationCity.add(et_OrganisationCity);
                    arrayListEmployeeRoles.add(et_EmployeeRoles);
                    arrayListEmployeeResponsibility.add(et_EmployeeResponsibility);
                    arrayListEmployeeExperience.add(et_TotalExperience);
                    arrayListJoiningDate.add(et_JoiningDate);
                    arrayListLeavingDate.add(et_LeavingDate);
                    arrayListHRNumber.add(et_HRNumber);
                    arrayListTotalExperience.add(et_TotalExperience);
                    arrayListDocRecycelrview.add(rv_AttachExperienceDocs);
                    arrayListDocuments.add(iv_AttachExperienceDocs);
                    experienceHeadLayout.addView(view, 0);

                }

                else {
                    btnIncreaseCount.setEnabled(false);
                }

                break;

            case R.id.button_decrease_count:

                if(count!=0){
                    count--;
                    btnIncreaseCount.setEnabled(true);
                    displayNumberOfGuardian(count);
                    experienceHeadLayout.removeViewAt(0);
                    arrayListOrganisationName.remove(count);
                    arrayListOrganisationCity.remove(count);
                    arrayListEmployeeRoles.remove(count);
                    arrayListEmployeeResponsibility.remove(count);
                    arrayListJoiningDate.remove(count);
                    arrayListLeavingDate.remove(count);
                    arrayListHRNumber.remove(count);
                    arrayListTotalExperience.remove(count);
                    arrayListEmployeeExperience.remove(count);
                    arrayListDocuments.remove(count);
                    arrayListDocRecycelrview.remove(count);



                }


                else{
                    btnDecreaseCount.setEnabled(false);
                }
                break;



        }

    }

    private void toDate(final int passingCase) {

        DatePickerDialog dialog1 = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy");

                //getting the current date from the datepicker
                str_date = simpleDateFormat.format(calendar.getTime());

                switch (passingCase){

                    case 6:
                        arrayListLeavingDate.get(0).setText(str_date);
                        String diff = getDateDiffreneceExp(arrayListJoiningDate.get(0).getText().toString(),arrayListLeavingDate.get(0).getText().toString());
                        Log.d("dateDiff", ""+diff);
                        arrayListEmployeeExperience.get(0).setText(diff);
                        break;

                    case 7:
                        arrayListLeavingDate.get(1).setText(str_date);
                        String diff1 = getDateDiffreneceExp(arrayListJoiningDate.get(1).getText().toString(),arrayListLeavingDate.get(1).getText().toString());
                        Log.d("dateDiff", ""+diff1);
                        arrayListEmployeeExperience.get(1).setText(diff1);
                        break;

                    case 8:
                        arrayListLeavingDate.get(2).setText(str_date);
                        String diff2 = getDateDiffreneceExp(arrayListJoiningDate.get(2).getText().toString(),arrayListLeavingDate.get(2).getText().toString());
                        arrayListEmployeeExperience.get(2).setText(diff2);
                        break;

                    case 9:
                        arrayListLeavingDate.get(3).setText(str_date);
                        String diff3 = getDateDiffreneceExp(arrayListJoiningDate.get(3).getText().toString(),arrayListLeavingDate.get(3).getText().toString());
                        arrayListEmployeeExperience.get(3).setText(diff3);
                        break;

                    case 10:
                        arrayListLeavingDate.get(4).setText(str_date);
                        String diff4 = getDateDiffreneceExp(arrayListJoiningDate.get(4).getText().toString(),arrayListLeavingDate.get(4).getText().toString());
                        arrayListEmployeeExperience.get(4).setText(diff4);
                        break;



                }


            }
        }, year, month, date);

        dialog1.getDatePicker().setMinDate(date_min);
        dialog1.getDatePicker().setMaxDate(System.currentTimeMillis());
        dialog1.show();


        /*if (date_min == 0) {
            dialog1.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
            dialog1.getDatePicker().setMaxDate(System.currentTimeMillis());
        } else {
            dialog1.getDatePicker().setMinDate(date_min);
        }*/

        dialog1.show();

        /*toDatePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);


                switch (passingCase){

                    case 6:
                        arrayListLeavingDate.get(0).setText(dateFormatter.format(newDate.getTime()));
                        String diff = getDateDiffreneceExp(arrayListJoiningDate.get(0).getText().toString(),arrayListLeavingDate.get(0).getText().toString());
                        Log.d("dateDiff", ""+diff);
                        arrayListEmployeeExperience.get(0).setText(diff);
                        break;

                    case 7:
                        arrayListLeavingDate.get(1).setText(dateFormatter.format(newDate.getTime()));
                        String diff1 = getDateDiffreneceExp(arrayListJoiningDate.get(1).getText().toString(),arrayListLeavingDate.get(1).getText().toString());
                        Log.d("dateDiff", ""+diff1);
                        arrayListEmployeeExperience.get(1).setText(diff1);
                        break;

                    case 8:
                        arrayListLeavingDate.get(2).setText(dateFormatter.format(newDate.getTime()));
                        String diff2 = getDateDiffreneceExp(arrayListJoiningDate.get(2).getText().toString(),arrayListLeavingDate.get(2).getText().toString());
                        arrayListEmployeeExperience.get(2).setText(diff2);
                        break;

                    case 9:
                        arrayListLeavingDate.get(3).setText(dateFormatter.format(newDate.getTime()));
                        String diff3 = getDateDiffreneceExp(arrayListJoiningDate.get(3).getText().toString(),arrayListLeavingDate.get(3).getText().toString());
                        arrayListEmployeeExperience.get(3).setText(diff3);
                        break;

                    case 10:
                        arrayListLeavingDate.get(4).setText(dateFormatter.format(newDate.getTime()));
                        String diff4 = getDateDiffreneceExp(arrayListJoiningDate.get(4).getText().toString(),arrayListLeavingDate.get(4).getText().toString());
                        arrayListEmployeeExperience.get(4).setText(diff4);
                        break;



                }
                newDate.add(Calendar.DAY_OF_MONTH, 30);


            }

        },
                newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        toDatePickerDialog.getDatePicker().setMinDate(milliseconds(changeDate(getFromDate)));
        toDatePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
        toDatePickerDialog.show();*/

    }

    private String getDateDiffreneceExp(String join, String leave) {

        Log.d("dates", ""+join+" "+leave);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy", Locale.US);
        float diff=0;
        String expCount = null;

        try {
            Date joiningDate = sdf.parse(join);
            Date leavingDate = sdf.parse(leave);
            Log.d("joindate", ""+joiningDate.toString());
            Log.d("leavingDate", ""+leavingDate.toString());

            long diffInMilliSec = leavingDate.getTime() - joiningDate.getTime();

            long days = (diffInMilliSec / (1000 * 60 * 60 * 24)) % 365;

            long years =  (diffInMilliSec / (1000l * 60 * 60 * 24 * 365));

            int month = Integer.parseInt(String.valueOf(days))/30;

            long getDaysDiff = TimeUnit.MILLISECONDS.toDays(diffInMilliSec);

            Log.d("dhd", ""+getDaysDiff);

            Float i = (float) (long) getDaysDiff;

            Log.d("dk", ""+i/365);

            Log.d("dhdh", ""+years+"."+month);


            expCount = years+"."+month;



        } catch (ParseException e) {
            e.printStackTrace();
        }


        return expCount;

    }

    public static long milliseconds(String date)
    {
        //String date_ = date;
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
        System.out.println("Date in milli :: " + date);
        try
        {
            Date mDate = sdf.parse(date);
            long timeInMilliseconds = mDate.getTime();
            System.out.println("Date in milli :: " + timeInMilliseconds);
            return timeInMilliseconds;
        }
        catch (ParseException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return 0;
    }

    private String  changeDate(String strStartDate){
        SimpleDateFormat readFormat = null, writeFormat = null;
        Date startDate = null, endDate = null;
        //  String strStartDate = "2014-12-09";

        readFormat = new SimpleDateFormat("dd-MM-yyyy");
        writeFormat = new SimpleDateFormat("MM/dd/yyyy");

        try {
            startDate = readFormat.parse(strStartDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return writeFormat.format(startDate);
    }

    private void fromDate(final int passingCase) {


        DatePickerDialog dialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy");

                //getting the current datefragment from the datepicker
                str_date = simpleDateFormat.format(calendar.getTime());

                try {
                    Date date = simpleDateFormat.parse(str_date);
                    date_min = date.getTime() + 1;
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                switch (passingCase) {
                    case 1:
                        arrayListJoiningDate.get(0).setText(str_date);
                        getFromDate = arrayListJoiningDate.get(0).getText().toString().trim();
                        break;

                    case 2:
                        arrayListJoiningDate.get(1).setText(str_date);
                        getFromDate = arrayListJoiningDate.get(1).getText().toString().trim();
                        break;

                    case 3:
                        arrayListJoiningDate.get(2).setText(str_date);
                        getFromDate = arrayListJoiningDate.get(2).getText().toString().trim();

                        break;

                    case 4:
                        arrayListJoiningDate.get(3).setText(str_date);
                        getFromDate = arrayListJoiningDate.get(3).getText().toString().trim();

                        break;

                    case 5:
                        arrayListJoiningDate.get(4).setText(str_date);
                        getFromDate = arrayListJoiningDate.get(4).getText().toString().trim();

                        break;



                }

            }
        }, year, month, date);
        dialog.getDatePicker().setMaxDate(System.currentTimeMillis());
        dialog.show();

        /*fromDatePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                //newDate.setTimeInMillis(System.currentTimeMillis());


                switch (passingCase) {
                    case 1:
                        arrayListJoiningDate.get(0).setText(dateFormatter.format(newDate.getTime()));
                        getFromDate = arrayListJoiningDate.get(0).getText().toString().trim();
                        break;

                    case 2:
                        arrayListJoiningDate.get(1).setText(dateFormatter.format(newDate.getTime()));
                        getFromDate = arrayListJoiningDate.get(1).getText().toString().trim();
                        break;

                    case 3:
                        arrayListJoiningDate.get(2).setText(dateFormatter.format(newDate.getTime()));
                        getFromDate = arrayListJoiningDate.get(2).getText().toString().trim();

                        break;

                    case 4:
                        arrayListJoiningDate.get(3).setText(dateFormatter.format(newDate.getTime()));
                        getFromDate = arrayListJoiningDate.get(3).getText().toString().trim();

                        break;

                    case 5:
                        arrayListJoiningDate.get(4).setText(dateFormatter.format(newDate.getTime()));
                        getFromDate = arrayListJoiningDate.get(4).getText().toString().trim();

                        break;


                }

            }
        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        fromDatePickerDialog.show();
        fromDatePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
*/

    }



    private void openDialogForImageChoose() {
        imageChooserDialog = new Dialog(getActivity());
        imageChooserDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        imageChooserDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        imageChooserDialog.setContentView(R.layout.attach_image_dialog);
        ImageView camera  = imageChooserDialog.findViewById(R.id.camera);
        ImageView gallery  = imageChooserDialog.findViewById(R.id.gallery);

        camera.setOnClickListener(this);
        gallery.setOnClickListener(this);
        imageChooserDialog.show();
    }



    private void displayNumberOfGuardian(int minteger) {
        tvNumberCount.setText(""+minteger);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constant.FROM_CAMERA && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            String path = MediaStore.Images.Media.insertImage(getActivity().getContentResolver(), photo, "Title", null);
            filePath = Uri.parse(path);
            switch (imageCase){

                case "A":
                    setImageToRecyclerView();
                    arrayListEmpExpDoc.add(filePath);
                    imageAdapter = new ImageAdpater(arrayListExpDoc1, getActivity() , requestCode);
                    arrayListDocRecycelrview.get(0).setAdapter(imageAdapter);
                    break;

                case "B":
                    setImageToRecyclerView();
                    arrayListEmpExpDoc.add(filePath);
                    imageAdapter = new ImageAdpater(arrayListExpDoc2, getActivity() , requestCode);
                    arrayListDocRecycelrview.get(1).setAdapter(imageAdapter);
                    break;

                case "C":
                    setImageToRecyclerView();
                    arrayListEmpExpDoc.add(filePath);
                    imageAdapter = new ImageAdpater(arrayListExpDoc3, getActivity() , requestCode);
                    arrayListDocRecycelrview.get(2).setAdapter(imageAdapter);
                    break;

                case "D":
                    setImageToRecyclerView();
                    arrayListEmpExpDoc.add(filePath);
                    imageAdapter = new ImageAdpater(arrayListExpDoc4, getActivity() , requestCode);
                    arrayListDocRecycelrview.get(3).setAdapter(imageAdapter);
                    break;


                case "E":
                    setImageToRecyclerView();
                    arrayListEmpExpDoc.add(filePath);
                    imageAdapter = new ImageAdpater(arrayListExpDoc5, getActivity() , requestCode);
                    arrayListDocRecycelrview.get(4).setAdapter(imageAdapter);
                    break;

            }


        }

        if (requestCode == Constant.FROM_GALLERY && resultCode == Activity.RESULT_OK) {
            filePath = data.getData();

            InputStream inputStream;
            try {
                inputStream = getContext().getContentResolver().openInputStream(filePath);
                bitmap = BitmapFactory.decodeStream(inputStream);
                String path = MediaStore.Images.Media.insertImage(getActivity().getContentResolver(), bitmap, "Title", null);
                filePath = Uri.parse(path);

                switch (imageCase){

                    case "A":
                        setImageToRecyclerView();
                        arrayListEmpExpDoc.add(filePath);
                        imageAdapter = new ImageAdpater(arrayListExpDoc1, getActivity() , requestCode);
                        arrayListDocRecycelrview.get(0).setAdapter(imageAdapter);
                        break;

                    case "B":
                        setImageToRecyclerView();
                        arrayListEmpExpDoc.add(filePath);
                        imageAdapter = new ImageAdpater(arrayListExpDoc2, getActivity() , requestCode);
                        arrayListDocRecycelrview.get(1).setAdapter(imageAdapter);
                        break;

                    case "C":
                        setImageToRecyclerView();
                        arrayListEmpExpDoc.add(filePath);
                        imageAdapter = new ImageAdpater(arrayListExpDoc3, getActivity() , requestCode);
                        arrayListDocRecycelrview.get(2).setAdapter(imageAdapter);
                        break;

                    case "D":
                        setImageToRecyclerView();
                        arrayListEmpExpDoc.add(filePath);
                        imageAdapter = new ImageAdpater(arrayListExpDoc4, getActivity() , requestCode);
                        arrayListDocRecycelrview.get(3).setAdapter(imageAdapter);
                        break;


                    case "E":
                        setImageToRecyclerView();
                        arrayListEmpExpDoc.add(filePath);
                        imageAdapter = new ImageAdpater(arrayListExpDoc5, getActivity() , requestCode);
                        arrayListDocRecycelrview.get(4).setAdapter(imageAdapter);
                        break;


                }



            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }


    }

    private void setImageToRecyclerView() {
        ImageModel imageModel;

        switch (imageCase) {

            case "A":
                LinearLayoutManager guardianLayoutManager1 = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
                arrayListDocRecycelrview.get(0).setLayoutManager(guardianLayoutManager1);
                imageModel = new ImageModel(filePath);
                arrayListExpDoc1.add(imageModel);
                break;

            case "B":
                LinearLayoutManager guardianLayoutManager2 = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
                arrayListDocRecycelrview.get(1).setLayoutManager(guardianLayoutManager2);
                imageModel = new ImageModel(filePath);
                arrayListExpDoc2.add(imageModel);
                break;

            case "C":
                LinearLayoutManager guardianLayoutManager3 = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
                arrayListDocRecycelrview.get(2).setLayoutManager(guardianLayoutManager3);
                imageModel = new ImageModel(filePath);
                arrayListExpDoc3.add(imageModel);
                break;


            case "D":
                LinearLayoutManager guardianLayoutManager4= new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
                arrayListDocRecycelrview.get(3).setLayoutManager(guardianLayoutManager4);
                imageModel = new ImageModel(filePath);
                arrayListExpDoc4.add(imageModel);
                break;

            case "E":
                LinearLayoutManager guardianLayoutManager5= new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
                arrayListDocRecycelrview.get(4).setLayoutManager(guardianLayoutManager5);
                imageModel = new ImageModel(filePath);
                arrayListExpDoc5.add(imageModel);
                break;
        }


    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.exp_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.sendExp:
                //Toast.makeText(getActivity(), "sdaadda", Toast.LENGTH_SHORT).show();

               if(!emp_registration_id.getText().toString().equals("Registration Number")){

                    switch (Constant.STAFF_TYPE){

                        case "Teaching":
                            storeDataToFirebase();
                            break;

                        case "Non_Teaching":
                            storeDataToFirebase();

                            break;

                        case "Transportation":
                            storeDataToFirebase();
                            break;

                    }


                }
                else {

                    Snackbar snackbar = Snackbar
                            .make(linear_LayoutHead, "Please select registration id", Snackbar.LENGTH_LONG);

                    snackbar.show();
                }



                break;

        }
        return super.onOptionsItemSelected(item);

    }

    private void storeDataToFirebase() {


        if(!emp_registration_id.getText().toString().equals("Registration Number")){

            if(count!=0){

                for (int j = 0; j < arrayListTotalExperience.size(); j++) {

                    et_OrganisationName = arrayListOrganisationName.get(j);
                    et_OrganisationCity = arrayListOrganisationCity.get(j);
                    et_EmployeeRoles = arrayListEmployeeRoles.get(j);
                    et_EmployeeResponsibility = arrayListEmployeeResponsibility.get(j);
                    et_JoiningDate = arrayListJoiningDate.get(j);
                    et_LeavingDate = arrayListLeavingDate.get(j);
                    et_HRNumber = arrayListHRNumber.get(j);
                    et_TotalExperience = arrayListTotalExperience.get(j);


                    if(!ValidationViews.EditTextNullValidate(et_OrganisationName)){
                        return;
                    }

                    else if(!ValidationViews.EditTextNullValidate(et_OrganisationCity)){
                        return;
                    }

                    else if(!ValidationViews.EditTextNullValidate(et_EmployeeRoles)){
                        return;
                    }

                    else if(!ValidationViews.EditTextNullValidate(et_EmployeeResponsibility)){
                        return;
                    }
                    else if(!ValidationViews.EditTextNullValidate(et_JoiningDate)){
                        return;
                    }

                    else if(!ValidationViews.EditTextNullValidate(et_LeavingDate)){
                        return;
                    }

                    else if(!ValidationViews.EditTextNullValidate(et_HRNumber)){
                        return;
                    }

                    else if(!ValidationViews.EditTextNullValidate(et_TotalExperience)){
                        return;
                    }


                }

            }

            if(count!=0) {

                final DatabaseReference db_parent = mRef.child(Constant.STAFF_TYPE).child(Constant.STAFF_FINAL_ID)
                        .child("Work Experience");

                for (int j = 0; j < arrayListTotalExperience.size(); j++) {

                    DatabaseReference db_child = db_parent.child("experience" + (j + 1));

                    str_OrganisationName = arrayListOrganisationName.get(j).getText().toString();
                    str_OrganisationCity = arrayListOrganisationCity.get(j).getText().toString();
                    str_EmployeeRoles = arrayListEmployeeRoles.get(j).getText().toString();
                    str_EmployeeResponsibility = arrayListEmployeeResponsibility.get(j).getText().toString();
                    str_JoiningDate = arrayListJoiningDate.get(j).getText().toString();
                    str_LeavingDate = arrayListLeavingDate.get(j).getText().toString();
                    str_HRNumber = arrayListHRNumber.get(j).getText().toString();
                    str_TotalExperience = arrayListTotalExperience.get(j).getText().toString();


                    db_child.child("str_OrganisationName").setValue(str_OrganisationName);
                    db_child.child("str_OrganisationCity").setValue(str_OrganisationCity);
                    db_child.child("str_EmployeeRoles").setValue(str_EmployeeRoles);
                    db_child.child("str_EmployeeResponsibility").setValue(str_EmployeeResponsibility);
                    db_child.child("str_JoiningDate").setValue(str_JoiningDate);
                    db_child.child("str_LeavingDate").setValue(str_LeavingDate);
                    db_child.child("str_HRNumber").setValue(str_HRNumber);
                    db_child.child("str_TotalExperience").setValue(str_TotalExperience);

                }
                progressDialog.setMessage("Storing data please wait...");
                progressDialog.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        Toast.makeText(getActivity(), ""+Constant.STAFF_FINAL_ID+" "+Constant.REGISTRATION_CURRENT_TEMP_ID, Toast.LENGTH_SHORT).show();
                        if(!Constant.STAFF_FINAL_ID.equals(Constant.REGISTRATION_CURRENT_TEMP_ID)){
                            //barriersRef.child(Constant.STAFF_TYPE).child("Current_Registration_Id").setValue(Constant.STAFF_FINAL_ID);
                            Toast.makeText(getActivity(), "Registration Finished...", Toast.LENGTH_SHORT).show();
                            Intent in = new Intent(getActivity() , LandingActivity.class);
                            startActivity(in);
                            progressDialog.dismiss();
                        }
                        else {

                            barriersRef.child(Constant.STAFF_TYPE).child("Current_Registration_Id").setValue(Constant.STAFF_FINAL_ID);
                            Toast.makeText(getActivity(), "Registration Finished...", Toast.LENGTH_SHORT).show();
                            Intent in = new Intent(getActivity() , LandingActivity.class);
                            startActivity(in);
                            progressDialog.dismiss();

                        }

                    }
                },3000);
            }
            else {

                progressDialog.setMessage("Storing data please wait...");
                progressDialog.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        if(!Constant.STAFF_FINAL_ID.equals(Constant.REGISTRATION_CURRENT_TEMP_ID)) {
                            mRef.child(Constant.STAFF_TYPE).child(Constant.STAFF_FINAL_ID)
                                    .child("Work Experience").child("experience").setValue(count);
                            Toast.makeText(getActivity(), "Registration Finished...", Toast.LENGTH_SHORT).show();
                            Intent in = new Intent(getActivity() , LandingActivity.class);
                            startActivity(in);
                            progressDialog.dismiss();
                        }
                        else {
                            mRef.child(Constant.STAFF_TYPE).child(Constant.STAFF_FINAL_ID)
                                    .child("Work Experience").child("experience").setValue(count);
                            barriersRef.child(Constant.STAFF_TYPE).child("Current_Registration_Id").setValue(Constant.STAFF_FINAL_ID);
                            Toast.makeText(getActivity(), "Registration Finished...", Toast.LENGTH_SHORT).show();
                            Intent in = new Intent(getActivity() , LandingActivity.class);
                            startActivity(in);
                            progressDialog.dismiss();
                        }

                    }
                },3000);

            }

        }
        else {
            Toast.makeText(getActivity(), "Please select staff", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        bus.register(this);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        bus.removeAllStickyEvents();
        bus.unregister(this);

    }



    @Subscribe
    public void onEvent(EmployeeAddModel event) {
        String registartionNumber= event.getEmpployee_registration_number();
        String name = event.getStr_Employee_FirstName();
        String dept = event.getStr_Employee_Department();
        emp_dept.setText(dept);
        emp_name.setText(name);
        emp_registration_id.setText(registartionNumber);
        Glide.with(getActivity()).load(Constant.STAFF_PROFILE_IMAGE).into(staffImage);
        bus.removeAllStickyEvents();
        //text_View.setText(event.getStr_Employee_FirstName());

    }


    @Override
    public void staffStatus(Context mContext, String status) {

    }
}
