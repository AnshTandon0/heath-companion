package com.example.healthcompanion;

import android.os.AsyncTask;

import java.util.List;

public class SelectAllAsyncTaskWaterRecords extends AsyncTask<Void , Void , List<WaterRecords>> {

    private WaterRecordsDao waterRecordsDao;

    public SelectAllAsyncTaskWaterRecords (WaterRecordsDao waterRecordsDao)
    {
        this.waterRecordsDao = waterRecordsDao;
    }

    @Override
    protected List<WaterRecords> doInBackground(Void... voids) {
        return waterRecordsDao.SelectAll();
    }
}
