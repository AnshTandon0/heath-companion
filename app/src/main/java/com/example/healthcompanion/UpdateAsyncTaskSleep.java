package com.example.healthcompanion;

import android.os.AsyncTask;

public class UpdateAsyncTaskSleep extends AsyncTask<Sleep,Void,Void> {

    private SleepDao sleepDao;

    public UpdateAsyncTaskSleep( SleepDao dao)
    {
        sleepDao = dao;
    }

    @Override
    protected Void doInBackground(Sleep... sleeps) {
        sleepDao.update(sleeps[0]);
        return null;
    }
}
