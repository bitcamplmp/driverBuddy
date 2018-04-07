package com.example.prachetagrawal.lawapp;

import android.content.Context;
import android.speech.tts.TextToSpeech;

import java.util.Locale;

public class SpeechVoice {
    public TextToSpeech reader; // Reader to take in string and read laws
    public Context context;

    public SpeechVoice(){
        reader = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if(i != TextToSpeech.ERROR){
                    reader.setLanguage(Locale.US);
                }
            }
        });
    }

    public void readSentence(String sentence){

    }
}
