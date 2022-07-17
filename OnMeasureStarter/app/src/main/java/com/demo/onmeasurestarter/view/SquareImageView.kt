package com.demo.onmeasurestarter.view

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatImageView

class SquareImageView: AppCompatImageView {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs : AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int): super(context, attrs, defStyleAttr)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val test = LinearLayout(context)

        val size = measuredHeight.coerceAtLeast(measuredWidth)
        setMeasuredDimension(size, size) // 保存测得的尺寸
    }
}