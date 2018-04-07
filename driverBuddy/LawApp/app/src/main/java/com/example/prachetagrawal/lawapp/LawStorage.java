package com.example.prachetagrawal.lawapp;

import android.graphics.drawable.Icon;
import android.media.Image;

import java.util.HashMap;

/**
 * Created by Lucas on 4/7/2018.
 */

public class LawStorage {
    //protected R.drawable logo;
    protected String mainTitle;
    protected HashMap<String, String> laws;

    public LawStorage(String mainTitle, HashMap<String, String> laws) {
        this.mainTitle = mainTitle;
        this.laws = laws;
    }


}
