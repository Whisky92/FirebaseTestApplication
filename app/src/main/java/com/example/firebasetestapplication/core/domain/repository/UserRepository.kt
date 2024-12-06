package com.example.firebasetestapplication.core.domain.repository

import com.example.firebasetestapplication.core.domain.model.LoginUserData
import com.example.firebasetestapplication.core.domain.model.RegisterUserData

interface UserRepository {
    suspend fun registerUser(user: RegisterUserData)

    suspend fun login(user: LoginUserData)
}