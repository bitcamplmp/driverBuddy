package com.example.prachetagrawal.lawapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class CategoryInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_info);

        String lawTitle = getIntent().getStringExtra("name");
        TextView temp = findViewById(R.id.testText);
        temp.setText(lawTitle);
    }
}
