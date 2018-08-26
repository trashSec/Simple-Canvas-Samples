package com.example.game;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class birds extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new GameView(this));
    }
}
