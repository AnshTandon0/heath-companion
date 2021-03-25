package com.example.healthcompanion;

import android.os.AsyncTask;

public class SearchAsyncTaskPedometer extends AsyncTask <String,Void,Steps> {

    private StepsDao stepsDao ;

    public SearchAsyncTaskPedometer(StepsDao dao )
    {
        stepsDao = dao;
    }
    @Override
    protected Steps doInBackground(String... strings) {
        return stepsDao.select(strings[0]);
    }
}
