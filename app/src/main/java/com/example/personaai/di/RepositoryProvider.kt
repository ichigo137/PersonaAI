package com.example.personaai.di

import com.example.personaai.core.database.DatabaseProvider
import com.example.personaai.features.chat.data.ChatRepository
import com.example.personaai.features.chat.data.ChatRepositoryImpl

object RepositoryProvider {

    val chatRepository: ChatRepository by lazy {
        ChatRepositoryImpl(
            DatabaseProvider.database.chatDao()
        )
    }
}