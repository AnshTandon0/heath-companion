package com.example.healthcompanion;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.Calendar;
import java.util.List;

public class WaterActivity extends AppCompatActivity {

    private Calendar calendar ;
    private List<Water> waters ;
    private List<WaterRecords> waterRecords;
    private  WaterRepository waterRepository;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        new MenuInflater(this).inflate(R.menu.menu_water,menu);
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water);

        waterRepository = new WaterRepository(this);
        if (waterRepository.SelectAllWaterRecords().size() > 0)
        {
            waterRecords = waterRepository.SelectAllWaterRecords();
            RecyclerView recyclerView = findViewById(R.id.recycler_view);
            WaterActivityAdapter waterActivityAdapter = new WaterActivityAdapter(this,waterRecords);
            recyclerView.setAdapter(waterActivityAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }


        if(waterRepository.SelectAllWater().size() > 0) {
            waters = waterRepository.SelectAllWater();

            Intent intent1 = getIntent();
            if (intent1.getStringExtra("schedule").equalsIgnoreCase("changed"))
            {
            for (int i = 0; i < waters.size(); i++) {
                calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY, DataConverter.timeSeparator(waterRepository.SelectAllWater().get(i).getTime()).get(0));
                calendar.set(Calendar.MINUTE, DataConverter.timeSeparator(waterRepository.SelectAllWater().get(i).getTime()).get(1));
                calendar.set(Calendar.SECOND, DataConverter.timeSeparator(waterRepository.SelectAllWater().get(i).getTime()).get(2));
                calendar.set(Calendar.MILLISECOND, 0);

                Intent intent = new Intent(WaterActivity.this, WaterNotificationReciever.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(WaterActivity.this, i + 1, intent, 0);
                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                alarmManager.setExact(AlarmManager.RTC, calendar.getTimeInMillis(), pendingIntent);
            }
            }
        }

    }
    public void schedule( MenuItem item )
    {
        Intent intent = new Intent(this,WaterSchedule.class);
        startActivity(intent);
        finish();
    }

}