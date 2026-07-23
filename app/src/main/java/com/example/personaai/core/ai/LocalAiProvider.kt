package com.example.personaai.core.ai

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalAiProvider @Inject constructor() : AiEngine {

    override suspend fun generateResponse(
        prompt: String,
        conversation: List<String>
    ): String {

        return "🟢 LOCAL ROUTER WORKING\n$prompt"
    }
}