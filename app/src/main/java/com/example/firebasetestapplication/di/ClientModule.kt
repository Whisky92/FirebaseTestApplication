package com.example.firebasetestapplication.di

import com.example.firebasetestapplication.core.data.db.dao.UserClient
import com.example.firebasetestapplication.core.data.db.dao.UserClientImpl
import com.example.firebasetestapplication.core.data.repository.UserRepositoryImpl
import com.example.firebasetestapplication.core.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ClientModule {

    @Binds
    @Singleton
    abstract fun bindUserClient(
        userClient: UserClientImpl
    ): UserClient
}