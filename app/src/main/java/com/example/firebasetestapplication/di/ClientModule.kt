package com.example.firebasetestapplication.di

import com.example.firebasetestapplication.core.data.db.dao.AuthClient
import com.example.firebasetestapplication.core.data.db.dao.FirebaseAuthClientImpl
import com.example.firebasetestapplication.core.data.db.dao.RemoteClient
import com.example.firebasetestapplication.core.data.db.dao.FirebaseClientImpl
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
    abstract fun bindRemoteClient(
        remoteClient: FirebaseClientImpl
    ): RemoteClient

    @Binds
    @Singleton
    abstract fun bindAuthClient(
        authClient: FirebaseAuthClientImpl
    ): AuthClient
}