package com.example.healthcompanion;

import android.os.AsyncTask;

public class UpdateAsyncTaskExercise extends AsyncTask<Exercise,Void,Void> {

    private ExerciseDao exerciseDao;
    public UpdateAsyncTaskExercise(ExerciseDao dao)
    {
        exerciseDao = dao;
    }
    @Override
    protected Void doInBackground(Exercise... exercises) {
        exerciseDao.update(exercises[0]);
        return null;
    }
}
