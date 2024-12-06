package com.example.firebasetestapplication.core.domain.model

import com.example.firebasetestapplication.core.data.model.LoginUserDto
import com.example.firebasetestapplication.registration.presentation.model.LoginUserUIModel

data class LoginUserData(
    val email: String,
    val password: String,
) {
    fun mapToLoginUserDto() = LoginUserDto(
        email = this.email,
        password = this.password,
    )

    fun mapToLoginUserUIModel() = LoginUserUIModel(
        email = this.email,
        password = this.password,
    )
}