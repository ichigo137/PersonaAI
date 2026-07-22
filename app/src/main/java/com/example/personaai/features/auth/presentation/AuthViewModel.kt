package com.example.personaai.features.auth.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.personaai.features.auth.domain.AuthRepository
import com.example.personaai.features.auth.domain.AuthResult
import com.example.personaai.features.auth.domain.AuthUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Top-level authentication state surfaced to the UI / navigation graph.
 */
sealed interface AuthState {
    data object Loading : AuthState
    data object Unauthenticated : AuthState
    data class Authenticated(val user: AuthUser) : AuthState
}

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    val authState: StateFlow<AuthState> = repository.currentUser
        .map { user ->
            user?.let { AuthState.Authenticated(it) } ?: AuthState.Unauthenticated
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = AuthState.Loading
        )

    private val _loginState = MutableStateFlow<AuthUiState>(AuthUiState.Idle)
    val loginState = _loginState.asStateFlow()

    private val _registerState = MutableStateFlow<AuthUiState>(AuthUiState.Idle)
    val registerState = _registerState.asStateFlow()

    fun login(email: String, password: String) {
        _loginState.value = AuthUiState.Loading
        viewModelScope.launch {
            when (val result = repository.login(email, password)) {
                is AuthResult.Success -> _loginState.value = AuthUiState.Idle
                is AuthResult.Error ->
                    _loginState.value = AuthUiState.Error(result.message)
            }
        }
    }

    fun register(email: String, password: String, displayName: String) {
        _registerState.value = AuthUiState.Loading
        viewModelScope.launch {
            when (val result = repository.register(email, password, displayName)) {
                is AuthResult.Success -> _registerState.value = AuthUiState.Idle
                is AuthResult.Error ->
                    _registerState.value = AuthUiState.Error(result.message)
            }
        }
    }

    fun resetLoginState() {
        _loginState.value = AuthUiState.Idle
    }

    fun resetRegisterState() {
        _registerState.value = AuthUiState.Idle
    }

    fun logout() {
        viewModelScope.launch { repository.logout() }
    }
}

sealed interface AuthUiState {
    data object Idle : AuthUiState
    data object Loading : AuthUiState
    data class Error(val message: String) : AuthUiState
}
