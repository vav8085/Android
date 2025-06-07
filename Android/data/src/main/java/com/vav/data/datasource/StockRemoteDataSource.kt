package com.vav.data.datasource

import com.vav.data.model.StockItemDto
import kotlinx.coroutines.flow.Flow

interface StockRemoteDataSource {
    fun getStockData(): Flow<List<StockItemDto>>
}