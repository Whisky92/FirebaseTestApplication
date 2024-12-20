package com.example.firebasetestapplication.registration.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firebasetestapplication.R
import com.example.firebasetestapplication.core.domain.model.RegisterUserData
import com.example.firebasetestapplication.core.domain.util.Resource
import com.example.firebasetestapplication.registration.domain.usecase.RegisterUseCase
import com.example.firebasetestapplication.registration.presentation.model.RegisterUserUIModel
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
            is RegistrationState.Event.ChangePassword -> updatePasswordState(event.password)
        }
    }

    fun setEvent(event: RegistrationState.Event) {
        viewModelScope.launch {
            _event.emit(event)
        }
    }

    private fun registerUser() {
        createUser()?.let {
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

    private fun createUser(): RegisterUserUIModel? {
        val email = _registrationState.value.email
        val password = _registrationState.value.password

        if (email.isBlank() || password.isBlank()) {
            return null
        }
        return RegisterUserUIModel(
            email = email,
            password = password,
        )
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

    private fun updateMessageState(messageId: Int?) {
        viewModelScope.launch {
            _messageState.emit(messageId)
        }
    }

}