package com.example.healthcompanion;

import android.os.AsyncTask;

public class SearchAsyncTaskSleep extends AsyncTask<String,Void,Sleep> {

    private SleepDao sleepDao;

    public SearchAsyncTaskSleep( SleepDao dao )
    {
        sleepDao = dao;
    }

    @Override
    protected Sleep doInBackground(String... strings) {
        return sleepDao.search(strings[0]);
    }
}
