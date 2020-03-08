package com.interview.androidlib;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

import java.io.IOException;
import java.util.Locale;

public class GPS {
    public static final int COARSE_LOCATION_PERMISSION_REQUEST = 1332;
    public static final int FINE_LOCATION_PERMISSION_REQUEST = 1333;
    public static final int CONTACTS_PERMISSION_REQUEST = 1334;
    public static final int CAMERA_PERMISSION_REQUEST = 1335;
    public static final int INTERNAL_STORAGE_PERMISSION_REQUEST = 1336;
    public static final int EXTERNAL_STORAGE_PERMISSION_REQUEST = 1337;
    public static final int INTERNET_PERMISSION_REQUEST = 1338;
    public static final int WIFI_PERMISSION_REQUEST = 1339;

    private LocationManager locationManager;
    protected LocationListener locationListener;
    private Location location;
    private Address address;
    private AppCompatActivity app;

    public GPS(AppCompatActivity app){
        if (app instanceof LocationListener) {
            this.app = app;
            locationListener = (LocationListener) app;
            locationManager = (LocationManager) app.getSystemService(Context.LOCATION_SERVICE);
            location = null;
            address = null;

            startGPSTracking();
        }
    }

    public Location getLastKnownLocation() {
        return location;
    }
    public Address getLastKnownAddress() {
        return address;
    }

    public void onPause() {
        if (ContextCompat.checkSelfPermission(app,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            startGPSTracking();
        }
    }

    public void onResume() {
        if (ContextCompat.checkSelfPermission(app,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            stopGPSTracking();
        }
    }

    public void startGPSTracking(){
        // Check if the GPS permission is available
        if (app.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        else {
            if (app.shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION) || app.shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_COARSE_LOCATION))
                Toast.makeText(app, "GPS permission is needed to view the map.", Toast.LENGTH_SHORT).show();

            //Request the user for permission to get access to the GPS
            app.requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, FINE_LOCATION_PERMISSION_REQUEST);
        }
    }

    public void stopGPSTracking(){
        locationManager.removeUpdates(locationListener);
    }

    public void onLocationChanged(Location location) {
        this.location = location;
        Geocoder geo = new Geocoder(app.getBaseContext(), Locale.getDefault());
        try{
            address = geo.getFromLocation(location.getLatitude(), location.getLongitude(), 1).get(0);
        }catch (IOException e){
            e.printStackTrace();
        }
        if (locationManager != null && locationListener != null)
            locationManager.removeUpdates(locationListener);
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode) {
            case CAMERA_PERMISSION_REQUEST:
                break;
            case CONTACTS_PERMISSION_REQUEST:
                break;
            case FINE_LOCATION_PERMISSION_REQUEST:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    startGPSTracking();
                else
                    Toast.makeText(app, "Fine location GPS permission was not granted.", Toast.LENGTH_SHORT).show();
                break;
            case COARSE_LOCATION_PERMISSION_REQUEST:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    startGPSTracking();
                else
                    Toast.makeText(app, "Coarse location GPS permission was not granted.", Toast.LENGTH_SHORT).show();
                break;
            case EXTERNAL_STORAGE_PERMISSION_REQUEST:
                break;
            case INTERNAL_STORAGE_PERMISSION_REQUEST:
                break;
            case INTERNET_PERMISSION_REQUEST:
                break;
            case WIFI_PERMISSION_REQUEST:
                break;
            default:
                app.onRequestPermissionsResult(requestCode, permissions, grantResults);
                break;
        }
    }
}
