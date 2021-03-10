package com.example.healthcompanion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Pedometer extends AppCompatActivity implements SensorEventListener {

    private SimpleDateFormat dateFormat;
    private String date;
    private Calendar calender;
    private SensorManager sensorManager;
    private Sensor sensor;
    private TextView distanceTotal, stepsTotal,distanceWalk,distanceRun;
    private DayWiseStepDatabase dayWiseStepDatabase;
    private DayWiseStepsDao dayWiseStepsDao;
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


        // calendar , date format and date inialized
        calender = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        date = dateFormat.format(calender.getTime());

        // database initialized
        dayWiseStepDatabase = DayWiseStepDatabase.getInstance(this);
        dayWiseStepsDao = dayWiseStepDatabase.dayWiseStepsDao();

        //shared prefrences initialized
        sharedPreferences = getSharedPreferences("steps",MODE_PRIVATE);
        editor = sharedPreferences.edit();

        //sensor and sensor manager initialized
        sensorManager = (SensorManager)this.getSystemService(Context.SENSOR_SERVICE);
        if(sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR) != null)
        { sensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);
            sensorManager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_FASTEST); }
        else
        { Toast.makeText(this, "Step Detector Sensor not Present", Toast.LENGTH_LONG).show(); }


        initialize();

        // set text for textView
        distanceTotal.setText(String.format("%.4f",dayWiseStepsDao.select(date).getTotalDistance()) + " km");
        distanceWalk.setText(String.format("%.4f",dayWiseStepsDao.select(date).getDistanceWalk()) + " km");
        distanceRun.setText(String.format("%.4f",dayWiseStepsDao.select(date).getDistanceRan()) + " km");
        stepsTotal.setText(String.valueOf(dayWiseStepsDao.select(date).getTotalSteps()));

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        update();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

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

    // setting the values steps and distance
    public void update()
    {
        date = dateFormat.format(calender.getTime());
        if(sharedPreferences.getString("current date","").equalsIgnoreCase(date)) {
            DayWiseStep dayWiseStep = dayWiseStepsDao.select(date);
            if (sharedPreferences.getString("current mode", "").equalsIgnoreCase("walk")) {
                dayWiseStepsDao.update(new DayWiseStep(date, dayWiseStep.getStepsWalk() + 1, dayWiseStep.getDistanceWalk() + 0.0008, dayWiseStep.getStepsRan(), dayWiseStep.getDistanceRan(), dayWiseStep.getTotalSteps() + 1, dayWiseStep.getTotalDistance() + 0.0008));
            }

            if (sharedPreferences.getString("current mode", "").equalsIgnoreCase("run")) {
                dayWiseStepsDao.update(new DayWiseStep(date, dayWiseStep.getStepsWalk(), dayWiseStep.getDistanceWalk(), dayWiseStep.getStepsRan() + 1, dayWiseStep.getDistanceRan() + 0.0008, dayWiseStep.getTotalSteps() + 1, dayWiseStep.getTotalDistance() + 0.0008));
            }
            distanceTotal.setText(String.format("%.4f", dayWiseStepsDao.select(date).getTotalDistance()) + " km");
            stepsTotal.setText(String.valueOf(dayWiseStepsDao.select(date).getTotalSteps()));
            distanceWalk.setText(String.format("%.4f", dayWiseStepsDao.select(date).getDistanceWalk()) + " km");
            distanceRun.setText(String.format("%.4f", dayWiseStepsDao.select(date).getDistanceRan()) + " km");
        }
        else
        {
            dayWiseStepsDao.insert(new DayWiseStep(date,0,0.0,0,0.0,0,0.0));
            editor.putString("current date",date);
            editor.putString("current mode","walk");
            editor.commit();
        }
    }

        // TODO to add async task for insert , update , delete.
    // get the current date and initialize it in the database
    public void initialize()
    {
        dayWiseStepsDao.insert(new DayWiseStep(date,0,0.0,0,0.0,0,0.0));
        editor.putString("current date",date);
        editor.putString("current mode","walk");
        editor.commit();
    }

    public void showAll(View view)
    {
        Intent intent = new Intent(this, AllDaysSteps.class);
        startActivity(intent);
        finish();
    }
}