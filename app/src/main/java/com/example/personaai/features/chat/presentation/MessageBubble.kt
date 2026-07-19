package com.example.personaai.features.chat.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.personaai.features.chat.domain.model.ChatMessage
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun MessageBubble(
    message: ChatMessage
) {

    val bubbleWidth = LocalConfiguration.current.screenWidthDp.dp * 0.75f
    
    val shape = if (message.isUser) {
        RoundedCornerShape(
            topStart = 20.dp,
            topEnd = 20.dp,
            bottomStart = 20.dp,
            bottomEnd = 4.dp
        )
    } else {
        RoundedCornerShape(
            topStart = 4.dp,
            topEnd = 20.dp,
            bottomStart = 20.dp,
            bottomEnd = 20.dp
        )
    }
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment =
            if (message.isUser)
                androidx.compose.ui.Alignment.CenterEnd
            else
                androidx.compose.ui.Alignment.CenterStart
    ) {

        Surface(
            Modifier.fillMaxWidth(0.75f),
            shape = RoundedCornerShape(20.dp),
            color =
                if (message.isUser)
                    MaterialTheme.colorScheme.primary
                else
                    MaterialTheme.colorScheme.surfaceVariant
        ) {

            androidx.compose.foundation.layout.Column(
                modifier = Modifier.padding(14.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {

                Text(
                    text = message.text,
                    color =
                        if (message.isUser)
                            MaterialTheme.colorScheme.onPrimary
                        else
                            MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.bodyLarge
                )

                Text(
                    text = formatTime(message.timestamp),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.End,
                    style = MaterialTheme.typography.labelSmall,
                    color =
                        if (message.isUser)
                            MaterialTheme.colorScheme.onPrimary.copy(alpha = .7f)
                        else
                            MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = .7f)
                )
            }
        }
    }
}

private fun formatTime(time: Long): String {

    return SimpleDateFormat(
        "HH:mm",
        Locale.getDefault()
    ).format(Date(time))
}