package com.example.prachetagrawal.lawapp;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CategoryInfo extends AppCompatActivity {

    private TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_info);

        String lawTitle = getIntent().getStringExtra("name");
        TextView temp = findViewById(R.id.testText);
        temp.setText(lawTitle);

        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if(i != TextToSpeech.ERROR){
                    Button speech = findViewById(R.id.speechButton);
                    speech.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            speakOut();
                        }
                    });
                }
            }
        });
    }

    private void speakOut() {
        // Get the text typed
        String text = "this is a test";
        // If no text is typed, tts will read out 'You haven't typed text'
        // else it reads out the text you typed

        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);


    }
}
