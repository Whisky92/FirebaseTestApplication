package com.example.firebasetestapplication.core.data.db.dao

import com.example.firebasetestapplication.core.data.model.LoginUserDto
import com.example.firebasetestapplication.core.data.model.RegisterUserDto

interface AuthClient {
    suspend fun createUser(user: RegisterUserDto)

    suspend fun login(user: LoginUserDto)
}