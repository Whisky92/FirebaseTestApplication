package com.example.firebasetestapplication.registration.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun RegistrationForm(
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
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        TextField(
            value = email,
            onValueChange = onEmailContentChanged,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = "",
                    tint = Color.LightGray
                )
            },
            label = {
                Text(text = "test@test.com", color = Color.LightGray)
            },
            shape = RoundedCornerShape(10.dp)
        )
        TextField(
            value = username,
            onValueChange = onUsernameContentChanged,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "",
                    tint = Color.LightGray
                )
            },
            label = {
                Text(text = "Username", color = Color.LightGray)
            },
            shape = RoundedCornerShape(10.dp)
        )
        TextField(
            value = name,
            onValueChange = onNameContentChanged,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "",
                    tint = Color.LightGray
                )
            },
            label = {
                Text(text = "Test Test", color = Color.LightGray)
            },
            shape = RoundedCornerShape(10.dp)
        )
        TextField(
            value = password,
            onValueChange = onPasswordContentChanged,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = "",
                    tint = Color.LightGray
                )
            },
            label = {
                Text(text = "**********", color = Color.LightGray)
            },
            visualTransformation = PasswordVisualTransformation(),
            shape = RoundedCornerShape(10.dp)
        )
        SignUpButton(onSubmit = onRegisterButtonClicked)
    }
}