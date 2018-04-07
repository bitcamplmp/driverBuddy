package com.example.prachetagrawal.lawapp;

import android.content.Context;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;

import java.util.Locale;

public class SpeechVoice {
    public TextToSpeech reader; // Reader to take in string and read laws
    public Context context;

    public SpeechVoice(Bundle Instance){
        reader = new TextToSpeech(context.getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if(i != TextToSpeech.ERROR){
                    reader.setLanguage(Locale.US);
                }
            }
        });
    }


    public void readSentence(String sentence, Bundle instance){
        String utteranceId = this.hashCode() + "";
        reader.speak(sentence, TextToSpeech.QUEUE_FLUSH, instance, utteranceId);
    }
}
