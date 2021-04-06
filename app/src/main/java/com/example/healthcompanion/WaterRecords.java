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

    @NonNull
    private int consumed;

    @NonNull
    private  int missed;

    public WaterRecords(@NonNull String date, @NonNull String schedule, @NonNull String status, int consumed, int missed) {
        this.date = date;
        this.schedule = schedule;
        this.status = status;
        this.consumed = consumed;
        this.missed = missed;
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

    public int getConsumed() {
        return consumed;
    }

    public void setConsumed(int consumed) {
        this.consumed = consumed;
    }

    public int getMissed() {
        return missed;
    }

    public void setMissed(int missed) {
        this.missed = missed;
    }
}
