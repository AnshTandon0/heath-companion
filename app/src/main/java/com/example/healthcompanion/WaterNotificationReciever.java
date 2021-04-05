package com.example.healthcompanion;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

public class WaterNotificationReciever extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Intent intent1 = new Intent(context,WaterService.class);
        intent1.putExtra("status","Consumed");
        intent1.putExtra("time",intent.getStringExtra("time"));
        System.out.println(intent.getStringExtra("time"));
        PendingIntent pendingIntent1 = PendingIntent.getService(context,999,intent1,0);

        Intent intent2 = new Intent(context,WaterService.class);
        intent2.putExtra("status","Missed");
        intent2.putExtra("time",intent.getStringExtra("time"));
        PendingIntent pendingIntent2 = PendingIntent.getService(context,998,intent2,0);

        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, "primary_notification_channel");

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel("primary_notification_channel", "channel", NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.setName("Health Companion");
            notificationChannel.enableLights(true);
            notificationChannel.enableVibration(true);
            manager.createNotificationChannel(notificationChannel);
        }
        mBuilder.setSmallIcon(R.drawable.water);
        mBuilder.setContentTitle("Water Intake");
        mBuilder.setContentText("Please consume a glass of water");
        mBuilder.addAction(R.drawable.ic_launcher_background,"Consume",pendingIntent1);
        mBuilder.addAction(R.drawable.ic_launcher_background,"Miss",pendingIntent2);
        mBuilder.setAutoCancel(true);

        manager.notify(100,mBuilder.build());
    }
}
