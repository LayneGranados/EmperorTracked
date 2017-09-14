package com.emperor_tracare.emperortracked;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.emperor_tracare.emperortracked.adapter.WeightRecordAdapterRecyclerView;
import com.emperor_tracare.emperortracked.model.MyApplication;
import com.emperor_tracare.emperortracked.model.WeightRecord;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;

public class WeightRecordsActivity extends AppCompatActivity {

    PopupWindow popupWindow;
    View popupView;
    int mCurrentX,mCurrentY;
    CoordinatorLayout coordinatorLayout;

    Activity activity;

    static final int REQUEST_TAKE_PHOTO = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_records);
        showToolbar("Weight Records", true);
        coordinatorLayout = (CoordinatorLayout)this.findViewById(R.id.coordinator_weight_activity);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            getWindow().setEnterTransition(new Fade());
        }
        activity = this;
        ((MyApplication)this.getApplication()).setFragment("weight");

        FloatingActionButton addWeight = (FloatingActionButton)findViewById(R.id.fab_add_weight_record);

        addWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPopUpAddNewWeight(view);
            }
        });

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

        RecyclerView weightRecordsRecyclerView = (RecyclerView)findViewById(R.id.weight_records_list_recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        weightRecordsRecyclerView.setLayoutManager(linearLayoutManager);
        WeightRecordAdapterRecyclerView weightRecordAdapterRecyclerView = new WeightRecordAdapterRecyclerView(buildWeightRecords(), R.layout.item_list_weight_record);
        weightRecordsRecyclerView.setAdapter(weightRecordAdapterRecyclerView);

    }

    public void showToolbar(String title, boolean upButton){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }

    private ArrayList<WeightRecord> buildWeightRecords() {
        ArrayList<WeightRecord> weightRecords = new ArrayList<>();
        WeightRecord sr1 = new WeightRecord("14","JUL","67.2");
        WeightRecord sr2 = new WeightRecord("09","JUL","67.5");
        WeightRecord sr3 = new WeightRecord("01","JUL","66.9");
        WeightRecord sr4 = new WeightRecord("25","JUN","67.2");
        WeightRecord sr5 = new WeightRecord("16","JUN","68.1");
        WeightRecord sr6 = new WeightRecord("20","MAY","68.5");
        weightRecords.add(sr1);
        weightRecords.add(sr2);
        weightRecords.add(sr3);
        weightRecords.add(sr4);
        weightRecords.add(sr5);
        weightRecords.add(sr6);
        return weightRecords;
    }

    private void openPopUpAddNewWeight(View view){

        popupView = getLayoutInflater().inflate(R.layout.popup_add_photo_weight, null);
        popupWindow = new PopupWindow(popupView, (coordinatorLayout.getMeasuredWidth() - 80) , RelativeLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);

        Button btnAdd = (Button)popupView.findViewById(R.id.button_add_popup_weight);
        Button btnCancel = (Button)popupView.findViewById(R.id.button_cancel_popup_weight);
        LinearLayout linearLayout = (LinearLayout)popupView.findViewById(R.id.linear_layout_peguin_add_weight);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(activity,
                        Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.CAMERA)) {
                    } else {
                        ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CAMERA}, REQUEST_TAKE_PHOTO);
                    }
                } else {
                    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                        startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
                    }
                }


            }
        });


        popupWindow.showAtLocation(coordinatorLayout, Gravity.CENTER, 0, 0);
        popupView.setOnTouchListener(new View.OnTouchListener() {
            private float mDx;
            private float mDy;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                if (action == MotionEvent.ACTION_DOWN) {
                    mDx = mCurrentX - event.getRawX();
                    mDy = mCurrentY - event.getRawY();
                } else
                if (action == MotionEvent.ACTION_MOVE) {
                    mCurrentX = (int) (event.getRawX() + mDx);
                    mCurrentY = (int) (event.getRawY() + mDy);
                    popupWindow.update(mCurrentX, mCurrentY, -1, -1);
                }
                return true;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            LinearLayout addWeight = (LinearLayout)popupView.findViewById(R.id.linear_layout_peguin_add_weight);
            LinearLayout showPhoto = (LinearLayout)popupView.findViewById(R.id.linear_layout_photo_added);
            ImageView imagePhoto = (ImageView)popupView.findViewById(R.id.imageview_photo_added);
            if (imageBitmap != null) {
                addWeight.setVisibility(View.GONE);
                imagePhoto.setImageBitmap(imageBitmap);
                showPhoto.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_TAKE_PHOTO: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                        startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
                    }
                } else {
                }
                return;
            }
        }
    }


}

