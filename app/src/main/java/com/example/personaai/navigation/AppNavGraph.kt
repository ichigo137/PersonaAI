package com.example.personaai.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.personaai.features.chat.presentation.ChatScreen
import com.example.personaai.features.profile.presentation.ProfileScreen
import com.example.personaai.features.settings.presentation.SettingsScreen

@Composable
fun AppNavGraph() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.Chat.route
    ) {

        composable(Routes.Chat.route) {
            ChatScreen()
        }

        composable(Routes.Profile.route) {
            ProfileScreen()
        }

        composable(Routes.Settings.route) {
            SettingsScreen()
        }
    }
}