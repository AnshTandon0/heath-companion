package com.example.healthcompanion;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class IndividualDayDisplay extends AppCompatActivity {

    private TextView StepsTotal,DistanceTotal,StepsWalk,DistanceWalk,StepsRan,DistanceRan;
    private DayWiseStep dayWiseSteps;
    private DayWiseStepDatabase dayWiseStepDatabase;
    private DayWiseStepsDao dayWiseStepsDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_day_display);
        StepsTotal = findViewById(R.id.stepsTotal);
        StepsRan = findViewById(R.id.StepsRun);
        StepsWalk = findViewById(R.id.StepsWalk);
        DistanceTotal = findViewById(R.id.distanceTotal);
        DistanceRan = findViewById(R.id.distanceRun);
        DistanceWalk = findViewById(R.id.distanceWalk);
        dayWiseStepDatabase = DayWiseStepDatabase.getInstance(this);
        dayWiseStepsDao = dayWiseStepDatabase.dayWiseStepsDao();
        dayWiseSteps = dayWiseStepsDao.select(getIntent().getStringExtra("date"));
        StepsTotal.setText(String.valueOf(dayWiseSteps.getTotalSteps()));
        StepsWalk.setText(String.valueOf(dayWiseSteps.getStepsWalk()));
        StepsRan.setText(String.valueOf(dayWiseSteps.getStepsRan()));
        DistanceTotal.setText(String.format("%.4f",dayWiseSteps.getTotalDistance()) + " km");
        DistanceWalk.setText(String.format("%.4f",dayWiseSteps.getDistanceWalk()) + " km");
        DistanceRan.setText(String.format("%.4f",dayWiseSteps.getDistanceRan()) + " km");

    }
}