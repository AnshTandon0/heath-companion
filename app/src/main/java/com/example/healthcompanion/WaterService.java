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
    String date;

// TODO  solve the ongoing error in notification
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        NotificationManagerCompat.from(getApplicationContext()).cancel(100);
        waterRepository = new WaterRepository(this);
        date = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
        waterRecords = new WaterRecords(date,0,0);
        waterRepository.InsertWaterRecords(waterRecords);

        if (intent.getStringExtra("status").equals("Consumed"))
        {
            waterRecords = waterRepository.SearchWaterRecords(date);
            waterRecords.setConsumed(waterRepository.SearchWaterRecords(date).getConsumed() + 1 );
            waterRepository.UpdateWaterRecords(waterRecords);

        }
        else if (intent.getStringExtra("status").equals("Missed"))
        {
            waterRecords = waterRepository.SearchWaterRecords(date);
            waterRecords.setMissed(waterRepository.SearchWaterRecords(date).getMissed() + 1 );
            waterRepository.UpdateWaterRecords(waterRecords);
        }

        return START_NOT_STICKY;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
