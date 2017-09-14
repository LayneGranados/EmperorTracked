package com.emperor_tracare.emperortracked;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.emperor_tracare.emperortracked.model.MyApplication;
import com.emperor_tracare.emperortracked.model.Person;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    PopupWindow popupWindow;
    View popupView;
    int mCurrentX,mCurrentY;
    CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((MyApplication)this.getApplication()).setPeople(null);
        ((MyApplication)this.getApplication()).setPersonSigned(null);
        ((MyApplication)this.getApplication()).setAlarms(null);

        Button login = (Button)findViewById(R.id.login);
        LinearLayout signUp = (LinearLayout)findViewById(R.id.linearLayout_sign_up);
        coordinatorLayout = (CoordinatorLayout)this.findViewById(R.id.coordinator_main_activity);
        final ArrayList<Person> people = this.buildPeople();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            TextInputEditText username = (TextInputEditText)findViewById(R.id.username);
            TextInputEditText password = (TextInputEditText)findViewById(R.id.password);
            if(username != null && username.equals("")){
                openPopUpLoginIncorrect(v);
            } else if (password != null && username.equals("")){
                openPopUpLoginIncorrect(v);
            } else {
                Person person = MyApplication.findPersonByUsername(people, username.getText().toString());
                if(person == null) {
                    openPopUpLoginIncorrect(v);
                } else if (!password.getText().toString().equals(person.getPassword())) {
                    openPopUpLoginIncorrect(v);
                } else {
                    Intent intent = new Intent(getApplicationContext(), NavigationDrawerActivity.class);
                    intent.putExtra("username",person.getUsuario());
                    startActivity(intent);
                }
            }
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(intent);
            }
        });
    }

    private ArrayList<Person> buildPeople(){
        Person marieAdams = new Person("Marie Adams", "marie.adams", "12345","marie.adams@gmail.com","marie_adams.jpg", 32, "August 21, 1985", "1.75mt", 69, "bveBcy4");
        Person claireAdams = new Person("Claire Adams", "claire.adams", "12345","claire.adams@gmail.com","claire_adams.jpg", 9, "May 2, 2008", "1mt", 38, "tsCCEGM");
        Person jhonBennet = new Person("Jhon Bennet", "jhon.bennet", "12345","jhon.bennet@gmail.com","jhon_bennet.jpg", 76, "January 9, 1941", "1.8mt", 70, "7SPiGU4");
        Person annaAdams = new Person("Anna Adams", "anna.adams", "12345","anna.adams@gmail.com","anna_adams.jpg", 29, "September 10, 1988", "1.63mt", 50, "fg3uqdr");
        Person christMcHill = new Person("Christ McHill", "christ.mchill", "12345","christ.mchill@gmail.com","christ_mchill.jpg", 79, "May 21, 1938", "1.8mt", 75, "2ysk85h");
        Person walterAdams = new Person("Walter Adams", "walter.adams", "12345","walter.adams@gmail.com","walter_adams.jpg", 70, "October 24, 1946", "1.72mt", 75, "7g2PB5c");
        Person julesAdams = new Person("Jules Adams", "jules.adams", "12345","jules.adams@gmail.com","jules_adams.png", 65, "March 7, 1951", "1.68mt", 65, "IzaS5yA");
        ArrayList<Person> people = new ArrayList<>();
        people.add(marieAdams);
        people.add(claireAdams);
        people.add(jhonBennet);
        people.add(annaAdams);
        people.add(christMcHill);
        people.add(walterAdams);
        people.add(julesAdams);

        ((MyApplication)this.getApplication()).setPeople(people);
        return people;
    }


    private void openPopUpLoginIncorrect(View view){

        popupView = getLayoutInflater().inflate(R.layout.popup_user_or_password_incorrect, null);
        popupWindow = new PopupWindow(popupView, (coordinatorLayout.getMeasuredWidth() - 80) , RelativeLayout.LayoutParams.WRAP_CONTENT);

        Button btnAccept = (Button)popupView.findViewById(R.id.button_accept_popup);
        btnAccept.setOnClickListener(new View.OnClickListener() {
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

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
}
