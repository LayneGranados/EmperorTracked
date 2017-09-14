package com.emperor_tracare.emperortracked;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TimePicker;

import com.emperor_tracare.emperortracked.model.Alarm;
import com.emperor_tracare.emperortracked.model.MyApplication;

import java.util.ArrayList;

public class AlarmsActivity extends AppCompatActivity {

    private ArrayList<Alarm> alarms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarms);
        showToolbar("Create an Alarm", true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            getWindow().setEnterTransition(new Fade());
        }

        alarms = ((MyApplication)this.getApplication()).getAlarms();
        if( alarms == null ) {
            alarms = new ArrayList<>();
        }
        Button createAlarm = (Button)findViewById(R.id.button_create_alarm);
        final TextInputEditText alarmName = (TextInputEditText)findViewById(R.id.textview_alarm_name);
        final CheckBox checkBoxMonday = (CheckBox)findViewById(R.id.checkbox_selector_monday);
        final CheckBox checkBoxTuesday = (CheckBox)findViewById(R.id.checkbox_selector_tuesday);
        final CheckBox checkBoxWednesday = (CheckBox)findViewById(R.id.checkbox_selector_wednesday);
        final CheckBox checkBoxThursday = (CheckBox)findViewById(R.id.checkbox_selector_thursday);
        final CheckBox checkBoxFriday = (CheckBox)findViewById(R.id.checkbox_selector_friday);
        final CheckBox checkBoxSaturday = (CheckBox)findViewById(R.id.checkbox_selector_saturday);
        final CheckBox checkBoxSunday = (CheckBox)findViewById(R.id.checkbox_selector_sunday);
        final TimePicker timePicker = (TimePicker)findViewById(R.id.time_picker_create_alarm);

        createAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Alarm alarm = new Alarm();
                alarm.setName(alarmName.getText().toString());
                alarm.setMonday(checkBoxMonday.isChecked());
                alarm.setTuesday(checkBoxTuesday.isChecked());
                alarm.setWednesday(checkBoxWednesday.isChecked());
                alarm.setThursday(checkBoxThursday.isChecked());
                alarm.setFriday(checkBoxFriday.isChecked());
                alarm.setSaturday(checkBoxSaturday.isChecked());
                alarm.setSunday(checkBoxSunday.isChecked());
                int hour = 0;
                int minute = 0;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                    hour = timePicker.getHour();
                    minute = timePicker.getMinute();
                } else {
                    hour = timePicker.getCurrentHour();
                    minute = timePicker.getCurrentMinute();
                }
                alarm.setHour(hour);
                alarm.setMinutes(minute);

                alarms.add(alarm);
                ((MyApplication) getApplication()).setAlarms(alarms);
                Intent intent = new Intent(getApplicationContext(), PersonTrackedContainerActivity.class);
                intent.putExtra("fragment","alarms");
                startActivity(intent);
            }
        });
    }

    public void showToolbar(String title, boolean upButton){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }
}
