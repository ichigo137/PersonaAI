package com.example.personaai.core.ai

import kotlinx.coroutines.flow.Flow

interface AiProvider {

    suspend fun sendMessage(
        request: AiRequest
    ): AiResponse

    fun streamMessage(
        request: AiRequest
    ): Flow<AiStreamChunk>

}