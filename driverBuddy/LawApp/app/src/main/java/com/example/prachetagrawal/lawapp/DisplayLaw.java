package com.example.prachetagrawal.lawapp;

import android.graphics.drawable.Icon;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class DisplayLaw extends AppCompatActivity {

    private ArrayList<LawStorage> contentCategories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_law);

        RecyclerView recList = findViewById(R.id.drivingList);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        recList.setLayoutManager(llm);

        contentCategories = scanData();

        ListAdapter listView = new ListAdapter(contentCategories);
        recList.setAdapter(listView);

    }

    private ArrayList<LawStorage> scanData() {
        ArrayList<LawStorage> myArray = new ArrayList<>();
        HashMap<String, String> temp = new HashMap<>();
        temp.put("Key1", "Val1");
        temp.put("Key2", "Val2");
        LawStorage a = new LawStorage("Laws", temp);
        myArray.add(a);
        myArray.add(a);
        myArray.add(a);

        return myArray;
    }

}
