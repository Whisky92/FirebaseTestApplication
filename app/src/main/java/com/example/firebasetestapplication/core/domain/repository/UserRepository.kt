package com.example.firebasetestapplication.core.domain.repository

import com.example.firebasetestapplication.core.domain.model.UserData

interface UserRepository {
    suspend fun registerUser(user: UserData)
}