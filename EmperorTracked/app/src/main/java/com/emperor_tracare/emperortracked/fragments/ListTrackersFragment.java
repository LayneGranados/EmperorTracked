package com.emperor_tracare.emperortracked.fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.emperor_tracare.emperortracked.NavigationDrawerActivity;
import com.emperor_tracare.emperortracked.R;
import com.emperor_tracare.emperortracked.adapter.PeopleTrackedAdapterRecyclerView;
import com.emperor_tracare.emperortracked.model.MyApplication;
import com.emperor_tracare.emperortracked.model.Person;

import java.util.ArrayList;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class ListTrackersFragment extends Fragment {

    CoordinatorLayout coordinatorLayout;
    PopupWindow popupWindow;
    View popupView;
    int mCurrentX,mCurrentY;


    public ListTrackersFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_people_tracked, container, false);
        RecyclerView peopleTrackedRecyclerView = (RecyclerView)view.findViewById(R.id.trackedPeopleListRecycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        coordinatorLayout = (CoordinatorLayout) view.findViewById(R.id.coordinator_fragment_list_people_tracked);
        Person person = ((MyApplication) getActivity().getApplication()).getPersonSigned();
        ArrayList<Person> people = buildPeople();
        ((MyApplication) getActivity().getApplication()).getPersonSigned().setTracked(people);
        person.setTracked(people);

        FloatingActionButton fabAddPeople = (FloatingActionButton) view.findViewById(R.id.floating_add_people);
//        FloatingActionButton fabMorePeople = (FloatingActionButton) view.findViewById(R.id.fab_add_more_people);
        fabAddPeople.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPopUpAddPerson(view);
            }
        });
        /*fabMorePeople.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPopUpAddPerson(view);
            }
        });*/

        LinearLayout linearLayoutListPeopleTracked = (LinearLayout)view.findViewById(R.id.linearLayout_fragment_list_people_tracked);
        LinearLayout linearLayoutNoPeopleTracked = (LinearLayout)view.findViewById(R.id.linearLayout_fragment_no_people_tracked);
        if(person != null ){
            if(person.getTracked().isEmpty()) {
                linearLayoutListPeopleTracked.setVisibility(view.GONE);
                //fabMorePeople.setVisibility(view.GONE);
                linearLayoutNoPeopleTracked.setVisibility(view.VISIBLE);
            } else {
                linearLayoutNoPeopleTracked.setVisibility(view.GONE);
                linearLayoutListPeopleTracked.setVisibility(view.VISIBLE);
                //fabMorePeople.setVisibility(view.VISIBLE);

                peopleTrackedRecyclerView.setLayoutManager(linearLayoutManager);
                PeopleTrackedAdapterRecyclerView pleopleTrackedAdapterRecyclerView = new PeopleTrackedAdapterRecyclerView(buildPeople(), R.layout.item_list_people_tracked, getActivity());
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
                gridLayoutManager.setSpanCount(2);
                peopleTrackedRecyclerView.setLayoutManager(gridLayoutManager);
                peopleTrackedRecyclerView.setAdapter(pleopleTrackedAdapterRecyclerView);
            }
        }
        return view;
    }

    private ArrayList<Person> buildPeople(){
        ArrayList<Person> people = ((MyApplication)getActivity().getApplication()).getPeople();
        Person personActivated = ((MyApplication)getActivity().getApplication()).getPersonSigned();
        ArrayList<Person> peopleAux = new ArrayList<>();
        peopleAux.add(people.get(0));
        peopleAux.add(people.get(3));
        return peopleAux;
    }


    private void openPopUpAddPerson(View view){

        LayoutInflater layoutInflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        popupView = layoutInflater.inflate(R.layout.popup_add_person_tracked,null);
        popupWindow = new PopupWindow(popupView, (coordinatorLayout.getMeasuredWidth() -80) ,RelativeLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);

        Button btnCancel = (Button)popupView.findViewById(R.id.button_cancel_popup);
        Button btnAdd = (Button)popupView.findViewById(R.id.button_add_popup);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });

        final TextView hashCode = (TextView)popupView.findViewById(R.id.hash_code_popup);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
                ArrayList<Person> people = ((MyApplication)getActivity().getApplication()).getPeople();
                Person person = MyApplication.findPersonByHashCode(people, hashCode.getText().toString());
                if(person ==  null) {
                    openPopUpPersonNotFound(view);
                } else {
                    ((MyApplication)getActivity().getApplication()).getPersonSigned().getTracked().add(person);
                    openPopUpPersonWasAdded(view, ((MyApplication)getActivity().getApplication()).getPersonSigned());
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

    private void openPopUpPersonWasAdded(View view, final Person person){

        LayoutInflater layoutInflater = (LayoutInflater)getContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        popupView = layoutInflater.inflate(R.layout.popup_person_tracked_was_added,null);
        popupWindow = new PopupWindow(popupView, (coordinatorLayout.getMeasuredWidth() -80) ,RelativeLayout.LayoutParams.WRAP_CONTENT);

        Button btnClose = (Button)popupView.findViewById(R.id.button_close_popup);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
                Intent intent = new Intent(getActivity().getApplicationContext(), NavigationDrawerActivity.class);
                intent.putExtra("username",person.getUsuario());
                startActivity(intent);
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

    private void openPopUpPersonNotFound(View view){

        LayoutInflater layoutInflater = (LayoutInflater)getContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        popupView = layoutInflater.inflate(R.layout.popup_person_to_track_not_found,null);
        popupWindow = new PopupWindow(popupView, (coordinatorLayout.getMeasuredWidth() -80) ,RelativeLayout.LayoutParams.WRAP_CONTENT);

        Button btnClose = (Button)popupView.findViewById(R.id.button_close_popup);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
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

}
