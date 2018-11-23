package com.example.varadhi.addstaff.EmployeeAdd;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.varadhi.addstaff.R;

import java.util.ArrayList;

public class AddEmployeeLanding extends AppCompatActivity {

    Fragment fragment = null;
    Menu menuNav;
    Menu menu;
    private ViewPager viewPager;
    BottomNavigationView employeeeBottomNavigation;

    MenuItem prevMenuItem;
    private Integer guardinas;
    private ArrayList<Fragment> mFragments;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee_landing);
        viewPager = findViewById(R.id.container);
        employeeeBottomNavigation = findViewById(R.id.navigation);
        mFragments = new ArrayList<Fragment>();
        mFragments.add(new PersonalInfoFragment());
        mFragments.add(new EducationInfoFragment());
        mFragments.add(new WorkInfoFragment());
        viewPager.setOffscreenPageLimit(2);
        menu  = employeeeBottomNavigation.getMenu();
        setupViewPager(viewPager);
        menuNav=employeeeBottomNavigation.getMenu();


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                employeeeBottomNavigation.getMenu().getItem(position).setChecked(true);
                prevMenuItem = employeeeBottomNavigation.getMenu().getItem(position);


                if (prevMenuItem != null)
                {
                    if(position==0){
                        menu.findItem(R.id.personal).setIcon(R.drawable.ic_man_user);
                    }

                    if(position==1){
                        menu.findItem(R.id.eduaction).setIcon(R.drawable.ic_college_graduation);
                        //refreshPage(position);

                    }

                    if(position==2){
                        menu.findItem(R.id.experience).setIcon(R.drawable.ic_certification_document_text_paper_black_interface_symbol);
                        //refreshPage(position);


                    }

                    prevMenuItem.setChecked(false);
                }
                else
                {
                    employeeeBottomNavigation.getMenu().getItem(0).setChecked(false);
                }



            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });




        employeeeBottomNavigation.setOnNavigationItemSelectedListener(new
                         BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override

            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.personal:
                        viewPager.setCurrentItem(0);
                        break;

                    case R.id.eduaction:
                        viewPager.setCurrentItem(1);
                        break;

                    case R.id.experience:
                        viewPager.setCurrentItem(2);
                        break;

                }

                return false;
            }
                         });



    }



    private void setupViewPager(ViewPager viewPager) {
        viewPager.setAdapter(new MyFragmentPagerAdapter(
                getSupportFragmentManager(), this));


    }

    private void selectedItems(MenuItem item, Fragment fragment) {
        item.setChecked(true);
        switch (item.getItemId()) {

            //loading fragment 1
            case R.id.personal:
                viewPager.setCurrentItem(0);
                break;

            //loading fragment 2

            case R.id.eduaction:
                viewPager.setCurrentItem(1);
                break;


            //loading fragment 4

            case R.id.experience:
                viewPager.setCurrentItem(2);
                break;



        }

    }



    //here we are handling to move the fragment..after finishing 1st one it will move to 2nd fragment..vice versa
    public void loadNextFragment(int button_enable, Fragment fragment,Bundle bnd) {
        switch (button_enable){
            case 1:
                //getSupportFragmentManager().findFragmentByTag("ABC");
                this.fragment = fragment;
               /* FamilyInfoFragment familyInfoFragment = (FamilyInfoFragment) viewPagerAdapter.getItem(2);
                familyInfoFragment.loadBundle(bnd);*/
                selectedItems(menuNav.getItem(1),fragment);

                break;


            case 2:
                this.fragment = fragment;
                selectedItems(menuNav.getItem(2),fragment);
                break;


            case 3:
                this.fragment = fragment;
                selectedItems(menuNav.getItem(3),fragment);
                break;

        }

    }


    public void loadBundle(Bundle b) {

    }


}