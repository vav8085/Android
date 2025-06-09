package com.vav.feature_stock_list.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.vav.feature_stock_list.viewModel.StockListViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.vav.domain.stockList.model.StockListItem
import com.vav.feature_stock_list.state.StockListUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StockListScreen(
    viewModel: StockListViewModel = hiltViewModel<StockListViewModel>(),
    backNav: () -> Unit,
    stockDetailNav: (stockSymbol: String) -> Unit
) {
    val uiState by viewModel.state.collectAsState()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Stocks") },
                colors = TopAppBarDefaults.topAppBarColors()
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            UI(uiState, stockDetailNav)
        }
    }
}

@Composable
private fun UI(uiState: StockListUiState, stockClicked: (String) -> Unit) {
    when (uiState) {
        is StockListUiState.Loading -> {
            Loading()
        }

        is StockListUiState.Error -> {
            ErrorView(uiState.message)
        }

        is StockListUiState.Success -> {
            StockList(uiState.stockListItem, stockClicked)
        }
    }
}

@Composable
fun Loading() {

}

@Composable
fun StockList(stockListItems: List<StockListItem>, stockClicked: (String) -> Unit) {

}

@Composable
fun ErrorView(message: String) {

}

@Preview(name = "Loading...", showBackground = true)
@Composable
fun LoadingPreview() {
    Loading()
}

@Preview(name = "Stock list", showBackground = true)
@Composable
fun StockListPreview() {
    StockList(listOf(), {})
}

@Preview(name = "Error View", showBackground = true)
@Composable
fun ErrorViewPreview() {
    ErrorView("Network Error!")
}

