package com.example.healthcompanion;

import android.os.AsyncTask;

import java.util.List;

public class SearchAllAsyncTaskExercise extends AsyncTask < Void , Void , List<Exercise>> {

    private ExerciseDao exerciseDao;
    public SearchAllAsyncTaskExercise(ExerciseDao dao)
    {
        exerciseDao = dao;
    }
    @Override
    protected List<Exercise> doInBackground(Void... voids) {
        return exerciseDao.selectAll() ;
    }
}
