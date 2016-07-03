package com.example.androidcustomview;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Created by resin on 03/07/2016.
 */
public class MyButton extends Button {

    private Paint textPaint, paint;
    private String text;
    private int ascent;
    private Shader shader;
    private Matrix matrix = new Matrix();
    private float start;
    private float rotate;
    private float sweep;
    private static float SWEEP_INC = 2;
    private static float START_INC = 15;


    public MyButton(Context context) {
        super(context);
        initLabelView();
    }

    public MyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        initLabelView();
    }

    private final void initLabelView() {

        textPaint = new Paint();
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(16);
        textPaint.setColor(0xFF000000);
        setPadding(15, 15, 15, 15);
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStrokeWidth(4);
        paint.setStyle(Paint.Style.STROKE);
        shader = new SweepGradient(this.getMeasuredWidth() / 2, this.getMeasuredHeight() / 2,
                new int[]{Color.GREEN, Color.RED, Color.CYAN, Color.DKGRAY}, null);
        paint.setShader(shader);
    }

    public void setText(String text) {
        this.text = text;
        requestLayout();
        invalidate();
    }

    @Override
    public void setTextSize(float size) {
        super.setTextSize(size);
        requestLayout();
        invalidate();
    }

    @Override
    public void setTextColor(int color) {
        textPaint.setColor(color);
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec));
    }

    private int measureWidth(int measureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = (int) textPaint.measureText(text) + getPaddingLeft() + getPaddingRight();
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

    private int measureHeight(int measureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        ascent = (int) textPaint.ascent();
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = (int) (-ascent + textPaint.descent() + getPaddingTop() + getPaddingBottom());
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }
}
