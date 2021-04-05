package com.example.healthcompanion;

import android.os.AsyncTask;

public class InsertAsyncTaskSleep extends AsyncTask<Sleep,Void,Void> {

    private SleepDao sleepDao;

    public InsertAsyncTaskSleep( SleepDao sleepDao)
    {
        this.sleepDao = sleepDao;
    }

    @Override
    protected Void doInBackground(Sleep... sleeps) {
        sleepDao.insert(sleeps[0]);
        return null;
    }
}
