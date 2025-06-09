package com.vav.domain.login.repository

import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    fun login(username: String, password: String): Flow<Result<String>>
    fun logout(): Flow<Result<Unit>>
}