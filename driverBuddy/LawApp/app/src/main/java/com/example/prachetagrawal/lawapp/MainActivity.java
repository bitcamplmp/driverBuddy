package com.example.prachetagrawal.lawapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    public String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SpeechVoice speach = new SpeechVoice(savedInstanceState);
        speach.readSentence(text, savedInstanceState);
        Button mainButton = findViewById(R.id.buttonToMain);
        mainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DisplayLaw.class);
                startActivity(intent);
            }
        });
    }
}
