package com.vav.domain.repository

import com.vav.domain.model.StockItem
import kotlinx.coroutines.flow.Flow

interface StockListRepository {
    fun getStockList(): Flow<Result<List<StockItem>>>
}