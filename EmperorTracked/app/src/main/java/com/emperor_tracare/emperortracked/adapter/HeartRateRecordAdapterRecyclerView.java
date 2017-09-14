package com.emperor_tracare.emperortracked.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.emperor_tracare.emperortracked.R;
import com.emperor_tracare.emperortracked.model.HeartRateRecord;

import java.util.ArrayList;


public class HeartRateRecordAdapterRecyclerView extends RecyclerView.Adapter<HeartRateRecordAdapterRecyclerView.HeartRateRecordsViewHolder>{

    private ArrayList<HeartRateRecord> heartRateRecords;
    private int resource;

    public HeartRateRecordAdapterRecyclerView(ArrayList<HeartRateRecord> heartRateRecords, int resource) {
        this.heartRateRecords = heartRateRecords;
        this.resource = resource;
    }

    @Override
    public HeartRateRecordAdapterRecyclerView.HeartRateRecordsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);
        return new HeartRateRecordAdapterRecyclerView.HeartRateRecordsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HeartRateRecordAdapterRecyclerView.HeartRateRecordsViewHolder holder, int position) {
        HeartRateRecord heartRateRecord = heartRateRecords.get(position);
        holder.day.setText(heartRateRecord.getDay());
        holder.month.setText(heartRateRecord.getMonth());
        holder.description.setText(heartRateRecord.getDescription());
        holder.value.setText(heartRateRecord.getValue());
    }

    @Override
    public int getItemCount() {
        return heartRateRecords.size();
    }

    public class HeartRateRecordsViewHolder extends RecyclerView.ViewHolder {

        private TextView day;
        private TextView month;
        private TextView description;
        private TextView value;

        public HeartRateRecordsViewHolder(View itemView) {
            super(itemView);

            day = (TextView)itemView.findViewById(R.id.textView_day_heart_rate_record);
            month = (TextView)itemView.findViewById(R.id.textView_month_heart_rate_record);
            description = (TextView)itemView.findViewById(R.id.textView_description_heart_rate_record);
            value = (TextView)itemView.findViewById(R.id.textView_value_heart_rate_record);
        }
    }
}
