package com.example.healthcompanion;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ExerciseDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert ( Exercise exercise );

    @Update
    void update ( Exercise exercise );

    @Query( "SELECT * FROM Exercise WHERE date == :date" )
    Exercise search ( String date );

    @Query(" SELECT * FROM Exercise")
    List<Exercise> selectAll ();

    @Query(" SELECT start_time FROM Exercise WHERE date == :date")
    String select_Start_time(String date);

    @Query(" SELECT end_time FROM Exercise WHERE date == :date")
    String select_end_time(String date);
}
