package com.emperor_tracare.emperortracked.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.emperor_tracare.emperortracked.R;
import com.emperor_tracare.emperortracked.model.SleepRecord;

import java.util.ArrayList;


public class SleepRecordAdapterRecyclerView extends RecyclerView.Adapter<SleepRecordAdapterRecyclerView.SleepRecordsViewHolder>{

    private ArrayList<SleepRecord> sleepRecords;
    private int resource;

    public SleepRecordAdapterRecyclerView(ArrayList<SleepRecord> sleepRecords, int resource) {
        this.sleepRecords = sleepRecords;
        this.resource = resource;
    }

    @Override
    public SleepRecordAdapterRecyclerView.SleepRecordsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);
        return new SleepRecordAdapterRecyclerView.SleepRecordsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SleepRecordAdapterRecyclerView.SleepRecordsViewHolder holder, int position) {
        SleepRecord sleepRecord = sleepRecords.get(position);
        holder.day.setText(sleepRecord.getDay());
        holder.month.setText(sleepRecord.getMonth());
        holder.description.setText(sleepRecord.getDescription());
        holder.hour.setText(sleepRecord.getHours());
        holder.minute.setText(sleepRecord.getMinutes());
    }

    @Override
    public int getItemCount() {
        return sleepRecords.size();
    }

    public class SleepRecordsViewHolder extends RecyclerView.ViewHolder {

        private TextView day;
        private TextView month;
        private TextView description;
        private TextView hour;
        private TextView minute;


        public SleepRecordsViewHolder(View itemView) {
            super(itemView);

            day = (TextView)itemView.findViewById(R.id.textView_day_sleep_record);
            month = (TextView)itemView.findViewById(R.id.textView_month_sleep_record);
            description = (TextView)itemView.findViewById(R.id.textView_description_sleep_record);
            hour = (TextView)itemView.findViewById(R.id.textView_hour_sleep_record);
            minute = (TextView)itemView.findViewById(R.id.textView_minute_sleep_record);
        }
    }
}
