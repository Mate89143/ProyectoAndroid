package com.example.hangul

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.hangul.ui.theme.HangulTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HangulTheme {
                AppNavigation()
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = DinastiaAMVRoute
    ) {
        // Pantalla de Crear Paquete
        composable<CrearPaqueteRoute> {
            CrearPaqueteScreen {
                navController.popBackStack()
            }
        }

        // Pantalla Principal: Dinastia AMV
        composable<DinastiaAMVRoute> {
            DinastiaAMVScreen(
                onNavigateToProfile = { navController.navigate(ProfileRoute) },
                onNavigateToPaquetes = { navController.navigate(PaquetesRoute) },
                onNavigateToDetail = { id, nombre ->
                    navController.navigate(
                        DetailRoute(
                            itemId = id,
                            title = nombre
                        )
                    )
                }
            )
        }

        // Pantalla de Perfil
        composable<ProfileRoute> {
            ProfileScreen { navController.popBackStack() }
        }

        // Pantalla de Paquetes
        composable<PaquetesRoute> {
            PaquetesScreen()
        }

        // Pantalla de Detalle
        composable<DetailRoute> { backStackEntry ->
            val args: DetailRoute = backStackEntry.toRoute()
            DetailScreen(
                itemId = args.itemId,
                title = args.title,
            ) { navController.popBackStack() }
        }
    }
}
