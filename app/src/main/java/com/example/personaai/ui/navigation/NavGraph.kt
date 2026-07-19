package com.example.personaai.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.personaai.features.chat.presentation.ChatScreen
import com.example.personaai.features.home.HomeScreen

@Composable
fun PersonaNavGraph() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {

        composable("home") {
            HomeScreen(
                onChatClick = {
                    navController.navigate("chat")
                }
            )
        }

        composable("chat") {
            ChatScreen()
        }
    }
}