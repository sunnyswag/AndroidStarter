package com.sunnyswag.staticlayout

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

import java.util.Random

/**
 * 正在播放音乐的 Animation
 */
class MusicVisualizer : View {

    private var random = Random()
    private var paint = Paint()

    /** 圆角 */
    private val round = 15F
    /** 用于在 onMeasure() 之后，记录所有驻条的绘制区域 */
    private val displayMatrix: ArrayList<Pair<Float, Float>> = ArrayList()

    /** 驻条宽度为 [rectWidth]，中间空白为 [rectWidth] * 3 */
    private var rectWidth = 3
    /** 驻条的颜色 */
    private var rectColor: Int = Color.WHITE

    private val animateView = object : Runnable {
        override fun run() {
            postDelayed(this, 120)
            invalidate()
        }
    }

    constructor(context: Context) : super(context) {
        MusicVisualizer(context, null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.MusicVisualizer)
        rectColor = typedArray.getColor(R.styleable.MusicVisualizer_rectColor, Color.WHITE)
        rectWidth = typedArray.getInt(R.styleable.MusicVisualizer_rectWidth, 3)
        typedArray.recycle()

        paint.color = rectColor

        removeCallbacks(animateView)
        post(animateView)
    }

    @SuppressLint("DrawAllocation")
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        var cumulativeWidth = 0F
        while (cumulativeWidth + rectWidth < measuredWidth) {
            displayMatrix.add(Pair(cumulativeWidth, cumulativeWidth + rectWidth))
            cumulativeWidth += rectWidth * 4
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        for (item in displayMatrix) {
            canvas.drawRoundRect(
                item.first,
                getRandomHeight(),
                item.second,
                (measuredHeight).toFloat(),round, round, paint)
        }
    }

    override fun onWindowVisibilityChanged(visibility: Int) {
        super.onWindowVisibilityChanged(visibility)
        if (visibility == VISIBLE) {
            removeCallbacks(animateView)
            post(animateView)
        } else if (visibility == GONE) {
            removeCallbacks(animateView)
        }
    }

    private fun getRandomHeight(): Float {
        val baseHeight = measuredHeight / 6
        val extraHeight = random.nextInt(measuredHeight * 5 / 6)
        return (baseHeight + extraHeight).toFloat()
    }
}