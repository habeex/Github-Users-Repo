package com.example.githubrepo.di

import com.example.githubrepo.data.repository.UserRepositoryImpl
import com.example.githubrepo.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindUsersRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository

}