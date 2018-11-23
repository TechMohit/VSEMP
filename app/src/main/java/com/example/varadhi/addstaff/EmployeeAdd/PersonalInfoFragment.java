package com.example.varadhi.addstaff.EmployeeAdd;


import android.annotation.SuppressLint;
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
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.BitmapCompat;
import android.support.v7.widget.SearchView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.varadhi.addstaff.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 */
public class PersonalInfoFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private static final int BUTTON_ENABLE = 1;
    LinearLayout          personalInfoLayout,linearLayoutPersonalProfileViews,
                          idDeatailsLayout,linearLayoutIDDetailsViews,
                          addressDeatailsLayout,linearLayoutAddressDetailsViews,
                          parentDeatailsLayout,linearLayoutParentDetailsViews,
                          maritalDeatailsLayout,linearLayoutMaritalDetailsViews,
                          medicalDeatailsLayout,linearLayoutMedicalDetailsViews,
                          spouseLayout,layout_PermanentAddress,llEmployee_DynamiMedicalIssues,
                          linear_head_child,add_ChileDetailsLayout,llMarriedVisible,linear_LayoutHead;


    CircleImageView       ivEmployee_ProfilePicture;


    RelativeLayout        personal_InfoLayoutClick,idProof_LayoutClick,
                          addressDetails_LayoutClick,parentDetails_LayoutClick,
                          maritalDetails_LayoutClick,medicalDetails_LayoutClick ;


    ImageView             personalInfoImageViewDown,idInfoImageViewDown,
                          addressImageViewDown,parentInfoImageViewDown,
                          maritalInfoImageViewDown,medicalInfoImageViewDown,
                          ivEmployee_AddIssues,ivEmployee_Calendar,staff_Profile_Picture;


    TextView              personalInfoTextView,iDInfoTextView,addressInfoTextView,
                          parentInfoTextView,maritalInfoTextView,medicalInfoTextView,
                          textViewGenderMale,textViewGenderFemale,textViewGenderOther,
                          textViewEmployeeMarried, textViewEmployeeUnmarried,childNumber,
                          staffRegistration_Number, staff_Name, staff_Dept;


    TextInputLayout       staffEmailId,ti_employeeDlNumber;




    EditText              etEmployee_FirstName,etEmployee_MiddleName,
                          etEmployee_LastName,etEmployee_BirthPlace,
                          etEmployee_DOB,etEmployee_MobileNumber,
                          etEmployee_Nationality,etEmployee_EmailID,
                          etEmployee_Cast,
                          etEmployee_AadharCardNumber,etEmployee_DLNumber,etEmployee_PANCardNumber,
                          etEmployee_VoterCardNumber,etEmployee_PassportNumber,

                          etEmployee_PresentDoorNumber,etEmployee_PresentStreet,
                          etEmployee_PresentLocality,etEmployee_PresentLandmark,
                          etEmployee_PresentPincode,etEmployee_PresentCity,
                          etEmployee_PresentState ,etEmployee_PresentCountry,
                          etEmployee_PresentContact,etEmployee_PermanentDoorNumber,
                          etEmployee_PermanentStreet,etEmployee_PermanentLocality,
                          etEmployee_PermanentLandmark,etEmployee_PermanentPincode,
                          etEmployee_PermanentCity,etEmployee_PermanentState,
                          etEmployee_PermanentCountry,etEmployee_PermanentContact,

                          etEmployee_FatherFirstName,etEmployee_FatherMiddleName,
                          etEmployee_FatherLastName,etEmployee_FatherDesignation,
                          etEmployee_FatherOrganisationName,etEmployee_FatherMobileNumber,
                          etEmployee_FatherEmail,etEmployee_MotherFirstName,

                          etEmployee_MotherMiddleName,etEmployee_MotherLastName,
                          etEmployee_MotherDesignation,etEmployee_MotherOrganisationName,
                          etEmployee_MotherMobileNumber,etEmployee_MotherEmail,

                          etEmployee_SpouseFName, etEmployee_SpouseMName,
                          etEmployee_SpouseLName, etEmployee_SpouseManniversary,
                          etEmployee_SpouseMobNum,etEmployee_SpouseDesignation,
                          etEmployee_SpouseOrganisationName,
                          etEmployee_SpouseEmailId,etEmployee_SpouseChildName,
                                  etEmployee_SpouseChildDOB,

                          etEmployee_BloodGroup,etEmployee_MedicalIssues,
                          etEmployee_DocNum,etEmployee_Height,etEmployee_Weight,
                          etEmployee_shortEyeVision,etEmployee_longEyeVision,child_StudyClassOrCourse,child_StudySchoolCollegeName,child_StudySchoolCollegeCity,
                                  child_JobRole,child_JobOfficeCity,child_JobOraganisatioName;


    Button                btnTeachingClick,btnNonTeachingClick,btnTransportClick,btnStaffOk;

    String                str_Employee_gender = "Male", maritalStatus = "Married",
                          str_comunityEmployee,str_Employee_FirstName,
                          str_Employee_MiddleName,str_Employee_LastName,
                          str_Employee_BirthPlace,str_Employee_DOB,
                          str_Employee_MobileNumber,str_Employee_Nationality,
                          str_Employee_EmailID,str_Employee_Cast,
                          str_Employee_Designation,str_Employee_GrossSalary,
                          str_Employee_Department, str_Employee_AadharCardNumber,
                          str_Employee_PANCardNumber,str_Employee_DLNumber,str_Employee_VoterCardNumber,
                          str_Employee_PassportNumber,str_Employee_PresentDoorNumber,
                          str_Employee_PresentStreet, str_Employee_PresentLocality,
                          str_Employee_PresentLandmark,str_Employee_PresentPincode,
                          str_Employee_PresentCity,str_Employee_PresentState,
                          str_Employee_PresentCountry,str_Employee_PresentContact,
                          str_Employee_PermanentDoorNumber, str_Employee_PermanentStreet,
                          str_Employee_PermanentLocality, str_Employee_PermanentLandmark,
                          str_Employee_PermanentPincode, str_Employee_PermanentCity,
                          str_Employee_PermanentState, str_Employee_PermanentCountry,
                          str_Employee_PermanentContact,str_Employee_FatherFirstName,
                          str_Employee_FatherMiddleName,str_Employee_FatherLastName,
                          str_Employee_FatherDesignation,str_Employee_FatherOrganisationName,
                          str_Employee_FatherMobileNumber,str_Employee_FatherEmail,
                          str_Employee_MotherFirstName,str_Employee_MotherMiddleName,
                          str_Employee_MotherLastName,str_Employee_MotherDesignation,
                          str_Employee_MotherOrganisationName,str_Employee_MotherMobileNumber,
                          str_Employee_MotherEmail,str_EmployeeSpouseOccupation,
                          str_EmployeeMotherOccupation,str_EmployeeFatherOccupation,
                          str_Employee_SpouseFName, str_Employee_SpouseMName,
                          str_Employee_SpouseLName, str_Employee_SpouseManniversary,
                          str_Employee_SpouseMobNum,str_Employee_SpouseDesignation,
                          str_Employee_SpouseOrganisationName,str_Employee_SpouseChild,
                          str_Employee_SpouseEmailId,str_EmployeeBloodGroup,str_EmployeeHealthIssue,
                          str_EmployeePersonalDoc,finalEmployeeId,str_EmployeeHeight,str_EmployeeWight,
                          str_EmployeeShortEyeVision,str_EmployeeLongEyeVision;


    ImageView             ivEmployee_AadharFront,ivEmployee_AadharBack,
                          ivEmployee_PANFront,ivEmployee_PANBack,
                          ivEmployee_DLFront,ivEmployee_DLBack,
                          ivEmployee_VoterIDFront,ivEmployee_VoterIDBack,
                          ivEmployee_PassportIDFront,ivEmployee_PassportIDBack;


    String                encodedAadharFrontImage,encodedAadharBackImage,staffSelection;


    Spinner               spEmployee_Community,spEmployee_Department,sp_Employee_Designation,spEmployee_FatherOccupation,
                          spEmployee_MotherOccupation,spEmployee_SpouseOccupation,spEmployee_BloodGroup,
                          childOccupation,spinnerStaff;


    Button                btnEmployee_PersonalSubmit,btnIncreaseCount,btnDecreaseCount;


    CheckBox              cbEmployee_Address;


    TextView              tvEmployee_ChildrensNumber;

    String passportRegex = "[A-Z]{1}[0-9]{7}";



    private Activity activity;
    private EducationInfoFragment educationInfoFragment;
    Activity parentActivity;




    Dialog                imageChooserDialog = null,dialogOfImageChange = null;

    Uri                   filePath,filePathAadharFront,filePathPANFront,
                          filePathVoterFront,filePathPassportFront,filePathAadharBack;

    Bitmap                bitmap;

    String                imagecase = "A";

    int                   max = 5,count = 0;

    Uri                   aadharFrontUri,aadharBackUri,panFrontUri,panBackUri,
                          voterFrontUri, voterBackUri, passportFrontUri, passportBackUri,
                          profileUri,dlFrontUri,dlBackUri;

    TextInputLayout txp;

    String selectedYear;
    String selectedMonth;
    String selectedDate;
    FirebaseDatabase mDataBase;
    DatabaseReference mRef;
    DatabaseReference snapShotRefrence;
    DatabaseReference staffRegistrationRef;
    DatabaseReference addEmployeeToRoles;
    InputStream imageStream = null;


    ProgressBar progressBar;

    String employeeDob,employeeAnnivarsary;

    StorageReference mStorageRef_Staff_profilePic;
    private int counter=0;
    private String timestampEmployee;
    private LinearLayout linearLayoutStudy;
    private LinearLayout linearLayoutWorking;



    ArrayList<LinearLayout> arrayListchildWorkLayout;
    ArrayList<LinearLayout> arrayListchildStudyLayout;
    ArrayList<EditText> arrayListchildName;
    ArrayList<EditText> arrayListchildDob;
    ArrayList<EditText> arrayListchildStudyClassorCourse;
    ArrayList<EditText> arrayListchildStudySchoolorCollegeName;
    ArrayList<EditText> arrayListchildStudySchoolorCollegeCity;
    ArrayList<EditText> arrayListchildJobRole;
    ArrayList<EditText> arrayListchildJobCity;
    ArrayList<EditText> arrayListchildJobOrganisationName;

    DatabaseReference staffSnapShotRef =FirebaseDatabase.getInstance().getReference("School/SchoolId/Staff_Details_SnapShot");
    DatabaseReference mref1 =FirebaseDatabase.getInstance().getReference("School/SchoolId/Staff_Registration");

    StorageReference mStoargeref_Staff_Details_Images;




    ArrayList<Spinner> arrayListchildOccupation;
    ArrayList<String> staffReg;



    private EditText etEmployee_DOJ;
    private String str_Employee_DOJ;
    private int educational_Details=1;
    private int work_Experience=1;
    private boolean checkBoxStatus;


    public PersonalInfoFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_personal_info, container, false);
        //openDialogForStaffChoose();
        //LandingFragment.selectedStaff = false;
        activity = (Activity) v.getContext();
        initViews(v);
        notificationStaffLogin();
        arrayListchildWorkLayout = new ArrayList<>();
        arrayListchildStudyLayout = new ArrayList<>();
        staffReg = new ArrayList<>();
        arrayListchildName = new ArrayList<>();
        arrayListchildDob = new ArrayList<>();
        arrayListchildStudyClassorCourse = new ArrayList<>();
        arrayListchildStudySchoolorCollegeName = new ArrayList<>();
        arrayListchildStudySchoolorCollegeCity = new ArrayList<>();
        arrayListchildJobRole = new ArrayList<>();
        arrayListchildJobCity = new ArrayList<>();
        arrayListchildJobOrganisationName = new ArrayList<>();




        mStoargeref_Staff_Details_Images = FirebaseStorage.getInstance().getReference("mStoargeref_Staff_Details_Images");
        arrayListchildOccupation = new ArrayList<>();
        mDataBase = FirebaseDatabase.getInstance();
        mRef      = mDataBase.getReference("School/SchoolId/Staff_Details_SnapShot");
        snapShotRefrence = mDataBase.getReference("School/SchoolId/Barriers/Staff_Registration_Ids");
        //mStorageRef = FirebaseStorage.getInstance().getReference("VARADHISMARTEK000/Employee Details");
        mStorageRef_Staff_profilePic = FirebaseStorage.getInstance().getReference("School/SchoolId/Staff_Details_SnapShot");
        staffRegistrationRef = FirebaseDatabase.getInstance().getReference("School/SchoolId/Staff_Registration");
        addEmployeeToRoles = FirebaseDatabase.getInstance().getReference("VARADHISMARTEK000/Barriers/Roles");
        progressBar =v.findViewById(R.id.progressBar);
        getTeachingStaffIDFromBarriers(Constant.STAFF_TYPE);
        setVisibilityBasedOnStaff(Constant.STAFF_TYPE);
        setHasOptionsMenu(true);
        initListners();
        initSpinnerForEmployeeComunity();
        initSpinnerForEmployeeDepartment();
        initSpinnerForEmployeeDesignation();
        initSpinnerForEmployeeFatherOccupation();
        initSpinnerForEmployeeMotherOccupation();
        initSpinnerForSpouseOccupation();
        initSpinnerForEmployeeBloodGroup();
        initSpinnerForStaff();
        employeedateOfBirthListner();
        //employeedateOfJoinListner();
        getStaffDetailsFromFirebase();
        employeeHeightListener();
        employeeWeightListener();
        shortEyeVision();
        longEyeVision();





        textWatcherForPincode();
        textWatcherForVoterID();
        textWatcherForAadharId();
        textWatcherForPassportId();





        return v;
    }

    private void longEyeVision() {

        etEmployee_longEyeVision.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {


                try {

                    if (editable.toString().startsWith(".")) {
                        editable.replace(0, editable.length(), "");

                    } else if (editable.toString().startsWith("0")) {
                        editable.replace(0, editable.length(), "");

                    } else if (Double.parseDouble(editable.toString()) > 20) {
                        editable.replace(0, editable.length(), "20");

                    }

                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                }

            }
        });
    }

    private void shortEyeVision() {

        etEmployee_shortEyeVision.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {


                try {

                    if (editable.toString().startsWith(".")) {
                        editable.replace(0, editable.length(), "");

                    } else if (editable.toString().startsWith("0")) {
                        editable.replace(0, editable.length(), "");

                    } else if (Double.parseDouble(editable.toString()) > 20) {
                        editable.replace(0, editable.length(), "20");

                    }

                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                }

            }
        });
    }

    private void employeeWeightListener() {
        etEmployee_Weight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {


                try {

                    if (editable.toString().startsWith(".")) {
                        editable.replace(0, editable.length(), "");

                    } else if (editable.toString().startsWith("0")) {
                        editable.replace(0, editable.length(), "");

                    } else if (Double.parseDouble(editable.toString()) > 150) {
                        editable.replace(0, editable.length(), "150");

                    }

                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                }

            }
        });
    }

    private void employeeHeightListener() {

        etEmployee_Height.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {


                try {

                    if (editable.toString().startsWith(".")) {
                        editable.replace(0, editable.length(), "");

                    } else if (editable.toString().startsWith("0")) {
                        editable.replace(0, editable.length(), "");

                    } else if (Double.parseDouble(editable.toString()) > 200) {
                        editable.replace(0, editable.length(), "200");

                    }

                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                }

            }
        });
    }

    private void getStaffDetailsFromFirebase() {

        staffSnapShotRef.child(Constant.STAFF_TYPE).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if(staffReg.size()>=0){
                    staffReg.clear();

                }

                Log.d("sksk", ""+dataSnapshot.getKey());

                for (DataSnapshot postSnapShotA : dataSnapshot.getChildren()){
                    Log.d("allregistartionNo", ""+postSnapShotA.getKey());
                    staffReg.add(postSnapShotA.getKey());

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    private void employeedateOfJoinListner() {


        etEmployee_DOJ.addTextChangedListener(new TextWatcher() {
            private String current = "";
            private String ddmmyyyy = "DDMMYYYY";
            private Calendar cal = Calendar.getInstance();

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @SuppressLint("DefaultLocale")
            @Override
            public void afterTextChanged(Editable s) {

                if (!s.toString().equals(current)){

                    String clean = s.toString().replaceAll("[^\\d.]|\\.", "");
                    String cleanC = current.replaceAll("[^\\d.]|\\.", "");

                    int cl = clean.length();
                    int sel = cl;
                    for (int i = 2; i <= cl && i < 6; i += 2) {
                        sel++;
                    }
                    //Fix for pressing delete next to a forward slash
                    if (clean.equals(cleanC)) sel--;

                    if (clean.length() < 8){
                        clean = clean + ddmmyyyy.substring(clean.length());
                    }else{
                        //This part makes sure that when we finish entering numbers
                        //the date is correct, fixing it otherwise
                        int day  = Integer.parseInt(clean.substring(0,2));
                        int mon  = Integer.parseInt(clean.substring(2,4));
                        int year = Integer.parseInt(clean.substring(4,8));
                        int currentYear = Calendar.getInstance().get(Calendar.YEAR);

                        day = day<1 ?1 : day >31 ?31 : day;
                        cal.set(Calendar.DAY_OF_MONTH, day);
                        mon = mon < 1 ? 1 : mon > 12 ? 12 : mon;
                        cal.set(Calendar.MONTH, mon-1);
                        year = (year<currentYear)?currentYear:(year>currentYear+1)?currentYear+1:year;
                        cal.set(Calendar.YEAR, +1);

                        day = (day > cal.getActualMaximum(Calendar.DATE))? cal.getActualMaximum(Calendar.DATE):day;
                        clean = String.format("%02d%02d%02d",day, mon, year);
                    }

                    clean = String.format("%s/%s/%s", clean.substring(0, 2),
                            clean.substring(2, 4),
                            clean.substring(4, 8));

                    sel = sel < 0 ? 0 : sel;
                    current = clean;


                    String d= current.substring(0,2);

                    Log.d("DDcurrent",""+current);

                    Log.d("DDDD",""+d);
                    String m = current.substring(3,5);
                    Log.d("MMM",""+m);

                    String y = current.substring(6,10);
                    Log.d("YYYY",""+y);

                    String newDate =String.valueOf(y) + "-" + String.valueOf(m)
                            + "-" +String.valueOf(d) ;

                    Log.d("NEWDAT",""+newDate);

                    str_Employee_DOJ = newDate;
                    etEmployee_DOJ.setText(current);
                    etEmployee_DOJ.setSelection(sel < current.length() ? sel : current.length());
                }

            }
        });
    }

    private void notificationStaffLogin() {

        //if(counter==0) {
            Snackbar.make(getActivity().findViewById(android.R.id.content),
                    "Logged in as " + Constant.STAFF_TYPE, Snackbar.LENGTH_LONG).show();
            counter++;
        //}
    }

    private void setVisibilityBasedOnStaff(String staffType) {

        switch (staffType){

            case "Teaching":
                //llMarriedVisible.setVisibility(View.VISIBLE);
                staffEmailId.setHint("Email Id*");
                ti_employeeDlNumber.setHint("Driving License Number");
                getActivity().setTitle("Teaching");

                break;


            case "Non_Teaching":
                //llMarriedVisible.setVisibility(View.VISIBLE);
                staffEmailId.setHint("Email Id*");
                ti_employeeDlNumber.setHint("Driving License Number");
                getActivity().setTitle("Non Teaching");

                break;

            case "Transportation":
                //llMarriedVisible.setVisibility(View.VISIBLE);
                staffEmailId.setHint("Email Id");
                ti_employeeDlNumber.setHint("Driving License Number*");
                getActivity().setTitle("Transportation");

                break;


        }
    }



    private void initSpinnerForStaff() {
    }

    private void textWatcherForPassportId() {
        etEmployee_PassportNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                try {
                    Log.d("Percentage", "input: " + editable);

                    if(editable.toString().startsWith("0")){
                        editable.replace(0, editable.length(), "");

                    }
                    /*if (editable.toString().matches(passportRegex)){

                    }
                    else {
                        etEmployee_PassportNumber.setError("Invalid passport");
                    }*/

                }
                catch(NumberFormatException nfe){
                    nfe.printStackTrace();
                }

            }
        });

    }

    private void textWatcherForAadharId() {
        etEmployee_AadharCardNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                try {
                    Log.d("Percentage", "input: " + editable);

                    if(editable.toString().startsWith("0")){
                        editable.replace(0, editable.length(), "");

                    }


                }
                catch(NumberFormatException nfe){
                    nfe.printStackTrace();
                }

            }
        });

    }

    private void textWatcherForVoterID() {
        etEmployee_VoterCardNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                try {
                    Log.d("Percentage", "input: " + editable);

                    if(editable.toString().startsWith("0")){
                        editable.replace(0, editable.length(), "");

                    }


                }
                catch(NumberFormatException nfe){
                    nfe.printStackTrace();
                }

            }
        });

    }

    private void initSpinnerForEmployeeBloodGroup() {

        ArrayList<String> employeeBloodArrayList = new ArrayList<String>();
        employeeBloodArrayList.add("Select");
        employeeBloodArrayList.add("O−");
        employeeBloodArrayList.add("O+");
        employeeBloodArrayList.add("A−");
        employeeBloodArrayList.add("A+");
        employeeBloodArrayList.add("B−");
        employeeBloodArrayList.add("B+");
        employeeBloodArrayList.add("AB−");
        employeeBloodArrayList.add("AB+");

        CustomSpinnerAdapter customSpinnerAdapter=new CustomSpinnerAdapter(getActivity(),employeeBloodArrayList);
        spEmployee_BloodGroup.setAdapter(customSpinnerAdapter);
        spEmployee_BloodGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(position!=0) {

                        etEmployee_BloodGroup.setText(parent.getItemAtPosition(position).toString());


                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    private void incrementEmployeeID() {

        if(Constant.STAFF_START_ID.equalsIgnoreCase(Constant.STAFF_DEFAULT_ID)){
            increment();
        }

        else {

            Constant.STAFF_CURRENT_ID = Constant.STAFF_DEFAULT_ID;
            Constant.STAFF_START_ID = Constant.STAFF_DEFAULT_ID;
            increment();

        }


    }

    private void increment() {

        int val =lastAlphaNumeric(Constant.STAFF_CURRENT_ID);
        String half1 = Constant.STAFF_CURRENT_ID.substring(0, val+1);
        String half2 = Constant.STAFF_CURRENT_ID.substring(val+1 , Constant.STAFF_CURRENT_ID.length());

        String newHalf2 = "9"+half2;

        int lastnumberincrease = Integer.parseInt((newHalf2))+1;

        String newHalf2Icreae = String.valueOf(lastnumberincrease);

        String finalNumber = newHalf2Icreae.substring(1,newHalf2Icreae.length() );

        finalEmployeeId = half1+finalNumber;
        Constant.STAFF_FINAL_ID =finalEmployeeId;
        Log.d("Incremet Id", ""+finalEmployeeId);
        Constant.REGISTRATION_CURRENT_TEMP_ID = Constant.STAFF_FINAL_ID;

    }

    private int lastAlphaNumeric(String employeeCurrentId) {
        for (int i = employeeCurrentId.length() - 1; i >= 0; i--) {
            char c = employeeCurrentId.charAt(i);
            if (Character.isLetter(c))
                return i;
        }
        return -1;
    }

    private void getTransportStaffIDFromBarriers(String s) {

        snapShotRefrence.child(s).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("dssdsd", ""+dataSnapshot.getKey());
                for(DataSnapshot postSnapShotA : dataSnapshot.getChildren()){
                    Log.d("postsnapShotA", ""+postSnapShotA.getKey());

                    if(postSnapShotA.getKey().equals("Default_Registration_Id")){
                        Log.d("staff_Default", ""+postSnapShotA.getValue());
                        Constant.STAFF_DEFAULT_ID = (String) postSnapShotA.getValue();

                    }

                    if(postSnapShotA.getKey().equals("Start_Registration_Id")){
                        Log.d("staff_Start", ""+postSnapShotA.getValue());
                        Constant.STAFF_START_ID = (String) postSnapShotA.getValue();
                        incrementEmployeeID();
                    }

                    if(postSnapShotA.getKey().equals("Current_Registration_Id")){
                        Log.d("current_Start", ""+postSnapShotA.getValue());
                        Constant.STAFF_CURRENT_ID = (String) postSnapShotA.getValue();

                    }
                    // progressBar.setVisibility(View.GONE);

                }



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    private void getNoNTeachingStaffIDFromBarriers(String s) {
        snapShotRefrence.child(s).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("dssdsd", ""+dataSnapshot.getKey());
                for(DataSnapshot postSnapShotA : dataSnapshot.getChildren()){
                    Log.d("postsnapShotA", ""+postSnapShotA.getKey());

                    if(postSnapShotA.getKey().equals("Default_Registration_Id")){
                        Log.d("staff_Default", ""+postSnapShotA.getValue());
                        Constant.STAFF_DEFAULT_ID = (String) postSnapShotA.getValue();

                    }

                    if(postSnapShotA.getKey().equals("Start_Registration_Id")){
                        Log.d("staff_Start", ""+postSnapShotA.getValue());
                        Constant.STAFF_START_ID = (String) postSnapShotA.getValue();
                        incrementEmployeeID();
                    }

                    if(postSnapShotA.getKey().equals("Current_Registration_Id")){
                        Log.d("current_Start", ""+postSnapShotA.getValue());
                        Constant.STAFF_CURRENT_ID = (String) postSnapShotA.getValue();

                    }
                   // progressBar.setVisibility(View.GONE);

                }



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void getTeachingStaffIDFromBarriers(String s) {
        //progressBar.setVisibility(View.VISIBLE);

        snapShotRefrence.child(s).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("dssdsd", ""+dataSnapshot.getKey());
                    for(DataSnapshot postSnapShotA : dataSnapshot.getChildren()){
                        Log.d("postsnapShotA", ""+postSnapShotA.getKey());

                                if(postSnapShotA.getKey().equals("Default_Registration_Id")){
                                    Log.d("staff_Default", ""+postSnapShotA.getValue());
                                    Constant.STAFF_DEFAULT_ID = (String) postSnapShotA.getValue();

                                }

                                if(postSnapShotA.getKey().equals("Start_Registration_Id")){
                                    Log.d("staff_Start", ""+postSnapShotA.getValue());
                                    Constant.STAFF_START_ID = (String) postSnapShotA.getValue();
                                    incrementEmployeeID();
                                }

                                if(postSnapShotA.getKey().equals("Current_Registration_Id")){
                                    Log.d("current_Start", ""+postSnapShotA.getValue());
                                    Constant.STAFF_CURRENT_ID = (String) postSnapShotA.getValue();

                                }
                                progressBar.setVisibility(View.GONE);

                            }



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });





    }

    private void textWatcherForPincode() {

            etEmployee_PresentPincode.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    int length = charSequence.length();
                    Log.d("textLength", ""+length);
                    if(length==6){
                        Log.d("textLength", ""+charSequence);

                        new AsynchTaskForStateAndCity(charSequence.toString(), Constant.PRESENT_PINCODE).execute();

                    }

                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });



            etEmployee_PermanentPincode.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    int length = charSequence.length();
                    Log.d("textLength", ""+length);
                    if(length==6){
                        Log.d("textLength", ""+charSequence);

                        new AsynchTaskForStateAndCity(charSequence.toString(), Constant.PERMANENT_PINCODE).execute();

                    }

                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });

        }


    private void initSpinnerForEmployeeDesignation() {

        ArrayList<String> employeeDesignationArrayList = new ArrayList<>();
        employeeDesignationArrayList.add("Designation");
        employeeDesignationArrayList.add("Jr Developer");
        employeeDesignationArrayList.add("Developer");
        employeeDesignationArrayList.add("ML");
        employeeDesignationArrayList.add("TL");
        employeeDesignationArrayList.add("Manager");
        employeeDesignationArrayList.add("Product Head");


        CustomSpinnerAdapter customSpinnerAdapter=new CustomSpinnerAdapter(getActivity(),employeeDesignationArrayList);
        sp_Employee_Designation.setAdapter(customSpinnerAdapter);
        sp_Employee_Designation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                // qualification_mother = parent.getItemAtPosition(position).toString();

                str_Employee_Designation = parent.getItemAtPosition(position).toString();

                //Toast.makeText(parent.getContext(), "Android Custom Spinner Example Output..." + item, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initSpinnerForEmployeeDepartment() {

        ArrayList<String> employeeDepartmentArrayList = new ArrayList<>();
        employeeDepartmentArrayList.add("Department");
        employeeDepartmentArrayList.add("Android");
        employeeDepartmentArrayList.add("Web");
        employeeDepartmentArrayList.add("iOS");
        employeeDepartmentArrayList.add("Backend");
        employeeDepartmentArrayList.add("Testing");
        employeeDepartmentArrayList.add("Graphic");


        CustomSpinnerAdapter customSpinnerAdapter=new CustomSpinnerAdapter(getActivity(),employeeDepartmentArrayList);
        spEmployee_Department.setAdapter(customSpinnerAdapter);
        spEmployee_Department.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                // qualification_mother = parent.getItemAtPosition(position).toString();

                str_Employee_Department = parent.getItemAtPosition(position).toString();

                //Toast.makeText(parent.getContext(), "Android Custom Spinner Example Output..." + item, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    private void employeedateOfBirthListner() {

        etEmployee_DOB.addTextChangedListener(new TextWatcher() {
            private String current = "";
            private String ddmmyyyy = "DDMMYYYY";
            private Calendar cal = Calendar.getInstance();

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @SuppressLint("DefaultLocale")
            @Override
            public void afterTextChanged(Editable s) {

                if (!s.toString().equals(current)){

                    String clean = s.toString().replaceAll("[^\\d.]|\\.", "");
                    String cleanC = current.replaceAll("[^\\d.]|\\.", "");

                    int cl = clean.length();
                    int sel = cl;
                    for (int i = 2; i <= cl && i < 6; i += 2) {
                        sel++;
                    }
                    //Fix for pressing delete next to a forward slash
                    if (clean.equals(cleanC)) sel--;

                    if (clean.length() < 8){
                        clean = clean + ddmmyyyy.substring(clean.length());
                    }else{
                        //This part makes sure that when we finish entering numbers
                        //the date is correct, fixing it otherwise
                        int day  = Integer.parseInt(clean.substring(0,2));
                        int mon  = Integer.parseInt(clean.substring(2,4));
                        int year = Integer.parseInt(clean.substring(4,8));
                        int currentYear = Calendar.getInstance().get(Calendar.YEAR);

                        day = day<1 ?1 : day >31 ?31 : day;
                        cal.set(Calendar.DAY_OF_MONTH, day);
                        mon = mon < 1 ? 1 : mon > 12 ? 12 : mon;
                        cal.set(Calendar.MONTH, mon-1);
                        year = (year<1970)?1970:(year>currentYear-18)?currentYear-18:year;
                        cal.set(Calendar.YEAR, -18);

                        day = (day > cal.getActualMaximum(Calendar.DATE))? cal.getActualMaximum(Calendar.DATE):day;
                        clean = String.format("%02d%02d%02d",day, mon, year);
                    }

                    clean = String.format("%s/%s/%s", clean.substring(0, 2),
                            clean.substring(2, 4),
                            clean.substring(4, 8));

                    sel = sel < 0 ? 0 : sel;
                    current = clean;


                    String d= current.substring(0,2);

                    Log.d("DDcurrent",""+current);

                    Log.d("DDDD",""+d);
                    String m = current.substring(3,5);
                    Log.d("MMM",""+m);

                    String y = current.substring(6,10);
                    Log.d("YYYY",""+y);

                    String newDate =String.valueOf(y) + "-" + String.valueOf(m)
                            + "-" +String.valueOf(d) ;

                    Log.d("NEWDAT",""+newDate);

                    str_Employee_DOB = newDate;
                    etEmployee_DOB.setText(current);
                    etEmployee_DOB.setSelection(sel < current.length() ? sel : current.length());
                }

            }
        });


    }

    private void initSpinnerForSpouseOccupation() {
        ArrayList<String> spouseoccupationArrayList = new ArrayList<>();
        spouseoccupationArrayList.add("-Occupation-");
        spouseoccupationArrayList.add("Business");
        spouseoccupationArrayList.add("Employee");
        spouseoccupationArrayList.add("Unemployee");
        CustomSpinnerAdapter customSpinnerAdapter=new CustomSpinnerAdapter(getActivity(),spouseoccupationArrayList);
        spEmployee_SpouseOccupation.setAdapter(customSpinnerAdapter);
        spEmployee_SpouseOccupation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                // qualification_mother = parent.getItemAtPosition(position).toString();

                str_EmployeeSpouseOccupation = parent.getItemAtPosition(position).toString();
                if(position==3 || position==0){
                    etEmployee_SpouseDesignation.setText("");
                    etEmployee_SpouseDesignation.setFocusable(false);
                    etEmployee_SpouseDesignation.setFocusableInTouchMode(false);

                    etEmployee_SpouseOrganisationName.setText("");
                    etEmployee_SpouseOrganisationName.setFocusable(false);
                    etEmployee_SpouseOrganisationName.setFocusableInTouchMode(false);
                }
                else {
                    etEmployee_SpouseDesignation.setFocusable(true);
                    etEmployee_SpouseDesignation.setFocusableInTouchMode(true);
                    etEmployee_SpouseOrganisationName.setFocusable(true);
                    etEmployee_SpouseOrganisationName.setFocusableInTouchMode(true);
                }


                //Toast.makeText(parent.getContext(), "Android Custom Spinner Example Output..." + item, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    private void initSpinnerForEmployeeMotherOccupation() {
        ArrayList<String> motheroccupationArrayList = new ArrayList<>();
        motheroccupationArrayList.add("-Occupation-");
        motheroccupationArrayList.add("Business");
        motheroccupationArrayList.add("Employee");
        motheroccupationArrayList.add("Unemployee");
        motheroccupationArrayList.add("Housewife");
        CustomSpinnerAdapter customSpinnerAdapter=new CustomSpinnerAdapter(getActivity(),motheroccupationArrayList);
        spEmployee_MotherOccupation.setAdapter(customSpinnerAdapter);
        spEmployee_MotherOccupation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                // qualification_mother = parent.getItemAtPosition(position).toString();

                str_EmployeeMotherOccupation = parent.getItemAtPosition(position).toString();


                if(position==4 || position==3 || position==0){
                    etEmployee_MotherDesignation.setText("");
                    etEmployee_MotherDesignation.setFocusable(false);
                    etEmployee_MotherDesignation.setFocusableInTouchMode(false);

                    etEmployee_MotherOrganisationName.setText("");
                    etEmployee_MotherOrganisationName.setFocusable(false);
                    etEmployee_MotherOrganisationName.setFocusableInTouchMode(false);
                }
                else {
                    etEmployee_MotherDesignation.setFocusable(true);
                    etEmployee_MotherDesignation.setFocusableInTouchMode(true);
                    etEmployee_MotherOrganisationName.setFocusable(true);
                    etEmployee_MotherOrganisationName.setFocusableInTouchMode(true);
                }
                //Toast.makeText(parent.getContext(), "Android Custom Spinner Example Output..." + item, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void initSpinnerForEmployeeFatherOccupation() {
        ArrayList<String> occupationArrayList = new ArrayList<>();
        occupationArrayList.add("-Occupation-");
        occupationArrayList.add("Business");
        occupationArrayList.add("Employee");
        occupationArrayList.add("Unemployee");
        CustomSpinnerAdapter customSpinnerAdapter=new CustomSpinnerAdapter(getActivity(),occupationArrayList);
        spEmployee_FatherOccupation.setAdapter(customSpinnerAdapter);
        spEmployee_FatherOccupation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                str_EmployeeFatherOccupation = parent.getItemAtPosition(position).toString();

                if(position==3 || position==0){
                    etEmployee_FatherDesignation.setText("");
                    etEmployee_FatherDesignation.setFocusable(false);
                    etEmployee_FatherDesignation.setFocusableInTouchMode(false);

                    etEmployee_FatherOrganisationName.setText("");
                    etEmployee_FatherOrganisationName.setFocusable(false);
                    etEmployee_FatherOrganisationName.setFocusableInTouchMode(false);
                }
                else {
                    etEmployee_FatherDesignation.setFocusable(true);
                    etEmployee_FatherDesignation.setFocusableInTouchMode(true);
                    etEmployee_FatherOrganisationName.setFocusable(true);
                    etEmployee_FatherOrganisationName.setFocusableInTouchMode(true);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    private void initSpinnerForEmployeeComunity() {
            ArrayList<String> employeeComunityArrayList = new ArrayList<String>();
            employeeComunityArrayList.add("Community");
            employeeComunityArrayList.add("General");
            employeeComunityArrayList.add("OBC");
            employeeComunityArrayList.add("SC/ST");
            employeeComunityArrayList.add("Others");
            CustomSpinnerAdapter customSpinnerAdapter=new CustomSpinnerAdapter(getActivity(),employeeComunityArrayList);
            spEmployee_Community.setAdapter(customSpinnerAdapter);
            spEmployee_Community.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    if(position!=0) {

                        str_comunityEmployee = parent.getItemAtPosition(position).toString();

                    }


                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });



    }

    private void initListners() {

        personalInfoLayout.setOnClickListener(this);
        idDeatailsLayout.setOnClickListener(this);
        addressDeatailsLayout.setOnClickListener(this);
        parentDeatailsLayout.setOnClickListener(this);
        maritalDeatailsLayout.setOnClickListener(this);
        medicalDeatailsLayout.setOnClickListener(this);


        personal_InfoLayoutClick.setOnClickListener(this);
        idProof_LayoutClick.setOnClickListener(this);
        addressDetails_LayoutClick.setOnClickListener(this);
        parentDetails_LayoutClick.setOnClickListener(this);
        maritalDetails_LayoutClick.setOnClickListener(this);
        medicalDetails_LayoutClick.setOnClickListener(this);


        textViewGenderMale.setOnClickListener(this);
        textViewGenderFemale.setOnClickListener(this);
        textViewGenderOther.setOnClickListener(this);
        textViewEmployeeMarried.setOnClickListener(this);
        textViewEmployeeUnmarried.setOnClickListener(this);



        btnEmployee_PersonalSubmit.setOnClickListener(this);
        ivEmployee_ProfilePicture.setOnClickListener(this);


        ivEmployee_AadharFront.setOnClickListener(this);
        ivEmployee_AadharBack.setOnClickListener(this);
        ivEmployee_DLFront.setOnClickListener(this);
        ivEmployee_DLBack.setOnClickListener(this);
        ivEmployee_PANFront.setOnClickListener(this);
        ivEmployee_PANBack.setOnClickListener(this);
        ivEmployee_VoterIDFront.setOnClickListener(this);
        ivEmployee_VoterIDBack.setOnClickListener(this);
        ivEmployee_PassportIDFront.setOnClickListener(this);
        ivEmployee_PassportIDBack.setOnClickListener(this);
        ivEmployee_Calendar.setOnClickListener(this);
        etEmployee_DOJ.setOnClickListener(this);
        etEmployee_DOB.setOnClickListener(this);
        cbEmployee_Address.setOnClickListener(this);
        btnIncreaseCount.setOnClickListener(this);
        btnDecreaseCount.setOnClickListener(this);
        etEmployee_SpouseManniversary.setOnClickListener(this);


    }

    private void initViews(View v) {

        txp  = v.findViewById(R.id.txl);

        personalInfoLayout                 = v.findViewById(R.id.linearLayout_PersonalProfile);
        idDeatailsLayout                   = v.findViewById(R.id.linearLayout_IDDetails);
        addressDeatailsLayout              = v.findViewById(R.id.linearLayout_AddressDetails);
        parentDeatailsLayout               = v.findViewById(R.id.linearLayout_ParentDetails);
        maritalDeatailsLayout              = v.findViewById(R.id.linearLayout_MaritalDetails);
        medicalDeatailsLayout              = v.findViewById(R.id.linearLayout_MedicalDetails);
        llMarriedVisible                   = v.findViewById(R.id.ll_married_visible);
        linear_LayoutHead                  = v.findViewById(R.id.linearLayoutHead);
        personal_InfoLayoutClick           = v.findViewById(R.id.personalInfoLayoutClick);
        idProof_LayoutClick                = v.findViewById(R.id.idProofLayoutClick);

        addressDetails_LayoutClick         = v.findViewById(R.id.addressDetailsLayoutClick);
        parentDetails_LayoutClick          = v.findViewById(R.id.parentDetailsLayoutClick);
        maritalDetails_LayoutClick         = v.findViewById(R.id.maritalDetailsLayoutClick);
        medicalDetails_LayoutClick         = v.findViewById(R.id.medicalDetailsLayoutClick);


        staffEmailId                       = v.findViewById(R.id.tiStaffEmail);

        linearLayoutPersonalProfileViews   = v.findViewById(R.id.linearLayout_PersonalProfileViews);
        linearLayoutIDDetailsViews         = v.findViewById(R.id.linearLayout_IDDetailsViews);
        linearLayoutAddressDetailsViews    = v.findViewById(R.id.linearLayout_AddressDetailsViews);
        linearLayoutParentDetailsViews     = v.findViewById(R.id.linearLayout_ParentDetailsViews);
        linearLayoutMaritalDetailsViews    = v.findViewById(R.id.linearLayout_MaritalDetailsViews);
        linearLayoutMedicalDetailsViews    = v.findViewById(R.id.linearLayout_MediacalDetailsViews);
        spouseLayout                       = v.findViewById(R.id.layoutForSpouse);
        layout_PermanentAddress            = v.findViewById(R.id.layoutPermanentAddress);


        linear_head_child                  = v.findViewById(R.id.linearHeadForChild);



        personalInfoImageViewDown          = v.findViewById(R.id.btnimageViewUpForPersonalInfo);
        idInfoImageViewDown                = v.findViewById(R.id.btnimageViewUpForIDDetail);
        addressImageViewDown               = v.findViewById(R.id.btnimageViewUpForAddressDetail);
        parentInfoImageViewDown            = v.findViewById(R.id.btnimageViewUpForParentDetail);
        maritalInfoImageViewDown           = v.findViewById(R.id.btnimageViewUpForMaritalDetail);
        medicalInfoImageViewDown           = v.findViewById(R.id.btnimageViewUpForMedicalDetail);
        ivEmployee_Calendar                = v.findViewById(R.id.ivCalendarEmployee);
        staff_Profile_Picture              = v.findViewById(R.id.staffProfileImage);



        personalInfoTextView               = v.findViewById(R.id.PersonalInfoTextView);
        iDInfoTextView                     = v.findViewById(R.id.IDDetailsTextView);
        addressInfoTextView                = v.findViewById(R.id.AddressDetailsTextView);
        parentInfoTextView                 = v.findViewById(R.id.ParentDetailsTextView);
        maritalInfoTextView                = v.findViewById(R.id.MaritalDetailsTextView);
        medicalInfoTextView                = v.findViewById(R.id.MedicalDetailsTextView);
        textViewGenderMale                 = v.findViewById(R.id.tvEmployeeMale);
        textViewGenderFemale               = v.findViewById(R.id.tvEmployeeFemale);
        textViewGenderOther                = v.findViewById(R.id.tvEmployeeOthers);
        textViewEmployeeMarried            = v.findViewById(R.id.tvEmployeeMarried);
        textViewEmployeeUnmarried          = v.findViewById(R.id.tvEmployeeUnMarried);
        //childNumber                        = v.findViewById(R.id.tvChildNumber);
        tvEmployee_ChildrensNumber         = v.findViewById(R.id.numberOfChildrens);
        staffRegistration_Number           = v.findViewById(R.id.staffRegistartionNumber);
        staff_Name                         = v.findViewById(R.id.staffName);
        staff_Dept                         = v.findViewById(R.id.staffDept);


        etEmployee_FirstName               = v.findViewById(R.id.etEmployeeFirstName);
        etEmployee_MiddleName              = v.findViewById(R.id.etEmployeeMiddleName);
        etEmployee_LastName                = v.findViewById(R.id.etEmployeeLastName);
        etEmployee_BirthPlace              = v.findViewById(R.id.etEmployeeBirthPlace);
        etEmployee_DOB                     = v.findViewById(R.id.etEmployeeDOB);
        etEmployee_DOJ                     = v.findViewById(R.id.etEmployeeDOJ);
        etEmployee_MobileNumber            = v.findViewById(R.id.etEmployeeMobileNumber);
        etEmployee_Nationality             = v.findViewById(R.id.etEmployeeNationality);
        etEmployee_EmailID                 = v.findViewById(R.id.etEmployeeEmailID);
        ti_employeeDlNumber                = v.findViewById(R.id.tiemployeeDlNumber);
        etEmployee_Cast                    = v.findViewById(R.id.etEmployeeCast);
        /*etEmployee_Designation             = v.findViewById(R.id.etEmployeeDesignation);
        etEmployee_GrossSalary             = v.findViewById(R.id.etEmployeeGrossSalary);
        etEmployee_Department              = v.findViewById(R.id.etEmployeeDepartment);
*/
        etEmployee_AadharCardNumber        = v.findViewById(R.id.etEmployeeAadharCardNumber);
        etEmployee_PANCardNumber           = v.findViewById(R.id.etEmployeePANCardNumber);
        etEmployee_DLNumber                = v.findViewById(R.id.etEmployeeDLNumber);
        etEmployee_VoterCardNumber         = v.findViewById(R.id.etEmployeeVoterCardNumber);
        etEmployee_PassportNumber          = v.findViewById(R.id.etEmployeePassportNumber);
        etEmployee_PresentDoorNumber       = v.findViewById(R.id.etEmployeePresentDoorNumber);
        etEmployee_PresentStreet           = v.findViewById(R.id.etEmployeePresentStreet);
        etEmployee_PresentLocality         = v.findViewById(R.id.etEmployeePresentLocality);
        etEmployee_PresentLandmark         = v.findViewById(R.id.etEmployeePresentLandmark);
        etEmployee_PresentPincode          = v.findViewById(R.id.etEmployeePresentPincode);
        etEmployee_PresentCity             = v.findViewById(R.id.etEmployeePresentCity);
        etEmployee_PresentState            = v.findViewById(R.id.etEmployeePresentState);
        etEmployee_PresentCountry          = v.findViewById(R.id.etEmployeePresentCountry);
        etEmployee_PresentContact          = v.findViewById(R.id.etEmployeePresentContact);
        etEmployee_PermanentDoorNumber     = v.findViewById(R.id.etEmployeePermanentDoorNumber);
        etEmployee_PermanentStreet         = v.findViewById(R.id.etEmployeePermanentStreet);
        etEmployee_PermanentLocality       = v.findViewById(R.id.etEmployeePermanentLocality);
        etEmployee_PermanentLandmark       = v.findViewById(R.id.etEmployeePermanentLandmark);
        etEmployee_PermanentPincode        = v.findViewById(R.id.etEmployeePermanentPincode);
        etEmployee_PermanentCity           = v.findViewById(R.id.etEmployeePermanentCity);
        etEmployee_PermanentState          = v.findViewById(R.id.etEmployeePermanentState);
        etEmployee_PermanentCountry        = v.findViewById(R.id.etEmployeePermanentCountry);
        etEmployee_PermanentContact        = v.findViewById(R.id.etEmployeePermanentContact);


        etEmployee_FatherFirstName         = v.findViewById(R.id.etEmployeeFatherFirstName);
        etEmployee_FatherMiddleName        = v.findViewById(R.id.etEmployeeFatherMiddleName);
        etEmployee_FatherLastName          = v.findViewById(R.id.etEmployeeFatherLastName);
        etEmployee_FatherDesignation       = v.findViewById(R.id.etEmployeeFatherDesignation);
        etEmployee_FatherOrganisationName  = v.findViewById(R.id.etEmployeeFatherOrganisationName);
        etEmployee_FatherMobileNumber      = v.findViewById(R.id.etEmployeeFatherMobileNumber);
        etEmployee_FatherEmail             = v.findViewById(R.id.etEmployeeFatherEmail);

        etEmployee_MotherFirstName         = v.findViewById(R.id.etEmployeeMotherFirstName);
        etEmployee_MotherMiddleName        = v.findViewById(R.id.etEmployeeMotherMiddleName);
        etEmployee_MotherLastName          = v.findViewById(R.id.etEmployeeMotherLastName);
        etEmployee_MotherDesignation       = v.findViewById(R.id.etEmployeeMotherDesignation);
        etEmployee_MotherOrganisationName  = v.findViewById(R.id.etEmployeeMotherOrganisationName);
        etEmployee_MotherMobileNumber      = v.findViewById(R.id.etEmployeeMotherMobileNumber);
        etEmployee_MotherEmail             = v.findViewById(R.id.etEmployeeMotherEmail);

        etEmployee_SpouseFName             = v.findViewById(R.id.etEmployeeSpouseFName);
        etEmployee_SpouseMName             = v.findViewById(R.id.etEmployeeSpouseMName);
        etEmployee_SpouseLName             = v.findViewById(R.id.etEmployeeSpouseLName);
        etEmployee_SpouseManniversary      = v.findViewById(R.id.etEmployeeSpouseManniversary);
        etEmployee_SpouseMobNum            = v.findViewById(R.id.etEmployeeSpouseMobNum);
        etEmployee_SpouseDesignation       = v.findViewById(R.id.etEmployeeSpouseDesignation);
        etEmployee_SpouseOrganisationName  = v.findViewById(R.id.etEmployeeSpouseOrganisationName);
        etEmployee_SpouseEmailId           = v.findViewById(R.id.etEmployeeSpouseEmailId);
        etEmployee_BloodGroup              = v.findViewById(R.id.etEmployeeBloodGroup);
        etEmployee_MedicalIssues           = v.findViewById(R.id.etEmployeeMedicalIssues);
        etEmployee_DocNum                  = v.findViewById(R.id.etEmployeeDocNum);
        etEmployee_Height                  = v.findViewById(R.id.et_height);
        etEmployee_Weight                  = v.findViewById(R.id.et_weight);
        etEmployee_shortEyeVision          = v.findViewById(R.id.et_Short_Eye);
        etEmployee_longEyeVision           = v.findViewById(R.id.et_long_eye);





        spEmployee_Community               = v.findViewById(R.id.spEmployeeCommunity);
        spEmployee_Department              =v.findViewById(R.id.spEmployeeDepartment);
        sp_Employee_Designation              =v.findViewById(R.id.spEmployeeDesignation);

        spEmployee_FatherOccupation        = v.findViewById(R.id.spEmployeeFatherOccupation);
        spEmployee_MotherOccupation        = v.findViewById(R.id.spEmployeeMotherOccupation);
        spEmployee_SpouseOccupation        = v.findViewById(R.id.spEmployeeSpouseOccupation);
        spEmployee_BloodGroup              = v.findViewById(R.id.spEmployeeBloodGroup);

        btnEmployee_PersonalSubmit         = v.findViewById(R.id.btnEmployeePersonalSubmit);


        ivEmployee_ProfilePicture          = v.findViewById(R.id.ivEmployeeProfilePicture);
        ivEmployee_AadharFront             = v.findViewById(R.id.ivEmployeeAadharFront);
        ivEmployee_AadharBack              = v.findViewById(R.id.ivEmployeeAadharBack);
        ivEmployee_DLFront                 = v.findViewById(R.id.ivEmployeeDLFront);
        ivEmployee_DLBack                 = v.findViewById(R.id.ivEmployeeDLBack);

        ivEmployee_PANFront                = v.findViewById(R.id.ivEmployeePANFront);
        ivEmployee_PANBack                 = v.findViewById(R.id.ivEmployeePANBack);
        ivEmployee_VoterIDFront            = v.findViewById(R.id.ivEmployeeVoterIDFront);
        ivEmployee_VoterIDBack             = v.findViewById(R.id.ivEmployeeVoterIDBack);
        ivEmployee_PassportIDFront         = v.findViewById(R.id.ivEmployeePassportIDFront);
        ivEmployee_PassportIDBack          = v.findViewById(R.id.ivEmployeePassportIDBack);

        cbEmployee_Address                 = v.findViewById(R.id.checkBoxAddressSameOrNot);

        btnIncreaseCount                   = v.findViewById(R.id.button_increase_count);
        btnDecreaseCount                   = v.findViewById(R.id.button_decrease_count);





    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            /*case R.id.btnStaffTeaching:
                iv_StaffImage.setBackground(getResources().getDrawable(R.drawable.ic_presentation));
                llMarriedVisible.setVisibility(View.VISIBLE);
                btnTeachingClick.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                btnNonTeachingClick.setBackground(getResources().getDrawable(R.drawable.shape_border));
                btnTransportClick.setBackground(getResources().getDrawable(R.drawable.shape_border));
                Constant.STAFF_TYPE= "Teaching";
                educationInfoFragment.staffStatus(getActivity(),Constant.STAFF_TYPE);
                getTeachingStaffIDFromBarriers(Constant.STAFF_TYPE);
                break;

            case R.id.btnStaffNonTeaching:

                iv_StaffImage.setBackground(getResources().getDrawable(R.drawable.ic_staff_people_group_in_a_circular_arrow));
                llMarriedVisible.setVisibility(View.VISIBLE);
                btnNonTeachingClick.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                btnTeachingClick.setBackground(getResources().getDrawable(R.drawable.shape_border));
                btnTransportClick.setBackground(getResources().getDrawable(R.drawable.shape_border));
                Constant.STAFF_TYPE= "Non_Teaching";
                educationInfoFragment.staffStatus(getActivity(),Constant.STAFF_TYPE);
                getNoNTeachingStaffIDFromBarriers(Constant.STAFF_TYPE);
                break;

            case R.id.btnStaffTransport:
                iv_StaffImage.setBackground(getResources().getDrawable(R.drawable.ic_driver));
                llMarriedVisible.setVisibility(View.GONE);
                btnTransportClick.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                btnNonTeachingClick.setBackground(getResources().getDrawable(R.drawable.shape_border));
                btnTeachingClick.setBackground(getResources().getDrawable(R.drawable.shape_border));
                Constant.STAFF_TYPE = "Transportation";
                educationInfoFragment.staffStatus(getActivity(),Constant.STAFF_TYPE);
                getTransportStaffIDFromBarriers(Constant.STAFF_TYPE);

                break;

            case R.id.btnStaffOk:
                chooseStaffDialog.dismiss();
                counterDialog = 1;
                break;
*/

            case R.id.checkBoxAddressSameOrNot:

                if(cbEmployee_Address.isChecked()){
                    checkBoxStatus = true;
                    layout_PermanentAddress.setVisibility(View.GONE);
                }
                else {
                    checkBoxStatus = false;
                    layout_PermanentAddress.setVisibility(View.VISIBLE);

                }
                break;



            case R.id.personalInfoLayoutClick:

                if(linearLayoutIDDetailsViews.getVisibility()==View.VISIBLE) {
                     idProofInfoVisibilityGone();
                }
                if(linearLayoutAddressDetailsViews.getVisibility()==View.VISIBLE) {
                    addressInfoVisibilityGone();

                }
                if(linearLayoutParentDetailsViews.getVisibility()==View.VISIBLE) {
                    parentInfoVisibilityGone();

                }
                if(linearLayoutMaritalDetailsViews.getVisibility()==View.VISIBLE) {
                    maritalInfoVisibilityGone();
                }
                if(linearLayoutMedicalDetailsViews.getVisibility()==View.VISIBLE) {
                    medicalInfoVisibilityGone();
                }

                if(linearLayoutPersonalProfileViews.getVisibility()==View.GONE){

                    personalInfoVisibilityVisible();

                }
                else if(linearLayoutPersonalProfileViews.getVisibility()==View.VISIBLE) {
                    personalInfoVisibilityGone();

                }


                break;




            case R.id.idProofLayoutClick:

                if(linearLayoutPersonalProfileViews.getVisibility()==View.VISIBLE) {

                personalInfoVisibilityGone();
                }

                if(linearLayoutAddressDetailsViews.getVisibility()==View.VISIBLE) {
                    addressInfoVisibilityGone();

                }
                if(linearLayoutParentDetailsViews.getVisibility()==View.VISIBLE) {
                    parentInfoVisibilityGone();

                }
                if(linearLayoutMaritalDetailsViews.getVisibility()==View.VISIBLE) {
                    maritalInfoVisibilityGone();
                }
                if(linearLayoutMedicalDetailsViews.getVisibility()==View.VISIBLE) {
                    medicalInfoVisibilityGone();
                }

                if(linearLayoutIDDetailsViews.getVisibility()==View.GONE){
                    idProofInfoVisibilityVisible();


                }
                else if(linearLayoutIDDetailsViews.getVisibility()==View.VISIBLE) {
                    idProofInfoVisibilityGone();

                }


                break;


            case R.id.addressDetailsLayoutClick:

                if(linearLayoutPersonalProfileViews.getVisibility()==View.VISIBLE) {

                    personalInfoVisibilityGone();
                }

                if(linearLayoutIDDetailsViews.getVisibility()==View.VISIBLE) {
                    idProofInfoVisibilityGone();

                }
                if(linearLayoutParentDetailsViews.getVisibility()==View.VISIBLE) {
                    parentInfoVisibilityGone();

                }
                if(linearLayoutMaritalDetailsViews.getVisibility()==View.VISIBLE) {
                    maritalInfoVisibilityGone();
                }
                if(linearLayoutMedicalDetailsViews.getVisibility()==View.VISIBLE) {
                    medicalInfoVisibilityGone();
                }



                if(linearLayoutAddressDetailsViews.getVisibility()==View.GONE){
                    addressInfoVisibilityVisible();


                }
                else if(linearLayoutAddressDetailsViews.getVisibility()==View.VISIBLE) {
                    addressInfoVisibilityGone();

                }

                break;



            case R.id.parentDetailsLayoutClick:

                if(linearLayoutPersonalProfileViews.getVisibility()==View.VISIBLE) {

                    personalInfoVisibilityGone();
                }

                if(linearLayoutIDDetailsViews.getVisibility()==View.VISIBLE) {
                    idProofInfoVisibilityGone();

                }
                if(linearLayoutAddressDetailsViews.getVisibility()==View.VISIBLE) {
                    addressInfoVisibilityGone();

                }
                if(linearLayoutMaritalDetailsViews.getVisibility()==View.VISIBLE) {
                    maritalInfoVisibilityGone();
                }
                if(linearLayoutMedicalDetailsViews.getVisibility()==View.VISIBLE) {
                    medicalInfoVisibilityGone();
                }


                if(linearLayoutParentDetailsViews.getVisibility()==View.GONE){

                    parentInfoVisibilityVisible();



                }
                else if(linearLayoutParentDetailsViews.getVisibility()==View.VISIBLE) {
                    parentInfoVisibilityGone();

                }


                break;


            case R.id.maritalDetailsLayoutClick:

                if(linearLayoutPersonalProfileViews.getVisibility()==View.VISIBLE) {

                    personalInfoVisibilityGone();
                }

                if(linearLayoutIDDetailsViews.getVisibility()==View.VISIBLE) {
                    idProofInfoVisibilityGone();

                }
                if(linearLayoutAddressDetailsViews.getVisibility()==View.VISIBLE) {
                    addressInfoVisibilityGone();

                }
                if(linearLayoutParentDetailsViews.getVisibility()==View.VISIBLE) {
                    parentInfoVisibilityGone();

                }
                if(linearLayoutMedicalDetailsViews.getVisibility()==View.VISIBLE) {
                    medicalInfoVisibilityGone();
                }

                if(linearLayoutMaritalDetailsViews.getVisibility()==View.GONE){

                    maritalInfoVisibilityVisible();

                }
                else if(linearLayoutMaritalDetailsViews.getVisibility()==View.VISIBLE) {

                    maritalInfoVisibilityGone();

                }


                break;


            case R.id.medicalDetailsLayoutClick:

                if(linearLayoutPersonalProfileViews.getVisibility()==View.VISIBLE) {
                    personalInfoVisibilityGone();
                }

                if(linearLayoutIDDetailsViews.getVisibility()==View.VISIBLE) {
                    idProofInfoVisibilityGone();

                }
                if(linearLayoutAddressDetailsViews.getVisibility()==View.VISIBLE) {
                    addressInfoVisibilityGone();

                }
                if(linearLayoutParentDetailsViews.getVisibility()==View.VISIBLE) {
                    parentInfoVisibilityGone();

                }
                if(linearLayoutMaritalDetailsViews.getVisibility()==View.VISIBLE) {
                    maritalInfoVisibilityGone();
                }

                if(linearLayoutMedicalDetailsViews.getVisibility()==View.GONE){
                    medicalInfoVisibilityVisible();

                }
                else if(linearLayoutMedicalDetailsViews.getVisibility()==View.VISIBLE) {
                    medicalInfoVisibilityGone();
                }


                break;


            case R.id.button_increase_count:
                if(count!=max) {
                    count++;
                    btnDecreaseCount.setEnabled(true);
                    displayNumberOfChild(count);
                    View view = getLayoutInflater().inflate(R.layout.dymanic_child_layout, null);
                    childNumber = view.findViewById(R.id.tvChildNumber);
                    etEmployee_SpouseChildName = view.findViewById(R.id.etChildName);
                    etEmployee_SpouseChildDOB = view.findViewById(R.id.etChildDob);
                    arrayListchildName.add(etEmployee_SpouseChildName);
                    arrayListchildDob.add(etEmployee_SpouseChildDOB);
                    etEmployee_SpouseChildDOB.setId(1000+count);
                    etEmployee_SpouseChildDOB.setOnClickListener(this);
                    childOccupation = view.findViewById(R.id.spChildOccupation);
                    arrayListchildOccupation.add(childOccupation);
                    add_ChileDetailsLayout = view.findViewById(R.id.addChileDetailsLayout);
                    child_StudyClassOrCourse = view.findViewById(R.id.childClassCourse);
                    child_StudySchoolCollegeName = view.findViewById(R.id.childSchoolCollegeName);
                    child_StudySchoolCollegeCity = view.findViewById(R.id.childSchoolCollegeCity);
                    child_JobRole = view.findViewById(R.id.childOfficeRole);
                    child_JobOfficeCity = view.findViewById(R.id.childOfficeCity);
                    child_JobOraganisatioName = view.findViewById(R.id.childOfficeOrganisation);
                    arrayListchildStudyClassorCourse.add(child_StudyClassOrCourse);
                    arrayListchildStudySchoolorCollegeName.add(child_StudySchoolCollegeName);
                    arrayListchildStudySchoolorCollegeCity.add(child_StudySchoolCollegeCity);
                    arrayListchildJobRole.add(child_JobRole);
                    arrayListchildJobCity.add(child_JobOfficeCity);
                    arrayListchildJobOrganisationName.add(child_JobOraganisatioName);
                    //etEmployee_SpouseChildDOB.setOnClickListener(this);




                    linearLayoutStudy = view.findViewById(R.id.layoutStudy);
                    linearLayoutWorking= view.findViewById(R.id.layoutWorking);
                    arrayListchildStudyLayout.add(linearLayoutStudy);
                    arrayListchildWorkLayout.add(linearLayoutWorking);
                    childOccupation.setId(count+1);
                    childOccupation.setOnItemSelectedListener(this);
                    showChildOccupationInSpinner();
                    linear_head_child.addView(view,0);
                    childNumber.setText(""+count);



                }

                else {
                    btnIncreaseCount.setEnabled(false);
                }

                break;



            case 1001:
                showDob(1);
                break;

            case 1002:
                showDob(2);
                break;

            case 1003:
                showDob(3);
                break;

            case 1004:
                showDob(4);
                break;

            case 1005:
                showDob(5);
                break;


            case R.id.etEmployeeDOJ:
                Calendar calendar1 = Calendar.getInstance();
                calendar1.add(Calendar.MONTH,3);
                long afterThreeMonthsinMilli=calendar1.getTimeInMillis();

                final SimpleDateFormat dateForm1 = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
                DatePickerDialog datePicker1 = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {

                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Calendar newDate = Calendar.getInstance();
                        newDate.set(year, monthOfYear, dayOfMonth);
                        System.out.print(dateForm1.format(newDate.getTime()));
                        etEmployee_DOJ.setText(dateForm1.format(newDate.getTime()));

                    }

                }, calendar1.get(Calendar.YEAR), calendar1.get(Calendar.MONTH), calendar1.get(Calendar.DAY_OF_MONTH));
                datePicker1.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datePicker1.getDatePicker().setMaxDate(afterThreeMonthsinMilli);

                datePicker1.show();



                break;


            case R.id.etEmployeeDOB:
                Calendar calendar2 = Calendar.getInstance();
                calendar2.add(Calendar.YEAR,-18);
                long afterThreeMonthsinMilli1=calendar2.getTimeInMillis();

                final SimpleDateFormat dateForm2 = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
                DatePickerDialog datePicker2 = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {

                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Calendar newDate = Calendar.getInstance();
                        newDate.set(year, monthOfYear, dayOfMonth);
                        System.out.print(dateForm2.format(newDate.getTime()));
                        etEmployee_DOB.setText(dateForm2.format(newDate.getTime()));

                    }

                }, calendar2.get(Calendar.YEAR), calendar2.get(Calendar.MONTH), calendar2.get(Calendar.DAY_OF_MONTH));
                //datePicker2.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datePicker2.getDatePicker().setMaxDate(afterThreeMonthsinMilli1);

                datePicker2.show();

                break;

            case R.id.button_decrease_count:

                if(count!=0){
                    count--;
                    btnIncreaseCount.setEnabled(true);
                    displayNumberOfChild(count);
                    linear_head_child.removeViewAt(0);
                    arrayListchildWorkLayout.remove(count);
                    arrayListchildStudyLayout.remove(count);
                    arrayListchildName.remove(count);
                    arrayListchildDob.remove(count);
                    arrayListchildStudyClassorCourse.remove(count);
                    arrayListchildStudySchoolorCollegeName.remove(count);
                    arrayListchildStudySchoolorCollegeCity.remove(count);
                    arrayListchildJobRole.remove(count);
                    arrayListchildJobCity.remove(count);
                    arrayListchildJobOrganisationName.remove(count);


                }

                else{
                    btnDecreaseCount.setEnabled(false);
                }
                break;

            case R.id.tvEmployeeMale:
                str_Employee_gender = "Male";
                textViewGenderMale.setTextColor(getResources().getColor(R.color.whitecolor));
                textViewGenderMale.setBackground(getResources().getDrawable(R.drawable.btn_round_shape_blue));
                textViewGenderFemale.setBackground(getResources().getDrawable(R.drawable.button_design));
                textViewGenderOther.setBackground(getResources().getDrawable(R.drawable.button_design));
                textViewGenderFemale.setTextColor(getResources().getColor(R.color.hintColor));
                textViewGenderOther.setTextColor(getResources().getColor(R.color.hintColor));

                break;


            case R.id.tvEmployeeFemale:
                str_Employee_gender = "Female";
                textViewGenderFemale.setTextColor(getResources().getColor(R.color.whitecolor));
                textViewGenderFemale.setBackground(getResources().getDrawable(R.drawable.btn_round_shape_blue));
                textViewGenderMale.setBackground(getResources().getDrawable(R.drawable.button_design));
                textViewGenderOther.setBackground(getResources().getDrawable(R.drawable.button_design));
                textViewGenderMale.setTextColor(getResources().getColor(R.color.hintColor));
                textViewGenderOther.setTextColor(getResources().getColor(R.color.hintColor));
                break;


            case R.id.tvEmployeeOthers:
                str_Employee_gender = "Other";
                textViewGenderOther.setTextColor(getResources().getColor(R.color.whitecolor));
                textViewGenderOther.setBackground(getResources().getDrawable(R.drawable.btn_round_shape_blue));
                textViewGenderMale.setBackground(getResources().getDrawable(R.drawable.button_design));
                textViewGenderFemale.setBackground(getResources().getDrawable(R.drawable.button_design));
                textViewGenderMale.setTextColor(getResources().getColor(R.color.hintColor));
                textViewGenderFemale.setTextColor(getResources().getColor(R.color.hintColor));
                break;


            case R.id.tvEmployeeMarried:
                maritalStatus = "Married";
                textViewEmployeeMarried.setTextColor(getResources().getColor(R.color.whitecolor));
                textViewEmployeeMarried.setBackground(getResources().getDrawable(R.drawable.btn_round_shape_blue));
                textViewEmployeeUnmarried.setBackground(getResources().getDrawable(R.drawable.button_design));
                textViewEmployeeUnmarried.setTextColor(getResources().getColor(R.color.hintColor));
                spouseLayout.setVisibility(View.VISIBLE);

                break;

            case R.id.tvEmployeeUnMarried:
                maritalStatus = "UnMarried";
                textViewEmployeeUnmarried.setTextColor(getResources().getColor(R.color.whitecolor));
                textViewEmployeeUnmarried.setBackground(getResources().getDrawable(R.drawable.btn_round_shape_blue));
                textViewEmployeeMarried.setBackground(getResources().getDrawable(R.drawable.button_design));
                textViewEmployeeMarried.setTextColor(getResources().getColor(R.color.hintColor));
                spouseLayout.setVisibility(View.GONE);
                break;



            case R.id.ivCalendarEmployee:

                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.YEAR, -18);

                final SimpleDateFormat dateForm = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
                DatePickerDialog datePicker = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {

                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Calendar newDate = Calendar.getInstance();
                        newDate.set(year, monthOfYear, dayOfMonth);
                        System.out.print(dateForm.format(newDate.getTime()));
                        etEmployee_DOB.setText(dateForm.format(newDate.getTime()));

                    }

                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePicker.show();
                /*final Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.datepicker);
                dialog.setTitle("");
                DatePicker datePicker = dialog.findViewById(R.id.datePickerDialog);
                //datePicker.setMinDate(1/ 1 /1998);
                final Calendar calendar = Calendar.getInstance();
                selectedYear = String.valueOf(calendar.get(Calendar.YEAR));
                selectedMonth = String.valueOf(calendar.get(Calendar.MONTH));
                selectedDate = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
                calendar.add(Calendar.YEAR, -20);

                if(selectedDate.length()==1){
                    selectedDate = "0"+selectedDate;
                }

                if(selectedMonth.length()==1){
                    selectedMonth = "0"+selectedMonth;
                }

                Log.e("selected date", selectedDate+"");
                Log.e("selected month", selectedMonth+"");
                Log.e("selected year", selectedYear+"");


                datePicker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() {

                    @Override
                    public void onDateChanged(DatePicker datePicker, int year, int month, int dayOfMonth) {

                        String newMonth= "" ,stringNewDate="", stringyear = String.valueOf(year);

                        if(String.valueOf(month+1).length()==1){
                            newMonth = "0"+(month+1);
                        }
                        else {
                            newMonth = String.valueOf(month+1);
                        }

                        if(String.valueOf(dayOfMonth).length()==1){
                            stringNewDate = "0"+dayOfMonth;
                        }
                        else {
                            stringNewDate = String.valueOf(dayOfMonth);
                        }
                        Log.e("month", ""+newMonth);
                        Log.e("date", ""+stringNewDate);
                        Log.e("year", ""+stringyear);


                        if(selectedDate.equals(stringNewDate) && selectedMonth.equals(newMonth) && selectedYear.equals(stringyear)) {
                            String date = String.valueOf(stringNewDate) + "/" + String.valueOf(newMonth)
                                    + "/" + String.valueOf(stringyear);

                            etEmployee_DOB.setText(date);

                            dialog.dismiss();
                        }else {

                            if(!selectedDate.equals(stringNewDate)){
                                String date = String.valueOf(stringNewDate) + "/" + String.valueOf(newMonth)
                                        + "/" + String.valueOf(stringyear);

                                etEmployee_DOB.setText(date);

                                dialog.dismiss();
                            }else {
                                if(!selectedMonth.equals(newMonth)){
                                    String date = String.valueOf(stringNewDate) + "/" + String.valueOf(newMonth)
                                            + "/" + String.valueOf(stringyear);

                                    etEmployee_DOB.setText(date);
                                    dialog.dismiss();
                                }
                            }
                        }
                        selectedDate=String.valueOf(dayOfMonth);
                        selectedMonth=String.valueOf(month);
                        selectedYear=String.valueOf(year);
                    }
                });
                dialog.show();
                datePicker.setMaxDate(calendar.getTimeInMillis());*/


                break;


            case R.id.etEmployeeSpouseManniversary:

                Calendar calendar4 = Calendar.getInstance();
                calendar4.add(Calendar.YEAR, 0);

                final SimpleDateFormat dateForm4 = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
                DatePickerDialog datePicker4 = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {

                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Calendar newDate = Calendar.getInstance();
                        newDate.set(year, monthOfYear, dayOfMonth);
                        System.out.print(dateForm4.format(newDate.getTime()));
                        etEmployee_SpouseManniversary.setText(dateForm4.format(newDate.getTime()));

                    }

                }, calendar4.get(Calendar.YEAR), calendar4.get(Calendar.MONTH), calendar4.get(Calendar.DAY_OF_MONTH));
                datePicker4.show();

                /*final Dialog dialog1 = new Dialog(getActivity());
                dialog1.setContentView(R.layout.datepicker);
                dialog1.setTitle("");
                DatePicker datePicker1 = dialog1.findViewById(R.id.datePickerDialog);
                //datePicker.setMinDate(1/ 1 /1998);
                final Calendar calendar1 = Calendar.getInstance();
                selectedYear = String.valueOf(calendar1.get(Calendar.YEAR));
                selectedMonth = String.valueOf(calendar1.get(Calendar.MONTH));
                selectedDate = String.valueOf(calendar1.get(Calendar.DAY_OF_MONTH));
                calendar1.add(Calendar.YEAR, 0);

                if(selectedDate.length()==1){
                    selectedDate = "0"+selectedDate;
                }

                if(selectedMonth.length()==1){
                    selectedMonth = "0"+selectedMonth;
                }

                Log.e("selected date", selectedDate+"");
                Log.e("selected month", selectedMonth+"");
                Log.e("selected year", selectedYear+"");


                datePicker1.init(calendar1.get(Calendar.YEAR), calendar1.get(Calendar.MONTH), calendar1.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() {

                    @Override
                    public void onDateChanged(DatePicker datePicker, int year, int month, int dayOfMonth) {

                        String newMonth= "" ,stringNewDate="", stringyear = String.valueOf(year);

                        if(String.valueOf(month+1).length()==1){
                            newMonth = "0"+(month+1);
                        }
                        else {
                            newMonth = String.valueOf(month+1);
                        }

                        if(String.valueOf(dayOfMonth).length()==1){
                            stringNewDate = "0"+dayOfMonth;
                        }
                        else {
                            stringNewDate = String.valueOf(dayOfMonth);
                        }
                        Log.e("month", ""+newMonth);
                        Log.e("date", ""+stringNewDate);
                        Log.e("year", ""+stringyear);


                        if(selectedDate.equals(stringNewDate) && selectedMonth.equals(newMonth) && selectedYear.equals(stringyear)) {
                            String date = String.valueOf(stringNewDate) + "/" + String.valueOf(newMonth)
                                    + "/" + String.valueOf(stringyear);

                            etEmployee_SpouseManniversary.setText(date);

                            dialog1.dismiss();
                        }else {

                            if(!selectedDate.equals(stringNewDate)){
                                String date = String.valueOf(stringNewDate) + "/" + String.valueOf(newMonth)
                                        + "/" + String.valueOf(stringyear);

                                etEmployee_SpouseManniversary.setText(date);

                                dialog1.dismiss();
                            }else {
                                if(!selectedMonth.equals(newMonth)){
                                    String date = String.valueOf(stringNewDate) + "/" + String.valueOf(newMonth)
                                            + "/" + String.valueOf(stringyear);

                                    etEmployee_SpouseManniversary.setText(date);
                                    dialog1.dismiss();
                                }
                            }
                        }
                        selectedDate=String.valueOf(dayOfMonth);
                        selectedMonth=String.valueOf(month);
                        selectedYear=String.valueOf(year);
                    }
                });
                dialog1.show();
                datePicker1.setMaxDate(calendar1.getTimeInMillis());*/
                break;



            case R.id.ivEmployeeProfilePicture:
                imagecase = "A";
                openDialogForImageChoose();
                break;

            case R.id.ivEmployeeAadharFront:
                imagecase = "B";
                openDialogForImageChoose();
                break;


            case R.id.ivEmployeeAadharBack:
                imagecase = "C";
                openDialogForImageChoose();
                break;

            case R.id.ivEmployeePANFront:
                imagecase = "D";
                openDialogForImageChoose();
                break;


            case R.id.ivEmployeePANBack:
                imagecase = "E";
                openDialogForImageChoose();
                break;

            case R.id.ivEmployeeVoterIDFront:
                imagecase = "F";
                openDialogForImageChoose();
                break;

            case R.id.ivEmployeeVoterIDBack:
                imagecase = "G";
                openDialogForImageChoose();
                break;

            case R.id.ivEmployeePassportIDFront:
                imagecase = "H";
                openDialogForImageChoose();
                break;


            case R.id.ivEmployeePassportIDBack:
                imagecase = "I";
                openDialogForImageChoose();
                break;

            case R.id.ivEmployeeDLFront:
                imagecase = "J";
                openDialogForImageChoose();
                break;

            case R.id.ivEmployeeDLBack:
                imagecase = "K";
                openDialogForImageChoose();
                break;



            case R.id.camera:
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, Constant.FROM_CAMERA);
                imageChooserDialog.dismiss();
                break;

            case R.id.gallery:

                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, Constant.FROM_GALLERY);


                /*Intent intent = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                intent.setType("image*//*");
                intent.putExtra("crop", "true");
                intent.putExtra("scale", true);
                intent.putExtra("outputX", 400);
                intent.putExtra("outputY", 256);
                intent.putExtra("aspectX", 1);
                intent.putExtra("aspectY", 1);
                intent.putExtra("return-data", true);
                startActivityForResult(intent, Constant.FROM_GALLERY);*/

                imageChooserDialog.dismiss();
                break;


            case R.id.etChildDob:

                Calendar calendar3 = Calendar.getInstance();
                calendar3.add(Calendar.YEAR, 0);

                final SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {

                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Calendar newDate = Calendar.getInstance();
                        newDate.set(year, monthOfYear, dayOfMonth);
                        System.out.print(dateFormatter.format(newDate.getTime()));
                        etEmployee_SpouseChildDOB.setText(dateFormatter.format(newDate.getTime()));

                    }

                }, calendar3.get(Calendar.YEAR), calendar3.get(Calendar.MONTH), calendar3.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();



                break;


            case R.id.btnEmployeePersonalSubmit:

                validateData();

                break;

        }

    }

    private void showDob(final int i) {

        Calendar calendarChild1 = Calendar.getInstance();
        calendarChild1.add(Calendar.YEAR, -1);

        final SimpleDateFormat dateFormChild1 = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        DatePickerDialog datePickerChild1 = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                switch (i){

                    case 1:
                        arrayListchildDob.get(0).setText(dateFormChild1.format(newDate.getTime()));
                        break;

                    case 2:
                        arrayListchildDob.get(1).setText(dateFormChild1.format(newDate.getTime()));
                        break;

                    case 3:
                        arrayListchildDob.get(2).setText(dateFormChild1.format(newDate.getTime()));
                        break;

                    case 4:
                        arrayListchildDob.get(3).setText(dateFormChild1.format(newDate.getTime()));
                        break;

                    case 5:
                        arrayListchildDob.get(4).setText(dateFormChild1.format(newDate.getTime()));
                        break;

                }


            }

        }, calendarChild1.get(Calendar.YEAR), calendarChild1.get(Calendar.MONTH), calendarChild1.get(Calendar.DAY_OF_MONTH));
        datePickerChild1.show();
    }


    private void validateData() {


        switch(Constant.STAFF_TYPE){
            case "Teaching":
                teacherDataValidation();
                break;


            case "Non_Teaching":
                teacherDataValidation();//same Ui as Teacher UI
                break;


            case "Transportation":
                transportDataValidation();
                break;

        }


    }

    private void transportDataValidation() {
        if(ivEmployee_ProfilePicture.getDrawable()==null){
            personalInfoValidation();
            Toast.makeText(getActivity(), "Upload employee Image", Toast.LENGTH_SHORT).show();
        }

        else if(!ValidationViews.EditTextNullValidate(etEmployee_FirstName)){
            personalInfoValidation();
        }
        else if(!ValidationViews.EditTextNullValidate(etEmployee_LastName)){
            personalInfoValidation();
        }
        else if(!ValidationViews.EditTextNullValidate(etEmployee_BirthPlace)){
            personalInfoValidation();
        }
        else if(!ValidationViews.EditTextNullValidate(etEmployee_DOB)){
            personalInfoValidation();
        }

        else if(!ValidationViews.EditTextNullValidate(etEmployee_DOJ)){
            personalInfoValidation();
        }

        else if(!ValidationViews.EditTextNullValidate(etEmployee_Nationality)){
            personalInfoValidation();
        }

        else if(!ValidationViews.EditTextNullValidate(etEmployee_MobileNumber)){
            personalInfoValidation();

        }

        else if(!ValidationViews.EditTextMobileNumberValidate(etEmployee_MobileNumber)){
            personalInfoValidation();
        }


        else if(!ValidationViews.EditTextEmailPatternValidate(etEmployee_EmailID)){
            personalInfoValidation();
        }

        else if(spEmployee_Community.getSelectedItemPosition()==0){
            personalInfoValidation();
            Toast.makeText(getActivity(), "Please select community ", Toast.LENGTH_SHORT).show();
        }

        else if(!ValidationViews.EditTextNullValidate(etEmployee_Cast)){
            personalInfoValidation();
        }

        else if(spEmployee_Department.getSelectedItemPosition()==0){
            personalInfoValidation();
            Toast.makeText(getActivity(), "Please select Department ", Toast.LENGTH_SHORT).show();
        }

        else if(sp_Employee_Designation.getSelectedItemPosition()==0){
            personalInfoValidation();
            Toast.makeText(getActivity(), "Please select Designation ", Toast.LENGTH_SHORT).show();
        }

        else if(!ValidationViews.EditTextNullValidate(etEmployee_AadharCardNumber)){
            idInfoValidation();
        }
        else if(!ValidationViews.EditTextNumberOfDigitsValidate(etEmployee_AadharCardNumber, 12)){
            etEmployee_AadharCardNumber.setError("invalid aadhar number");
            idInfoValidation();
        }

        else if(aadharFrontUri==null){
            Toast.makeText(getActivity(), "Upload Aadhar Front Image", Toast.LENGTH_SHORT).show();
            idInfoValidation();
        }

        else if(aadharBackUri==null){
            Toast.makeText(getActivity(), "Upload Aadhar Back Image", Toast.LENGTH_SHORT).show();
            idInfoValidation();
        }

        else if(!ValidationViews.EditTextNullValidate(etEmployee_PANCardNumber)){
            idInfoValidation();
        }
        else if(!ValidationViews.EditTextPanPatternValidate(etEmployee_PANCardNumber)){
             idInfoValidation();

        }
        else if(!ValidationViews.EditTextNumberOfDigitsValidate(etEmployee_PANCardNumber,10)){
            etEmployee_PANCardNumber.setError("Invalid PAN number");
            idInfoValidation();
        }

        else if(panFrontUri==null){
            Toast.makeText(getActivity(), "Upload pan Front Image", Toast.LENGTH_SHORT).show();
            idInfoValidation();
        }

        else if(panBackUri==null){
            Toast.makeText(getActivity(), "Upload pan Back Image", Toast.LENGTH_SHORT).show();
            idInfoValidation();
        }

        //==================================================

        else if(!ValidationViews.EditTextNullValidate(etEmployee_DLNumber)){
            idInfoValidation();
        }

        else if(dlFrontUri==null){
            Toast.makeText(getActivity(), "Upload DL Front Image", Toast.LENGTH_SHORT).show();
            idInfoValidation();
        }

        else if(dlBackUri==null){
            Toast.makeText(getActivity(), "Upload DL Back Image", Toast.LENGTH_SHORT).show();
            idInfoValidation();
        }

        else if(!ValidationViews.EditTextNullValidate(etEmployee_PresentDoorNumber)){
            addressInfoValidation();
        }
        else if(!ValidationViews.EditTextNullValidate(etEmployee_PresentStreet)){
            addressInfoValidation();
        }
        else if(!ValidationViews.EditTextNullValidate(etEmployee_PresentLocality)){
            addressInfoValidation();
        }
        else if(!ValidationViews.EditTextNullValidate(etEmployee_PresentLandmark)){
            addressInfoValidation();
        }
        else if(!ValidationViews.EditTextNullValidate(etEmployee_PresentPincode)){
            addressInfoValidation();
        }
        else if(!ValidationViews.EditTextNumberOfDigitsValidate(etEmployee_PresentPincode,6)){
            etEmployee_PresentPincode.setError("invalid pincode");
            addressInfoValidation();
        }
        else if(!ValidationViews.EditTextNullValidate(etEmployee_PresentCity)){
            etEmployee_PresentPincode.setError("invalid pincode");
            addressInfoValidation();
        }
        else if(!ValidationViews.EditTextNullValidate(etEmployee_PresentState)){
            addressInfoValidation();
        }
        else if(!ValidationViews.EditTextNullValidate(etEmployee_PresentCountry)){
            addressInfoValidation();
        }
        else if(!ValidationViews.EditTextNullValidate(etEmployee_PresentContact)){
            addressInfoValidation();
        }
        else if(!ValidationViews.EditTextMobileNumberValidate(etEmployee_PresentContact)){
            etEmployee_PresentContact.setError("invalid mobile number");
            addressInfoValidation();
        }
        else if(!ValidationViews.EditTextNullValidate(etEmployee_FatherFirstName)){
            parentInfoValidation();
        }
        else if(!ValidationViews.EditTextNullValidate(etEmployee_FatherLastName)){
            parentInfoValidation();
        }
        else if(spEmployee_FatherOccupation.getSelectedItemPosition()==0){
            parentInfoValidation();
            Toast.makeText(getActivity(), "Please select father occupation", Toast.LENGTH_SHORT).show();
        }
        else if(!ValidationViews.EditTextNullValidate(etEmployee_FatherMobileNumber)){
            parentInfoValidation();
            Toast.makeText(getActivity(), "Please select father occupation", Toast.LENGTH_SHORT).show();
        }
        else if(!ValidationViews.EditTextMobileNumberValidate(etEmployee_FatherMobileNumber)){
            parentInfoValidation();
            etEmployee_FatherMobileNumber.setError("invalid mobile number");
        }


        else if(!etEmployee_FatherEmail.getText().toString().equals("") &&!ValidationViews.EditTextEmailPatternValidate(etEmployee_FatherEmail)){
            parentInfoValidation();
        }


        else if(!ValidationViews.EditTextNullValidate(etEmployee_MotherFirstName)){
            parentInfoValidation();
        }
        else if(!ValidationViews.EditTextNullValidate(etEmployee_MotherLastName)){
            parentInfoValidation();
        }
        else if(spEmployee_MotherOccupation.getSelectedItemPosition()==0){
            parentInfoValidation();
            Toast.makeText(getActivity(), "Please select mother occupation", Toast.LENGTH_SHORT).show();
        }
        else if(!ValidationViews.EditTextNullValidate(etEmployee_MotherMobileNumber)){
            parentInfoValidation();
        }
        else if(!ValidationViews.EditTextMobileNumberValidate(etEmployee_MotherMobileNumber)){
            parentInfoValidation();
            etEmployee_MotherMobileNumber.setError("invalid mobile number");
        }

        else if(!etEmployee_MotherEmail.getText().toString().equals("") &&!ValidationViews.EditTextEmailPatternValidate(etEmployee_MotherEmail)){
            parentInfoValidation();
        }
        else {



            if(maritalStatus.equals("Married")){

                if(!ValidationViews.EditTextNullValidate(etEmployee_SpouseFName,etEmployee_SpouseLName,etEmployee_SpouseManniversary,etEmployee_SpouseMobNum)){
                    maritalInfoValidation();
                    return;
                }
                else if(spEmployee_SpouseOccupation.getSelectedItemPosition()==0){
                    maritalInfoValidation();
                    return;
                }
                else if(!etEmployee_SpouseEmailId.getText().toString().equals("") &&!ValidationViews.EditTextEmailPatternValidate(etEmployee_SpouseEmailId)){
                    parentInfoValidation();
                    return;
                }

                else {

                    if(count!=0){
                        for(int i = 0 ; i<arrayListchildName.size(); i++){

                            etEmployee_SpouseChildName = arrayListchildName.get(i);
                            childOccupation    = arrayListchildOccupation.get(i);
                            etEmployee_SpouseChildDOB = arrayListchildDob.get(i);

                            if(!ValidationViews.EditTextNullValidate(etEmployee_SpouseChildName)){
                                return;
                            }
                            else if(!ValidationViews.EditTextNullValidate(etEmployee_SpouseChildDOB)){
                                return;
                            }
                            else if(childOccupation.getSelectedItemPosition()==1 && !ValidationViews.EditTextNullValidate(arrayListchildStudyClassorCourse.get(i))){
                                return;
                            }

                            else if(childOccupation.getSelectedItemPosition()==1 && !ValidationViews.EditTextNullValidate(arrayListchildStudySchoolorCollegeName.get(i))){
                                return;
                            }

                            else if(childOccupation.getSelectedItemPosition()==1 && !ValidationViews.EditTextNullValidate(arrayListchildStudySchoolorCollegeCity.get(i))){
                                return;
                            }
                            else if(childOccupation.getSelectedItemPosition()==2 && !ValidationViews.EditTextNullValidate(arrayListchildJobRole.get(i))){
                                return;
                            }

                            else if(childOccupation.getSelectedItemPosition()==2 && !ValidationViews.EditTextNullValidate(arrayListchildJobCity.get(i))){
                                return;
                            }

                            else if(childOccupation.getSelectedItemPosition()==2 && !ValidationViews.EditTextNullValidate(arrayListchildJobOrganisationName.get(i))){
                                return;
                            }

                        }
                    }

                    str_Employee_SpouseFName = etEmployee_SpouseFName.getText().toString();
                    str_Employee_SpouseMName = etEmployee_SpouseMName.getText().toString();
                    str_Employee_SpouseLName = etEmployee_SpouseLName.getText().toString();
                    str_Employee_SpouseManniversary = etEmployee_SpouseManniversary.getText().toString();
                    str_Employee_SpouseMobNum = etEmployee_SpouseMobNum.getText().toString();
                    str_Employee_SpouseDesignation = etEmployee_SpouseDesignation.getText().toString();
                    str_Employee_SpouseOrganisationName = etEmployee_SpouseOrganisationName.getText().toString();
                    str_Employee_SpouseChild = tvEmployee_ChildrensNumber.getText().toString();
                    str_Employee_SpouseEmailId = etEmployee_SpouseEmailId.getText().toString();
                }

            }
            else {
                str_Employee_SpouseFName = "";
                str_Employee_SpouseMName = "";
                str_Employee_SpouseLName = "";
                str_Employee_SpouseManniversary = "";
                str_Employee_SpouseMobNum  = "";
                str_Employee_SpouseDesignation = "";
                str_Employee_SpouseOrganisationName = "";
                str_Employee_SpouseChild = "";
                str_Employee_SpouseEmailId = "";

            }

            if(!ValidationViews.EditTextNullValidate(etEmployee_BloodGroup)){
                medicalInfoValidation();
                return;
            }
            else {

                final Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                timestampEmployee = String.valueOf(timestamp.getTime());

                /*final ProgressDialog progressDialog = new ProgressDialog(getActivity());
                progressDialog.setMessage("Storing data...please wait");
                progressDialog.show();
*/

                str_Employee_FirstName         = etEmployee_FirstName.getText().toString().trim();
                str_Employee_MiddleName        = etEmployee_MiddleName.getText().toString().trim();
                str_Employee_LastName          = etEmployee_LastName.getText().toString().trim();
                str_Employee_BirthPlace        = etEmployee_BirthPlace.getText().toString().trim();
                str_Employee_DOB               = etEmployee_DOB.getText().toString().trim();
                str_Employee_MobileNumber      = etEmployee_MobileNumber.getText().toString().trim();
                str_Employee_Nationality       = etEmployee_Nationality.getText().toString().trim();
                str_Employee_EmailID           = etEmployee_EmailID.getText().toString().trim();
                str_Employee_Cast              = etEmployee_Cast.getText().toString().trim();
                str_Employee_AadharCardNumber  = etEmployee_AadharCardNumber.getText().toString();
                str_Employee_PANCardNumber     = etEmployee_PANCardNumber.getText().toString();
                str_Employee_DLNumber          = etEmployee_DLNumber.getText().toString();
                str_Employee_VoterCardNumber   = etEmployee_VoterCardNumber.getText().toString();
                str_Employee_PassportNumber    = etEmployee_PassportNumber.getText().toString();


                str_Employee_PresentDoorNumber = etEmployee_PresentDoorNumber.getText().toString();
                str_Employee_PresentStreet     = etEmployee_PresentStreet.getText().toString();
                str_Employee_PresentLocality   = etEmployee_PresentLocality.getText().toString();
                str_Employee_PresentLandmark   = etEmployee_PresentLandmark.getText().toString();
                str_Employee_PresentPincode    = etEmployee_PresentPincode.getText().toString();
                str_Employee_PresentCity       = etEmployee_PresentCity.getText().toString();
                str_Employee_PresentState      = etEmployee_PresentState.getText().toString();
                str_Employee_PresentCountry    = etEmployee_PresentCountry.getText().toString();
                str_Employee_PresentContact    = etEmployee_PresentContact.getText().toString();

                str_Employee_PermanentDoorNumber = etEmployee_PermanentDoorNumber.getText().toString();
                str_Employee_PermanentStreet = etEmployee_PermanentStreet.getText().toString();
                str_Employee_PermanentLocality = etEmployee_PermanentLocality.getText().toString();
                str_Employee_PermanentLandmark = etEmployee_PermanentLandmark.getText().toString();
                str_Employee_PermanentPincode = etEmployee_PermanentPincode.getText().toString();
                str_Employee_PermanentCity = etEmployee_PermanentCity.getText().toString();
                str_Employee_PermanentState = etEmployee_PermanentState.getText().toString();
                str_Employee_PermanentCountry = etEmployee_PermanentCountry.getText().toString();
                str_Employee_PermanentContact = etEmployee_PermanentContact.getText().toString();


                if(checkBoxStatus){
                    str_Employee_PermanentDoorNumber= str_Employee_PresentDoorNumber;
                    str_Employee_PermanentStreet = str_Employee_PresentStreet;
                    str_Employee_PermanentLocality = str_Employee_PresentLocality;
                    str_Employee_PermanentLandmark =  str_Employee_PresentLandmark;
                    str_Employee_PermanentPincode = str_Employee_PresentPincode;
                    str_Employee_PermanentCity = str_Employee_PresentCity;
                    str_Employee_PermanentState = str_Employee_PresentState;
                    str_Employee_PermanentCountry = str_Employee_PresentCountry;
                    str_Employee_PermanentContact = str_Employee_PresentContact;

                }


                str_Employee_FatherFirstName    = etEmployee_FatherFirstName.getText().toString();
                str_Employee_FatherMiddleName   = etEmployee_FatherMiddleName.getText().toString();
                str_Employee_FatherLastName     = etEmployee_FatherLastName.getText().toString();
                str_Employee_FatherDesignation  = etEmployee_FatherDesignation.getText().toString();
                str_Employee_FatherOrganisationName = etEmployee_FatherOrganisationName.getText().toString();
                str_Employee_FatherMobileNumber = etEmployee_FatherMobileNumber.getText().toString();
                str_Employee_FatherEmail        = etEmployee_FatherEmail.getText().toString();

                str_Employee_MotherFirstName    = etEmployee_MotherFirstName.getText().toString();
                str_Employee_MotherMiddleName   = etEmployee_MotherMiddleName.getText().toString();
                str_Employee_MotherLastName     = etEmployee_MotherLastName.getText().toString();
                str_Employee_MotherDesignation  = etEmployee_MotherDesignation.getText().toString();
                str_Employee_MotherOrganisationName = etEmployee_MotherOrganisationName.getText().toString();
                str_Employee_MotherMobileNumber = etEmployee_MotherMobileNumber.getText().toString();
                str_Employee_MotherEmail        = etEmployee_MotherEmail.getText().toString();
                str_EmployeeBloodGroup          = etEmployee_BloodGroup.getText().toString();
                str_EmployeeHealthIssue         = etEmployee_MedicalIssues.getText().toString();
                str_EmployeePersonalDoc         = etEmployee_DocNum.getText().toString();
                str_EmployeeWight               = etEmployee_Weight.getText().toString();
                str_EmployeeHeight              = etEmployee_Height.getText().toString();
                str_EmployeeShortEyeVision      = etEmployee_shortEyeVision.getText().toString();
                str_EmployeeLongEyeVision       = etEmployee_longEyeVision.getText().toString();




                EmployeeAddModel employeeAddModelForPersonalDetails = new EmployeeAddModel(1,str_Employee_FirstName, str_Employee_MiddleName,
                        str_Employee_LastName,
                        str_Employee_gender,  str_Employee_BirthPlace,
                        str_Employee_DOB, str_Employee_DOJ, str_Employee_MobileNumber,
                        str_Employee_Nationality,  str_Employee_EmailID,
                        str_comunityEmployee,  str_Employee_Cast,
                        str_Employee_Designation,  str_Employee_Department);


                EmployeeAddModel employeeAddModelForIdDetails = new EmployeeAddModel(str_Employee_AadharCardNumber,
                        str_Employee_PANCardNumber,str_Employee_DLNumber,str_Employee_VoterCardNumber,
                        str_Employee_PassportNumber,1);


                EmployeeAddModel employeeAddModelForAddressDetails = new EmployeeAddModel(str_Employee_PresentDoorNumber,
                        str_Employee_PresentStreet, str_Employee_PresentLocality,
                        str_Employee_PresentLandmark,str_Employee_PresentPincode,
                        str_Employee_PresentCity,str_Employee_PresentState,
                        str_Employee_PresentCountry,str_Employee_PresentContact,
                        str_Employee_PermanentDoorNumber, str_Employee_PermanentStreet,
                        str_Employee_PermanentLocality, str_Employee_PermanentLandmark,
                        str_Employee_PermanentPincode, str_Employee_PermanentCity,
                        str_Employee_PermanentState, str_Employee_PermanentCountry,
                        str_Employee_PermanentContact);


                EmployeeAddModel employeeAddModelForParentDetails = new EmployeeAddModel(str_Employee_FatherFirstName,
                        str_Employee_FatherMiddleName,str_Employee_FatherLastName,str_EmployeeFatherOccupation,
                        str_Employee_FatherDesignation,str_Employee_FatherOrganisationName,
                        str_Employee_FatherMobileNumber,str_Employee_FatherEmail,
                        str_Employee_MotherFirstName,str_Employee_MotherMiddleName,
                        str_Employee_MotherLastName,str_EmployeeMotherOccupation,str_Employee_MotherDesignation,
                        str_Employee_MotherOrganisationName,str_Employee_MotherMobileNumber,
                        str_Employee_MotherEmail);

                EmployeeAddModel employeeAddModelForSpouseDetails = new EmployeeAddModel(maritalStatus,str_Employee_SpouseFName, str_Employee_SpouseMName,
                        str_Employee_SpouseLName, str_Employee_SpouseManniversary,
                        str_Employee_SpouseMobNum,str_EmployeeSpouseOccupation,str_Employee_SpouseDesignation,
                        str_Employee_SpouseOrganisationName,str_Employee_SpouseChild,
                        str_Employee_SpouseEmailId);

                EmployeeAddModel employeeAddModelForMedicalDetails = new EmployeeAddModel(1,str_EmployeeBloodGroup,str_EmployeeHeight,str_EmployeeWight,str_EmployeeShortEyeVision,str_EmployeeLongEyeVision,
                        str_EmployeeHealthIssue, str_EmployeePersonalDoc);



                EmployeeAddModel employeeAddModel = new EmployeeAddModel(employeeAddModelForPersonalDetails,
                        employeeAddModelForIdDetails,
                        employeeAddModelForAddressDetails,
                        employeeAddModelForParentDetails,
                        employeeAddModelForSpouseDetails,employeeAddModelForMedicalDetails);




                Toast.makeText(getActivity(), ""+Constant.STAFF_TYPE+" "+Constant.STAFF_FINAL_ID, Toast.LENGTH_SHORT).show();

                mRef.child(Constant.STAFF_TYPE).child(Constant.STAFF_FINAL_ID).setValue(employeeAddModelForPersonalDetails);
                staffRegistrationRef.child(Constant.STAFF_TYPE).child(Constant.STAFF_FINAL_ID).child("Personal_Information").setValue(employeeAddModel);


                if(count!=0){

                    for(int i = 0 ; i<arrayListchildName.size() ; i++){

                        if(arrayListchildOccupation.get(i).getSelectedItemPosition()==0){

                            String str_childName = arrayListchildName.get(i).getText().toString().trim();
                            String str_childDob  = arrayListchildDob.get(i).getText().toString().trim();
                            String str_childOccupation = arrayListchildOccupation.get(i).getSelectedItem().toString();

                            DatabaseReference childDetailsRef = staffRegistrationRef.child(Constant.STAFF_TYPE).child(Constant.STAFF_FINAL_ID).child("Personal_Information")
                                    .child("employee_child_details").child("child"+(i+1));

                            childDetailsRef.child("str_childName").setValue(str_childName);
                            childDetailsRef.child("str_childDob").setValue(str_childDob);
                            childDetailsRef.child("str_childOccupation").setValue(str_childOccupation);

                        }


                        if(arrayListchildOccupation.get(i).getSelectedItemPosition()==1){

                            String str_childName = arrayListchildName.get(i).getText().toString().trim();
                            String str_childDob  = arrayListchildDob.get(i).getText().toString().trim();
                            String str_childOccupation = arrayListchildOccupation.get(i).getSelectedItem().toString();
                            String str_childSchoolCollegeName = arrayListchildStudySchoolorCollegeName.get(i).getText().toString();
                            String str_childClassOrCourse = arrayListchildStudyClassorCourse.get(i).getText().toString();
                            String str_childSchoolCollegeCity = arrayListchildStudySchoolorCollegeCity.get(i).getText().toString();

                            DatabaseReference childDetailsRef = staffRegistrationRef.child(Constant.STAFF_TYPE).child(Constant.STAFF_FINAL_ID).child("Personal_Information")
                                    .child("employee_child_details").child("child"+(i+1));

                            childDetailsRef.child("str_childName").setValue(str_childName);
                            childDetailsRef.child("str_childDob").setValue(str_childDob);
                            childDetailsRef.child("str_childOccupation").setValue(str_childOccupation);
                            childDetailsRef.child("str_childSchoolCollegeName").setValue(str_childSchoolCollegeName);
                            childDetailsRef.child("str_childClassOrCourse").setValue(str_childClassOrCourse);
                            childDetailsRef.child("str_childSchoolCollegeCity").setValue(str_childSchoolCollegeCity);

                        }

                        else if(arrayListchildOccupation.get(i).getSelectedItemPosition()==2){

                            String str_childName = arrayListchildName.get(i).getText().toString().trim();
                            String str_childDob  = arrayListchildDob.get(i).getText().toString().trim();
                            String str_childOccupation = arrayListchildOccupation.get(i).getSelectedItem().toString();
                            String str_childJobRole = arrayListchildJobRole.get(i).getText().toString();
                            String str_childJobCity = arrayListchildJobCity.get(i).getText().toString();
                            String str_childJobOrgName = arrayListchildJobOrganisationName.get(i).getText().toString();

                            DatabaseReference childDetailsRef = staffRegistrationRef.child(Constant.STAFF_TYPE).child(Constant.STAFF_FINAL_ID).child("Personal_Information")
                                    .child("employee_child_details").child("child"+(i+1));

                            childDetailsRef.child("str_childName").setValue(str_childName);
                            childDetailsRef.child("str_childDob").setValue(str_childDob);
                            childDetailsRef.child("str_childOccupation").setValue(str_childOccupation);
                            childDetailsRef.child("str_childJobRole").setValue(str_childJobRole);
                            childDetailsRef.child("str_childJobCity").setValue(str_childJobCity);
                            childDetailsRef.child("str_childJobOrgName").setValue(str_childJobOrgName);


                        }

                    }

                }


                mStorageRef_Staff_profilePic.child(Constant.STAFF_TYPE).child(Constant.STAFF_FINAL_ID).child("str_Staff_profile_picture").putFile(profileUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        mRef.child(Constant.STAFF_TYPE).child(Constant.STAFF_FINAL_ID).child("str_Staff_profile_picture").setValue(taskSnapshot.getDownloadUrl().toString());


                    }
                });


                StorageReference storageReference = mStoargeref_Staff_Details_Images.child(Constant.STAFF_TYPE).child(Constant.STAFF_FINAL_ID);


                storageReference.child("Personal_Information").child("employee_personal_deatils").child("str_ProfileImage").putFile(profileUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        String url = taskSnapshot.getDownloadUrl().toString();
                        staffRegistrationRef.child(Constant.STAFF_TYPE).child(Constant.STAFF_FINAL_ID).child("Personal_Information").
                                child("employee_personal_deatils").child("str_Staff_profile_picture").setValue(url);

                    }

                });



                storageReference.child("Personal_Information").child("employee_id_details").child("AadharFrontImage").putFile(aadharFrontUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        String url = taskSnapshot.getDownloadUrl().toString();
                        staffRegistrationRef.child(Constant.STAFF_TYPE).child(Constant.STAFF_FINAL_ID).child("Personal_Information").
                                child("employee_id_details").child("str_Employee_AadharCardFrontImage").setValue(url);


                    }

                });

                storageReference.child("Personal_Information").child("employee_id_details").child("AadharBackImage").putFile(aadharBackUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        String url = taskSnapshot.getDownloadUrl().toString();
                        staffRegistrationRef.child(Constant.STAFF_TYPE).child(Constant.STAFF_FINAL_ID).child("Personal_Information").
                                child("employee_id_details").child("str_Employee_AadharCardBackImage").setValue(url);


                    }

                });

                storageReference.child("Personal_Information").child("employee_id_details").child("PanFrontImage").putFile(panFrontUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        String url = taskSnapshot.getDownloadUrl().toString();
                        staffRegistrationRef.child(Constant.STAFF_TYPE).child(Constant.STAFF_FINAL_ID).child("Personal_Information").
                                child("employee_id_details").child("str_Employee_PANFrontImage").setValue(url);


                    }

                });

                storageReference.child("Personal_Information").child("employee_id_details").child("PanBackImage").putFile(panBackUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        String url = taskSnapshot.getDownloadUrl().toString();
                        staffRegistrationRef.child(Constant.STAFF_TYPE).child(Constant.STAFF_FINAL_ID).child("Personal_Information").
                                child("employee_id_details").child("str_Employee_PANBackImage").setValue(url);


                    }

                });


                storageReference.child("Personal_Information").child("employee_id_details").child("DLFrontImage").putFile(dlFrontUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            String url = taskSnapshot.getDownloadUrl().toString();
                            staffRegistrationRef.child(Constant.STAFF_TYPE).child(Constant.STAFF_FINAL_ID).child("Personal_Information").
                                    child("employee_id_details").child("str_Employee_DLFrontImage").setValue(url);
                   }

                });





                storageReference.child("Personal_Information").child("employee_id_details").child("DLBackImage").putFile(dlBackUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            String url = taskSnapshot.getDownloadUrl().toString();
                            staffRegistrationRef.child(Constant.STAFF_TYPE).child(Constant.STAFF_FINAL_ID).child("Personal_Information").
                                    child("employee_id_details").child("str_Employee_DLBackImage").setValue(url);

                   }

                });


                if(voterFrontUri!=null){
                    storageReference.child("Personal_Information").child("employee_id_details").child("VoterFrontImage").putFile(voterFrontUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            String url = taskSnapshot.getDownloadUrl().toString();
                            staffRegistrationRef.child(Constant.STAFF_TYPE).child(Constant.STAFF_FINAL_ID).child("Personal_Information").
                                    child("employee_id_details").child("str_Employee_VoterFrontImage").setValue(url);


                        }

                    });

                }

                if(voterBackUri!=null){
                    storageReference.child("Personal_Information").child("employee_id_details").child("VoterBackImage").putFile(voterBackUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            String url = taskSnapshot.getDownloadUrl().toString();
                            staffRegistrationRef.child(Constant.STAFF_TYPE).child(Constant.STAFF_FINAL_ID).child("Personal_Information").
                                    child("employee_id_details").child("str_Employee_VoterBackImage").setValue(url);


                        }

                    });

                }



                if(passportFrontUri!=null){
                    storageReference.child("Personal_Information").child("employee_id_details").child("PassportFrontImage").putFile(passportFrontUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            String url = taskSnapshot.getDownloadUrl().toString();
                            staffRegistrationRef.child(Constant.STAFF_TYPE).child(Constant.STAFF_FINAL_ID).child("Personal_Information").
                                    child("employee_id_details").child("str_Employee_PassportFrontImage").setValue(url);


                        }

                    });


                }

                if(passportBackUri!=null){
                    storageReference.child("Personal_Information").child("employee_id_details").child("PassportBackImage").putFile(passportBackUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            String url = taskSnapshot.getDownloadUrl().toString();
                            staffRegistrationRef.child(Constant.STAFF_TYPE).child(Constant.STAFF_FINAL_ID).child("Personal_Information").
                                    child("employee_id_details").child("str_Employee_PassportBackImage").setValue(url);


                        }

                    });


                }

                Constant.STAFF_PROFILE_IMAGE = profileUri.toString();
                AddEmployeeLanding newAdmissionActivity = (AddEmployeeLanding) getActivity();
                EducationInfoFragment educationInfoFragment = new EducationInfoFragment();
                if (newAdmissionActivity != null) {
                    //EventBus.getDefault().post(new EmployeeAddModel(1,finalEmployeeId,str_Employee_FirstName,str_Employee_Department));
                    newAdmissionActivity.loadNextFragment(BUTTON_ENABLE , educationInfoFragment,null);

                }
                EventBus.getDefault().post(new EmployeeAddModel(1,finalEmployeeId,str_Employee_FirstName,str_Employee_Department,Constant.STAFF_PROFILE_IMAGE));


            }

        }


    }

    private void non_teacherDataValidation() {
    }

    private void teacherDataValidation() {

        if(ivEmployee_ProfilePicture.getDrawable()==null){
            personalInfoValidation();
            Toast.makeText(getActivity(), "Upload employee Image", Toast.LENGTH_SHORT).show();
        }

        else if(!ValidationViews.EditTextNullValidate(etEmployee_FirstName)){
            personalInfoValidation();


        }
        else if(!ValidationViews.EditTextNullValidate(etEmployee_LastName)){
            personalInfoValidation();
        }
        else if(!ValidationViews.EditTextNullValidate(etEmployee_BirthPlace)){
            personalInfoValidation();
        }
        else if(!ValidationViews.EditTextNullValidate(etEmployee_DOB)){
            personalInfoValidation();
        }

        else if(!ValidationViews.EditTextNullValidate(etEmployee_DOJ)){
            personalInfoValidation();
        }

        else if(!ValidationViews.EditTextNullValidate(etEmployee_Nationality)){
            personalInfoValidation();
        }

        else if(!ValidationViews.EditTextNullValidate(etEmployee_MobileNumber)){
            personalInfoValidation();

        }

        else if(!ValidationViews.EditTextMobileNumberValidate(etEmployee_MobileNumber)){
            personalInfoValidation();
        }


        else if(!ValidationViews.EditTextNullValidate(etEmployee_EmailID)){
            personalInfoValidation();
        }

        else if(!ValidationViews.EditTextEmailPatternValidate(etEmployee_EmailID)){
            personalInfoValidation();
        }

        else if(spEmployee_Community.getSelectedItemPosition()==0){
            personalInfoValidation();
            Toast.makeText(getActivity(), "Please select community ", Toast.LENGTH_SHORT).show();
        }

        else if(!ValidationViews.EditTextNullValidate(etEmployee_Cast)){
            personalInfoValidation();
        }

        else if(spEmployee_Department.getSelectedItemPosition()==0){
            personalInfoValidation();
            Toast.makeText(getActivity(), "Please select Department ", Toast.LENGTH_SHORT).show();
        }

        else if(sp_Employee_Designation.getSelectedItemPosition()==0){
            personalInfoValidation();
            Toast.makeText(getActivity(), "Please select Designation ", Toast.LENGTH_SHORT).show();
        }

        else if(!ValidationViews.EditTextNullValidate(etEmployee_AadharCardNumber)){
            idInfoValidation();
        }
        else if(!ValidationViews.EditTextNumberOfDigitsValidate(etEmployee_AadharCardNumber, 12)){
            etEmployee_AadharCardNumber.setError("invalid aadhar number");
            idInfoValidation();
        }

        else if(aadharFrontUri==null){
            Toast.makeText(getActivity(), "Upload Aadhar Front Image", Toast.LENGTH_SHORT).show();
            idInfoValidation();
        }

        else if(aadharBackUri==null){
            Toast.makeText(getActivity(), "Upload Aadhar Back Image", Toast.LENGTH_SHORT).show();
            idInfoValidation();
        }

        else if(!ValidationViews.EditTextNullValidate(etEmployee_PANCardNumber)){
            idInfoValidation();
        }
        else if(!ValidationViews.EditTextPanPatternValidate(etEmployee_PANCardNumber)){
            idInfoValidation();

        }
        else if(!ValidationViews.EditTextNumberOfDigitsValidate(etEmployee_PANCardNumber,10)){
            etEmployee_PANCardNumber.setError("invalid PAN number");
            idInfoValidation();
        }

        else if(panFrontUri==null){
            Toast.makeText(getActivity(), "Upload pan Front Image", Toast.LENGTH_SHORT).show();
            idInfoValidation();
        }

        else if(panBackUri==null){
            Toast.makeText(getActivity(), "Upload pan Back Image", Toast.LENGTH_SHORT).show();
            idInfoValidation();
        }

        else if(!ValidationViews.EditTextNullValidate(etEmployee_PresentDoorNumber)){
            addressInfoValidation();
        }
        else if(!ValidationViews.EditTextNullValidate(etEmployee_PresentStreet)){
            addressInfoValidation();
        }
        else if(!ValidationViews.EditTextNullValidate(etEmployee_PresentLocality)){
            addressInfoValidation();
        }
        else if(!ValidationViews.EditTextNullValidate(etEmployee_PresentLandmark)){
            addressInfoValidation();
        }
        else if(!ValidationViews.EditTextNullValidate(etEmployee_PresentPincode)){
            addressInfoValidation();
        }
        else if(!ValidationViews.EditTextNumberOfDigitsValidate(etEmployee_PresentPincode,6)){
            etEmployee_PresentPincode.setError("invalid pincode");
            addressInfoValidation();
        }
        else if(!ValidationViews.EditTextNullValidate(etEmployee_PresentCity)){
            etEmployee_PresentPincode.setError("invalid pincode");
            addressInfoValidation();
        }
        else if(!ValidationViews.EditTextNullValidate(etEmployee_PresentState)){
            addressInfoValidation();
        }
        else if(!ValidationViews.EditTextNullValidate(etEmployee_PresentCountry)){
            addressInfoValidation();
        }
        else if(!ValidationViews.EditTextNullValidate(etEmployee_PresentContact)){
            addressInfoValidation();
        }
        else if(!ValidationViews.EditTextMobileNumberValidate(etEmployee_PresentContact)){
            etEmployee_PresentContact.setError("invalid mobile number");
            addressInfoValidation();
        }
        else if(!ValidationViews.EditTextNullValidate(etEmployee_FatherFirstName)){
            parentInfoValidation();
        }
        else if(!ValidationViews.EditTextNullValidate(etEmployee_FatherLastName)){
            parentInfoValidation();
        }
        else if(spEmployee_FatherOccupation.getSelectedItemPosition()==0){
            parentInfoValidation();
            Toast.makeText(getActivity(), "Please select father occupation", Toast.LENGTH_SHORT).show();
        }
        else if(!ValidationViews.EditTextNullValidate(etEmployee_FatherMobileNumber)){
            parentInfoValidation();
            Toast.makeText(getActivity(), "Please select father occupation", Toast.LENGTH_SHORT).show();
        }
        else if(!ValidationViews.EditTextMobileNumberValidate(etEmployee_FatherMobileNumber)){
            parentInfoValidation();
            etEmployee_FatherMobileNumber.setError("invalid mobile number");
        }

        else if(!etEmployee_FatherEmail.getText().toString().equals("") &&!ValidationViews.EditTextEmailPatternValidate(etEmployee_FatherEmail)){
            parentInfoValidation();
        }


        else if(!ValidationViews.EditTextNullValidate(etEmployee_MotherFirstName)){
            parentInfoValidation();
        }
        else if(!ValidationViews.EditTextNullValidate(etEmployee_MotherLastName)){
            parentInfoValidation();
        }
        else if(spEmployee_MotherOccupation.getSelectedItemPosition()==0){
            parentInfoValidation();
            Toast.makeText(getActivity(), "Please select mother occupation", Toast.LENGTH_SHORT).show();
        }
        else if(!ValidationViews.EditTextNullValidate(etEmployee_MotherMobileNumber)){
            parentInfoValidation();
        }
        else if(!ValidationViews.EditTextMobileNumberValidate(etEmployee_MotherMobileNumber)){
            parentInfoValidation();
            etEmployee_MotherMobileNumber.setError("invalid mobile number");
        }

        else if(!etEmployee_MotherEmail.getText().toString().equals("") && !ValidationViews.EditTextEmailPatternValidate(etEmployee_MotherEmail)){
            parentInfoValidation();

        }
        else {



            if(maritalStatus.equals("Married")){

                if(!ValidationViews.EditTextNullValidate(etEmployee_SpouseFName,etEmployee_SpouseLName,etEmployee_SpouseManniversary,etEmployee_SpouseMobNum)){
                    maritalInfoValidation();
                    return;
                }
                else if(spEmployee_SpouseOccupation.getSelectedItemPosition()==0){
                    maritalInfoValidation();
                    return;
                }
                else if(!etEmployee_SpouseEmailId.getText().toString().equals("") &&!ValidationViews.EditTextEmailPatternValidate(etEmployee_SpouseEmailId)){
                    maritalInfoValidation();
                    return;
                }
               /* else if(count!=0){
                    validateChildData();
                }*/
                else {

                    if(count!=0){
                        for(int i = 0 ; i<arrayListchildName.size(); i++){

                            etEmployee_SpouseChildName = arrayListchildName.get(i);
                            childOccupation    = arrayListchildOccupation.get(i);
                            etEmployee_SpouseChildDOB = arrayListchildDob.get(i);

                            if(!ValidationViews.EditTextNullValidate(etEmployee_SpouseChildName)){
                                maritalInfoValidation();
                                return;
                            }
                            else if(!ValidationViews.EditTextNullValidate(etEmployee_SpouseChildDOB)){
                                maritalInfoValidation();
                                return;
                            }
                            else if(childOccupation.getSelectedItemPosition()==1 && !ValidationViews.EditTextNullValidate(arrayListchildStudyClassorCourse.get(i))){
                                maritalInfoValidation();
                                return;
                            }

                            else if(childOccupation.getSelectedItemPosition()==1 && !ValidationViews.EditTextNullValidate(arrayListchildStudySchoolorCollegeName.get(i))){
                                maritalInfoValidation();
                                return;
                            }

                            else if(childOccupation.getSelectedItemPosition()==1 && !ValidationViews.EditTextNullValidate(arrayListchildStudySchoolorCollegeCity.get(i))){
                                maritalInfoValidation();
                                return;
                            }
                            else if(childOccupation.getSelectedItemPosition()==2 && !ValidationViews.EditTextNullValidate(arrayListchildJobRole.get(i))){
                                maritalInfoValidation();
                                return;
                            }

                            else if(childOccupation.getSelectedItemPosition()==2 && !ValidationViews.EditTextNullValidate(arrayListchildJobCity.get(i))){
                                maritalInfoValidation();
                                return;
                            }

                            else if(childOccupation.getSelectedItemPosition()==2 && !ValidationViews.EditTextNullValidate(arrayListchildJobOrganisationName.get(i))){
                                maritalInfoValidation();
                                return;
                            }

                        }
                    }

                    str_Employee_SpouseFName = etEmployee_SpouseFName.getText().toString();
                    str_Employee_SpouseMName = etEmployee_SpouseMName.getText().toString();
                    str_Employee_SpouseLName = etEmployee_SpouseLName.getText().toString();
                    str_Employee_SpouseManniversary = etEmployee_SpouseManniversary.getText().toString();
                    str_Employee_SpouseMobNum = etEmployee_SpouseMobNum.getText().toString();
                    str_Employee_SpouseDesignation = etEmployee_SpouseDesignation.getText().toString();
                    str_Employee_SpouseOrganisationName = etEmployee_SpouseOrganisationName.getText().toString();
                    str_Employee_SpouseChild = tvEmployee_ChildrensNumber.getText().toString();
                    str_Employee_SpouseEmailId = etEmployee_SpouseEmailId.getText().toString();
                }

            }
            else {
                str_Employee_SpouseFName = "";
                str_Employee_SpouseMName = "";
                str_Employee_SpouseLName = "";
                str_Employee_SpouseManniversary = "";
                str_Employee_SpouseMobNum  = "";
                str_Employee_SpouseDesignation = "";
                str_Employee_SpouseOrganisationName = "";
                str_Employee_SpouseChild = "";
                str_Employee_SpouseEmailId = "";

            }

            if(!ValidationViews.EditTextNullValidate(etEmployee_BloodGroup)){
                medicalInfoValidation();
                return;
            }
            else {

                final Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                timestampEmployee = String.valueOf(timestamp.getTime());

                /*final ProgressDialog progressDialog = new ProgressDialog(getActivity());
                progressDialog.setMessage("Storing data...please wait");
                progressDialog.show();
*/

                str_Employee_FirstName         = etEmployee_FirstName.getText().toString().trim();
                str_Employee_MiddleName        = etEmployee_MiddleName.getText().toString().trim();
                str_Employee_LastName          = etEmployee_LastName.getText().toString().trim();
                str_Employee_BirthPlace        = etEmployee_BirthPlace.getText().toString().trim();
                str_Employee_DOB               = etEmployee_DOB.getText().toString().trim();
                str_Employee_MobileNumber      = etEmployee_MobileNumber.getText().toString().trim();
                str_Employee_Nationality       = etEmployee_Nationality.getText().toString().trim();
                str_Employee_EmailID           = etEmployee_EmailID.getText().toString().trim();
                str_Employee_Cast              = etEmployee_Cast.getText().toString().trim();
                str_Employee_AadharCardNumber  = etEmployee_AadharCardNumber.getText().toString();
                str_Employee_PANCardNumber     = etEmployee_PANCardNumber.getText().toString();
                str_Employee_DLNumber          = etEmployee_DLNumber.getText().toString();
                str_Employee_VoterCardNumber   = etEmployee_VoterCardNumber.getText().toString();
                str_Employee_PassportNumber    = etEmployee_PassportNumber.getText().toString();


                str_Employee_PresentDoorNumber = etEmployee_PresentDoorNumber.getText().toString();
                str_Employee_PresentStreet     = etEmployee_PresentStreet.getText().toString();
                str_Employee_PresentLocality   = etEmployee_PresentLocality.getText().toString();
                str_Employee_PresentLandmark   = etEmployee_PresentLandmark.getText().toString();
                str_Employee_PresentPincode    = etEmployee_PresentPincode.getText().toString();
                str_Employee_PresentCity       = etEmployee_PresentCity.getText().toString();
                str_Employee_PresentState      = etEmployee_PresentState.getText().toString();
                str_Employee_PresentCountry    = etEmployee_PresentCountry.getText().toString();
                str_Employee_PresentContact    = etEmployee_PresentContact.getText().toString();

                str_Employee_PermanentDoorNumber = etEmployee_PermanentDoorNumber.getText().toString();
                str_Employee_PermanentStreet = etEmployee_PermanentStreet.getText().toString();
                str_Employee_PermanentLocality = etEmployee_PermanentLocality.getText().toString();
                str_Employee_PermanentLandmark = etEmployee_PermanentLandmark.getText().toString();
                str_Employee_PermanentPincode = etEmployee_PermanentPincode.getText().toString();
                str_Employee_PermanentCity = etEmployee_PermanentCity.getText().toString();
                str_Employee_PermanentState = etEmployee_PermanentState.getText().toString();
                str_Employee_PermanentCountry = etEmployee_PermanentCountry.getText().toString();
                str_Employee_PermanentContact = etEmployee_PermanentContact.getText().toString();

                str_Employee_FatherFirstName    = etEmployee_FatherFirstName.getText().toString();
                str_Employee_FatherMiddleName   = etEmployee_FatherMiddleName.getText().toString();
                str_Employee_FatherLastName     = etEmployee_FatherLastName.getText().toString();
                str_Employee_FatherDesignation  = etEmployee_FatherDesignation.getText().toString();
                str_Employee_FatherOrganisationName = etEmployee_FatherOrganisationName.getText().toString();
                str_Employee_FatherMobileNumber = etEmployee_FatherMobileNumber.getText().toString();
                str_Employee_FatherEmail        = etEmployee_FatherEmail.getText().toString();

                str_Employee_MotherFirstName    = etEmployee_MotherFirstName.getText().toString();
                str_Employee_MotherMiddleName   = etEmployee_MotherMiddleName.getText().toString();
                str_Employee_MotherLastName     = etEmployee_MotherLastName.getText().toString();
                str_Employee_MotherDesignation  = etEmployee_MotherDesignation.getText().toString();
                str_Employee_MotherOrganisationName = etEmployee_MotherOrganisationName.getText().toString();
                str_Employee_MotherMobileNumber = etEmployee_MotherMobileNumber.getText().toString();
                str_Employee_MotherEmail        = etEmployee_MotherEmail.getText().toString();
                str_EmployeeBloodGroup          = etEmployee_BloodGroup.getText().toString();
                str_EmployeeHealthIssue         = etEmployee_MedicalIssues.getText().toString();
                str_EmployeePersonalDoc         = etEmployee_DocNum.getText().toString();
                str_EmployeeWight               = etEmployee_Weight.getText().toString();
                str_EmployeeHeight              = etEmployee_Height.getText().toString();
                str_EmployeeShortEyeVision      = etEmployee_shortEyeVision.getText().toString();
                str_EmployeeLongEyeVision       = etEmployee_longEyeVision.getText().toString();



                if(checkBoxStatus){
                    str_Employee_PermanentDoorNumber= str_Employee_PresentDoorNumber;
                    str_Employee_PermanentStreet = str_Employee_PresentStreet;
                    str_Employee_PermanentLocality = str_Employee_PresentLocality;
                    str_Employee_PermanentLandmark =  str_Employee_PresentLandmark;
                    str_Employee_PermanentPincode = str_Employee_PresentPincode;
                    str_Employee_PermanentCity = str_Employee_PresentCity;
                    str_Employee_PermanentState = str_Employee_PresentState;
                    str_Employee_PermanentCountry = str_Employee_PresentCountry;
                    str_Employee_PermanentContact = str_Employee_PresentContact;

                }



                EmployeeAddModel employeeAddModelForPersonalDetails = new EmployeeAddModel(1,str_Employee_FirstName, str_Employee_MiddleName,
                        str_Employee_LastName,
                        str_Employee_gender,  str_Employee_BirthPlace,
                        str_Employee_DOB, str_Employee_DOJ, str_Employee_MobileNumber,
                        str_Employee_Nationality,  str_Employee_EmailID,
                        str_comunityEmployee,  str_Employee_Cast,
                        str_Employee_Designation,  str_Employee_Department);


                EmployeeAddModel employeeAddModelForIdDetails = new EmployeeAddModel(str_Employee_AadharCardNumber,
                        str_Employee_PANCardNumber,str_Employee_DLNumber,str_Employee_VoterCardNumber,
                        str_Employee_PassportNumber,1);


                EmployeeAddModel employeeAddModelForAddressDetails = new EmployeeAddModel(str_Employee_PresentDoorNumber,
                        str_Employee_PresentStreet, str_Employee_PresentLocality,
                        str_Employee_PresentLandmark,str_Employee_PresentPincode,
                        str_Employee_PresentCity,str_Employee_PresentState,
                        str_Employee_PresentCountry,str_Employee_PresentContact,
                        str_Employee_PermanentDoorNumber, str_Employee_PermanentStreet,
                        str_Employee_PermanentLocality, str_Employee_PermanentLandmark,
                        str_Employee_PermanentPincode, str_Employee_PermanentCity,
                        str_Employee_PermanentState, str_Employee_PermanentCountry,
                        str_Employee_PermanentContact);


                EmployeeAddModel employeeAddModelForParentDetails = new EmployeeAddModel(str_Employee_FatherFirstName,
                        str_Employee_FatherMiddleName,str_Employee_FatherLastName,str_EmployeeFatherOccupation,
                        str_Employee_FatherDesignation,str_Employee_FatherOrganisationName,
                        str_Employee_FatherMobileNumber,str_Employee_FatherEmail,
                        str_Employee_MotherFirstName,str_Employee_MotherMiddleName,
                        str_Employee_MotherLastName,str_EmployeeMotherOccupation,str_Employee_MotherDesignation,
                        str_Employee_MotherOrganisationName,str_Employee_MotherMobileNumber,
                        str_Employee_MotherEmail);

                EmployeeAddModel employeeAddModelForSpouseDetails = new EmployeeAddModel(maritalStatus,str_Employee_SpouseFName, str_Employee_SpouseMName,
                        str_Employee_SpouseLName, str_Employee_SpouseManniversary,
                        str_Employee_SpouseMobNum,str_EmployeeSpouseOccupation,str_Employee_SpouseDesignation,
                        str_Employee_SpouseOrganisationName,str_Employee_SpouseChild,
                        str_Employee_SpouseEmailId);

                EmployeeAddModel employeeAddModelForMedicalDetails = new EmployeeAddModel(1,str_EmployeeBloodGroup,str_EmployeeHeight,str_EmployeeWight,str_EmployeeShortEyeVision,str_EmployeeLongEyeVision,
                        str_EmployeeHealthIssue, str_EmployeePersonalDoc);



                EmployeeAddModel employeeAddModel = new EmployeeAddModel(employeeAddModelForPersonalDetails,
                        employeeAddModelForIdDetails,
                        employeeAddModelForAddressDetails,
                        employeeAddModelForParentDetails,
                        employeeAddModelForSpouseDetails,employeeAddModelForMedicalDetails);




                Toast.makeText(getActivity(), ""+Constant.STAFF_TYPE+" "+Constant.STAFF_FINAL_ID, Toast.LENGTH_SHORT).show();

                mRef.child(Constant.STAFF_TYPE).child(Constant.STAFF_FINAL_ID).setValue(employeeAddModelForPersonalDetails);
                staffRegistrationRef.child(Constant.STAFF_TYPE).child(Constant.STAFF_FINAL_ID).child("Personal_Information").setValue(employeeAddModel);


                if(count!=0){

                    for(int i = 0 ; i<arrayListchildName.size() ; i++){

                        if(arrayListchildOccupation.get(i).getSelectedItemPosition()==0){

                            String str_childName = arrayListchildName.get(i).getText().toString().trim();
                            String str_childDob  = arrayListchildDob.get(i).getText().toString().trim();
                            String str_childOccupation = arrayListchildOccupation.get(i).getSelectedItem().toString();

                            DatabaseReference childDetailsRef = staffRegistrationRef.child(Constant.STAFF_TYPE).child(Constant.STAFF_FINAL_ID).child("Personal_Information")
                                    .child("employee_child_details").child("child"+(i+1));

                            childDetailsRef.child("str_childName").setValue(str_childName);
                            childDetailsRef.child("str_childDob").setValue(str_childDob);
                            childDetailsRef.child("str_childOccupation").setValue(str_childOccupation);

                        }


                        if(arrayListchildOccupation.get(i).getSelectedItemPosition()==1){

                            String str_childName = arrayListchildName.get(i).getText().toString().trim();
                            String str_childDob  = arrayListchildDob.get(i).getText().toString().trim();
                            String str_childOccupation = arrayListchildOccupation.get(i).getSelectedItem().toString();
                            String str_childSchoolCollegeName = arrayListchildStudySchoolorCollegeName.get(i).getText().toString();
                            String str_childClassOrCourse = arrayListchildStudyClassorCourse.get(i).getText().toString();
                            String str_childSchoolCollegeCity = arrayListchildStudySchoolorCollegeCity.get(i).getText().toString();

                            DatabaseReference childDetailsRef = staffRegistrationRef.child(Constant.STAFF_TYPE).child(Constant.STAFF_FINAL_ID).child("Personal_Information")
                                    .child("employee_child_details").child("child"+(i+1));

                            childDetailsRef.child("str_childName").setValue(str_childName);
                            childDetailsRef.child("str_childDob").setValue(str_childDob);
                            childDetailsRef.child("str_childOccupation").setValue(str_childOccupation);
                            childDetailsRef.child("str_childSchoolCollegeName").setValue(str_childSchoolCollegeName);
                            childDetailsRef.child("str_childClassOrCourse").setValue(str_childClassOrCourse);
                            childDetailsRef.child("str_childSchoolCollegeCity").setValue(str_childSchoolCollegeCity);

                        }

                        else if(arrayListchildOccupation.get(i).getSelectedItemPosition()==2){

                            String str_childName = arrayListchildName.get(i).getText().toString().trim();
                            String str_childDob  = arrayListchildDob.get(i).getText().toString().trim();
                            String str_childOccupation = arrayListchildOccupation.get(i).getSelectedItem().toString();
                            String str_childJobRole = arrayListchildJobRole.get(i).getText().toString();
                            String str_childJobCity = arrayListchildJobCity.get(i).getText().toString();
                            String str_childJobOrgName = arrayListchildJobOrganisationName.get(i).getText().toString();

                            DatabaseReference childDetailsRef = staffRegistrationRef.child(Constant.STAFF_TYPE).child(Constant.STAFF_FINAL_ID).child("Personal_Information")
                                    .child("employee_child_details").child("child"+(i+1));

                            childDetailsRef.child("str_childName").setValue(str_childName);
                            childDetailsRef.child("str_childDob").setValue(str_childDob);
                            childDetailsRef.child("str_childOccupation").setValue(str_childOccupation);
                            childDetailsRef.child("str_childJobRole").setValue(str_childJobRole);
                            childDetailsRef.child("str_childJobCity").setValue(str_childJobCity);
                            childDetailsRef.child("str_childJobOrgName").setValue(str_childJobOrgName);


                        }

                    }

                }


                mStorageRef_Staff_profilePic.child(Constant.STAFF_TYPE).child(Constant.STAFF_FINAL_ID).child("str_Staff_profile_picture").putFile(profileUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        mRef.child(Constant.STAFF_TYPE).child(Constant.STAFF_FINAL_ID).child("str_Staff_profile_picture").setValue(taskSnapshot.getDownloadUrl().toString());


                    }
                });


                StorageReference storageReference = mStoargeref_Staff_Details_Images.child(Constant.STAFF_TYPE).child(Constant.STAFF_FINAL_ID);


                storageReference.child("Personal_Information").child("employee_personal_deatils").child("str_ProfileImage").putFile(profileUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        String url = taskSnapshot.getDownloadUrl().toString();
                        staffRegistrationRef.child(Constant.STAFF_TYPE).child(Constant.STAFF_FINAL_ID).child("Personal_Information").
                                child("employee_personal_deatils").child("str_Staff_profile_picture").setValue(url);

                    }

                });



                storageReference.child("Personal_Information").child("employee_id_details").child("AadharFrontImage").putFile(aadharFrontUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        String url = taskSnapshot.getDownloadUrl().toString();
                        staffRegistrationRef.child(Constant.STAFF_TYPE).child(Constant.STAFF_FINAL_ID).child("Personal_Information").
                                child("employee_id_details").child("str_Employee_AadharCardFrontImage").setValue(url);


                    }

                });

                storageReference.child("Personal_Information").child("employee_id_details").child("AadharBackImage").putFile(aadharBackUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        String url = taskSnapshot.getDownloadUrl().toString();
                        staffRegistrationRef.child(Constant.STAFF_TYPE).child(Constant.STAFF_FINAL_ID).child("Personal_Information").
                                child("employee_id_details").child("str_Employee_AadharCardBackImage").setValue(url);


                    }

                });

                storageReference.child("Personal_Information").child("employee_id_details").child("PanFrontImage").putFile(panFrontUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        String url = taskSnapshot.getDownloadUrl().toString();
                        staffRegistrationRef.child(Constant.STAFF_TYPE).child(Constant.STAFF_FINAL_ID).child("Personal_Information").
                                child("employee_id_details").child("str_Employee_PANFrontImage").setValue(url);


                    }

                });

                storageReference.child("Personal_Information").child("employee_id_details").child("PanBackImage").putFile(panBackUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        String url = taskSnapshot.getDownloadUrl().toString();
                        staffRegistrationRef.child(Constant.STAFF_TYPE).child(Constant.STAFF_FINAL_ID).child("Personal_Information").
                                child("employee_id_details").child("str_Employee_PANBackImage").setValue(url);


                    }

                });


                if(dlFrontUri!=null){
                    storageReference.child("Personal_Information").child("employee_id_details").child("DLFrontImage").putFile(dlFrontUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            String url = taskSnapshot.getDownloadUrl().toString();
                            staffRegistrationRef.child(Constant.STAFF_TYPE).child(Constant.STAFF_FINAL_ID).child("Personal_Information").
                                    child("employee_id_details").child("str_Employee_DLFrontImage").setValue(url);


                        }

                    });

                }



                if(dlBackUri!=null){
                    storageReference.child("Personal_Information").child("employee_id_details").child("DLBackImage").putFile(dlBackUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            String url = taskSnapshot.getDownloadUrl().toString();
                            staffRegistrationRef.child(Constant.STAFF_TYPE).child(Constant.STAFF_FINAL_ID).child("Personal_Information").
                                    child("employee_id_details").child("str_Employee_DLBackImage").setValue(url);


                        }

                    });

                }

                if(voterFrontUri!=null){
                    storageReference.child("Personal_Information").child("employee_id_details").child("VoterFrontImage").putFile(voterFrontUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            String url = taskSnapshot.getDownloadUrl().toString();
                            staffRegistrationRef.child(Constant.STAFF_TYPE).child(Constant.STAFF_FINAL_ID).child("Personal_Information").
                                    child("employee_id_details").child("str_Employee_VoterFrontImage").setValue(url);


                        }

                    });

                }

                if(voterBackUri!=null){
                    storageReference.child("Personal_Information").child("employee_id_details").child("VoterBackImage").putFile(voterBackUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            String url = taskSnapshot.getDownloadUrl().toString();
                            staffRegistrationRef.child(Constant.STAFF_TYPE).child(Constant.STAFF_FINAL_ID).child("Personal_Information").
                                    child("employee_id_details").child("str_Employee_VoterBackImage").setValue(url);


                        }

                    });

                }



                if(passportFrontUri!=null){
                    storageReference.child("Personal_Information").child("employee_id_details").child("PassportFrontImage").putFile(passportFrontUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            String url = taskSnapshot.getDownloadUrl().toString();
                            staffRegistrationRef.child(Constant.STAFF_TYPE).child(Constant.STAFF_FINAL_ID).child("Personal_Information").
                                    child("employee_id_details").child("str_Employee_PassportFrontImage").setValue(url);


                        }

                    });


                }

                if(passportBackUri!=null){
                    storageReference.child("Personal_Information").child("employee_id_details").child("PassportBackImage").putFile(passportBackUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            String url = taskSnapshot.getDownloadUrl().toString();
                            staffRegistrationRef.child(Constant.STAFF_TYPE).child(Constant.STAFF_FINAL_ID).child("Personal_Information").
                                    child("employee_id_details").child("str_Employee_PassportBackImage").setValue(url);


                        }

                    });


                }

                Constant.STAFF_PROFILE_IMAGE = profileUri.toString();
                AddEmployeeLanding newAdmissionActivity = (AddEmployeeLanding) getActivity();
                EducationInfoFragment educationInfoFragment = new EducationInfoFragment();
                if (newAdmissionActivity != null) {
                    //EventBus.getDefault().post(new EmployeeAddModel(1,finalEmployeeId,str_Employee_FirstName,str_Employee_Department));
                    newAdmissionActivity.loadNextFragment(BUTTON_ENABLE , educationInfoFragment,null);

                }
                EventBus.getDefault().post(new EmployeeAddModel(1,finalEmployeeId,str_Employee_FirstName,str_Employee_Department,Constant.STAFF_PROFILE_IMAGE));


            }

        }
    }

    private void validateChildData() {
        switch (count){

            case 1:
                if(!ValidationViews.EditTextNullValidate(etEmployee_SpouseChildName)){
                    return;
                }
                break;

            case 2:
                if(!ValidationViews.EditTextNullValidate(etEmployee_SpouseChildName)){
                    return;
                }
                break;

            case 3:
                if(!ValidationViews.EditTextNullValidate(etEmployee_SpouseChildName)){
                    return;
                }
                break;

            case 4:
                if(!ValidationViews.EditTextNullValidate(etEmployee_SpouseChildName)){
                    return;
                }
                break;


            case 5:
                if(!ValidationViews.EditTextNullValidate(etEmployee_SpouseChildName)){
                    return;
                }
                break;


        }
    }

    private void showChildOccupationInSpinner() {
        ArrayList<String> occupationArrayList = new ArrayList<>();
        occupationArrayList.add("Infant");
        occupationArrayList.add("Study");
        occupationArrayList.add("Working");
        CustomSpinnerAdapter customSpinnerAdapter=new CustomSpinnerAdapter(getActivity(),occupationArrayList);
        childOccupation.setAdapter(customSpinnerAdapter);
        childOccupation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                // Toast.makeText(getActivity(), ""+parent.getId(), Toast.LENGTH_SHORT).show();
                switch (parent.getId()){
                    case 2:
                       // Toast.makeText(getActivity(), "1", Toast.LENGTH_SHORT).show();
                        if(parent.getSelectedItem().toString().equalsIgnoreCase("Study")){
                            //Toast.makeText(getActivity(), "Study", Toast.LENGTH_SHORT).show();
                            //linearLayoutWorking.setVisibility(View.GONE);
                            //linearLayoutStudy.setVisibility(View.VISIBLE);
                            arrayListchildStudyLayout.get(0).setVisibility(View.VISIBLE);
                            arrayListchildWorkLayout.get(0).setVisibility(View.GONE);


                        }

                        else if(parent.getSelectedItem().toString().equalsIgnoreCase("Working")){
                            //linearLayoutStudy.setVisibility(View.GONE);
                            //linearLayoutWorking.setVisibility(View.VISIBLE);
                            arrayListchildWorkLayout.get(0).setVisibility(View.VISIBLE);
                            arrayListchildStudyLayout.get(0).setVisibility(View.GONE);

                        }

                        else if(parent.getSelectedItem().toString().equalsIgnoreCase("Infant")){
                            //linearLayoutStudy.setVisibility(View.GONE);
                            //linearLayoutWorking.setVisibility(View.VISIBLE);
                            arrayListchildWorkLayout.get(0).setVisibility(View.GONE);
                            arrayListchildStudyLayout.get(0).setVisibility(View.GONE);

                        }


                        break;

                    case 3:
                        //Toast.makeText(getActivity(), "2", Toast.LENGTH_SHORT).show();
                        if(parent.getSelectedItem().toString().equalsIgnoreCase("Study")){
                            //linearLayoutWorking.setVisibility(View.GONE);
                            //linearLayoutStudy.setVisibility(View.VISIBLE);
                            arrayListchildStudyLayout.get(1).setVisibility(View.VISIBLE);
                            arrayListchildWorkLayout.get(1).setVisibility(View.GONE);
                        }
                        else if(parent.getSelectedItem().toString().equalsIgnoreCase("Working")){
                            //linearLayoutStudy.setVisibility(View.GONE);
                            //linearLayoutWorking.setVisibility(View.VISIBLE);
                            arrayListchildWorkLayout.get(1).setVisibility(View.VISIBLE);
                            arrayListchildStudyLayout.get(1).setVisibility(View.GONE);
                        }

                        else if(parent.getSelectedItem().toString().equalsIgnoreCase("Infant")){
                            //linearLayoutStudy.setVisibility(View.GONE);
                            //linearLayoutWorking.setVisibility(View.VISIBLE);
                            arrayListchildWorkLayout.get(1).setVisibility(View.GONE);
                            arrayListchildStudyLayout.get(1).setVisibility(View.GONE);

                        }
                        break;

                    case 4:
                        if(parent.getSelectedItem().toString().equalsIgnoreCase("Study")){
                            arrayListchildStudyLayout.get(2).setVisibility(View.VISIBLE);
                            arrayListchildWorkLayout.get(2).setVisibility(View.GONE);
                        }
                        else if(parent.getSelectedItem().toString().equalsIgnoreCase("Working")){

                            arrayListchildWorkLayout.get(2).setVisibility(View.VISIBLE);
                            arrayListchildStudyLayout.get(2).setVisibility(View.GONE);
                        }

                        else if(parent.getSelectedItem().toString().equalsIgnoreCase("Infant")){
                            //linearLayoutStudy.setVisibility(View.GONE);
                            //linearLayoutWorking.setVisibility(View.VISIBLE);
                            arrayListchildWorkLayout.get(2).setVisibility(View.GONE);
                            arrayListchildStudyLayout.get(2).setVisibility(View.GONE);

                        }
                        break;

                    case 5:
                        if(parent.getSelectedItem().toString().equalsIgnoreCase("Study")){

                            arrayListchildStudyLayout.get(3).setVisibility(View.VISIBLE);
                            arrayListchildWorkLayout.get(3).setVisibility(View.GONE);

                        }
                        else if(parent.getSelectedItem().toString().equalsIgnoreCase("Working")){
                            arrayListchildWorkLayout.get(3).setVisibility(View.VISIBLE);
                            arrayListchildStudyLayout.get(3).setVisibility(View.GONE);
                        }

                        else if(parent.getSelectedItem().toString().equalsIgnoreCase("Infant")){
                            //linearLayoutStudy.setVisibility(View.GONE);
                            //linearLayoutWorking.setVisibility(View.VISIBLE);
                            arrayListchildWorkLayout.get(3).setVisibility(View.GONE);
                            arrayListchildStudyLayout.get(3).setVisibility(View.GONE);

                        }
                        break;

                    case 6:
                        if(parent.getSelectedItem().toString().equalsIgnoreCase("Study")){

                            arrayListchildStudyLayout.get(4).setVisibility(View.VISIBLE);
                            arrayListchildWorkLayout.get(4).setVisibility(View.GONE);
                        }
                        else if(parent.getSelectedItem().toString().equalsIgnoreCase("Working")){

                            arrayListchildWorkLayout.get(4).setVisibility(View.VISIBLE);
                            arrayListchildStudyLayout.get(4).setVisibility(View.GONE);
                        }
                        else if(parent.getSelectedItem().toString().equalsIgnoreCase("Infant")){
                            //linearLayoutStudy.setVisibility(View.GONE);
                            //linearLayoutWorking.setVisibility(View.VISIBLE);
                            arrayListchildWorkLayout.get(4).setVisibility(View.GONE);
                            arrayListchildStudyLayout.get(4).setVisibility(View.GONE);

                        }
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    private void storeValuesToBarriersRole(String str_employee_department, String str_employee_designation, String finalEmployeeId) {

        Log.d("smsm", ""+str_employee_designation);

        switch (str_employee_designation){

            case "ML":
                addEmployeeToRoles.child("Module Leader").child(str_employee_department).child(finalEmployeeId).child("Employee_Name").setValue(str_Employee_FirstName);
                break;
        }



    }

    private void medicalInfoValidation() {
        personalInfoVisibilityGone();
        idProofInfoVisibilityGone();
        addressInfoVisibilityGone();
        parentInfoVisibilityGone();
        maritalInfoVisibilityGone();
        medicalInfoVisibilityVisible();
    }

    private void maritalInfoValidation() {

        personalInfoVisibilityGone();
        idProofInfoVisibilityGone();
        addressInfoVisibilityGone();
        parentInfoVisibilityGone();
        maritalInfoVisibilityVisible();
        medicalInfoVisibilityGone();
    }

    private void parentInfoValidation() {

        personalInfoVisibilityGone();
        idProofInfoVisibilityGone();
        addressInfoVisibilityGone();
        parentInfoVisibilityVisible();
        maritalInfoVisibilityGone();
        medicalInfoVisibilityGone();
    }


    private void addressInfoValidation() {

        personalInfoVisibilityGone();
        idProofInfoVisibilityGone();
        addressInfoVisibilityVisible();
        parentInfoVisibilityGone();
        maritalInfoVisibilityGone();
        medicalInfoVisibilityGone();
    }

    private void idInfoValidation() {

        personalInfoVisibilityGone();
        idProofInfoVisibilityVisible();
        addressInfoVisibilityGone();
        parentInfoVisibilityGone();
        maritalInfoVisibilityGone();
        medicalInfoVisibilityGone();
    }

    private void personalInfoValidation() {

        personalInfoVisibilityVisible();
        idProofInfoVisibilityGone();
        addressInfoVisibilityGone();
        parentInfoVisibilityGone();
        maritalInfoVisibilityGone();
        medicalInfoVisibilityGone();

    }

    private void medicalInfoVisibilityVisible() {

        medicalDeatailsLayout.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.anim_on));
        medicalInfoTextView.setTextColor(getResources().getColor(R.color.colorPrimary));
        medicalInfoTextView.setBackgroundColor(getResources().getColor(R.color.whitecolor));
        medicalInfoImageViewDown.setColorFilter(getResources().getColor(R.color.colorPrimary));
        linearLayoutMedicalDetailsViews.setVisibility(View.VISIBLE);
        medicalInfoImageViewDown.setRotation(medicalInfoImageViewDown.getRotation() + 180);

    }

    private void maritalInfoVisibilityVisible() {

        maritalDeatailsLayout.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.anim_on));
        maritalInfoTextView.setTextColor(getResources().getColor(R.color.colorPrimary));
        maritalInfoTextView.setBackgroundColor(getResources().getColor(R.color.whitecolor));
        maritalInfoImageViewDown.setColorFilter(getResources().getColor(R.color.colorPrimary));
        linearLayoutMaritalDetailsViews.setVisibility(View.VISIBLE);
        maritalInfoImageViewDown.setRotation(maritalInfoImageViewDown.getRotation() + 180);

    }

    private void parentInfoVisibilityVisible() {

        parentDeatailsLayout.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.anim_on));
        parentInfoTextView.setTextColor(getResources().getColor(R.color.colorPrimary));
        parentInfoTextView.setBackgroundColor(getResources().getColor(R.color.whitecolor));
        parentInfoImageViewDown.setColorFilter(getResources().getColor(R.color.colorPrimary));
        linearLayoutParentDetailsViews.setVisibility(View.VISIBLE);
        parentInfoImageViewDown.setRotation(parentInfoImageViewDown.getRotation() + 180);
    }

    private void addressInfoVisibilityVisible() {

        addressDeatailsLayout.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.anim_on));
        addressInfoTextView.setTextColor(getResources().getColor(R.color.colorPrimary));
        addressInfoTextView.setBackgroundColor(getResources().getColor(R.color.whitecolor));
        addressImageViewDown.setColorFilter(getResources().getColor(R.color.colorPrimary));
        linearLayoutAddressDetailsViews.setVisibility(View.VISIBLE);
        addressImageViewDown.setRotation(addressImageViewDown.getRotation() + 180);

    }

    private void idProofInfoVisibilityVisible() {

        idDeatailsLayout.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.anim_on));
        linearLayoutIDDetailsViews.setVisibility(View.VISIBLE);
        iDInfoTextView.setTextColor(getResources().getColor(R.color.colorPrimary));
        iDInfoTextView.setBackgroundColor(getResources().getColor(R.color.whitecolor));
        idInfoImageViewDown.setColorFilter(getResources().getColor(R.color.colorPrimary));
        idInfoImageViewDown.setRotation(idInfoImageViewDown.getRotation() + 180);


    }

    private void personalInfoVisibilityVisible() {
        personalInfoLayout.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.anim_on));
        personalInfoTextView.setTextColor(getResources().getColor(R.color.colorPrimary));
        personalInfoTextView.setBackgroundColor(getResources().getColor(R.color.whitecolor));
        personalInfoImageViewDown.setColorFilter(getResources().getColor(R.color.colorPrimary));
        linearLayoutPersonalProfileViews.setVisibility(View.VISIBLE);
        personalInfoImageViewDown.setRotation(personalInfoImageViewDown.getRotation() + 180);

    }

    private void medicalInfoVisibilityGone() {

        medicalInfoTextView.setTextColor(getResources().getColor(R.color.whitecolor));
        medicalInfoTextView.setBackgroundColor(getResources().getColor(R.color.colorOrange));
        medicalInfoImageViewDown.setColorFilter(getResources().getColor(R.color.whitecolor));
        linearLayoutMedicalDetailsViews.setVisibility(View.GONE);
        medicalInfoImageViewDown.setRotation(medicalInfoImageViewDown.getRotation() + 180);

    }

    private void maritalInfoVisibilityGone() {

        maritalInfoTextView.setTextColor(getResources().getColor(R.color.whitecolor));
        maritalInfoTextView.setBackgroundColor(getResources().getColor(R.color.colorOrange));
        maritalInfoImageViewDown.setColorFilter(getResources().getColor(R.color.whitecolor));
        linearLayoutMaritalDetailsViews.setVisibility(View.GONE);
        maritalInfoImageViewDown.setRotation(maritalInfoImageViewDown.getRotation() + 180);

    }

    private void parentInfoVisibilityGone() {

        parentInfoTextView.setTextColor(getResources().getColor(R.color.whitecolor));
        parentInfoTextView.setBackgroundColor(getResources().getColor(R.color.colorOrange));
        parentInfoImageViewDown.setColorFilter(getResources().getColor(R.color.whitecolor));
        linearLayoutParentDetailsViews.setVisibility(View.GONE);
        parentInfoImageViewDown.setRotation(parentInfoImageViewDown.getRotation() + 180);

    }

    private void addressInfoVisibilityGone() {

        addressInfoTextView.setTextColor(getResources().getColor(R.color.whitecolor));
        addressInfoTextView.setBackgroundColor(getResources().getColor(R.color.colorOrange));
        addressImageViewDown.setColorFilter(getResources().getColor(R.color.whitecolor));
        linearLayoutAddressDetailsViews.setVisibility(View.GONE);
        addressImageViewDown.setRotation(addressImageViewDown.getRotation() + 180);

    }

    private void personalInfoVisibilityGone() {

        personalInfoTextView.setTextColor(getResources().getColor(R.color.whitecolor));
        personalInfoTextView.setBackgroundColor(getResources().getColor(R.color.colorOrange));
        //personalInfoTextView.setBackgroundResource(R.drawable.gradient);
        personalInfoImageViewDown.setColorFilter(getResources().getColor(R.color.whitecolor));
        linearLayoutPersonalProfileViews.setVisibility(View.GONE);
        personalInfoImageViewDown.setRotation(personalInfoImageViewDown.getRotation() + 180);


    }

    private void idProofInfoVisibilityGone() {

        iDInfoTextView.setTextColor(getResources().getColor(R.color.whitecolor));
        iDInfoTextView.setBackgroundColor(getResources().getColor(R.color.colorOrange));
        idInfoImageViewDown.setColorFilter(getResources().getColor(R.color.whitecolor));
        linearLayoutIDDetailsViews.setVisibility(View.GONE);
        idInfoImageViewDown.setRotation(idInfoImageViewDown.getRotation() + 180);
    }

    private void displayNumberOfChild(int count) {
        tvEmployee_ChildrensNumber.setText(""+count);
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


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constant.FROM_CAMERA && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                Bitmap photo = (Bitmap) data.getExtras().get("data");
                int nh = (int) (photo.getHeight() * (256.0 / photo.getWidth()));
                Bitmap scaled = Bitmap.createScaledBitmap(photo, 256, nh, true);
                String path = MediaStore.Images.Media.insertImage(getActivity().getContentResolver(), scaled, "Title", null);
                filePath = Uri.parse(path);

                switch (imagecase) {

                    case "A":
                        profileUri = filePath;
                        setImageToView(profileUri);
                        break;

                    case "B":
                        aadharFrontUri = filePath;
                        setImageToView(aadharFrontUri);
                        ivEmployee_AadharBack.setVisibility(View.VISIBLE);
                        break;

                    case "C":
                        aadharBackUri = filePath;
                        setImageToView(aadharBackUri);
                        break;

                    case "D":
                        panFrontUri = filePath;
                        setImageToView(panFrontUri);
                        ivEmployee_PANBack.setVisibility(View.VISIBLE);
                        break;

                    case "E":
                        panBackUri = filePath;
                        setImageToView(panBackUri);
                        break;

                    case "F":
                        voterFrontUri = filePath;
                        setImageToView(voterFrontUri);
                        ivEmployee_VoterIDBack.setVisibility(View.VISIBLE);
                        break;

                    case "G":
                        voterBackUri = filePath;
                        setImageToView(voterBackUri);
                        break;

                    case "H":
                        passportFrontUri = filePath;
                        setImageToView(passportFrontUri);
                        ivEmployee_PassportIDBack.setVisibility(View.VISIBLE);
                        break;

                    case "I":
                        passportBackUri = filePath;
                        setImageToView(passportBackUri);
                        break;

                    case "J":
                        dlFrontUri = filePath;
                        setImageToView(dlFrontUri);
                        ivEmployee_DLBack.setVisibility(View.VISIBLE);
                        break;

                    case "K":
                        dlBackUri = filePath;
                        setImageToView(dlBackUri);
                        break;

                }


            }
        }

        if (requestCode == Constant.FROM_GALLERY && resultCode == Activity.RESULT_OK) {

            if (data != null) {
                Uri contentURI = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), contentURI);
                    int bitmapByteCount= BitmapCompat.getAllocationByteCount(bitmap);

                    int nh = (int) ( bitmap.getHeight() * (256.0 / bitmap.getWidth()) );
                    Bitmap scaled = Bitmap.createScaledBitmap(bitmap, 256, nh, true);

                    int bitmapByteCount1=BitmapCompat.getAllocationByteCount(scaled);

                    Log.d("djdj", ""+bitmapByteCount+" "+bitmapByteCount1);

                    String path = MediaStore.Images.Media.insertImage(getActivity().getContentResolver(), scaled, "Title", null);
                    filePath = Uri.parse(path);

                    switch (imagecase){

                        case "A":
                            profileUri = filePath;
                            setImageToView(profileUri);
                            break;

                        case "B":
                            aadharFrontUri = filePath;
                            setImageToView(aadharFrontUri);
                            ivEmployee_AadharBack.setVisibility(View.VISIBLE);
                            break;

                        case "C":
                            aadharBackUri = filePath;
                            setImageToView(aadharBackUri);
                            break;

                        case "D":
                            panFrontUri = filePath;
                            setImageToView(panFrontUri);
                            ivEmployee_PANBack.setVisibility(View.VISIBLE);
                            break;

                        case "E":
                            panBackUri = filePath;
                            setImageToView(panBackUri);
                            break;

                        case "F":
                            voterFrontUri = filePath;
                            setImageToView(voterFrontUri);
                            ivEmployee_VoterIDBack.setVisibility(View.VISIBLE);

                            break;

                        case "G":
                            voterBackUri = filePath;
                            setImageToView(voterBackUri);
                            break;

                        case "H":
                            passportFrontUri = filePath;
                            setImageToView(passportFrontUri);
                            ivEmployee_PassportIDBack.setVisibility(View.VISIBLE);

                            break;

                        case "I":
                            passportBackUri = filePath;
                            setImageToView(passportBackUri);
                            break;

                        case "J":
                            dlFrontUri = filePath;
                            setImageToView(dlFrontUri);
                            ivEmployee_DLBack.setVisibility(View.VISIBLE);
                            break;

                        case "K":
                            dlBackUri = filePath;
                            setImageToView(dlBackUri);
                            break;



                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }




            /*filePath = data.getData();
            InputStream inputStream;
            try {
                inputStream = getContext().getContentResolver().openInputStream(filePath);
                bitmap = BitmapFactory.decodeStream(inputStream);
                String path = MediaStore.Images.Media.insertImage(getActivity().getContentResolver(), bitmap, "Title", null);
                filePath = Uri.parse(path);

                switch (imagecase){

                    case "A":
                        profileUri = filePath;
                        setImageToView(profileUri);
                        break;

                    case "B":
                        aadharFrontUri = filePath;
                        setImageToView(aadharFrontUri);
                        ivEmployee_AadharBack.setVisibility(View.VISIBLE);
                        break;

                    case "C":
                        aadharBackUri = filePath;
                        setImageToView(aadharBackUri);
                        break;

                    case "D":
                        panFrontUri = filePath;
                        setImageToView(panFrontUri);
                        ivEmployee_PANBack.setVisibility(View.VISIBLE);
                        break;

                    case "E":
                        panBackUri = filePath;
                        setImageToView(panBackUri);
                        break;

                    case "F":
                        voterFrontUri = filePath;
                        setImageToView(voterFrontUri);
                        ivEmployee_VoterIDBack.setVisibility(View.VISIBLE);

                        break;

                    case "G":
                        voterBackUri = filePath;
                        setImageToView(voterBackUri);
                        break;

                    case "H":
                        passportFrontUri = filePath;
                        setImageToView(passportFrontUri);
                        ivEmployee_PassportIDBack.setVisibility(View.VISIBLE);

                        break;

                    case "I":
                        passportBackUri = filePath;
                        setImageToView(passportBackUri);
                        break;

                    case "J":
                        dlFrontUri = filePath;
                        setImageToView(dlFrontUri);
                        ivEmployee_DLBack.setVisibility(View.VISIBLE);
                        break;

                    case "K":
                        dlBackUri = filePath;
                        setImageToView(dlBackUri);
                        break;



                }



            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }*/
        }

        
    }


    private void setImageToView(Uri fileUri) {

        try {

            imageStream = getActivity().getContentResolver().openInputStream(fileUri);
            Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
            selectedImage = getResizedBitmap(selectedImage, 300);

            switch (imagecase){

                case "A":
                    ivEmployee_ProfilePicture.setImageBitmap(selectedImage);
                    break;

                case "B":
                    ivEmployee_AadharFront.setImageBitmap(selectedImage);
                    break;

                case "C":
                    ivEmployee_AadharBack.setImageBitmap(selectedImage);
                    break;

                case "D":
                    ivEmployee_PANFront.setImageBitmap(selectedImage);
                    break;

                case "E":
                    ivEmployee_PANBack.setImageBitmap(selectedImage);
                    break;

                case "F":
                    ivEmployee_VoterIDFront.setImageBitmap(selectedImage);
                    break;

                case "G":
                    ivEmployee_VoterIDBack.setImageBitmap(selectedImage);
                    break;

                case "H":
                    ivEmployee_PassportIDFront.setImageBitmap(selectedImage);
                    break;

                case "I":
                    ivEmployee_PassportIDBack.setImageBitmap(selectedImage);
                    break;

                case "J":
                    ivEmployee_DLFront.setImageBitmap(selectedImage);
                    break;

                case "K":
                    ivEmployee_DLBack.setImageBitmap(selectedImage);
                    break;



            }

        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Bitmap getResizedBitmap(Bitmap image, int maxSize) {

        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float)width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getActivity(), ""+parent.getId(), Toast.LENGTH_SHORT).show();

        switch (parent.getId()){

            case 11:
                Toast.makeText(getActivity(), "dde", Toast.LENGTH_SHORT).show();
                break;

            case 12:
                Toast.makeText(getActivity(), "dde", Toast.LENGTH_SHORT).show();
                break;

        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }




   /* private Bitmap decodeProcess(String encodedImage) {
        byte[] decodedBytes = Base64.decode(encodedImage, 0);
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
    }

    private String encodeProcess(Bitmap image, Bitmap.CompressFormat compressFormat, int quality) {
        ByteArrayOutputStream byteArrayOS = new ByteArrayOutputStream();
        image.compress(compressFormat, quality, byteArrayOS);
        return Base64.encodeToString(byteArrayOS.toByteArray(), Base64.DEFAULT);
    }*/


    @SuppressLint("StaticFieldLeak")
    private class AsynchTaskForStateAndCity extends AsyncTask<String , String , String > {
        String pincode;
        String state , city , country;
        String presentPermanentTag;
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());


        AsynchTaskForStateAndCity(String charSequence, String present_permanent_code) {
            this.pincode = (String) charSequence;
            this.presentPermanentTag = present_permanent_code;


        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setMessage("getting data...please wait");
            progressDialog.show();
        }


        /*doInBackground we are careting first api link and that link we are setting in Jparser class
        * Jpasrser class give the response in JSON format after getting json we
        * will parse the Json there we will get city state and country based on the pincode*/
        @Override
        protected String doInBackground(String... strings) {
            /*Creating object of JsonCityStateParser*/
            JsonCitySateParser jsonCitySateParser = new JsonCitySateParser();

            /*Afer creating object we are sending the pincode entered by user to
            * JsonCityStateParser class
            * That will resturn response in JSon Format and I m storing
            * that response in JSONObject class*/
            JSONObject json = jsonCitySateParser.getJSONFromUrl(pincode);
            Log.d("json", ""+json);

            JSONArray array = null;

            try{

                /*here we are parsing Json and getting city country and state
                * based on PIncode*/
                array = json.getJSONArray("PostOffice");
                JSONObject jsonObject = array.getJSONObject(0);
                state = jsonObject.getString("State");
                city = jsonObject.getString("District");
                country = jsonObject.getString("Country");
                Log.d("state", ""+state+"  city:  "+city );




            } catch (JSONException e) {
                e.printStackTrace();
            }


            progressDialog.dismiss();


            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            switch (presentPermanentTag){
                /*After finishing doInBackground it will excute onPost method
                * there we are setting all getting data from json parsing to edittext*/

                /*Below case is for father present address pin code*/
                case Constant.PRESENT_PINCODE:
                    etEmployee_PresentState.setText(state);
                    etEmployee_PresentCity.setText(city);
                    etEmployee_PresentCountry.setText(country);
                    break;

                /*Below case is for paarent permanent address pin code*/
                case Constant.PERMANENT_PINCODE:
                    etEmployee_PermanentState.setText(state);
                    etEmployee_PermanentCity.setText(city);
                    etEmployee_PermanentCountry.setText(country);
                    break;



            }

        }
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_item, menu);
        final MenuItem myActionMenuItem = menu.findItem( R.id.action_search);

        myActionMenuItem.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {

            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                // Do whatever you need
                searchStaffData(myActionMenuItem);
                return true; // KEEP IT TO TRUE OR IT DOESN'T OPEN !!
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                // Do whatever you need
                return true; // OR FALSE IF YOU DIDN'T WANT IT TO CLOSE!
            }
        });




    }

    private void searchStaffData(MenuItem myActionMenuItem) {

        final SearchView searchView = (SearchView) myActionMenuItem.getActionView();
        searchView.setQueryHint("Registration Number");
        final SearchView.SearchAutoComplete searchAutoComplete = searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        searchAutoComplete.setDropDownBackgroundResource(android.R.color.white);

        ArrayAdapter<String> newsAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, staffReg);
        searchAutoComplete.setAdapter(newsAdapter);


        searchAutoComplete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final String queryString=(String)parent.getItemAtPosition(position);
                searchAutoComplete.setText(queryString);

                Log.d("qary", ""+queryString);
                 Constant.SEARCH_ID = queryString;
                 Constant.STAFF_FINAL_ID = Constant.SEARCH_ID;
                Log.d("dsjkdanjkdas", ""+queryString);

                Log.d("dsjkdanjkdas", ""+queryString);
                staffSnapShotRef.child(Constant.STAFF_TYPE).child(queryString).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        staffRegistration_Number.setText(queryString);

                        for (DataSnapshot postSnapShotB : dataSnapshot.getChildren()){
                            Log.d("PostSS", ""+postSnapShotB.getKey());

                            if(postSnapShotB.getKey().equals("str_Employee_Department")){

                                // studentClass = (String) postSnapShotB.getValue();
                                staff_Dept.setText((String)postSnapShotB.getValue());

                            }

                            if(postSnapShotB.getKey().equals("str_Employee_FirstName")){

                                //studentFName = (String) postSnapShotB.getValue();
                                staff_Name.setText((String) postSnapShotB.getValue());

                            }

                            if(postSnapShotB.getKey().equals("str_Staff_profile_picture")){

                                //studentprofilePicture = (String) postSnapShotB.getValue();
                                Constant.STAFF_PROFILE_IMAGE = (String) postSnapShotB.getValue();
                                Glide.with(getActivity()).load((String) postSnapShotB.getValue()).into(staff_Profile_Picture);

                            }


                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


                mref1.child(Constant.STAFF_TYPE).child(queryString).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        educational_Details = 1;
                        work_Experience=1;
                        Log.d("jhjdfs", ""+dataSnapshot.getKey());

                        for(DataSnapshot postSnapShotA : dataSnapshot.getChildren()){

                            Log.d("dagh", ""+postSnapShotA.getKey());

                            if(postSnapShotA.getKey().equalsIgnoreCase("Educational Details")){
                                educational_Details =0;
                            }

                            if(postSnapShotA.getKey().equalsIgnoreCase("Work Experience")){
                                work_Experience = 0;
                            }
                        }

                        Log.d("educational", ""+educational_Details);
                        Log.d("work_Experience", ""+work_Experience);

                        if(educational_Details==1){

                            Constant.STAFF_FINAL_ID = queryString;
                            AddEmployeeLanding newAdmissionActivity = (AddEmployeeLanding) getActivity();
                            EducationInfoFragment educationInfoFragment = new EducationInfoFragment();
                            if (newAdmissionActivity != null) {
                                //EventBus.getDefault().post(new EmployeeAddModel(1,finalEmployeeId,str_Employee_FirstName,str_Employee_Department));
                                newAdmissionActivity.loadNextFragment(BUTTON_ENABLE , educationInfoFragment,null);

                            }
                            EventBus.getDefault().post(new EmployeeAddModel(1,Constant.STAFF_FINAL_ID,staff_Name.getText().toString(),staff_Dept.getText().toString(),Constant.STAFF_PROFILE_IMAGE));


                        }
                        else if(work_Experience==1){

                            EventBus.getDefault().post(new EmployeeAddModel(1,Constant.STAFF_FINAL_ID,staff_Name.getText().toString(),staff_Dept.getText().toString(),Constant.STAFF_PROFILE_IMAGE));
                            AddEmployeeLanding newAdmissionActivity = (AddEmployeeLanding) getActivity();
                            WorkInfoFragment workInfoFragment = new WorkInfoFragment();
                            assert newAdmissionActivity != null;
                            newAdmissionActivity.loadNextFragment(2, workInfoFragment, null);

                        }
                        else {
                            Toast.makeText(activity, "All details already filled", Toast.LENGTH_SHORT).show();
                            Constant.STAFF_FINAL_ID = Constant.REGISTRATION_CURRENT_TEMP_ID;
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });


    }





}
