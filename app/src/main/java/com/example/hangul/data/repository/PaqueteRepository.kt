package com.example.hangul.data.repository

import com.example.hangul.data.api.PaqueteApiService
import com.example.hangul.data.model.PaqueteTuristico

class PaqueteRepository(
    private val api: PaqueteApiService
) {

    suspend fun obtenerPaquetes() =
        api.getPaquetes()

    suspend fun crearPaquete(
        paquete: PaqueteTuristico
    ) = api.crearPaquete(paquete)

    suspend fun eliminarPaquete(id: String) =
        api.eliminarPaquete(id)
}
