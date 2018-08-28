package com.example.game.Birds;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.CountDownTimer;
import android.view.MotionEvent;
import android.view.View;

import com.example.game.R;

public class GameView extends View {

    private Sprite playerBird;
    private Sprite enemyBird;
    private Sprite coin;

    private int viewWidth;
    private int viewHeight;

    private int points = 0;
    private int coins = 0;

    private final int timerInterval = 15;

    public GameView(Context context) {
        super(context);

        Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.player2);
        int w = b.getWidth() / 4;
        int h = b.getHeight() / 4;
        Rect firstFrame = new Rect(0, 0, w, h);
        playerBird = new Sprite(10, 0, 0, 100, firstFrame, b);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                if (i == 2 && j == 3) {
                    continue;
                }
                playerBird.addFrame(new Rect(j * w, i * h, j * w + w, i * w + w));
            }
        }

        b = BitmapFactory.decodeResource(getResources(), R.drawable.enemy);
        w = b.getWidth() / 5;
        h = b.getHeight() / 3;
        firstFrame = new Rect(4 * w, 0, 5 * w, h);

        enemyBird = new Sprite(2000, 250, -300, 0, firstFrame, b);

        for (int i = 0; i < 3; i++) {
            for (int j = 4; j >= 0; j--) {
                if (i == 0 && j == 4) {
                    continue;
                }
                if (i == 2 && j == 0) {
                    continue;
                }
                enemyBird.addFrame(new Rect(j * w, i * h, j * w + w, i * w + w));
            }
        }

        b = BitmapFactory.decodeResource(getResources(), R.drawable.coinsprite);
        w = b.getWidth();
        h = b.getHeight();
        firstFrame = new Rect(0, 0, w, h);
        coin = new Sprite(2000, 250, -125, 0, firstFrame, b);

        coin.addFrame(new Rect(w / h, h / w, w, h));

        Timer t = new Timer();
        t.start();
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        viewWidth = w;
        viewHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawARGB(250, 127, 199, 255);
        playerBird.draw(canvas);
        enemyBird.draw(canvas);
        coin.draw(canvas);

        Paint p = new Paint();
        p.setAntiAlias(true);
        p.setTextSize(55.0f);
        p.setColor(Color.WHITE);
        Bitmap c = BitmapFactory.decodeResource(getResources(), R.drawable.sprite2);
        canvas.drawBitmap(c, viewWidth - 100, 27, p);
        canvas.drawText(coins + "", viewWidth - 50, 70, p);
        canvas.drawText("score: " + points + "", viewWidth - 350, 70, p);
    }

    protected void update() {
        playerBird.update(timerInterval);
        enemyBird.update(timerInterval);
        coin.update(timerInterval);

        if (playerBird.getY() + playerBird.getFrameHeight() > viewHeight) {
            playerBird.setY(viewHeight - playerBird.getFrameHeight());
            playerBird.setVy(-playerBird.getVy());
            points++;
        } else if (playerBird.getY() < 0) {
            playerBird.setY(0);
            playerBird.setVy(-playerBird.getVy());
            points++;
        }

        if (enemyBird.getX() < -enemyBird.getFrameWidth()) {
            teleportEnemy();
            points += 20;
        }

        if (enemyBird.intersect(playerBird)) {
            teleportEnemy();
            points -= 40;
        }

        if (coin.getX() < -coin.getFrameWidth()) {
            teleportCoin();
        }
        if (coin.intersect(playerBird)) {
            points += 20;
            coins += 1;
            teleportCoin();
        }
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int eventAction = event.getAction();
        if (eventAction == MotionEvent.ACTION_DOWN) {

            if (event.getY() < playerBird.getBoundingBoxRect().top) {
                playerBird.setVy(-100);
            } else if (event.getY() > (playerBird.getBoundingBoxRect().bottom)) {
                playerBird.setVy(100);
            }
        }

        return true;
    }

    private void teleportEnemy() {
        enemyBird.setX(viewWidth + Math.random() * 500);
        enemyBird.setY(Math.random() * (viewHeight - enemyBird.getFrameHeight()));
    }

    private void teleportCoin() {
        coin.setX(viewWidth + Math.random() * getHeight());
        coin.setY(Math.random() * (viewHeight - coin.getFrameHeight()));
    }

    class Timer extends CountDownTimer {

        public Timer() {
            super(Integer.MAX_VALUE, timerInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            update();
        }

        @Override
        public void onFinish() {

        }
    }
}

