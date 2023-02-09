package com.example.viewpager2starter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.viewpager2.widget.ViewPager2
import com.example.viewpager2starter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        binding.initViewPager()

        setContentView(binding.root)
    }

    private fun ActivityMainBinding.initViewPager() {
        vpTest.adapter = HorizontalVpAdapter(baseContext)
        vpTest.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
//                tvMethodName.text = "onPageSelected:"
//                tvResult.text = position.toString()
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
                tvMethodName.text = "onPageScrollStateChanged:"
                tvResult.text = "state: $state, vpTest.currentItem: ${vpTest.currentItem}"
            }
        })
    }
}