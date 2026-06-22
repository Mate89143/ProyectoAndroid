package com.example.hangul

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.hangul.data.api.RetrofitInstance
import com.example.hangul.data.repository.PaqueteRepository
import com.example.hangul.ui.viewmodel.PaqueteViewModel

@Composable
fun CrearPaqueteScreen(
    onBack: () -> Unit,
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
    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Título Principal
            Text(
                text = "Crear Nuevo Paquete",
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center
            )

            Text(
                text = "Ingresa la información del destino",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.secondary,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            ElevatedCard(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.elevatedCardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Campo Destino
                    OutlinedTextField(
                        value = destino,
                        onValueChange = { destino = it },
                        label = { Text("Destino") },
                        placeholder = { Text("Ej. San Andrés") },
                        leadingIcon = { Text("📍", style = MaterialTheme.typography.titleLarge) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = MaterialTheme.shapes.medium
                    )

                    // Campo Precio
                    OutlinedTextField(
                        value = precio,
                        onValueChange = { precio = it },
                        label = { Text("Precio") },
                        placeholder = { Text("Ej. 1200000") },
                        leadingIcon = { Text("💰", style = MaterialTheme.typography.titleLarge) },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier.fillMaxWidth(),
                        shape = MaterialTheme.shapes.medium
                    )

                    // Campo Duración
                    OutlinedTextField(
                        value = duracion,
                        onValueChange = { duracion = it },
                        label = { Text("Duración") },
                        placeholder = { Text("Ej. 4 días") },
                        leadingIcon = { Text("⏳", style = MaterialTheme.typography.titleLarge) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = MaterialTheme.shapes.medium
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Botón Guardar
                    Button(
                        onClick = {
                            if (destino.isNotBlank() && precio.isNotBlank() && duracion.isNotBlank()) {
                                viewModel.crearPaquete(
                                    destino = destino,
                                    precio = precio.toDoubleOrNull() ?: 0.0,
                                    duracion = duracion
                                )
                                onBack()
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = MaterialTheme.shapes.medium,
                        contentPadding = PaddingValues(12.dp)
                    ) {
                        Text("💾 Guardar Paquete", fontWeight = FontWeight.Bold)
                    }

                    // Botón Cancelar
                    OutlinedButton(
                        onClick = onBack,
                        modifier = Modifier.fillMaxWidth(),
                        shape = MaterialTheme.shapes.medium,
                        contentPadding = PaddingValues(12.dp),
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = MaterialTheme.colorScheme.error
                        )
                    ) {
                        Text("❌ Cancelar", fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}
