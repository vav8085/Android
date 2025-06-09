package com.vav.data.login.datasource.impl

import com.vav.data.login.datasource.LoginDataSource
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginDataSourceImpl @Inject constructor() : LoginDataSource {
    override fun login(username: String, password: String) = flow {
        emit("Token")
    }

    override fun logout() = flow {
        emit(Unit)
    }
}