package com.vav.feature_login.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.vav.feature_login.state.LoginUiState
import com.vav.feature_login.viewModel.LoginViewModel
//for 'by'
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun LoginScreen(viewModel: LoginViewModel = hiltViewModel<LoginViewModel>(), navigateToStockList: () -> Unit){
    val uiState by viewModel.state.collectAsState()

    LaunchedEffect(uiState) {
        if(uiState is LoginUiState.Success){
            navigateToStockList()
        }
    }
    UI()
}

@Composable
fun UI(){
    Surface(modifier = Modifier.fillMaxSize()) {
        Column (modifier = Modifier.fillMaxSize().padding(16.dp)) {
            Text(text = "Login")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview(){
    MaterialTheme{
        UI ()
    }
}