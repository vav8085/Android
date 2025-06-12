package com.vav.feature_login.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vav.domain.login.usecase.LoginUseCase
import com.vav.domain.login.usecase.LogoutUseCase
import com.vav.feature_login.state.LoginUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow<LoginUiState>(LoginUiState.Default)
    val state = _state.asStateFlow()

    fun login(newUsername: String, newPassword: String) {
        viewModelScope.launch {
            _state.update { LoginUiState.Loading }
            loginUseCase.invoke(newUsername, newPassword).collect { result ->
                result.onSuccess { value ->
                    _state.update {
                        LoginUiState.Success
                    }
                }
                result.onFailure { value ->
                    _state.update {
                        LoginUiState.Error(value.message.toString())
                    }
                }
            }
        }
    }
}