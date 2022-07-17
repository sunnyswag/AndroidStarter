package com.demo.onmeasurestarter.view

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.ViewGroup
import java.lang.Integer.max

class TagLayoutV2: ViewGroup {

    private val childBounds: ArrayList<Rect> = ArrayList()

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs : AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int): super(context, attrs, defStyleAttr)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var widthUsed = 0
        var heightUsed = 0
        var lineMaxHeight = 0

        for (i in 0 until childCount) {
            val child = getChildAt(i)
            measureChildWithMargins(child, widthMeasureSpec, widthUsed, heightMeasureSpec, heightUsed)

            val childBound: Rect
            if (childBounds.size <= i) {
                childBound = Rect()
                childBounds.add(childBound)
            } else {
                childBound = childBounds[i]
            }

            childBound.set(widthUsed, heightUsed,
                widthUsed + child.measuredWidth, heightUsed + child.measuredHeight)
            widthUsed += child.measuredWidth

            lineMaxHeight = max(lineMaxHeight, child.measuredHeight)
        }

        setMeasuredDimension(widthUsed, lineMaxHeight)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        for (i in 0 until childCount) {
            val childRect = childBounds[i]
            getChildAt(i).layout(childRect.left, childRect.top,
                childRect.right, childRect.bottom)
        }
    }

    override fun generateLayoutParams(attrs: AttributeSet?): LayoutParams {
        return MarginLayoutParams(context, attrs)
    }
}