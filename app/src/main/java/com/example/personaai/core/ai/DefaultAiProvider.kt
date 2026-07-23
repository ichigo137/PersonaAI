package com.example.personaai.core.ai

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultAiProvider @Inject constructor(
    private val personaRouter: PersonaRouter
) : AiProvider {

    override suspend fun chat(messages: List<ChatMessage>): String {
        val prompt = messages.lastOrNull()?.content.orEmpty()

        return personaRouter.generateResponse(
            prompt = prompt,
            conversation = messages.map { it.content }
        )
    }
}