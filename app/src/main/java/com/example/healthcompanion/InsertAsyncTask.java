package com.example.healthcompanion;

import android.os.AsyncTask;

class InsertAsyncTask extends AsyncTask<DayWiseStep,Void,Void>
{
    private DayWiseStepsDao mDayWiseStepsDao;
    public InsertAsyncTask(DayWiseStepsDao dao) {
        mDayWiseStepsDao = dao;
    }

    @Override
    protected Void doInBackground(DayWiseStep... dayWiseSteps) {
        mDayWiseStepsDao.insert(dayWiseSteps[0]);
        return null;
    }
}
