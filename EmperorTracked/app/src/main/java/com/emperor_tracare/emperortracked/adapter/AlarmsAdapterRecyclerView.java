package com.emperor_tracare.emperortracked.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.emperor_tracare.emperortracked.R;
import com.emperor_tracare.emperortracked.model.Alarm;

import java.util.ArrayList;


public class AlarmsAdapterRecyclerView extends RecyclerView.Adapter<AlarmsAdapterRecyclerView.AlarmsViewHolder>{

    private ArrayList<Alarm> alarms;
    private int resource;
    private Activity activity;

    public AlarmsAdapterRecyclerView(ArrayList<Alarm> alarms, int resource, Activity activity) {
        this.alarms = alarms;
        this.resource = resource;
        this.activity = activity;
    }

    @Override
    public AlarmsAdapterRecyclerView.AlarmsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);
        return new AlarmsAdapterRecyclerView.AlarmsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AlarmsAdapterRecyclerView.AlarmsViewHolder holder, int position) {
        Alarm alarm = alarms.get(position);
        holder.name.setText(alarm.getName());
        holder.hourAlarm.setText(alarm.getHour()+":"+alarm.getMinutes());
        this.setVisibilityOfDay(holder.monday, alarm.isMonday());
        this.setVisibilityOfDay(holder.tuesday, alarm.isTuesday());
        this.setVisibilityOfDay(holder.wednesday, alarm.isWednesday());
        this.setVisibilityOfDay(holder.thursday, alarm.isThursday());
        this.setVisibilityOfDay(holder.friday, alarm.isFriday());
        this.setVisibilityOfDay(holder.saturday, alarm.isSaturday());
        this.setVisibilityOfDay(holder.sunday, alarm.isSunday());
    }

    @Override
    public int getItemCount() {
        return alarms.size();
    }

    public class AlarmsViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView monday;
        private TextView tuesday;
        private TextView wednesday;
        private TextView thursday;
        private TextView friday;
        private TextView saturday;
        private TextView sunday;
        private TextView hourAlarm;

        public AlarmsViewHolder(View itemView) {
            super(itemView);

            name = (TextView)itemView.findViewById(R.id.textview_alarm_name_item_list_alarms);
            monday = (TextView)itemView.findViewById(R.id.textView_monday_days_of_alarm);
            tuesday = (TextView)itemView.findViewById(R.id.textView_tuesday_days_of_alarm);
            wednesday = (TextView)itemView.findViewById(R.id.textView_wednesday_days_of_alarm);
            thursday = (TextView)itemView.findViewById(R.id.textView_thursday_days_of_alarm);
            friday = (TextView)itemView.findViewById(R.id.textView_friday_days_of_alarm);
            saturday = (TextView)itemView.findViewById(R.id.textView_saturday_days_of_alarm);
            sunday = (TextView)itemView.findViewById(R.id.textView_sunday_days_of_alarm);
            hourAlarm = (TextView)itemView.findViewById(R.id.textView_hour_number_alarm);
        }
    }

    private void setVisibilityOfDay(TextView day, boolean visibility) {
        if(visibility) {
            day.setVisibility(View.VISIBLE);
        } else {
            day.setVisibility(View.GONE);
        }

    }

}
