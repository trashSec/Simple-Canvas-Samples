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
import com.example.game.FlashingBall.SurfaceActivity;
import com.example.game.Lines.Lines;
import com.example.game.Recurs.Recurs;
import com.example.game.Smile.Smile;


public class MainActivity extends Activity {
    private Intent ballsIntent, smileIntent, linesIntent, recursIntent, flashingIntent;
    private Button ballsButton, smileButton, linesButton, recursButton, flashingButton;
    private boolean p1 = false, p2 = false, p3 = false, p4 = false, p5 = false;
    private ProgressBar progressBar;
    private TextView text;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialIntents();

        initialButtons();

        progressBar = findViewById(R.id.progressBar);
        text = findViewById(R.id.text);

        buttonsSetTasks();
    }

    private void initialIntents() {
        ballsIntent = new Intent(this, Balls.class);
        smileIntent = new Intent(this, Smile.class);
        linesIntent = new Intent(this, Lines.class);
        recursIntent = new Intent(this, Recurs.class);
        flashingIntent = new Intent(this, SurfaceActivity.class);
    }

    private void initialButtons() {
        ballsButton = findViewById(R.id.balls_button);
        smileButton = findViewById(R.id.smile_button);
        linesButton = findViewById(R.id.lines_button);
        recursButton = findViewById(R.id.recurs_button);
        flashingButton = findViewById(R.id.flashing_button);
    }

    private void buttonsSetTasks() {
        ballsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p1 = true;
                new LoadImage().execute();

            }
        });
        smileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p2 = true;
                new LoadImage().execute();

            }
        });
        linesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p3 = true;
                new LoadImage().execute();
            }
        });
        recursButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p4 = true;
                new LoadImage().execute();
            }
        });
        flashingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p5 = true;
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
                startActivity(ballsIntent);
                p1 = false;
            } else if (p2) {
                startActivity(smileIntent);
                p2 = false;
            } else if (p3) {
                startActivity(linesIntent);
                p3 = false;
            } else if (p4) {
                startActivity(recursIntent);
                p4 = false;
            } else {
                startActivity(flashingIntent);
                p5 = false;
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





