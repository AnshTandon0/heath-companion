package com.example.healthcompanion;

import android.os.AsyncTask;

class UpdateAsyncTaskPedometer extends AsyncTask<Steps,Void,Void>
{
    private StepsDao stepsDao;
    public UpdateAsyncTaskPedometer(StepsDao dao)
    {
        stepsDao = dao;
    }

    @Override
    protected Void doInBackground(Steps... steps) {
        stepsDao.update(steps[0]);
        return null;
    }
}