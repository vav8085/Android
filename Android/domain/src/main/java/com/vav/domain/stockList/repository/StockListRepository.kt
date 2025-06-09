package com.vav.domain.stockList.repository

import com.vav.domain.stockList.model.StockListItem
import kotlinx.coroutines.flow.Flow

interface StockListRepository {
    fun getStockList(): Flow<Result<List<StockListItem>>>
}