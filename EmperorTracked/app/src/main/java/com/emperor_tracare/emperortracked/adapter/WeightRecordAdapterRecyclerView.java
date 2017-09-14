package com.emperor_tracare.emperortracked.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.emperor_tracare.emperortracked.R;
import com.emperor_tracare.emperortracked.model.WeightRecord;

import java.util.ArrayList;


public class WeightRecordAdapterRecyclerView extends RecyclerView.Adapter<WeightRecordAdapterRecyclerView.WeightRecordsViewHolder>{

    private ArrayList<WeightRecord> weightRecords;
    private int resource;

    public WeightRecordAdapterRecyclerView(ArrayList<WeightRecord> weightRecords, int resource) {
        this.weightRecords = weightRecords;
        this.resource = resource;
    }

    @Override
    public WeightRecordAdapterRecyclerView.WeightRecordsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);
        return new WeightRecordAdapterRecyclerView.WeightRecordsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final WeightRecordAdapterRecyclerView.WeightRecordsViewHolder holder, int position) {
        WeightRecord weightRecord = weightRecords.get(position);
        holder.day.setText(weightRecord.getDay());
        holder.month.setText(weightRecord.getMonth());
        holder.value.setText(weightRecord.getValue());
        holder.collapse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(holder.collapsible.getVisibility()==View.VISIBLE) {
                   holder.collapse.setImageResource(R.drawable.ic_expand_more_black);
                   holder.collapsible.setVisibility(View.GONE);
                   //holder.cardView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, R.dimen.height_cardview_weight_expanded_records));
                   //holder.cardView.setMinimumHeight(70);
               } else if(holder.collapsible.getVisibility()==View.GONE) {
                   holder.collapse.setImageResource(R.drawable.ic_expand_less_black);
                   holder.collapsible.setVisibility(View.VISIBLE);
                   //holder.cardView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, R.dimen.height_cardview_weight_collapsed_records));
                   //holder.cardView.setMinimumHeight(70);
               }
            }
        });
    }

    @Override
    public int getItemCount() {
        return weightRecords.size();
    }

    public class WeightRecordsViewHolder extends RecyclerView.ViewHolder {

        private TextView day;
        private TextView month;
        private TextView value;
        private LinearLayout collapsible;
        private ImageView collapse;
        private CardView cardView;


        public WeightRecordsViewHolder(View itemView) {
            super(itemView);

            day = (TextView)itemView.findViewById(R.id.textView_day_weight_record);
            month = (TextView)itemView.findViewById(R.id.textView_month_weight_record);
            value = (TextView)itemView.findViewById(R.id.textView_value_weight_record);
            collapsible = (LinearLayout)itemView.findViewById(R.id.linear_layout_collapsible_weight_item);
            collapse = (ImageView)itemView.findViewById(R.id.imageview_collapse_expand_item_weight);
            cardView = (CardView)itemView.findViewById(R.id.carview_item_list_weight_record);
        }
    }
}
