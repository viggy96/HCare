package com.atomindustries.vignesh.hcare;

import android.content.Intent;
import android.hardware.location.GeofenceHardwareRequest;
import android.location.Location;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class DoctorActivity extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_layout);
        setUpMapIfNeeded();
        mMap.setMyLocationEnabled(true);
        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            String phoneNumber = "";
            @Override
            public void onInfoWindowClick(Marker marker) {
               if (marker.getTitle() == "UNC Campus Health Services") {
                   Intent intent = new Intent(Intent.ACTION_CALL);
                   phoneNumber = "(919) 966 2281";
                   intent.setData(Uri.parse("tel:" + phoneNumber));
                   if (intent.resolveActivity(getPackageManager()) != null) startActivity(intent);
               }
               else {
                   Intent intent = new Intent(Intent.ACTION_CALL);
                   intent.setData(Uri.parse("tel:" + ""));
                   if (intent.resolveActivity(getPackageManager()) != null) startActivity(intent);
               }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera.
     * <p>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        GeofenceHardwareRequest location = null;
        final LatLng CAMPUS_HEALTH_SERVICES = new LatLng(35.905631, -79.049383);
        Marker campus_health_services = mMap.addMarker(new MarkerOptions()
                                                            .position(CAMPUS_HEALTH_SERVICES)
                                                            .title("UNC Campus Health Services"));
        campus_health_services.showInfoWindow();

        CameraUpdate cu = CameraUpdateFactory.newLatLngZoom(CAMPUS_HEALTH_SERVICES, 25);
        mMap.animateCamera(cu);
    }
}
