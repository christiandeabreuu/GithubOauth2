package com.example.appgithubapioauth2.app.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object GithubRetrofitInstance {
    private const val BASE_URL = "https://api.github.com/"

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}