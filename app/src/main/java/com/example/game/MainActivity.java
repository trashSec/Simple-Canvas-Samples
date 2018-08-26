package com.example.game;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;


public class MainActivity extends Activity {
    Intent birdsIntent, ballsIntent, smileIntent;
    Button birdsButton, ballsButton, smileButton;
    boolean p1 = false, p2 = false, p3 = false;
    ProgressBar progressBar;
    TextView text;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        birdsIntent = new Intent(this, birds.class);
        ballsIntent = new Intent(this, balls.class);
        smileIntent = new Intent(this, Smile.class);

        birdsButton = (Button) findViewById(R.id.b1);
        ballsButton = (Button) findViewById(R.id.b2);
        smileButton = (Button) findViewById(R.id.b3);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        text = (TextView) findViewById(R.id.text);

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
    }

    private class LoadImage extends AsyncTask<Void, Integer, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            text.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.VISIBLE);
        }

        protected Void doInBackground(Void... args) {
            for (int i = 0; i < 100; i += 5) {
                try {
                    Thread.sleep(200);
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
            } else {
                startActivity(smileIntent);
                p3 = false;
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





