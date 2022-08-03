package com.example.httpstarter.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.httpstarter.R
import com.example.httpstarter.data.App
import com.example.httpstarter.httpservice.AppService
import com.example.httpstarter.httpservice.ServiceCreator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.StringBuilder

class RetrofitFragment: Fragment() {

    lateinit var webContent: TextView
    lateinit var sendRequest: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_retrofit, container, true)

        sendRequest = root.findViewById(R.id.sendRequest)
        sendRequest.setOnClickListener {
            sendRetrofitReq()
        }
        webContent = root.findViewById(R.id.webContent)

        return root
    }

    private fun sendRetrofitReq() {
        val appService = ServiceCreator.create(AppService::class.java)
        appService.getData().enqueue(object : Callback<List<App>> {
            override fun onResponse(call: Call<List<App>>, response: Response<List<App>>) {
                val list = response.body()
                val sb = StringBuilder()
                list?.let {
                    for (app in it) {
                        sb.append("appID: ").append(app.id)
                            .append("appName: ").append(app.name)
                            .append("appVersion: ").append(app.version).append("/n")
                    }
                }

                activity?.runOnUiThread {
                    webContent.text = sb.toString()
                }
            }

            override fun onFailure(call: Call<List<App>>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }
}

