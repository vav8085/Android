package com.vav.data.login.datasource

import kotlinx.coroutines.flow.Flow

interface LoginDataSource {
    fun login(username: String, password: String): Flow<String>
    fun logout(): Flow<Unit>
}