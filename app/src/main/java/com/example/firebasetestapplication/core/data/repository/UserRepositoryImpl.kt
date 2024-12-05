package com.example.firebasetestapplication.core.data.repository

import com.example.firebasetestapplication.core.data.db.dao.UserClient
import com.example.firebasetestapplication.core.domain.model.UserData
import com.example.firebasetestapplication.core.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userClient: UserClient
) : UserRepository {

    override suspend fun registerUser(user: UserData) {
        try {
            userClient.addUser(user.mapToUserDto())
        } catch (e: Exception) {
            throw e
        }
    }
}