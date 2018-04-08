package com.example.prachetagrawal.lawapp;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

/**
 * Created by prachetagrawal on 4/6/18.
 */

public class LocationService{
    Context context;

    private LocationManager manager;

    public LocationService(Context context) {
        this.context = context;
    }

    @SuppressLint("MissingPermission")
    public Location getLocation() {
        Location location = null;
        Log.d("Find Location", "in find_location");
        String location_context = Context.LOCATION_SERVICE;
        manager = (LocationManager) context.getSystemService(location_context);
        List<String> providers = manager.getProviders(true);
        for (String provider : providers) {
            manager.requestLocationUpdates(provider, 1000, 0,
                new LocationListener() {

                    public void onLocationChanged(Location location) {
                    }

                    public void onProviderDisabled(String provider) {}

                    public void onProviderEnabled(String provider) {}

                    public void onStatusChanged(String provider, int status,
                                                Bundle extras) {}
                });
            if(location == null) {
                location = manager.getLastKnownLocation(provider);
            }
        }
        return location;
    }


}
