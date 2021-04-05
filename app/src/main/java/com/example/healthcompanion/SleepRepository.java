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
        sleepDao.insert(sleep);
    }
    public void Update ( Sleep sleep)
    {
        sleepDao.update(sleep);
    }
    public List<Sleep> SelectAll ()
    {
        return sleepDao.selectAll();
    }
    public Sleep Search ( String date)
    {
        return sleepDao.search(date);
    }


}
