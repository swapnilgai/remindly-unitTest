package com.blanyal.remindme;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by swapnil on 12/2/15.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(packageName = "com.blanyal.remindme", constants = BuildConfig.class, sdk = 21)
public class ReminderAddActivityTest {

    private ReminderAddActivity activity;
    private ReminderDatabase reminderDatabase;
    private Reminder reminder;
    private View mTestRoboActivityView;
    static int count=0;
    static int idForDelete;
    private Context mContext;

    public ReminderAddActivityTest(){
    }


    /*Ref:
    http://antonioleiva.com/android-unit-testing-using-robolectric-introduction/
   */
    @Before
    public void setup() {
        activity = Robolectric.buildActivity(ReminderAddActivity.class).create().get();
    }

    // TC ID 01
    @Test
    public void testCreateReminder() {
        Context context = activity.getApplicationContext();
        reminderDatabase = new ReminderDatabase(context);
        reminder = new Reminder();
        reminder.setID(2);
        reminder.setTitle("testFromDa");
        reminder.setDate("14/11/2015");
        reminder.setTime("11:59");
        reminder.setRepeat("true");
        reminder.setRepeatNo("1");
        reminder.setRepeatType("Hour");
        reminder.setActive("true");
        whenDataisSave(reminderDatabase, reminder);
    }

    //TC Id 4
    @Test
    public void testReminderTitleText() throws Exception {
        EditText textView = (EditText) activity.findViewById(R.id.reminder_title);
        String temp = "reminder test";
        textView.setText(temp);
        assertEquals(textView.getText(), temp);
    }

    //TC Id 05
    @Test
    public void testEditDateSetText() throws Exception {
        TextView textView = (TextView) activity.findViewById(R.id.set_date);
        textView.setText("11/14/2105");
        assertEquals("11/14/2105", textView.getText());
    }

    //TC Id 06
    @Test
    public void testEditTimeSetText() throws Exception {
        TextView textView = (TextView) activity.findViewById(R.id.set_time);
        textView.setText("11:20");
        assertEquals("11:20",textView.getText());
    }
    //TC Id 07
    @Test
    public void testEmptyDateText() throws Exception {
        TextView textView = (TextView) activity.findViewById(R.id.date_text);
        assertEquals(textView.getText(), "Date");
    }

    //TC Id 08
    @Test
    public void testDefaultDateSetText() throws Exception {
        TextView textView = (TextView) activity.findViewById(R.id.set_date);

        //ref : http://stackoverflow.com/questions/8745297/want-current-date-and-time-in-dd-mm-yyyy-hhmmss-ss-format
        String pattern = "dd/MM/yyyy";
        String dateInString =new SimpleDateFormat(pattern).format(new Date());

        assertEquals(dateInString,textView.getText());
    }

    //TC Id 09
    @Test
    public void testEmptyTimeText() throws Exception {
        TextView textView = (TextView) activity.findViewById(R.id.time_text);
        assertEquals(textView.getText(), "Time");
    }

    //TC Id 10
    @Test
    public void testDefaultTimeSetText() throws Exception {
        TextView textView = (TextView) activity.findViewById(R.id.set_time);

        //ref : http://stackoverflow.com/questions/8745297/want-current-date-and-time-in-dd-mm-yyyy-hhmmss-ss-format
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        String formattedDate = dateFormat.format(new Date()).toString();

        assertEquals(formattedDate, textView.getText());
    }

    //TC Id 11
    @Test
    public void testEmptyRepeatText() throws Exception {
        TextView textView = (TextView) activity.findViewById(R.id.repeat_text);
        assertEquals(textView.getText(), "Repeat");
    }
    //TC Id 12
    @Test
    public void testDefaultSwitchOn() throws Exception {
        TextView textView = (TextView) activity.findViewById(R.id.set_repeat);
        Switch switc = (Switch) activity.findViewById(R.id.repeat_switch);
        assertTrue(switc.isChecked());
    }

    //TC Id 13
    @Test
    public void testDefaultRepeatSetTextOnSwitchOn() throws Exception {
        TextView textView = (TextView) activity.findViewById(R.id.set_repeat);
        Switch switc = (Switch) activity.findViewById(R.id.repeat_switch);
        if (switc.callOnClick())
            assertEquals("Every 1 Hour(s)", textView.getText());
    }

    //TC Id 14
    @Test
    public void testDefaultRepeatSetTextOnSwitchOff() throws Exception {
        Switch switc = (Switch) activity.findViewById(R.id.repeat_switch);
        switc.performClick();
        TextView textView = (TextView) activity.findViewById(R.id.set_repeat);

        if(switc.isChecked()==false)
            assertEquals("Off", textView.getText());
    }

    //TC Id 15
    @Test
    public void testEmptyRepeatNoText() throws Exception {
        TextView textView = (TextView) activity.findViewById(R.id.repeat_no_text);
        assertEquals(textView.getText(), "Repetition Interval");
    }

