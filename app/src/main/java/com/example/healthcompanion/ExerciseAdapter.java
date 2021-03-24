package com.example.healthcompanion;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder> {

    private List<Exercise> exerciseList;
    private LayoutInflater layoutInflater;
    private Context context;
    private StartTimeEndTimeAdapter startTimeEndTimeAdapter;

    public ExerciseAdapter(Context context , List<Exercise> exerciseList )
    {
        layoutInflater =LayoutInflater.from(context);
        this.exerciseList = exerciseList;
        this.context = context;
    }

    @NonNull
    @Override
    public ExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.exercise_adapter,parent,false);
        return new ExerciseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseViewHolder holder, int position) {
        holder.textView.setText(exerciseList.get(position).getDate());
        startTimeEndTimeAdapter = new StartTimeEndTimeAdapter(context,DataConverter.gettingListFromString(exerciseList.get(position).getStart_time()),DataConverter.gettingListFromString(exerciseList.get(position).getEnd_time()));
        holder.recyclerView.setAdapter(startTimeEndTimeAdapter);
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(context));
    }

    @Override
    public int getItemCount() {
        return exerciseList.size();
    }

    public class ExerciseViewHolder extends RecyclerView.ViewHolder
    {
        private RecyclerView recyclerView;
        private TextView textView;


        public ExerciseViewHolder(@NonNull View itemView) {

            super(itemView);
            textView = itemView.findViewById(R.id.textView);
            recyclerView = itemView.findViewById(R.id.recyclerView);
        }
    }
}
