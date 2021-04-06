package com.example.healthcompanion;

import android.os.AsyncTask;

public class DeleteAsyncTaskWater extends AsyncTask<Water,Void,Void> {

    private WaterDao waterDao;

    public DeleteAsyncTaskWater ( WaterDao waterDao)
    {
        this.waterDao = waterDao;
    }

    @Override
    protected Void doInBackground(Water... waters) {
       waterDao.Delete(waters[0]);
        return null;
    }
}
