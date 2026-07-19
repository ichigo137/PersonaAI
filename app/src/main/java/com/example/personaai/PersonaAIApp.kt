package com.example.personaai

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.personaai.ui.navigation.PersonaNavGraph
import com.example.personaai.ui.theme.PersonaAITheme

@Composable
fun PersonaAIApp() {
    PersonaAITheme {
        Surface(
            modifier = Modifier
        ) {
            PersonaNavGraph()
        }
    }
}