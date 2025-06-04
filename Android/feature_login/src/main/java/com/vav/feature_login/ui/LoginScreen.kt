package com.vav.feature_login.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.vav.feature_login.state.LoginUiState
import com.vav.feature_login.viewModel.LoginViewModel

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel<LoginViewModel>(),
    navigateToStockList: () -> Unit
) {
    val uiState by viewModel.state.collectAsState()
    LaunchedEffect(uiState) {
        if (uiState is LoginUiState.Success) {
            navigateToStockList()
        }
    }
    UI(uiState, navigateToStockList)
}

@Composable
fun UI(uiState: LoginUiState, navigateToStockList: () -> Unit) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var accessKey by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
        ) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Text(text = "Login", fontWeight = FontWeight.Bold)
            }
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = username,
                enabled = uiState is LoginUiState.Error,
                onValueChange = { newUsername -> username = newUsername },
                label = { Text("UserName") })
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                enabled = uiState is LoginUiState.Error,
                value = password,
                onValueChange = { newPassword -> password = newPassword },
                label = { Text("Password") })
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                enabled = uiState is LoginUiState.Error,
                value = accessKey,
                onValueChange = { newAccessKey -> accessKey = newAccessKey },
                label = { Text("Access Key") })
            Spacer(modifier = Modifier.padding(top = 10.dp))
            Button(
                modifier = Modifier.fillMaxWidth(),
                enabled = uiState is LoginUiState.Error,
                onClick = {
                    keyboardController?.hide()
                    navigateToStockList()
                }) {
                if (uiState is LoginUiState.Loading) {
                    CircularProgressIndicator(color = Color.White)
                } else {
                    Text("Sign In!")
                }
            }
        }
    }
}

@Preview(name = "Error State", showBackground = true)
@Composable
fun LoginErrorScreenPreview() {
    MaterialTheme {
        UI(LoginUiState.Error("Error")) {}
    }
}

@Preview(name = "Default State", showBackground = true)
@Composable
fun LoginDefaultScreenPreview() {
    MaterialTheme {
        UI(LoginUiState.Default) {}
    }
}

@Preview(name = "Success State", showBackground = true)
@Composable
fun LoginSuccessScreenPreview() {
    MaterialTheme {
        UI(LoginUiState.Success) {}
    }
}

@Preview(name = "Loading State", showBackground = true)
@Composable
fun LoginLoadingScreenPreview() {
    MaterialTheme {
        UI(LoginUiState.Loading) {}
    }
}