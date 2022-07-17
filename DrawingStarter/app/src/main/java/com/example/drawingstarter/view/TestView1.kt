package com.example.drawingstarter.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PathMeasure
import android.util.AttributeSet
import android.view.View
import com.example.drawingstarter.Utils

class TestView1(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    // 抗锯齿的 Flag： Paint.ANTI_ALIAS_FLAG
    private val mPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val path = Path()
    private lateinit var pathMeasure: PathMeasure

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        path.reset()
        path.addRect((width / 2 - 150).toFloat(), (height / 2 - 300).toFloat(),
            (width / 2 + 150).toFloat(), (height / 2).toFloat(), Path.Direction.CCW)
        path.addCircle((width / 2).toFloat(),
            (height / 2).toFloat(), 150F , Path.Direction.CCW)

        pathMeasure = PathMeasure(path, false)
        pathMeasure.length
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        path.fillType = Path.FillType.EVEN_ODD
        canvas?.drawPath(path, mPaint)
//        canvas?.drawLine(100F, 100F, 200F, 200F, mPaint)
//        canvas?.drawCircle((width / 2).toFloat(), (height / 2).toFloat(), Utils.dp2px(100F), mPaint)
    }
}