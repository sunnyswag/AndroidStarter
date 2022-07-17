package com.example.animationstarter.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class CircleView(context: Context?, attrs: AttributeSet?) : View(context, attrs){

    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    private var radius = 100F
        set(value) {
            field = value
            invalidate()
        }

    init {
        mPaint.color = Color.RED
    }

    @Override
    protected override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawCircle((width / 2).toFloat(), (height / 2).toFloat(), radius, mPaint);
    }
}