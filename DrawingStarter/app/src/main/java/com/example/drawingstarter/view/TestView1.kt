package com.example.drawingstarter.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class TestView1(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    // 抗锯齿的 Flag： Paint.ANTI_ALIAS_FLAG
    private val mPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.drawLine(100F, 100F, 200F, 200F, mPaint)
    }
}