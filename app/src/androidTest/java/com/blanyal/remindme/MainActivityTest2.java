package com.blanyal.remindme;


import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

import com.getbase.floatingactionbutton.FloatingActionButton;

import org.junit.Test;

/**
 * Created by Dell on 11/12/2015.
 */

public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {


    private MainActivity mActivity;
    private FloatingActionButton mAddReminderButton;;
    private String resourceString;

    public MainActivityTest(Class<MainActivity> activityClass) {
        super(activityClass);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mActivity = this.getActivity();
        mAddReminderButton = (FloatingActionButton) mActivity.findViewById
                (R.id.add_reminder);

    }

    @Test
    public void clickOnFloating(){
        //mAddReminderButton.
    }
}