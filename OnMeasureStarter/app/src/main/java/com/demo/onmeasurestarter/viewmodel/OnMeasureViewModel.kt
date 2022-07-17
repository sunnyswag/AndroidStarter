package com.demo.onmeasurestarter.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class OnMeasureViewModel: ViewModel() {

    // 上次点击时的宽度
    val widthLastTime = MutableLiveData(10)
    // 上次点击时的长度
    val heightLastTime = MutableLiveData(900)

    inner class Text()
    class Text1()

    val test = Runnable { TODO("Not yet implemented") }

}