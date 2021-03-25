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
        return new SearchAllAsyncTaskPedometer(stepsDao).execute().get();
    }
    public Steps search (String date) throws ExecutionException, InterruptedException {
        return new SearchAsyncTaskPedometer(stepsDao).execute(date).get();
    }
    public void insert (Steps steps)
    {
        new InsertAsyncTaskPedometer(stepsDao).execute(steps);
    }
    public void update (Steps steps)
    {
        new UpdateAsyncTaskPedometer(stepsDao).execute(steps);
    }
}
