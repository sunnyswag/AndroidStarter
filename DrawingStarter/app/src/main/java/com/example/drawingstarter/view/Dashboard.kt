package com.example.drawingstarter.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.annotation.IntRange
import com.example.drawingstarter.Utils
import kotlin.math.cos
import kotlin.math.sin

class Dashboard(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val dash = Path()

    private val pathEffect: PathDashPathEffect

    init {
        dash.addRect(0F, 0F, Utils.dp2px(2F), Utils.dp2px(10F), Path.Direction.CW)

        mPaint.let {
            it.style = Paint.Style.STROKE
            it.strokeWidth = Utils.dp2px(2F)
        }

        // 获取到 Arc 的长度
        val pathMeasure = PathMeasure(
            Path().apply { addArc((width / 2).toFloat() - RADIUS, (height / 2).toFloat() - RADIUS,
                (width / 2).toFloat() + RADIUS, (height / 2).toFloat() + RADIUS,
                (90 + ANGLE / 2).toFloat(), (360 - ANGLE).toFloat()) },
            false
        )

        pathEffect = PathDashPathEffect(dash, (pathMeasure.length - Utils.dp2px(2F)) / SUB, 0F, PathDashPathEffect.Style.ROTATE)
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

        // 画指针
        mPaint.pathEffect = null
        canvas?.drawLine((width / 2).toFloat(), (height / 2).toFloat(),
            (cos(getAngleFromMark(80)) * LENGTH + width / 2).toFloat(),
            (sin(getAngleFromMark(80)) * LENGTH + height / 2).toFloat(),
            mPaint)
    }

    companion object{
        const val ANGLE = 120 // 仪表盘下边的角度
        const val SUB = 20 // 仪表盘刻度的份数
        val RADIUS = Utils.dp2px(150F) // 仪表盘的半径
        val LENGTH = Utils.dp2px(100F) // 指针的长度

        fun getAngleFromMark(@IntRange(from = 0L, to = 100L) mark: Int): Double {
            return ((90 + ANGLE.toFloat() / 2).toDouble()
                    + (360 - ANGLE.toFloat()) * (mark / 100) * 20)
        }
    }
}