package com.example.healthcompanion;

import android.os.AsyncTask;

public class InsertAsyncTaskWaterRecords extends AsyncTask<WaterRecords , Void , Void> {

    private WaterRecordsDao waterRecordsDao;

    public InsertAsyncTaskWaterRecords ( WaterRecordsDao waterRecordsDao)
    {
        this.waterRecordsDao = waterRecordsDao;
    }

    @Override
    protected Void doInBackground(WaterRecords... waterRecords) {
        waterRecordsDao.Insert(waterRecords[0]);
        return null;
    }
}
