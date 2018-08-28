package com.example.game;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.game.Balls.Balls;
import com.example.game.Birds.Birds;
import com.example.game.Lines.Lines;
import com.example.game.Smile.Smile;


public class MainActivity extends Activity {
    Intent birdsIntent, ballsIntent, smileIntent, linesIntent;
    Button birdsButton, ballsButton, smileButton, linesButton;
    boolean p1 = false, p2 = false, p3 = false, p4 = false;
    ProgressBar progressBar;
    TextView text;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        birdsIntent = new Intent(this, Birds.class);
        ballsIntent = new Intent(this, Balls.class);
        smileIntent = new Intent(this, Smile.class);
        linesIntent = new Intent(this, Lines.class);


        birdsButton = findViewById(R.id.birds_button);
        ballsButton = findViewById(R.id.balls_button);
        smileButton = findViewById(R.id.smile_button);
        linesButton = findViewById(R.id.lines_button);

        progressBar = findViewById(R.id.progressBar);
        text = findViewById(R.id.text);

        birdsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                p1 = true;
                new LoadImage().execute();

            }
        });
        ballsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                p2 = true;
                new LoadImage().execute();

            }
        });
        smileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                p3 = true;
                new LoadImage().execute();

            }
        });
        linesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p4 = true;
                new LoadImage().execute();
            }
        });
    }

    private class LoadImage extends AsyncTask<Void, Integer, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            text.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.VISIBLE);
        }

        protected Void doInBackground(Void... args) {
            for (int i = 0; i < 100; i += 1) {
                try {
                    Thread.sleep(20);
                    publishProgress(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            publishProgress(100);
            return null;
        }

        protected void onPostExecute(Void image) {
            text.setText("Задача завершена");
            if (p1) {
                startActivity(birdsIntent);
                p1 = false;
            } else if (p2) {
                startActivity(ballsIntent);
                p2 = false;
            } else if (p3) {
                startActivity(smileIntent);
                p3 = false;
            } else {
                startActivity(linesIntent);
                p4 = false;
            }
            text.setVisibility(View.INVISIBLE);
            progressBar.setVisibility(View.INVISIBLE);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressBar.setProgress(values[0]);
            text.setText("Выполнено : " + values[0] + "/100");

        }
    }
}





