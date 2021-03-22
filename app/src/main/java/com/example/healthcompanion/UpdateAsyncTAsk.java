package com.example.healthcompanion;

import android.os.AsyncTask;

class UpdateAsyncTask extends AsyncTask<Steps,Void,Void>
{
    private StepsDao stepsDao;
    public UpdateAsyncTask(StepsDao dao)
    {
        stepsDao = dao;
    }

    @Override
    protected Void doInBackground(Steps... steps) {
        stepsDao.update(steps[0]);
        return null;
    }
}
