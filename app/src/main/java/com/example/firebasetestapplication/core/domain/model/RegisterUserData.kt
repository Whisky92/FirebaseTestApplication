package com.example.firebasetestapplication.core.domain.model

import com.example.firebasetestapplication.core.data.model.RegisterUserDto
import com.example.firebasetestapplication.registration.presentation.model.RegisterUserUIModel

data class RegisterUserData(
    val email: String,
    val password: String,
) {
    fun mapToRegisterUserDto() = RegisterUserDto(
        email = this.email,
        password = this.password,
    )

    fun mapToRegisterUserUIModel() = RegisterUserUIModel(
        email = this.email,
        password = this.password,
    )
}