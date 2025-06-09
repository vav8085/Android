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
    private val logoutUseCase: LogoutUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow<LoginUiState>(LoginUiState.Default)
    val state = _state.asStateFlow()

    fun login() {
        viewModelScope.launch {
            loginUseCase.invoke("username", "password").collect { result ->
                result.onSuccess { value ->
                    _state.update {
                        LoginUiState.Success
                    }
                }
                result.onFailure { value ->
                    _state.update {
                        LoginUiState.Default
                    }
                }
            }
        }
    }

    fun logout(){
        viewModelScope.launch {
            logoutUseCase.invoke().collect { result ->
                result.onSuccess {
                    //ToDo Logout
                }
            }
        }
    }
}