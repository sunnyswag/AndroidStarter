package com.example.javastarter.multiprocess

import android.util.Log

class MyThread: Thread() {
    override fun run() {
        super.run()
        Log.i("MyThread", "huiqinhuang" + currentThread().id.toString())
    }
}