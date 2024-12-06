package com.example.firebasetestapplication.core.data.db.dao

import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class FirebaseClientImpl @Inject constructor(
    database: FirebaseFirestore
) : RemoteClient {
    private val collection = database.collection("user")
}