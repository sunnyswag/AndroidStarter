package com.example.httpstarter.okhttpreq

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import kotlin.concurrent.thread

object SendHttpRequest {

    suspend fun sendOkHttpReqWithGet(): String = withContext(Dispatchers.IO) {
            val client = OkHttpClient()
            val request = Request.Builder()
                .url("https://www.bilibili.com")
                .build()
            val response = client.newCall(request).execute()
            Log.d("huiqinhuang", "do request")
            return@withContext response.body.toString()
    }

     fun sendOkHttpReqWithPost(callback: okhttp3.Callback) {
        thread {
            val client = OkHttpClient().newBuilder()
                .addInterceptor {
                    val request = it.request()
                    val url = request.url.toString()
                    Log.i("huiqinhuang", "intercept: proceed start: $url, at ${System.currentTimeMillis()}")

                    val response = it.proceed(request)
                    val body = response.body

                    Log.i("huiqinhuang", "intercept: proceed start: $url, at ${System.currentTimeMillis()}")

                    response
                }
                .build()

            val contentType = "text/x-markdown; charset=utf-8".toMediaTypeOrNull()
            val body = "hello!".toRequestBody(contentType)

            val request = Request.Builder()
                .url("https://api.github.com/markdown/raw")
                .post(body)
                .build()

            client.newCall(request).enqueue(callback)
        }
    }
}