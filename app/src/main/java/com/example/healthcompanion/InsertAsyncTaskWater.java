package com.example.healthcompanion;

import android.os.AsyncTask;

public class InsertAsyncTaskWater extends AsyncTask< Water , Void , Void > {

    private WaterDao waterDao;

    public InsertAsyncTaskWater ( WaterDao water )
    {
        this.waterDao = water;
    }

    @Override
    protected Void doInBackground(Water... waters) {
        waterDao.Insert(waters[0]);
        return null;
    }
}
