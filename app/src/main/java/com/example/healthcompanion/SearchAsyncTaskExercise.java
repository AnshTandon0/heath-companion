package com.example.healthcompanion;

import android.os.AsyncTask;

public class SearchAsyncTaskExercise extends AsyncTask<String , Void , Exercise> {

    private ExerciseDao exerciseDao;
    public SearchAsyncTaskExercise(ExerciseDao dao)
    {
        exerciseDao = dao;
    }
    @Override
    protected Exercise doInBackground(String... strings) {
        return exerciseDao.search(strings[0]);
    }
}
