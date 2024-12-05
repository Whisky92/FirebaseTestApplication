package com.example.firebasetestapplication.registration.domain.usecase

import com.example.firebasetestapplication.R
import com.example.firebasetestapplication.core.domain.model.UserData
import com.example.firebasetestapplication.core.domain.repository.UserRepository
import com.example.firebasetestapplication.core.domain.util.ErrorType
import com.example.firebasetestapplication.core.domain.util.Resource
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(user: UserData) = flow {
        emit(Resource.Loading())

        try {
            userRepository.registerUser(user)
            emit(Resource.Success(R.string.registration_successful))
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            emit(Resource.Error(ErrorType.User.Register))
        }
    }
}