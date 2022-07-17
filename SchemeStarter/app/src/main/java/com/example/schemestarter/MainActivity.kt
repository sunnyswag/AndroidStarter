package com.example.schemestarter

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<>(R.id.title)

        findViewById<TextView>(R.id.jump).let {
            it.setOnClickListener {

                val url = "scheme://test/areYouOK?OKId=001"

                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                startActivity(intent)
            }
        }
    }

    override fun onPause() {
        super.onPause()
    }
}