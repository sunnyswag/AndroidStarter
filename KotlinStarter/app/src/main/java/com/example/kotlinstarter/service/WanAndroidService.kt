package com.example.kotlinstarter.service

import com.example.kotlinstarter.bean.BannerBean
import com.example.kotlinstarter.bean.BaseResult
import retrofit2.http.GET

interface WanAndroidService {

    @GET("banner/json")
    suspend fun getBanner(): BaseResult<List<BannerBean>>
}