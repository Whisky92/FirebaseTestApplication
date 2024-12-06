package com.example.firebasetestapplication.login.presentation

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.firebasetestapplication.R
import com.example.firebasetestapplication.core.presentation.components.ChangeScreenButton
import com.example.firebasetestapplication.core.presentation.components.Title
import com.example.firebasetestapplication.login.presentation.components.LoginForm
import com.example.firebasetestapplication.registration.presentation.RegistrationState

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel<LoginViewModel>(),
    switchToRegisterScreen: () -> Unit
) {
    val context = LocalContext.current
    val uiState by viewModel.loginState.collectAsStateWithLifecycle()
    val messageState by viewModel.messageState.collectAsStateWithLifecycle(initialValue = null)

    val message = messageState?.let { stringResource(id = it) }
    LaunchedEffect(key1 = messageState) {
        messageState?.let {
            Toast.makeText(
                context,
                message,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    val onEmailContentChanged: (String) -> Unit = {
        viewModel.setEvent(
            LoginState.Event.ChangeEmail(it)
        )
    }
    val onPasswordContentChanged: (String) -> Unit = {
        viewModel.setEvent(
            LoginState.Event.ChangePassword(it)
        )
    }
    val onLoginButtonClicked: () -> Unit = {
        viewModel.setEvent(
            LoginState.Event.Login
        )
    }

    LoginScreenContent(
        email = uiState.email,
        password = uiState.password,
        onEmailContentChanged = onEmailContentChanged,
        onPasswordContentChanged = onPasswordContentChanged,
        onLoginButtonClicked = onLoginButtonClicked,
        switchToRegisterScreen = switchToRegisterScreen
    )
}

@Composable
@Preview
private fun LoginScreenContent(
    email: String = "",
    password: String = "",
    onEmailContentChanged: (String) -> Unit = {},
    onPasswordContentChanged: (String) -> Unit = {},
    onLoginButtonClicked: () -> Unit = {},
    switchToRegisterScreen: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.linearGradient(
                    listOf(
                        Color(86, 148, 245, 255),
                        Color(83, 124, 245, 255)
                    )
                )
            ),
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Title(text = stringResource(id = R.string.login_text))
        LoginForm(
            email = email,
            password = password,
            onEmailContentChanged = onEmailContentChanged,
            onPasswordContentChanged = onPasswordContentChanged,
            onLoginButtonClicked = onLoginButtonClicked
        )
        ChangeScreenButton(
            text = stringResource(id = R.string.register_text),
            onClick = switchToRegisterScreen
        )
    }
}