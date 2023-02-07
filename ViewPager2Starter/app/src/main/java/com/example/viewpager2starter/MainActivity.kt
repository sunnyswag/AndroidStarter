package com.example.viewpager2starter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.viewpager2starter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        binding.vpTest.adapter = HorizontalVpAdapter(baseContext)

        setContentView(binding.root)
    }
}