package com.vav.data.login.repository

import com.vav.data.login.datasource.LoginDataSource
import com.vav.domain.login.repository.LoginRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(val loginDataSource: LoginDataSource) :
    LoginRepository {
    override fun login(
        username: String,
        password: String
    ): Flow<Result<String>> = loginDataSource.login(username, password).map {
        Result.success(it)
    }.flowOn(Dispatchers.IO).catch {
        emit(Result.failure(it))
    }


    override fun logout(): Flow<Result<Unit>> = loginDataSource.logout().map {
        Result.success(it)
    }.flowOn(Dispatchers.IO)
}