package com.example.healthcompanion;

import android.content.Context;

import java.util.List;

public class ExerciseRepository  {

    private ExerciseDao exerciseDao;

    public ExerciseRepository(Context context)
    {
        ExerciseDatabase exerciseDatabase = ExerciseDatabase.getInstance(context);
        exerciseDao = exerciseDatabase.exerciseDao();
    }

    public void insert(Exercise exercise)
    {
        exerciseDao.insert(exercise);
    }

    public void update(Exercise exercise)
    {
        exerciseDao.update(exercise);
    }

    public List<Exercise> selectAll()
    {
        return exerciseDao.selectAll();
    }

    public Exercise search( String date)
    {
        return exerciseDao.search(date);
    }

    public String selectStartTime( String date )
    {
        return exerciseDao.select_Start_time(date);
    }

    public String selectEndTime( String date )
    {
        return exerciseDao.select_end_time(date);
    }
}
