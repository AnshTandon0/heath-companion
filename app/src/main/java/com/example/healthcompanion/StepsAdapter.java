package com.example.healthcompanion;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.DayWiseStepViewHolder> {

    private final List<Steps> steps;
    private LayoutInflater layoutInflater;
    private ItemClickListener itemClickListener;

    public StepsAdapter(Context context, List<Steps> steps, ItemClickListener itemClickListener)
    {
        this.layoutInflater = layoutInflater.from(context);
        this.steps = steps;
        this.itemClickListener = itemClickListener;

    }


    public class DayWiseStepViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        private TextView textView;
        private  ItemClickListener itemClickListener;
        public DayWiseStepViewHolder( View itemView , ItemClickListener itemClickListener) {
            super(itemView);
            this.itemClickListener = itemClickListener;
            this.textView = itemView.findViewById(R.id.textView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            itemClickListener.OnItemClick(getAdapterPosition());
        }
    }
    @NonNull
    @Override
    public DayWiseStepViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = layoutInflater.inflate(R.layout.day_wise_step,parent,false);
        return new DayWiseStepViewHolder(mView , itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull DayWiseStepViewHolder holder, int position) {

        holder.textView.setText(steps.get(position).getDate() + "  :-  " + steps.get(position).getTotalSteps());

    }

    @Override
    public int getItemCount() {
        return steps.size();
    }

    public interface ItemClickListener
    {
        void OnItemClick( int position );
    }

}
