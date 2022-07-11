package com.example.fourcomponentstarter

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log

class MainService: Service() {

    private val TAG = "MainService"

    private val mBinder = DownloadBinder()


    class DownloadBinder: Binder() {

        fun startDownload() {
            Log.d("DownloadBinder", "startDownload()")
        }

        fun getProgress(): Int {
            Log.d("DownloadBinder", "getProgress()")
            return 0
        }
    }

    override fun onBind(intent: Intent?): IBinder {
        Log.d(TAG, "onBind()")
        return mBinder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.d(TAG, "onUnbind()")
        return false
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate()")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand()")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy()")
    }
}