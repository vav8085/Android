package com.vav.domain.login

interface LoginUseCase {
    suspend fun invoke(username: String, password: String): Result<Unit>
}