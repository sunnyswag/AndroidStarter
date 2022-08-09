package com.example.touchconfliction.badview

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.abs

class BadRecyclerView(context: Context, attrs: AttributeSet?) : RecyclerView(context, attrs) {

    var lastX = 0
    var lastY = 0

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        return dispatchTouchEventInside(ev)
    }

    private fun dispatchTouchEventInside(ev: MotionEvent?): Boolean {
        val x: Int = (ev?.x ?: 0).toInt()
        val y: Int = (ev?.y ?: 0).toInt()

        when (ev?.action) {
            MotionEvent.ACTION_DOWN -> {
                parent.requestDisallowInterceptTouchEvent(true)
            }
            MotionEvent.ACTION_MOVE -> {
                val deltaX = abs(x - lastX)
                val deltaY = abs(y - lastY)
                parent.requestDisallowInterceptTouchEvent(deltaX > deltaY)
            }
            MotionEvent.ACTION_UP -> {

            }
        }

        lastX = x
        lastY = y

        return super.dispatchTouchEvent(ev)
    }
}