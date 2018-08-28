package com.example.game.Recurs;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class Recurs extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new RecursDraw(this));
    }
}
