package com.example.game.Smile;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class Smile extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new SmileDraw(this));
    }
}
