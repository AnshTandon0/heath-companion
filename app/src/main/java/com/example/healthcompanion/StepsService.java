package com.example.healthcompanion;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.ExecutionException;

import static android.content.ContentValues.TAG;

public class StepsService extends Service implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor sensor;
    private SimpleDateFormat dateFormat;
    private String date;
    private Calendar calender;
    private StepsRepository stepsRepository;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private LocalBroadcastManager broadcastManager;

    @Override
    public void onCreate() {
        super.onCreate();
        // database initialized
        stepsRepository = new StepsRepository(this);
        broadcastManager = LocalBroadcastManager.getInstance(this);

        // calendar , date format and date initialized
        calender = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        date = dateFormat.format(calender.getTime());

        //shared preferences initialized
        sharedPreferences = getSharedPreferences("steps",MODE_PRIVATE);
        editor = sharedPreferences.edit();

        //sensor initialized
        sensorManager = (SensorManager)this.getSystemService(Context.SENSOR_SERVICE);
        if(sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR) != null)
        {
            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);
            sensorManager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_FASTEST);
        }
        else if(sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER) != null)
        {
            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
            sensorManager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_FASTEST);
        }
        else
        {
            Toast.makeText(this, "Your phone does not contain sensor", Toast.LENGTH_LONG).show();
            Toast.makeText(this, "This App can't run on your phone", Toast.LENGTH_LONG).show();
        }

    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        try {
            update();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        try {
            update();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    // setting the values steps and distance
    public void update() throws ExecutionException, InterruptedException {
        calender = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        date = dateFormat.format(calender.getTime());

        if (sharedPreferences.getString("current date", "").equalsIgnoreCase(date)) {
            Steps steps = stepsRepository.search(date);
            if (sharedPreferences.getString("current mode", "").equalsIgnoreCase("walk")) {
                stepsRepository.update(new Steps(date, steps.getStepsWalk() + 1, steps.getDistanceWalk() + 0.0008, steps.getStepsRan(), steps.getDistanceRan(), steps.getTotalSteps() + 1, steps.getTotalDistance() + 0.0008));
            }

            if (sharedPreferences.getString("current mode", "").equalsIgnoreCase("run")) {
                stepsRepository.update(new Steps(date, steps.getStepsWalk(), steps.getDistanceWalk(), steps.getStepsRan() + 1, steps.getDistanceRan() + 0.0008, steps.getTotalSteps() + 1, steps.getTotalDistance() + 0.0008));
            }
        } else {
            stepsRepository.insert(new Steps(date, 0, 0.0, 0, 0.0, 0, 0.0));
            editor.putString("current date", date);
            editor.putString("current mode", "walk");
            editor.commit();
        }

        Intent intent = new Intent("com.example.healthcompanion");
        if (intent != null) {
            broadcastManager.sendBroadcast(intent);
        }
    }

}
