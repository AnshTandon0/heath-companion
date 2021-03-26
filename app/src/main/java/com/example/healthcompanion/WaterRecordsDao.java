package com.example.healthcompanion;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface WaterRecordsDao {

    @Insert
    void Insert( WaterRecords waterRecords);

    @Update
    void Update ( WaterRecords waterRecords);

    @Query("SELECT * FROM WaterRecord WHERE date == :date")
    WaterRecords Search(String date);

    @Query("SELECT * FROM WaterRecord")
    List<WaterRecords> SelectAll();

}
