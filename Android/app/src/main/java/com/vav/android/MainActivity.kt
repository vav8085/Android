package com.vav.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vav.android.navigation.AppDestinations
import com.vav.android.ui.theme.AndroidTheme
import com.vav.feature_login.ui.LoginScreen
import com.vav.feature_stock_list.ui.StockListScreen
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidTheme {
                AppNavigation()
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController,
        startDestination = AppDestinations.LOGIN_ROUTE) {

        composable(AppDestinations.LOGIN_ROUTE) {
            LoginScreen(navigateToStockList = {
                navController.navigate(AppDestinations.STOCK_LIST_ROUTE){
                    popUpTo(AppDestinations.LOGIN_ROUTE){
                        inclusive = true
                    }
                    launchSingleTop = true
                }
            })
        }

        composable(AppDestinations.STOCK_LIST_ROUTE){
            StockListScreen(backNav = {}, stockDetailNav = {})
        }
    }
}