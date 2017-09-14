package com.emperor_tracare.emperortracked.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.emperor_tracare.emperortracked.AlarmsActivity;
import com.emperor_tracare.emperortracked.R;
import com.emperor_tracare.emperortracked.adapter.AlarmsAdapterRecyclerView;
import com.emperor_tracare.emperortracked.model.Alarm;
import com.emperor_tracare.emperortracked.model.MyApplication;

import java.util.ArrayList;

public class AlarmsFragment extends Fragment {

    CoordinatorLayout coordinatorLayout;
    PopupWindow popupWindow;
    View popupView;
    int mCurrentX,mCurrentY;

    private ArrayList<Alarm> alarms;

    public AlarmsFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_alarms, container, false);

        coordinatorLayout = (CoordinatorLayout) view.findViewById(R.id.coordinator_fragment_list_alarms);
        alarms = ((MyApplication) getActivity().getApplication()).getAlarms();

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.floating_add_alarms);
        FloatingActionButton fabMoreAlarms = (FloatingActionButton) view.findViewById(R.id.fab_create_more_alarms);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), AlarmsActivity.class);
                startActivity(intent);
            }
        });

        fabMoreAlarms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), AlarmsActivity.class);
                startActivity(intent);
            }
        });

        LinearLayout linearLayoutListAlarms = (LinearLayout)view.findViewById(R.id.linearLayout_fragment_list_alarms);
        LinearLayout linearLayoutNoAlarms = (LinearLayout)view.findViewById(R.id.linearLayout_fragment_no_alarms);

        if (alarms == null || alarms.isEmpty()){
            linearLayoutListAlarms.setVisibility(view.GONE);
            fabMoreAlarms.setVisibility(view.GONE);
            linearLayoutNoAlarms.setVisibility(view.VISIBLE);
        } else {
            linearLayoutNoAlarms.setVisibility(view.GONE);
            linearLayoutListAlarms.setVisibility(view.VISIBLE);
            fabMoreAlarms.setVisibility(view.VISIBLE);
            RecyclerView alarmsRecyclerView = (RecyclerView)view.findViewById(R.id.alarmsListRecycler);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            alarmsRecyclerView.setLayoutManager(linearLayoutManager);
            AlarmsAdapterRecyclerView alarmsAdapterRecyclerView = new AlarmsAdapterRecyclerView(alarms, R.layout.item_list_alarms, getActivity());
            alarmsRecyclerView.setAdapter(alarmsAdapterRecyclerView);
        }
        return view;
    }

}
