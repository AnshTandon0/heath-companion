package com.example.healthcompanion;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Steps")
public class DayWiseStep {

    @PrimaryKey
    @NonNull
    private String date;

    @NonNull
    private int stepsWalk;

    @NonNull
    private double distanceWalk = 0;

    @NonNull
    private int stepsRan;

    @NonNull
    private double distanceRan = 0;

    @NonNull
    private int totalSteps;

    @NonNull
    private double totalDistance = 0;

    public DayWiseStep(@NonNull String date, int stepsWalk, double distanceWalk, int stepsRan, double distanceRan, int totalSteps, double totalDistance) {
        this.date = date;
        this.stepsWalk = stepsWalk;
        this.distanceWalk = distanceWalk;
        this.stepsRan = stepsRan;
        this.distanceRan = distanceRan;
        this.totalSteps = totalSteps;
        this.totalDistance = totalDistance;
    }


    @NonNull
    public String getDate() {
        return date;
    }

    public void setDate(@NonNull String date) {
        this.date = date;
    }

    public int getStepsWalk() {
        return stepsWalk;
    }

    public void setStepsWalk(int stepsWalk) {
        this.stepsWalk = stepsWalk;
    }

    public double getDistanceWalk() {
        return distanceWalk;
    }

    public void setDistanceWalk(double distanceWalk) {
        this.distanceWalk = distanceWalk;
    }

    public int getStepsRan() {
        return stepsRan;
    }

    public void setStepsRan(int stepsRan) {
        this.stepsRan = stepsRan;
    }

    public double getDistanceRan() {
        return distanceRan;
    }

    public void setDistanceRan(double distanceRan) {
        this.distanceRan = distanceRan;
    }

    public int getTotalSteps() {
        return totalSteps;
    }

    public void setTotalSteps(int totalSteps) {
        this.totalSteps = totalSteps;
    }

    public double getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(double totalDistance) {
        this.totalDistance = totalDistance;
    }
}
