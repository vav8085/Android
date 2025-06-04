package com.vav.feature_login.state

sealed interface LoginUiState{
    object Loading: LoginUiState
    object Default: LoginUiState
    object Success: LoginUiState
    data class Error(val message: String): LoginUiState
}