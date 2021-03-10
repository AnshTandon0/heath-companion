package com.example.healthcompanion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.List;

public class AllDaysSteps extends AppCompatActivity implements DayWiseStepAdapter.ItemClickListener {

    private RecyclerView recyclerView;
    private DayWiseStepAdapter dayWiseStepAdapter;
    private List<DayWiseStep> dayWiseSteps;
    private DayWiseStepDatabase dayWiseStepDatabase;
    private DayWiseStepsDao dayWiseStepsDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_days_steps);
        dayWiseStepDatabase = DayWiseStepDatabase.getInstance(this);
        dayWiseStepsDao = dayWiseStepDatabase.dayWiseStepsDao();
        dayWiseSteps = dayWiseStepsDao.selectAll();
        recyclerView = findViewById(R.id.recyclerView);
        dayWiseStepAdapter = new DayWiseStepAdapter(this,dayWiseSteps,this);
        recyclerView.setAdapter(dayWiseStepAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void OnItemClick(int position) {
        Intent intent = new Intent(this,IndividualDayDisplay.class);
        intent.putExtra("date",dayWiseStepsDao.selectAll().get(position).getDate());
        startActivity(intent);
    }
}