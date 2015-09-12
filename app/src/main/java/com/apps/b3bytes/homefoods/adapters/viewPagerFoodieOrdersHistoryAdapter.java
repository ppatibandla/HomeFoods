package com.apps.b3bytes.homefoods.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.apps.b3bytes.homefoods.fragments.FoodiePastOrdersTabFragment;
import com.apps.b3bytes.homefoods.fragments.FoodiePendingOrdersTabFragment;

public class viewPagerFoodieOrdersHistoryAdapter extends FragmentStatePagerAdapter {
    private CharSequence Titles[]; // This will Store the Titles of the Tabs which are Going to be passed when ViewPagerAdapter is created
    private int NumbOfTabs; // Store the number of tabs, this will also be passed when the ViewPagerAdapter is created
    private Context mContext;

    // Build a Constructor and assign the passed Values to appropriate values in the class
    public viewPagerFoodieOrdersHistoryAdapter(Context context, FragmentManager fm, CharSequence mTitles[], int mNumbOfTabsumb) {
        super(fm);
        mContext = context;
        this.Titles = mTitles;
        this.NumbOfTabs = mNumbOfTabsumb;

    }

    //This method return the fragment for the every position in the View Pager
    @Override
    public Fragment getItem(int position) {

        if (position == 0) // if the position is 0 we are returning the First tab
        {
            FoodiePendingOrdersTabFragment pendingOrdersTab = new FoodiePendingOrdersTabFragment();
            return pendingOrdersTab;
        } else             // As we are having 2 tabs if the position is now 0 it must be 1 so we are returning second tab
        {
            FoodiePastOrdersTabFragment pastOrdersTab = new FoodiePastOrdersTabFragment();
            return pastOrdersTab;
        }
    }

    // This method return the titles for the Tabs in the Tab Strip

    @Override
    public CharSequence getPageTitle(int position) {
        return Titles[position];
    }

    // This method return the Number of tabs for the tabs Strip

    @Override
    public int getCount() {
        return NumbOfTabs;
    }

}
