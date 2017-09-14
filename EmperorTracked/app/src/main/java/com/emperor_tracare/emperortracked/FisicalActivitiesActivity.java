package com.emperor_tracare.emperortracked;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.view.View;

import com.emperor_tracare.emperortracked.adapter.FisicalActivityAdapterRecyclerView;
import com.emperor_tracare.emperortracked.model.FisicalActivity;
import com.emperor_tracare.emperortracked.model.MyApplication;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;

public class FisicalActivitiesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fisical_activities);
        showToolbar("Fisical Activities", true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            getWindow().setEnterTransition(new Fade());
        }
        ((MyApplication)this.getApplication()).setFragment("fisical");

        GraphView graph = (GraphView) findViewById(R.id.graph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(0, 3.5),
                new DataPoint(1, 4.2),
                new DataPoint(2, 7.3),
                new DataPoint(3, 4.9),
                new DataPoint(4, 9.2),
                new DataPoint(5, 5.2),
                new DataPoint(6, 9.5),
                new DataPoint(7, 7.8),
                new DataPoint(8, 8)
        });
        graph.addSeries(series);

        RecyclerView fisicalActivitiesRecyclerView = (RecyclerView)findViewById(R.id.fisical_activity_list_recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        fisicalActivitiesRecyclerView.setLayoutManager(linearLayoutManager);
        FisicalActivityAdapterRecyclerView sleepRecordAdapterRecyclerView = new FisicalActivityAdapterRecyclerView(buildFisicalActivities(), R.layout.item_list_fisical_activities_record);
        fisicalActivitiesRecyclerView.setAdapter(sleepRecordAdapterRecyclerView);

        FloatingActionButton add = (FloatingActionButton)findViewById(R.id.fab_add_fisical_activity);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), StartFisicalActivitiesActivity.class);
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

    private ArrayList<FisicalActivity> buildFisicalActivities() {
        ArrayList<FisicalActivity> fisicalActivities = new ArrayList<>();
        FisicalActivity sr1 = new FisicalActivity("14","JUL","RUN","05:57","250");
        FisicalActivity sr2 = new FisicalActivity("13","JUL","SWIM","22:40","670");
        FisicalActivity sr3 = new FisicalActivity("12","JUL","PLAY","15:30","496");
        FisicalActivity sr4 = new FisicalActivity("11","JUL","RUN","86:16","740");
        FisicalActivity sr5 = new FisicalActivity("10","JUL","BIKE","75:39","630");
        FisicalActivity sr6 = new FisicalActivity("09","JUL","BIKE","15:00","300");
        FisicalActivity sr7 = new FisicalActivity("08","JUL","SWIM","46:00","450");
        FisicalActivity sr8 = new FisicalActivity("07","JUL","RUN","60:00","819");
        FisicalActivity sr9 = new FisicalActivity("06","JUL","BIKE","56:23","560");
        fisicalActivities.add(sr1);
        fisicalActivities.add(sr2);
        fisicalActivities.add(sr3);
        fisicalActivities.add(sr4);
        fisicalActivities.add(sr5);
        fisicalActivities.add(sr6);
        fisicalActivities.add(sr7);
        fisicalActivities.add(sr8);
        fisicalActivities.add(sr9);
        return fisicalActivities;
    }
}
