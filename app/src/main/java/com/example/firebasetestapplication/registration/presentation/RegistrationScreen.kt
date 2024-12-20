package com.example.firebasetestapplication.registration.presentation

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import com.example.firebasetestapplication.core.presentation.components.Title
import com.example.firebasetestapplication.registration.presentation.components.RegistrationForm
import com.example.firebasetestapplication.core.presentation.components.ChangeScreenButton

@Composable
fun RegistrationScreen(
    viewModel: RegistrationViewModel = hiltViewModel<RegistrationViewModel>(),
    switchToLoginScreen: () -> Unit
) {
    val context = LocalContext.current
    val uiState by viewModel.registrationState.collectAsStateWithLifecycle()
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
            RegistrationState.Event.ChangeEmail(it)
        )
    }
    val onPasswordContentChanged: (String) -> Unit = {
        viewModel.setEvent(
            RegistrationState.Event.ChangePassword(it)
        )
    }
    val onRegisterButtonClicked: () -> Unit = {
        viewModel.setEvent(
            RegistrationState.Event.Register
        )
    }

    RegistrationScreenContent(
        password = uiState.password,
        email = uiState.email,
        onPasswordContentChanged = onPasswordContentChanged,
        onEmailContentChanged = onEmailContentChanged,
        onRegisterButtonClicked = onRegisterButtonClicked,
        switchToLoginScreen = switchToLoginScreen
    )
}

@Composable
@Preview
private fun RegistrationScreenContent(
    username: String = "",
    email: String = "",
    password: String = "",
    name: String = "",
    onUsernameContentChanged: (String) -> Unit = {},
    onEmailContentChanged: (String) -> Unit = {},
    onPasswordContentChanged: (String) -> Unit = {},
    onNameContentChanged: (String) -> Unit = {},
    onRegisterButtonClicked: () -> Unit = {},
    switchToLoginScreen: () -> Unit = {}
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
        Title(text = stringResource(id = R.string.register_text))
        RegistrationForm(
            username = username,
            password = password,
            email = email,
            name = name,
            onUsernameContentChanged = onUsernameContentChanged,
            onPasswordContentChanged = onPasswordContentChanged,
            onEmailContentChanged = onEmailContentChanged,
            onNameContentChanged = onNameContentChanged,
            onRegisterButtonClicked = onRegisterButtonClicked
        )
        ChangeScreenButton(
            text = stringResource(id = R.string.login_text),
            onClick = switchToLoginScreen
        )
    }
}