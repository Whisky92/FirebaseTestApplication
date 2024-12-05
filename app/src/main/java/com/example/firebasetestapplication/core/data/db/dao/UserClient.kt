package com.example.firebasetestapplication.core.data.db.dao

import com.example.firebasetestapplication.core.data.model.UserDto

interface UserClient {
    suspend fun addUser(user: UserDto)
}