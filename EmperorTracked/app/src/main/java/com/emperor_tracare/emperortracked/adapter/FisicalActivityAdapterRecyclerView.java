package com.emperor_tracare.emperortracked.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.emperor_tracare.emperortracked.R;
import com.emperor_tracare.emperortracked.model.FisicalActivity;

import java.util.ArrayList;

public class FisicalActivityAdapterRecyclerView extends RecyclerView.Adapter<FisicalActivityAdapterRecyclerView.FisicalActivityViewHolder>{

    private ArrayList<FisicalActivity> fisicalActivities;
    private int resource;

    public FisicalActivityAdapterRecyclerView(ArrayList<FisicalActivity> fisicalActivities, int resource) {
        this.fisicalActivities = fisicalActivities;
        this.resource = resource;
    }

    @Override
    public FisicalActivityAdapterRecyclerView.FisicalActivityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);
        return new FisicalActivityAdapterRecyclerView.FisicalActivityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FisicalActivityAdapterRecyclerView.FisicalActivityViewHolder holder, int position) {
        FisicalActivity fisicalActivity = fisicalActivities.get(position);
        holder.day.setText(fisicalActivity.getDay());
        holder.month.setText(fisicalActivity.getMonth());
        holder.calories.setText(fisicalActivity.getCalories());
        holder.time.setText(fisicalActivity.getTime());
        holder.type_value.setText(fisicalActivity.getType());
        if(fisicalActivity.getType().equalsIgnoreCase("RUN")){
            holder.type.setImageResource(R.drawable.ic_running_black);
        } else if (fisicalActivity.getType().equalsIgnoreCase("SWIM")) {
            holder.type.setImageResource(R.drawable.ic_swimming_black);
        } else if (fisicalActivity.getType().equalsIgnoreCase("PLAY")) {
            holder.type.setImageResource(R.drawable.ic_play_basketball_black);
        } else if (fisicalActivity.getType().equalsIgnoreCase("BIKE")) {
            holder.type.setImageResource(R.drawable.ic_bike_black);
        }
    }

    @Override
    public int getItemCount() {
        return fisicalActivities.size();
    }

    public class FisicalActivityViewHolder extends RecyclerView.ViewHolder {

        private TextView day;
        private TextView month;
        private TextView time;
        private TextView calories;
        private TextView type_value;
        private ImageView type;

        public FisicalActivityViewHolder(View itemView) {
            super(itemView);

            day = (TextView)itemView.findViewById(R.id.textView_day_fisical_activity);
            month = (TextView)itemView.findViewById(R.id.textView_month_fisical_activity);
            time = (TextView)itemView.findViewById(R.id.textView_value_time_fisical_activities);
            calories = (TextView)itemView.findViewById(R.id.textView_calories_burned_fisical_activity);
            type = (ImageView)itemView.findViewById(R.id.imageview_type_item_fisical_activity);
            type_value = (TextView)itemView.findViewById(R.id.textView_type_item_fisical_activity);
        }
    }
}
