package com.example.firebasetestapplication.core.data.model

import com.example.firebasetestapplication.core.domain.model.LoginUserData
import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
data class LoginUserDto(
    val email: String,
    val password: String,
) {
    fun mapToLoginUserData() = LoginUserData(
        email = this.email,
        password = this.password,
    )
}
