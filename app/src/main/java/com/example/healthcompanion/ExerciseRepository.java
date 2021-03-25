package com.example.healthcompanion;

import android.content.Context;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class ExerciseRepository  {

    private ExerciseDao exerciseDao;

    public ExerciseRepository(Context context)
    {
        ExerciseDatabase exerciseDatabase = ExerciseDatabase.getInstance(context);
        exerciseDao = exerciseDatabase.exerciseDao();
    }

    public void insert(Exercise exercise)
    {
        new InsertAsyncTaskExercise(exerciseDao).execute(exercise);
    }

    public void update(Exercise exercise)
    {
        new UpdateAsyncTaskExercise(exerciseDao).execute(exercise);
    }

    public List<Exercise> selectAll() throws ExecutionException, InterruptedException {
        return new SearchAllAsyncTaskExercise(exerciseDao).execute().get();
    }

    public Exercise search( String date) throws ExecutionException, InterruptedException {
        return new SearchAsyncTaskExercise(exerciseDao).execute(date).get();
    }

    public String selectStartTime( String date ) throws ExecutionException, InterruptedException {
        return new SearchAsyncTaskExercise(exerciseDao).execute(date).get().getStart_time();
    }

    public String selectEndTime( String date ) throws ExecutionException, InterruptedException {
        return new SearchAsyncTaskExercise(exerciseDao).execute(date).get().getEnd_time();
    }
}
