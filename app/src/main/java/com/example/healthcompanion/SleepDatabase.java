package com.example.healthcompanion;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Sleep.class},exportSchema = false,version = 1)
public abstract class SleepDatabase extends RoomDatabase {

    public abstract SleepDao sleepDao();
    private static SleepDatabase sleepDatabase;

    public static SleepDatabase getInstance( Context context )
    {
        if ( sleepDatabase == null )
        {
            sleepDatabase = SleepDatabase.buildDatabase(context);
        }
        return sleepDatabase;
    }

    private static SleepDatabase buildDatabase( Context context )
    {
        return Room.databaseBuilder(context,SleepDatabase.class,"SleepDatabase")
                .allowMainThreadQueries()
                .build();

    }

}
