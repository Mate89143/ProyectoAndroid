package com.example.hangul

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.hangul.data.api.RetrofitInstance
import com.example.hangul.data.repository.PaqueteRepository
import com.example.hangul.ui.viewmodel.PaqueteViewModel

@Composable
fun PaquetesScreen(
    onCrearPaquete: () -> Unit,
    onBack: () -> Unit,
) {
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

    Scaffold(
        floatingActionButton = {
            Row(
                modifier = Modifier.padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Botón Volver al Inicio
                ExtendedFloatingActionButton(
                    onClick = onBack,
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                    contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                    icon = { Text("🏠", style = MaterialTheme.typography.titleLarge) },
                    text = { Text("Inicio") }
                )

                // Botón Nuevo Paquete
                ExtendedFloatingActionButton(
                    onClick = onCrearPaquete,
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary,
                    icon = { Text("➕", style = MaterialTheme.typography.titleLarge) },
                    text = { Text("Nuevo Paquete") }
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            // Título Estilizado
            Text(
                text = "Administrar Paquetes",
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center
            )

            Text(
                text = "Gestiona tus paquetes turísticos disponibles",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.secondary,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(vertical = 12.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            if (paquetes.isEmpty()) {
                Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            } else {
                // Tarjeta contenedora con elevación
                ElevatedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f, fill = false),
                    colors = CardDefaults.elevatedCardColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    )
                ) {
                    LazyColumn(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        itemsIndexed(paquetes) { index, paquete ->
                            // Fila de paquete con detalles bonitos
                            AdminPackageRow(
                                emoji = when {
                                    paquete.destino.contains("Cartagena", ignoreCase = true) -> "🏖️"
                                    paquete.destino.contains("Medellin", ignoreCase = true) -> "🌺"
                                    paquete.destino.contains("Bogotá", ignoreCase = true) -> "🏙️"
                                    paquete.destino.contains("Eje Cafetero", ignoreCase = true) -> "☕"
                                    else -> "🌴"
                                },
                                name = paquete.destino,
                                price = "$${paquete.precio}",
                                duration = paquete.duracion
                            )

                            // Divisor entre elementos
                            if (index < (paquetes.size - 1)) {
                                HorizontalDivider(
                                    modifier = Modifier.padding(vertical = 12.dp),
                                    color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)
                                )
                            }
                        }
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(80.dp)) // Espacio para que el FAB no tape el contenido
        }
    }
}

@Composable
fun AdminPackageRow(emoji: String, name: String, price: String, duration: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth(),
    ) {
        Text(emoji, style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(
                text = name, 
                fontWeight = FontWeight.Bold, 
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "Valor: $price", 
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Duración: $duration", 
                style = MaterialTheme.typography.bodySmall, 
                color = MaterialTheme.colorScheme.secondary
            )
        }
    }
}
