package com.example.personaai.features.auth.domain

import kotlinx.coroutines.flow.Flow

/**
 * Minimal user snapshot surfaced to the UI.
 */
data class AuthUser(
    val uid: String,
    val email: String?,
    val displayName: String?
)

/**
 * Outcome of an auth action. Errors carry a user-facing message.
 */
sealed interface AuthResult {
    data object Success : AuthResult
    data class Error(val message: String) : AuthResult
}

/**
 * Abstraction over the authentication backend (Firebase by default).
 */
interface AuthRepository {

    /** Stream of the current user, or null when signed out. */
    val currentUser: Flow<AuthUser?>

    suspend fun login(email: String, password: String): AuthResult

    suspend fun register(email: String, password: String, displayName: String): AuthResult

    suspend fun logout()
}
