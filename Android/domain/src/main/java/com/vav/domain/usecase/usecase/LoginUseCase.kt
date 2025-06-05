package com.vav.domain.usecase.usecase

interface LoginUseCase {
    suspend fun invoke(username: String, password: String): Result<Unit>
}