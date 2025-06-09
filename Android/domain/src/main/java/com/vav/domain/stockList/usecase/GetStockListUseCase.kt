package com.vav.domain.stockList.usecase

import com.vav.domain.stockList.model.StockListItem
import com.vav.domain.stockList.repository.StockListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetStockListUseCase @Inject constructor(private val repository: StockListRepository) {
    fun invoke(): Flow<Result<List<StockListItem>>> = repository.getStockList()
}