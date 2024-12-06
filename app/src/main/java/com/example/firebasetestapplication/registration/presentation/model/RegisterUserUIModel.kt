package com.example.firebasetestapplication.registration.presentation.model

import com.example.firebasetestapplication.core.domain.model.RegisterUserData

data class RegisterUserUIModel(
    val email: String,
    val password: String,
) {
    fun mapToRegisterUserData() = RegisterUserData(
        email = this.email,
        password = this.password,
    )
}