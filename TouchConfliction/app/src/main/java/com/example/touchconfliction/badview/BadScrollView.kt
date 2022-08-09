package com.example.touchconfliction.badview

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.widget.ScrollView
import kotlin.math.abs

class BadScrollView(context: Context?, attrs: AttributeSet?) : ScrollView(context, attrs) {

    var lastX = 0
    var lastY = 0

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return interceptEventInside(ev)
    }

    private fun interceptEventInside(ev: MotionEvent?) :Boolean {
        return when (ev?.action) {
            MotionEvent.ACTION_DOWN -> {
                super.onInterceptTouchEvent(ev)
                false
            }
            else -> {
                true
            }
        }
    }

    private fun interceptEventOutside(ev: MotionEvent?): Boolean {
        var intercepted = false
        val x: Int = (ev?.x ?: 0).toInt()
        val y: Int = (ev?.y ?: 0).toInt()

        when (ev?.action) {
            MotionEvent.ACTION_DOWN -> {
                intercepted = false
                super.onInterceptTouchEvent(ev)
            }
            MotionEvent.ACTION_MOVE -> {
                val deltaX = abs(x - lastX)
                val deltaY = abs(y - lastY)
                intercepted = deltaX < deltaY
            }
            MotionEvent.ACTION_UP -> {
                intercepted = false
            }
        }

        lastX = x
        lastY = y

        Log.i("huiqinhuang", "intercepted: $intercepted")

        return intercepted
    }
}