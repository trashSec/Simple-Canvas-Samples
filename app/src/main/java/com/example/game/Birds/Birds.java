package com.example.game.Birds;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Birds extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new GameView(this));
    }
}
