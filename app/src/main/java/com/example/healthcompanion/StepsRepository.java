package com.example.healthcompanion;

import android.content.Context;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class StepsRepository {

    private StepsDao stepsDao;

    public StepsRepository( Context context)
    {
        StepsDatabase stepsDatabase = StepsDatabase.getInstance(context);
        stepsDao = stepsDatabase.stepsDao();
    }

    public List<Steps> getAllSteps() throws ExecutionException, InterruptedException {
        return new SearchAllAsyncTask(stepsDao).execute().get();
    }
    public Steps search (String date) throws ExecutionException, InterruptedException {
        return new SearchAsyncTask(stepsDao).execute(date).get();
    }
    public void insert (Steps steps)
    {
        new InsertAsyncTask(stepsDao).execute(steps);
    }
    public void update (Steps steps)
    {
        new UpdateAsyncTask(stepsDao).execute(steps);
    }
}
