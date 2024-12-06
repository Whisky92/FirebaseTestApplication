package com.example.firebasetestapplication.core.data.model

import com.example.firebasetestapplication.core.domain.model.RegisterUserData
import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
data class RegisterUserDto(
    val email: String,
    val password: String,
) {
    fun mapToRegisterUserData() = RegisterUserData(
        email = this.email,
        password = this.password,
    )
}
