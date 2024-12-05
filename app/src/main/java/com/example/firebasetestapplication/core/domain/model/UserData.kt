package com.example.firebasetestapplication.core.domain.model

import com.example.firebasetestapplication.core.data.model.UserDto

data class UserData(
    val username: String,
    val email: String,
    val password: String,
    val name: String
) {
    fun mapToUserDto() = UserDto(
        username = this.username,
        email = this.email,
        password = this.password,
        name = this.name
    )
}