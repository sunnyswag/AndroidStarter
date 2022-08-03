package com.example.httpstarter.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.example.httpstarter.R

class WebViewFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_webview, container, true)

        root.findViewById<WebView>(R.id.webView).apply {
            settings.javaScriptEnabled = true
            webViewClient = WebViewClient()
            loadUrl("www.bilibili.com")
        }

        return root
    }
}

