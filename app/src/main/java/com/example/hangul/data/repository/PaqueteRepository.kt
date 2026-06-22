package com.example.hangul.data.repository

import com.example.hangul.data.api.PaqueteApiService

class PaqueteRepository(
    private val api: PaqueteApiService
) {

    suspend fun obtenerPaquetes() =
        api.getPaquetes()
}