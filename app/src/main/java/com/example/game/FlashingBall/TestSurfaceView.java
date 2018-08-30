package com.example.game.FlashingBall;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

class TestSurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    MyDrawThread thread;

    class MyDrawThread extends Thread {
        SurfaceHolder holder;
        boolean running = true;
        int x = 1;

        MyDrawThread(SurfaceHolder holder) {
            this.holder = holder;
        }

        @Override
        public void run() {
            for (int i = 1; i > 0; i++) {
                Canvas canvas = holder.lockCanvas();
                canvas.drawColor(Color.YELLOW);

                if (x == 1) {
                    Paint paint = new Paint();
                    paint.setColor(Color.RED);
                    canvas.drawCircle(getWidth() / 2, getHeight() / 2, getWidth() / 2, paint);
                    x = 2;
                } else if (x != 1) {
                    try {
                        sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    x = 1;
                }
                holder.unlockCanvasAndPost(canvas);
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public TestSurfaceView(Context context) {
        super(context);
        getHolder().addCallback(this);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread = new MyDrawThread(holder);
        thread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        thread.running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
