package com.example.healthcompanion;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SleepAdapter extends RecyclerView.Adapter<SleepAdapter.SleepViewHolder> {

    List<Sleep> sleeps;
    LayoutInflater layoutInflater;

    public SleepAdapter( Context context , List<Sleep> sleeps)
    {
        this.sleeps = sleeps;
        this.layoutInflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public SleepViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.recycler_view_sleep,parent,false);
        return new SleepViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SleepViewHolder holder, int position) {

        holder.status.setText(" Status  -  " + sleeps.get(position).getStatus());
        holder.date.setText(" Date  -  " + sleeps.get(position).getDate());
        holder.time.setText(" Time  -  " + sleeps.get(position).getStartTime() + " -- " + sleeps.get(position).getEndTime());
    }

    @Override
    public int getItemCount() {
        return sleeps.size();
    }

    public class SleepViewHolder extends RecyclerView.ViewHolder
    {
        TextView date , time , status ;

        public SleepViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.date);
            time = itemView.findViewById(R.id.time);
            status = itemView.findViewById(R.id.status);
        }
    }

}
