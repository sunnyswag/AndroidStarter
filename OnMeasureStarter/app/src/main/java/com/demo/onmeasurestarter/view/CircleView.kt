package com.demo.onmeasurestarter.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.demo.onmeasurestarter.Utils

class CircleView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = (PADDING + RADIUS).toInt() * 2
        val height = (PADDING + RADIUS).toInt() * 2
//        setMeasuredDimension(
//            (PADDING + RADIUS).toInt() * 2,
//            (PADDING + RADIUS).toInt() * 2)

        setMeasuredDimension(resolveSize(width, widthMeasureSpec),
                    resolveSize(height, heightMeasureSpec))
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.drawColor(Color.BLUE)
        canvas?.drawCircle(PADDING + RADIUS, PADDING + RADIUS, RADIUS, mPaint)
    }

    companion object{
        val RADIUS = Utils.dp2px(80F)
        val PADDING = Utils.dp2px(30F)
    }
}