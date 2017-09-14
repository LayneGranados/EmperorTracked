package com.emperor_tracare.emperortracked;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;

import com.emperor_tracare.emperortracked.adapter.HeartRateRecordAdapterRecyclerView;
import com.emperor_tracare.emperortracked.model.HeartRateRecord;
import com.emperor_tracare.emperortracked.model.MyApplication;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;

public class HeartRateRecordsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heart_rate_records);
        showToolbar("Heart Rate Records", true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            getWindow().setEnterTransition(new Fade());
        }
        ((MyApplication)this.getApplication()).setFragment("heart");

        GraphView graph = (GraphView) findViewById(R.id.graph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[]{
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

        RecyclerView heartRateRecordsRecyclerView = (RecyclerView) findViewById(R.id.heart_rate_records_list_recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        heartRateRecordsRecyclerView.setLayoutManager(linearLayoutManager);
        HeartRateRecordAdapterRecyclerView heartRateRecordAdapterRecyclerView = new HeartRateRecordAdapterRecyclerView(buildHeartRateRecords(), R.layout.item_list_heart_rate_record);
        heartRateRecordsRecyclerView.setAdapter(heartRateRecordAdapterRecyclerView);

    }

    public void showToolbar(String title, boolean upButton) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }

    private ArrayList<HeartRateRecord> buildHeartRateRecords() {
        ArrayList<HeartRateRecord> heartRateRecords = new ArrayList<>();
        HeartRateRecord hr1 = new HeartRateRecord("14", "JUL", "Heart Rate is fast", "157");
        HeartRateRecord hr2 = new HeartRateRecord("13", "JUL", "Heart Rate is fast", "140");
        HeartRateRecord hr3 = new HeartRateRecord("12", "JUL", "Heart Rate is normal", "80");
        HeartRateRecord hr4 = new HeartRateRecord("11", "JUL", "Heart Rate is slow", "62");
        HeartRateRecord hr5 = new HeartRateRecord("10", "JUL", "Heart Rate is normal", "85");
        HeartRateRecord hr6 = new HeartRateRecord("09", "JUL", "Heart Rate is fast", "120");
        HeartRateRecord hr7 = new HeartRateRecord("08", "JUL", "Heart Rate is normal", "100");
        HeartRateRecord hr8 = new HeartRateRecord("07", "JUL", "Heart Rate is slow", "60");
        HeartRateRecord hr9 = new HeartRateRecord("06", "JUL", "Heart Rate is normal", "93");
        heartRateRecords.add(hr1);
        heartRateRecords.add(hr2);
        heartRateRecords.add(hr3);
        heartRateRecords.add(hr4);
        heartRateRecords.add(hr5);
        heartRateRecords.add(hr6);
        heartRateRecords.add(hr7);
        heartRateRecords.add(hr8);
        heartRateRecords.add(hr9);
        return heartRateRecords;
    }
}