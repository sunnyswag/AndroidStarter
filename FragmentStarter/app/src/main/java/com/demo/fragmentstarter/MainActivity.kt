
package com.demo.fragmentstarter

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<View>(R.id.static_fragment)?.apply {
            findViewById<TextView>(R.id.textView).text = context.getString(R.string.testString)
        }

        findViewById<View>(R.id.static_fragment1)?.apply {
            findViewById<TextView>(R.id.textView).text = context.getString(R.string.testString1)
        }
    }
}