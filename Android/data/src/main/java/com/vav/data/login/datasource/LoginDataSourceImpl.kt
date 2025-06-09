package com.vav.data.login.datasource

import kotlinx.coroutines.flow.flow

class LoginDataSourceImpl : LoginDataSource {
    override fun login(username: String, password: String) = flow {
        emit("Token")
    }

    override fun logout() = flow {
        emit(Unit)
    }
}