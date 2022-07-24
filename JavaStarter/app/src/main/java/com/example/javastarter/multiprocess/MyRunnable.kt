package com.example.javastarter.multiprocess

import android.util.Log

class MyRunnable: Runnable {
    override fun run() {
        Log.i("MyRunnable", "huiqinhuang" + Thread.currentThread().name)
    }
}