package com.example.personaai.core.ai

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PersonaRouter @Inject constructor(
    private val classifier: TaskClassifier,
    private val localAi: LocalAiProvider,
    private val cloudAi: HuggingFaceEngine
){

    suspend fun generateResponse(
        prompt: String,
        conversation: List<String> = emptyList()
    ): String {

        return when (classifier.classify(prompt)) {

            TaskType.LOCAL ->
                localAi.generateResponse(prompt, conversation)

            TaskType.CLOUD ->
                cloudAi.generateResponse(prompt, conversation)
        }
    }
}