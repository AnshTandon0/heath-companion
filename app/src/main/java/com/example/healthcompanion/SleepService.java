package com.example.healthcompanion;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationManagerCompat;

import java.util.Calendar;

public class SleepService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        NotificationManagerCompat.from(this).cancel(14);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MINUTE,calendar.get(Calendar.MINUTE) + 1);
        calendar.set(Calendar.MILLISECOND,0);

        Intent intent1 = new Intent(this, SleepNotificationReciever.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 11, intent1, 0);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC,calendar.getTimeInMillis(),pendingIntent);

        SharedPreferences sharedPreferences = getSharedPreferences("sleep",MODE_PRIVATE);

        SleepRepository sleepRepository = new SleepRepository(this);
        Sleep sleep = sleepRepository.Search(sharedPreferences.getString("date",""));
        sleep.setStatus("Over Slept");
        sleepRepository.Update(sleep);

        return START_NOT_STICKY;
    }
}
