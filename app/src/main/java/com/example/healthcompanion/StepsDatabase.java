package com.example.healthcompanion;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = { Steps.class },version = 1,exportSchema = false)

public abstract class StepsDatabase extends RoomDatabase {

    public abstract StepsDao stepsDao();
    private static StepsDatabase db;

    public static StepsDatabase getInstance(Context context)
    {
        if(db==null)
        {
            db = db.buildDatabaseInstance(context);
        }

        return db;
    }

    private static StepsDatabase buildDatabaseInstance (Context context)
    {
        return Room.databaseBuilder(context, StepsDatabase.class,"database")
                //.allowMainThreadQueries()
                .build();
    }
}
