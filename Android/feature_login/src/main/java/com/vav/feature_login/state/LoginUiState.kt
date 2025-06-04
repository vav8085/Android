package com.vav.feature_login.state

sealed interface LoginUiState{
    object Loading: LoginUiState
    data class Error(val message: String): LoginUiState
    object Success: LoginUiState
}