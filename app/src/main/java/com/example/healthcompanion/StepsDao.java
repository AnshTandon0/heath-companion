package com.example.healthcompanion;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface StepsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Steps steps);

    @Update
    void update(Steps steps);

    @Query("SELECT * FROM Steps")
    List <Steps> selectAll();

    @Query("SELECT * FROM Steps WHERE date = :date")
    Steps select(String date);
}
