package com.example.healthcompanion;

import android.os.AsyncTask;

import java.util.List;

public class SelectAllAsyncTaskWater extends AsyncTask<Void,Void, List<Water>> {

    private WaterDao waterDao;

    public SelectAllAsyncTaskWater ( WaterDao waterDao)
    {
        this.waterDao = waterDao;
    }

    @Override
    protected List<Water> doInBackground(Void... voids) {
        return waterDao.SelectAll();
    }
}
