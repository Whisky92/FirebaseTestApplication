package com.example.firebasetestapplication.core.data.model

import com.example.firebasetestapplication.core.domain.model.UserData
import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
data class UserDto(
    val username: String? = null,
    val email: String? = null,
    val password: String? = null,
    val name: String? = null
) {
    fun mapToUserData() = UserData(
        username =  this.username ?: "",
        email = this.email ?: "",
        password = this.password ?: "",
        name = this.name ?: ""
    )
}
