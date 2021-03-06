package com.example.prachetagrawal.lawapp;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.content.ContextCompat;
import android.location.LocationListener;
import android.location.LocationManager;
import android.provider.Settings;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import static android.app.NotificationChannel.DEFAULT_CHANNEL_ID;

public class MainActivity extends AppCompatActivity {

    private Button mainButton;
    private String curState = "";
    private Button helpButton;

    LocationManager locationManager;
    Context mContext;
    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        locationManager=(LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                60000,
                10, locationListenerGPS);
        isLocationEnabled();
        mainButton = findViewById(R.id.buttonToMain);
        mainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DisplayLaw.class);
                startActivity(intent);
            }
        });

        helpButton = findViewById(R.id.buttonToHelp);
        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendNotification();
            }
        });
    }


    LocationListener locationListenerGPS = new LocationListener() {
        @Override
        public void onLocationChanged(android.location.Location location) {
            double latitude=location.getLatitude();
            double longitude=location.getLongitude();
//            String msg="New Latitude: "+latitude + "New Longitude: "+longitude;
//            Toast.makeText(mContext,msg,Toast.LENGTH_LONG).show();
            String state = findState(longitude, latitude);
            if(!state.equals(curState)) {
               curState = state;
               sendNotification();
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
    };


    protected void onResume(){
        super.onResume();
        isLocationEnabled();
    }

    private void isLocationEnabled() {

        if(!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            AlertDialog.Builder alertDialog=new AlertDialog.Builder(mContext);
//            alertDialog.setTitle("Enable Location");
//            alertDialog.setMessage("Your locations setting is not enabled. Please enabled it in settings menu.");
            alertDialog.setPositiveButton("Location Settings", new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialog, int which){
                    Intent intent=new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivity(intent);
                }
            });
            alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialog, int which){
                    dialog.cancel();
                }
            });
            AlertDialog alert=alertDialog.create();
            alert.show();
        }
        else{
//            AlertDialog.Builder alertDialog=new AlertDialog.Builder(mContext);
//            alertDialog.setTitle("Confirm Location");
//            alertDialog.setMessage("Your Location is enabled, please enjoy");
//            alertDialog.setNegativeButton("Back to interface",new DialogInterface.OnClickListener(){
//                public void onClick(DialogInterface dialog, int which){
//                    dialog.cancel();
//                }
//            });
//            AlertDialog alert=alertDialog.create();
//            alert.show();
        }
    }

    private void sendNotification() {
        String CHANNEL_ID = "channel_01";

        Intent intent = new Intent(this, DisplayLaw.class);
        PendingIntent pend = PendingIntent.getActivity(this, 0, intent, 0);

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this, CHANNEL_ID);
        mBuilder.setSmallIcon(R.mipmap.ic_launcher_round);
        mBuilder.setContentTitle("Welcome to " + curState + " !");
        mBuilder.setContentText("You have new laws to check!");
        mBuilder.setPriority(NotificationCompat.PRIORITY_MAX);
        mBuilder.setContentIntent(pend);
        mBuilder.setAutoCancel(true);

        NotificationManager mNotificationManager =
                (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_DEFAULT);
            mNotificationManager.createNotificationChannel(channel);
        }

        mNotificationManager.notify(0, mBuilder.build());

    }


    private String findState(double lng, double lat) {
        Geocoder gcd = new Geocoder(getApplicationContext(), Locale.getDefault());
        try {
            List<Address> addressList = gcd.getFromLocation(lat, lng, 1);
            if(addressList.size() > 0) {
                return addressList.get(0).getAdminArea();
            }
        } catch (IOException e) {
            return "";
        }
        return  "";
    }

}
