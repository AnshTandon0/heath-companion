package com.example.healthcompanion;

import android.content.Context;

import java.util.List;

public class WaterRepository {

    private WaterDao waterDao;
    private WaterRecordsDao waterRecordsDao;

    public WaterRepository (Context context)
    {
        WaterDatabase waterDatabase = WaterDatabase.getInstance(context);
        waterDao = waterDatabase.waterDao();
        waterRecordsDao = waterDatabase.waterRecordsDao();
    }

    public void InsertWater ( Water water)
    {
        new InsertAsyncTaskWater(waterDao).doInBackground(water);
    }

    public void DeleteWater ( Water water )
    {
        new DeleteAsyncTaskWater(waterDao).doInBackground(water);
    }

    public List<Water> SelectAllWater ()
    {
        return new SelectAllAsyncTaskWater(waterDao).doInBackground();
    }

    public void InsertWaterRecords ( WaterRecords waterRecords)
    {
        new InsertAsyncTaskWaterRecords(waterRecordsDao).doInBackground(waterRecords);
    }

    public void UpdateWaterRecords ( WaterRecords waterRecords )
    {
       new UpdateAsyncTaskWaterRecords(waterRecordsDao).doInBackground(waterRecords);
    }

    public List<WaterRecords> SelectAllWaterRecords ()
    {
        return new SelectAllAsyncTaskWaterRecords(waterRecordsDao).doInBackground();
    }

    public WaterRecords SearchWaterRecords ( String date )
    {
        return new SearchAsyncTaskWaterRecords(waterRecordsDao).doInBackground(date);
    }

}
