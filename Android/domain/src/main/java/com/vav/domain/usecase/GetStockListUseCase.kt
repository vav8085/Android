package com.vav.domain.usecase

import com.vav.domain.model.StockItem
import com.vav.domain.repository.StockListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetStockListUseCase @Inject constructor(private val repository: StockListRepository) {
    fun invoke(): Flow<Result<List<StockItem>>> = repository.getStockList()
}