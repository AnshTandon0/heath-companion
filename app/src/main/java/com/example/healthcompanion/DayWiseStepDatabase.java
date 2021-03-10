package com.example.healthcompanion;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = { DayWiseStep.class },version = 1,exportSchema = false)

public abstract class DayWiseStepDatabase extends RoomDatabase {

    public abstract DayWiseStepsDao dayWiseStepsDao();
    private static DayWiseStepDatabase db;

    public static DayWiseStepDatabase getInstance(Context context)
    {
        if(db==null)
        {
            db = db.buildDatabaseInstance(context);
        }

        return db;
    }

    private static DayWiseStepDatabase buildDatabaseInstance (Context context)
    {
        return Room.databaseBuilder(context,DayWiseStepDatabase.class,"database")
                .allowMainThreadQueries()
                .build();
    }
}
