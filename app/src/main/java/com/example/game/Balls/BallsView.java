package com.example.game.Balls;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.CountDownTimer;
import android.view.View;

public class BallsView extends View {
    public BallsView(Context context) {
        super(context);
        MyTimer timer = new MyTimer();
        timer.start();
    }

    class Ball {
        int x, y, r, color, vx, vy;

        Ball(int x, int y, int r, int color, int vx, int vy) {
            this.x = x;
            this.y = y;
            this.r = r;
            this.color = color;
            this.vx = vx;
            this.vy = vy;
        }
    }

    private Ball[] balls = {new Ball(250, 300, 110, Color.parseColor("#0090FF"), 32, -4),
            new Ball(200, 500, 130, Color.parseColor("#3D00FF"), 10, 13),
            new Ball(530, 200, 75, Color.parseColor("#FFD300"), -7, 22),
            new Ball(300, 330, 100, Color.parseColor("#FF8080"), 7, 23),
            new Ball(350, 430, 110, Color.parseColor("#FF9200"), 13, -2),
            new Ball(140, 400, 95, Color.rgb(80, 100, 255), 3, 7),
            new Ball(350, 130, 70, Color.rgb(158, 80, 255), 4, -9),
            new Ball(195, 230, 60, Color.parseColor("#FFE980"), 13, 20),
            new Ball(640, 230, 50, Color.parseColor("#FFC880"), 20, -35)
    };

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.parseColor("#A000BFFF"));
        canvas.drawPaint(paint);
        for (int i = 0; i < balls.length; i++) {
            paint.setColor(balls[i].color);
            canvas.drawCircle(balls[i].x, balls[i].y, balls[i].r, paint);
        }
    }

    class MyTimer extends CountDownTimer {

        MyTimer() {
            super(Integer.MAX_VALUE, 100);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            for (Ball ball : balls) {
                if (ball.x + ball.vx < getWidth() - ball.r && ball.x + ball.vx > ball.r)
                    ball.x += ball.vx;
                else ball.vx = -ball.vx;
                if (ball.y + ball.vy < getHeight() - ball.r && ball.y + ball.vy > ball.r)
                    ball.y += ball.vy;
                else ball.vy = -ball.vy;
            }
            invalidate();
        }

        @Override
        public void onFinish() {
        }
    }
}
