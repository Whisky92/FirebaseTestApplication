package com.example.firebasetestapplication.core.data.repository

import com.example.firebasetestapplication.core.data.db.dao.AuthClient
import com.example.firebasetestapplication.core.data.db.dao.RemoteClient
import com.example.firebasetestapplication.core.domain.model.LoginUserData
import com.example.firebasetestapplication.core.domain.model.RegisterUserData
import com.example.firebasetestapplication.core.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val authClient: AuthClient
) : UserRepository {

    override suspend fun registerUser(user: RegisterUserData) {
        try {
            authClient.createUser(user.mapToRegisterUserDto())
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun login(user: LoginUserData) {
        try {
            authClient.login(user.mapToLoginUserDto())
        } catch (e: Exception) {
            throw e
        }
    }
}