package com.example.hangul

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.hangul.data.api.RetrofitInstance
import com.example.hangul.data.repository.PaqueteRepository
import com.example.hangul.ui.viewmodel.PaqueteViewModel

@Composable
fun CrearPaqueteScreen(
    onBack: () -> Unit
) {

    val viewModel = remember {
        PaqueteViewModel(
            PaqueteRepository(
                RetrofitInstance.api
            )
        )
    }

    var destino by remember { mutableStateOf("") }
    var precio by remember { mutableStateOf("") }
    var duracion by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {

        Text(
            "Crear Paquete",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = destino,
            onValueChange = { destino = it },
            label = { Text("Destino") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            value = precio,
            onValueChange = { precio = it },
            label = { Text("Precio") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            value = duracion,
            onValueChange = { duracion = it },
            label = { Text("Duración") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(24.dp))

        Button(
            onClick = {

                viewModel.crearPaquete(
                    destino = destino,
                    precio = precio.toDoubleOrNull() ?: 0.0,
                    duracion = duracion
                )

                onBack()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Guardar")
        }

        Spacer(Modifier.height(8.dp))

        OutlinedButton(
            onClick = onBack,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Cancelar")
        }
    }
}