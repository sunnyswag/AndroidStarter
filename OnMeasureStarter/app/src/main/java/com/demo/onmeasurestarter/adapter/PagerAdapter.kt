package com.demo.onmeasurestarter.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.demo.onmeasurestarter.fragment.OnDrawFragment
import com.demo.onmeasurestarter.fragment.OnLayoutFragment
import com.demo.onmeasurestarter.fragment.OnMeasureFragment

class PagerAdapter(fragment: FragmentActivity): FragmentStateAdapter(fragment) {

    private val pagerCreator = mapOf(
        ON_MEASURE_DEMO to { OnMeasureFragment() },
        ON_LAYOUT_DEMO to { OnLayoutFragment() },
        ON_DRAW_DEMO to { OnDrawFragment() }
    )

    override fun getItemCount(): Int {
        return pagerCreator.size
    }

    override fun createFragment(position: Int): Fragment {
        return pagerCreator[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }

    companion object PagerIndex{
        const val ON_MEASURE_DEMO = 0
        const val ON_LAYOUT_DEMO = 1
        const val ON_DRAW_DEMO = 2
    }
}