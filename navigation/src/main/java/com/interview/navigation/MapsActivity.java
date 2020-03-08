package com.interview.navigation;
/**
 * @author Mauricio Lomeli
 * @version Feburary, 2020
 *
 * This activity begins the GoogleMaps activity. It will connect to
 * Google's maps and provides an interactive map for the user to view
 * the relative location of the recommendation.
 */
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.location.Address;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.interview.androidlib.GPS;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, LocationListener {
    private TextView textView_Title;
    private TextView textView_Radius;
    private TextView textView_Calories;
    private TextView textView_Recommend;
    private TextView textView_Options;

    private GoogleMap mMap;
    private GPS gps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        textView_Title = (TextView) findViewById(R.id.textView_Title);
        textView_Radius = (TextView) findViewById(R.id.textView_Radius);
        textView_Calories = (TextView) findViewById(R.id.textView_Calories);
        textView_Recommend = (TextView) findViewById(R.id.textView_Recommend);
        textView_Options = (TextView) findViewById(R.id.textView_Options);

        this.gps = new GPS(this);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragmentmap);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in Sydney and move the camera
        LatLng gpsCenterCoord = new LatLng(40, -100);
        LatLng gpsBoundaryCoord = new LatLng(10, -154);
        LatLngBounds unitedStates = new LatLngBounds(gpsBoundaryCoord, gpsBoundaryCoord);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(unitedStates.getCenter(), 14));
    }

    public LatLng latlng(Address address){
        return new LatLng(address.getLatitude(), address.getLongitude());
    }

    public LatLng latlng(Location location){
        return new LatLng(location.getLatitude(), location.getLongitude());
    }

    public void moveCamera(Address address){
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latlng(address)));
    }

    public void moveCamera(Location location){
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latlng(location)));
    }

    public void addMarker(Address address, String title){
        mMap.addMarker(new MarkerOptions().position(latlng(address)).title(title));
    }

    public void addMarker(Location location, String title){
        mMap.addMarker(new MarkerOptions().position(latlng(location)).title(title));
    }

    public void addMarker(Address address, String title, String snippet){
        mMap.addMarker(new MarkerOptions().position(latlng(address)).title(title).snippet(snippet));
    }

    public void addMarker(Location location, String title, String snippet){
        mMap.addMarker(new MarkerOptions().position(latlng(location)).title(title).snippet(snippet));
    }

    public void clear(){
        mMap.clear();
    }

    @Override
    public void onLocationChanged(Location location) {
        gps.onLocationChanged(location);

        Address address = gps.getLastKnownAddress();
        String city = (address == null) ? "Current Location" : "Current Location in " + address.getLocality();

        if (location != null) {
            addMarker(address, city);
            moveCamera(address);
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    protected void onPause() {
        super.onPause();
        gps.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        gps.onResume();
    }
}
