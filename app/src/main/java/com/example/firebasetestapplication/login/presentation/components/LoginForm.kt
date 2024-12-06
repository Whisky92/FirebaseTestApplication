package com.example.firebasetestapplication.login.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.firebasetestapplication.R
import com.example.firebasetestapplication.core.presentation.components.FormSubmitButton

@Composable
fun LoginForm(
    email: String,
    password: String,
    onEmailContentChanged: (String) -> Unit,
    onPasswordContentChanged: (String) -> Unit,
    onLoginButtonClicked: () -> Unit
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
        FormSubmitButton(
            text = stringResource(id = R.string.login_text),
            onClick = onLoginButtonClicked
        )
    }
}