package com.example.personaai.core.ai

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CloudAiProvider @Inject constructor() : AiEngine {

    override suspend fun generateResponse(
        prompt: String,
        conversation: List<String>
    ): String {

        return "🔵 CLOUD ROUTER WORKING\n$prompt"
    }
}