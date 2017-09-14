package com.emperor_tracare.emperortracked;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ToggleButton;

public class StartFisicalActivitiesActivity extends AppCompatActivity {

    long timeElapsed = 0;

    boolean stopped;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_fisical_activities);
        final Chronometer chronometer = (Chronometer)findViewById(R.id.chronometer_fisical_activity);
        showToolbar("Fisical Activities", true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            getWindow().setEnterTransition(new Fade());
        }
        final ToggleButton start = (ToggleButton)findViewById(R.id.button_start_chronometer);
        ToggleButton stop = (ToggleButton)findViewById(R.id.button_stop_chronometer);

        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                timeElapsed = SystemClock.elapsedRealtime() - chronometer.getBase();
            }
        });
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(timeElapsed == 0){
                    chronometer.setBase(SystemClock.elapsedRealtime());
                    chronometer.start();
                }

                if(timeElapsed != 0){
                    timeElapsed = 0;
                    chronometer.stop();
                }

                stopped = false;
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chronometer.stop();
                start.setVisibility(v.GONE);
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
