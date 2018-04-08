package com.example.prachetagrawal.lawapp;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import static android.app.NotificationChannel.DEFAULT_CHANNEL_ID;

public class MainActivity extends AppCompatActivity {

    private BroadcastReceiver broadcastReceiver;
    private String curState = "defState";
    private final String notificationText =
            "Click here for new laws to be aware of!";

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

        Button rightBut = findViewById(R.id.buttonToHelp);

        rightBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendNotification();
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

    private void sendNotification() {
        Intent intent = new Intent(this, DisplayLaw.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pend = PendingIntent.getActivity(this,0, intent, 0);


        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(MainActivity.this);
                mBuilder.setSmallIcon(R.mipmap.ic_launcher_round);
                mBuilder.setContentTitle("Welcome to " + curState + " !");
                mBuilder.setContentText(notificationText);
                mBuilder.setPriority(NotificationCompat.PRIORITY_MAX);
                mBuilder.setContentIntent(pend);
                mBuilder.setAutoCancel(true);


        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(0, mBuilder.build());



    }

}
