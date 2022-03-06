package com.demo.onmeasurestarter.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView

class CircleImageView: AppCompatImageView {

    private val mPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs : AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int): super(context, attrs, defStyleAttr)

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        mPaint.color = Color.BLUE
        canvas?.drawCircle(PADDING + RADIUS, PADDING + RADIUS, RADIUS, mPaint)
    }

    @SuppressLint("RestrictedApi")
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {

        // 不让 view 自己再测一遍，浪费资源
        // super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val expectedSize = (PADDING + RADIUS).toInt() * 2

        // 方式一
        val width: Int = resolveSize(expectedSize, widthMeasureSpec)
        val height: Int = resolveSize(expectedSize, widthMeasureSpec)
        setMeasuredDimension(width, height)

        // 方式二
//        val result = getMeasuredSizeManually(expectedSize, widthMeasureSpec)
//        setMeasuredDimension(result, result)
    }

    private fun getMeasuredSizeManually(expectedSize: Int, widthMeasureSpec: Int): Int {
        // 获取父 View 传来的参数
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)

        val result = if (widthMode == MeasureSpec.AT_MOST) { // 取两者的最小值，相当于 wrap_content
            if (widthSize < expectedSize) widthSize else expectedSize
        } else if (widthMode == MeasureSpec.EXACTLY) { // 父 View 给多少用多少，相当于 match_parent
            widthSize
        } else if (widthMode == MeasureSpec.UNSPECIFIED) { // 没有指定大小，相当于使用子 View 自己指定的大小
            expectedSize
        } else {
            0
        }
        return result
    }

    companion object{
        private const val PADDING: Float = 100.0F
        private const val RADIUS: Float = 400.0F
    }
}