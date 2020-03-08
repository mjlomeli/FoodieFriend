package com.interview.lib;

public class Coordinates {
    private static int ID = 0;
    double longitude;
    double latitude;
    int id;

    public Coordinates() {
        this.longitude = 0.0;
        this.latitude = 0.0;
        this.id = ++ID;
    }

    public Coordinates(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.id = ++ID;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }
        //33.847641, -117.924253
        //33.847611, -117.924346

}
