package com.vav.domain.login.usecase

import com.vav.domain.login.repository.LoginRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(val loginRepository: LoginRepository) {
    fun invoke(username: String, password: String) =
        loginRepository.login(username, password)
}