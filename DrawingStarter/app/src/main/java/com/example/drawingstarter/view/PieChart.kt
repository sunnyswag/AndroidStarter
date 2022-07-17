package com.example.drawingstarter.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import com.example.drawingstarter.Utils
import kotlin.math.cos
import kotlin.math.sin

class PieChart(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val bounds = RectF()

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        // 画 arc 先画 bounds，再画角度
        bounds.set((width / 2).toFloat() - RADIUS, (height / 2).toFloat() - RADIUS,
            (width / 2).toFloat() + RADIUS, (height / 2).toFloat() + RADIUS)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        var currentAngle = 0F
        for (i in angles.indices) {
            mPaint.color = colors[i]

            canvas?.save()
            if (PULLOUT_INDEX == i) {
                canvas?.translate(
                    (cos(Math.toRadians((currentAngle + angles[i] / 2).toDouble())) * LENGTH).toFloat(),
                    (sin(Math.toRadians((currentAngle + angles[i] / 2).toDouble())) * LENGTH).toFloat())
            }
            canvas?.drawArc(bounds, currentAngle, angles[i], true, mPaint)
            canvas?.restore()

            currentAngle += angles[i]
        }
    }

    companion object{
        val RADIUS = Utils.dp2px(150F) // 半径
        val angles = floatArrayOf(60F, 100F, 120F, 80F) // 每个弧度的角度
        val colors = intArrayOf(Color.parseColor("#e7feea"),
            Color.parseColor("#d8ebf4"),
            Color.parseColor("#6200ee"),
            Color.parseColor("#8cd3ec"))

        const val LENGTH = 20F
        const val PULLOUT_INDEX = 2 // 偏移哪个饼状图
    }
}