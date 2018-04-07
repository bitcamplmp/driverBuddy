package com.example.prachetagrawal.lawapp;

import static org.junit.Assert.*;

public class SpeechVoiceTest {
    public void test1() {
        SpeechVoice test = new SpeechVoice();
        String t1 = "Read Aloud";
        try {
            test.readSentence(t1);
        } catch(Exception e){
            System.out.print("Error");
        }
    }
}