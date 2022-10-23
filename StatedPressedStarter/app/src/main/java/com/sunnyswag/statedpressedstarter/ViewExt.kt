package com.sunnyswag.statedpressedstarter

import android.annotation.SuppressLint
import android.view.MotionEvent
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.Group

/**
 * 获取当前 Group 下的 View，因为是 getViewById，所以不会存在性能问题
 */
fun Group.getViews(): List<View> =
    this.referencedIds.map {
        (this.parent as ConstraintLayout).getViewById(it)
    }

/**
 * 对当前 Group 下的 View 实现按下透明度变化的效果
 */
fun Group.fadeWhenTouch(pressedAlpha: Float = 0.5F) {
    this.getViews().forEach {
        it.fadeWhenTouch(pressedAlpha)
    }
}

/**
 * 对当前 View 实现按下透明度变化的效果
 */
@SuppressLint("ClickableViewAccessibility")
fun View.fadeWhenTouch(pressedAlpha: Float = 0.5F) {
    this.setOnTouchListener { v, event ->
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> v?.alpha = pressedAlpha
            MotionEvent.ACTION_UP -> v?.alpha = 1F
            MotionEvent.ACTION_CANCEL -> v?.alpha = 1F
        }
        false
    }
}