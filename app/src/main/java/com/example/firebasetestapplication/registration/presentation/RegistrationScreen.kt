package com.example.firebasetestapplication.registration.presentation

import android.widget.Toast
import androidx.compose.foundation.background
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
import com.example.firebasetestapplication.core.presentation.components.Title
import com.example.firebasetestapplication.registration.presentation.components.RegistrationForm
import com.example.firebasetestapplication.registration.presentation.components.LoginButton

@Composable
fun RegistrationScreen(
    viewModel: RegistrationViewModel = hiltViewModel<RegistrationViewModel>()
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

    val onUsernameContentChanged: (String) -> Unit = {
        viewModel.setEvent(
            RegistrationState.Event.ChangeUsername(it)
        )
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
    val onNameContentChanged: (String) -> Unit = {
        viewModel.setEvent(
            RegistrationState.Event.ChangeName(it)
        )
    }
    val onRegisterButtonClicked: () -> Unit = {
        viewModel.setEvent(
            RegistrationState.Event.Register
        )
    }

    RegistrationScreenContent(
        username = uiState.username,
        password = uiState.password,
        email = uiState.email,
        name = uiState.name,
        onUsernameContentChanged = onUsernameContentChanged,
        onPasswordContentChanged = onPasswordContentChanged,
        onEmailContentChanged = onEmailContentChanged,
        onNameContentChanged = onNameContentChanged,
        onRegisterButtonClicked = onRegisterButtonClicked
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
    onRegisterButtonClicked: () -> Unit = {}
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
            )    ) {
        Title()
        Spacer(modifier = Modifier.weight(1f))
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
        Spacer(modifier = Modifier.weight(1f))
        LoginButton(onSignUp = {})
    }
}