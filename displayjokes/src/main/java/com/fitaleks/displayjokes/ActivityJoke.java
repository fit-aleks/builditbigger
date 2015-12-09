package com.fitaleks.displayjokes;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by alexanderkulikovskiy on 09.12.15.
 */
public class ActivityJoke extends AppCompatActivity {
    public static final String KEY_JOKE = "key_joke";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.fitaleks.displayjokes.R.layout.activity_joke);
    }
}
