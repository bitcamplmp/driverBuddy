package com.example.prachetagrawal.lawapp;

import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class SpeechVoiceActivity extends AppCompatActivity {
    private TextToSpeech tts;
    private Button btnSpeak;
    private TextView txtText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speech_voice);
        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if(i != TextToSpeech.ERROR){
                    btnSpeak = (Button) findViewById(R.id.tempButt);
                    txtText = (TextView) findViewById(R.id.testText);
                    Button tb = findViewById(R.id.tempButt);
                    tb.setOnClickListener(new View.OnClickListener() {
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
        String text = txtText.getText().toString();
        // If no text is typed, tts will read out 'You haven't typed text'
        // else it reads out the text you typed
        if (text.length() == 0) {
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
        }

    }
}
