package com.example.healthcompanion;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.ExecutionException;

import static android.content.ContentValues.TAG;

public class Pedometer extends AppCompatActivity {

    private String date;
    private Calendar calender;
    private TextView distanceTotal, stepsTotal,distanceWalk,distanceRun;
    private StepsRepository stepsRepository;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedometer);

        //textView initialized
        stepsTotal = findViewById(R.id.stepsTotal);
        distanceTotal = findViewById(R.id.distanceTotal);
        distanceRun = findViewById(R.id.distanceRun);
        distanceWalk = findViewById(R.id.distanceWalk);


        // calendar , date format and date initialized
        calender = Calendar.getInstance();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        date = dateFormat.format(calender.getTime());

        // database initialized
       stepsRepository = new StepsRepository(this);

        //shared preferences initialized
        sharedPreferences = getSharedPreferences("steps",MODE_PRIVATE);
        editor = sharedPreferences.edit();

        // set text for textView
        // TODO to update the data as sensor is triggered
        try {
            if (stepsRepository.getAllSteps().size() > 0) {
                distanceTotal.setText(String.format("%.4f", stepsRepository.search(date).getTotalDistance()) + " km");
                distanceWalk.setText(String.format("%.4f", stepsRepository.search(date).getDistanceWalk()) + " km");
                distanceRun.setText(String.format("%.4f", stepsRepository.search(date).getDistanceRan()) + " km");
                stepsTotal.setText(String.valueOf(stepsRepository.search(date).getTotalSteps()));
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        Intent intent = new Intent(this,StepsService.class);
        startService(intent);
    }

    public void runningMode(View view)
    {
        Button button = findViewById(R.id.StartRunningMode);
        if (sharedPreferences.getString("current mode","").equalsIgnoreCase("walk"))
        {
            button.setText("Stop Running mode");
            editor.putString("current mode","run");
            editor.commit();
        }
       else if (sharedPreferences.getString("current mode","").equalsIgnoreCase("run"))
        {
            button.setText("Start Running mode");
            editor.putString("current mode","walk");
            editor.commit();
        }
    }


    public void showAll(View view)
    {
        Intent intent = new Intent(this, AllDaysSteps.class);
        startActivity(intent);
        finish();
    }
}
