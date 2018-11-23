package com.example.varadhi.addstaff.EmployeeAdd;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.BitmapCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class EducationInfoFragment extends Fragment implements View.OnClickListener{

    private static final int BUTTON_ENABLE =2 ;
    LinearLayout schoolInfoLayout, linearLayoutSchoolViews,
                 pucDeatailsLayout, linearLayoutPUCDetailsViews,
                 ugDeatailsLayout, linearLayoutUGDetailsViews,
                 pgDeatailsLayout, linearLayoutPGDetailsViews,
                 BedDedDeatailsLayout,linearLayoutBedDedDetailsViews,
                 phdDeatailsLayout, linearLayoutPHDDetailsViews,
                 achievementDeatailsLayout, linearLayoutAchievementDetailsViews,
                 achivemntsLinearLayout,linearLayout_Sports_DetailsViews,
                 sports_Layout;


    ImageView    schoolInfoImageViewDown, pucInfoImageViewDown,
                 ugImageViewDown, pgInfoImageViewDown,
                 phdInfoImageViewDown,BedDedInfoImageViewDown, sportsInfoImageViewDown,
                 achievemetInfoImageViewDown,ivAttach_SchoolDocs,
                 ivAttach_PUCDocs, ivAttach_GraduateDocs,
                 ivAttach_PGDocs,ivAttach_PHDDocs,ivAttach_BedDedDocs;


    EditText     etEmployee_SchoolName,etEmployee_SchoolPercentage,
                 etEmployee_SchoolPassing ,etEmployee_SchoolBoard,
                 etEmployee_SchoolState,etEmployee_PUCName,
                 etEmployee_PUCPercentage,etEmployee_PUCPassing,
                 etEmployee_PUCBoard,etEmployee_PUCState,
                 etEmployee_GraduateCollegeName,etEmployee_GraduateUniversityName,
                 etEmployee_GraduateSpecialization,etEmployee_GraduateBranchName,
                 etEmployee_GraduatePercentage,etEmployee_GraduatePassingYear,
                 etEmployee_PGCollegeName,etEmployee_PGUniversityName,
                 etEmployee_PGSpecialization,etEmployee_PGBranchName,
                 etEmployee_PGPercentage ,etEmployee_PGPassingYear,
                 etEmployee_PHDCollegeName,etEmployee_PHDUniversityName,
                 etEmployee_PHDSpecialization,etEmployee_PHDBranchName,
                 etEmployee_PHDPercentage,etEmployee_BedDedCollegeName,
                 etEmployee_BedDedUniversityName,etEmployee_BedDedSpecialization,
                 etEmployee_BedDedBranchName, etEmployee_BedDedPercentage,etEmployee_BedDedPassingYear,
                 etEmployee_AchievementTitle,etEmployee_AchievementYear,
                 etEmployee_AchievementPlace,etEmployee_AchievementDescription,
                 et_SportsName,et_SportsYear,et_sportsLevel,et_SportsPosition,
                 et_SportTeamName,et_SportsLocation;


    String       str_Employee_SchoolName,str_Employee_SchoolPercentage,
                 str_Employee_SchoolPassing ,str_Employee_SchoolBoard,
                 str_Employee_SchoolState,str_Employee_PUCName,
                 str_Employee_PUCPercentage,str_Employee_PUCPassing,
                 str_Employee_PUCBoard,str_Employee_PUCState,
                 str_Employee_GraduateCollegeName,str_Employee_GraduateUniversityName,
                 str_Employee_GraduateSpecialization,str_Employee_GraduateBranchName,
                 str_Employee_GraduatePercentage,str_Employee_GraduatePassingYear,
                 str_Employee_PGCollegeName,str_Employee_PGUniversityName,
                 str_Employee_PGSpecialization,str_Employee_PGBranchName,
                 str_Employee_PGPercentage ,str_Employee_PGPassingYear,
                 str_Employee_PHDCollegeName,str_Employee_PHDUniversityName,
                 str_Employee_PHDSpecialization,str_Employee_PHDBranchName,
                 str_Employee_PHDPercentage,str_Employee_BedDedCollegeName,
                 str_Employee_BedDedUniversityName,str_Employee_BedDedSpecialization,
                 str_Employee_BedDedBranchName,str_Employee_BedDedPercentage,
                 str_Employee_BedDedPassingYear,
                 selectedYear,selectedMonth,
                 selectedDate,str_EmployeeAchievementTitle,str_EmployeeAchievementYear,
                 str_EmployeeAchievementPlace,str_EmployeeAchievementDescription,
                 str_EmployeeSportsName,str_EmployeeSportsYear,str_EmployeeSportsLevel,
                 str_EmployeeSportsPosition,str_EmployeeSportsTeamName,
                 str_EmployeeSportsLocation,str_employeeDesignation,str_employeeDepartment,
                 str_employeeID,str_Employee_FirstName,str_Employee_LastName,statusStaff;



    TextView     schoolInfoTextView, pucInfoTextView, ugInfoTextView,
                 pgInfoTextView, phdInfoTextView,BedDedInfoTextView, achievementInfoTextView,
                 numberOfSportsCounter,tvSports_Number;


    Button       btnIncreaseCount,btnDecreaseCount,
                 button_decrease_count_sports,button_increase_sports;

    int          max = 5,count = 0,count1 =0;

    TextView     tvNumberCount;
    TextView     sportsDetails_TextView;

    RelativeLayout school_InfoLayoutClick,
                   puc_LayoutClick,
                   graduate_LayoutClick,
                   pg_LayoutClick,
                   phd_LayoutClick,
                   BedDed_LayoutClick,
                   achievements_LayoutClick,
                   sportsDetails_LayoutClick;

    RecyclerView   rvAttach_SchoolDocs,rvAttach_PUCDocs,
                   rvAttach_GraduateDocs,rvAttach_PGDocs,
                   rvAttach_PHDDocs,rvAttach_BedDedDocs;
    String         imageCase="A";

    Dialog         imageChooserDialog = null;
    Uri filePath;
    Bitmap bitmap;
    double min=35;
    int minYear = 1990;
    int maxYear = 2018;

    int counter = 0;
    int imageCounter=0;
    Activity activity;

    ArrayList<Uri> arrayListSchoolDocs ;
    ArrayList<Uri> arrayListPUCDocs = new ArrayList<>();
    ArrayList<Uri> arrayListGraduationDocs = new ArrayList<>();
    ArrayList<Uri> arrayListPGDocs = new ArrayList<>();
    ArrayList<Uri> arrayListPHDDocs = new ArrayList<>();
    ArrayList<Uri> arrayListBedDedDocs = new ArrayList<>();

    StorageReference mStoargeref_Staff_Details_Images;



    TextView emp_name,emp_registration_id,emp_dept;



    DatabaseReference refLastEmployee;






    ArrayList<ImageModel> arrayListSchoolDocsModel         = new ArrayList<>();
    ArrayList<ImageModel> arrayListPUCDocsModel            = new ArrayList<>();
    ArrayList<ImageModel> arrayListGraduateDocsModel       = new ArrayList<>();
    ArrayList<ImageModel> arrayListPGDocsModel             = new ArrayList<>();
    ArrayList<ImageModel> arrayListPHDDocsModel            = new ArrayList<>();
    ArrayList<ImageModel> arrayListBedDedDocsModel         = new ArrayList<>();


    ArrayList<EditText> arrayList_AchievementTitle         = new ArrayList<>();
    ArrayList<EditText> arrayList_AchievementYear          = new ArrayList<>();
    ArrayList<EditText> arrayList_AchievementPlace         = new ArrayList<>();
    ArrayList<EditText> arrayList_AchievementDescription   = new ArrayList<>();

    ArrayList<EditText> arrayList_SportsName               = new ArrayList<>();
    ArrayList<EditText> arrayList_SportsYear               = new ArrayList<>();
    ArrayList<EditText> arrayList_sportsLevel              = new ArrayList<>();
    ArrayList<EditText> arrayList_SportsPosition           = new ArrayList<>();
    ArrayList<EditText> arrayList_SportTeamName            = new ArrayList<>();
    ArrayList<EditText> arrayList_SportsLocation           = new ArrayList<>();




    LinearLayoutManager   layoutManagerForSchoolDocs;
    LinearLayoutManager layoutManagerForPUCDocs;
    LinearLayoutManager layoutManagerForGraduateDocs;
    LinearLayoutManager layoutManagerForPGDocs;
    LinearLayoutManager layoutManagerForPHDDocs;
    LinearLayoutManager layoutManagerForBedDedDocs;

    Button finalSubmitEducationDetails;
    String passingCase = "1";
    TextView tvAchievementDetails;

    FirebaseDatabase mDataBase;
    DatabaseReference mRef;
    Context mContext;

    StorageReference mStorageRef;

    CircleImageView staffImage;
    EventBus bus = EventBus.getDefault();
    private TextView text_View;
    private String registartionNumber;
    private String name;
    private String dept;


    public EducationInfoFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_education_info, container, false);
        activity =(Activity) v.getContext();
        BedDedDeatailsLayout                     = v.findViewById(R.id.linearLayout_BedDedDetails);
        initViews(v);
        arrayListSchoolDocs = new ArrayList<>();
        setVisibilityBasedOnStaff(Constant.STAFF_TYPE);
        mDataBase = FirebaseDatabase.getInstance();
        mRef      = FirebaseDatabase.getInstance().getReference("School/SchoolId/Staff_Registration");
        refLastEmployee = mDataBase.getReference("VARADHISMARTEK000/Employee_List");
        mStorageRef = FirebaseStorage.getInstance().getReference("VARADHISMARTEK000/Employee Details");
        getLastEmployeeDetails();
        intListners();
        mStoargeref_Staff_Details_Images = FirebaseStorage.getInstance().getReference("mStoargeref_Staff_Details_Images");

        addTextChangeListnerScoolPercentage();
        addTextChangeListnerPUCPercentage();
        addTextChangeListnerGraduatePercentage();
        addTextChangeListnerPGPercentage();
        addTextChangeListnerPHDPercentage();
        addTextChangeListnerBedPercentage();

        addTextChangeListnerScoolPassingYear();
        addTextChangeListnerPUCPassingYear();
        addTextChangeListnerGraduationPassingYear();
        addTextChangeListnerPGPassingYear();
        addTextChangeListnerBedDedPassingYear();

        return v;
    }

    private void setVisibilityBasedOnStaff(String staffType) {

        switch (staffType){

            case "Teaching":
                BedDedDeatailsLayout.setVisibility(View.VISIBLE);
                phdDeatailsLayout.setVisibility(View.VISIBLE);
                break;


            case "Non_Teaching":
                BedDedDeatailsLayout.setVisibility(View.GONE);
                phdDeatailsLayout.setVisibility(View.GONE);
                break;

            case "Transportation":
                BedDedDeatailsLayout.setVisibility(View.GONE);
                phdDeatailsLayout.setVisibility(View.GONE);
                break;


        }
    }




    private void getLastEmployeeDetails() {
        Query query = refLastEmployee.orderByKey().limitToLast(1);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapShotA : dataSnapshot.getChildren()) {
                    Log.d("dataSnapShotFragB", "" + postSnapShotA.getKey());
                    str_employeeID = String.valueOf(postSnapShotA.getKey());
                    for (DataSnapshot postSnapShotB : postSnapShotA.getChildren()) {
                        if(postSnapShotB.getKey().equals("str_Employee_Department")){
                            str_employeeDepartment = (String) postSnapShotB.getValue();
                        }
                        if(postSnapShotB.getKey().equals("str_Employee_Designation")){
                            str_employeeDesignation = (String) postSnapShotB.getValue();
                        }
                        Log.d("postSnapShotBFRag", "" + postSnapShotB.getKey());
                       /* for (DataSnapshot postSnapShotC : postSnapShotB.getChildren()) {
                            Log.d("postSnapShotCFRag", "" + postSnapShotC.getKey());

                        }*/
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void addTextChangeListnerAchievementYear() {
        etEmployee_AchievementYear.addTextChangedListener
                (new GenericTextWatcher(etEmployee_AchievementYear));
    }

    private void addTextChangeListnerPGPassingYear() {
        etEmployee_PGPassingYear.addTextChangedListener
                (new GenericTextWatcher(etEmployee_PGPassingYear));
    }

    private void addTextChangeListnerBedDedPassingYear() {
        etEmployee_BedDedPassingYear.addTextChangedListener
                (new GenericTextWatcher(etEmployee_BedDedPassingYear));
    }

    private void addTextChangeListnerGraduationPassingYear() {

        etEmployee_GraduatePassingYear.addTextChangedListener
                (new GenericTextWatcher(etEmployee_GraduatePassingYear));
    }

    private void addTextChangeListnerPUCPassingYear() {

        etEmployee_PUCPassing.addTextChangedListener
                (new GenericTextWatcher(etEmployee_PUCPassing));
    }

    private void addTextChangeListnerScoolPassingYear() {
        etEmployee_SchoolPassing.addTextChangedListener
                (new GenericTextWatcher(etEmployee_SchoolPassing));

    }

    private void addTextChangeListnerBedPercentage() {
        etEmployee_BedDedPercentage.addTextChangedListener
                (new GenericTextWatcher(etEmployee_BedDedPercentage));
    }

    private void addTextChangeListnerPHDPercentage() {
        etEmployee_PHDPercentage.addTextChangedListener
                (new GenericTextWatcher(etEmployee_PHDPercentage));
    }

    private void addTextChangeListnerPGPercentage() {
        etEmployee_PGPercentage.addTextChangedListener
                (new GenericTextWatcher(etEmployee_PGPercentage));
    }

    private void addTextChangeListnerGraduatePercentage() {
        etEmployee_GraduatePercentage.addTextChangedListener
                (new GenericTextWatcher(etEmployee_GraduatePercentage));
    }

    private void addTextChangeListnerPUCPercentage() {
        etEmployee_PUCPercentage.addTextChangedListener
                (new GenericTextWatcher(etEmployee_PUCPercentage));
    }

    private void addTextChangeListnerScoolPercentage() {

        etEmployee_SchoolPercentage.addTextChangedListener
                (new GenericTextWatcher(etEmployee_SchoolPercentage));

    }


    private void intListners() {

        schoolInfoLayout.setOnClickListener(this);
        pucDeatailsLayout.setOnClickListener(this);
        ugDeatailsLayout.setOnClickListener(this);
        pgDeatailsLayout.setOnClickListener(this);
        phdDeatailsLayout.setOnClickListener(this);
        achievementDeatailsLayout.setOnClickListener(this);
        btnIncreaseCount.setOnClickListener(this);
        btnDecreaseCount.setOnClickListener(this);
        button_decrease_count_sports.setOnClickListener(this);
        button_increase_sports.setOnClickListener(this);


        school_InfoLayoutClick.setOnClickListener(this);
        puc_LayoutClick.setOnClickListener(this);
        graduate_LayoutClick.setOnClickListener(this);
        pg_LayoutClick.setOnClickListener(this);
        phd_LayoutClick.setOnClickListener(this);
        BedDed_LayoutClick.setOnClickListener(this);
        achievements_LayoutClick.setOnClickListener(this);
        sportsDetails_LayoutClick.setOnClickListener(this);
        ivAttach_SchoolDocs.setOnClickListener(this);
        ivAttach_PUCDocs.setOnClickListener(this);
        ivAttach_GraduateDocs.setOnClickListener(this);
        ivAttach_PGDocs.setOnClickListener(this);
        ivAttach_PHDDocs.setOnClickListener(this);
        ivAttach_BedDedDocs.setOnClickListener(this);

        finalSubmitEducationDetails.setOnClickListener(this);
       /* etEmployee_SchoolPassing.setOnClickListener(this);
        etEmployee_PUCPassing.setOnClickListener(this);*/



    }

    private void initViews(View v) {


        schoolInfoLayout                         = v.findViewById(R.id.linearLayout_SchoolingDetail);
        pucDeatailsLayout                        = v.findViewById(R.id.linearLayout_PUCDetails);
        ugDeatailsLayout                         = v.findViewById(R.id.linearLayout_UGDetails);
        pgDeatailsLayout                         = v.findViewById(R.id.linearLayout_PGDetails);
        phdDeatailsLayout                        = v.findViewById(R.id.linearLayout_PHDDetails);
        BedDedDeatailsLayout                     = v.findViewById(R.id.linearLayout_BedDedDetails);
        achievementDeatailsLayout                = v.findViewById(R.id.linearLayout_AchievemetDetails);
        sports_Layout                            = v.findViewById(R.id.sportsLayout);


        linearLayoutSchoolViews                  = v.findViewById(R.id.linearLayout_SchoolViews);
        linearLayoutPUCDetailsViews              = v.findViewById(R.id.linearLayout_PUCDetailsViews);
        linearLayoutUGDetailsViews               = v.findViewById(R.id.linearLayout_UGDetailsViews);
        linearLayoutPGDetailsViews               = v.findViewById(R.id.linearLayout_PGDetailsViews);
        linearLayoutPHDDetailsViews              = v.findViewById(R.id.linearLayout_PhdDetailsViews);
        linearLayoutBedDedDetailsViews           = v.findViewById(R.id.linearLayout_BedDedDetailsViews);
        linearLayoutAchievementDetailsViews      = v.findViewById(R.id.linearLayout_AchievemetDetailsViews);
        linearLayout_Sports_DetailsViews         = v.findViewById(R.id.linearLayout_SportsDetailsViews);




        schoolInfoImageViewDown                  = v.findViewById(R.id.btnimageViewUpForSchoolInfo);
        pucInfoImageViewDown                     = v.findViewById(R.id.btnimageViewUpForPUDetail);
        ugImageViewDown                          = v.findViewById(R.id.btnimageViewUpForUGDetail);
        pgInfoImageViewDown                      = v.findViewById(R.id.btnimageViewUpForPGDetail);
        phdInfoImageViewDown                     = v.findViewById(R.id.btnimageViewUpForPhdDetail);
        BedDedInfoImageViewDown                  = v.findViewById(R.id.btnimageViewUpForBedDedDetail);
        achievemetInfoImageViewDown              = v.findViewById(R.id.btnimageViewUpForAchievemetDetail);
        sportsInfoImageViewDown                  = v.findViewById(R.id.btnimageViewUpForSportsDetail);


        schoolInfoTextView                       = v.findViewById(R.id.schoolInfoTextView);
        pucInfoTextView                          = v.findViewById(R.id.pucDetailsTextView);
        ugInfoTextView                           = v.findViewById(R.id.UGDetailsTextView);
        pgInfoTextView                           = v.findViewById(R.id.PGDetailsTextView);
        BedDedInfoTextView                       = v.findViewById(R.id.BedDedDetailsTextView);
        phdInfoTextView                          = v.findViewById(R.id.phdDetailsTextView);
        achievementInfoTextView                  = v.findViewById(R.id.achievemetDetailsTextView);


        btnIncreaseCount                         = v.findViewById(R.id.button_increase_count);
        btnDecreaseCount                         = v.findViewById(R.id.button_decrease_count);
        button_decrease_count_sports             = v.findViewById(R.id.button_decrease_count_sports);
        button_increase_sports                   = v.findViewById(R.id.button_increase_sports);
        tvNumberCount                            = v.findViewById(R.id.numberOfAchievements);
        numberOfSportsCounter                    = v.findViewById(R.id.numberOfSports);
        achivemntsLinearLayout                   = v.findViewById(R.id.achievementsLayout);
        sportsDetails_TextView                   = v.findViewById(R.id.sportsDetailsTextView);
        tvSports_Number                          = v.findViewById(R.id.tvSportsNumber);



        school_InfoLayoutClick                   = v.findViewById(R.id.schoolInfoLayoutClick);
        puc_LayoutClick                          = v.findViewById(R.id.pucInfoLayoutClick);
        graduate_LayoutClick                     = v.findViewById(R.id.UGDetailsLayoutClick);
        pg_LayoutClick                           = v.findViewById(R.id.PGDetailsLayoutClick);
        phd_LayoutClick                          = v.findViewById(R.id.phdDetailsLayoutClick);
        BedDed_LayoutClick                       = v.findViewById(R.id.BedDedDetailsLayoutClick);

        achievements_LayoutClick                 = v.findViewById(R.id.achievemetDetailsLayoutClick);
        sportsDetails_LayoutClick                = v.findViewById(R.id.sportsDetailsLayoutClick);

        etEmployee_SchoolName                    = v.findViewById(R.id.etEmployeeSchoolName);
        etEmployee_SchoolPercentage              = v.findViewById(R.id.etEmployeeSchoolPercentage);
        etEmployee_SchoolPassing                 = v.findViewById(R.id.etEmployeeSchoolPassing);
        etEmployee_SchoolBoard                   = v.findViewById(R.id.etEmployeeSchoolBoard);
        etEmployee_SchoolState                   = v.findViewById(R.id.etEmployeeSchoolState);
        etEmployee_PUCName                       = v.findViewById(R.id.etEmployeePUCName);
        etEmployee_PUCPercentage                 = v.findViewById(R.id.etEmployeePUCPercentage);
        etEmployee_PUCPassing                    = v.findViewById(R.id.etEmployeePUCPassing);
        etEmployee_PUCBoard                      = v.findViewById(R.id.etEmployeePUCBoard);
        etEmployee_PUCState                      = v.findViewById(R.id.etEmployeePUCState);
        etEmployee_GraduateCollegeName           = v.findViewById(R.id.etEmployeeGraduateCollegeName);
        etEmployee_GraduateUniversityName        = v.findViewById(R.id.etEmployeeGraduateUniversityName);
        etEmployee_GraduateSpecialization        = v.findViewById(R.id.etEmployeeGraduateSpecialization);
        etEmployee_GraduateBranchName            = v.findViewById(R.id.etEmployeeGraduateBranchName);
        etEmployee_GraduatePercentage            = v.findViewById(R.id.etEmployeeGraduatePercentage);
        etEmployee_GraduatePassingYear           = v.findViewById(R.id.etEmployeeGraduatePassingYear);
        etEmployee_PGCollegeName                 = v.findViewById(R.id.etEmployeePGCollegeName);
        etEmployee_PGUniversityName              = v.findViewById(R.id.etEmployeePGUniversityName);
        etEmployee_PGSpecialization              = v.findViewById(R.id.etEmployeePGSpecialization);
        etEmployee_PGBranchName                  = v.findViewById(R.id.etEmployeePGBranchName);
        etEmployee_PGPercentage                  = v.findViewById(R.id.etEmployeePGPercentage);
        etEmployee_PGPassingYear                 = v.findViewById(R.id.etEmployeePGPassingYear);
        etEmployee_PHDCollegeName                = v.findViewById(R.id.etEmployeePHDCollegeName);
        etEmployee_PHDUniversityName             = v.findViewById(R.id.etEmployeePHDUniversityName);
        etEmployee_PHDSpecialization             = v.findViewById(R.id.etEmployeePHDSpecialization);
        etEmployee_PHDBranchName                 = v.findViewById(R.id.etEmployeePHDBranchName);
        etEmployee_PHDPercentage                 = v.findViewById(R.id.etEmployeePHDPercentage);


        etEmployee_BedDedCollegeName            = v.findViewById(R.id.etEmployeeBedDedCollegeName);
        etEmployee_BedDedUniversityName         = v.findViewById(R.id.etEmployeeBedDedUniversityName);
        etEmployee_BedDedSpecialization         = v.findViewById(R.id.etEmployeeBedDedSpecialization);
        etEmployee_BedDedBranchName             = v.findViewById(R.id.etEmployeeBedDedBranchName);
        etEmployee_BedDedPercentage             = v.findViewById(R.id.etEmployeeBedDedPercentage);
        etEmployee_BedDedPassingYear            = v.findViewById(R.id.etEmployeeBedDedPassingYear);


        ivAttach_SchoolDocs                      = v.findViewById(R.id.ivAttachSchoolDocs);
        ivAttach_PUCDocs                         = v.findViewById(R.id.ivAttachPUCDocs);
        ivAttach_GraduateDocs                    = v.findViewById(R.id.ivAttachGraduateDocs);
        ivAttach_PGDocs                          = v.findViewById(R.id.ivAttachPGDocs);
        ivAttach_PHDDocs                         = v.findViewById(R.id.ivAttachPHDDocs);
        ivAttach_BedDedDocs                      = v.findViewById(R.id.ivAttachBedDedDocs);



        rvAttach_SchoolDocs                      = v.findViewById(R.id.rvAttachSchoolDocs);
        rvAttach_PUCDocs                         = v.findViewById(R.id.rvAttachPUCDocs);
        rvAttach_GraduateDocs                    = v.findViewById(R.id.rvAttachGraduateDocs);
        rvAttach_PGDocs                          = v.findViewById(R.id.rvAttachPGDocs);
        rvAttach_PHDDocs                         = v.findViewById(R.id.rvAttachPHDDocs);
        rvAttach_BedDedDocs                      = v.findViewById(R.id.rvAttachBedDedDocs);



        finalSubmitEducationDetails              = v.findViewById(R.id.btnEmployeeEducationSubmit);


        layoutManagerForSchoolDocs               = new LinearLayoutManager(getActivity(),
                                                   LinearLayoutManager.HORIZONTAL, false);

        layoutManagerForPUCDocs                  = new LinearLayoutManager(getActivity(),
                                                   LinearLayoutManager.HORIZONTAL, false);

        layoutManagerForGraduateDocs             = new LinearLayoutManager(getActivity(),
                                                   LinearLayoutManager.HORIZONTAL, false);

        layoutManagerForPGDocs                   = new LinearLayoutManager(getActivity(),
                                                   LinearLayoutManager.HORIZONTAL, false);

        layoutManagerForPHDDocs                  = new LinearLayoutManager(getActivity(),
                                                   LinearLayoutManager.HORIZONTAL, false);

        layoutManagerForBedDedDocs               = new LinearLayoutManager(getActivity(),
                                                   LinearLayoutManager.HORIZONTAL, false);




        rvAttach_SchoolDocs.setLayoutManager(layoutManagerForSchoolDocs);
        rvAttach_PUCDocs.setLayoutManager(layoutManagerForPUCDocs);
        rvAttach_GraduateDocs.setLayoutManager(layoutManagerForGraduateDocs);
        rvAttach_PHDDocs.setLayoutManager(layoutManagerForPGDocs);
        rvAttach_PGDocs.setLayoutManager(layoutManagerForPHDDocs);
        rvAttach_BedDedDocs.setLayoutManager(layoutManagerForBedDedDocs);

        staffImage          = v.findViewById(R.id.staffProfileImage);
        emp_name            = v.findViewById(R.id.staffName);
        emp_registration_id = v.findViewById(R.id.staffRegistartionNumber);
        emp_dept            = v.findViewById(R.id.staffDept);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
           /* case R.id.etEmployeeSchoolPassing:
                passingCase = "1";
                openCalendarDialog(passingCase);
                break;


            case R.id.etEmployeePUCPassing:
                passingCase = "2";
                openCalendarDialog(passingCase);
                break;*/

            case R.id.ivAttachSchoolDocs:
                imageCase = "A";
                openDialogForImageChoose();
                break;


            case R.id.ivAttachPUCDocs:
                imageCase = "B";
                openDialogForImageChoose();
                break;

            case R.id.ivAttachGraduateDocs:
                imageCase = "C";
                openDialogForImageChoose();
                break;


            case R.id.ivAttachPGDocs:
                imageCase = "D";
                openDialogForImageChoose();
                break;

            case R.id.ivAttachPHDDocs:
                imageCase = "E";
                openDialogForImageChoose();
                break;

            case R.id.ivAttachBedDedDocs:
                imageCase = "F";
                openDialogForImageChoose();
                break;


            case R.id.schoolInfoLayoutClick:

                if(linearLayoutPUCDetailsViews.getVisibility()==View.VISIBLE) {
                    pucLayoutVisibility();
                }
                if(linearLayoutUGDetailsViews.getVisibility()==View.VISIBLE) {
                    ugLayoutVisibility();
                }
                if(linearLayoutPGDetailsViews.getVisibility()==View.VISIBLE) {
                    pgLayoutVisibility();
                }
                if(linearLayoutPHDDetailsViews.getVisibility()==View.VISIBLE) {
                    phdLayoutVisibility();
                }
                if(linearLayoutBedDedDetailsViews.getVisibility()==View.VISIBLE) {
                    BedDedLayoutVisibility();
                }
                if(linearLayoutAchievementDetailsViews.getVisibility()==View.VISIBLE) {
                    achiemenetLayoutVisibility();
                }
                if(linearLayoutSchoolViews.getVisibility()==View.GONE){
                    schoolInfoLayout.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.anim_on));
                    schoolInfoTextView.setTextColor(getResources().getColor(R.color.colorPrimary));
                    schoolInfoTextView.setBackgroundColor(getResources().getColor(R.color.whitecolor));
                    schoolInfoImageViewDown.setColorFilter(getResources().getColor(R.color.colorPrimary));
                    linearLayoutSchoolViews.setVisibility(View.VISIBLE);
                    schoolInfoImageViewDown.setRotation(schoolInfoImageViewDown.getRotation() + 180);

                }
                else if(linearLayoutSchoolViews.getVisibility()==View.VISIBLE) {
                    schoolLayoutVisibility();


                }

                break;


            case R.id.pucInfoLayoutClick:

                if(linearLayoutSchoolViews.getVisibility()==View.VISIBLE) {
                    schoolLayoutVisibility();
                }
                if(linearLayoutUGDetailsViews.getVisibility()==View.VISIBLE) {
                    ugLayoutVisibility();
                }
                if(linearLayoutPGDetailsViews.getVisibility()==View.VISIBLE) {
                    pgLayoutVisibility();
                }
                if(linearLayoutPHDDetailsViews.getVisibility()==View.VISIBLE) {
                    phdLayoutVisibility();
                }
                if(linearLayoutAchievementDetailsViews.getVisibility()==View.VISIBLE) {
                    achiemenetLayoutVisibility();
                }
                if(linearLayoutBedDedDetailsViews.getVisibility()==View.VISIBLE) {
                    BedDedLayoutVisibility();
                }


                if(linearLayoutPUCDetailsViews.getVisibility()==View.GONE){
                    pucDeatailsLayout.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.anim_on));
                    pucInfoTextView.setTextColor(getResources().getColor(R.color.colorPrimary));
                    pucInfoTextView.setBackgroundColor(getResources().getColor(R.color.whitecolor));
                    pucInfoImageViewDown.setColorFilter(getResources().getColor(R.color.colorPrimary));
                    linearLayoutPUCDetailsViews.setVisibility(View.VISIBLE);
                    pucInfoImageViewDown.setRotation(pucInfoImageViewDown.getRotation() + 180);

                }
                else if(linearLayoutPUCDetailsViews.getVisibility()==View.VISIBLE) {
                    pucLayoutVisibility();


                }
                break;


            case R.id.UGDetailsLayoutClick:

                if(linearLayoutSchoolViews.getVisibility()==View.VISIBLE) {
                    schoolLayoutVisibility();
                }
                if(linearLayoutPUCDetailsViews.getVisibility()==View.VISIBLE) {
                    pucLayoutVisibility();

                }
                if(linearLayoutPGDetailsViews.getVisibility()==View.VISIBLE) {
                    pgLayoutVisibility();
                }
                if(linearLayoutPHDDetailsViews.getVisibility()==View.VISIBLE) {
                    phdLayoutVisibility();
                }
                if(linearLayoutAchievementDetailsViews.getVisibility()==View.VISIBLE) {
                    achiemenetLayoutVisibility();
                }
                if(linearLayoutBedDedDetailsViews.getVisibility()==View.VISIBLE) {
                    BedDedLayoutVisibility();
                }


                if(linearLayoutUGDetailsViews.getVisibility()==View.GONE){
                    ugDeatailsLayout.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.anim_on));
                    ugInfoTextView.setTextColor(getResources().getColor(R.color.colorPrimary));
                    ugInfoTextView.setBackgroundColor(getResources().getColor(R.color.whitecolor));
                    ugImageViewDown.setColorFilter(getResources().getColor(R.color.colorPrimary));
                    linearLayoutUGDetailsViews.setVisibility(View.VISIBLE);
                    ugImageViewDown.setRotation(ugImageViewDown.getRotation() + 180);

                }
                else if(linearLayoutUGDetailsViews.getVisibility()==View.VISIBLE) {
                    ugLayoutVisibility();



                }
                break;



            case R.id.PGDetailsLayoutClick:

                if(linearLayoutSchoolViews.getVisibility()==View.VISIBLE) {
                    schoolLayoutVisibility();
                }
                if(linearLayoutPUCDetailsViews.getVisibility()==View.VISIBLE) {
                    pucLayoutVisibility();

                }
                if(linearLayoutUGDetailsViews.getVisibility()==View.VISIBLE) {
                    ugLayoutVisibility();
                }
                if(linearLayoutPHDDetailsViews.getVisibility()==View.VISIBLE) {
                    phdLayoutVisibility();
                }
                if(linearLayoutAchievementDetailsViews.getVisibility()==View.VISIBLE) {
                    achiemenetLayoutVisibility();
                }
                if(linearLayoutBedDedDetailsViews.getVisibility()==View.VISIBLE) {
                    BedDedLayoutVisibility();
                }

                if(linearLayoutPGDetailsViews.getVisibility()==View.GONE){
                    pgDeatailsLayout.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.anim_on));
                    pgInfoTextView.setTextColor(getResources().getColor(R.color.colorPrimary));
                    pgInfoTextView.setBackgroundColor(getResources().getColor(R.color.whitecolor));
                    pgInfoImageViewDown.setColorFilter(getResources().getColor(R.color.colorPrimary));
                    linearLayoutPGDetailsViews.setVisibility(View.VISIBLE);
                    pgInfoImageViewDown.setRotation(pgInfoImageViewDown.getRotation() + 180);

                }
                else if(linearLayoutPGDetailsViews.getVisibility()==View.VISIBLE) {
                    pgLayoutVisibility();


                }

                break;


            case R.id.phdDetailsLayoutClick:

                if(linearLayoutSchoolViews.getVisibility()==View.VISIBLE) {
                    schoolLayoutVisibility();
                }
                if(linearLayoutPUCDetailsViews.getVisibility()==View.VISIBLE) {
                    pucLayoutVisibility();

                }
                if(linearLayoutUGDetailsViews.getVisibility()==View.VISIBLE) {
                    ugLayoutVisibility();
                }
                if(linearLayoutPGDetailsViews.getVisibility()==View.VISIBLE) {
                    pgLayoutVisibility();
                }
                if(linearLayoutAchievementDetailsViews.getVisibility()==View.VISIBLE) {
                    achiemenetLayoutVisibility();
                }
                if(linearLayoutBedDedDetailsViews.getVisibility()==View.VISIBLE) {
                    BedDedLayoutVisibility();
                }

                if(linearLayoutPHDDetailsViews.getVisibility()==View.GONE){
                    phdDeatailsLayout.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.anim_on));
                    phdInfoTextView.setTextColor(getResources().getColor(R.color.colorPrimary));
                    phdInfoTextView.setBackgroundColor(getResources().getColor(R.color.whitecolor));
                    phdInfoImageViewDown.setColorFilter(getResources().getColor(R.color.colorPrimary));
                    linearLayoutPHDDetailsViews.setVisibility(View.VISIBLE);
                    phdInfoImageViewDown.setRotation(phdInfoImageViewDown.getRotation() + 180);

                }
                else if(linearLayoutPHDDetailsViews.getVisibility()==View.VISIBLE) {
                    phdLayoutVisibility();


                }
                break;


            case R.id.achievemetDetailsLayoutClick:

                if(linearLayoutSchoolViews.getVisibility()==View.VISIBLE) {
                    schoolLayoutVisibility();
                }
                if(linearLayoutPUCDetailsViews.getVisibility()==View.VISIBLE) {
                    pucLayoutVisibility();

                }
                if(linearLayoutUGDetailsViews.getVisibility()==View.VISIBLE) {
                    ugLayoutVisibility();
                }
                if(linearLayoutPGDetailsViews.getVisibility()==View.VISIBLE) {
                    pgLayoutVisibility();
                }
                if(linearLayoutPHDDetailsViews.getVisibility()==View.VISIBLE) {
                    phdLayoutVisibility();
                }
                if(linearLayoutBedDedDetailsViews.getVisibility()==View.VISIBLE) {
                    BedDedLayoutVisibility();
                }

                if(linearLayoutAchievementDetailsViews.getVisibility()==View.GONE){
                    achievementDeatailsLayout.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.anim_on));
                    achievementInfoTextView.setTextColor(getResources().getColor(R.color.colorPrimary));
                    achievementInfoTextView.setBackgroundColor(getResources().getColor(R.color.whitecolor));
                    achievemetInfoImageViewDown.setColorFilter(getResources().getColor(R.color.colorPrimary));
                    linearLayoutAchievementDetailsViews.setVisibility(View.VISIBLE);
                    achievemetInfoImageViewDown.setRotation(achievemetInfoImageViewDown.getRotation() + 180);

                }
                else if(linearLayoutAchievementDetailsViews.getVisibility()==View.VISIBLE) {
                    achiemenetLayoutVisibility();

                }
                break;


            case R.id.sportsDetailsLayoutClick:

                if(linearLayoutSchoolViews.getVisibility()==View.VISIBLE) {
                    schoolLayoutVisibility();
                }
                if(linearLayoutPUCDetailsViews.getVisibility()==View.VISIBLE) {
                    pucLayoutVisibility();

                }
                if(linearLayoutUGDetailsViews.getVisibility()==View.VISIBLE) {
                    ugLayoutVisibility();
                }
                if(linearLayoutPGDetailsViews.getVisibility()==View.VISIBLE) {
                    pgLayoutVisibility();
                }
                if(linearLayoutPHDDetailsViews.getVisibility()==View.VISIBLE) {
                    phdLayoutVisibility();
                }
                if(linearLayoutBedDedDetailsViews.getVisibility()==View.VISIBLE) {
                    BedDedLayoutVisibility();
                }

                if(linearLayout_Sports_DetailsViews.getVisibility()==View.GONE){
                    sportsDetails_LayoutClick.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.anim_on));
                    sportsDetails_TextView.setTextColor(getResources().getColor(R.color.colorPrimary));
                    sportsDetails_TextView.setBackgroundColor(getResources().getColor(R.color.whitecolor));
                    sportsInfoImageViewDown.setColorFilter(getResources().getColor(R.color.colorPrimary));
                    linearLayout_Sports_DetailsViews.setVisibility(View.VISIBLE);
                    sportsInfoImageViewDown.setRotation(sportsInfoImageViewDown.getRotation() + 180);

                }
                else if(linearLayout_Sports_DetailsViews.getVisibility()==View.VISIBLE) {
                    SportsLayoutVisibility();

                }
                break;


            case R.id.BedDedDetailsLayoutClick:

                if(linearLayoutSchoolViews.getVisibility()==View.VISIBLE) {
                    schoolLayoutVisibility();
                }
                if(linearLayoutPUCDetailsViews.getVisibility()==View.VISIBLE) {
                    pucLayoutVisibility();

                }
                if(linearLayoutUGDetailsViews.getVisibility()==View.VISIBLE) {
                    ugLayoutVisibility();
                }
                if(linearLayoutPGDetailsViews.getVisibility()==View.VISIBLE) {
                    pgLayoutVisibility();
                }
                if(linearLayoutPHDDetailsViews.getVisibility()==View.VISIBLE) {
                    phdLayoutVisibility();
                }
                if(linearLayout_Sports_DetailsViews.getVisibility()==View.VISIBLE) {
                    SportsLayoutVisibility();
                }

                if(linearLayoutBedDedDetailsViews.getVisibility()==View.GONE){
                    BedDed_LayoutClick.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.anim_on));
                    BedDedInfoTextView.setTextColor(getResources().getColor(R.color.colorPrimary));
                    BedDedInfoTextView.setBackgroundColor(getResources().getColor(R.color.whitecolor));
                    BedDedInfoImageViewDown.setColorFilter(getResources().getColor(R.color.colorPrimary));
                    linearLayoutBedDedDetailsViews.setVisibility(View.VISIBLE);
                    BedDedInfoImageViewDown.setRotation(BedDedInfoImageViewDown.getRotation() + 180);

                }
                else if(linearLayoutBedDedDetailsViews.getVisibility()==View.VISIBLE) {
                    BedDedLayoutVisibility();

                }
                break;


            case R.id.button_increase_count:
                if(count!=max) {
                    count++;
                    btnDecreaseCount.setEnabled(true);
                    displayNumberOfGuardian(count);
                    View view = getLayoutInflater().inflate(R.layout.achievements_layout, null);
                    tvAchievementDetails               = view.findViewById(R.id.tvAchievemetNumber);
                    etEmployee_AchievementTitle        = view.findViewById(R.id.etEmployeeAchievementTitle);
                    etEmployee_AchievementYear         = view.findViewById(R.id.etEmployeeAchievementYear);
                    etEmployee_AchievementPlace        = view.findViewById(R.id.etEmployeeAchievementPlace);
                    etEmployee_AchievementDescription  = view.findViewById(R.id.etEmployeeAchievementDescription);
                    etEmployee_AchievementYear.setId(1000+count);
                    etEmployee_AchievementYear.addTextChangedListener
                            (new GenericTextWatcher(etEmployee_AchievementYear));
                    arrayList_AchievementTitle.add(etEmployee_AchievementTitle);
                    arrayList_AchievementYear.add(etEmployee_AchievementYear);
                    arrayList_AchievementPlace.add(etEmployee_AchievementPlace);
                    arrayList_AchievementDescription.add(etEmployee_AchievementDescription);
                    tvAchievementDetails.setText(""+count);
                    achivemntsLinearLayout.addView(view, 0);

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
                    achivemntsLinearLayout.removeViewAt(0);
                    arrayList_AchievementTitle.remove(count);
                    arrayList_AchievementYear.remove(count);
                    arrayList_AchievementPlace.remove(count);
                    arrayList_AchievementDescription.remove(count);
                    tvAchievementDetails.setText(""+count);


                }

                else{
                    btnDecreaseCount.setEnabled(false);
                }
                break;





            case R.id.button_increase_sports:
                if(count1!=max) {
                    count1++;
                    btnDecreaseCount.setEnabled(true);
                    displayNumberOfSports(count1);
                    View view = getLayoutInflater().inflate(R.layout.dynamic_sports, null);
                    tvSports_Number                   = view.findViewById(R.id.tvSportsNumber);
                    et_SportsName                     = view.findViewById(R.id.etSportsName);
                    et_SportsYear                     = view.findViewById(R.id.etSportsYear);
                    et_sportsLevel                    = view.findViewById(R.id.etsportsLevel);
                    et_SportsPosition                 = view.findViewById(R.id.etSportsPosition);
                    et_SportTeamName                  = view.findViewById(R.id.etSportsTeamName);
                    et_SportsLocation                 = view.findViewById(R.id.etSportsLocation);
                    et_SportsYear.setId(100+count);
                    et_SportsYear.addTextChangedListener
                            (new GenericTextWatcher(et_SportsYear));
                    arrayList_SportsName.add(et_SportsName);
                    arrayList_SportsYear.add(et_SportsYear);
                    arrayList_sportsLevel.add(et_sportsLevel);
                    arrayList_SportsPosition.add(et_SportsPosition);
                    arrayList_SportTeamName.add(et_SportTeamName);
                    arrayList_SportsLocation.add(et_SportsLocation);
                    tvSports_Number.setText(""+count1);






                    sports_Layout.addView(view, 0);

                }

                else {
                    btnIncreaseCount.setEnabled(false);
                }

                break;

            case R.id.button_decrease_count_sports:
                if(count1!=0){
                    count1--;
                    btnIncreaseCount.setEnabled(true);
                    displayNumberOfSports(count1);
                    sports_Layout.removeViewAt(0);

                    arrayList_SportsName.remove(arrayList_SportsName.size()-1);
                    arrayList_SportsYear.remove(arrayList_SportsYear.size()-1);
                    arrayList_sportsLevel.remove(arrayList_sportsLevel.size()-1);
                    arrayList_SportsPosition.remove(arrayList_SportsPosition.size()-1);
                    arrayList_SportTeamName.remove(arrayList_SportTeamName.size()-1);
                    arrayList_SportsLocation.remove(arrayList_SportsLocation.size()-1);

                }

                else{
                    btnDecreaseCount.setEnabled(false);
                }
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

                /*Intent in_gallery=new Intent(Intent.ACTION_PICK);
                in_gallery.setType("image*//*");
                in_gallery.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(in_gallery,Constant.FROM_GALLERY);*/
                imageChooserDialog.dismiss();
                break;

            case R.id.btnEmployeeEducationSubmit:

                if(!emp_registration_id.getText().toString().equals("Registration Number")){
                    validateData();
                }
                else {
                    Toast.makeText(getActivity(), "Please select staff", Toast.LENGTH_SHORT).show();
                }



                break;


        }

    }

    private void validateData() {

        switch(Constant.STAFF_TYPE){
            case "Teaching":
                teacherDataValidation();
                break;


            case "Non_Teaching":
                non_teacherDataValidation();//same Ui as Teacher UI
                break;


            case "Transportation":
                transportDataValidation();
                break;

        }
    }

    private void non_teacherDataValidation() {

        if(!ValidationViews.EditTextNullValidate(etEmployee_SchoolName)){

        }

        else if(!ValidationViews.EditTextNullValidate(etEmployee_SchoolPercentage)){
        }

        else if(Double.parseDouble(etEmployee_SchoolPercentage.getText().toString())<35){
            Toast.makeText(getActivity(), "Please check School percentage", Toast.LENGTH_SHORT).show();
        }

        else if(!ValidationViews.EditTextNullValidate(etEmployee_SchoolPassing)){
            Toast.makeText(getActivity(), "Please check School passing year", Toast.LENGTH_SHORT).show();
        }

        else if(Integer.parseInt(etEmployee_SchoolPassing.getText().toString())<1990){
            Toast.makeText(getActivity(), "Please check School passing year", Toast.LENGTH_SHORT).show();
        }

        else if(!ValidationViews.EditTextNullValidate(etEmployee_SchoolBoard)){

        }
        else if(!ValidationViews.EditTextNullValidate(etEmployee_SchoolState)){

        }
        else if(arrayListSchoolDocsModel.size()==0){
            Toast.makeText(getActivity(), "Please upload school documents", Toast.LENGTH_SHORT).show();

        }

        else if(!ValidationViews.EditTextNullValidate(etEmployee_PUCName)){
        }
        else if(!ValidationViews.EditTextNullValidate(etEmployee_PUCPercentage)){

        }

        else if(Double.parseDouble(etEmployee_PUCPercentage.getText().toString())<35){
            Toast.makeText(getActivity(), "Please check PUC percentage", Toast.LENGTH_SHORT).show();
        }

        else if(!ValidationViews.EditTextNullValidate(etEmployee_PUCPassing)){
            Toast.makeText(getActivity(), "Please check PUC passing year", Toast.LENGTH_SHORT).show();

        }

        else if(Integer.parseInt(etEmployee_PUCPassing.getText().toString())<1990){
            Toast.makeText(getActivity(), "Please check PUC passing year", Toast.LENGTH_SHORT).show();
        }

        else if(!ValidationViews.EditTextNullValidate(etEmployee_PUCBoard)){

        }
        else if(!ValidationViews.EditTextNullValidate(etEmployee_PUCState)){

        }
        else if(arrayListPUCDocsModel.size()==0){
            Toast.makeText(getActivity(), "Please upload PUC documents", Toast.LENGTH_SHORT).show();

        }

        else if(!ValidationViews.EditTextNullValidate(etEmployee_GraduateCollegeName)){
        }

        else if(!ValidationViews.EditTextNullValidate(etEmployee_GraduateUniversityName)){
        }

        else if(!ValidationViews.EditTextNullValidate(etEmployee_GraduateSpecialization)){

        }
        else if(!ValidationViews.EditTextNullValidate(etEmployee_GraduateBranchName)){

        }
        else if(!ValidationViews.EditTextNullValidate(etEmployee_GraduatePercentage)){

        }
        else if(Double.parseDouble(etEmployee_GraduatePercentage.getText().toString())<35){
            Toast.makeText(getActivity(), "Please check graduate percentage", Toast.LENGTH_SHORT).show();
        }

        else if(!ValidationViews.EditTextNullValidate(etEmployee_GraduatePassingYear)){
            Toast.makeText(getActivity(), "Please check Graduate passing year", Toast.LENGTH_SHORT).show();

        }

        else if(Integer.parseInt(etEmployee_GraduatePassingYear.getText().toString())<1990){
            Toast.makeText(getActivity(), "Please check Graduate passing year", Toast.LENGTH_SHORT).show();
        }

        else if(arrayListGraduateDocsModel.size()==0){
            Toast.makeText(getActivity(), "Please upload Graduation documents", Toast.LENGTH_SHORT).show();

        }
        else if(!etEmployee_PGPercentage.getText().toString().equals("") && Double.parseDouble(etEmployee_PGPercentage.getText().toString())<35 ){
            Toast.makeText(getActivity(), "Please check PG percentage", Toast.LENGTH_SHORT).show();
        }

        else if(!etEmployee_PGPassingYear.getText().toString().equals("") && etEmployee_PGPassingYear.getText().length()<4 ){
            Toast.makeText(getActivity(), "Please check PG passing year", Toast.LENGTH_SHORT).show();
        }

        else {

            if(count!=0){

                for(int i = 0 ; i<arrayList_AchievementTitle.size(); i++){

                    etEmployee_AchievementTitle = arrayList_AchievementTitle.get(i);
                    etEmployee_AchievementYear    = arrayList_AchievementYear.get(i);
                    etEmployee_AchievementPlace = arrayList_AchievementPlace.get(i);
                    etEmployee_AchievementDescription = arrayList_AchievementDescription.get(i);

                    if(!ValidationViews.EditTextNullValidate(etEmployee_AchievementTitle)){
                        return;
                    }
                    else if(!ValidationViews.EditTextNullValidate(etEmployee_AchievementYear)){
                        return;
                    }

                    else if(!ValidationViews.EditTextNullValidate(etEmployee_AchievementPlace)){
                        return;
                    }

                    else if(!ValidationViews.EditTextNullValidate(etEmployee_AchievementDescription)){
                        return;
                    }

                }


            }

            if(count1!=0){

                for (int i = 0; i < arrayList_SportsName.size(); i++) {

                    et_SportsName = arrayList_SportsName.get(i);
                    et_SportsYear = arrayList_SportsYear.get(i);
                    et_sportsLevel = arrayList_sportsLevel.get(i);
                    et_SportsPosition = arrayList_SportsPosition.get(i);
                    et_SportTeamName = arrayList_SportTeamName.get(i);
                    et_SportsLocation = arrayList_SportsLocation.get(i);

                    if(!ValidationViews.EditTextNullValidate(et_SportsName)){
                        return;
                    }
                    else if(!ValidationViews.EditTextNullValidate(et_SportsYear)){
                        return;
                    }

                    else if(!ValidationViews.EditTextNullValidate(et_sportsLevel)){
                        return;
                    }

                    else if(!ValidationViews.EditTextNullValidate(et_SportsPosition)){
                        return;
                    }

                    else if(!ValidationViews.EditTextNullValidate(et_SportTeamName)){
                        return;
                    }

                    else if(!ValidationViews.EditTextNullValidate(et_SportsLocation)){
                        return;
                    }


                }

            }

            str_Employee_SchoolName = etEmployee_SchoolName.getText().toString();
            str_Employee_SchoolPercentage = etEmployee_SchoolPercentage.getText().toString();
            str_Employee_SchoolPassing = etEmployee_SchoolPassing.getText().toString();
            str_Employee_SchoolBoard = etEmployee_SchoolBoard.getText().toString();
            str_Employee_SchoolState = etEmployee_SchoolState.getText().toString();
            str_Employee_PUCName = etEmployee_PUCName.getText().toString();
            str_Employee_PUCPercentage = etEmployee_PUCPercentage.getText().toString();
            str_Employee_PUCPassing = etEmployee_PUCPassing.getText().toString();
            str_Employee_PUCBoard = etEmployee_PUCBoard.getText().toString();
            str_Employee_PUCState = etEmployee_PUCState.getText().toString();
            str_Employee_GraduateCollegeName = etEmployee_GraduateCollegeName.getText().toString();
            str_Employee_GraduateUniversityName = etEmployee_GraduateUniversityName.getText().toString();
            str_Employee_GraduateSpecialization = etEmployee_GraduateSpecialization.getText().toString();
            str_Employee_GraduateBranchName = etEmployee_GraduateBranchName.getText().toString();
            str_Employee_GraduatePercentage = etEmployee_GraduatePercentage.getText().toString();
            str_Employee_GraduatePassingYear = etEmployee_GraduatePassingYear.getText().toString();
            str_Employee_PGCollegeName = etEmployee_PGCollegeName.getText().toString();
            str_Employee_PGUniversityName = etEmployee_PGUniversityName.getText().toString();
            str_Employee_PGSpecialization = etEmployee_PGSpecialization.getText().toString();
            str_Employee_PGBranchName = etEmployee_PGBranchName.getText().toString();
            str_Employee_PGPercentage = etEmployee_PGPercentage.getText().toString();
            str_Employee_PGPassingYear = etEmployee_PGPassingYear.getText().toString();


            EmployeeAddModel employeeSchoolDetails = new EmployeeAddModel(str_Employee_SchoolName, str_Employee_SchoolPercentage,
                    str_Employee_SchoolPassing, str_Employee_SchoolBoard,
                    str_Employee_SchoolState);


            EmployeeAddModel employeePUCDetails = new EmployeeAddModel(str_Employee_FirstName, str_Employee_PUCName,
                    str_Employee_PUCPercentage, str_Employee_PUCPassing,
                    str_Employee_PUCBoard, str_Employee_PUCState);


            EmployeeAddModel employeeGraduationDetails = new EmployeeAddModel(str_Employee_FirstName, str_Employee_GraduateCollegeName, str_Employee_GraduateUniversityName,
                    str_Employee_GraduateSpecialization, str_Employee_GraduateBranchName,
                    str_Employee_GraduatePercentage, str_Employee_GraduatePassingYear);

            EmployeeAddModel employeePostGraduationDetails = new EmployeeAddModel(str_Employee_FirstName, str_Employee_LastName, str_Employee_PGCollegeName,
                    str_Employee_PGUniversityName, str_Employee_PGSpecialization,
                    str_Employee_PGBranchName, str_Employee_PGPercentage, str_Employee_PGPassingYear);


            EmployeeAddModel employeeAddModel = new EmployeeAddModel(employeeSchoolDetails, employeePUCDetails,
                    employeeGraduationDetails, employeePostGraduationDetails);


            mRef.child(Constant.STAFF_TYPE).child(Constant.STAFF_FINAL_ID)
                    .child("Educational Details").setValue(employeeAddModel);


            DatabaseReference db_parent = mRef.child(Constant.STAFF_TYPE).child(Constant.STAFF_FINAL_ID)
                    .child("Educational Details");


            if (count != 0) {

                for (int i = 0; i < arrayList_AchievementTitle.size(); i++) {

                    String achievement = arrayList_AchievementTitle.get(i).getText().toString();
                    String achievementYear = arrayList_AchievementYear.get(i).getText().toString();
                    String achievementPlace = arrayList_AchievementPlace.get(i).getText().toString();
                    String achievementDescr = arrayList_AchievementDescription.get(i).getText().toString();


                    DatabaseReference db_child = db_parent.child("employee_achievements_details").child("achievements" + (i + 1));
                    db_child.child("str_Achievement_title").setValue(achievement);
                    db_child.child("str_Achievement_year").setValue(achievementYear);
                    db_child.child("str_Achievement_place").setValue(achievementPlace);
                    db_child.child("str_Achievement_description").setValue(achievementDescr);


                }




            }

            if(count1!=0){

                for (int i = 0; i < arrayList_SportsName.size(); i++) {

                    str_EmployeeSportsName = arrayList_SportsName.get(i).getText().toString();
                    str_EmployeeSportsYear = arrayList_SportsYear.get(i).getText().toString();
                    str_EmployeeSportsLevel = arrayList_sportsLevel.get(i).getText().toString();
                    str_EmployeeSportsPosition = arrayList_SportsPosition.get(i).getText().toString();
                    str_EmployeeSportsTeamName = arrayList_SportTeamName.get(i).getText().toString();
                    str_EmployeeSportsLocation = arrayList_SportsLocation.get(i).getText().toString();


                    DatabaseReference db_child = db_parent.child("employee_sports_details").child("sports" + (i + 1));
                    db_child.child("str_SportsName").setValue(str_EmployeeSportsName);
                    db_child.child("str_SportsYear").setValue(str_EmployeeSportsYear);
                    db_child.child("str_SportsLevel").setValue(str_EmployeeSportsLevel);
                    db_child.child("str_SportsPosition").setValue(str_EmployeeSportsPosition);
                    db_child.child("str_SportsTeamName").setValue(str_EmployeeSportsTeamName);
                    db_child.child("str_SportsLocation").setValue(str_EmployeeSportsLocation);


                }


            }


            StorageReference storageReference = mStoargeref_Staff_Details_Images.child(Constant.STAFF_TYPE).child(Constant.STAFF_FINAL_ID);


            if(arrayListSchoolDocs.size()!=0){

                imageCounter=0;

                for(int i = 0 ; i<arrayListSchoolDocs.size(); i++){


                    storageReference.child("Educational Details").child("employee_school_deatils").child("str_School_Docs"+i).putFile(arrayListSchoolDocs.get(i)).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            String url = taskSnapshot.getDownloadUrl().toString();
                            mRef.child(Constant.STAFF_TYPE).child(Constant.STAFF_FINAL_ID)
                                    .child("Educational Details").child("employee_school_deatils")
                                    .child("Document"+imageCounter).setValue(url);
                            imageCounter++;

                        }

                    });
                }
            }

            if(arrayListPUCDocs.size()!=0){

                imageCounter=0;

                for(int i = 0 ; i<arrayListPUCDocs.size(); i++){


                    storageReference.child("Educational Details").child("employee_puc_details").child("str_PUC_Docs"+i).putFile(arrayListPUCDocs.get(i)).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            String url = taskSnapshot.getDownloadUrl().toString();
                            mRef.child(Constant.STAFF_TYPE).child(Constant.STAFF_FINAL_ID)
                                    .child("Educational Details").child("employee_puc_details")
                                    .child("Document"+imageCounter).setValue(url);
                            imageCounter++;

                        }

                    });
                }
            }


            if(arrayListGraduationDocs.size()!=0){

                imageCounter=0;

                for(int i = 0 ; i<arrayListGraduationDocs.size(); i++){


                    storageReference.child("Educational Details").child("employee_graduation_details").child("str_Graduate_Docs"+i).putFile(arrayListGraduationDocs.get(i)).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            String url = taskSnapshot.getDownloadUrl().toString();
                            mRef.child(Constant.STAFF_TYPE).child(Constant.STAFF_FINAL_ID)
                                    .child("Educational Details").child("employee_graduation_details")
                                    .child("Document"+imageCounter).setValue(url);
                            imageCounter++;

                        }

                    });
                }
            }



            if(arrayListPGDocs.size()!=0){

                imageCounter=0;

                for(int i = 0 ; i<arrayListPGDocs.size(); i++){

                    storageReference.child("Educational Details").child("employee_postgraduation_details").child("str_Pg_Docs"+i).putFile(arrayListPGDocs.get(i)).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            String url = taskSnapshot.getDownloadUrl().toString();
                            mRef.child(Constant.STAFF_TYPE).child(Constant.STAFF_FINAL_ID)
                                    .child("Educational Details").child("employee_postgraduation_details")
                                    .child("Document"+imageCounter).setValue(url);
                            imageCounter++;

                        }

                    });
                }
            }


            EventBus.getDefault().post(new EmployeeAddModel(1, registartionNumber, name, dept,Constant.STAFF_PROFILE_IMAGE));
            AddEmployeeLanding newAdmissionActivity = (AddEmployeeLanding) getActivity();
            WorkInfoFragment workInfoFragment = new WorkInfoFragment();
            assert newAdmissionActivity != null;
            newAdmissionActivity.loadNextFragment(BUTTON_ENABLE, workInfoFragment, null);



        }
    }

    private void transportDataValidation() {

        if(!ValidationViews.EditTextNullValidate(etEmployee_SchoolName)){

        }

        else if(!ValidationViews.EditTextNullValidate(etEmployee_SchoolPercentage)){
        }

        else if(Double.parseDouble(etEmployee_SchoolPercentage.getText().toString())<35){
            Toast.makeText(getActivity(), "Please check School percentage", Toast.LENGTH_SHORT).show();
        }

        else if(!ValidationViews.EditTextNullValidate(etEmployee_SchoolPassing)){
            Toast.makeText(getActivity(), "Please check School passing year", Toast.LENGTH_SHORT).show();
        }

        else if(Integer.parseInt(etEmployee_SchoolPassing.getText().toString())<1990){
            Toast.makeText(getActivity(), "Please check School passing year", Toast.LENGTH_SHORT).show();
        }

        else if(!ValidationViews.EditTextNullValidate(etEmployee_SchoolBoard)){

        }
        else if(!ValidationViews.EditTextNullValidate(etEmployee_SchoolState)){

        }
        else if(arrayListSchoolDocsModel.size()==0){
            Toast.makeText(getActivity(), "Please upload school documents", Toast.LENGTH_SHORT).show();

        }

        else if(!ValidationViews.EditTextNullValidate(etEmployee_PUCName)){
        }
        else if(!ValidationViews.EditTextNullValidate(etEmployee_PUCPercentage)){

        }

        else if(Double.parseDouble(etEmployee_PUCPercentage.getText().toString())<35){
            Toast.makeText(getActivity(), "Please check PUC percentage", Toast.LENGTH_SHORT).show();
        }

        else if(!ValidationViews.EditTextNullValidate(etEmployee_PUCPassing)){
            Toast.makeText(getActivity(), "Please check PUC passing year", Toast.LENGTH_SHORT).show();

        }

        else if(Integer.parseInt(etEmployee_PUCPassing.getText().toString())<1990){
            Toast.makeText(getActivity(), "Please check PUC passing year", Toast.LENGTH_SHORT).show();
        }

        else if(!ValidationViews.EditTextNullValidate(etEmployee_PUCBoard)){

        }
        else if(!ValidationViews.EditTextNullValidate(etEmployee_PUCState)){

        }
        else if(arrayListPUCDocsModel.size()==0){
            Toast.makeText(getActivity(), "Please upload PUC documents", Toast.LENGTH_SHORT).show();

        }

        else if(!etEmployee_GraduatePercentage.getText().toString().equals("") && Double.parseDouble(etEmployee_GraduatePercentage.getText().toString())<35 ){
            Toast.makeText(getActivity(), "Please check Graduate percentage", Toast.LENGTH_SHORT).show();
        }

        else if(!etEmployee_GraduatePassingYear.getText().toString().equals("") && etEmployee_GraduatePassingYear.getText().length()<4 ){
            Toast.makeText(getActivity(), "Please check Graduate passing year", Toast.LENGTH_SHORT).show();
        }

        else if(!etEmployee_PGPercentage.getText().toString().equals("") && Double.parseDouble(etEmployee_PGPercentage.getText().toString())<35 ){
            Toast.makeText(getActivity(), "Please check PG percentage", Toast.LENGTH_SHORT).show();
        }

        else if(!etEmployee_PGPassingYear.getText().toString().equals("") && etEmployee_PGPassingYear.getText().length()<4 ){
            Toast.makeText(getActivity(), "Please check PG passing year", Toast.LENGTH_SHORT).show();
        }

        else {

            if(count!=0){

                for(int i = 0 ; i<arrayList_AchievementTitle.size(); i++){

                    etEmployee_AchievementTitle = arrayList_AchievementTitle.get(i);
                    etEmployee_AchievementYear    = arrayList_AchievementYear.get(i);
                    etEmployee_AchievementPlace = arrayList_AchievementPlace.get(i);
                    etEmployee_AchievementDescription = arrayList_AchievementDescription.get(i);

                    if(!ValidationViews.EditTextNullValidate(etEmployee_AchievementTitle)){
                        return;
                    }
                    else if(!ValidationViews.EditTextNullValidate(etEmployee_AchievementYear)){
                        return;
                    }

                    else if(Integer.parseInt(etEmployee_AchievementYear.getText().toString())<1990){
                        Toast.makeText(getActivity(), "Please check achievement year", Toast.LENGTH_SHORT).show();
                    }

                    else if(!ValidationViews.EditTextNullValidate(etEmployee_AchievementPlace)){
                        return;
                    }

                    else if(!ValidationViews.EditTextNullValidate(etEmployee_AchievementDescription)){
                        return;
                    }

                }


            }

            if(count1!=0){

                for (int i = 0; i < arrayList_SportsName.size(); i++) {

                    et_SportsName = arrayList_SportsName.get(i);
                    et_SportsYear = arrayList_SportsYear.get(i);
                    et_sportsLevel = arrayList_sportsLevel.get(i);
                    et_SportsPosition = arrayList_SportsPosition.get(i);
                    et_SportTeamName = arrayList_SportTeamName.get(i);
                    et_SportsLocation = arrayList_SportsLocation.get(i);

                    if(!ValidationViews.EditTextNullValidate(et_SportsName)){
                        return;
                    }
                    else if(!ValidationViews.EditTextNullValidate(et_SportsYear)){
                        return;
                    }

                    else if(Integer.parseInt(et_SportsYear.getText().toString())<1990){
                        Toast.makeText(getActivity(), "Please check sport year", Toast.LENGTH_SHORT).show();
                    }

                    else if(!ValidationViews.EditTextNullValidate(et_sportsLevel)){
                        return;
                    }

                    else if(!ValidationViews.EditTextNullValidate(et_SportsPosition)){
                        return;
                    }

                    else if(!ValidationViews.EditTextNullValidate(et_SportTeamName)){
                        return;
                    }

                    else if(!ValidationViews.EditTextNullValidate(et_SportsLocation)){
                        return;
                    }


                }

            }

            str_Employee_SchoolName = etEmployee_SchoolName.getText().toString();
            str_Employee_SchoolPercentage = etEmployee_SchoolPercentage.getText().toString();
            str_Employee_SchoolPassing = etEmployee_SchoolPassing.getText().toString();
            str_Employee_SchoolBoard = etEmployee_SchoolBoard.getText().toString();
            str_Employee_SchoolState = etEmployee_SchoolState.getText().toString();
            str_Employee_PUCName = etEmployee_PUCName.getText().toString();
            str_Employee_PUCPercentage = etEmployee_PUCPercentage.getText().toString();
            str_Employee_PUCPassing = etEmployee_PUCPassing.getText().toString();
            str_Employee_PUCBoard = etEmployee_PUCBoard.getText().toString();
            str_Employee_PUCState = etEmployee_PUCState.getText().toString();
            str_Employee_GraduateCollegeName = etEmployee_GraduateCollegeName.getText().toString();
            str_Employee_GraduateUniversityName = etEmployee_GraduateUniversityName.getText().toString();
            str_Employee_GraduateSpecialization = etEmployee_GraduateSpecialization.getText().toString();
            str_Employee_GraduateBranchName = etEmployee_GraduateBranchName.getText().toString();
            str_Employee_GraduatePercentage = etEmployee_GraduatePercentage.getText().toString();
            str_Employee_GraduatePassingYear = etEmployee_GraduatePassingYear.getText().toString();
            str_Employee_PGCollegeName = etEmployee_PGCollegeName.getText().toString();
            str_Employee_PGUniversityName = etEmployee_PGUniversityName.getText().toString();
            str_Employee_PGSpecialization = etEmployee_PGSpecialization.getText().toString();
            str_Employee_PGBranchName = etEmployee_PGBranchName.getText().toString();
            str_Employee_PGPercentage = etEmployee_PGPercentage.getText().toString();
            str_Employee_PGPassingYear = etEmployee_PGPassingYear.getText().toString();


            EmployeeAddModel employeeSchoolDetails = new EmployeeAddModel(str_Employee_SchoolName, str_Employee_SchoolPercentage,
                    str_Employee_SchoolPassing, str_Employee_SchoolBoard,
                    str_Employee_SchoolState);


            EmployeeAddModel employeePUCDetails = new EmployeeAddModel(str_Employee_FirstName, str_Employee_PUCName,
                    str_Employee_PUCPercentage, str_Employee_PUCPassing,
                    str_Employee_PUCBoard, str_Employee_PUCState);


            EmployeeAddModel employeeGraduationDetails = new EmployeeAddModel(str_Employee_FirstName, str_Employee_GraduateCollegeName, str_Employee_GraduateUniversityName,
                    str_Employee_GraduateSpecialization, str_Employee_GraduateBranchName,
                    str_Employee_GraduatePercentage, str_Employee_GraduatePassingYear);

            EmployeeAddModel employeePostGraduationDetails = new EmployeeAddModel(str_Employee_FirstName, str_Employee_LastName, str_Employee_PGCollegeName,
                    str_Employee_PGUniversityName, str_Employee_PGSpecialization,
                    str_Employee_PGBranchName, str_Employee_PGPercentage, str_Employee_PGPassingYear);


            EmployeeAddModel employeeAddModel = new EmployeeAddModel(employeeSchoolDetails, employeePUCDetails,
                    employeeGraduationDetails, employeePostGraduationDetails);


            mRef.child(Constant.STAFF_TYPE).child(Constant.STAFF_FINAL_ID)
                    .child("Educational Details").setValue(employeeAddModel);


            DatabaseReference db_parent = mRef.child(Constant.STAFF_TYPE).child(Constant.STAFF_FINAL_ID)
                    .child("Educational Details");


            if (count != 0) {

                for (int i = 0; i < arrayList_AchievementTitle.size(); i++) {

                    String achievement = arrayList_AchievementTitle.get(i).getText().toString();
                    String achievementYear = arrayList_AchievementYear.get(i).getText().toString();
                    String achievementPlace = arrayList_AchievementPlace.get(i).getText().toString();
                    String achievementDescr = arrayList_AchievementDescription.get(i).getText().toString();


                    DatabaseReference db_child = db_parent.child("employee_achievements_details").child("achievements" + (i + 1));
                    db_child.child("str_Achievement_title").setValue(achievement);
                    db_child.child("str_Achievement_year").setValue(achievementYear);
                    db_child.child("str_Achievement_place").setValue(achievementPlace);
                    db_child.child("str_Achievement_description").setValue(achievementDescr);


                }




            }

            if(count1!=0){

                for (int i = 0; i < arrayList_SportsName.size(); i++) {

                    str_EmployeeSportsName = arrayList_SportsName.get(i).getText().toString();
                    str_EmployeeSportsYear = arrayList_SportsYear.get(i).getText().toString();
                    str_EmployeeSportsLevel = arrayList_sportsLevel.get(i).getText().toString();
                    str_EmployeeSportsPosition = arrayList_SportsPosition.get(i).getText().toString();
                    str_EmployeeSportsTeamName = arrayList_SportTeamName.get(i).getText().toString();
                    str_EmployeeSportsLocation = arrayList_SportsLocation.get(i).getText().toString();


                    DatabaseReference db_child = db_parent.child("employee_sports_details").child("sports" + (i + 1));
                    db_child.child("str_SportsName").setValue(str_EmployeeSportsName);
                    db_child.child("str_SportsYear").setValue(str_EmployeeSportsYear);
                    db_child.child("str_SportsLevel").setValue(str_EmployeeSportsLevel);
                    db_child.child("str_SportsPosition").setValue(str_EmployeeSportsPosition);
                    db_child.child("str_SportsTeamName").setValue(str_EmployeeSportsTeamName);
                    db_child.child("str_SportsLocation").setValue(str_EmployeeSportsLocation);


                }


            }

            StorageReference storageReference = mStoargeref_Staff_Details_Images.child(Constant.STAFF_TYPE).child(Constant.STAFF_FINAL_ID);


            if(arrayListSchoolDocs.size()!=0){

                imageCounter=0;

                for(int i = 0 ; i<arrayListSchoolDocs.size(); i++){


                    storageReference.child("Educational Details").child("employee_school_deatils").child("str_School_Docs"+i).putFile(arrayListSchoolDocs.get(i)).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            String url = taskSnapshot.getDownloadUrl().toString();
                            mRef.child(Constant.STAFF_TYPE).child(Constant.STAFF_FINAL_ID)
                                    .child("Educational Details").child("employee_school_deatils")
                                    .child("Document"+imageCounter).setValue(url);
                            imageCounter++;

                        }

                    });
                }
            }

            if(arrayListPUCDocs.size()!=0){

                imageCounter=0;

                for(int i = 0 ; i<arrayListPUCDocs.size(); i++){


                    storageReference.child("Educational Details").child("employee_puc_details").child("str_PUC_Docs"+i).putFile(arrayListPUCDocs.get(i)).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            String url = taskSnapshot.getDownloadUrl().toString();
                            mRef.child(Constant.STAFF_TYPE).child(Constant.STAFF_FINAL_ID)
                                    .child("Educational Details").child("employee_puc_details")
                                    .child("Document"+imageCounter).setValue(url);
                            imageCounter++;

                        }

                    });
                }
            }


            if(arrayListGraduationDocs.size()!=0){

                imageCounter=0;

                for(int i = 0 ; i<arrayListGraduationDocs.size(); i++){


                    storageReference.child("Educational Details").child("employee_graduation_details").child("str_Graduate_Docs"+i).putFile(arrayListGraduationDocs.get(i)).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            String url = taskSnapshot.getDownloadUrl().toString();
                            mRef.child(Constant.STAFF_TYPE).child(Constant.STAFF_FINAL_ID)
                                    .child("Educational Details").child("employee_graduation_details")
                                    .child("Document"+imageCounter).setValue(url);
                            imageCounter++;

                        }

                    });
                }
            }



            if(arrayListPGDocs.size()!=0){

                imageCounter=0;

                for(int i = 0 ; i<arrayListPGDocs.size(); i++){

                    storageReference.child("Educational Details").child("employee_postgraduation_details").child("str_Pg_Docs"+i).putFile(arrayListPGDocs.get(i)).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            String url = taskSnapshot.getDownloadUrl().toString();
                            mRef.child(Constant.STAFF_TYPE).child(Constant.STAFF_FINAL_ID)
                                    .child("Educational Details").child("employee_postgraduation_details")
                                    .child("Document"+imageCounter).setValue(url);
                            imageCounter++;

                        }

                    });
                }
            }


            EventBus.getDefault().post(new EmployeeAddModel(1, registartionNumber, name, dept,Constant.STAFF_PROFILE_IMAGE));
            AddEmployeeLanding newAdmissionActivity = (AddEmployeeLanding) getActivity();
            WorkInfoFragment workInfoFragment = new WorkInfoFragment();
            assert newAdmissionActivity != null;
            newAdmissionActivity.loadNextFragment(BUTTON_ENABLE, workInfoFragment, null);



        }



    }

    private void teacherDataValidation() {

        if(!ValidationViews.EditTextNullValidate(etEmployee_SchoolName)){

        }

        else if(!ValidationViews.EditTextNullValidate(etEmployee_SchoolPercentage)){
        }

        else if(Double.parseDouble(etEmployee_SchoolPercentage.getText().toString())<35){
            Toast.makeText(getActivity(), "Please check School percentage", Toast.LENGTH_SHORT).show();
        }

        else if(!ValidationViews.EditTextNullValidate(etEmployee_SchoolPassing)){
            Toast.makeText(getActivity(), "Please check School passing year", Toast.LENGTH_SHORT).show();
        }

        else if(Integer.parseInt(etEmployee_SchoolPassing.getText().toString())<1990){
            Toast.makeText(getActivity(), "Please check School passing year", Toast.LENGTH_SHORT).show();
        }

        else if(!ValidationViews.EditTextNullValidate(etEmployee_SchoolBoard)){

        }
        else if(!ValidationViews.EditTextNullValidate(etEmployee_SchoolState)){

        }
        else if(arrayListSchoolDocsModel.size()==0){
            Toast.makeText(getActivity(), "Please upload school documents", Toast.LENGTH_SHORT).show();

        }

        else if(!ValidationViews.EditTextNullValidate(etEmployee_PUCName)){
                }
        else if(!ValidationViews.EditTextNullValidate(etEmployee_PUCPercentage)){

                }

        else if(Double.parseDouble(etEmployee_PUCPercentage.getText().toString())<35){
                    Toast.makeText(getActivity(), "Please check PUC percentage", Toast.LENGTH_SHORT).show();
                }

        else if(!ValidationViews.EditTextNullValidate(etEmployee_PUCPassing)){
                    Toast.makeText(getActivity(), "Please check PUC passing year", Toast.LENGTH_SHORT).show();

                }

        else if(Integer.parseInt(etEmployee_PUCPassing.getText().toString())<1990){
                    Toast.makeText(getActivity(), "Please check PUC passing year", Toast.LENGTH_SHORT).show();
                }

        else if(!ValidationViews.EditTextNullValidate(etEmployee_PUCBoard)){

                }
        else if(!ValidationViews.EditTextNullValidate(etEmployee_PUCState)){

                }
        else if(arrayListPUCDocsModel.size()==0){
                    Toast.makeText(getActivity(), "Please upload PUC documents", Toast.LENGTH_SHORT).show();

                }

        else if(!ValidationViews.EditTextNullValidate(etEmployee_GraduateCollegeName)){
                }

        else if(!ValidationViews.EditTextNullValidate(etEmployee_GraduateUniversityName)){
                }

        else if(!ValidationViews.EditTextNullValidate(etEmployee_GraduateSpecialization)){

                }
        else if(!ValidationViews.EditTextNullValidate(etEmployee_GraduateBranchName)){

                }
        else if(!ValidationViews.EditTextNullValidate(etEmployee_GraduatePercentage)){

                }
        else if(Double.parseDouble(etEmployee_GraduatePercentage.getText().toString())<35){
                    Toast.makeText(getActivity(), "Please check graduate percentage", Toast.LENGTH_SHORT).show();
                }

        else if(!ValidationViews.EditTextNullValidate(etEmployee_GraduatePassingYear) ){
                    Toast.makeText(getActivity(), "Please check Graduate passing year", Toast.LENGTH_SHORT).show();

                }

        else if(Integer.parseInt(etEmployee_GraduatePassingYear.getText().toString())<1990){
                    Toast.makeText(getActivity(), "Please check Graduate passing year", Toast.LENGTH_SHORT).show();
                }

        else if(arrayListGraduateDocsModel.size()==0){
                    Toast.makeText(getActivity(), "Please upload Graduation documents", Toast.LENGTH_SHORT).show();

                }

        else if(!etEmployee_PGPercentage.getText().toString().equals("") && Double.parseDouble(etEmployee_PGPercentage.getText().toString())<35 ){
            Toast.makeText(getActivity(), "Please check PG percentage", Toast.LENGTH_SHORT).show();
        }

        else if(!etEmployee_PGPassingYear.getText().toString().equals("") && etEmployee_PGPassingYear.getText().length()<4 ){
            Toast.makeText(getActivity(), "Please check PG passing year", Toast.LENGTH_SHORT).show();
        }

        else if(!etEmployee_BedDedPercentage.getText().toString().equals("") && Double.parseDouble(etEmployee_BedDedPercentage.getText().toString())<35 ){
            Toast.makeText(getActivity(), "Please check Bed/Ded percentage", Toast.LENGTH_SHORT).show();
        }

        else if(!etEmployee_BedDedPassingYear.getText().toString().equals("") && etEmployee_BedDedPassingYear.getText().length()<4 ){
            Toast.makeText(getActivity(), "Please check Bed/Ded passing year", Toast.LENGTH_SHORT).show();
        }
        else if(!etEmployee_PHDPercentage.getText().toString().equals("") && Double.parseDouble(etEmployee_PHDPercentage.getText().toString())<35 ){
            Toast.makeText(getActivity(), "Please check Bed/Ded percentage", Toast.LENGTH_SHORT).show();
        }


        else {

            if(count!=0){

                for(int i = 0 ; i<arrayList_AchievementTitle.size(); i++){

                    etEmployee_AchievementTitle = arrayList_AchievementTitle.get(i);
                    etEmployee_AchievementYear    = arrayList_AchievementYear.get(i);
                    etEmployee_AchievementPlace = arrayList_AchievementPlace.get(i);
                    etEmployee_AchievementDescription = arrayList_AchievementDescription.get(i);

                    if(!ValidationViews.EditTextNullValidate(etEmployee_AchievementTitle)){
                        return;
                    }
                    else if(!ValidationViews.EditTextNullValidate(etEmployee_AchievementYear) || etEmployee_AchievementYear.getText().length()<4){
                        return;
                    }

                    else if(!ValidationViews.EditTextNullValidate(etEmployee_AchievementPlace)){
                        return;
                    }

                    else if(!ValidationViews.EditTextNullValidate(etEmployee_AchievementDescription)){
                        return;
                    }

                }


            }

             if(count1!=0){

                for (int i = 0; i < arrayList_SportsName.size(); i++) {

                    et_SportsName = arrayList_SportsName.get(i);
                    et_SportsYear = arrayList_SportsYear.get(i);
                    et_sportsLevel = arrayList_sportsLevel.get(i);
                    et_SportsPosition = arrayList_SportsPosition.get(i);
                    et_SportTeamName = arrayList_SportTeamName.get(i);
                    et_SportsLocation = arrayList_SportsLocation.get(i);

                    if(!ValidationViews.EditTextNullValidate(et_SportsName)){
                        return;
                    }
                    else if(!ValidationViews.EditTextNullValidate(et_SportsYear) || et_SportsYear.getText().length()<4){
                        return;
                    }

                    else if(!ValidationViews.EditTextNullValidate(et_sportsLevel)){
                        return;
                    }

                    else if(!ValidationViews.EditTextNullValidate(et_SportsPosition)){
                        return;
                    }

                    else if(!ValidationViews.EditTextNullValidate(et_SportTeamName)){
                        return;
                    }

                    else if(!ValidationViews.EditTextNullValidate(et_SportsLocation)){
                        return;
                    }


                }

            }

            Toast.makeText(getActivity(), "Clicked", Toast.LENGTH_SHORT).show();

            str_Employee_SchoolName = etEmployee_SchoolName.getText().toString();
            str_Employee_SchoolPercentage = etEmployee_SchoolPercentage.getText().toString();
            str_Employee_SchoolPassing = etEmployee_SchoolPassing.getText().toString();
            str_Employee_SchoolBoard = etEmployee_SchoolBoard.getText().toString();
            str_Employee_SchoolState = etEmployee_SchoolState.getText().toString();
            str_Employee_PUCName = etEmployee_PUCName.getText().toString();
            str_Employee_PUCPercentage = etEmployee_PUCPercentage.getText().toString();
            str_Employee_PUCPassing = etEmployee_PUCPassing.getText().toString();
            str_Employee_PUCBoard = etEmployee_PUCBoard.getText().toString();
            str_Employee_PUCState = etEmployee_PUCState.getText().toString();
            str_Employee_GraduateCollegeName = etEmployee_GraduateCollegeName.getText().toString();
            str_Employee_GraduateUniversityName = etEmployee_GraduateUniversityName.getText().toString();
            str_Employee_GraduateSpecialization = etEmployee_GraduateSpecialization.getText().toString();
            str_Employee_GraduateBranchName = etEmployee_GraduateBranchName.getText().toString();
            str_Employee_GraduatePercentage = etEmployee_GraduatePercentage.getText().toString();
            str_Employee_GraduatePassingYear = etEmployee_GraduatePassingYear.getText().toString();
            str_Employee_PGCollegeName = etEmployee_PGCollegeName.getText().toString();
            str_Employee_PGUniversityName = etEmployee_PGUniversityName.getText().toString();
            str_Employee_PGSpecialization = etEmployee_PGSpecialization.getText().toString();
            str_Employee_PGBranchName = etEmployee_PGBranchName.getText().toString();
            str_Employee_PGPercentage = etEmployee_PGPercentage.getText().toString();
            str_Employee_PGPassingYear = etEmployee_PGPassingYear.getText().toString();
            str_Employee_PHDCollegeName = etEmployee_PHDCollegeName.getText().toString();
            str_Employee_PHDUniversityName = etEmployee_PHDUniversityName.getText().toString();
            str_Employee_PHDSpecialization = etEmployee_PHDSpecialization.getText().toString();
            str_Employee_PHDBranchName = etEmployee_PHDBranchName.getText().toString();
            str_Employee_PHDPercentage = etEmployee_PHDPercentage.getText().toString();
            str_Employee_BedDedCollegeName = etEmployee_BedDedCollegeName.getText().toString();
            str_Employee_BedDedUniversityName = etEmployee_BedDedUniversityName.getText().toString();
            str_Employee_BedDedSpecialization = etEmployee_BedDedSpecialization.getText().toString();
            str_Employee_BedDedBranchName    = etEmployee_BedDedBranchName.getText().toString();
            str_Employee_BedDedPercentage    = etEmployee_BedDedPercentage.getText().toString();
            str_Employee_BedDedPassingYear   = etEmployee_BedDedPassingYear.getText().toString();


            EmployeeAddModel employeeSchoolDetails = new EmployeeAddModel(str_Employee_SchoolName, str_Employee_SchoolPercentage,
                    str_Employee_SchoolPassing, str_Employee_SchoolBoard,
                    str_Employee_SchoolState);


            EmployeeAddModel employeePUCDetails = new EmployeeAddModel(str_Employee_FirstName, str_Employee_PUCName,
                    str_Employee_PUCPercentage, str_Employee_PUCPassing,
                    str_Employee_PUCBoard, str_Employee_PUCState);


            EmployeeAddModel employeeGraduationDetails = new EmployeeAddModel(str_Employee_FirstName, str_Employee_GraduateCollegeName, str_Employee_GraduateUniversityName,
                    str_Employee_GraduateSpecialization, str_Employee_GraduateBranchName,
                    str_Employee_GraduatePercentage, str_Employee_GraduatePassingYear);

            EmployeeAddModel employeePostGraduationDetails = new EmployeeAddModel(str_Employee_FirstName, str_Employee_LastName, str_Employee_PGCollegeName,
                    str_Employee_PGUniversityName, str_Employee_PGSpecialization,
                    str_Employee_PGBranchName, str_Employee_PGPercentage, str_Employee_PGPassingYear);


            EmployeeAddModel employeeBedDetails = new EmployeeAddModel(1,1, str_Employee_BedDedCollegeName, str_Employee_BedDedUniversityName,
                    str_Employee_BedDedSpecialization, str_Employee_BedDedBranchName,
                    str_Employee_BedDedPercentage,str_Employee_BedDedPassingYear);

            EmployeeAddModel employeePHDDetails = new EmployeeAddModel(1, str_Employee_PHDCollegeName, str_Employee_PHDUniversityName,
                    str_Employee_PHDSpecialization, str_Employee_PHDBranchName,
                    str_Employee_PHDPercentage);


            EmployeeAddModel employeeAddModel = new EmployeeAddModel(1,1,employeeSchoolDetails, employeePUCDetails,
                    employeeGraduationDetails, employeePostGraduationDetails, employeePHDDetails,employeeBedDetails);


            mRef.child(Constant.STAFF_TYPE).child(Constant.STAFF_FINAL_ID)
                    .child("Educational Details").setValue(employeeAddModel);


            DatabaseReference db_parent = mRef.child(Constant.STAFF_TYPE).child(Constant.STAFF_FINAL_ID)
                    .child("Educational Details");


            if (count != 0) {

                for (int i = 0; i < arrayList_AchievementTitle.size(); i++) {

                    String achievement = arrayList_AchievementTitle.get(i).getText().toString();
                    String achievementYear = arrayList_AchievementYear.get(i).getText().toString();
                    String achievementPlace = arrayList_AchievementPlace.get(i).getText().toString();
                    String achievementDescr = arrayList_AchievementDescription.get(i).getText().toString();


                    DatabaseReference db_child = db_parent.child("employee_achievements_details").child("achievements" + (i + 1));
                    db_child.child("str_Achievement_title").setValue(achievement);
                    db_child.child("str_Achievement_year").setValue(achievementYear);
                    db_child.child("str_Achievement_place").setValue(achievementPlace);
                    db_child.child("str_Achievement_description").setValue(achievementDescr);


                }




            }

            if(count1!=0){

                for (int i = 0; i < arrayList_SportsName.size(); i++) {

                    str_EmployeeSportsName = arrayList_SportsName.get(i).getText().toString();
                    str_EmployeeSportsYear = arrayList_SportsYear.get(i).getText().toString();
                    str_EmployeeSportsLevel = arrayList_sportsLevel.get(i).getText().toString();
                    str_EmployeeSportsPosition = arrayList_SportsPosition.get(i).getText().toString();
                    str_EmployeeSportsTeamName = arrayList_SportTeamName.get(i).getText().toString();
                    str_EmployeeSportsLocation = arrayList_SportsLocation.get(i).getText().toString();


                    DatabaseReference db_child = db_parent.child("employee_sports_details").child("sports" + (i + 1));
                    db_child.child("str_SportsName").setValue(str_EmployeeSportsName);
                    db_child.child("str_SportsYear").setValue(str_EmployeeSportsYear);
                    db_child.child("str_SportsLevel").setValue(str_EmployeeSportsLevel);
                    db_child.child("str_SportsPosition").setValue(str_EmployeeSportsPosition);
                    db_child.child("str_SportsTeamName").setValue(str_EmployeeSportsTeamName);
                    db_child.child("str_SportsLocation").setValue(str_EmployeeSportsLocation);


                }


            }




            StorageReference storageReference = mStoargeref_Staff_Details_Images.child(Constant.STAFF_TYPE).child(Constant.STAFF_FINAL_ID);


            if(arrayListSchoolDocs.size()!=0){

                imageCounter=0;

                for(int i = 0 ; i<arrayListSchoolDocs.size(); i++){


                    storageReference.child("Educational Details").child("employee_school_deatils").child("str_School_Docs"+i).putFile(arrayListSchoolDocs.get(i)).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            String url = taskSnapshot.getDownloadUrl().toString();
                            mRef.child(Constant.STAFF_TYPE).child(Constant.STAFF_FINAL_ID)
                                    .child("Educational Details").child("employee_school_deatils")
                                    .child("Document"+imageCounter).setValue(url);
                            imageCounter++;

                        }

                    });
                }
            }

            if(arrayListPUCDocs.size()!=0){

                imageCounter=0;

                for(int i = 0 ; i<arrayListPUCDocs.size(); i++){


                    storageReference.child("Educational Details").child("employee_puc_details").child("str_PUC_Docs"+i).putFile(arrayListPUCDocs.get(i)).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            String url = taskSnapshot.getDownloadUrl().toString();
                            mRef.child(Constant.STAFF_TYPE).child(Constant.STAFF_FINAL_ID)
                                    .child("Educational Details").child("employee_puc_details")
                                    .child("Document"+imageCounter).setValue(url);
                            imageCounter++;

                        }

                    });
                }
            }


            if(arrayListGraduationDocs.size()!=0){

                imageCounter=0;

                for(int i = 0 ; i<arrayListGraduationDocs.size(); i++){


                    storageReference.child("Educational Details").child("employee_graduation_details").child("str_Graduate_Docs"+i).putFile(arrayListGraduationDocs.get(i)).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            String url = taskSnapshot.getDownloadUrl().toString();
                            mRef.child(Constant.STAFF_TYPE).child(Constant.STAFF_FINAL_ID)
                                    .child("Educational Details").child("employee_graduation_details")
                                    .child("Document"+imageCounter).setValue(url);
                            imageCounter++;

                        }

                    });
                }
            }



            if(arrayListPGDocs.size()!=0){

                imageCounter=0;

                for(int i = 0 ; i<arrayListPGDocs.size(); i++){

                    storageReference.child("Educational Details").child("employee_postgraduation_details").child("str_Pg_Docs"+i).putFile(arrayListPGDocs.get(i)).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            String url = taskSnapshot.getDownloadUrl().toString();
                            mRef.child(Constant.STAFF_TYPE).child(Constant.STAFF_FINAL_ID)
                                    .child("Educational Details").child("employee_postgraduation_details")
                                    .child("Document"+imageCounter).setValue(url);
                            imageCounter++;

                        }

                    });
                }
            }

            if(arrayListBedDedDocs.size()!=0){

                imageCounter=0;

                for(int i = 0 ; i<arrayListBedDedDocs.size(); i++){

                    storageReference.child("Educational Details").child("employee_bed_details").child("str_BedDocs"+i).putFile(arrayListBedDedDocs.get(i)).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            String url = taskSnapshot.getDownloadUrl().toString();
                            mRef.child(Constant.STAFF_TYPE).child(Constant.STAFF_FINAL_ID)
                                    .child("Educational Details").child("employee_bed_details")
                                    .child("Document"+imageCounter).setValue(url);
                            imageCounter++;

                        }

                    });
                }
            }

            if(arrayListPHDDocs.size()!=0){

                imageCounter=0;

                for(int i = 0 ; i<arrayListPHDDocs.size(); i++){

                    storageReference.child("Educational Details").child("employee_phd_details").child("str_PHD_Docs"+i).putFile(arrayListPHDDocs.get(i)).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            String url = taskSnapshot.getDownloadUrl().toString();
                            mRef.child(Constant.STAFF_TYPE).child(Constant.STAFF_FINAL_ID)
                                    .child("Educational Details").child("employee_phd_details")
                                    .child("Document"+imageCounter).setValue(url);
                            imageCounter++;

                        }

                    });
                }
            }



            EventBus.getDefault().post(new EmployeeAddModel(1, registartionNumber, name, dept,Constant.STAFF_PROFILE_IMAGE));
            AddEmployeeLanding newAdmissionActivity = (AddEmployeeLanding) getActivity();
            WorkInfoFragment workInfoFragment = new WorkInfoFragment();
            assert newAdmissionActivity != null;
            newAdmissionActivity.loadNextFragment(BUTTON_ENABLE, workInfoFragment, null);




        }


    }

    private void displayNumberOfSports(int count1) {
        numberOfSportsCounter.setText(""+count1);

    }

    private void SportsLayoutVisibility() {

        sportsDetails_TextView.setTextColor(getResources().getColor(R.color.whitecolor));
        sportsDetails_TextView.setBackgroundColor(getResources().getColor(R.color.colorOrange));
        sportsInfoImageViewDown.setColorFilter(getResources().getColor(R.color.whitecolor));
        linearLayout_Sports_DetailsViews.setVisibility(View.GONE);
        sportsInfoImageViewDown.setRotation(sportsInfoImageViewDown.getRotation() + 180);
    }


    private void achiemenetLayoutVisibility() {
        achievementInfoTextView.setTextColor(getResources().getColor(R.color.whitecolor));
        achievementInfoTextView.setBackgroundColor(getResources().getColor(R.color.colorOrange));
        achievemetInfoImageViewDown.setColorFilter(getResources().getColor(R.color.whitecolor));
        linearLayoutAchievementDetailsViews.setVisibility(View.GONE);
        achievemetInfoImageViewDown.setRotation(achievemetInfoImageViewDown.getRotation() + 180);

    }

    private void BedDedLayoutVisibility() {
        BedDedInfoTextView.setTextColor(getResources().getColor(R.color.whitecolor));
        BedDedInfoTextView.setBackgroundColor(getResources().getColor(R.color.colorOrange));
        BedDedInfoImageViewDown.setColorFilter(getResources().getColor(R.color.whitecolor));
        linearLayoutBedDedDetailsViews.setVisibility(View.GONE);
        BedDedInfoImageViewDown.setRotation(achievemetInfoImageViewDown.getRotation() + 180);

    }

    private void phdLayoutVisibility() {

        phdInfoTextView.setTextColor(getResources().getColor(R.color.whitecolor));
        phdInfoTextView.setBackgroundColor(getResources().getColor(R.color.colorOrange));
        phdInfoImageViewDown.setColorFilter(getResources().getColor(R.color.whitecolor));
        linearLayoutPHDDetailsViews.setVisibility(View.GONE);
        phdInfoImageViewDown.setRotation(phdInfoImageViewDown.getRotation() + 180);



    }

    private void pgLayoutVisibility() {

        pgInfoTextView.setTextColor(getResources().getColor(R.color.whitecolor));
        pgInfoTextView.setBackgroundColor(getResources().getColor(R.color.colorOrange));
        pgInfoImageViewDown.setColorFilter(getResources().getColor(R.color.whitecolor));
        linearLayoutPGDetailsViews.setVisibility(View.GONE);
        pgInfoImageViewDown.setRotation(pgInfoImageViewDown.getRotation() + 180);



    }

    private void ugLayoutVisibility() {
        ugInfoTextView.setTextColor(getResources().getColor(R.color.whitecolor));
        ugInfoTextView.setBackgroundColor(getResources().getColor(R.color.colorOrange));
        ugImageViewDown.setColorFilter(getResources().getColor(R.color.whitecolor));
        linearLayoutUGDetailsViews.setVisibility(View.GONE);
        ugImageViewDown.setRotation(ugImageViewDown.getRotation() + 180);



    }

    private void schoolLayoutVisibility() {

        schoolInfoTextView.setTextColor(getResources().getColor(R.color.whitecolor));
        schoolInfoTextView.setBackgroundColor(getResources().getColor(R.color.colorOrange));
        schoolInfoImageViewDown.setColorFilter(getResources().getColor(R.color.whitecolor));
        linearLayoutSchoolViews.setVisibility(View.GONE);
        schoolInfoImageViewDown.setRotation(schoolInfoImageViewDown.getRotation() + 180);



    }

    private void pucLayoutVisibility() {
        pucInfoTextView.setTextColor(getResources().getColor(R.color.whitecolor));
        pucInfoTextView.setBackgroundColor(getResources().getColor(R.color.colorOrange));
        pucInfoImageViewDown.setColorFilter(getResources().getColor(R.color.whitecolor));
        linearLayoutPUCDetailsViews.setVisibility(View.GONE);
        pucInfoImageViewDown.setRotation(pucInfoImageViewDown.getRotation() + 180);




    }

    private void openCalendarDialog(final String passingYear) {

        final Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.datepicker);
        dialog.setTitle("");
        DatePicker datePicker = dialog.findViewById(R.id.datePickerDialog);
        datePicker.setMinDate(1/ 1 /1998);
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

                    switch (passingYear){

                        case "1":
                            etEmployee_SchoolPassing.setText(date);
                            break;

                        case "2":
                            etEmployee_PUCPassing.setText(date);
                            break;


                    }


                    dialog.dismiss();
                }else {

                    if(!selectedDate.equals(stringNewDate)){
                        String date = String.valueOf(stringNewDate) + "/" + String.valueOf(newMonth)
                                + "/" + String.valueOf(stringyear);

                        switch (passingYear){
                            case "1":
                                etEmployee_SchoolPassing.setText(date);
                                break;

                            case "2":
                                etEmployee_PUCPassing.setText(date);
                                break;


                        }


                        dialog.dismiss();
                    }else {
                        if(!selectedMonth.equals(newMonth)){
                            String date = String.valueOf(stringNewDate) + "/" + String.valueOf(newMonth)
                                    + "/" + String.valueOf(stringyear);

                            switch (passingYear){

                                case "1":
                                    etEmployee_SchoolPassing.setText(date);
                                    break;

                                case "2":
                                    etEmployee_PUCPassing.setText(date);
                                    break;

                            }

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
        datePicker.setMaxDate(calendar.getTimeInMillis());



    }


    private void sendData() {



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

    private void displayNumberOfGuardian(int count) {

        tvNumberCount.setText(""+count);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constant.FROM_CAMERA && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            String path = MediaStore.Images.Media.insertImage(getActivity().getContentResolver(), photo, "Title", null);
            filePath = Uri.parse(path);
            ImageAdpater imageAdapter;

            switch (imageCase) {

                case "A":
                    arrayListSchoolDocs.add(filePath);
                    setImageToRecyclerView();
                    imageAdapter = new ImageAdpater(arrayListSchoolDocsModel, getActivity(), requestCode);
                    rvAttach_SchoolDocs.setAdapter(imageAdapter);
                    imageAdapter.notifyDataSetChanged();
                    break;

                case "B":
                    arrayListPUCDocs.add(filePath);
                    setImageToRecyclerView();
                    imageAdapter = new ImageAdpater(arrayListPUCDocsModel, getActivity(), requestCode);
                    rvAttach_PUCDocs.setAdapter(imageAdapter);
                    imageAdapter.notifyDataSetChanged();
                    break;

                case "C":
                    arrayListGraduationDocs.add(filePath);
                    setImageToRecyclerView();
                    imageAdapter = new ImageAdpater(arrayListGraduateDocsModel, getActivity(), requestCode);
                    rvAttach_GraduateDocs.setAdapter(imageAdapter);
                    imageAdapter.notifyDataSetChanged();
                    break;

                case "D":
                    arrayListPGDocs.add(filePath);
                    setImageToRecyclerView();
                    imageAdapter = new ImageAdpater(arrayListPGDocsModel, getActivity(), requestCode);
                    rvAttach_PGDocs.setAdapter(imageAdapter);
                    imageAdapter.notifyDataSetChanged();

                    break;

                case "E":
                    arrayListPHDDocs.add(filePath);
                    setImageToRecyclerView();
                    imageAdapter = new ImageAdpater(arrayListPHDDocsModel, getActivity(), requestCode);
                    rvAttach_PHDDocs.setAdapter(imageAdapter);
                    imageAdapter.notifyDataSetChanged();
                    break;

                case "F":
                    arrayListBedDedDocs.add(filePath);
                    setImageToRecyclerView();
                    imageAdapter = new ImageAdpater(arrayListBedDedDocsModel, getActivity(), requestCode);
                    rvAttach_BedDedDocs.setAdapter(imageAdapter);
                    imageAdapter.notifyDataSetChanged();
                    break;


            }


        }

        if (requestCode == Constant.FROM_GALLERY && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                Uri contentURI = data.getData();

                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), contentURI);
                    int bitmapByteCount = BitmapCompat.getAllocationByteCount(bitmap);

                    int nh = (int) (bitmap.getHeight() * (256.0 / bitmap.getWidth()));
                    Bitmap scaled = Bitmap.createScaledBitmap(bitmap, 256, nh, true);

                    int bitmapByteCount1 = BitmapCompat.getAllocationByteCount(scaled);

                    Log.d("djdj", "" + bitmapByteCount + " " + bitmapByteCount1);

                    String path = MediaStore.Images.Media.insertImage(getActivity().getContentResolver(), scaled, "Title", null);
                    filePath = Uri.parse(path);
                    ImageAdpater imageAdapter;

                    switch (imageCase) {

                        case "A":
                            arrayListSchoolDocs.add(filePath);
                            setImageToRecyclerView();
                            imageAdapter = new ImageAdpater(arrayListSchoolDocsModel, getActivity(), requestCode);
                            rvAttach_SchoolDocs.setAdapter(imageAdapter);
                            imageAdapter.notifyDataSetChanged();
                            break;

                        case "B":
                            arrayListPUCDocs.add(filePath);
                            setImageToRecyclerView();
                            imageAdapter = new ImageAdpater(arrayListPUCDocsModel, getActivity(), requestCode);
                            rvAttach_PUCDocs.setAdapter(imageAdapter);
                            imageAdapter.notifyDataSetChanged();
                            break;

                        case "C":
                            arrayListGraduationDocs.add(filePath);
                            setImageToRecyclerView();
                            imageAdapter = new ImageAdpater(arrayListGraduateDocsModel, getActivity(), requestCode);
                            rvAttach_GraduateDocs.setAdapter(imageAdapter);
                            imageAdapter.notifyDataSetChanged();
                            break;

                        case "D":
                            arrayListPGDocs.add(filePath);
                            setImageToRecyclerView();
                            imageAdapter = new ImageAdpater(arrayListPGDocsModel, getActivity(), requestCode);
                            rvAttach_PGDocs.setAdapter(imageAdapter);
                            imageAdapter.notifyDataSetChanged();

                            break;

                        case "E":
                            arrayListPHDDocs.add(filePath);
                            setImageToRecyclerView();
                            imageAdapter = new ImageAdpater(arrayListPHDDocsModel, getActivity(), requestCode);
                            rvAttach_PHDDocs.setAdapter(imageAdapter);
                            imageAdapter.notifyDataSetChanged();
                            break;


                        case "F":
                            arrayListBedDedDocs.add(filePath);
                            setImageToRecyclerView();
                            imageAdapter = new ImageAdpater(arrayListBedDedDocsModel, getActivity(), requestCode);
                            rvAttach_BedDedDocs.setAdapter(imageAdapter);
                            imageAdapter.notifyDataSetChanged();
                            break;

                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
                /*filePath = data.getData();

                InputStream inputStream;
                try {
                    inputStream = getContext().getContentResolver().openInputStream(filePath);
                    bitmap = BitmapFactory.decodeStream(inputStream);
                    String path = MediaStore.Images.Media.insertImage(getActivity().getContentResolver(), bitmap, "Title", null);

                    filePath = Uri.parse(path);
                    ImageAdpater imageAdapter;


                    switch (imageCase) {

                        case "A":
                            arrayListSchoolDocs.add(filePath);
                            setImageToRecyclerView();
                            imageAdapter = new ImageAdpater(arrayListSchoolDocsModel, getActivity(), requestCode);
                            rvAttach_SchoolDocs.setAdapter(imageAdapter);
                            imageAdapter.notifyDataSetChanged();
                            break;

                        case "B":
                            arrayListPUCDocs.add(filePath);
                            setImageToRecyclerView();
                            imageAdapter = new ImageAdpater(arrayListPUCDocsModel, getActivity(), requestCode);
                            rvAttach_PUCDocs.setAdapter(imageAdapter);
                            imageAdapter.notifyDataSetChanged();
                            break;

                        case "C":
                            arrayListGraduationDocs.add(filePath);
                            setImageToRecyclerView();
                            imageAdapter = new ImageAdpater(arrayListGraduateDocsModel, getActivity(), requestCode);
                            rvAttach_GraduateDocs.setAdapter(imageAdapter);
                            imageAdapter.notifyDataSetChanged();
                            break;

                        case "D":
                            arrayListPGDocs.add(filePath);
                            setImageToRecyclerView();
                            imageAdapter = new ImageAdpater(arrayListPGDocsModel, getActivity(), requestCode);
                            rvAttach_PGDocs.setAdapter(imageAdapter);
                            imageAdapter.notifyDataSetChanged();

                            break;

                        case "E":
                            arrayListPHDDocs.add(filePath);
                            setImageToRecyclerView();
                            imageAdapter = new ImageAdpater(arrayListPHDDocsModel, getActivity(), requestCode);
                            rvAttach_PHDDocs.setAdapter(imageAdapter);
                            imageAdapter.notifyDataSetChanged();
                            break;


                        case "F":
                            arrayListBedDedDocs.add(filePath);
                            setImageToRecyclerView();
                            imageAdapter = new ImageAdpater(arrayListBedDedDocsModel, getActivity(), requestCode);
                            rvAttach_BedDedDocs.setAdapter(imageAdapter);
                            imageAdapter.notifyDataSetChanged();
                            break;

                    }


                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }*/
            }


        }
    }

    private void setImageToRecyclerView() {

        ImageModel imageModel = new ImageModel(filePath);

        switch (imageCase) {

            case "A":
                arrayListSchoolDocsModel.add(imageModel);
                break;

            case "B":
                arrayListPUCDocsModel.add(imageModel);
                break;

            case "C":
                arrayListGraduateDocsModel.add(imageModel);
                break;


            case "D":
                arrayListPGDocsModel.add(imageModel);
                break;

            case "E":
                arrayListPHDDocsModel.add(imageModel);
                break;

            case "F":
                arrayListBedDedDocsModel.add(imageModel);
                break;


           }


        }

        /*------------------------------------------------------*/

    /*@Override
    public void staffStatus(Context mContext,String status) {
        statusStaff = status;
        Toast.makeText(mContext, "StaffStaus"+status, Toast.LENGTH_SHORT).show();


    }*/

    private class GenericTextWatcher implements TextWatcher {

        View view;
        public GenericTextWatcher(View view) {
            this.view = view;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            switch (view.getId()){

                case R.id.etEmployeeSchoolPercentage:
                    try {
                        Log.d("Percentage", "input: " + editable);

                        if(editable.toString().startsWith(".")){
                            editable.replace(0, editable.length(), "");

                        }
                        else if(editable.toString().startsWith("0")){
                            editable.replace(0, editable.length(), "");

                        }
                        else if(Double.parseDouble(editable.toString())<min){
                            etEmployee_SchoolPercentage.setError("minimum 35% required");

                        }


                        else if(Double.parseDouble(editable.toString())>100){
                            editable.replace(0, editable.length(), "100");

                        }

                    }
                    catch(NumberFormatException nfe){
                        nfe.printStackTrace();
                    }

                    break;

                case R.id.etEmployeePUCPercentage:
                    try {
                        Log.d("Percentage", "input: " + editable);

                        if(editable.toString().startsWith(".")){
                            editable.replace(0, editable.length(), "");

                        }
                        else if(editable.toString().startsWith("0")){
                            editable.replace(0, editable.length(), "");

                        }
                        else if(Double.parseDouble(editable.toString())<min){
                            etEmployee_PUCPercentage.setError("minimum 35% required");

                        }


                        else if(Double.parseDouble(editable.toString())>100){
                            editable.replace(0, editable.length(), "100");

                        }

                    }
                    catch(NumberFormatException nfe){
                        nfe.printStackTrace();
                    }

                    break;



                case R.id.etEmployeeGraduatePercentage:
                    try {
                        Log.d("Percentage", "input: " + editable);

                        if(editable.toString().startsWith(".")){
                            editable.replace(0, editable.length(), "");

                        }
                        else if(editable.toString().startsWith("0")){
                            editable.replace(0, editable.length(), "");

                        }
                        else if(Double.parseDouble(editable.toString())<min){
                            etEmployee_GraduatePercentage.setError("minimum 35% required");

                        }


                        else if(Double.parseDouble(editable.toString())>100){
                            editable.replace(0, editable.length(), "100");

                        }

                    }
                    catch(NumberFormatException nfe){
                        nfe.printStackTrace();
                    }

                    break;


                case R.id.etEmployeePGPercentage:
                    try {
                        Log.d("Percentage", "input: " + editable);

                        if(editable.toString().startsWith(".")){
                            editable.replace(0, editable.length(), "");

                        }
                        else if(editable.toString().startsWith("0")){
                            editable.replace(0, editable.length(), "");

                        }
                        else if(Double.parseDouble(editable.toString())<min){
                            etEmployee_PGPercentage.setError("minimum 35% required");

                        }


                        else if(Double.parseDouble(editable.toString())>100){
                            editable.replace(0, editable.length(), "100");

                        }

                    }
                    catch(NumberFormatException nfe){
                        nfe.printStackTrace();
                    }

                    break;


                case R.id.etEmployeePHDPercentage:
                    try {
                        Log.d("Percentage", "input: " + editable);

                        if(editable.toString().startsWith(".")){
                            editable.replace(0, editable.length(), "");

                        }
                        else if(editable.toString().startsWith("0")){
                            editable.replace(0, editable.length(), "");

                        }
                        else if(Double.parseDouble(editable.toString())<min){
                            etEmployee_PHDPercentage.setError("minimum 35% required");

                        }


                        else if(Double.parseDouble(editable.toString())>100){
                            editable.replace(0, editable.length(), "100");

                        }

                    }
                    catch(NumberFormatException nfe){
                        nfe.printStackTrace();
                    }

                    break;

                case R.id.etEmployeeBedDedPercentage:
                    try {
                        Log.d("Percentage", "input: " + editable);

                        if(editable.toString().startsWith(".")){
                            editable.replace(0, editable.length(), "");

                        }
                        else if(editable.toString().startsWith("0")){
                            editable.replace(0, editable.length(), "");

                        }
                        else if(Double.parseDouble(editable.toString())<min){
                            etEmployee_BedDedPercentage.setError("minimum 35% required");

                        }


                        else if(Double.parseDouble(editable.toString())>100){
                            editable.replace(0, editable.length(), "100");

                        }

                    }
                    catch(NumberFormatException nfe){
                        nfe.printStackTrace();
                    }

                    break;


                case R.id.etEmployeeSchoolPassing:
                    try {

                        if(editable.toString().startsWith(".")){
                            editable.replace(0, editable.length(), "");

                        }
                        else if(editable.toString().startsWith("0")){
                            editable.replace(0, editable.length(), "");

                        }
                        else if(Double.parseDouble(editable.toString())<minYear){
                            etEmployee_SchoolPassing.setError("minimum 1990 required");

                        }


                        else if(Double.parseDouble(editable.toString())>2018){
                            editable.replace(0, editable.length(), "2018");

                        }

                    }
                    catch(NumberFormatException nfe){
                        nfe.printStackTrace();
                    }

                    break;



                case R.id.etEmployeePUCPassing:
                    try {

                        if(editable.toString().startsWith(".")){
                            editable.replace(0, editable.length(), "");

                        }
                        else if(editable.toString().startsWith("0")){
                            editable.replace(0, editable.length(), "");

                        }
                        else if(Double.parseDouble(editable.toString())<minYear){
                            etEmployee_PUCPassing.setError("minimum 1990 required");

                        }


                        else if(Double.parseDouble(editable.toString())>2018){
                            editable.replace(0, editable.length(), "2018");

                        }

                    }
                    catch(NumberFormatException nfe){
                        nfe.printStackTrace();
                    }

                    break;



                case R.id.etEmployeeGraduatePassingYear:
                    try {

                        if(editable.toString().startsWith(".")){
                            editable.replace(0, editable.length(), "");

                        }
                        else if(editable.toString().startsWith("0")){
                            editable.replace(0, editable.length(), "");

                        }
                        else if(Double.parseDouble(editable.toString())<minYear){
                            etEmployee_GraduatePassingYear.setError("minimum 1990 required");

                        }


                        else if(Double.parseDouble(editable.toString())>2018){
                            editable.replace(0, editable.length(), "2018");

                        }

                    }
                    catch(NumberFormatException nfe){
                        nfe.printStackTrace();
                    }

                    break;


                case R.id.etEmployeePGPassingYear:
                    try {

                        if(editable.toString().startsWith(".")){
                            editable.replace(0, editable.length(), "");

                        }
                        else if(editable.toString().startsWith("0")){
                            editable.replace(0, editable.length(), "");

                        }
                        else if(Double.parseDouble(editable.toString())<minYear){
                            etEmployee_PGPassingYear.setError("minimum 1990 required");

                        }


                        else if(Double.parseDouble(editable.toString())>2018){
                            editable.replace(0, editable.length(), "2018");

                        }

                    }
                    catch(NumberFormatException nfe){
                        nfe.printStackTrace();
                    }

                    break;

                case R.id.etEmployeeBedDedPassingYear:
                    try {

                        if(editable.toString().startsWith(".")){
                            editable.replace(0, editable.length(), "");

                        }
                        else if(editable.toString().startsWith("0")){
                            editable.replace(0, editable.length(), "");

                        }
                        else if(Double.parseDouble(editable.toString())<minYear){
                            etEmployee_BedDedPassingYear.setError("minimum 1990 required");

                        }


                        else if(Double.parseDouble(editable.toString())>2018){
                            editable.replace(0, editable.length(), "2018");

                        }

                    }
                    catch(NumberFormatException nfe){
                        nfe.printStackTrace();
                    }

                    break;


                case 101:
                    try {

                        if(editable.toString().startsWith(".")){
                            editable.replace(0, editable.length(), "");

                        }
                        else if(editable.toString().startsWith("0")){
                            editable.replace(0, editable.length(), "");

                        }
                        else if(Double.parseDouble(editable.toString())<minYear){
                            arrayList_SportsYear.get(0).setError("minimum 1990 required");

                        }


                        else if(Double.parseDouble(editable.toString())>2018){
                            editable.replace(0, editable.length(), "2018");

                        }

                    }
                    catch(NumberFormatException nfe){
                        nfe.printStackTrace();
                    }
                    break;


                case 102:
                    try {

                        if(editable.toString().startsWith(".")){
                            editable.replace(0, editable.length(), "");

                        }
                        else if(editable.toString().startsWith("0")){
                            editable.replace(0, editable.length(), "");

                        }
                        else if(Double.parseDouble(editable.toString())<minYear){
                            arrayList_SportsYear.get(1).setError("minimum 1990 required");

                        }


                        else if(Double.parseDouble(editable.toString())>2018){
                            editable.replace(0, editable.length(), "2018");

                        }

                    }
                    catch(NumberFormatException nfe){
                        nfe.printStackTrace();
                    }
                    break;

                case 103:
                    try {

                        if(editable.toString().startsWith(".")){
                            editable.replace(0, editable.length(), "");

                        }
                        else if(editable.toString().startsWith("0")){
                            editable.replace(0, editable.length(), "");

                        }
                        else if(Double.parseDouble(editable.toString())<minYear){
                            arrayList_SportsYear.get(2).setError("minimum 1990 required");

                        }


                        else if(Double.parseDouble(editable.toString())>2018){
                            editable.replace(0, editable.length(), "2018");

                        }

                    }
                    catch(NumberFormatException nfe){
                        nfe.printStackTrace();
                    }

                    break;

                case 104:

                    try {

                        if(editable.toString().startsWith(".")){
                            editable.replace(0, editable.length(), "");

                        }
                        else if(editable.toString().startsWith("0")){
                            editable.replace(0, editable.length(), "");

                        }
                        else if(Double.parseDouble(editable.toString())<minYear){
                            arrayList_SportsYear.get(3).setError("minimum 1990 required");

                        }


                        else if(Double.parseDouble(editable.toString())>2018){
                            editable.replace(0, editable.length(), "2018");

                        }

                    }
                    catch(NumberFormatException nfe){
                        nfe.printStackTrace();
                    }
                    break;

                case 105:

                    try {

                        if(editable.toString().startsWith(".")){
                            editable.replace(0, editable.length(), "");

                        }
                        else if(editable.toString().startsWith("0")){
                            editable.replace(0, editable.length(), "");

                        }
                        else if(Double.parseDouble(editable.toString())<minYear){
                            arrayList_SportsYear.get(4).setError("minimum 1990 required");

                        }


                        else if(Double.parseDouble(editable.toString())>2018){
                            editable.replace(0, editable.length(), "2018");

                        }

                    }
                    catch(NumberFormatException nfe){
                        nfe.printStackTrace();
                    }
                    break;



                case 1001:
                    try {

                        if(editable.toString().startsWith(".")){
                            editable.replace(0, editable.length(), "");

                        }
                        else if(editable.toString().startsWith("0")){
                            editable.replace(0, editable.length(), "");

                        }
                        else if(Double.parseDouble(editable.toString())<minYear){
                            arrayList_AchievementYear.get(0).setError("minimum 1990 required");

                        }


                        else if(Double.parseDouble(editable.toString())>2018){
                            editable.replace(0, editable.length(), "2018");

                        }

                    }
                    catch(NumberFormatException nfe){
                        nfe.printStackTrace();
                    }
                    break;


                case 1002:
                    try {

                        if(editable.toString().startsWith(".")){
                            editable.replace(0, editable.length(), "");

                        }
                        else if(editable.toString().startsWith("0")){
                            editable.replace(0, editable.length(), "");

                        }
                        else if(Double.parseDouble(editable.toString())<minYear){
                            arrayList_AchievementYear.get(1).setError("minimum 1990 required");

                        }


                        else if(Double.parseDouble(editable.toString())>2018){
                            editable.replace(0, editable.length(), "2018");

                        }

                    }
                    catch(NumberFormatException nfe){
                        nfe.printStackTrace();
                    }
                    break;

                case 10003:
                    try {

                        if(editable.toString().startsWith(".")){
                            editable.replace(0, editable.length(), "");

                        }
                        else if(editable.toString().startsWith("0")){
                            editable.replace(0, editable.length(), "");

                        }
                        else if(Double.parseDouble(editable.toString())<minYear){
                            arrayList_AchievementYear.get(2).setError("minimum 1990 required");

                        }


                        else if(Double.parseDouble(editable.toString())>2018){
                            editable.replace(0, editable.length(), "2018");

                        }

                    }
                    catch(NumberFormatException nfe){
                        nfe.printStackTrace();
                    }

                    break;

                case 1004:

                    try {

                        if(editable.toString().startsWith(".")){
                            editable.replace(0, editable.length(), "");

                        }
                        else if(editable.toString().startsWith("0")){
                            editable.replace(0, editable.length(), "");

                        }
                        else if(Double.parseDouble(editable.toString())<minYear){
                            arrayList_AchievementYear.get(3).setError("minimum 1990 required");

                        }


                        else if(Double.parseDouble(editable.toString())>2018){
                            editable.replace(0, editable.length(), "2018");

                        }

                    }
                    catch(NumberFormatException nfe){
                        nfe.printStackTrace();
                    }
                    break;

                case 1005:

                    try {

                        if(editable.toString().startsWith(".")){
                            editable.replace(0, editable.length(), "");

                        }
                        else if(editable.toString().startsWith("0")){
                            editable.replace(0, editable.length(), "");

                        }
                        else if(Double.parseDouble(editable.toString())<minYear){
                            arrayList_AchievementYear.get(4).setError("minimum 1990 required");

                        }


                        else if(Double.parseDouble(editable.toString())>2018){
                            editable.replace(0, editable.length(), "2018");

                        }

                    }
                    catch(NumberFormatException nfe){
                        nfe.printStackTrace();
                    }
                    break;



                /*case R.id.etEmployeeAchievementYear:
                    try {

                        if(editable.toString().startsWith(".")){
                            editable.replace(0, editable.length(), "");

                        }
                        else if(editable.toString().startsWith("0")){
                            editable.replace(0, editable.length(), "");

                        }
                        else if(Double.parseDouble(editable.toString())<minYear){
                            etEmployee_AchievementYear.setError("minimum 1990 required");

                        }


                        else if(Double.parseDouble(editable.toString())>2018){
                            editable.replace(0, editable.length(), "2018");

                        }

                    }
                    catch(NumberFormatException nfe){
                        nfe.printStackTrace();
                    }

                    break;*/
            }

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

   /* @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            Log.d("dhsdsdds", "he;;o");
            getFragmentManager().beginTransaction().detach(this).attach(this).commit();

        }
    }*/


    @Subscribe
    public void onEvent(EmployeeAddModel event) {

        registartionNumber= event.getEmpployee_registration_number();
        name = event.getStr_Employee_FirstName();
        dept = event.getStr_Employee_Department();
        emp_dept.setText(dept);
        emp_name.setText(name);
        emp_registration_id.setText(registartionNumber);
        Glide.with(getActivity()).load(Constant.STAFF_PROFILE_IMAGE).into(staffImage);
        bus.removeAllStickyEvents();
        //text_View.setText(event.getStr_Employee_FirstName());


    }

}
