package com.sunnyswag.statedpressedstarter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sunnyswag.statedpressedstarter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        binding.ivLoveYou.fadeWhenTouch()
    }
}