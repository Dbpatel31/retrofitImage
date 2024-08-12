package com.example.imageapi

import android.telecom.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {

    @GET("id/1/info")
    fun getImageInfo(): retrofit2.Call<ImageResponse>
}

object RetrofitInstance{

    private val BASE_URL= "https://picsum.photos/"

    val api: ApiService by lazy {
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build().create(ApiService::class.java)
    }
}