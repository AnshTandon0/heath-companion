package com.example.healthcompanion;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationManagerCompat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SleepServiceCancel extends Service {
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        NotificationManagerCompat.from(this).cancel(14);

        SharedPreferences sharedPreferences = getSharedPreferences("sleep",MODE_PRIVATE);

        String time = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
        SleepRepository sleepRepository = new SleepRepository(this);
        Sleep sleep = sleepRepository.Search(sharedPreferences.getString("date",""));
        sleep.setEndTime(time);

        if (sleep.getStatus().equals(""))
        {
            sleep.setStatus("Normal Sleep");
        }
        sleepRepository.Update(sleep);
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
