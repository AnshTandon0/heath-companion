package com.example.healthcompanion;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Water")
public class Water {

    @PrimaryKey
    @NonNull
    private String time;

    @NonNull
    public String getTime() {
        return time;
    }

    public void setTime(@NonNull String time) {
        this.time = time;
    }

    public Water(@NonNull String time) {
        this.time = time;
    }
}
