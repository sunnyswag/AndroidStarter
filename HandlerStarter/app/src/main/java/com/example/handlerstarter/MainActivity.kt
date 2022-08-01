package com.example.handlerstarter

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import java.lang.ref.WeakReference
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val handler = MyHandler(this)
        val msg = Message.obtain().apply {
            what = 0
            obj = "test handler message!"
        }

        thread {
            handler.sendMessage(msg)
            Log.i("huiqinhuang", "3527-3576" + "watch out thread id!")
        }
        msg.apply {

        }
    }

    class MyHandler(): Handler(Looper.getMainLooper()) {

        private lateinit var mActivity: WeakReference<Activity>

        constructor(activity: Activity) : this() {
            this.mActivity = WeakReference(activity)
        }

        override fun handleMessage(msg: Message) {
            when (msg.what) {
                0 -> {
                    Log.i("huiqinhuang", "3527-3527" + msg.obj.toString())}
            }
        }
    }
}