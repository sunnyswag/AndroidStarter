package com.example.httpstarter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.viewpager2.widget.ViewPager2
import com.example.httpstarter.adapter.PagerAdapter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<ViewPager2>(R.id.viewpager2).adapter = PagerAdapter(this)
    }
}