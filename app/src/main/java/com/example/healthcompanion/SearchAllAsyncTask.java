package com.example.healthcompanion;

import android.os.AsyncTask;

import java.util.List;

public class SearchAllAsyncTask extends AsyncTask<Steps,Void, List<Steps>> {

    private StepsDao stepsDao;

    public SearchAllAsyncTask ( StepsDao stepsDao)
    {
        this.stepsDao = stepsDao;
    }

    @Override
    protected List<Steps> doInBackground(Steps... steps) {
        return stepsDao.selectAll();
    }
}