    //TC Id 16
    @Test
    public void testDefaultRepeatNoSetText() throws Exception {
        TextView textView = (TextView) activity.findViewById(R.id.set_repeat_no);
        assertEquals("1", textView.getText());
    }

    //TC Id 17
    @Test
    public void testEmptyTypeOfRepetitionsText() throws Exception {
        TextView textView = (TextView) activity.findViewById(R.id.repeat_type_text);
        assertEquals(textView.getText(), "Type of Repetitions");
    }

    //Tc Id 18
    @Test
    public void testDefaultEmptyTypeOfRepetitionsSetText() throws Exception {
        TextView textView = (TextView) activity.findViewById(R.id.set_repeat_type);
        assertEquals(textView.getText(), "Hour");
    }

    //TC Id 19
    @Test
    public void testEmptyDetailText() throws Exception {
        TextView textView = (TextView) activity.findViewById(R.id.details);
        assertEquals(textView.getText(), "Details");
    }


    //TC Id 20
    @Test
    public void testGetReminder()
    {
        int count=0;
        Context context =  activity.getApplicationContext();
        reminderDatabase = new ReminderDatabase(context);
        reminder = new Reminder();
        reminder.setID(3);
        reminder.setTitle("testFromDa");
        reminder.setDate("14/11/2015");
        reminder.setTime("11:59");
        reminder.setRepeat("true");
        reminder.setRepeatNo("1");
        reminder.setRepeatType("Hour");
        reminder.setActive("true");
        reminderDatabase.addReminder(reminder);

        whenDataisSave(reminderDatabase,reminder );
        count++;

        reminder = new Reminder();
        reminder.setID(4);
        reminder.setTitle("testFromDa4");
        reminder.setDate("14/11/2015");
        reminder.setTime("11:20");
        reminder.setRepeat("true");
        reminder.setRepeatNo("1");
        reminder.setRepeatType("Hour");
        reminder.setActive("true");

        whenDataisSave(reminderDatabase, reminder);
        count++;

        List<Reminder> temp = reminderDatabase.getAllReminders();
        count++;

        assertEquals(temp.size(), count);
    }

    //TC Id 22
    @Test
    public void testMultipleAddReminderForSameData()
    {
        Context context =  activity.getApplicationContext();
        reminderDatabase = new ReminderDatabase(context);
        reminder = new Reminder();
        reminder.setID(6);
        reminder.setTitle("testFromDa");
        reminder.setDate("14/11/2015");
        reminder.setTime("11:59");
        reminder.setRepeat("true");
        reminder.setRepeatNo("1");
        reminder.setRepeatType("Hour");
        reminder.setActive("true");
        count++;
        whenDataisSave(reminderDatabase, reminder);

        reminder = new Reminder();
        reminder.setID(6);
        reminder.setTitle("testFromDa");
        reminder.setDate("14/11/2015");
        reminder.setTime("11:59");
        reminder.setRepeat("true");
        reminder.setRepeatNo("1");
        reminder.setRepeatType("Hour");
        reminder.setActive("true");

        whenDataisSave(reminderDatabase, reminder);

        List<Reminder> temp = reminderDatabase.getAllReminders();

        String title[]= new String[2];
        String datet[]= new String[2];
        String time[]= new String[2];
        String repeat[]= new String[2];
        String active[] = new String[2];

        int i=0;
        for(Reminder r: temp)
        {
            title[i]=r.getTitle();
            datet[i]=r.getDate();
            time[i]= r.getTime();
            repeat[i]= r.getRepeat();
            active[i] = r.getActive();
            i++;
            if(i>1)
                i=0;
        }
        assertNotEquals(title[0], title[1]);
        assertNotEquals(datet[0], datet[1]);
        assertNotEquals(time[0], time[1]);
        assertNotEquals(repeat[0], repeat[1]);
        assertNotEquals(active[0], active[1]);
    }


    //TC Id 21
    @Test
    public void testGetReminderCount()
    {
        Context context =  activity.getApplicationContext();
        reminderDatabase = new ReminderDatabase(context);
        int count = 0;
        reminder = new Reminder();
        reminder.setID(5);
        reminder.setTitle("testFromDa4");
        reminder.setDate("14/11/2015");
        reminder.setTime("11:20");
        reminder.setRepeat("true");
        reminder.setRepeatNo("1");
        reminder.setRepeatType("Hour");
        reminder.setActive("true");
        count++;
        whenDataisSave(reminderDatabase, reminder);

        int countInDb = reminderDatabase.getRemindersCount();
        assertEquals(countInDb,count);
    }

    private Reminder whenDataisSave(ReminderDatabase reminderDatabase, Reminder reminder) {
        Reminder expected = new Reminder();
        boolean isSave = false;
        boolean isRetrive = false;
        int a = 0;
        idForDelete=a;
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
            count+=1;
            if (isRetrive)
                assertEquals(reminder.getTitle(), expected.getTitle());
            else
                assertEquals(reminder.getTitle(), expected.getTitle());
        }
        return expected;
    }

}