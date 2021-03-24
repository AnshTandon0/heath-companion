package com.example.healthcompanion;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StartTimeEndTimeAdapter extends RecyclerView.Adapter<StartTimeEndTimeAdapter.StartTimeEndTimeViewHolder> {

    private LayoutInflater layoutInflater;
    private List<String> startTimeList;
    private List<String> endTimeList;

    public StartTimeEndTimeAdapter(Context context , List<String> startTimeList , List<String> endTimeList)
    {
        layoutInflater = LayoutInflater.from(context);
        this.startTimeList = startTimeList;
        this.endTimeList = endTimeList;
    }

    @NonNull
    @Override
    public StartTimeEndTimeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.start_time_end_time_adapter,parent,false);
        return new StartTimeEndTimeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull StartTimeEndTimeViewHolder holder, int position) {
        holder.textView.setText(startTimeList.get(position) + "    --     " + endTimeList.get(position));
    }

    @Override
    public int getItemCount() {
        return startTimeList.size();
    }

    public class StartTimeEndTimeViewHolder extends RecyclerView.ViewHolder{

        private TextView textView;

        public StartTimeEndTimeViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
        }
    }
}



