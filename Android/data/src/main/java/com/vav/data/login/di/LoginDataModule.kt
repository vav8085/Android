package com.vav.data.login.di

import com.vav.data.login.datasource.LoginDataSource
import com.vav.data.login.datasource.impl.LoginDataSourceImpl
import com.vav.data.login.repository.LoginRepositoryImpl
import com.vav.domain.login.repository.LoginRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LoginDataModule {
    @Binds
    @Singleton
    abstract fun bindLoginDataSource(loginDataSourceImpl: LoginDataSourceImpl): LoginDataSource

    @Binds
    @Singleton
    abstract fun bindLoginRepository(loginRepositoryImpl: LoginRepositoryImpl): LoginRepository
}