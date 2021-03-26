package com.example.healthcompanion;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class WaterScheduleAdapter extends RecyclerView.Adapter<WaterScheduleAdapter.WaterViewHolder> {

    private LayoutInflater layoutInflater;
    private List<Water> waters = new ArrayList<>();
    private Context context;
    private Delete delete;

    public WaterScheduleAdapter(Context context , List<Water> waters , Delete delete)
    {
        layoutInflater = LayoutInflater.from(context);
        this.waters = waters;
        this.context = context;
        this.delete = delete;

    }

    @NonNull
    @Override
    public WaterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.water_schedule_recycler_view,parent,false);
        return new WaterViewHolder(itemView , delete);
    }

    @Override
    public void onBindViewHolder(@NonNull WaterViewHolder holder, int position) {
       holder.textView.setText(waters.get(position).getTime());

       holder.textView.setOnLongClickListener(new View.OnLongClickListener() {
           @Override
           public boolean onLongClick(View v) {

               AlertDialog.Builder alert = new AlertDialog.Builder(context)
                       .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialog, int which) {
                               AlertDialog.Builder alert2 = new AlertDialog.Builder(context)
                                       .setTitle("Are you sure ?")
                                       .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                           @Override
                                           public void onClick(DialogInterface dialog, int which) {
                                               delete.DeleteWaterSchedule(position);
                                           }
                                       })
                                       .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                           @Override
                                           public void onClick(DialogInterface dialog, int which) {

                                           }
                                       });
                               alert2.show();
                           }
                       });
               alert.show();
               return false;
           }
       });
    }

    @Override
    public int getItemCount() {
        return waters.size();
    }

    public class WaterViewHolder extends RecyclerView.ViewHolder {

        private TextView textView ;
        private  Delete delete;
        public WaterViewHolder(@NonNull View itemView , Delete delete) {
            super(itemView);
            textView = itemView.findViewById(R.id.textViewWater);
            this.delete = delete;
        }
    }

    public interface Delete
    {
        public void DeleteWaterSchedule( int position );
    }
}
