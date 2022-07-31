package com.example.touchconfliction

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.ScrollView

class HorizontalScrollView: ScrollView {

    private var downX: Float? = 0.0f
    private var downY: Float? = 0.0f

    constructor(context: Context?) : super(context)

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        val curX = ev?.x
        val curY = ev?.y

        when (ev?.action) {
            MotionEvent.ACTION_DOWN -> {
                downX = curX
                downY = curY
            }
            MotionEvent.ACTION_MOVE -> {
                val deltaX = Math.abs(downX!! - curX!!)
                val deltaY = Math.abs(downY!! - curY!!)

                if (deltaX > deltaY) {
                    return false
                }
            }
        }

        return super.onInterceptTouchEvent(ev)
    }
}