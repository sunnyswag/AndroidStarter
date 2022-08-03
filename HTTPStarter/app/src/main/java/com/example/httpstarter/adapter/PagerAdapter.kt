package com.example.httpstarter.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.httpstarter.fragment.HTTPURLConFragment
import com.example.httpstarter.fragment.RetrofitFragment
import com.example.httpstarter.fragment.WebViewFragment

class PagerAdapter(fragment: FragmentActivity): FragmentStateAdapter(fragment) {

    private val pagerCreator = mapOf(
        ON_WEBVIEW_DEMO to { WebViewFragment() },
        ON_HTTP_URL_CON_DEMO to { HTTPURLConFragment() },
        ON_RETROFIT_DEMO to { RetrofitFragment() }
    )

    override fun getItemCount(): Int {
        return pagerCreator.size
    }

    override fun createFragment(position: Int): Fragment {
        return pagerCreator[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }

    companion object PagerIndex{
        const val ON_WEBVIEW_DEMO = 0
        const val ON_HTTP_URL_CON_DEMO = 1
        const val ON_RETROFIT_DEMO = 2
    }
}