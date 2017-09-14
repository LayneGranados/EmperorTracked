package com.emperor_tracare.emperortracked;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;

import com.emperor_tracare.emperortracked.fragments.AlarmsFragment;
import com.emperor_tracare.emperortracked.fragments.HealthFragment;
import com.emperor_tracare.emperortracked.fragments.GoogleMapFragment;
import com.emperor_tracare.emperortracked.fragments.ProfileFragment;
import com.emperor_tracare.emperortracked.model.MyApplication;
import com.emperor_tracare.emperortracked.model.Person;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import java.util.ArrayList;

public class PersonTrackedContainerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_tracked_container);
        showToolbar("List of People Tracked", true);
        Intent myIntent = getIntent(); // gets the previously created intent
        String fragment = myIntent.getStringExtra("fragment");
        final String username = myIntent.getStringExtra("username");
        if(username != null && !username.isEmpty()) {
            ArrayList<Person> people = ((MyApplication)getApplication()).getPeople();
            final Person person = MyApplication.findPersonByUsername(people, username);
            ((MyApplication)getApplication()).setPersonSelected(person);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            getWindow().setEnterTransition(new Fade());
        }

        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottombar_person_tracked);
        bottomBar.setDefaultTab(R.id.tabs_profile);
        bottomBar.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                switch (tabId){
                    case R.id.tabs_profile:
                        ProfileFragment profileFragment = new ProfileFragment();
                        changeToFragment(profileFragment, R.id.container_person_tracked_detail);
                        getSupportActionBar().setTitle("Person Tracked");
                        break;
                    case R.id.tabs_health:
                        HealthFragment healthFragment = new HealthFragment();
                        changeToFragment(healthFragment, R.id.container_person_tracked_detail);
                        getSupportActionBar().setTitle("Person Tracked");
                        break;
                    case R.id.tabs_alarms:
                        AlarmsFragment alarmsFragment = new AlarmsFragment();
                        changeToFragment(alarmsFragment, R.id.container_person_tracked_detail);
                        getSupportActionBar().setTitle("Person Tracked");
                        break;
                }
            }
        });
        String fragmentExp = ((MyApplication)this.getApplication()).getFragment();
        if("alarms".equalsIgnoreCase(fragment)) {
            changeToFragment(new AlarmsFragment(), R.id.container_person_tracked_detail);
            getSupportActionBar().setTitle("Person Tracked");
        } else if("heart".equals(fragmentExp)) {
            changeToFragment(new HealthFragment(), R.id.container_person_tracked_detail);
            getSupportActionBar().setTitle("Person Tracked");
        } else if("sleep".equals(fragmentExp)) {
            changeToFragment(new HealthFragment(), R.id.container_person_tracked_detail);
            getSupportActionBar().setTitle("Person Tracked");
        } else if("step".equals(fragmentExp)) {
            changeToFragment(new HealthFragment(), R.id.container_person_tracked_detail);
            getSupportActionBar().setTitle("Person Tracked");
        } else if("weight".equals(fragmentExp)) {
            changeToFragment(new HealthFragment(), R.id.container_person_tracked_detail);
            getSupportActionBar().setTitle("Person Tracked");
        } else if("fisical".equals(fragmentExp)) {
            changeToFragment(new HealthFragment(), R.id.container_person_tracked_detail);
            getSupportActionBar().setTitle("Person Tracked");
        }
    }

    public void showToolbar(String tittle, boolean upButton){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(tittle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }

    @Override
    public void onBackPressed() {
        System.out.println("entro");
    }

    private void changeToFragment(Fragment fragment, int container){
        Bundle bundle=new Bundle();
        fragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(container, fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .addToBackStack(null).commit();
    }

}
