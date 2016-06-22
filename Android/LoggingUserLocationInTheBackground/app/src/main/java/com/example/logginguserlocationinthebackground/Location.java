package com.example.logginguserlocationinthebackground;

public class Location {
    long time;
    double latitude;
    double longitude;

    public Location(long time, double latitude, double longitude) {
        this.time = time;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "time=" + time + ", lat=" + latitude + ", long=" + longitude;
    }
}
