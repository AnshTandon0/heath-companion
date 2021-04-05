package com.example.healthcompanion;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

public class SleepNotificationReciever extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Intent intent1 = new Intent(context,SleepService.class);
        PendingIntent pendingIntent1 = PendingIntent.getService(context,22,intent1,0);

        Intent intent2 = new Intent(context,SleepServiceCancel.class);
        PendingIntent pendingIntent2 = PendingIntent.getService(context,25,intent2,0);

        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, "primary_notification_channel_sleep");

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel("primary_notification_channel_sleep", "channel_sleep", NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.setName("Health Companion");
            notificationChannel.enableLights(true);
            notificationChannel.enableVibration(true);
            manager.createNotificationChannel(notificationChannel);
        }
        mBuilder.setSmallIcon(R.drawable.alarm);
        mBuilder.setContentTitle("Alarm");
        mBuilder.setContentText("Good Morning . It's time to wake up");
        mBuilder.setAutoCancel(true);
        mBuilder.addAction(R.drawable.ic_launcher_background,"Snooze",pendingIntent1);
        mBuilder.addAction(R.drawable.ic_launcher_background,"Cancel",pendingIntent2);

        manager.notify(14,mBuilder.build());
    }
}
