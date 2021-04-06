package com.example.healthcompanion;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class WaterActivityAdapter extends RecyclerView.Adapter<WaterActivityAdapter.WaterActivityViewHolder> {

    private LayoutInflater layoutInflater;
    private List<WaterRecords> waterRecords;

    public WaterActivityAdapter(Context context , List<WaterRecords> waterRecords)
    {
        this.layoutInflater = LayoutInflater.from(context);
        this.waterRecords = waterRecords;
    }
    @NonNull
    @Override
    public WaterActivityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = layoutInflater.inflate(R.layout.recycler_view_water_activity,parent,false);
        return new WaterActivityViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull WaterActivityViewHolder holder, int position) {

        holder.date.setText(waterRecords.get(position).getDate());
        holder.consumed.setText("Consumed  -  " + String.valueOf(waterRecords.get(position).getConsumed()));
        holder.missed.setText("Missed  -  " + String.valueOf(waterRecords.get(position).getMissed()));
    }

    @Override
    public int getItemCount() {
        return waterRecords.size();
    }

    public class WaterActivityViewHolder extends RecyclerView.ViewHolder {

        private TextView date , consumed , missed ;

        public WaterActivityViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.date);
            consumed = itemView.findViewById(R.id.consumed);
            missed = itemView.findViewById(R.id.missed);
        }
    }
}
