package com.vav.feature_stock_list.state

import com.vav.domain.stockList.model.StockListItem

sealed interface StockListUiState {
    object Loading: StockListUiState
    data class Success(val stockListItem: List<StockListItem>): StockListUiState
    data class Error(val message: String): StockListUiState
}