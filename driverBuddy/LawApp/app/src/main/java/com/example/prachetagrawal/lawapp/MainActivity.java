package com.example.prachetagrawal.lawapp;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private BroadcastReceiver broadcastReceiver;
    private String curState = "defState";

    @Override
    protected void onResume() {
        super.onResume();
        if(broadcastReceiver == null) {
            broadcastReceiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    String coords = intent.getStringExtra("coordinates");
                    String temp[] = coords.split(" ");
                    int longitude = Integer.parseInt(temp[0]);
                    int latitude = Integer.parseInt(temp[1]);

                    String newState = findState(longitude, latitude);
                    if (!newState.equals(curState)) {
                        curState = newState;
                        TextView t = findViewById(R.id.testLocation);
                        t.setText(curState);
                    }


                }
            };
        }
        registerReceiver(broadcastReceiver, new IntentFilter("Location Updates"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Button mainButton = findViewById(R.id.buttonToMain);
        mainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DisplayLaw.class);
                startActivity(intent);
            }
        });

        if(!runtimePermissions()) {
            //enableService();
        }
    }

    private Boolean runtimePermissions() {
        if(Build.VERSION.SDK_INT >= 23 && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 100);
            return true;
        }
        return false;
    }

    private String findState(double lng, double lat) {
        Geocoder gcd = new Geocoder(getApplicationContext(), Locale.getDefault());
        try {
            List<Address> addresses = gcd.getFromLocation(lat, lng, 1);
            if (addresses.size() > 0)
                return addresses.get(0).getAdminArea();
        } catch (IOException e) {
            return "";
        }
        return "";
    }

}
