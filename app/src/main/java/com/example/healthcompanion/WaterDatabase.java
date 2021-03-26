package com.example.healthcompanion;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Water.class,WaterRecords.class},version = 1 , exportSchema = false)
public abstract class WaterDatabase extends RoomDatabase {

    private static WaterDatabase waterDatabase;
    public abstract WaterDao waterDao();
    public abstract WaterRecordsDao waterRecordsDao();

    public static WaterDatabase getInstance(Context context)
    {
        if (waterDatabase == null)
        {
            waterDatabase = WaterDatabase.buildDatabaseInstance(context);
        }

        return waterDatabase;
    }

    private static WaterDatabase buildDatabaseInstance( Context context)
    {
        return Room.databaseBuilder(context,WaterDatabase.class,"WaterDatabase")
                .allowMainThreadQueries()
                .build();
    }

}
