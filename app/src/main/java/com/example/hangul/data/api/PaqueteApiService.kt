package com.example.hangul.data.api

import com.example.hangul.data.model.PaqueteTuristico
import retrofit2.http.GET
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.DELETE
import retrofit2.http.Path

interface PaqueteApiService {

    @GET("paquetes")
    suspend fun getPaquetes(): List<PaqueteTuristico>

    @POST("paquetes")
    suspend fun crearPaquete(
        @Body paquete: PaqueteTuristico
    ): PaqueteTuristico

    @DELETE("paquetes/{id}")
    suspend fun eliminarPaquete(
        @Path("id") id: String
    )
}