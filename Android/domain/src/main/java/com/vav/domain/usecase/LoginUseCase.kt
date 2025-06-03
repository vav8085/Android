package com.vav.domain.usecase

interface LoginUseCase {
    suspend fun invoke(username: String, password: String): Result<Unit>
}