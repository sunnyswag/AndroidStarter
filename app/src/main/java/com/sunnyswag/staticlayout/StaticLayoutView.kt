package com.sunnyswag.staticlayout

import android.content.Context
import android.graphics.Canvas
import android.text.StaticLayout
import android.text.TextPaint
import android.util.AttributeSet
import android.util.Log
import android.view.View

class StaticLayoutView: View {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    private val paint = TextPaint()

    init {
        paint.textSize = 55F
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val staticLayout = StaticLayout.Builder.obtain(
            testText, 0, testText.length,
            paint, measuredWidth).build()

        Log.d("huiqinhuang", "staticLayout.height: ${staticLayout.height}" )
        Log.d("huiqinhuang", "staticLayout.lineCount: ${staticLayout.lineCount}" )
        canvas?.save()
        canvas?.translate(0F, 100F)
        staticLayout.draw(canvas)
        canvas?.restore()
    }

    companion object {
        const val testText = "收到反馈上了飞机上看了飞机上看风景里都是风急浪大开始放假快放假的路上"
    }
}