package com.example.healthcompanion;

import android.content.Context;

import java.util.Date;
import java.util.List;

public class SleepRepository {

    private SleepDao sleepDao;

    public SleepRepository(Context context)
    {
        SleepDatabase sleepDatabase = SleepDatabase.getInstance(context);
        sleepDao = sleepDatabase.sleepDao();
    }

    public void Insert ( Sleep sleep)
    {
        new InsertAsyncTaskSleep(sleepDao).execute(sleep);
    }
    public void Update ( Sleep sleep)
    {
        new UpdateAsyncTaskSleep(sleepDao).execute(sleep);
    }
    public List<Sleep> SelectAll ()
    {
        return new SelectAllAsyncTaskSleep(sleepDao).doInBackground();
    }
    public Sleep Search ( String date)
    {
        return new SearchAsyncTaskSleep(sleepDao).doInBackground(date);
    }


}
