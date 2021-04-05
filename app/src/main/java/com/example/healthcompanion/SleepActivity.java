package com.example.healthcompanion;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class SleepActivity extends AppCompatActivity {


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        new MenuInflater(this).inflate(R.menu.menu_sleep,menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep);

        SleepRepository sleepRepository = new SleepRepository(this);

        RecyclerView  recyclerView = findViewById(R.id.recyclerSleep);
        SleepAdapter sleepAdapter = new SleepAdapter(SleepActivity.this,sleepRepository.SelectAll());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(sleepAdapter);
    }

    public void StartSleep ( MenuItem item )
    {

        SleepRepository sleepRepository = new SleepRepository(SleepActivity.this);
        String date = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
        String time = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
        sleepRepository.Insert(new Sleep(date,time,"",""));

        SharedPreferences sharedPreferences = getSharedPreferences("sleep",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("date",date);
        editor.commit();

        RecyclerView  recyclerView = findViewById(R.id.recyclerSleep);
        SleepAdapter sleepAdapter = new SleepAdapter(SleepActivity.this,sleepRepository.SelectAll());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(sleepAdapter);
    }

    public void AddAlarm ( MenuItem item )
    {
        LinearLayout layout = new LinearLayout(SleepActivity.this);
        layout.setOrientation(LinearLayout.VERTICAL);

       final EditText editText1 = new EditText(this);
       editText1.setHint("HH:MM:SS");
       layout.addView(editText1);

        final EditText editText2 = new EditText(this);
        editText2.setHint("DD");
        layout.addView(editText2);

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("ALARM");
        alert.setIcon(R.drawable.alarm);
        alert.setMessage("Enter the Time ( 24 hrs Format ) && Enter the Date");
        alert.setView(layout);
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY,DataConverter.timeSeparator(editText1.getText().toString()).get(0));
                calendar.set(Calendar.MINUTE,DataConverter.timeSeparator(editText1.getText().toString()).get(1));
                calendar.set(Calendar.SECOND,DataConverter.timeSeparator(editText1.getText().toString()).get(2));
                calendar.set(Calendar.DATE,Integer.parseInt(editText2.getText().toString()));
                calendar.set(Calendar.MILLISECOND,0);

                Intent intent = new Intent(SleepActivity.this, SleepNotificationReciever.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(SleepActivity.this, 11, intent, 0);


                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC, calendar.getTimeInMillis() ,pendingIntent);
                }


                Toast.makeText(SleepActivity.this, "Alarm added successfully", Toast.LENGTH_SHORT).show();


            }
        });
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alert.show();
    }

    public void CancelAlarm ( MenuItem item )
    {
        AlertDialog.Builder alert = new AlertDialog.Builder(this)
                .setMessage("Are you sure you wish to cancel the Alarm ?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        NotificationManagerCompat.from(SleepActivity.this).cancel(14);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        alert.show();
    }
}