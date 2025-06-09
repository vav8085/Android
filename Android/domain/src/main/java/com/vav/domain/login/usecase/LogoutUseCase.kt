package com.vav.domain.login.usecase

import com.vav.domain.login.repository.LoginRepository
import javax.inject.Inject

class LogoutUseCase @Inject constructor(val loginRepository: LoginRepository) {
    fun invoke() = loginRepository.logout()
}