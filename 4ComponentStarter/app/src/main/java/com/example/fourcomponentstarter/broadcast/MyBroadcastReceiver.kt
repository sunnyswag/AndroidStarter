package com.example.fourcomponentstarter.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class MyBroadcastReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Toast.makeText(context, "MyBroadcastReceiver onReceive()", Toast.LENGTH_SHORT).show()
    }
}