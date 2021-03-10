package com.example.healthcompanion;

import android.content.Context;

import java.util.List;

public class DayWiseStepRepository {

   List<DayWiseStep> dayWiseStep;
    DayWiseStepsDao dayWiseStepsDao;

    public DayWiseStepRepository( Context context)
    {
        DayWiseStepDatabase dayWiseStepDatabase = DayWiseStepDatabase.getInstance(context);
        dayWiseStepsDao = dayWiseStepDatabase.dayWiseStepsDao();
    }

    public void insert( DayWiseStep dayWiseStep)
    {
        new InsertAsyncTask(dayWiseStepsDao).execute(dayWiseStep);
    }
    public void update(DayWiseStep dayWiseStep)
    {
        new UpdateAsyncTask(dayWiseStepsDao).execute(dayWiseStep);
    }
    public DayWiseStep select( String date)
    {
        return dayWiseStepsDao.select(date);
    }

}
