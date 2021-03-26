package com.example.healthcompanion;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class WaterSchedule extends AppCompatActivity implements WaterScheduleAdapter.Delete {

    private WaterRepository waterRepository;
    private WaterScheduleAdapter waterScheduleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_schedule);
        waterRepository = new WaterRepository(this);
        if (waterRepository.SelectAllWater().size() > 0) {
            RecyclerView recyclerView = findViewById(R.id.recyclerViewWater);
            waterScheduleAdapter = new WaterScheduleAdapter(WaterSchedule.this, waterRepository.SelectAllWater(),this);
            recyclerView.setAdapter(waterScheduleAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(WaterSchedule.this));
        }
    }

    public void addSchedule(View view)
    {
        final EditText editText = new EditText(this);
        editText.setHint(" HH:MM:SS ");
        AlertDialog.Builder alert = new AlertDialog.Builder(WaterSchedule.this)
                .setTitle("SCHEDULE")
                .setMessage("Enter the Time ( 24 hrs format )")
                .setView(editText)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        waterRepository.InsertWater(new Water(editText.getText().toString()));
                        RecyclerView recyclerView = findViewById(R.id.recyclerViewWater);
                        waterScheduleAdapter = new WaterScheduleAdapter(WaterSchedule.this,waterRepository.SelectAllWater(),WaterSchedule.this::DeleteWaterSchedule);
                        recyclerView.setAdapter(waterScheduleAdapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(WaterSchedule.this));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        alert.show();
    }

    @Override
    public void DeleteWaterSchedule(int position) {
        waterRepository.DeleteWater(waterRepository.SelectAllWater().get(position));
        RecyclerView recyclerView = findViewById(R.id.recyclerViewWater);
        waterScheduleAdapter = new WaterScheduleAdapter(WaterSchedule.this,waterRepository.SelectAllWater(),WaterSchedule.this::DeleteWaterSchedule);
        recyclerView.setAdapter(waterScheduleAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(WaterSchedule.this));
    }
}