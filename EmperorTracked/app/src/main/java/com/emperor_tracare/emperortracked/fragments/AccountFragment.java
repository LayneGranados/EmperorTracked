package com.emperor_tracare.emperortracked.fragments;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.transition.Explode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.emperor_tracare.emperortracked.PersonTrackedContainerActivity;
import com.emperor_tracare.emperortracked.R;
import com.emperor_tracare.emperortracked.model.MyApplication;
import com.emperor_tracare.emperortracked.model.Person;

import de.hdodenhof.circleimageview.CircleImageView;


public class AccountFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        CircleImageView profileImage = (CircleImageView)view.findViewById(R.id.profile_image_edit_account);
        TextView name = (TextView) view.findViewById(R.id.textView_name_edit_account);
        final Person person = ((MyApplication)getActivity().getApplication()).getPersonSigned();
        name.setText(person.getNombre());
        profileImage.setImageResource(Person.findImageByUsername(person.getUsuario()));

        Button buttonSeeMyStats = (Button)view.findViewById(R.id.button_see_my_stats);
        buttonSeeMyStats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PersonTrackedContainerActivity.class);
                intent.putExtra("username",person.getUsuario());
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                    Explode explode = new Explode();
                    explode.setDuration(1000);
                    getActivity().getWindow().setExitTransition(explode);
                    getActivity().startActivity(intent,
                            ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), view, getActivity().getString(R.string.app_name)).toBundle());

                }else {
                    getActivity().startActivity(intent);
                }
            }
        });



        return view;
    }


}
