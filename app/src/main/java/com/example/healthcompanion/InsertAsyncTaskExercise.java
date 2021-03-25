package com.example.healthcompanion;

import android.os.AsyncTask;

public class InsertAsyncTaskExercise extends AsyncTask<Exercise,Void,Void> {

    private ExerciseDao exerciseDao;
    public InsertAsyncTaskExercise(ExerciseDao dao)
    {
        exerciseDao = dao;
    }
    @Override
    protected Void doInBackground(Exercise... exercises) {
        exerciseDao.insert(exercises[0]);
        return null;
    }
}
