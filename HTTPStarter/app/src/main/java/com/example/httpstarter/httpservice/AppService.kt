package com.example.httpstarter.httpservice

import com.example.httpstarter.data.App
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface AppService {

    @GET("get_data.json")
    fun getData(): Call<List<App>>

    @GET("{page}/get_data.json")
    fun getData(@Path("page") page: Int): Call<List<App>>
}