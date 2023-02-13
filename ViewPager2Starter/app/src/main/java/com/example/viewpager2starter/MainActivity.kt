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
//                tvResult.text = "position: $position"
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
//                tvMethodName.text = "onPageScrolled:"
//                tvResult.text = "position: $position, positionOffset: $positionOffset, positionOffsetPixels: $positionOffsetPixels"
            }
            

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
                tvMethodName.text = "onPageScrollStateChanged:"
                tvResult.text = "state: "+ when (state) {
                    ViewPager2.SCROLL_STATE_IDLE -> "SCROLL_STATE_IDLE"
                    ViewPager2.SCROLL_STATE_DRAGGING -> "SCROLL_STATE_DRAGGING"
                    ViewPager2.SCROLL_STATE_SETTLING -> "SCROLL_STATE_SETTLING"
                    else -> "Unknown state"
                }
            }

        })
    }
}