package com.example.drawingstarter

import android.content.res.Resources
import android.util.TypedValue

object Utils {

    @JvmStatic
    fun dp2px(dp: Float): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, dp, Resources.getSystem().displayMetrics)
    }
}