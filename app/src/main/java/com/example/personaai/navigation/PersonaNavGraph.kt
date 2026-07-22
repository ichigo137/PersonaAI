package com.example.personaai.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.personaai.features.auth.presentation.AuthState
import com.example.personaai.features.auth.presentation.AuthViewModel
import com.example.personaai.features.auth.presentation.LoginScreen
import com.example.personaai.features.auth.presentation.RegisterScreen
import com.example.personaai.features.chat.presentation.ChatScreen
import com.example.personaai.features.home.presentation.HomeScreen

@Composable
fun PersonaNavGraph(
    navController: NavHostController,
    authViewModel: AuthViewModel = hiltViewModel()
) {
    val authState by authViewModel.authState.collectAsStateWithLifecycle()

    // React to auth state changes — navigate accordingly.
    LaunchedEffect(authState) {
        when (authState) {
            is AuthState.Authenticated -> {
                val current = navController.currentDestination?.route
                if (current == Routes.LOGIN || current == Routes.REGISTER) {
                    navController.navigate(Routes.HOME) {
                        popUpTo(Routes.LOGIN) { inclusive = true }
                    }
                }
            }
            is AuthState.Unauthenticated -> {
                val current = navController.currentDestination?.route
                if (current != Routes.LOGIN && current != Routes.REGISTER) {
                    navController.navigate(Routes.LOGIN) {
                        popUpTo(0) { inclusive = true }
                    }
                }
            }
            is AuthState.Loading -> { /* wait */ }
        }
    }

    val startDestination = when (authState) {
        is AuthState.Authenticated -> Routes.HOME
        is AuthState.Unauthenticated -> Routes.LOGIN
        is AuthState.Loading -> Routes.LOGIN
    }

    NavHost(navController = navController, startDestination = startDestination) {

        composable(Routes.LOGIN) {
            LoginScreen(
                onNavigateToRegister = {
                    navController.navigate(Routes.REGISTER)
                }
            )
        }

        composable(Routes.REGISTER) {
            RegisterScreen(
                onNavigateToLogin = {
                    navController.navigate(Routes.LOGIN) {
                        popUpTo(Routes.REGISTER) { inclusive = true }
                    }
                }
            )
        }

        composable(Routes.HOME) {
            HomeScreen(
                onChatClick = { conversationId ->
                    navController.navigate(Routes.chat(conversationId))
                }
            )
        }

        composable(
            route = Routes.CHAT,
            arguments = listOf(
                navArgument(Routes.CHAT_ARG) { type = NavType.StringType }
            )
        ) {
            ChatScreen(
                onBack = { navController.popBackStack() }
            )
        }
    }
}