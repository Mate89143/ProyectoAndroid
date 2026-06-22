package com.example.hangul.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private const val BASE_URL = "https://6a38c85d64a2d8269222c9e5.mockapi.io/api/v1/"

    val api: PaqueteApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .build()
            .create(PaqueteApiService::class.java)
    }
}