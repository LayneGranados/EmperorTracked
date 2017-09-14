package com.emperor_tracare.emperortracked.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.transition.Explode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.emperor_tracare.emperortracked.PersonTrackedContainerActivity;
import com.emperor_tracare.emperortracked.R;
import com.emperor_tracare.emperortracked.model.Person;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class PeopleTrackedAdapterRecyclerView extends RecyclerView.Adapter<PeopleTrackedAdapterRecyclerView.PeopleTrackedViewHolder>{

    private ArrayList<Person> persons;
    private int resource;
    private Activity activity;

    public PeopleTrackedAdapterRecyclerView(ArrayList<Person> persons, int resource, Activity activity) {
        this.persons = persons;
        this.resource = resource;
        this.activity = activity;
    }

    @Override
    public PeopleTrackedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);
        return new PeopleTrackedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PeopleTrackedViewHolder holder, int position) {
        final Person person = persons.get(position);
        holder.nombre.setText(person.getNombre());
        holder.edad.setText(String.valueOf(person.getEdad()));
        holder.profileImage.setImageResource(Person.findImageByUsername(person.getUsuario()));

    }

    @Override
    public int getItemCount() {
        return persons.size();
    }

    public class PeopleTrackedViewHolder extends RecyclerView.ViewHolder {

        private TextView nombre;
        private TextView edad;
        private CircleImageView profileImage;
        private LinearLayout linearLayout;

        public PeopleTrackedViewHolder(View itemView) {
            super(itemView);
            nombre = (TextView)itemView.findViewById(R.id.textView_name_cardview);
            edad = (TextView)itemView.findViewById(R.id.textView_text_age_cardview);
            profileImage = (CircleImageView)itemView.findViewById(R.id.image_cardview_item_list_People);
            linearLayout = (LinearLayout)itemView.findViewById(R.id.layout_people_tracked_item);
        }
    }

}
