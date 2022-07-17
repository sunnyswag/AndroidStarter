package com.example.drawingstarter.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.drawingstarter.Utils

class Dashboard(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val dash = Path()

    private lateinit var pathEffect: PathDashPathEffect

    init {
        dash.addRect(0F, 0F, Utils.dp2px(2F), Utils.dp2px(10F), Path.Direction.CW)
        pathEffect = PathDashPathEffect(dash, 50F, 0F, PathDashPathEffect.Style.ROTATE)

        mPaint.let {
            it.style = Paint.Style.STROKE
            it.strokeWidth = Utils.dp2px(2F)
        }

        PathMeasure(
            Path().apply { addArc((width / 2).toFloat() - RADIUS, (height / 2).toFloat() - RADIUS,
                (width / 2).toFloat() + RADIUS, (height / 2).toFloat() + RADIUS,
                (90 + ANGLE / 2).toFloat(), (360 - ANGLE).toFloat()) },
            false
        )

        pathEffect = PathDashPathEffect(dash, 50F, 0F, PathDashPathEffect.Style.ROTATE)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        // 画线
        canvas?.drawArc((width / 2).toFloat() - RADIUS, (height / 2).toFloat() - RADIUS,
            (width / 2).toFloat() + RADIUS, (height / 2).toFloat() + RADIUS,
            (90 + ANGLE / 2).toFloat(), (360 - ANGLE).toFloat(), false, mPaint)

        // 画刻度
        mPaint.pathEffect = pathEffect
        canvas?.drawArc((width / 2).toFloat() - RADIUS, (height / 2).toFloat() - RADIUS,
            (width / 2).toFloat() + RADIUS, (height / 2).toFloat() + RADIUS,
            (90 + ANGLE / 2).toFloat(), (360 - ANGLE).toFloat(), false, mPaint)
    }

    companion object{
        const val ANGLE = 120
        val RADIUS = Utils.dp2px(150F)
    }
}