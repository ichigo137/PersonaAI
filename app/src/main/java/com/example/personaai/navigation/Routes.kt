package com.example.personaai.navigation

object Routes {
    const val LOGIN = "login"
    const val REGISTER = "register"
    const val HOME = "home"
    const val CHAT = "chat/{conversationId}"

    const val CHAT_ARG = "conversationId"

    fun chat(conversationId: String) = "chat/$conversationId"
}
