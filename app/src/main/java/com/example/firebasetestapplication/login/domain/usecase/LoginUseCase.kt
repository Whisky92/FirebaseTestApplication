package com.example.firebasetestapplication.login.domain.usecase

import com.example.firebasetestapplication.R
import com.example.firebasetestapplication.core.domain.repository.UserRepository
import com.example.firebasetestapplication.core.domain.util.ErrorType
import com.example.firebasetestapplication.core.domain.util.Resource
import com.example.firebasetestapplication.registration.presentation.model.LoginUserUIModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(user: LoginUserUIModel) = flow {
        emit(Resource.Loading())

        try {
            userRepository.login(user.mapToLoginUserData())
            emit(Resource.Success(R.string.login_successful))
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            emit(Resource.Error(ErrorType.User.Login))
        }
    }
}