package com.example.hangul

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DinastiaAMVScreen(
    onNavigateToProfile: () -> Unit,
    onNavigateToDetail: (String, String) -> Unit,
    onNavigateToPaquetes: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Dinastia AMV", style = MaterialTheme.typography.headlineLarge)

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onNavigateToProfile,
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text("Ir a Perfil")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = { onNavigateToDetail("1", "Paquetes Turísticos") },
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text("Ver Paquetes")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = onNavigateToPaquetes,
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text("Administrar Paquetes")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = { onNavigateToDetail("2", "Plan Reservado") },
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text("Ver Plan Reservado")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = { onNavigateToDetail("3", "Destinos Turísticos Populares") },
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text("Ver Destinos Turísticos Populares")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = { onNavigateToDetail("4", "Asesoría") },
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text("Asesoría")
        }
    }
}
