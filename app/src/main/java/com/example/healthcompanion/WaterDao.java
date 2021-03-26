package com.example.healthcompanion;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface WaterDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void Insert( Water water );

    @Delete
    void Delete( Water water );

    @Query("SELECT * FROM Water")
    List<Water> SelectAll();
}
