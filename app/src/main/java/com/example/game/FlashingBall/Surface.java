package com.example.game.FlashingBall;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class Surface extends SurfaceView implements SurfaceHolder.Callback {
    int x = 200, y = 200, r = 70;
    DrawThread thread;

    public Surface(Context context) {
        super(context);
        getHolder().addCallback(this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        x = (int) event.getX();
        y = (int) event.getY();
        return false;

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread = new DrawThread(getHolder());
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        thread.requestStop();
        boolean retry = true;
        while (retry) {
            try {
                thread.join();
                retry = false;
            } catch (InterruptedException e) {
            }
        }
    }

    class DrawThread extends Thread {
        private volatile boolean running = true;
        private SurfaceHolder surfaceHolder;

        public void requestStop() {
            running = false;
        }

        public DrawThread(SurfaceHolder surfaceHolder) {
            this.surfaceHolder = surfaceHolder;
        }

        @Override
        public void run() {
            Canvas canvas = null;
            while (running) {
                try {
                    canvas = surfaceHolder.lockCanvas();
                    Paint paint = new Paint();
                    paint.setColor(Color.YELLOW);
                    canvas.drawPaint(paint);
                    paint.setColor(Color.RED);
                    canvas.drawCircle(x, y, r, paint);
                    x += 2;
                } finally {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
                try {
                    sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
