package com.example.healthcompanion;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

public class IndividualDayDisplay extends AppCompatActivity {

    private TextView StepsTotal,DistanceTotal,StepsWalk,DistanceWalk,StepsRan,DistanceRan;
    private Steps dayWiseSteps;
    private StepsRepository stepsRepository;

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


        stepsRepository = new StepsRepository(this);


        try {
            dayWiseSteps = stepsRepository.search(getIntent().getStringExtra("date"));
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        StepsTotal.setText(String.valueOf(dayWiseSteps.getTotalSteps()));
        StepsWalk.setText(String.valueOf(dayWiseSteps.getStepsWalk()));
        StepsRan.setText(String.valueOf(dayWiseSteps.getStepsRan()));
        DistanceTotal.setText(String.format("%.4f",dayWiseSteps.getTotalDistance()) + " km");
        DistanceWalk.setText(String.format("%.4f",dayWiseSteps.getDistanceWalk()) + " km");
        DistanceRan.setText(String.format("%.4f",dayWiseSteps.getDistanceRan()) + " km");

    }
}