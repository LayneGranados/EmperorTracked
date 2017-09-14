package com.emperor_tracare.emperortracked;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;

import com.emperor_tracare.emperortracked.adapter.SleepRecordAdapterRecyclerView;
import com.emperor_tracare.emperortracked.model.MyApplication;
import com.emperor_tracare.emperortracked.model.SleepRecord;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;

public class SleepRecordsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep_records);
        showToolbar("Sleep Records", true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            getWindow().setEnterTransition(new Fade());
        }
        ((MyApplication)this.getApplication()).setFragment("sleep");

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

        RecyclerView sleepRecordsRecyclerView = (RecyclerView)findViewById(R.id.sleep_records_list_recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        sleepRecordsRecyclerView.setLayoutManager(linearLayoutManager);
        SleepRecordAdapterRecyclerView sleepRecordAdapterRecyclerView = new SleepRecordAdapterRecyclerView(buildSleepRecords(), R.layout.item_list_sleep_record);
        sleepRecordsRecyclerView.setAdapter(sleepRecordAdapterRecyclerView);

    }

    public void showToolbar(String title, boolean upButton){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }

    private ArrayList<SleepRecord> buildSleepRecords() {
        ArrayList<SleepRecord> sleepRecords = new ArrayList<>();
        SleepRecord sr1 = new SleepRecord("14","JUL","Poor sleep time","3","30");
        SleepRecord sr2 = new SleepRecord("13","JUL","Poor sleep time","4","17");
        SleepRecord sr3 = new SleepRecord("12","JUL","Sufficient sleep time","7","22");
        SleepRecord sr4 = new SleepRecord("11","JUL","Poor sleep time","4","56");
        SleepRecord sr5 = new SleepRecord("10","JUL","Too much sleep time","9","11");
        SleepRecord sr6 = new SleepRecord("09","JUL","Poor sleep time","5","12");
        SleepRecord sr7 = new SleepRecord("08","JUL","Too much sleep time","9","34");
        SleepRecord sr8 = new SleepRecord("07","JUL","Sufficient sleep time","7","45");
        SleepRecord sr9 = new SleepRecord("06","JUL","Sufficient sleep time","8","02");
        sleepRecords.add(sr1);
        sleepRecords.add(sr2);
        sleepRecords.add(sr3);
        sleepRecords.add(sr4);
        sleepRecords.add(sr5);
        sleepRecords.add(sr6);
        sleepRecords.add(sr7);
        sleepRecords.add(sr8);
        sleepRecords.add(sr9);
        return sleepRecords;
    }
}
