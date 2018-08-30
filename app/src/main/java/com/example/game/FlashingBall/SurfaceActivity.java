package com.example.game.FlashingBall;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class SurfaceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new TestSurfaceView(this));
    }
}
