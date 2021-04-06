package com.example.healthcompanion;

import android.os.AsyncTask;

public class SearchAsyncTaskWaterRecords extends AsyncTask<String,Void,WaterRecords> {

    private WaterRecordsDao waterRecordsDao;

    public SearchAsyncTaskWaterRecords ( WaterRecordsDao waterRecordsDao)
    {
        this.waterRecordsDao = waterRecordsDao;
    }

    @Override
    protected WaterRecords doInBackground(String... strings) {
        return waterRecordsDao.Search(strings[0]);
    }
}
