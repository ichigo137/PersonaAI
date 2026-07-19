package com.example.personaai.navigation

sealed class Routes(val route: String) {
    data object Chat : Routes("chat")
    data object Profile : Routes("profile")
    data object Settings : Routes("settings")
}