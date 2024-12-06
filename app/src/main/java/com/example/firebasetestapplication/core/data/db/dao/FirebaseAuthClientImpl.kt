package com.example.firebasetestapplication.core.data.db.dao

import com.example.firebasetestapplication.core.data.model.LoginUserDto
import com.example.firebasetestapplication.core.data.model.RegisterUserDto
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseAuthClientImpl @Inject constructor(
    private val auth: FirebaseAuth
) : AuthClient {

    override suspend fun createUser(user: RegisterUserDto) {
        try {
            auth.createUserWithEmailAndPassword(
                user.email,
                user.password
            ).await()
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun login(user: LoginUserDto) {
        try {
            auth.signInWithEmailAndPassword(
                user.email,
                user.password
            ).await()
        } catch (e: Exception) {
            throw e
        }
    }
}