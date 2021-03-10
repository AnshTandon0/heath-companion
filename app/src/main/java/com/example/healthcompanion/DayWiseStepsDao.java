package com.example.healthcompanion;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DayWiseStepsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(DayWiseStep dayWiseStep);

    @Update
    void update(DayWiseStep dayWiseStep);

    @Query("SELECT * FROM Steps")
    List <DayWiseStep> selectAll();

    @Query("SELECT * FROM Steps WHERE date = :date")
    DayWiseStep select(String date);
}
