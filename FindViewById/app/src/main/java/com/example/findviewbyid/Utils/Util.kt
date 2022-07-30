package com.example.findviewbyid.Utils

import android.view.View
import android.view.ViewGroup

object Util {

    /**
     * 通过 viewID 来找到相应的 View，
     * 如果存在，则返回 View。如果不存在，则返回 null
     *
     * 如果是 ViewGroup ，则递归查找子 View，找到相应的 ID，
     * 如果是 View ，找到则直接返回
     * @param viewGroup 在该 viewGroup 中执行查找
     * @param viewID view 的 ID
     */
    fun findViewById(view: View, viewID: Int): View? {
        if (viewID == view.id) {
            return view
        }

        if (view is ViewGroup) {
            for (i in 0 until view.childCount) {
                val tmpView = findViewById(view.getChildAt(i), viewID)
                tmpView?.let {
                    return tmpView
                }
            }
        }

        return null
    }
}