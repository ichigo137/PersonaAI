package com.example.personaai.features.chat.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "chat_messages")
data class ChatEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val text: String,

    val isUser: Boolean,

    val timestamp: Long

)