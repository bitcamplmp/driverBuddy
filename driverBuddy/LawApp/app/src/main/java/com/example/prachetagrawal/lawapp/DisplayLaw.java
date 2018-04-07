package com.example.prachetagrawal.lawapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class DisplayLaw extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_law);

        RecyclerView recList = findViewById(R.id.drivingList);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        recList.setLayoutManager(llm);
    }


}
