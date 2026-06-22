package com.example.hangul.data.api

import com.example.hangul.data.model.PaqueteTuristico
import retrofit2.http.GET

interface PaqueteApiService {

    @GET("paquetes")
    suspend fun getPaquetes(): List<PaqueteTuristico>
}