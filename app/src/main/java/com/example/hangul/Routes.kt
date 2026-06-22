package com.example.hangul

import kotlinx.serialization.Serializable

@Serializable
data object DinastiaAMVRoute

@Serializable
data object ProfileRoute

@Serializable
data object PaquetesRoute

@Serializable
data object CrearPaqueteRoute

@Serializable
data class DetailRoute(
    val itemId: String,
    val title: String = "Sin Título",
)

