package com.blanyal.remindme;

import android.content.Context;
import android.content.Intent;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;

import static junit.framework.Assert.assertEquals;
import static org.robolectric.Shadows.shadowOf;


/**
 * Created by swapnil on 12/2/15.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(packageName = "com.blanyal.remindme", constants = BuildConfig.class, sdk = 21)
public class ReminderEditActivityTest {

    private ReminderEditActivity activity;
    private ReminderAddActivity activityadd;
    private MainActivity mainActivity;

    private ReminderDatabase reminderDatabase;
    private Reminder reminder;
    static int count=0;
    static int id;

    /*Ref:
      http://antonioleiva.com/android-unit-testing-using-robolectric-introduction/
    */
    @Before
    public void setup() {
        mainActivity= Robolectric.buildActivity(MainActivity.class).create().get();
        mainActivity.findViewById(R.id.add_reminder).performClick();
        ShadowActivity shadowActivity = shadowOf(mainActivity);
        Intent startedIntent = shadowActivity.getNextStartedActivity();
        activity = Robolectric.buildActivity(ReminderEditActivity.class).withIntent(startedIntent).get();
    }

    // TC ID 3
    @Test
    public void testEditReminder()
    {
        Context context = activity.getApplicationContext();
        reminderDatabase = new ReminderDatabase(context);

        reminder = new Reminder();
        reminder.setID(0);
        reminder.setTitle("testFromDa");
        reminder.setDate("14/11/2015");
        reminder.setTime("11:59");
        reminder.setRepeat("true");
        reminder.setRepeatNo("1");
        reminder.setRepeatType("Hour");
        reminder.setActive("true");
        whenDataisSave(reminderDatabase, reminder);
        count++;

        //updatereminder
        reminder.setID(1);
        reminder.setTitle("testFromDaUpdate");
        reminder.setDate("14/11/2015");
        reminder.setTime("11:20");
        reminder.setRepeat("true");
        reminder.setRepeatNo("1");
        reminder.setRepeatType("Hour");
        reminder.setActive("true");

        id=reminderDatabase.updateReminder(reminder);

        Reminder temp1= reminderDatabase.getReminder(id);

        //Check for update for change in time and title
        assertEquals(reminder.getTitle(), temp1.getTitle());
        assertEquals(reminder.getTime(),temp1.getTime());
    }

    private Reminder whenDataisSave(ReminderDatabase reminderDatabase, Reminder reminder) {
        Reminder expected = new Reminder();
        boolean isSave = false;
        boolean isRetrive = false;
        int a = 0;
        try {
            a = reminderDatabase.addReminder(reminder);
            isSave = true;
        } catch (Exception e) {
            isSave = false;
        }
        if (isSave) {
            try {
                expected = reminderDatabase.getReminder(a);
                isRetrive = true;
            } catch (Exception e) {
                isRetrive = false;
            }
            count++;
            if (isRetrive)
                assertEquals(reminder.getTitle(), expected.getTitle());
            else
                assertEquals(reminder.getTitle(), expected.getTitle());
        }
        return expected;
    }

}