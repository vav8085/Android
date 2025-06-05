package com.vav.feature_stock_list.state

import com.vav.domain.usecase.model.StockItem

sealed interface StockListUiState {
    object Loading: StockListUiState
    data class Success(val stockItem: List<StockItem>): StockListUiState
    data class Error(val message: String): StockListUiState
}