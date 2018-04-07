package com.example.prachetagrawal.lawapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    public String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SpeechVoice speach = new SpeechVoice(savedInstanceState);
        speach.readSentence(text, savedInstanceState);
    }
}
