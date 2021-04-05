package com.example.healthcompanion;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class WaterService extends Service {

    WaterRepository waterRepository;
    WaterRecords waterRecords;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String date;

// TODO add recycler view for water activity and solve the ongoing error in notification
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        NotificationManagerCompat.from(getApplicationContext()).cancel(100);

        if (intent.getStringExtra("status").equals("Consumed"))
        {
            if(sharedPreferences.getString("date","").equals(date))
            {
               waterRecords.setSchedule(waterRepository.SearchWaterRecords(date).getSchedule() + intent.getStringExtra("time") + ";");
               waterRecords.setStatus(waterRepository.SearchWaterRecords(date).getStatus() + "Consumed;");
               waterRepository.UpdateWaterRecords(waterRecords);
                System.out.println(waterRepository.SearchWaterRecords(date).getSchedule());
                Log.d("", "onStartCommand:iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii ");
            }
            else
            {
                waterRecords.setSchedule(intent.getStringExtra("time") + ";");
                waterRecords.setStatus("Consumed;");
                waterRepository.InsertWaterRecords(waterRecords);
                editor.putString("date",date);
                editor.commit();
                System.out.println(waterRepository.SearchWaterRecords(date).getSchedule());
            }

        }
        else if (intent.getStringExtra("status").equals("Missed"))
        {
            if(sharedPreferences.getString("date","").equals(date))
            {
                waterRecords.setSchedule(waterRepository.SearchWaterRecords(date).getSchedule() + intent.getStringExtra("time") + ";");
                waterRecords.setStatus(waterRepository.SearchWaterRecords(date).getStatus() + "Missed;");
                waterRepository.UpdateWaterRecords(waterRecords);
            }
            else
            {
                waterRecords.setSchedule(intent.getStringExtra("time") + ";");
                waterRecords.setStatus("Missed;");
                waterRepository.InsertWaterRecords(waterRecords);
                editor.putString("date",date);
                editor.apply();
            }
        }

        return START_NOT_STICKY;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        waterRepository = new WaterRepository(this);
        date = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
        waterRecords = new WaterRecords(date,"","");

        sharedPreferences = getSharedPreferences("Water",MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString("date","");
        editor.apply();

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
