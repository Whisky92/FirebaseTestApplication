package com.example.firebasetestapplication.core.data.db.dao

import com.example.firebasetestapplication.core.data.model.UserDto
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.Transaction
import com.google.firebase.firestore.core.OrderBy
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserClientImpl @Inject constructor(
    database: FirebaseFirestore
) : UserClient {
    private val collection = database.collection("user")

    override suspend fun addUser(
        user: UserDto
    ) {
        try {
            collection.add(user).await()
        } catch (e: Exception) {
            throw e
        }
    }
}