package com.vav.feature_stock_list.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.vav.feature_stock_list.viewModel.StockListViewModel
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
            StockList(uiState, stockClicked)
        }
    }
}

@Composable
fun Loading() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun StockList(state: StockListUiState.Success, stockClicked: (String) -> Unit) {
    if (state.stockListItems.isEmpty()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("No items!")
        }
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            this.items(items = state.stockListItems, key = { item -> item.symbol }) { stock ->
                StockRow(stock, onClick = { stockClicked(stock.symbol) })
            }
        }
    }
}

@Composable
fun StockRow(stock: StockListItem, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick), verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = stock.symbol,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = stock.companyName,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column (horizontalAlignment = Alignment.End) {
            Text(
                text = stock.currentPrice.toString(),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = "${stock.priceChange} (${stock.priceChangePercent}%)",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
fun ErrorView(message: String) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = message, color = Color.Red)
    }
}

@Preview(name = "Loading...", showBackground = true)
@Composable
fun LoadingPreview() {
    Loading()
}

@Preview(name = "Stock list", showBackground = true)
@Composable
fun StockListPreview() {
    StockList(
        StockListUiState.Success(
            listOf(
                StockListItem(
                    "1",
                    "AAPL",
                    "Apple",
                    200.00,
                    2.00,
                    2.00
                ),
                StockListItem(
                    "2",
                    "GOOGL",
                    "Google",
                    105.00,
                    1.500,
                    1.00
                )
            )
        ), {})
}

@Preview(name = "Error View", showBackground = true)
@Composable
fun ErrorViewPreview() {
    ErrorView("Network Error!")
}

