package com.emperor_tracare.emperortracked;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;

import com.emperor_tracare.emperortracked.adapter.StepRecordAdapterRecyclerView;
import com.emperor_tracare.emperortracked.model.MyApplication;
import com.emperor_tracare.emperortracked.model.StepRecord;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;

public class StepRecordsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_records);
        showToolbar("Step Records", true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            getWindow().setEnterTransition(new Fade());
        }
        ((MyApplication)this.getApplication()).setFragment("step");

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

        RecyclerView stepRecordsRecyclerView = (RecyclerView)findViewById(R.id.step_records_list_recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        stepRecordsRecyclerView.setLayoutManager(linearLayoutManager);
        StepRecordAdapterRecyclerView stepRecordAdapterRecyclerView = new StepRecordAdapterRecyclerView(buildStepRecords(), R.layout.item_list_step_record);
        stepRecordsRecyclerView.setAdapter(stepRecordAdapterRecyclerView);
    }

    public void showToolbar(String title, boolean upButton){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }

    private ArrayList<StepRecord> buildStepRecords() {
        ArrayList<StepRecord> stepRecords = new ArrayList<>();
        StepRecord sr1 = new StepRecord("14","JUL","3.467","250");
        StepRecord sr2 = new StepRecord("13","JUL","12.000","1.500");
        StepRecord sr3 = new StepRecord("12","JUL","8.765","743");
        StepRecord sr4 = new StepRecord("11","JUL","15.000","2.038");
        StepRecord sr5 = new StepRecord("10","JUL","7.523","654");
        StepRecord sr6 = new StepRecord("09","JUL","1.600","80");
        stepRecords.add(sr1);
        stepRecords.add(sr2);
        stepRecords.add(sr3);
        stepRecords.add(sr4);
        stepRecords.add(sr5);
        stepRecords.add(sr6);
        return stepRecords;
    }
}
