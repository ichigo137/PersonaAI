package com.example.personaai.features.chat.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ChatScreen() {

    var message by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),

        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),

            shape = RoundedCornerShape(20.dp)
        ) {

            Text(
                text = "Hello! I'm PersonaAI.\n\nYour conversations will appear here.",
                modifier = Modifier.padding(20.dp),
                style = MaterialTheme.typography.bodyLarge
            )
        }

        OutlinedTextField(
            value = message,
            onValueChange = { message = it },

            modifier = Modifier.fillMaxWidth(),

            label = {
                Text("Message")
            }
        )

        TextButton(
            onClick = { }
        ) {
            Text("Send")
        }
    }
}