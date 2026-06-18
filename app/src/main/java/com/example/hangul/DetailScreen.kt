package com.example.hangul

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun DetailScreen(itemId: String, title: String, onBack: () -> Unit) {
    val scrollState = rememberScrollState()
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(20.dp))

        // Título dinámico
        Text(
            text = title,
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.ExtraBold,
            textAlign = TextAlign.Center
        )

        // Descripción dinámica según el ID
        val description = when (itemId) {
            "1" -> "Nuestros Paquetes Disponibles"
            "2" -> "Detalles de tu Reserva Actual"
            "3" -> "Los destinos más populares de Colombia"
            "4" -> "Dinastia AMV a tu servicio, ¿en qué te ayudamos?"
            else -> "Información de la sección"
        }

        Text(
            text = description,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.secondary,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(vertical = 12.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // CONTENIDO SEGÚN LA SECCIÓN SELECCIONADA
        when (itemId) {
            "1" -> {
                // SECCIÓN: PAQUETES
                ElevatedCard(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.surface)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        PackageRow("🌴", "Cartagena", "300.000$")
                        HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp))
                        PackageRow("🌊", "Santa Marta", "400.000$")
                        HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp))
                        PackageRow("💃", "Cali", "250.000$")
                    }
                }
            }
            "2" -> {
                // SECCIÓN: PLAN RESERVADO
                ElevatedCard(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
                ) {
                    Column(modifier = Modifier.padding(20.dp)) {
                        Text("✅ Plan: Cartagena Mágico", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            "Incluye tiquetes aéreos, hotel frente al mar y tours guiados. Tu descanso soñado en el Caribe.",
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Text("Total: 300.000$", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.ExtraBold)
                    }
                }
            }
            "3" -> {
                // SECCIÓN: DESTINOS POPULARES
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    DestinationCard("🏰", "Cartagena", "La ciudad heroica, con su historia colonial y playas caribeñas.")
                    DestinationCard("💃", "Cali", "La sucursal del cielo, famosa por su salsa, gastronomía y alegría.")
                    DestinationCard("🏔️", "Santa Marta", "La ciudad más antigua, con la Sierra Nevada y el Parque Tayrona.")
                    DestinationCard("☕", "Manizales", "La ciudad de las puertas abiertas, famosa por su café y paisajes de montaña.")
                    DestinationCard("🌉", "Pereira", "La querendona, trasnochadora y morena, epicentro del Eje Cafetero.")
                }
            }
            else -> {
                // OTRAS SECCIONES
                Card(
                    modifier = Modifier.fillMaxWidth().height(150.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                        Text("Contenido detallado de $title", textAlign = TextAlign.Center)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Botón de acción común
        Button(
            onClick = { /* Acción visual */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (itemId == "2") "Ver Itinerario" else "Más Información")
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Botón para volver
        OutlinedButton(
            onClick = onBack,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Volver al Inicio")
        }
        
        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Composable
fun PackageRow(emoji: String, name: String, price: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(emoji, style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(name, fontWeight = FontWeight.Bold)
            Text("Valor: $price", style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Composable
fun DestinationCard(emoji: String, name: String, description: String) {
    ElevatedCard(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(emoji, style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(name, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                Text(description, style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}
