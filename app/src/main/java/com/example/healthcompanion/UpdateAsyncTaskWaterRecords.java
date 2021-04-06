package com.example.healthcompanion;

import android.os.AsyncTask;

public class UpdateAsyncTaskWaterRecords extends AsyncTask< WaterRecords , Void , Void> {

    private WaterRecordsDao waterRecordsDao;

    public UpdateAsyncTaskWaterRecords ( WaterRecordsDao waterRecordsDao)
    {
        this.waterRecordsDao = waterRecordsDao;
    }
    @Override
    protected Void doInBackground(WaterRecords... waterRecords) {
        waterRecordsDao.Update(waterRecords[0]);
        return null;
    }
}
