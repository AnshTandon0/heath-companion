package com.example.healthcompanion;

import android.os.AsyncTask;

class InsertAsyncTask extends AsyncTask<Steps,Void,Void>
{
    private StepsDao stepsDao;
    public InsertAsyncTask(StepsDao dao)
    {
        stepsDao = dao;
    }

    @Override
    protected Void doInBackground(Steps... steps) {
        stepsDao.insert(steps[0]);
        return null;
    }
}

