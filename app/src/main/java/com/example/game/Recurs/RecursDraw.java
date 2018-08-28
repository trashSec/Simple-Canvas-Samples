package com.example.game.Recurs;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class RecursDraw extends View {
    int x, y, r = 175;

    public RecursDraw(Context context) {
        super(context);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        x = w / 2;
        y = h / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        drawCircles(x, y, r, canvas, paint);
    }

    private void drawCircles(int x, int y, int r, Canvas canvas, Paint paint) {
        if (r < 1) return;
        canvas.drawCircle(x, y, r, paint);
        drawCircles(x + r * 2, y, r / 3, canvas, paint);
        drawCircles(x - r * 2, y, r / 3, canvas, paint);
        drawCircles(x, y + r * 2, r / 3, canvas, paint);
        drawCircles(x, y - r * 2, r / 3, canvas, paint);
    }
}
