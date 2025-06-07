package com.vav.domain.repository

import com.vav.domain.model.StockItem
import kotlinx.coroutines.flow.Flow

interface StockRepository {
    fun getStockList(): Flow<Result<List<StockItem>>>
}