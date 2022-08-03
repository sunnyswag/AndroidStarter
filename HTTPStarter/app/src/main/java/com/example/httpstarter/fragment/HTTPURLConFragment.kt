package com.example.httpstarter.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.httpstarter.R
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

class HTTPURLConFragment: Fragment() {

    lateinit var webContent: TextView
    lateinit var sendRequest: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_httpurlconnection, container, true)

        sendRequest = root.findViewById(R.id.sendRequest)
        webContent = root.findViewById(R.id.webContent)
        sendRequest.setOnClickListener {
//            sendHTTPConnectionReq(it)
            sendReqWithOkHttp(it, object : okhttp3.Callback{
                override fun onFailure(call: Call, e: IOException) {
                    TODO("Not yet implemented")
                }

                override fun onResponse(call: Call, response: Response) {
                    response.body?.string()?.let {
                        activity?.runOnUiThread {
                            webContent.text = it
                        }
                    }
                }

            })
        }

        return root
    }

    private fun sendReqWithOkHttp(view: View, callback: okhttp3.Callback) {
        thread {
            try {
                val client = OkHttpClient()
                val request = Request.Builder()
                    .url("https://www.bilibili.com")
                    .build()
                val response = client.newCall(request).enqueue(callback)
//                val responseData = response.body?.string()
//                responseData?.let {
//                    view.post {
//                        webContent.text = responseData
//                    }
//                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun sendHTTPConnectionReq(view: View) {
        thread {
            var connection: HttpURLConnection? = null
            try {
                val response = StringBuilder()
                val url = URL("http://www.bilibili.com")

                connection = url.openConnection() as HttpURLConnection
                connection.connectTimeout = 8000
                connection.readTimeout = 8000
                val input = connection.inputStream
                val reader = BufferedReader(InputStreamReader(input))
                reader.use {
                    reader.forEachLine {
                        response.append(it)
                    }
                }

                view.post {
                    webContent.text = response
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                connection?.disconnect()
            }
        }
    }
}