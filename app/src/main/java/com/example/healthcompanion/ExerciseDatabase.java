package com.example.healthcompanion;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {Exercise.class},version = 1,exportSchema = false)
public abstract class ExerciseDatabase extends RoomDatabase  {

   private static ExerciseDatabase dbExercise;
   public abstract ExerciseDao exerciseDao();

   public static ExerciseDatabase getInstance(Context context)
   {
       if (dbExercise == null)
       {
           dbExercise = ExerciseDatabase.buildDatabaseInstance(context);
       }

       return dbExercise;
   }

   private static ExerciseDatabase buildDatabaseInstance( Context context)
   {
       return Room.databaseBuilder(context,ExerciseDatabase.class,"databaseExercise")
               //.allowMainThreadQueries()
               .build();
   }
}
