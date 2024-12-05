package com.example.firebasetestapplication.core.domain.util

import com.example.firebasetestapplication.R

sealed interface ErrorType {
    val stringResId: Int

    sealed interface User : ErrorType {
        data object Register : User {
            override val stringResId: Int
                get() = R.string.register_user_error
        }
    }
}