package com.example.personaai

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.personaai.navigation.PersonaNavGraph
import com.example.personaai.ui.theme.PersonaAITheme

@Composable
fun PersonaAIApp() {
    PersonaAITheme {
        PersonaNavGraph(navController = rememberNavController())
    }
}