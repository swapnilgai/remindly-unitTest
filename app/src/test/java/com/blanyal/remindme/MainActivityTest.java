package com.blanyal.remindme;

import android.content.Intent;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowIntent;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.robolectric.Shadows.shadowOf;

/**
 * Created by swapnil on 11/13/2015.
 */

@RunWith(RobolectricGradleTestRunner.class)
@Config(packageName = "com.blanyal.remindme", constants = BuildConfig.class, sdk = 21)
public class MainActivityTest {

    private MainActivity activity;
    private ReminderDatabase reminderDatabase;
    private Reminder reminder;

    /*Ref:
      http://antonioleiva.com/android-unit-testing-using-robolectric-introduction/
   */
    @Before
    public void setup() {
        activity = Robolectric.buildActivity(MainActivity.class).create().get();
    }

    //TC ID 23
    @Test
    public void emptyRecyclerViewText() throws Exception {
        TextView textView = (TextView) activity.findViewById(R.id.no_reminder_text);
        assertEquals(textView.getText(), "Click on the plus button below to begin creating your reminders!");
    }

    //TC Id 24
    @Test
    public void testAddReminderButtonClick() throws Exception {
        activity.findViewById(R.id.add_reminder).performClick();
        ShadowActivity shadowActivity = shadowOf(activity);
        Intent startedIntent = shadowActivity.getNextStartedActivity();
        ShadowIntent shadowIntent = shadowOf(startedIntent);
        assertThat(shadowIntent.getComponent().getClassName(), equalTo(ReminderAddActivity.class.getName()));
    }
}