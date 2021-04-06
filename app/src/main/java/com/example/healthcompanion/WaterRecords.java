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
    private int consumed;

    @NonNull
    private  int missed;

    public WaterRecords(@NonNull String date, int consumed, int missed) {
        this.date = date;
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
