package com.example.firebasetestapplication.login.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firebasetestapplication.core.domain.util.Resource
import com.example.firebasetestapplication.login.domain.usecase.LoginUseCase
import com.example.firebasetestapplication.registration.presentation.model.LoginUserUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _loginState = MutableStateFlow(LoginState.UiState())
    val loginState = _loginState.asStateFlow()

    private val _messageState = MutableSharedFlow<Int?>()
    val messageState = _messageState.asSharedFlow()

    private val _event = MutableSharedFlow<LoginState.Event>()

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

    private fun handleEvent(event: LoginState.Event) {
        when (event) {
            is LoginState.Event.Login -> login()
            is LoginState.Event.ChangeEmail -> updateEmailState(event.email)
            is LoginState.Event.ChangePassword -> updatePasswordState(event.password)
        }
    }

    fun setEvent(event: LoginState.Event) {
        viewModelScope.launch {
            _event.emit(event)
        }
    }

    private fun login() {
        createUser()?.let {
            viewModelScope.launch {
                loginUseCase.invoke(it).collect {
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
        }
    }

    private fun createUser(): LoginUserUIModel? {
        val email = _loginState.value.email
        val password = _loginState.value.password

        if (email.isBlank() || password.isBlank()) {
            return null
        }
        return LoginUserUIModel(
            email = email,
            password = password,
        )
    }

    private fun updateIsLoadingState(isLoading: Boolean) {
        _loginState.update {
            it.copy(
                isLoading = isLoading
            )
        }
    }

    private fun updateEmailState(email: String) {
        _loginState.update {
            it.copy(
                email = email
            )
        }
    }

    private fun updatePasswordState(password: String) {
        _loginState.update {
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