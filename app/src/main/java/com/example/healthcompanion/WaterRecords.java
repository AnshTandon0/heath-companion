package com.example.healthcompanion;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "WaterRecord")
public class WaterRecords {

    @PrimaryKey
    @NonNull
    private String date;

    @NonNull
    private String schedule;

    @NonNull
    private String status;

    public WaterRecords(@NonNull String date, @NonNull String schedule, @NonNull String status) {
        this.date = date;
        this.schedule = schedule;
        this.status = status;
    }

    @NonNull
    public String getDate() {
        return date;
    }

    public void setDate(@NonNull String date) {
        this.date = date;
    }

    @NonNull
    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(@NonNull String schedule) {
        this.schedule = schedule;
    }

    @NonNull
    public String getStatus() {
        return status;
    }

    public void setStatus(@NonNull String status) {
        this.status = status;
    }
}
