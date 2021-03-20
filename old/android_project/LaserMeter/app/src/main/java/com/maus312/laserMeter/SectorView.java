package com.maus312.laserMeter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class SectorView extends View {
    Paint gridPaint;
    Paint targetPaint;
    Paint beamPaint;
    Point center;

    float width;
    float height;
    float side;


    public SectorView(Context context) {
        super(context);
        init();
    }

    public SectorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SectorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public SectorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    void init() {
        gridPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        gridPaint.setColor(Color.BLACK);
        gridPaint.setStyle(Paint.Style.STROKE);

        targetPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        targetPaint.setColor(Color.RED);

        beamPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        beamPaint.setColor(Color.YELLOW);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawGrid(canvas);
    }

    private void drawGrid(Canvas canvas) {
        drawLines(canvas,10);
        drawRadius(180, 90, side/6, canvas);
    }

    private void drawLines(Canvas canvas,int quantities) {
        float angleStep =(float) (0.5f * Math.PI / (quantities));

        for (int iDiv = 5; iDiv < quantities; ++iDiv)
        {
            float ang = (float) (-0.25f * 3.14 + (iDiv) * angleStep);
            float r = (float) (side /Math.cos(ang));
            canvas.drawLine((float)(r * -Math.cos(ang))+center.getX(),(float) (r * -Math.sin(ang))+center.getY(),center.getX(), center.getY(),gridPaint);
        }

        for (int iDiv = 0; iDiv < (quantities/2)+1; ++iDiv)
        {
            float ang = (float) (-0.25f * 3.14 + (iDiv) * angleStep);
            float r = (float) (side /Math.cos(ang));
            canvas.drawLine((float)(r * Math.sin(ang))+center.getX(),(float) (r * -Math.cos(ang))+center.getY(),center.getX(), center.getY(),gridPaint);
        }
    }

    private void drawRadius(float startAngle, float endAngle, float step, Canvas canvas) {
        for (int i = 0; i <= Math.ceil(side/step); i++) {
            final RectF oval = new RectF();
            oval.set(side - step * i,side - step * i,side + step * i,side + step * i);
            canvas.drawArc(oval, startAngle, endAngle, true, gridPaint);
        }
    }

    public void initSizeParam() {
        center = new Point();
        width = getWidth();
        height = getHeight();
        center.setX(width);
        center.setY(height);

        side = width  > height ? height  : width;
        invalidate();
    }
}
