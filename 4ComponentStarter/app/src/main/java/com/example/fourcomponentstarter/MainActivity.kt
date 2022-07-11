package com.example.fourcomponentstarter

import android.content.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.Button
import com.example.fourcomponentstarter.broadcast.TimeChangeReceiver

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    lateinit var downloadBinder: MainService.DownloadBinder
    lateinit var timeChangeReceiver: TimeChangeReceiver

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            Log.d("MainService", "onServiceConnected()")
            downloadBinder = service as MainService.DownloadBinder
            downloadBinder.startDownload()
            downloadBinder.getProgress()
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            TODO("Not yet implemented")
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.startService).setOnClickListener {
            val intent = Intent(this, MainService::class.java)
            startService(intent)
        }

        findViewById<Button>(R.id.stopService).setOnClickListener {
            val intent = Intent(this, MainService::class.java)
            stopService(intent)
        }

        findViewById<Button>(R.id.bindService).setOnClickListener {
            val intent = Intent(this, MainService::class.java)
            bindService(intent, connection, Context.BIND_AUTO_CREATE)
        }

        findViewById<Button>(R.id.unbindService).setOnClickListener {
            unbindService(connection)
        }

        findViewById<Button>(R.id.sendBroadcast).setOnClickListener {
            val intent = Intent("com.example.fourcomponentstarter.MY_BROADCAST")
            intent.setPackage(packageName)
//            sendBroadcast(intent)
            sendOrderedBroadcast(intent, null)
        }

        Log.d(TAG, "onCreate(), ${savedInstanceState?.getString(TAG)}")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart()")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.d(TAG, "onRestoreInstanceState(), ${savedInstanceState.getString(TAG)}")
    }

    override fun onResume() {
        super.onResume()
        val intentFilter = IntentFilter()
        intentFilter.addAction("android.intent.action.TIME_TICK")
        timeChangeReceiver = TimeChangeReceiver()
        registerReceiver(timeChangeReceiver, intentFilter)

        Log.d(TAG, "onResume()")
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(timeChangeReceiver)
        Log.d(TAG, "onPause()")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop()")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(TAG, "test save instance state")
        Log.d(TAG, "onSaveInstanceState()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy()")
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
    }
}