package com.emperor_tracare.emperortracked;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.emperor_tracare.emperortracked.fragments.AccountFragment;
import com.emperor_tracare.emperortracked.fragments.ListTrackersFragment;
import com.emperor_tracare.emperortracked.model.MyApplication;
import com.emperor_tracare.emperortracked.model.Person;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class NavigationDrawerActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout mDrawer;
    private String username;
    private View header;
    private Person person;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        header = navigationView.getHeaderView(0);
        username = getIntent().getStringExtra("username");
        ArrayList<Person> people = ((MyApplication)getApplication()).getPeople();
        for(Person p: people) {
            if(p.getUsuario().equals(username)) {
                person = p;
                CircleImageView circleImageView = (CircleImageView)header.findViewById(R.id.profile_image);
                TextView usernameMenu = (TextView)header.findViewById(R.id.menu_username);
                usernameMenu.setText(p.getNombre());
                circleImageView.setImageResource(Person.findImageByUsername(p.getUsuario()));

                ((MyApplication)getApplication()).setPersonSigned(p);
                break;
            }
        }

        LinearLayout linearLayoutLogOut = (LinearLayout)navigationView.findViewById(R.id.linear_layout_log_out);
        linearLayoutLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

            }
        });

        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        changeToFragment(new AccountFragment(), R.id.content_navigation_drawer);
    }

    @Override
    public void onBackPressed() {
        System.out.println("se presiono el botón de atras");
        /*mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (mDrawer.isDrawerOpen(GravityCompat.START)) {
            mDrawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_por_revisar) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_my_stats) {
            changeToFragment(new AccountFragment(), R.id.content_navigation_drawer);
        } else if (id == R.id.nav_who_is_tracking_me) {
            changeToFragment(new ListTrackersFragment(), R.id.content_navigation_drawer);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void changeToFragment(Fragment fragment, int container){
        getSupportFragmentManager().beginTransaction().replace(container, fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .addToBackStack(null).commit();

    }

    @Override
    protected void onResume() {
        super.onResume();
        Person person = ((MyApplication)getApplication()).getPersonSigned();
        CircleImageView circleImageView = (CircleImageView)header.findViewById(R.id.profile_image);
        TextView usernameMenu = (TextView)header.findViewById(R.id.menu_username);
        usernameMenu.setText(person.getNombre());
        circleImageView.setImageResource(Person.findImageByUsername(person.getUsuario()));
    }



}
