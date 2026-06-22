package com.example.hangul.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hangul.data.model.PaqueteTuristico
import com.example.hangul.data.repository.PaqueteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PaqueteViewModel(
    private val repository: PaqueteRepository
) : ViewModel() {

    // Estado compartido para el paquete reservado (Simulado como estático para persistencia entre pantallas)
    companion object {
        private val _paqueteReservado = MutableStateFlow<PaqueteTuristico?>(null)
        val paqueteReservado: StateFlow<PaqueteTuristico?> = _paqueteReservado
    }

    private val _paquetes =
        MutableStateFlow<List<PaqueteTuristico>>(emptyList())

    val paquetes: StateFlow<List<PaqueteTuristico>>
        get() = _paquetes

    fun reservarPaquete(paquete: PaqueteTuristico) {
        _paqueteReservado.value = paquete
    }

    fun cargarPaquetes() {
        viewModelScope.launch {
            try {
                _paquetes.value =
                    repository.obtenerPaquetes()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun crearPaquete(
        destino: String,
        precio: Double,
        duracion: String
    ) {
        viewModelScope.launch {

            repository.crearPaquete(
                PaqueteTuristico(
                    id = "",
                    destino = destino,
                    precio = precio,
                    duracion = duracion
                )
            )

            cargarPaquetes()
        }
    }

    fun eliminarPaquete(id: String) {
        viewModelScope.launch {

            repository.eliminarPaquete(id)

            cargarPaquetes()
        }
    }
}

