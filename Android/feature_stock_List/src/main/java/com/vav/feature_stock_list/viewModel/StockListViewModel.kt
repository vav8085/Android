package com.vav.feature_stock_list.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vav.domain.stockList.usecase.GetStockListUseCase
import com.vav.feature_stock_list.state.StockListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StockListViewModel @Inject constructor(val stockListUseCase: GetStockListUseCase) :
    ViewModel() {
    private val _state = MutableStateFlow(StockListUiState.Loading)
    var state = _state.asStateFlow()

    init {
        fetchStockList()
    }

    fun fetchStockList() {
        viewModelScope.launch {
            stockListUseCase.invoke().onStart {
                _state.value = StockListUiState.Loading
            }.collect { result ->
                result.onSuccess { StockListUiState.Success(it) }
                result.onFailure { StockListUiState.Error(it.message.toString()) }
            }
        }
    }
}