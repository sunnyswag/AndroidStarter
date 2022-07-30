package com.example.touchconfliction

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val test: String by Delegates.observable("test"){

        }

        test.toString()
        test.toString()
        findViewById<ViewPager2>(R.id.viewPager).adapter = DemoPagerAdapter(this)
    }
}