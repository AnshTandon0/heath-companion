package com.example.healthcompanion;

import android.os.AsyncTask;

class UpdateAsyncTask extends AsyncTask<DayWiseStep,Void,Void>
{
    private DayWiseStepsDao mDayWiseStepsDao;
    public UpdateAsyncTask(DayWiseStepsDao dao) {
        mDayWiseStepsDao = dao;
    }

    @Override
    protected Void doInBackground(DayWiseStep... dayWiseSteps) {
        mDayWiseStepsDao.update(dayWiseSteps[0]);
        return null;
    }
}
