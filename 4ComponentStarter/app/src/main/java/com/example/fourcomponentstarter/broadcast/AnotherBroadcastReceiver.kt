package com.example.fourcomponentstarter.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class AnotherBroadcastReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Toast.makeText(context, "AnotherBroadcastReceiver onReceive()", Toast.LENGTH_SHORT).show()
        abortBroadcast()
    }
}