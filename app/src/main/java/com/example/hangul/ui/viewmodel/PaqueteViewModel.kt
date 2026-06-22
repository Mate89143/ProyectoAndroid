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

    private val _paquetes =
        MutableStateFlow<List<PaqueteTuristico>>(emptyList())

    val paquetes: StateFlow<List<PaqueteTuristico>>
        get() = _paquetes

    fun cargarPaquetes() {
        viewModelScope.launch {
            _paquetes.value =
                repository.obtenerPaquetes()
        }
    }
}