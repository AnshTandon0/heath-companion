package com.example.healthcompanion;

import android.os.AsyncTask;

import java.util.List;

public class SelectAllAsyncTaskSleep extends AsyncTask<Void,Void, List<Sleep>> {

    private SleepDao sleepDao;

    public SelectAllAsyncTaskSleep( SleepDao dao )
    {
        sleepDao = dao;
    }

    @Override
    protected List<Sleep> doInBackground(Void... voids) {
        return sleepDao.selectAll();
    }
}
