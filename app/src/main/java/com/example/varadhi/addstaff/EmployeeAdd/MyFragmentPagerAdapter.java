package com.example.varadhi.addstaff.EmployeeAdd;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.widget.Toast;

/**
 * @author Nishant Srivastava
 */

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    Context mContext;

    public MyFragmentPagerAdapter(FragmentManager fm, Context mContext) {
        super(fm);
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return 3;
    }

    /*@Override
    public int getItemPosition(Object object){
        return PagerAdapter.POSITION_NONE;
    }*/


    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new PersonalInfoFragment();

            case 1:
                return new EducationInfoFragment();

            case 2:
                return new WorkInfoFragment();

            default:
                return null;
        }

    }
}
