package com.emperor_tracare.emperortracked.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.emperor_tracare.emperortracked.R;
import com.emperor_tracare.emperortracked.model.MyApplication;
import com.emperor_tracare.emperortracked.model.Person;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFragment extends Fragment {

    public ProfileFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_profile, container, false);
        CircleImageView profileImage = (CircleImageView)view.findViewById(R.id.image_person_tracked_profile_information);
        TextView name = (TextView) view.findViewById(R.id.textView_username_person_tracked_profile_information);
        TextView age = (TextView)view.findViewById(R.id.textView_age_person_tracked_profile_information);
        TextView birdthday = (TextView)view.findViewById(R.id.textView_birthday_person_tracked_profile_information);
        TextView heigth = (TextView)view.findViewById(R.id.textView_heigth_person_tracked_profile_information);
        TextView weigth = (TextView)view.findViewById(R.id.textView_weigth_person_tracked_profile_information);
        TextView hashCode = (TextView)view.findViewById(R.id.textView_link_hask_code_person_tracked_profile_information);
        Person person = ((MyApplication)getActivity().getApplication()).getPersonSelected();
        name.setText(person.getNombre());
        age.setText(String.valueOf(person.getEdad()));
        birdthday.setText(person.getFechaNacimiento());
        weigth.setText(String.valueOf(person.getKilos()));
        heigth.setText(person.getAltura());
        hashCode.setText(person.getHasCode());
        profileImage.setImageResource(Person.findImageByUsername(person.getUsuario()));
        return view;
    }

}
