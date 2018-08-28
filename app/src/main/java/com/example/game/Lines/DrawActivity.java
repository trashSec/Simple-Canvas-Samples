package com.example.game.Lines;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class DrawActivity extends View {
    public DrawActivity(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);
        int i = 0;
        while (i < canvas.getWidth()) {
            canvas.drawLine(getWidth() - i, getHeight() + i, 1 - i, 1 + i, paint);
            canvas.drawLine(getWidth() + i, getHeight() - i, 1 + i, 1 - i, paint);
            i = i + 50;
        }
    }
}
