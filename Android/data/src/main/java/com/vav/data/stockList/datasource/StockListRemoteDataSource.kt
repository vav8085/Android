package com.vav.data.stockList.datasource

import com.vav.data.stockList.model.StockListItemDto
import kotlinx.coroutines.flow.Flow

interface StockListRemoteDataSource {
    fun getStockData(): Flow<List<StockListItemDto>>
}