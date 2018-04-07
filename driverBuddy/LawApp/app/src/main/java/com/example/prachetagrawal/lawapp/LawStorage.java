package com.example.prachetagrawal.lawapp;

import android.graphics.drawable.Icon;
import android.media.Image;

import java.util.HashMap;

/**
 * Created by Lucas on 4/7/2018.
 */

public class LawStorage {
    protected Icon logo;
    protected String mainTitle;
    protected HashMap<String, String> laws;

    public LawStorage(Icon logo, String mainTitle, HashMap<String, String> laws) {
        this.logo = logo;
        this.mainTitle = mainTitle;
        this.laws = laws;
    }


}
