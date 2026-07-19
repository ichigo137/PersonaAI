package com.example.personaai.features.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Chat
import androidx.compose.material.icons.outlined.Memory
import androidx.compose.material.icons.outlined.Note
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    onChatClick: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),

        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {

        Text(
            text = "PersonaAI",
            style = MaterialTheme.typography.headlineLarge
        )

        Text(
            text = "Your personal AI companion",
            style = MaterialTheme.typography.bodyMedium
        )

        HomeCard(
            icon = Icons.Outlined.Chat,
            title = "Chat",
            subtitle = "Talk with PersonaAI",
            onClick = onChatClick
        )

        HomeCard(
            icon = Icons.Outlined.Memory,
            title = "Memory",
            subtitle = "What I've learned",
            onClick = { }
        )

        HomeCard(
            icon = Icons.Outlined.Note,
            title = "Notes",
            subtitle = "Quick capture",
            onClick = { }
        )

        HomeCard(
            icon = Icons.Outlined.Settings,
            title = "Settings",
            subtitle = "Personalization",
            onClick = { }
        )
    }
}

@Composable
private fun HomeCard(
    icon: ImageVector,
    title: String,
    subtitle: String,
    onClick: () -> Unit
){

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),

        shape = RoundedCornerShape(18.dp),

        elevation = CardDefaults.cardElevation(4.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(22.dp),

            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                imageVector = icon,
                contentDescription = null
            )

            Column(
                modifier = Modifier.padding(start = 18.dp)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium
                )

                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}