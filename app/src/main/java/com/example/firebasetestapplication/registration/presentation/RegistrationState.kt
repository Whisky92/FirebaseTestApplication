package com.example.firebasetestapplication.registration.presentation

class RegistrationState {
    data class UiState(
        val isLoading: Boolean = false,
        val username: String = "",
        val name: String = "",
        val email: String = "",
        val password: String = "",
    )

    sealed interface Event {
        data class ChangeUsername(val username: String) : Event
        data class ChangePassword(val password: String) : Event
        data class ChangeEmail(val email: String) : Event
        data class ChangeName(val name: String) : Event
        data object Register : Event
    }
}