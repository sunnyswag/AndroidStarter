package com.demo.onmeasurestarter.view

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout

class TagLayout: LinearLayout {

    private val mChildRectList: ArrayList<Rect> = ArrayList();

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs : AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int): super(context, attrs, defStyleAttr)

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
//        if (mChildRectList.isEmpty()) {
//            return
//        }

        for ( i in 0 until childCount ) {
            val child = getChildAt(i)
            Log.d("huiqinhuang", "left: ${child.left}, top: ${child.top}, right: ${child.right}, bottom: ${child.bottom}, ")
            child.layout(child.left, child.top, child.right, child.bottom)
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
//        var lineHeightUsed: Int = 0
//        var lineWidthUsed: Int = 0
//        var widthUsed: Int = 0
//        var heightUsed: Int = 0
//
//        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
//        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
//
//        for (i in 0 until childCount) {
//            val child: View = getChildAt(i)
//            // widthUsed 设置为 0，不会被挤压
//            measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, heightUsed)
//            if (widthMode != MeasureSpec.UNSPECIFIED && lineWidthUsed + child.measuredWidth > widthSize) {
//                lineWidthUsed = 0
//                heightUsed += lineHeightUsed
//                measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, heightUsed)
//            }
//
//            val childBound: Rect
//            if (mChildRectList.size <= i) {
//                childBound = Rect()
//                mChildRectList.add(childBound)
//            } else {
//                childBound = mChildRectList[i]
//            }
//
//            childBound.set(lineWidthUsed, heightUsed, lineWidthUsed + child.measuredWidth, heightUsed + child.measuredHeight)
//            lineWidthUsed += child.measuredWidth
//            lineHeightUsed = Math.max(lineHeightUsed, child.measuredHeight)
//            widthUsed = Math.max(lineWidthUsed, widthUsed)
//        }
//
//        heightUsed += lineHeightUsed
//        setMeasuredDimension(widthUsed, heightUsed)
    }

//    override fun generateLayoutParams(attrs: AttributeSet?): LayoutParams {
//        return MarginLayoutParams(context, attrs)
//    }

}