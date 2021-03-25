package com.example.healthcompanion;

import android.os.AsyncTask;

import java.util.List;

public class SearchAllAsyncTaskPedometer extends AsyncTask<Void,Void, List<Steps>> {

    private StepsDao stepsDao;

    public SearchAllAsyncTaskPedometer(StepsDao stepsDao)
    {
        this.stepsDao = stepsDao;
    }

    @Override
    protected List<Steps> doInBackground(Void... voids) {
        return stepsDao.selectAll();
    }
}
