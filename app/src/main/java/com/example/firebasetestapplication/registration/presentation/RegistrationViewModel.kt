package com.example.firebasetestapplication.registration.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firebasetestapplication.R
import com.example.firebasetestapplication.core.domain.model.UserData
import com.example.firebasetestapplication.core.domain.util.Resource
import com.example.firebasetestapplication.registration.domain.usecase.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase
) : ViewModel() {

    private val _registrationState = MutableStateFlow(RegistrationState.UiState())
    val registrationState = _registrationState.asStateFlow()

    private val _messageState = MutableSharedFlow<Int?>()
    val messageState = _messageState.asSharedFlow()

    private val _event = MutableSharedFlow<RegistrationState.Event>()

    init {
        subscribeToEvent()
    }

    private fun subscribeToEvent() {
        viewModelScope.launch {
            _event.collect {
                handleEvent(it)
            }
        }
    }

    private fun handleEvent(event: RegistrationState.Event) {
        when (event) {
            is RegistrationState.Event.Register -> registerUser()
            is RegistrationState.Event.ChangeEmail -> updateEmailState(event.email)
            is RegistrationState.Event.ChangeName -> updateNameState(event.name)
            is RegistrationState.Event.ChangePassword -> updatePasswordState(event.password)
            is RegistrationState.Event.ChangeUsername -> updateUsernameState(event.username)
        }
    }

    fun setEvent(event: RegistrationState.Event) {
        viewModelScope.launch {
            _event.emit(event)
        }
    }

    private fun registerUser() {
        val user = createUser()?.let {
            viewModelScope.launch {
                registerUseCase.invoke(it).collect {
                    when (it) {
                        is Resource.Error -> {
                            updateIsLoadingState(false)
                            updateMessageState(it.errorType.stringResId)
                        }
                        is Resource.Loading -> {
                            updateIsLoadingState(true)
                            updateMessageState(null)
                        }
                        is Resource.Success -> {
                            updateIsLoadingState(false)
                            updateMessageState(it.data)
                        }
                    }
                }
            }
        } ?: updateMessageState(R.string.not_all_data_filled_error)
    }

    private fun createUser(): UserData? {
        val username = _registrationState.value.username
        val email = _registrationState.value.email
        val password = _registrationState.value.password
        val name = _registrationState.value.name

        if (username.isBlank() || email.isBlank() || password.isBlank() || name.isBlank()) {
            return null
        }
        return UserData(
            username = username,
            email = email,
            password = password,
            name = name
        )
    }

    private fun isAllDataGiven(): Boolean {
        return _registrationState.value.username?.trim() != null
                && _registrationState.value.email?.trim() != null
                && _registrationState.value.password?.trim() != null
                && _registrationState.value.name?.trim() != null
    }


    private fun updateUsernameState(username: String) {
        _registrationState.update {
            it.copy(
                username = username
            )
        }
    }

    private fun updateIsLoadingState(isLoading: Boolean) {
        _registrationState.update {
            it.copy(
                isLoading = isLoading
            )
        }
    }

    private fun updateEmailState(email: String) {
        _registrationState.update {
            it.copy(
                email = email
            )
        }
    }

    private fun updatePasswordState(password: String) {
        _registrationState.update {
            it.copy(
                password = password
            )
        }
    }

    private fun updateNameState(name: String) {
        _registrationState.update {
            it.copy(
                name = name
            )
        }
    }

    private fun updateMessageState(messageId: Int?) {
        viewModelScope.launch {
            _messageState.emit(messageId)
        }
    }

}