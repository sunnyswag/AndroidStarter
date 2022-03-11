package com.demo.fragmentstarter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.demo.fragmentstarter.fragment.DynamicFragmentContainer
import com.demo.fragmentstarter.fragment.StaticFragmentContainer

class ViewPagerAdapter(activity: FragmentActivity): FragmentStateAdapter(activity) {

    private val pagerCreator = mapOf(
        STATIC_FRAGMENT_CONTAINER to {StaticFragmentContainer()},
        DYNAMIC_FRAGMENT_CONTAINER to {DynamicFragmentContainer()}
    )

    override fun getItemCount(): Int {
        return pagerCreator.size
    }

    override fun createFragment(position: Int): Fragment {
        return pagerCreator[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }

    companion object{
        private const val STATIC_FRAGMENT_CONTAINER = 0
        private const val DYNAMIC_FRAGMENT_CONTAINER = 1
    }
}