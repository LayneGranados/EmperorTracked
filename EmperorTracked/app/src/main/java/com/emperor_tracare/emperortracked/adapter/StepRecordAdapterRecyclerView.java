package com.emperor_tracare.emperortracked.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.emperor_tracare.emperortracked.R;
import com.emperor_tracare.emperortracked.model.StepRecord;

import java.util.ArrayList;


public class StepRecordAdapterRecyclerView extends RecyclerView.Adapter<StepRecordAdapterRecyclerView.StepRecordsViewHolder>{

    private ArrayList<StepRecord> stepRecords;
    private int resource;

    public StepRecordAdapterRecyclerView(ArrayList<StepRecord> stepRecords, int resource) {
        this.stepRecords = stepRecords;
        this.resource = resource;
    }

    @Override
    public StepRecordAdapterRecyclerView.StepRecordsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);
        return new StepRecordAdapterRecyclerView.StepRecordsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StepRecordAdapterRecyclerView.StepRecordsViewHolder holder, int position) {
        StepRecord stepRecord = stepRecords.get(position);
        holder.day.setText(stepRecord.getDay());
        holder.month.setText(stepRecord.getMonth());
        holder.numberSteps.setText(stepRecord.getNumberSteps());
        holder.caloriesBurned.setText(stepRecord.getCaloriesBurned());
    }

    @Override
    public int getItemCount() {
        return stepRecords.size();
    }

    public class StepRecordsViewHolder extends RecyclerView.ViewHolder {

        private TextView day;
        private TextView month;
        private TextView numberSteps;
        private TextView caloriesBurned;

        public StepRecordsViewHolder(View itemView) {
            super(itemView);

            day = (TextView)itemView.findViewById(R.id.textView_day_step_record);
            month = (TextView)itemView.findViewById(R.id.textView_month_step_record);
            numberSteps = (TextView)itemView.findViewById(R.id.textView_number_steps_step_record);
            caloriesBurned = (TextView)itemView.findViewById(R.id.textView_calories_burned_step_record);
        }
    }
}
