package com.example.firebasetestapplication.registration.presentation.model

import com.example.firebasetestapplication.core.domain.model.LoginUserData
import com.example.firebasetestapplication.core.domain.model.RegisterUserData

data class LoginUserUIModel(
    val email: String,
    val password: String,
) {
    fun mapToLoginUserData() = LoginUserData(
        email = this.email,
        password = this.password,
    )
}