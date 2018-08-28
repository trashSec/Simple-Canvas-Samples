package com.example.game.Lines;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class Lines extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new DrawActivity(this));
    }
}
