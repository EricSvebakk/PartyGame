package no.tepohi.partygame

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import no.tepohi.partygame.screens.*

/**
 * Determines items to be included in navbar
 */
sealed class BottomNavItem(var title: String, var icon: ImageVector, var screen_route: String){

    object Options: BottomNavItem("Options", Icons.Filled.Settings, "options_route")
    object ClassicMode: BottomNavItem("Classic Mode", Icons.Filled.Favorite, "classic_mode_route")
    object RoundsMode: BottomNavItem("Rounds Mode", Icons.Filled.Stairs, "rounds_mode_route")
    object TimerMode: BottomNavItem("Time Mode", Icons.Filled.Timer, "time_mode_route")
}

/**
 * Creates navigation capabilities for BottomNavigation()
 */
@Composable
fun NavigationGraph(
    navController: NavHostController
) {
    NavHost(navController, startDestination = BottomNavItem.Options.screen_route) {
        composable(BottomNavItem.Options.screen_route) {
            OptionsScreen(navController)
        }
        composable(BottomNavItem.ClassicMode.screen_route) {
            ClassicModeScreen2()
        }
        composable(BottomNavItem.RoundsMode.screen_route) {
            ClassicModeScreen3()
        }
        composable(BottomNavItem.TimerMode.screen_route) {
            ClassicModeScreen()
        }
    }
}