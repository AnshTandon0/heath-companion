package com.example.healthcompanion;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface SleepDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Sleep sleep);

    @Update
    void update(Sleep sleep);

    @Query("SELECT * FROM Sleep")
    List<Sleep> selectAll();

    @Query("SELECT * FROM Sleep WHERE date == :date")
    Sleep search( String date);
}
