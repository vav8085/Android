package com.vav.data.login.datasource.impl

import com.vav.data.login.datasource.LoginDataSource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds

class LoginDataSourceImpl @Inject constructor() : LoginDataSource {
    override fun login(username: String, password: String) = flow {
        delay(3000.milliseconds)
        emit("Token")
    }

    override fun logout() = flow {
        emit(Unit)
    }
}