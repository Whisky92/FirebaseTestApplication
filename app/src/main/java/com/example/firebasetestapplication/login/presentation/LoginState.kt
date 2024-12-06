package com.example.firebasetestapplication.login.presentation

class LoginState {
    data class UiState(
        val isLoading: Boolean = false,
        val email: String = "",
        val password: String = "",
    )

    sealed interface Event {
        data class ChangePassword(val password: String) : Event
        data class ChangeEmail(val email: String) : Event
        data object Login : Event
    }
}