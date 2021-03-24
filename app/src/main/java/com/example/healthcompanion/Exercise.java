package com.example.healthcompanion;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity( tableName = "Exercise")
public class Exercise {

    @PrimaryKey
    @NonNull
    private String date;

    private String start_time;

    private String end_time;

    public Exercise(@NonNull String date, String start_time, String end_time) {
        this.date = date;
        this.start_time = start_time;
        this.end_time = end_time;
    }

    @NonNull
    public String getDate() {
        return date;
    }

    public void setDate(@NonNull String date) {
        this.date = date;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }
}
