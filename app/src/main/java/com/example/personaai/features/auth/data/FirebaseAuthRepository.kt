package com.example.personaai.features.auth.data

import com.example.personaai.features.auth.domain.AuthRepository
import com.example.personaai.features.auth.domain.AuthResult
import com.example.personaai.features.auth.domain.AuthUser
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class FirebaseAuthRepository @Inject constructor(
    private val auth: FirebaseAuth
) : AuthRepository {

    override val currentUser: Flow<AuthUser?> = callbackFlow {
        val listener = FirebaseAuth.AuthStateListener { firebaseAuth ->
            trySend(firebaseAuth.currentUser?.toAuthUser())
        }
        auth.addAuthStateListener(listener)
        // Seed initial value immediately.
        trySend(auth.currentUser?.toAuthUser())
        awaitClose { auth.removeAuthStateListener(listener) }
    }

    override suspend fun login(email: String, password: String): AuthResult {
        return try {
            auth.signInWithEmailAndPassword(email, password).await()
            AuthResult.Success
        } catch (e: Exception) {
            AuthResult.Error(e.toUserMessage())
        }
    }

    override suspend fun register(
        email: String,
        password: String,
        displayName: String
    ): AuthResult {
        return try {
            val result = auth.createUserWithEmailAndPassword(email, password).await()
            // Persist the display name on the freshly created profile.
            result.user?.updateProfile(
                UserProfileChangeRequest.Builder()
                    .setDisplayName(displayName)
                    .build()
            )?.await()
            AuthResult.Success
        } catch (e: Exception) {
            AuthResult.Error(e.toUserMessage())
        }
    }

    override suspend fun logout() {
        auth.signOut()
    }

    private fun Exception.toUserMessage(): String = when (this) {
        is FirebaseAuthInvalidUserException ->
            "No account found for that email."
        is FirebaseAuthInvalidCredentialsException ->
            "Incorrect email or password."
        is FirebaseAuthUserCollisionException ->
            "An account with this email already exists."
        is FirebaseAuthWeakPasswordException ->
            "Password is too weak. Use at least 6 characters."
        else -> message ?: "Authentication failed. Please try again."
    }
}

private fun com.google.firebase.auth.FirebaseUser.toAuthUser() = AuthUser(
    uid = uid,
    email = email,
    displayName = displayName
)
