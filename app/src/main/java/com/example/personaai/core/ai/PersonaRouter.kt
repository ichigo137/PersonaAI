package com.example.personaai.core.ai

import com.example.personaai.core.ai.routing.TaskClassifier
import com.example.personaai.core.ai.routing.TaskType
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

        val task = classifier.classify(prompt)

        return when (task) {

            // Offline tools (temporarily handled locally)
            TaskType.CALCULATOR,
            TaskType.MEMORY,
            TaskType.CHAT -> {
                localAi.generateResponse(prompt, conversation)
            }

            // AI-heavy tasks
            TaskType.GENERAL,
            TaskType.CODING,
            TaskType.SUMMARY,
            TaskType.TRANSLATION,
            TaskType.SEARCH,
            TaskType.WEATHER,
            TaskType.UNKNOWN -> {
                cloudAi.generateResponse(prompt, conversation)
            }
        }
    }
}