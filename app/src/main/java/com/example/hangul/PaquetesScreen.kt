package com.example.hangul

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.hangul.data.api.RetrofitInstance
import com.example.hangul.data.repository.PaqueteRepository
import com.example.hangul.ui.viewmodel.PaqueteViewModel

@Composable
fun PaquetesScreen() {

    val viewModel = remember {
        PaqueteViewModel(
            PaqueteRepository(
                RetrofitInstance.api
            )
        )
    }

    val paquetes by viewModel.paquetes.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.cargarPaquetes()
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(paquetes) { paquete ->

            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(text = paquete.destino)
                Text(text = "$${paquete.precio}")
                Text(text = paquete.duracion)
            }
        }
    }
}